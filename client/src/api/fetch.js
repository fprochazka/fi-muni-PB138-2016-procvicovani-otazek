import isomorphicFetch from 'isomorphic-fetch'
import config from '../../config.js';

export default function fetch(endpoint, options) {
	return isomorphicFetch(config.api + endpoint, options);
}
