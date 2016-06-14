import React, {Component, PropTypes} from 'react';

export default class FormComponent extends Component {

	static propTypes = {
		name: PropTypes.string.isRequired,
		isRequired: PropTypes.bool,
		requiredErrorMessage: PropTypes.string,
		label: PropTypes.string,
	};

	static contextTypes = {
		form: PropTypes.object,
	};

	static defaultProps = {
		isRequired: false,
		label: '',
	};

	errors = [];

	componentDidMount() {
		this.context.form.addComponent(this);
	}

	addError = (error) => {
		this.context.form.addError({name: this.props.name, error});
		this.errors.push(error);
		this.forceUpdate();
	};

	hasErrors = () => this.errors.length > 0;

	truncateErrors = () => {
		this.errors = [];
		this.forceUpdate();
	};

	validate = () => ({});

	getValue = () => ({});

	validateRequired = (scope) => {
		if (this.props.isRequired && !scope.getValue()) {
			this.addError('Vyplňte ' + this.props.label.toLowerCase());
		}
	};

}
