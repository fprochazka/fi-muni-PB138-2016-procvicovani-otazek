import isomorphicFetch from 'isomorphic-fetch'
import config from '../../config.js';

export function fetch(endpoint, options) {
	var headers = new Headers(options.headers || {});
	headers.set('Accept', 'application/json');

	if (typeof options.accessToken != "undefined" && options.accessToken != null) {
		const accessToken = options.accessToken;
		delete options.accessToken;

		headers.set('X-Authorization-Token', 'Bearer ' + accessToken.token);
	}

	options.headers = headers;

	return isomorphicFetch(config.api + endpoint, options)
		.then((response) =>
			new Promise((resolve, reject) => {
				if (response.status >= 200 && response.status < 300) {
					resolve(response);
				} else {
					response.json().then((json) => {
						reject({response, json});
					});
				}
			})
		);
}

export function get(endpoint, options) {
	return fetch(endpoint, Object.assign({}, options, {
		method: 'get',
	}));
}

export function post(endpoint, jsonBody, options) {
	return fetch(endpoint, Object.assign({}, options, {
		method: 'post',
		body: JSON.stringify(jsonBody),
		headers: {
			'Content-Type': 'application/json;charset=UTF-8'
		}
	}));
}

export function put(endpoint, jsonBody, options) {
	return fetch(endpoint, Object.assign({}, options, {
		method: 'put',
		body: JSON.stringify(jsonBody),
		headers: {
			'Content-Type': 'application/json;charset=UTF-8'
		}
	}));
}
