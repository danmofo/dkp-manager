class AjaxForm {

	constructor(options) {
		this.options = options || {};
		this.validateOptions();

		this.form = document.querySelector(this.options.selector);

		if(!this.form) {
			console.log(`No form found on the page with the selector: '${this.options.selector}'`)
			return;
		}

		this.errorHandler = new ValidationErrorHandler();
		this.bindEvents();
	}

	validateOptions() {
		if(!this.options.selector || !this.options.endpoint) {
			throw new Error("Please specify both the 'selector' and 'endpoint' keys in the options object.")
		}
	}

	bindEvents() {
		this.form.addEventListener('submit', this.onSubmit.bind(this));
	}

	onSubmit(event) {
		const formData = new FormData(this.form);
		console.log('Form submitted!', formData);

		fetch(this.options.endpoint, {
			body: formData,
			method: 'POST'
		}).then(resp => {
			return resp.json();
		}).then(json => {
			if(!json.success) {
				const validationResp = json.payload.validation;
				this.errorHandler.handleErrors(validationResp);
				return;
			}

			window.location.href = json.payload.redirectUrl;
		});

		event.preventDefault();
	}

}

window.AjaxForm = AjaxForm