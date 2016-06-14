export const PATTERN = '^[^@]+@[^@]+\\.[^.]+$';

const REGEXP = new RegExp(PATTERN);
const MAX_LENGTH = 254;

/**
 * @param {string} email
 * @return {boolean}
 */
export default function validateEmail(email) {
	return email.length > 0 && email.length <= MAX_LENGTH && REGEXP.test(email);
}
