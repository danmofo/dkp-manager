export default class EditDecayDkpIntervalButton {

	constructor() {
		this.button = document.querySelector('.js-edit-dkp-decay-interval-btn');

		if(!this.button) {
			return;
		}

		this.bindEvents();
	}

	bindEvents() { 
		this.button.addEventListener('click', this.onClick.bind(this));
	}
	
	onClick() {
		console.log('Edit button clicked!');
	}

}