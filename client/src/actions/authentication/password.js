import {
	createAccessToken,
	createRegistration,
} from '../../api/authentication/password.js'

export const AUTHENTICATION_PASSWORD_REGISTER_REQUEST = 'AUTHENTICATION_PASSWORD_REGISTER_REQUEST';
export const AUTHENTICATION_PASSWORD_REGISTER_RECEIVE = 'AUTHENTICATION_PASSWORD_REGISTER_RECEIVE';
export const AUTHENTICATION_PASSWORD_REGISTER_FAIL = 'AUTHENTICATION_PASSWORD_REGISTER_FAIL';

function requestPasswordRegister(uco, email, password) {
	return {
		type: AUTHENTICATION_PASSWORD_REGISTER_REQUEST,
		uco,
		email,
		password,
	};
}

function receivePasswordRegistration(json) {
	return {
		type: AUTHENTICATION_PASSWORD_REGISTER_RECEIVE,
		accessToken: json.accessToken,
	};
}

function failPasswordRegistration(data) {
	return {
		type: AUTHENTICATION_PASSWORD_REGISTER_FAIL,
		data,
	};
}

export function passwordRegister(uco, email, password) {
	return (dispatch) => {
		dispatch(requestPasswordRegister(uco, email, password));

		return createRegistration(uco, email, password)
			.then(response => response.json())
			.then(json => dispatch(receivePasswordRegistration(json)))
			.catch((data) => {
				dispatch(failPasswordRegistration(data));
				return Promise.reject(data);
			})
	};
}

export const AUTHENTICATION_PASSWORD_LOGIN_REQUEST = 'AUTHENTICATION_PASSWORD_LOGIN_REQUEST';
export const AUTHENTICATION_PASSWORD_LOGIN_RECEIVE = 'AUTHENTICATION_PASSWORD_LOGIN_RECEIVE';
export const AUTHENTICATION_PASSWORD_LOGIN_FAIL = 'AUTHENTICATION_PASSWORD_LOGIN_FAIL';

function requestPasswordLogin(username, password) {
	return {
		type: AUTHENTICATION_PASSWORD_LOGIN_REQUEST,
		username,
		password,
	};
}

function receivePasswordLogin(json) {
	return {
		type: AUTHENTICATION_PASSWORD_LOGIN_RECEIVE,
		accessToken: json.accessToken,
	};
}

function failPasswordLogin(data) {
	return {
		type: AUTHENTICATION_PASSWORD_LOGIN_FAIL,
		data,
	};
}

export function passwordLogin(username, password) {
	return (dispatch) => {
		dispatch(requestPasswordLogin(username, password));

		return createAccessToken(username, password)
			.then(response => response.json())
			.then(json => dispatch(receivePasswordLogin(json)))
			.catch((data) => {
				dispatch(failPasswordLogin(data));
				return Promise.reject(data);
			})
	};
}

export const AUTHENTICATION_LOGOUT = 'AUTHENTICATION_LOGOUT';

function requestLogout() {
	return {
		type: AUTHENTICATION_LOGOUT,
	};
}

export function logout() {
	return (dispatch) => {
		dispatch(requestLogout());
		return Promise.resolve();
	}
}
