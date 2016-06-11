import React, {Component, PropTypes} from 'react'
import {connect} from 'react-redux'

import DrillQuestion from './DrillQuestion.js'
import DrillFooter from './DrillFooter.js'

class PageDrill extends Component {

	static propTypes = {};

	static defaultProps = {};

	constructor(props) {
		super(props);
	}

	render() {
		return <div className="drill">
			<DrillQuestion/>,
			<DrillFooter/>
		</div>
	}
}


export default connect((state, ownProps) => {
	return {};
}, {})(PageDrill);
