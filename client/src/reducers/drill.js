import {
	DRILLS_GET_REQUEST,
	DRILLS_GET_RECEIVE,
	DRILLS_GET_FAIL,
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
			});
		case DRILLS_GET_FAIL:
			return Object.assign({}, state, {
				isFetching: false,
			});
		default:
			return state;
	}
}
