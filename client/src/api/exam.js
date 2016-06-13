import fetch from './fetch.js'

export function getExams(userId) {
	return fetch('/user/' + encodeURIComponent(userId) + '/exam');
}

export function createExam(userId, drillId) {
	return fetch('/user/' + encodeURIComponent(userId) + '/exam', {
		method: 'post',
		body: JSON.stringify({
			drillId: drillId
		})
	});
}

export function getExam(userId, examId) {
	return fetch('/user/' + encodeURIComponent(userId) + '/exam/' + encodeURIComponent(examId))
}

export function saveAnswers(userId, examId, answers) {
	return fetch('/user/' + encodeURIComponent(userId) + '/exam/' + encodeURIComponent(examId), {
		method: 'post',
		body: JSON.stringify({
			answers: answers,
		})
	})
}
