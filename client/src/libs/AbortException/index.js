import Exception from '../Exception';

export const CODE_ABORT = '__abort__';

export default class AbortException extends Exception {

	constructor() {
		super(CODE_ABORT, '');
	}

}
