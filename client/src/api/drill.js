import fetch from './fetch.js'

export function getDrills() {
	return fetch('/drill');
}

export function createDrill(name) {
	return fetch('/drill', {
		method: 'post',
		body: JSON.stringify({
			name: name
		})
	});
}

export function getDrill(drillId) {
	return fetch('/drill/' + encodeURIComponent(drillId))
}

export function getDrillQuestions(drillId) {
	return fetch('/drill/' + encodeURIComponent(drillId) + '/question')
}

export function createDrillQuestions(drillId, text, answers) {
	return fetch('/drill/' + encodeURIComponent(drillId) + '/question', {
		method: 'post',
		body: JSON.stringify({
			text: text,
			answers: answers
		})
	})
}

export function getDrillQuestion(drillId, questionId) {
	return fetch('/drill/' + encodeURIComponent(drillId) + '/question/' + encodeURIComponent(questionId));
}


export function updateDrillQuestion(drillId, questionId, text, answers) {
	return fetch('/drill/' + encodeURIComponent(drillId) + '/question/' + encodeURIComponent(questionId), {
		method: 'post',
		body: JSON.stringify({
			text: text,
			answers: answers
		})
	});
}
