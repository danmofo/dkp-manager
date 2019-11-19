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
		const tableRow = getParentTableRowElement(event.target);

		if(event.target.matches('.js-decay-dkp')) {
			this.onClickDecayBtn(tableRow);
			return;
		}

		if(event.target.matches('.js-decay-dkp-cancel')) {
			this.onClickCancelDecayBtn(tableRow);
		}

		if(event.target.matches('.js-decay-dkp-confirm')) {
			this.onClickConfirmDecayBtn(tableRow);
			return;
		}
	}

	onClickDecayBtn(tableRow) {
		console.log('Clicked decay button.');
		this.showDkpForm(tableRow);
	}

	showDkpForm(tableRow) {
		const decayBtn = tableRow.querySelector('.js-decay-dkp');
		const dkpForm = tableRow.querySelector('.js-decay-dkp-form');
		decayBtn.style.display = 'none';
		dkpForm.style.display = 'block';
	}

	onClickCancelDecayBtn(tableRow) {
		console.log('Cancelling cancel DKP process.');
		this.hideDkpForm(tableRow);
		this.errorHandler.clearExistingErrors();
	}

	hideDkpForm(tableRow) {
		const decayBtn = tableRow.querySelector('.js-decay-dkp');
		const dkpForm = tableRow.querySelector('.js-decay-dkp-form');
		dkpForm.style.display = 'none';
		decayBtn.style.display = 'block';
	}

	getDecayDkpRequestData(tableRow) {
		const amountInput = tableRow.querySelector('.js-decay-dkp-amount');
		const confirmDkpBtn = tableRow.querySelector('.js-decay-dkp-confirm');
		return {
			'amount': parseFloat(amountInput.value || 0),
			'playerId': parseInt(confirmDkpBtn.dataset.playerId)
		};
	}

	onClickConfirmDecayBtn(tableRow) {
		console.log('Confirmed decay of DKP.');
		this.errorHandler.clearExistingErrors();
		const data = this.getDecayDkpRequestData(tableRow);
		console.log(data);

		this.sendDecayDkpRequest(data)
			.then(json => {
				if(!json.success) {
					const validationResp = json.payload.validation;
					this.errorHandler.handleErrors(validationResp);
					return;
				}

				this.hideDkpForm(tableRow);
				this.updateDkpValue(tableRow, json.payload.newDkpValue);
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

	updateDkpValue(tableRow, newDkpValue) {
		const dkpCell = tableRow.querySelector('.js-decay-dkp-amount-cell');
		dkpCell.textContent = newDkpValue;
	}

}

function getParentTableRowElement(el) {
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

function isTableRow(el) {
	return el.tagName.toLowerCase() === 'tr';
}

new DecayDkpTable();