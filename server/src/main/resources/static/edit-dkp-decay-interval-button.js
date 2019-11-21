class EditDecayDkpIntervalButton {

	constructor() {
		this.button = document.querySelector('.js-edit-dkp-decay-interval-btn');
		this.bindEvents();
	}

	bindEvents() {
		this.button.addEventListener('click', this.onClick.bind(this));
	}
	
	onClick() {
		console.log('Edit button clicked!');
	}

}

new EditDecayDkpIntervalButton();