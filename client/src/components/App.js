import React, {Component, PropTypes} from 'react'
import {connect} from 'react-redux'
import {browserHistory} from 'react-router'
// import Explore from '../components/Explore'
// import {resetErrorMessage} from '../actions'

class App extends Component {

	static propTypes = {
		// Injected by React Redux
		// errorMessage: PropTypes.string,
		// resetErrorMessage: PropTypes.func.isRequired,
		// inputValue: PropTypes.string.isRequired,
		// Injected by React Router
		main: PropTypes.node,
		children: PropTypes.node,
	};

	static defaultProps = {};

	constructor(props) {
		super(props);
		// this.handleChange = this.handleChange.bind(this);
		// this.handleDismissClick = this.handleDismissClick.bind(this)
	}

	render() {
		const {main, children} = this.props;
		let content = null;

		switch (true) {
			case main !== undefined:
				content = main;
				break;
			case children !== undefined:
				content = children;
				break;
			default:
				content = '';
				break;
		}

		/*{this.renderErrorMessage()}*/
		return content;
	}
}

export default connect((state, ownProps) => {
	return {
		// errorMessage: state.errorMessage,
		// inputValue: ownProps.location.pathname.substring(1)
	}
}, {
	// resetErrorMessage
})(App);
