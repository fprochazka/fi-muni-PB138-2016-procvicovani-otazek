import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux';
import {browserHistory} from 'react-router'

import {passwordLogin} from '../../actions/authentication/password.js';

import Footer from '../Common/Footer.js';
import Header from '../Common/Header.js';
import LoginForm from './LoginForm.js';

class PageLogin extends Component {

	static propTypes = {
		passwordLogin: PropTypes.func.isRequired,
	};

	static defaultProps = {};

	render() {
		return <div className="main">
			<div className="container">
				<Header/>
				<div className="row">
					<div className="col-sm-12">
						<LoginForm onSubmit={this.handleSubmit} />
					</div>
				</div>
				<Footer/>
			</div>
		</div>;
	}

	handleSubmit = (e) => {
		if (e.hasErrors()) {
			return;
		}
		const values = e.values;

		return this.props.passwordLogin(values.username, values.password)
			.then(() => browserHistory.push('/'));
	}

}

export default connect((state, ownProps) => {
	return {};
}, {
	passwordLogin,
})(PageLogin);
