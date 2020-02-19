<#macro pagination results>

	<#if results.numPages < 2>
		<#return>
	</#if>

	<div class="pagination js-pagination" data-current-page="${results.parameters.page}">
		<#if results.hasPrevPage()>
			<#if results.numPages &gt; 2>
				<button class="btn btn--sm btn--secondary js-pagination-page" data-page="1">First</button>
			</#if>

			<button class="btn btn--sm btn--primary js-pagination-page" data-page="${results.prevPageNum()}"> &laquo; Previous</button>
		</#if>

		<#if results.hasNextPage()>
			<button class="btn btn--sm btn--primary js-pagination-page" data-page="${results.nextPageNum()}">Next &raquo;</button>

			<#if results.numPages &gt; 2>
				<button class="btn btn--sm btn--secondary js-pagination-page" data-page="${results.numPages}">Last</button>
			</#if>
		</#if>

		<span class="pagination__text">Page <strong>${results.parameters.page}</strong> of <strong>${results.numPages}</strong></span>
	</div>

</#macro>