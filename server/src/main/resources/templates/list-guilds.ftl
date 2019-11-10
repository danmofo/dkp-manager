<#import "template/base.ftl" as layout />
<@layout.general>
	<h1>Guilds</h1>
	<ul>
		<#list guilds as guild>
			<li>${guild.name}</li>
		</#list>
	</ul>|
</@layout.general>