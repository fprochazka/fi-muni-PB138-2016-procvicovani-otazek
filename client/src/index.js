import 'babel-polyfill'
import React from 'react'
import {render} from 'react-dom'
import {browserHistory} from 'react-router'
import {syncHistoryWithStore} from 'react-router-redux'
import configureStore from './store/configureStore'

import './style/index.scss'

import {Provider} from 'react-redux'
import {Router} from 'react-router'
import routes from './routes.js'

const store = configureStore();
const history = syncHistoryWithStore(browserHistory, store);

render(
	<Provider store={store}>
		<Router history={history} routes={routes}/>
	</Provider>,
	document.getElementById('root')
);
