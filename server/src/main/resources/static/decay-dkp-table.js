// todo: Make this code more resilient to changes in the DOM structure.
class DecayDkpTable {

	constructor() {
		this.table = document.querySelector('.js-decay-dkp-table');
		this.errorHandler = new ValidationErrorHandler();
		this.bindEvents();
	}

	bindEvents() {
		this.table.addEventListener('click', this.onClickTable.bind(this));
	}

	onClickTable(event) {
		if(event.target.matches('.js-decay-dkp')) {
			this.onClickDecayBtn(event.target);
			return;
		}

		if(event.target.matches('.js-decay-dkp-cancel')) {
			this.onClickCancelDecayBtn(event.target);
		}

		if(event.target.matches('.js-decay-dkp-confirm')) {
			this.onClickConfirmDecayBtn(event.target);
			return;
		}
	}

	onClickDecayBtn(decayBtn) {
		console.log('Clicked decay button.');
		this.showDkpForm(decayBtn);
	}

	showDkpForm(decayDkpBtn) {
		decayDkpBtn.style.display = 'none';
		decayDkpBtn.nextElementSibling.style.display = 'block';
	}

	onClickCancelDecayBtn(cancelBtn) {
		console.log('Cancelling cancel DKP process.');
		this.hideDkpForm(cancelBtn);
	}

	hideDkpForm(cancelBtn) {
		const parentEl = cancelBtn.parentElement;
		parentEl.style.display = 'none';
		parentEl.previousElementSibling.style.display = 'block';
	}

	getDecayDkpRequestData(confirmDkpBtn) {
		const amountInput = confirmDkpBtn.previousElementSibling.previousElementSibling;
		return {
			'amount': parseFloat(amountInput.value || 0),
			'playerId': parseInt(confirmDkpBtn.dataset.playerId)
		};
	}

	onClickConfirmDecayBtn(confirmDkpBtn) {
		console.log('Confirmed decay of DKP.');
		this.errorHandler.clearExistingErrors();
		const data = this.getDecayDkpRequestData(confirmDkpBtn);
		console.log(data);

		this.sendDecayDkpRequest(data)
			.then(json => {
				if(!json.success) {
					const validationResp = json.payload.validation;
					this.errorHandler.handleErrors(validationResp);
					return;
				}

				this.hideDkpForm(confirmDkpBtn.nextElementSibling);
				this.updateDkpValue(confirmDkpBtn, json.payload.newDkpValue);
			});
	}

	sendDecayDkpRequest(data) {
		return fetch('/guild-management/decay-dkp', {
			method: 'POST', 
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(data)
		}).then(resp => {
			return resp.json();
		});
	}

	updateDkpValue(confirmDkpBtn, newDkpValue) {
		const formWrapper = confirmDkpBtn.parentElement;
		const actionsCell = formWrapper.parentElement;
		const dkpCell = actionsCell.previousElementSibling;
		dkpCell.textContent = newDkpValue;
	}

}

new DecayDkpTable();