import {routerReducer as routing} from 'react-router-redux'
import {combineReducers} from 'redux'
import reduceReducers from '../libs/reduceReducers.js'

import drill from './drill.js';
import exam from './exam.js';

const namespacedReducers = combineReducers({
	routing,
	drill,
	exam: function (state = {
		drill: null,
		examId: null,
		answerStats: [],
		question: null,
		showResult: false,
	}, action) {
		return state;
	}
});

export default reduceReducers(
	namespacedReducers,
	exam,
);
