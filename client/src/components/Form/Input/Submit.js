import React, {Component, PropTypes} from 'react';

export default class Submit extends Component {

	static propTypes = {
		name: PropTypes.string.isRequired,
		children: PropTypes.node.isRequired,
		className: PropTypes.string,
	};

	static contextTypes = {
		form: PropTypes.object,
	};

	static defaultProps = {
		children: 'Odeslat',
		className: 'btn',
	};

	componentDidMount() {
		this.context.form.addSubmit(this);
	}

	render() {
		return (
			<button type='button'
			        onClick={() => this.context.form.submit(this.props.name)}
			        className={this.props.className}
			>{this.props.children ? this.props.children : 'Odeslat'}</button>
		);
	}

}
