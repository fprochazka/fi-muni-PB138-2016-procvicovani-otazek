import {get, post, put} from './fetch.js'

export function getExamsList(userId, accessToken) {
	return get('/user/' + encodeURIComponent(userId) + '/exam', {
		accessToken,
	});
}

export function createExam(userId, drillId, accessToken) {
	return post('/user/' + encodeURIComponent(userId) + '/exam', {
		drillId: drillId,
	}, {
		accessToken,
	});
}

export function getExam(userId, examId, accessToken) {
	return get('/user/' + encodeURIComponent(userId) + '/exam/' + encodeURIComponent(examId), {
		accessToken,
	})
}

export function saveAnswers(userId, examId, answers, accessToken) {
	return post('/user/' + encodeURIComponent(userId) + '/exam/' + encodeURIComponent(examId), {
		answers: answers,
	}, {
		accessToken,
	})
}
