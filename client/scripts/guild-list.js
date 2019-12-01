import Pagination from './pagination'

export default class GuildList {

	constructor() {
		console.log('Created guild list...');
		this.list = document.querySelector('.js-guild-list');

		if(!this.list) {
			console.log('No guild list found on the page to bind events to.');
			return;
		}

		this.pagination = new Pagination({
			onClickPage: this.onClickPage.bind(this)
		});
	}

	onClickPage(pageNum) {
		console.log('Guild list: clicked page - ' + pageNum);
		this.fetchGuildListPage(pageNum).then(html => {
			this.list.innerHTML = html;
		});
	}

	fetchGuildListPage(pageNum) {
		return fetch(`/guilds/ajax?page=${pageNum}`).then(resp => {
			return resp.text()
		});
	}

}