<#import "template/base.ftl" as layout />
<@layout.general>
	<h1>${guild.name}</h1>
	<p>There are <strong>${guild.players?size}</strong> players in this guild.</p>
	<ul>
		<#list guild.players as player>
			<li><a href="/players/${player.id}">${player.name}</a> - ${player.dkp} DKP</li>
		</#list>
	</ul>
</@layout.general>