<#macro pagination results>

	<#if results.numPages < 2>
		<#return>
	</#if>

	<p>You are currently on page <strong>${results.parameters.page}</strong> of <strong>${results.numPages}</strong></p>

	<#if results.hasPrevPage()>
		<button>Prev page</button>
	</#if>

	<#if results.hasNextPage()>
		<button>Next page</button>
	</#if>

	<p>Next page: <strong>${results.nextPageNum()}</strong> (hasNextPage? ${results.hasNextPage()?c})</p>
	<p>Prev page: <strong>${results.prevPageNum()}</strong> (hasPrevPage? ${results.hasPrevPage()?c})</p>

</#macro>