import React, {Component, PropTypes} from 'react';
import {Link} from 'react-router';

export default class DrillFooter extends Component {

	static propTypes = {
	};

	static defaultProps = {};

	componentDidMount() {
	}

	componentWillUnmount() {

	}

	componentWillReceiveProps(nextProps) {
	}

	render() {
		return <div className="footer-fixed-fluid">
			<div className="container">
				<div className="row">
				<span className="col-sm-4">
					Otázka: <b>1</b> z 359
				</span>
				<span className="col-sm-4 text-sm-center">
					VB036
				</span>
				<span className="col-sm-4 text-sm-right">
					<Link to={`/`}>Hlavní strana</Link>
				</span>
				</div>
			</div>
		</div>;
	}

	handleSubmit() {
		this.props.onSubmit(this.props);
	}

}
