<#import "template/base.ftl" as layout />
<@layout.general>
	<#assign playerResults=guild.pagedPlayers />

	<h1>${guild.name}</h1>
	<p>There are <strong>${playerResults.numFound}</strong> players in this guild.</p>

	<#if playerResults.numFound == 0>
		<p>No players currently belong to this guild.</p>
	<#else>
		<p>Here are the players in this guild....</p>
		<#include "player-results.ftl" />
	</#if>

</@layout.general>