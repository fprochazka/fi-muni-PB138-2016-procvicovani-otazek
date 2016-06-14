import React, {Component, PropTypes} from 'react';

import Form from '../Form/Form.js';
import Text from '../Form/Input/Text.js';
import Password from '../Form/Input/Password.js';
import Submit from '../Form/Input/Submit.js';

export default class PageLogin extends Component {

	static propTypes = {
		onSubmit: PropTypes.func.isRequired,
	};

	static defaultProps = {};

	errors = [];

	render() {
		return <Form className="form-signin" onSubmit={this.handleSubmit}>
			<h2 className="form-signin-heading">Přihlášení</h2>

			{this.errors.map((item, i) => {
				return <div className="alert alert-danger" role="alert" key={i}>{item.error}</div>
			})}

			<label htmlFor="username" className="sr-only">Email nebo UČO</label>
			<Text isRequired
			      name="username"
			      placeholder="Email nebo UČO"
			      label="email nebo učo"
			      autoFocus={true} />

			<label htmlFor="password" className="sr-only">Heslo</label>
			<Password isRequired
			          minLengthValidate={false}
			          name="password"
			          label="heslo"
			          placeholder="Heslo" />

			<Submit name='submit' className="btn btn-lg btn-primary btn-block">Přihlásit se</Submit>
		</Form>;
	}

	handleSubmit = (e) => {
		this.errors = e.getErrors();
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
					this.forceUpdate();
				}
			});
	}

}
