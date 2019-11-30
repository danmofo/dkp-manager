export function getParentTableRowElement(el) {
	if(isTableRow(el)) {
		return el;
	}

	let parentEl = el.parentElement;
	while(parentEl !== null) {
		if(isTableRow(parentEl)) {
			return parentEl;
		}
		parentEl = parentEl.parentElement;
	}

	return parentEl;
}

export function isTableRow(el) {
	return el.tagName.toLowerCase() === 'tr';
}