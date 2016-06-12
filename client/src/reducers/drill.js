import {
	DRILLS_GET_REQUEST,
	DRILLS_GET_RECEIVE,
	DRILLS_GET_FAIL,
	DRILL_QUESTIONS_GET_REQUEST,
	DRILL_QUESTIONS_GET_RECEIVE,
	DRILL_QUESTIONS_GET_FAIL,
} from '../actions/drill.js';

export default function (state = {
	drillsLoaded: false,
	isFetching: false,
	drills: [],
}, action = null) {
	switch (action.type) {
		case DRILLS_GET_REQUEST:
			return Object.assign({}, state, {
				isFetching: true,
			});
		case DRILLS_GET_RECEIVE:
			return Object.assign({}, state, {
				drillsLoaded: true,
				isFetching: false,
				drills: action.drills || [],
				drillsByCode: action.drillsByCode || [],
			});
		case DRILLS_GET_FAIL:
			return Object.assign({}, state, {
				isFetching: false,
			});
		case DRILL_QUESTIONS_GET_REQUEST:
			return Object.assign({}, state, {
				isFetching: true,
			});
		case DRILL_QUESTIONS_GET_RECEIVE:
			return Object.assign({}, state, {
				isFetching: false,
				drills: state.drills.map(drill => {
					if (drill.id == action.drillId) {
						drill.questions = action.questions;
						drill.questionsLoaded = true;
					}

					return drill;
				}),
			});
		case DRILL_QUESTIONS_GET_FAIL:
			return Object.assign({}, state, {
				isFetching: false,
			});
		default:
			return state;
	}
}
