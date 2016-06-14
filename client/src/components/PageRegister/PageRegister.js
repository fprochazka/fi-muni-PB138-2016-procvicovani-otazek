import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux';
import {browserHistory} from 'react-router'

import {passwordRegister} from '../../actions/authentication/password.js';

import Footer from '../Common/Footer.js';
import Header from '../Common/Header.js';
import RegistrationForm from './RegistrationForm.js';

class PageRegister extends Component {

	static propTypes = {
		passwordRegister: PropTypes.func.isRequired,
	};

	static defaultProps = {};

	render() {
		return <div className="main">
			<div className="container">
				<Header/>
				<div className="row">
					<div className="col-sm-12">
						<RegistrationForm onSubmit={this.handleSubmit}/>
					</div>
				</div>
				<Footer/>
			</div>
		</div>;
	}

	handleSubmit = (e) => {
		console.log(e);
		if (e.hasErrors()) {
			return;
		}
		const values = e.values;
		return this.props.passwordRegister(values.uco, values.email, values.password)
			.then(() => browserHistory.push('/'));
	}

}

export default connect((state, ownProps) => {
	return {};
}, {
	passwordRegister,
})(PageRegister);
