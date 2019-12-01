export default class Pagination {

	constructor(options) {
		this.root = document.querySelector('.js-pagination');
		
		if(!this.root) {
			console.warn('No pagination elements found on the page.');
			return;
		}

		this.options = options || {
			onClickPage: page => {}
		};

		this.currentPage = this.root.getAttribute('data-current-page');

		this.bindEvents();

		console.log('Pagination initialised, it is on page: ' + this.currentPage, this.options);
	}

	bindEvents() {
		console.log('Binding pagination events.');
		document.addEventListener('click', this.onClickPagination.bind(this));
	}

	onClickPagination(event) {
		if(event.target.matches('.js-pagination-page')) {
			const newPageNum = parseInt(event.target.getAttribute("data-page"));
			this.currentPage = newPageNum;
			console.log('Clicked page: ' + newPageNum);
			this.onClickPage(newPageNum);
			return;
		}
	}

	onClickPage(pageNum) {
		this.options.onClickPage(pageNum);
	}

}