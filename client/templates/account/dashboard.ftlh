<#import "../template/base.ftlh" as layout />
<#import "/spring.ftl" as spring />

<@layout.general>
    <#if message??>
        <div class="alert alert-success">
            ${message}
        </div>
    </#if>

    <h1>Dashboard</h1>
	<p>Welcome back, ${session.player.name}</p>

	<h2>DKP info</h2>
	<p>You currently have ${session.player.dkp} DKP.</p>
	
	<h2>Guild info</h2>
	<p>Name: <strong>${session.player.guild.name}</strong></p>
	<p>Invite other guild members by using this link: <a href="http://localhost:8080/signup/${session.player.guild.inviteCode}">http://localhost:8080/signup/${session.player.guild.inviteCode}</a></p>

	<#if session.player.isGuildMaster>
		<h2>Guild master controls</h2>
		<p><a href="/guild-management/edit">Edit guild information</a></p>
		<p><a href="/guild-management/award-dkp">Award raid DKP</a></p>
		<p><a href="/guild-management/decay-dkp">Decay raid DKP</a></p>
		<p><a href="/guild-management/invite">Invite guild members</a></p>
	</#if>

</@layout.general>