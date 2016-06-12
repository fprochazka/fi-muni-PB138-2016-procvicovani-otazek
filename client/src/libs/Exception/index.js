export const LEVEL_API = 'API';
export const LEVEL_JS = 'JS';
export const LEVEL_EXCEPTION = 'EXCEPTION';

export default class Exception extends Error {
	constructor(code, message, level = LEVEL_EXCEPTION, prevError = new Error()) {
		super(message);

		if (level.push === undefined) {
			level = [level];
		}

		this.name = 'Exception';
		this.message = message;
		this.code = code;
		this.stack = prevError.stack;
		this.level = level;
	}
}
