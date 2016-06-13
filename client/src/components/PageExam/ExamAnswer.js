import React, {Component, PropTypes} from 'react';

export default class ExamAnswer extends Component {

	static propTypes = {
		children: PropTypes.node,
		selected: PropTypes.bool.isRequired,
		correct: PropTypes.bool.isRequired,
		onToggle: PropTypes.func.isRequired,
	};

	static defaultProps = {
		selected: false,
		correct: false,
	};

	render() {
		const {children, selected, correct, onToggle} = this.props;

		return <li className={[selected ? 'selected' : '', correct ? 'correct' : ''].join(' ')}>
			<label>
				<input type="checkbox" onChange={onToggle} checked={selected}/>
				<i className="icon-checkbox"/>
				<span className="text">{children}</span>
			</label>
		</li>;
	}

}
