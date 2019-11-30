export default class ValidationErrorHandler {

	handleErrors(validationResp) {
		console.log('Handling errors.')
		this.clearExistingErrors();

		if(!validationResp || !validationResp.hasErrors) {
			console.log('No errors found, not doing anything.');
			return;
		}

		validationResp.errors.forEach(error => {
			console.log('Selector: ' + '.error_' + error.fieldName);
			const errorElements = document.querySelectorAll('.error_' + error.fieldName);

			errorElements.forEach(errorEl => {
				errorEl.innerText = error.message;
				errorEl.style.display = 'block';
			});
		});
	}
	
	clearExistingErrors() {
		document.querySelectorAll('.error').forEach(el => {
			el.style.display = 'none';
		});
	}

}