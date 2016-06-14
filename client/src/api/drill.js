import {post, get, put} from './fetch.js'

export function getDrills(accessToken = null) {
	return get('/drill', {
		accessToken,
	});
}

export function createDrill(name, accessToken) {
	return post('/drill', {
		name: name
	}, {
		accessToken,
	});
}

export function getDrill(drillId, accessToken = null) {
	return get('/drill/' + encodeURIComponent(drillId), {
		accessToken,
	})
}

export function getDrillQuestions(drillId, accessToken = null) {
	return get('/drill/' + encodeURIComponent(drillId) + '/question', {
		accessToken,
	})
}

export function createDrillQuestions(drillId, text, answers, accessToken) {
	return post('/drill/' + encodeURIComponent(drillId) + '/question', {
		text: text,
		answers: answers
	}, {
		accessToken,
	})
}

export function getDrillQuestion(drillId, questionId, accessToken = null) {
	return get('/drill/' + encodeURIComponent(drillId) + '/question/' + encodeURIComponent(questionId), {
		accessToken,
	});
}


export function updateDrillQuestion(drillId, questionId, text, answers, accessToken) {
	return post('/drill/' + encodeURIComponent(drillId) + '/question/' + encodeURIComponent(questionId), {
		text: text,
		answers: answers
	}, {
		accessToken,
	});
}
