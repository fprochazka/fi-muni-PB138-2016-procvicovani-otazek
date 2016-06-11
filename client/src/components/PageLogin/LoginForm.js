import React, {Component, PropTypes} from 'react';

export default class PageLogin extends Component {

	static propTypes = {
		onSubmit: PropTypes.func.isRequired,
		usernameValue: PropTypes.string,
		passwordValue: PropTypes.string,
	};

	static defaultProps = {};

	componentDidMount() {
	}

	componentWillUnmount() {

	}

	componentWillReceiveProps(nextProps) {
	}

	render() {
		return <form className="form-signin">
			<h2 className="form-signin-heading">Přihlášení</h2>
			<label htmlFor="inputUsername" className="sr-only">Email nebo UČO</label>
			<input type="username" id="inputUsername" className="form-control" placeholder="Email nebo UČO" required autofocus/>
			<label htmlFor="inputPassword" className="sr-only">Heslo</label>
			<input type="password" id="inputPassword" className="form-control" placeholder="Heslo" required/>

			<button className="btn btn-lg btn-primary btn-block" type="submit" onClick={this.handleSubmit}>
				Přihlásit se
			</button>
		</form>;
	}

	handleSubmit() {
		this.props.onSubmit(this.props);
	}

}
