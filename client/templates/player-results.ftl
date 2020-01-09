<#import "common/pagination.ftl" as pagination />
<div class="js-player-list" data-guild-id="${playerResults.parameters.guildId}">
	<ul>
		<#list playerResults.items as player>
			<li><a href="/players/${player.id}">${player.name}</a> - ${player.dkp} DKP</li>
		</#list>
	</ul>
	<@pagination.pagination results=playerResults />
</div>