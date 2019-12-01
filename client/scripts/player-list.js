import Pagination from './pagination'

export default class PlayerList {

	constructor() {
		console.log('Created player list...');
		this.list = document.querySelector('.js-player-list');

		if(!this.list) {
			console.log('No player list found on the page to bind events to.');
			return;
		}

		this.guildId = this.list.getAttribute("data-guild-id");

		this.pagination = new Pagination({
			onClickPage: this.onClickPage.bind(this)
		});
	}

	onClickPage(pageNum) {
		console.log('Guild list: clicked page - ' + pageNum);
		this.showLoading();
		this.fetchPlayerListPage(pageNum).then(html => {
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

	fetchPlayerListPage(pageNum) {
		return fetch(`/players/ajax?page=${pageNum}&guildId=${this.guildId}`).then(resp => {
			return resp.text()
		});
	}

}