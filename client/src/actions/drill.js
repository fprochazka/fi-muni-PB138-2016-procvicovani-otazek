import {
	getDrills,
	getDrillQuestions,
} from '../api/drill.js';

export const DRILLS_GET_REQUEST = 'DRILLS_GET_REQUEST';
export const DRILLS_GET_RECEIVE = 'DRILLS_GET_RECEIVE';
export const DRILLS_GET_FAIL = 'DRILLS_GET_FAIL';

function requestGetDrills() {
	return {
		type: DRILLS_GET_REQUEST,
	};
}

function receiveGetDrills(json) {
	var drills = json.drills.map(drill => {
		return {
			...drill,
			questionsLoaded: false,
		}
	});

	var drillsByCode = [];
	drills.forEach(drill => {
		drillsByCode[drill.code] = drill;
	});

	return {
		type: DRILLS_GET_RECEIVE,
		drills: drills,
		drillsByCode: drillsByCode,
	};
}

function failGetDrills(data) {
	return {
		type: DRILLS_GET_FAIL,
		data,
	};
}

export function fetchDrills() {
	return (dispatch, getState) => {
		const {drill, authentication} = getState();
		if (drill.drillsLoaded) {
			return Promise.resolve(drill.drillsByCode);
		}

		dispatch(requestGetDrills());

		return getDrills(authentication.accessToken)
			.then(response => response.json())
			.then(json => dispatch(receiveGetDrills(json)))
			.then(action => action.drillsByCode)
			.catch((data) => {
				dispatch(failGetDrills(data));
				return Promise.reject(data);
			})
	};
}

export const DRILL_QUESTIONS_GET_REQUEST = 'DRILL_QUESTIONS_GET_REQUEST';
export const DRILL_QUESTIONS_GET_RECEIVE = 'DRILL_QUESTIONS_GET_RECEIVE';
export const DRILL_QUESTIONS_GET_FAIL = 'DRILL_QUESTIONS_GET_FAIL';

function requestGetDrillQuestions(drillId) {
	return {
		type: DRILL_QUESTIONS_GET_REQUEST,
		drillId,
	};
}

function receiveGetDrillQuestions(json, drillId) {
	return {
		type: DRILL_QUESTIONS_GET_RECEIVE,
		drillId,
		questions: json.questions,
	};
}

function failGetDrillQuestions(data, drillId) {
	return {
		type: DRILL_QUESTIONS_GET_FAIL,
		drillId,
		data,
	};
}

export function fetchQuestions(drillId) {
	return (dispatch, getState) => {
		const {authentication} = getState();

		dispatch(requestGetDrillQuestions(drillId));

		return getDrillQuestions(drillId, authentication.accessToken)
			.then(response => response.json())
			.then(json => dispatch(receiveGetDrillQuestions(json, drillId)))
			.catch((data) => {
				dispatch(failGetDrillQuestions(data, drillId));
				return Promise.reject(data);
			})
	};
}
