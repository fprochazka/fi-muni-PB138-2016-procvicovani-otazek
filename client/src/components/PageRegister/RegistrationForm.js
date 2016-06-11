import React, {Component, PropTypes} from 'react';

export default class RegistrationForm extends Component {

	static propTypes = {
		onSubmit: PropTypes.func.isRequired,
		ucoValue: PropTypes.string,
		emailValue: PropTypes.string,
		passwordValue: PropTypes.string,
	};

	static defaultProps = {};

	constructor(props) {
		super(props);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	componentDidMount() {
	}

	componentWillUnmount() {

	}

	componentWillReceiveProps(nextProps) {
	}

	render() {
		return <form className="form-register">
			<h2 className="form-register-heading">Registrace</h2>

			<fieldset className="form-group">
				<label htmlFor="uco">UČO</label>
				<input type="email" className="form-control" id="uco" placeholder="Vyplňte učo"/>
			</fieldset>
			<fieldset className="form-group">
				<label htmlFor="email">Email</label>
				<input type="email" className="form-control" id="email" placeholder="Vyplňte email"/>
				<small className="text-muted">Slouží pouze pro obnovení hesla, nezasíláme reklamní sdělení.</small>
			</fieldset>
			<fieldset className="form-group">
				<label htmlFor="password">Heslo</label>
				<input type="password" className="form-control" id="password" placeholder="Heslo"/>
			</fieldset>

			<button className="btn btn-lg btn-primary btn-block" type="submit" onClick={this.handleSubmit}>
				Registrovat se
			</button>
		</form>;
	}

	handleSubmit() {
		this.props.onSubmit(this.props);
	}

}
