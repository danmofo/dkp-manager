<#import "template/base.ftl" as layout />
<@layout.general>
	<h1>Guilds</h1>

	<#if guildResults.numFound == 0>
		<p>No guilds found.</p>
	<#else>
		<ul>
			<#list guildResults.items as guild>
				<li><a href="/guilds/${guild.uri}">${guild.name}</a></li>
			</#list>
		</ul>
	</#if>
</@layout.general>