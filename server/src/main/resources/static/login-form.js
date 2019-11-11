class LoginForm {

	constructor() {
		this.form = document.querySelector('.js-login-form');
		this.errorHandler = new ValidationErrorHandler()
		this.bindEvents();
	}

	bindEvents() {
		this.form.addEventListener('submit', this.onSubmit.bind(this));
	}

	onSubmit(event) {
		const loginData = new FormData(this.form);
		console.log('Form submitted!', loginData);

		fetch('/login', {
			body: loginData,
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

new LoginForm();