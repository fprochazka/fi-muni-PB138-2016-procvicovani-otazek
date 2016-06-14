import React, {Component, PropTypes} from 'react'
import {connect} from 'react-redux'

import {fetchDrills, fetchQuestions} from '../../actions/drill.js';
import {
	openDrill,
	openedDrill,
	truncateDrill,
	selectQuestion,
	selectQuestionAnswer,
	checkResultOfQuestion
} from '../../actions/exam.js';

import ExamQuestion from './ExamQuestion.js'
import ExamFooter from './ExamFooter.js'

function loadData(drillCode) {
	return (dispatch) => {
		const openedDrillPromise = (drill) => (dispatch) => new Promise((resolve, reject) => {
			return dispatch(openedDrill(drill.code))
				.then(() => dispatch(selectQuestion()))
				.catch(reject)
		});

		const loadQuestionsPromise = (drill) => (dispatch) => new Promise((resolve, reject) => {
			if (drill.questionsLoaded) {
				return dispatch(openedDrillPromise(drill))
					.catch(reject);
			}

			return dispatch(fetchQuestions(drill.id))
				.then(() => dispatch(openedDrillPromise(drill)))
				.catch(reject)
		});

		const openDrillPromise = (drillCode) => (dispatch) => new Promise((resolve, reject) => {
			return dispatch(openDrill(drillCode))
				.then(drill => dispatch(loadQuestionsPromise(drill)))
				.catch(reject);
		});

		const loadDrillsPromise = (drillCode) => (dispatch) => new Promise((resolve, reject) => {
			return dispatch(fetchDrills())
				.then(drillsByCode => {
					if (typeof drillsByCode[drillCode] == "undefined") {
						Promise.reject("Drill with code " + drillCode + " not found");
					}
					return drillsByCode[drillCode];
				})
				.then(() => dispatch(openDrillPromise(drillCode)))
				.catch(reject);
		});

		return dispatch(truncateDrill())
			.then(() => dispatch(loadDrillsPromise(drillCode)))
			.catch((error) => console.log(error));
	}
}

class PageExam extends Component {

	static propTypes = {
		loadData: PropTypes.func.isRequired,
		drillCode: PropTypes.string.isRequired,
		drill: PropTypes.shape({
			id: PropTypes.any.isRequired,
			code: PropTypes.string.isRequired,
			name: PropTypes.string.isRequired,
			questionsLoaded: PropTypes.bool.isRequired,
		}),
		question: PropTypes.shape({
			id: PropTypes.string.isRequired,
			text: PropTypes.string.isRequired,
			answers: PropTypes.array,
		}),
		showResult: PropTypes.bool.isRequired,
		selectQuestion: PropTypes.func.isRequired,
		selectQuestionAnswer: PropTypes.func.isRequired,
		checkResultOfQuestion: PropTypes.func.isRequired,
	};

	static defaultProps = {
		showResult: false,
	};

	componentDidMount() {
		const {loadData, drillCode} = this.props;
		return loadData(drillCode);
	}

	render() {
		const {drill, question, showResult, selectQuestionAnswer, checkResultOfQuestion} = this.props;

		return <div className={['drill', showResult ? 'show-result' : ''].join(' ')}>
			{question
				? <ExamQuestion question={question}
				                onAnswerToggle={(i) => selectQuestionAnswer(i)}
								onCheck={() => checkResultOfQuestion()}
								onNext={this.handleOpenNext} />
				: ''}
			<ExamFooter drill={drill}/>
		</div>
	}

	handleOpenNext = () => {
		const {showResult, selectQuestion, checkResultOfQuestion} = this.props;

		if (showResult) {
			selectQuestion();
		} else {
			checkResultOfQuestion();
		}
	}
}

export default connect((state, ownProps) => {
	return {
		drillCode: ownProps.routeParams.drillCode,
		drill: state.exam.drill,
		question: state.exam.question,
		showResult: state.exam.showResult,
	};
}, {
	loadData,
	selectQuestion,
	selectQuestionAnswer,
	checkResultOfQuestion,
})(PageExam);
