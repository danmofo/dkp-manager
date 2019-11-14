<#import "../template/base.ftl" as layout />
<#import "/spring.ftl" as spring />

<@layout.general>
    <h1>Dashboard</h1>

    <#if message??>
        <div class="alert alert-success">
            ${message}
        </div>
    </#if>

    <p>Only logged in users see this.</p>
</@layout.general>