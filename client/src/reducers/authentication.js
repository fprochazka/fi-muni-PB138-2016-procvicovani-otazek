import {
	AUTHENTICATION_PASSWORD_REGISTER_REQUEST,
	AUTHENTICATION_PASSWORD_REGISTER_RECEIVE,
	AUTHENTICATION_PASSWORD_REGISTER_FAIL,
	AUTHENTICATION_PASSWORD_LOGIN_REQUEST,
	AUTHENTICATION_PASSWORD_LOGIN_RECEIVE,
	AUTHENTICATION_PASSWORD_LOGIN_FAIL,
	AUTHENTICATION_LOGOUT,
} from '../actions/authentication/password.js';

import AccessToken from '../libs/AccessToken.js';

export default function (state = createInitialState(), action = null) {
	switch (action.type) {
		case AUTHENTICATION_PASSWORD_REGISTER_REQUEST:
		case AUTHENTICATION_PASSWORD_LOGIN_REQUEST:
			return Object.assign({}, state, {
				isFetching: true,
			});
		case AUTHENTICATION_PASSWORD_REGISTER_RECEIVE:
		case AUTHENTICATION_PASSWORD_LOGIN_RECEIVE:
			AccessToken.persistToken(action.accessToken);
			return Object.assign({}, state, {
				isFetching: false,
				accessToken: {
					token: action.accessToken.token,
					scope: action.accessToken.scope,
				},
				user: {
					...action.accessToken.user,
				}
			});
		case AUTHENTICATION_PASSWORD_REGISTER_FAIL:
		case AUTHENTICATION_PASSWORD_LOGIN_FAIL:
			return Object.assign({}, state, {
				isFetching: false,
				accessToken: null,
				user: null,
			});
		case AUTHENTICATION_LOGOUT:
			AccessToken.clear();
			return Object.assign({}, state, {
				accessToken: null,
				user: null,
			});
		default:
			return state;
	}
}

function createInitialState() {
	var accessToken = AccessToken.getToken();
	return {
		isFetching: false,
		accessToken: accessToken ? {
			token: accessToken.token,
			scope: accessToken.scope,
		} : null,
		user: accessToken ? accessToken.user : null,
	};
}
