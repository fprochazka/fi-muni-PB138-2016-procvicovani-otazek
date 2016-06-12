export const EXAM_DRILL_OPEN = 'EXAM_DRILL_OPEN';
export const EXAM_DRILL_OPENED = 'EXAM_DRILL_OPENED';

function openExamDrill(drillCode) {
	return {
		type: EXAM_DRILL_OPEN,
		drillCode,
	};
}

export function openDrill(drillCode) {
	return (dispatch, getState) => {
		dispatch(openExamDrill(drillCode));

		const {exam} = getState();
		return new Promise((resolve) => resolve(exam.drill));
	};
}

function openedExamDrill() {
	return {
		type: EXAM_DRILL_OPENED,
	};
}

export function openedDrill() {
	return (dispatch) => {
		dispatch(openedExamDrill());
		return new Promise((resolve) => resolve());
	};
}

export const EXAM_DRILL_TRUNCATE = 'EXAM_DRILL_TRUNCATE';

function truncateExamDrill() {
	return {
		type: EXAM_DRILL_TRUNCATE,
	};
}

export function truncateDrill() {
	return (dispatch) => {
		dispatch(truncateExamDrill());
		return new Promise((resolve) => resolve());
	};
}

export const EXAM_QUESTION_SELECT = 'EXAM_QUESTION_SELECT';

function selectExamQuestion() {
	return {
		type: EXAM_QUESTION_SELECT,
	};
}

export function selectQuestion() {
	return (dispatch) => {
		dispatch(selectExamQuestion());
		return new Promise((resolve) => resolve());
	};
}

export const EXAM_QUESTION_ANSWER_SELECT = 'EXAM_QUESTION_ANSWER_SELECT';

function selectExamQuestionAnswer(answerOffset) {
	return {
		type: EXAM_QUESTION_ANSWER_SELECT,
		answerOffset,
	};
}

export function selectQuestionAnswer(answerOffset) {
	return (dispatch) => {
		dispatch(selectExamQuestionAnswer(answerOffset));
		return new Promise((resolve) => resolve());
	};
}

export const EXAM_QUESTION_CHECK_RESULT = 'EXAM_CHECK_RESULT';

function checkResultExamQuestion() {
	return {
		type: EXAM_QUESTION_CHECK_RESULT,
	};
}

export function checkResultOfQuestion() {
	return (dispatch) => {
		dispatch(checkResultExamQuestion());
		return new Promise((resolve) => resolve());
	};
}
