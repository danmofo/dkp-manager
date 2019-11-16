<#import "template/base.ftl" as layout />
<@layout.general>
	<h1>${player.name} &lt;${player.guild.name}&gt; </h1>
	<p>Total DKP: <strong>${player.dkp}</strong></p>

	<h2>DKP breakdown</h2>
	<table>
		<tr>
			<th>Date received</th>
			<th>Amount</th>
		</tr>
		<#list player.dkpHistory as historyItem>
			<tr>
				<td>${historyItem.created}</td>
				<td>${historyItem.dkp}</td>
			</tr>
		</#list>
	</table>
</@layout.general>