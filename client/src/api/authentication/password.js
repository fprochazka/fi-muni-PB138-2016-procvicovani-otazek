import {get, post, put} from './../fetch.js'

export function createRegistration(uco, email, password) {
	return post('/authentication/password/register', {
		uco: uco,
		email: email,
		password: password,
	});
}

export function createAccessToken(username, password) {
	return post('/authentication/password/login', {
		username: username,
		password: password,
	});
}
