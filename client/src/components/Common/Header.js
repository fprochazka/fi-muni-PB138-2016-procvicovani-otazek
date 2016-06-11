import React, {Component, PropTypes} from 'react';
import {Link} from 'react-router';

class Header extends Component {
	render() {
		return <div className="header clearfix">
			<nav>
				<ul className="nav nav-pills pull-xs-right">
					<li className="nav-item">
						<Link to={`/prihlaseni`} className="nav-link">Přihlášení</Link>
					</li>
					<li className="nav-item">
						<Link to={`/registrace`} className="nav-link">Registrace</Link>
					</li>
				</ul>
			</nav>
			<h3 className="text-muted">
				<Link to={`/`} className="nav-link">Drill pro FI MUNI</Link>
			</h3>
		</div>
	}
}

export default Header;
