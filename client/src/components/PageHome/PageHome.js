import React, {Component, PropTypes} from 'react';
import {Link} from 'react-router';
import {connect} from 'react-redux';

import {fetchDrills} from '../../actions/drill.js';

import Footer from '../Common/Footer.js';
import Header from '../Common/Header.js';

class PageHome extends Component {

	static propTypes = {
		isFetching: PropTypes.bool.isRequired,
		drills: PropTypes.arrayOf(PropTypes.shape({
			id: PropTypes.any.isRequired,
			name: PropTypes.string.isRequired,
		})).isRequired,
		fetchDrills: PropTypes.func.isRequired,
		user: PropTypes.shape({
			id: PropTypes.string,
			email: PropTypes.string,
		}),
	};

	static defaultProps = {
		drills: [],
	};

	componentDidMount() {
		const {fetchDrills} = this.props;
		fetchDrills();
	}

	componentWillUnmount() {

	}

	componentWillReceiveProps(nextProps) {
	}

	render() {
		const {drills} = this.props;

		return <div className="main">
			<div className="container">
				<Header/>
				<div className="row drills-overview">
					<div className="col-sm-12 drill-columns">
						{drills.map(this.renderDrillItem)}
					</div>
				</div>
				<Footer/>
			</div>
		</div>;
	}

	renderDrillItem = (drill, i) => {
		return <div className="card card-block" key={i}>
			<Link to={`/odpovednik/${drill.code}`} className="open btn btn-primary">Otevřít</Link>
			{this.props.user
				? <Link to={`/editor/${drill.code}`} className="edit btn btn-warning"><i className="fa fa-pencil" aria-hidden="true"/></Link>
				: ''}
			<h3 className="card-title">{drill.code}</h3>
			<p className="card-text">{drill.name}</p>
		</div>;
	}

}

export default connect((state, ownProps) => {
	return {
		isFetching: state.drill.isFetching,
		drills: state.drill.drills,
		user: state.authentication.user,
	};
}, {
	fetchDrills,
})(PageHome);
