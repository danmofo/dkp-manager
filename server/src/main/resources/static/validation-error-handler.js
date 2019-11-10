class ValidationErrorHandler {

	handleErrors(validationResp) {
		console.log('Handling errors.')
		this.clearExistingErrors();

		if(!validationResp || !validationResp.hasErrors) {
			console.log('No errors found, not doing anything.');
			return;
		}

		validationResp.errors.forEach(error => {
			console.log('Selector: ' + '.error_' + error.fieldName);
			const errorEl = document.querySelector('.error_' + error.fieldName);
			errorEl.innerText = error.message;
			errorEl.style.display = 'block';
		});
	}

	
	clearExistingErrors() {
		document.querySelectorAll('.error').forEach(el => {
			el.style.display = 'none';
		});
	}

}

window.ValidationErrorHandler = ValidationErrorHandler;