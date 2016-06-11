import React, {Component, PropTypes} from 'react';

import DrillAnswer from './DrillAnswer.js'

export default class DrillQuestion extends Component {

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
		return <div className="container-fluid">
			<div className="row">
				<p className="question">
					MIKE SAID THAT IF HE _____ ABOUT THE DOG'S ERRATIC BEHAVIOUR, HE WOULD NEVER HAVE BOUGHT IT.
				</p>
			</div>
			<div className="row">
				<ul className="answers">
					<DrillAnswer>knows</DrillAnswer>
					<DrillAnswer>had known</DrillAnswer>
					<DrillAnswer>knew</DrillAnswer>
				</ul>
			</div>
			<div className="row">
				<div className="controls">
					<button type="button" className="btn btn-success">Check</button>
					<button type="button" className="btn btn-primary">Next</button>
				</div>
			</div>
		</div>;
	}

	handleSubmit() {
		this.props.onSubmit(this.props);
	}

}
