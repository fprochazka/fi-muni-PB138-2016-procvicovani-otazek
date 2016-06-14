import React, {Component, PropTypes} from 'react';
import {ENTER} from '../../../libs/KeyCode.js';

import FormComponent from '../FormComponent.js';

export default class Text extends FormComponent {

	static propTypes = {
		name: PropTypes.string.isRequired,
		isRequired: PropTypes.bool,
		placeholder: PropTypes.string,
		defaultValue: PropTypes.string,
		value: PropTypes.string,
		autoFocus: PropTypes.bool,
		onBlur: PropTypes.func,
		className: PropTypes.string,
	};

	static defaultProps = {
		isRequired: false,
		className: 'form-control',
	};

	render() {
		return (
			<input
				type={this.inputType}
				ref={ref => this._input = ref}
				placeholder={this.props.placeholder}
				id={this.props.name}
				value={this.props.value}
				defaultValue={this.props.defaultValue}
				onKeyUp={this.onKeyUp.bind(this)}
				autofocus={this.props.autoFocus}
				onBlur={this.handleBlur}
				className={[this.props.className, (this.hasErrors() ? 'form-control-danger' : '')].join(' ')}
			/>
		);
	}

	get inputType() {
		return 'text';
	}

	validate = () => {
		this.validateRequired(this);
	};

	getValue = () => this._input ? this._input.value.trim() : null;

	handleBlur = () => {
		if (this.props.onBlur === null || this.props.onBlur === undefined) {
			return;
		}

		this.props.onBlur(this);
	};

	onKeyUp(e) {
		if (e.keyCode === ENTER) {
			this.context.form.submit(this.props.name);
		}
	}
}
