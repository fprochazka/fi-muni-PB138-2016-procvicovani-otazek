import React, {Component, PropTypes} from 'react';
import Text from './Text.js';

class Password extends Text {

	get inputType() {
		return 'Password';
	}

	getValue = () => this._input.value;

	validate = () => {
		this.validateRequired(this);

		if (this.getValue) {
			if (this.getValue().length && this.getValue().length < 6) {
				this.addError('Myslete na svoji bezpečnost, vaše heslo musí mít alespoň 6\xa0znaků');
			}
		}
	};

}

export default Password;
