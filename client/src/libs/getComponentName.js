export default function getComponentName(component) {
	switch (true) {
		case component.displayName !== undefined && component.displayName !== null:
			return component.displayName;
		case component.name !== undefined && component.name !== null:
			return component.name;
		default:
			return 'Component';
	}
}
