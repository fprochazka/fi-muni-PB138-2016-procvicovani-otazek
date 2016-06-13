import {
	EXAM_DRILL_TRUNCATE,
	EXAM_DRILL_OPEN,
	EXAM_DRILL_OPENED,
	EXAM_QUESTION_SELECT,
	EXAM_QUESTION_ANSWER_SELECT,
	EXAM_QUESTION_CHECK_RESULT,
} from '../actions/exam.js';

export default function (state, action) {
	switch (action.type) {
		case EXAM_DRILL_TRUNCATE:
			return {
				...state,
				exam: {
					drill: null,
					examId: null,
					answerStats: [],
					question: null,
					showResult: false,
				}
			};
		case EXAM_DRILL_OPEN:
			return {
				...state,
				exam: {
					drill: state.drill.drillsByCode[action.drillCode],
					examId: null,
					answerStats: [],
					question: null,
					showResult: false,
				}
			};
		case EXAM_DRILL_OPENED:
			return {
				...state,
				exam: {
					...state.exam,
					answerStats: completeAnswerStats([], state.exam.drill.questions),
				}
			};
		case EXAM_QUESTION_SELECT:
			return {
				...state,
				exam: {
					...state.exam,
					question: selectQuestion(
						state.exam.drill.questions,
						state.exam.answerStats
					),
					showResult: false,
				}
			};
		case EXAM_QUESTION_ANSWER_SELECT:
			return {
				...state,
				exam: {
					...state.exam,
					question: toggleAnswer(state.exam.question, action.answerOffset),
					showResult: false,
				}
			};
		case EXAM_QUESTION_CHECK_RESULT:
			return {
				...state,
				exam: {
					...state.exam,
					answerStats: updateAnswerStats(
						state.exam.answerStats,
						state.exam.question
					),
					showResult: true,
				}
			};
		default:
			return state;
	}
}

function toggleAnswer(question, toggledOffset) {
	return {
		...question,
		answers: question.answers
			.slice()
			.map((answer, offset) => {
				return {
					...answer,
					selected: offset == toggledOffset ? !answer.selected : answer.selected
				}
			}),
	}
}

function selectQuestion(questions, answerStats) {
	var selected = questions[0];
	questions.every(question => {
		selected = question;
		return question.id != answerStats[0].questionId
	});

	return {
		...selected,
		answers: selected.answers.map(answer => {
			return {
				...answer,
				selected: false,
			}
		})
	};
}

function updateAnswerStats(answersStats, question) {
	return answersStats
		.slice() // copy array
		.map(answerStats => {
			return {
				...answerStats, // copy answerStats
				lastAnsweredBefore: answerStats.lastAnsweredBefore < Number.MAX_SAFE_INTEGER
					? answerStats.lastAnsweredBefore + 1
					: answerStats.lastAnsweredBefore
			};
		})
		.map(answerStats => {
			if (answerStats.questionId == question.id) {
				answerStats.lastAnsweredBefore = 0;

				var correct = question.answers.every(answer => {
					return answer.correct ? answer.selected : !answer.selected;
				});

				if (correct) {
					answerStats.correct += 1;
				} else {
					answerStats.wrong += 1;
				}
			}

			return answerStats;
		})
		.sort(createAnswerStatsComparator());
}

function completeAnswerStats(answerStats, questions) {
	var statsByQuestion = {};

	questions.forEach(question => {
		statsByQuestion[question.id] = {
			questionId: question.id,
			correct: 0,
			wrong: 0,
			lastAnsweredBefore: Number.MAX_SAFE_INTEGER,
		};
	});

	answerStats.forEach(stats => {
		statsByQuestion[stats.questionId] = Object.assign(
			{},
			statsByQuestion[stats.questionId],
			stats
		);
	});

	return Object
		.keys(statsByQuestion)
		.map(key => statsByQuestion[key])
		.sort(createAnswerStatsComparator());
}

function createAnswerStatsComparator() {
	return (a, b) => {
		if (a.lastAnsweredBefore == b.lastAnsweredBefore) {
			var aWeight = a.correct - a.wrong;
			var bWeight = b.correct - b.wrong;
			return aWeight > bWeight ? -1 : (aWeight < bWeight ? -1 : 0);
		}

		return a.lastAnsweredBefore > b.lastAnsweredBefore ? -1 : 1;
	};
}
