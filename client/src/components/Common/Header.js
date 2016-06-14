import React, {Component, PropTypes} from 'react';
import {Link} from 'react-router';
import {connect} from 'react-redux';
import {browserHistory} from 'react-router'

import {logout} from '../../actions/authentication/password.js';

class Header extends Component {

	static propTypes = {
		user: PropTypes.shape({
			email: PropTypes.string,
		}),
	};

	render() {
		return <div className="header clearfix">
			<nav>
				<ul className="nav nav-pills pull-xs-right">
					{!this.props.user ? <li className="nav-item">
						<Link to={`/prihlaseni`} className="nav-link">Přihlášení</Link>
					</li> : ''}
					{!this.props.user ? <li className="nav-item">
						<Link to={`/registrace`} className="nav-link">Registrace</Link>
					</li> : ''}
					{this.props.user ? <li className="nav-item">
						<span className="nav-link">{this.props.user.email}</span>
					</li> : ''}
					{this.props.user ? <li className="nav-item">
						<a className="nav-link" href="javascript:;" onClick={this.handleLogout}>Odhlásit se</a>
					</li> : ''}
				</ul>
			</nav>
			<h3 className="text-muted">
				<Link to={`/`} className="nav-link">Drill pro FI MUNI</Link>
			</h3>
		</div>
	}

	handleLogout = () => {
		this.props.logout()
			.then(() => browserHistory.push('/'));
	}

}

export default connect((state, ownProps) => {
	return {
		user: state.authentication.user,
	};
}, {
	logout
})(Header);
