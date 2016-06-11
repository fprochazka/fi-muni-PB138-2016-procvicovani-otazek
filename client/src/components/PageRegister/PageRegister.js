import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux';

import Footer from '../Common/Footer.js';
import Header from '../Common/Header.js';
import RegistrationForm from './RegistrationForm.js';

class PageRegister extends Component {

	static propTypes = {
	};

	static defaultProps = {};

	constructor(props) {
		super(props);
	}

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
						<RegistrationForm/>
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

})(PageRegister);
