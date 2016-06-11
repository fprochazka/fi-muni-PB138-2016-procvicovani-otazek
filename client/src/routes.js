import React from 'react'
import {Route, IndexRoute, Redirect} from 'react-router'
import App from './components/App.js'

import PageHome from './components/PageHome/PageHome.js'
import PageLogin from './components/PageLogin/PageLogin.js'
import PageRegister from './components/PageRegister/PageRegister.js'
import PageDrill from './components/PageDrill/PageDrill.js'
import PageEditor from './components/PageEditor/PageEditor.js'

export default (
	<Route path="/" component={App}>
		<IndexRoute component={PageHome}/>
		<Route path='registrace' components={PageRegister}/>
		<Route path='prihlaseni' components={PageLogin}/>
		<Route path='odpovednik/:code' components={PageDrill}/>
		<Route path='editor/:code' components={PageEditor}/>
	</Route>
)
