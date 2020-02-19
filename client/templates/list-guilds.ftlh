<#import "template/base.ftlh" as layout />
<@layout.general>
	<div class="container">
		<h1>Guilds</h1>
		<div class="js-guild-list">
			<#include "guild-results.ftlh" />
		</div>
	</div>
</@layout.general>