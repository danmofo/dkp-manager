class DeleteDecayDkpIntervalButton {

	constructor() {
		this.button = document.querySelector('.js-delete-dkp-decay-interval-btn');
		this.bindEvents();
	}

	bindEvents() {
		this.button.addEventListener('click', this.onClick.bind(this));
	}

	onClick(event) {
		console.log('Button clicked!');

		fetch('/guild-management/delete-dkp-decay-interval', {
			method: 'POST'
		}).then(resp => {
			return resp.json()
		}).then(json => {
			if(json.success) {
				window.location.reload();
			}
		});

	}

}

new DeleteDecayDkpIntervalButton();