import React, {Component, PropTypes} from 'react';
import {Link} from 'react-router';

class EditorQuestionAnswer extends Component {

	static propTypes = {
		isCorrect: PropTypes.bool.isRequired,
		text: PropTypes.string.isRequired,
	};

	static defaultProps = {};

	render() {
		const {text, isCorrect} = this.props;

		return <div className="row">
			<div className="col-sm-2 is-correct-checkbox">
				<label className="c-input c-checkbox">
					<input type="checkbox" checked={isCorrect}/>
					<span className="c-indicator"/>
					Správně
				</label>
			</div>
			<div className="col-sm-9">
				<textarea className="form-control" placeholder="Text odpovědi" rows="1" value={text} />
			</div>
			<div className="col-sm-1 delete-answer">
				<a href="javascript:;">
					<i className="fa fa-trash-o fa-2x" aria-hidden="true"/>
				</a>
			</div>
		</div>;
	}

}

export default EditorQuestionAnswer;
