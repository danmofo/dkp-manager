<#import "template/base.ftlh" as layout />
<@layout.general>
	<h1>${player.name} &lt;${player.guild.name}&gt; </h1>
	<p>Total DKP: <strong>${player.dkp}</strong></p>

	<#if dkpHistoryResults.numFound == 0>
		<p>This player currently doesn't have any DKP history to view.</p>
	<#else>
		<h2>DKP breakdown</h2>

		<div class="js-dkp-history-list" data-player-id="${player.id}">
			<#include "dkp-history-results.ftlh" />
		</div>
	</#if>
</@layout.general>