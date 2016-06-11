import React, {Component, PropTypes} from 'react';
import {Link} from 'react-router';

class EditorHeader extends Component {


	static propTypes = {
		drill: PropTypes.shape({
			code: PropTypes.string.isRequired,
		}),
	};

	static defaultProps = {};

	constructor(props) {
		super(props);
		this.handleSave = this.handleSave.bind(this);
	}

	render() {
		const {drill} = this.props;

		return <div className="header clearfix">
			<nav>
				<ul className="nav nav-pills pull-xs-right">
					<li className="nav-item">
						<Link to={`/`} className="nav-link">Odejít bez uložení</Link>
					</li>
					<li className="nav-item">
						<a className="nav-link btn btn-primary" onClick={this.handleSave}>Uložit změny</a>
					</li>
				</ul>
			</nav>
			<h3 className="text-muted">
				{drill.code}
			</h3>
		</div>;
	}

	handleSave() {

	}
}

export default EditorHeader;
