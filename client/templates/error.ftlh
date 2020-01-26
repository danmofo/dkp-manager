<#import "template/base.ftlh" as layout />
<@layout.general>
	<div class="container">

		<#if environment == "development">
			<h1>${error}</h1>
			<p>Something went wrong...here's some more information:</p>
			<ul>
				<li><strong>Type</strong>, ${error}</li>
				<li><strong>Message</strong>, ${message}</li>
				<li><strong>Timestamp</strong>, ${timestamp?datetime}</li>
				<li><strong>Status code</strong>, ${status}</li>
			</ul>
			<pre>${trace}</pre>
		<#else>
			<h1>An error occurred.</h1>
			<p>Something went wrong when trying to load this page. Our developers have been informed and are looking at the problem. Please try refreshing the page in 5 minutes.</p>
		</#if>

	</div>
</@layout.general>