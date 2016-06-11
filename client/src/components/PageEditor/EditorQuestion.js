import React, {Component, PropTypes} from 'react';
import {Link} from 'react-router';

import Answer from './EditorQuestionAnswer.js';

class EditorQuestion extends Component {

	static propTypes = {
		text: PropTypes.string,
		answers: PropTypes.array,
		active: PropTypes.bool.isRequired,
		offset: PropTypes.number.isRequired,
	};

	static defaultProps = {
		active: false,
		offset: 0,
	};

	render() {
		const {text, answers, active, offset} = this.props;

		return <div className="panel panel-default">
			<div className="panel-heading" role="tab" id={'heading-' + offset}>
				<h4 className="panel-title">
					<a data-toggle="collapse" data-parent="#questions" href={'#collapse-' + offset} aria-expanded="true" aria-controls={'collapse-' + offset}>
						{text}
					</a>
				</h4>
			</div>
			<div id={'collapse-' + offset} className={'panel-collapse collapse' + (active ? ' in' : '')} role="tabpanel" aria-labelledby={'heading-' + offset}>
				<form>
					<div className="question-actions">
						<a href="javascript:;" className="btn btn-danger-outline">Smazat otázku</a>
					</div>

					<fieldset className="form-group question">
						<label htmlFor="question-1-text">Otázka</label>
						<textarea className="form-control" id="question-1-text" rows="3" value={text}/>
					</fieldset>

					<div className="answers">
						{answers.map(this.renderAnswerItem)}

						<div className="row">
							<div className="col-sm-offset-11 col-sm-1 add-answer">
								<a href="javascript:;">
									<i className="fa fa-plus fa-2x" aria-hidden="true"/>
								</a>
							</div>
						</div>
					</div>

				</form>
			</div>
		</div>;
	}

	renderAnswerItem(answerProps, i) {
		return <Answer {...answerProps} key={i}/>
	}

}

export default EditorQuestion;
