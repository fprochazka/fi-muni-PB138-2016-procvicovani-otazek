import {routerReducer as routing} from 'react-router-redux'
import {combineReducers} from 'redux'
import reduceReducers from '../libs/reduceReducers.js'

import drill from './drill.js';
import exam from './exam.js';
import authentication from './authentication.js';

const namespacedReducers = combineReducers({
	routing,
	drill,
	authentication,
	exam: (state = createExamInitialState(), action) => state,
});

export default reduceReducers(
	namespacedReducers,
	exam,
);

function createExamInitialState() {
	return {
		drill: null,
		examId: null,
		answerStats: [],
		question: null,
		showResult: false,
	};
}
