import Pagination from './pagination'

// todo: we should DRY this up, it's pretty much a repeat of the other pages which have pagination on
export default class DkpHistoryList {

	constructor() {
		console.log('Created DKP history list...');
		this.list = document.querySelector('.js-dkp-history-list');

		if(!this.list) {
			console.log('No DKP history list found on the page to bind events to.');
			return;
		}

		this.playerId = this.list.getAttribute("data-player-id");

		this.pagination = new Pagination({
			onClickPage: this.onClickPage.bind(this)
		});
	}

	onClickPage(pageNum) {
		console.log('DKP history list: clicked page - ' + pageNum);
		this.showLoading();
		this.fetchDkpHistoryPage(pageNum).then(html => {
			this.hideLoading();
			this.list.innerHTML = html;
		});
	}

	showLoading() {
		this.list.innerHTML = 'Loading...'; // TODO: Use a spinner and move this into the template.
	}

	hideLoading() {
		this.list.innerHTML = '';
	}

	fetchDkpHistoryPage(pageNum) {
		return fetch(`/dkp-history/ajax?page=${pageNum}&playerId=${this.playerId}`).then(resp => {
			return resp.text();
		});
	}

}