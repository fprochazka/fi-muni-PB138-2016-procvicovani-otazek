import React, {Component, PropTypes} from 'react';

export default class DrillAnswer extends Component {

	static propTypes = {
		children: PropTypes.node.isRequired,
	};

	static defaultProps = {};

	componentDidMount() {
	}

	componentWillUnmount() {

	}

	componentWillReceiveProps(nextProps) {
	}

	render() {
		return <li>
			<label>
				<input type="checkbox"/>
				<i className="icon-checkbox"/>
				<span className="text">{this.props.children}</span>
			</label>
		</li>;
	}

}
