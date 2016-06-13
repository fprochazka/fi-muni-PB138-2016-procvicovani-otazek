import React from 'react'
import {Route, IndexRoute, Redirect} from 'react-router'
import App from './components/App.js'

import PageHome from './components/PageHome/PageHome.js'
import PageLogin from './components/PageLogin/PageLogin.js'
import PageRegister from './components/PageRegister/PageRegister.js'
import PageExam from './components/PageExam/PageExam.js'
import PageEditor from './components/PageEditor/PageEditor.js'

export default (
	<Route path="/" component={App}>
		<IndexRoute component={PageHome}/>
		<Route path='registrace' components={PageRegister}/>
		<Route path='prihlaseni' components={PageLogin}/>
		<Route path='odpovednik/:drillCode' components={PageExam}/>
		<Route path='editor/:drillCode' components={PageEditor}/>
	</Route>
)
