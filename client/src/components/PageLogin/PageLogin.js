import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux';

import Footer from '../Common/Footer.js';
import Header from '../Common/Header.js';
import LoginForm from './LoginForm.js';

class PageLogin extends Component {

	static propTypes = {};

	static defaultProps = {};

	componentDidMount() {
	}

	componentWillUnmount() {

	}

	componentWillReceiveProps(nextProps) {
	}

	render() {
		return <div className="main">
			<div className="container">
				<Header/>
				<div className="row">
					<div className="col-sm-12">
						<LoginForm/>
					</div>
				</div>
				<Footer/>
			</div>
		</div>;
	}

}

export default connect((state, ownProps) => {
	return {};
}, {

})(PageLogin);
