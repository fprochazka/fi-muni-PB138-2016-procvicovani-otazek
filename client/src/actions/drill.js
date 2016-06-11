import {getDrills} from '../api/drill.js';

export const DRILLS_GET_REQUEST = 'DRILLS_GET_REQUEST';
export const DRILLS_GET_RECEIVE = 'DRILLS_GET_RECEIVE';
export const DRILLS_GET_FAIL = 'DRILLS_GET_FAIL';

export function requestGetDrills() {
	return {
		type: DRILLS_GET_REQUEST,
	};
}

export function receiveGetDrills(json) {
	return {
		type: DRILLS_GET_RECEIVE,
		drills: json.drills,
	};
}

export function failGetDrills(data) {
	return {
		type: DRILLS_GET_FAIL,
		data,
	};
}

export function fetchCategories() {
	return (dispatch) => {
		dispatch(requestGetDrills());

		return getDrills()
			.then(response => response.json())
			.then(json => {
				dispatch(receiveGetDrills(json));
			})
			.catch((data) => {
				dispatch(failGetDrills(data));
				return Promise.reject(data);
			})
	};
}
