<#import "template/base.ftl" as layout />
<#import "/spring.ftl" as spring />

<@layout.general>
    <h1>Reset your password</h1>
	
	<#if token?has_content>
	
		<#if player??>
			<p>Welcome ${player.name}! Let's help you reset your password...</p>
			<p>todo: New password form goes here....</p>
		<#else>
			<p>Invalid token.</p>
		</#if>

	<#else>
		<p>No token was specified.</p>
	</#if>

</@layout.general>