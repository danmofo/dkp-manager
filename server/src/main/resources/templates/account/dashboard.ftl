<#import "../template/base.ftl" as layout />
<#import "/spring.ftl" as spring />

<@layout.general>
    <h1>Dashboard</h1>
	<p>Welcome back, ${session.player.name}</p>
	<p>${session.player.isGuildMaster?c}</p>

    <#if message??>
        <div class="alert alert-success">
            ${message}
        </div>
    </#if>

</@layout.general>