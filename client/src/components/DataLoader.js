import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux';
import AbortException, {CODE_ABORT} from '../libs/AbortException';
import {LEVEL_EXCEPTION} from '../libs/Exception';
// import catchErrors from '../../libs/catchErrors/index.js';
// import {handleError} from '../../actions/flashMessages.js';
// import {REPLACE} from 'history/lib/Actions.js';
import getComponentName from '../../libs/getComponentName.js';

import Spinner, {THEME_PINK} from 'components/Spinner/Spinner.js';

/**
 * Component enhancer for pre-loading needed data.
 *
 * Has ability to prevent load via preventLoad prop. Use this prop ONLY, if you are sure, that data was already loaded once at least.
 * It'll try to render enhanced component without complete data otherwise, which can and most likely will result in error state of it.
 *
 * @param {function} callback Must return Promise
 * @param {string} spinnerTheme
 * @returns {function}
 * @constructor
 */
const DataLoader = (callback, spinnerTheme = THEME_PINK) => {

	function abort(bool = true) {
		const exception = new AbortException();
		exception.continue = bool;
		throw exception;
	}

	function promiseCancelable(promise) {
		let doCancel;

		const cancelPromise = new Promise((resolve, reject) => {
			doCancel = () => {
				reject(new AbortException());
			};
		});

		const race = Promise.race([
			promise,
			cancelPromise,
		]);

		race.cancel = doCancel;

		return race;
	}

	return function wrap(ComposedComponent) {
		class DataLoaderClass extends Component {

			static displayName = 'DataLoader(' + getComponentName(ComposedComponent) + ')';

			static WrappedComponent = ComposedComponent;

			static propTypes = {
				preventLoad: PropTypes.bool,
				location: PropTypes.object.isRequired,
				dispatch: PropTypes.func.isRequired,
			};

			static contextTypes = {
				store: PropTypes.object,
			};

			state = {
				dataLoaded: false,
			};

			promise = null;

			cancelPromise = () => {
				if (this.promise !== null) {
					this.promise.cancel();
				}
			};

			/**
			 * Retrieves promise from callback and updates state.
			 * @param {object} props
			 * @returns {Promise|null}
			 */
			loadData = (props) => {
				this.cancelPromise();

				if (this.props.preventLoad) {
					return null;
				}

				this.setState({dataLoaded: false});

				let aborted = false;
				let continueProcess = true;
				try {
					const promise = callback(props.location, props.params, props.dispatch, this.context.store, abort);
					this.promise = promiseCancelable(promise);
				} catch (error) {
					try {
						catchErrors(error, (errorCase, level) =>
							level(LEVEL_EXCEPTION, (errorCase) =>
								errorCase(CODE_ABORT, (exception) => {
									aborted = true;
									continueProcess = exception.continue !== undefined ? exception.continue : true;
								})
							)
						);
					} catch (error) {
						aborted = true;
						continueProcess = false;
						this.props.dispatch(handleError(error));
					}
				}

				if (aborted && continueProcess) {
					this.setState({dataLoaded: true});
					return null;
				}

				if (aborted) {
					return null;
				}

				if (this.promise === null || this.promise === undefined || this.promise.then === undefined) {
					throw new Error('No promise was given to DataLoader');
				}

				return this.promise.then(() => new Promise(resolve => {
					this.setState({dataLoaded: true}, () => resolve());
				})).catch(error =>
					catchErrors(error, (errorCase, level) =>
						level(LEVEL_EXCEPTION, (errorCase) =>
							errorCase(CODE_ABORT, (exception) => {
								if (exception.continue === true) {
									this.setState({dataLoaded: true});
								}
							})
						)
					)
				).catch((error) => {
					this.props.dispatch(handleError(error));
				});
			};

			componentWillMount() {
				this.loadData(this.props);
			}

			componentWillReceiveProps(nextProps) {
				const nextUrl = nextProps.location.pathname + nextProps.location.search;
				const currentUrl = this.props.location.pathname + this.props.location.search;

				if (nextUrl !== currentUrl && nextProps.location.action !== REPLACE) {
					this.loadData(nextProps);
				}
			}

			componentWillUnmount() {
				this.cancelPromise();
			}

			render() {
				if (this.state.dataLoaded) {
					return <ComposedComponent {...this.props} reload={() => this.loadData(this.props)}/>;
				}
				return <div><Spinner theme={spinnerTheme}/></div>;
			}

		}

		return connect(state =>
			({
				location: state.router.location,
				params: state.router.params,
			})
		)(HoistStatics(DataLoaderClass, ComposedComponent));
	};
};

export default DataLoader;
