<#import "template/base.ftl" as layout />
<@layout.general>
	<h1>Guilds</h1>
	<ul>
		<#list guilds as guild>
			<li><a href="/guilds/${guild.uri}">${guild.name}</a></li>
		</#list>
	</ul>|
</@layout.general>