import React, {Component, PropTypes} from 'react';

import ExamAnswer from './ExamAnswer.js'

export default class ExamQuestion extends Component {

	static propTypes = {
		question: PropTypes.shape({
			id: PropTypes.string,
			text: PropTypes.string,
			answers: PropTypes.arrayOf(PropTypes.shape({
				text: PropTypes.string.isRequired,
				selected: PropTypes.bool.isRequired,
				correct: PropTypes.bool.isRequired,
			})),
		}),
		onAnswerToggle: PropTypes.func.isRequired,
		onCheck: PropTypes.func.isRequired,
		onNext: PropTypes.func.isRequired,
	};

	static defaultProps = {
		question: {
			answers: [],
		},
	};

	render() {
		const {question, onCheck, onNext} = this.props;

		return <div className="container-fluid">
			<div className="row">
				<p className="question">
					{question.text}
				</p>
			</div>
			<div className="row">
				<ul className="answers">
					{question.answers.map((answer, i) => {
						const {onAnswerToggle} = this.props;
						return <ExamAnswer {...answer} onToggle={() => onAnswerToggle(i)} key={i}>{answer.text}</ExamAnswer>
					})}
				</ul>
			</div>
			<div className="row">
				<div className="controls">
					<button type="button" className="btn btn-success" onClick={onCheck}>
						Check
					</button>
					<button type="button" className="btn btn-primary" onClick={onNext}>Next</button>
				</div>
			</div>
		</div>;
	}

}
