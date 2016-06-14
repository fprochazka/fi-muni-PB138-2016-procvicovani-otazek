import Cookies from 'cookies-js';

const END_OF_EPOCH = 'Tue, 19 Jan 2038 03:14:07 UTC';

const COOKIE_TOKEN = 'AccessToken';

export default {

	persistToken(accessToken, user) {
		Cookies.set(COOKIE_TOKEN, JSON.stringify(accessToken), {
			expires: new Date(END_OF_EPOCH),
		});
	},

	clear() {
		Cookies.expire(COOKIE_TOKEN);
	},

	getToken() {
		const accessToken = Cookies.get(COOKIE_TOKEN);
		try {
			if (typeof accessToken === 'undefined') {
				return null;
			}
			return JSON.parse(accessToken);
		} catch (e) {
			return null;
		}
	},

}
