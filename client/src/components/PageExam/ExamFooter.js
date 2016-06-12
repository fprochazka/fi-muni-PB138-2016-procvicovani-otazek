import React, {Component, PropTypes} from 'react';
import {Link} from 'react-router';

export default class ExamFooter extends Component {

	static propTypes = {
		drill: PropTypes.shape({
			id: PropTypes.any.isRequired,
			code: PropTypes.string.isRequired,
			name: PropTypes.string.isRequired,
		}),
	};

	static defaultProps = {};

	render() {
		const {drill} = this.props;

		return <div className="footer-fixed-fluid">
			<div className="container">
				<div className="row">
				{drill
					? <span className="col-sm-4">Otázka: <b>1</b> z 359</span>
					: <span className="col-sm-4">Načítám...</span>}
				<span className="col-sm-4 text-sm-center">
					{drill ? drill.code : ''}
				</span>
				<span className="col-sm-4 text-sm-right">
					<Link to={`/`}>Hlavní strana</Link>
				</span>
				</div>
			</div>
		</div>;
	}

}
