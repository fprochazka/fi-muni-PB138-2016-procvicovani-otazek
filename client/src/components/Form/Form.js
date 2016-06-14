import React, {Component, PropTypes} from 'react';

export default class Form extends Component {
	components = [];
	errors = [];
	submits = [];

	static propTypes = {
		onSubmit: PropTypes.func.isRequired,
		children: PropTypes.node.isRequired,
		className: PropTypes.string,
	};

	static defaultProps = {
		className: '',
	};

	static childContextTypes = {
		form: PropTypes.object,
	};

	render() {
		return (
			<form
				onSubmit={(e) => {
					e.preventDefault();
					this.submit(null);
				}}
				noValidate
				className={this.props.className}
			>
				{this.props.children}
			</form>
		);
	}

	getChildContext() {
		return {
			form: this,
		};
	}

	getValues = () => {
		let values = {};
		this.components.forEach((component) => {
			values = Object.assign(values, {
				[component.props.name]: component.getValue(),
			});
		});

		return values;
	};

	addComponent = (component) => {
		this.components.push(component);
	};

	getComponent = (componentName) =>
		this.components.filter((component) => component.props.name === componentName)[0];

	addSubmit = (submit) => {
		this.submits.push(submit);
	};

	addError = (error) => {
		this.errors = [...this.errors, error];
		this.forceUpdate();
	};

	getErrors = () => this.errors;

	getErrorsByControl = () =>
		Object.keys(this.errors).reduce((prev, curr) => {
			let error = {};
			error[this.errors[curr].name] = this.errors[curr].error;
			return {...prev, ...error};
		}, {});

	/**
	 * Truncates all errors (even on child components)
	 */
	truncateErrors = () => {
		this.errors = [];
		this.components.forEach((component) => {
			component.truncateErrors();
		});
	};

	hasErrors = () => this.errors.length > 0;

	validate = () => {
		this.components.forEach((component) => {
			component.validate();
		});
	};

	submit = (submitName) => {
		this.truncateErrors();
		this.validate();

		this.props.onSubmit({
			submittedBy: submitName,
			values: this.getValues(),
			getErrors: this.getErrors,
			getErrorsByControl: this.getErrorsByControl,
			addError: this.addError,
			hasErrors: this.hasErrors,
		});
	};
}
