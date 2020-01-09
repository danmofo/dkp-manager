<#import "common/pagination.ftl" as pagination>

<#if guildResults.numFound == 0>
	<p>No guilds found.</p>
<#else>
	<ul>
		<#list guildResults.items as guild>
			<li><a href="/guilds/${guild.uri}">${guild.name}</a></li>
		</#list>
	</ul>

	<@pagination.pagination results=guildResults />
</#if>