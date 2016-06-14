import React, {Component, PropTypes} from 'react';
import Text from './Text.js';
import validateEmail from '../../../libs/validateEmail.js';

class Email extends Text {

	get inputType() {
		return 'email';
	}

	getValue = () => this._input.value.trim().toLowerCase();

	validate = () => {
		const value = this.getValue();

		this.validateRequired(this);
		if (value.length > 0 && !validateEmail(value)) {
			this.addError('Zadejte platnou e-mailovou adresu');
		}
	};

}

export default Email;
