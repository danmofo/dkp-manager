<#import "../template/base.ftl" as layout />
<#import "/spring.ftl" as spring />

<@layout.general>
    <#if message??>
        <div class="alert alert-success">
            ${message}
        </div>
    </#if>

    <h1>Dashboard</h1>
	<p>Welcome back, ${session.player.name}</p>
	
	<#if session.player.isGuildMaster>
		<h2>Guild master controls</h2>
		<p><a href="/guild-management/edit">Edit guild information</a></p>
		<p><a href="/guild-management/award-dkp">Award raid DKP</a></p>
		<p><a href="/guild-management/decay-dkp">Decay raid DKP</a></p>
		<p><a href="/guild-management/edit-dkp">Manually edit player's DKP</a></p>
	</#if>

</@layout.general>