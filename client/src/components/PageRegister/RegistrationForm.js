import React, {Component, PropTypes} from 'react';

import Form from '../Form/Form.js';
import Email from '../Form/Input/Email.js';
import Text from '../Form/Input/Text.js';
import Password from '../Form/Input/Password.js';
import Submit from '../Form/Input/Submit.js';

export default class RegistrationForm extends Component {

	static propTypes = {
		onSubmit: PropTypes.func.isRequired,
	};

	static defaultProps = {};

	errors = [];
	errorsByControl = {};

	render() {
		return <Form className="form-register" onSubmit={this.handleSubmit}>
			<h2 className="form-register-heading">Registrace</h2>

			{this.errors.map((item, i) => {
				return <div className="alert alert-danger" role="alert" key={i}>{item.error}</div>
			})}

			<fieldset className={["form-group", this.hasErrors('uco') ? 'has-danger' : ''].join(' ')}>
				<label htmlFor="uco">UČO</label>
				<Text isRequired
				      name='uco'
				      label="UČO"
				      placeholder="Vyplňte učo"
				      autoFocus={true} />
			</fieldset>
			<fieldset className={["form-group", this.hasErrors('email') ? 'has-danger' : ''].join(' ')}>
				<label htmlFor="email">Email</label>
				<Email isRequired
				       name='email'
				       label="Email"
				       placeholder="Vyplňte email"/>
				<small className="text-muted">Slouží pouze pro obnovení hesla, nezasíláme reklamní sdělení.</small>
			</fieldset>
			<fieldset className={["form-group", this.hasErrors('password') ? 'has-danger' : ''].join(' ')}>
				<label htmlFor="password">Heslo</label>
				<Password isRequired
				          name='password'
				          label="Heslo"
				          placeholder="Heslo"/>
			</fieldset>

			<Submit name='submit' className="btn btn-lg btn-primary btn-block">Registrovat se</Submit>
		</Form>;
	}

	hasErrors = (name) => typeof this.errorsByControl[name] != "undefined";

	handleSubmit = (e) => {
		this.errors = e.getErrors();
		this.errorsByControl = e.getErrorsByControl();

		if (e.hasErrors()) {
			this.forceUpdate();
		}

		this.props.onSubmit(e)
			.catch((data) => {
				if (data.json && data.json.errors) {
					data.json.errors.forEach((val) => {
						e.addError({
							name: null,
							error: val.message,
						});
					});

					this.errors = e.getErrors();
					this.errorsByControl = e.getErrorsByControl();
					this.forceUpdate();
				}
			});
	}

}
