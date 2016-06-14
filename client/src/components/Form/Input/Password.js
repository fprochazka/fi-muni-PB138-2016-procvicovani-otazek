import React, {Component, PropTypes} from 'react';
import Text from './Text.js';

class Password extends Text {

	static propTypes = {
		name: PropTypes.string.isRequired,
		isRequired: PropTypes.bool,
		placeholder: PropTypes.string,
		defaultValue: PropTypes.string,
		value: PropTypes.string,
		autoFocus: PropTypes.bool,
		onBlur: PropTypes.func,
		className: PropTypes.string,
		minLengthValidate: PropTypes.bool,
	};

	static defaultProps = {
		isRequired: false,
		className: 'form-control',
		minLengthValidate: true,
	};

	get inputType() {
		return 'Password';
	}

	getValue = () => this._input.value;

	validate = () => {
		this.validateRequired(this);

		if (this.getValue && this.props.minLengthValidate) {
			if (this.getValue().length && this.getValue().length < 6) {
				this.addError('Myslete na svoji bezpečnost, vaše heslo musí mít alespoň 6\xa0znaků');
			}
		}
	};

}

export default Password;
