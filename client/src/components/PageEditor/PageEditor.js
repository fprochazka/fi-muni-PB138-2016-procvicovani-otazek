import React, {Component, PropTypes} from 'react';
import {Link} from 'react-router';
import {connect} from 'react-redux';

import EditorHeader from './EditorHeader.js';
import Question from './EditorQuestion.js';

class PageEditor extends Component {

	static propTypes = {
		drill: PropTypes.shape({
			code: PropTypes.string.isRequired,
			questions: PropTypes.array,
		}),
	};

	static defaultProps = {
		drill: {
			code: "VB036",
			questions: [
				{
					text: "I WISH IT _____ AS SIMPLE AS THAT, JOEY, BUT UNFORTUNATELY IT ISN'T.",
					answers: [
						{isCorrect: false, text: "is"},
						{isCorrect: true, text: "was"},
						{isCorrect: false, text: "would be"},
					],
				},
				{
					text: "A SPECIALIZED LANGUAGE FOR REQUESTING INFORMATION FROM ...",
					answers: [
						{isCorrect: false, text: "an inquiry language"},
						{isCorrect: false, text: "a question language"},
						{isCorrect: true, text: "a query language"},
						{isCorrect: false, text: "a search language"},
					],
					active: true,
				},
				{
					text: "WHICH OF THE FOLLOWING STATEMENTS IS NOT TRUE?",
					answers: [
						{isCorrect: true, text: "A data dictionary contains actual data from the database."},
						{isCorrect: false, text: "A data dictionary holds information about the data it stores."},
						{isCorrect: false, text: "A data dictionary is an integral part of a database."},
						{isCorrect: false, text: "A data dictionary is sometimes called a repository."},
					],
				},
			]
		},
	};

	render() {
		const {drill} = this.props;

		return <div className="main editor">
			<div className="container">
				<EditorHeader drill={drill} />
				<div className="row drills-overview">
					<div className="col-sm-12 drill-columns">
						<div id="questions" role="tablist" aria-multiselectable="true">
							{drill.questions.map(this.renderQuestionItem)}
						</div>
					</div>
				</div>
				<div className="row">
					<div className="col-sm-12 bottom-actions">
						<a href="javascript:;" className="btn btn-success-outline">Přidat otázku</a>
					</div>
				</div>
			</div>
		</div>;
	}

	renderQuestionItem(questionProps, i) {
		return <Question {...questionProps} offset={i+1} key={i}/>
	}

}

export default connect((state, ownProps) => {
	return {};
}, {
})(PageEditor);
