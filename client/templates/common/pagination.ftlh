<#macro pagination results>

	<#if results.numPages < 2>
		<#return>
	</#if>

	<div class="js-pagination" data-current-page="${results.parameters.page}">
		<p>Page <strong>${results.parameters.page}</strong> of <strong>${results.numPages}</strong></p>

		<#if results.hasPrevPage()>
			<#if results.numPages &gt; 2>
				<button class="js-pagination-page" data-page="1">First page</button>
			</#if>

			<button class="js-pagination-page" data-page="${results.prevPageNum()}">Prev page</button>
		</#if>

		<#if results.hasNextPage()>
			<button class="js-pagination-page" data-page="${results.nextPageNum()}">Next page</button>

			<#if results.numPages &gt; 2>
				<button class="js-pagination-page" data-page="${results.numPages}">Last page</button>
			</#if>
		</#if>
	</div>

</#macro>