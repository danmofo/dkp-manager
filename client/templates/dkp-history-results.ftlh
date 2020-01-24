<#import "common/pagination.ftlh" as pagination />

<table>
	<tr>
		<th>Date received</th>
		<th>Amount</th>
		<th>Reason</th>
	</tr>
	<#list dkpHistoryResults.items as historyItem>
		<tr>
			<td>${historyItem.created}</td>
			<td>${historyItem.dkp}</td>
			<td>${historyItem.reason}</td>
		</tr>
	</#list>
</table>
<@pagination.pagination results=dkpHistoryResults />