<#import "template/base.ftlh" as layout />
<#import "/spring.ftl" as spring />

<@layout.general>
    <h1>Sign up</h1>

    <#if inviteCode?has_content>
        <h2>You have been invited to join the guild ${guild.name}</h2>
    </#if>

    <form action="/signup" method="POST" class="js-signup-form">

        <h2>Information about your WoW character</h2>
        <div class="form-group">
            <@spring.bind "signupForm.characterName" />
            <label for=" ${spring.status.expression}">Character name</label>
            <input type="text" name="${spring.status.expression}" value="${spring.status.value?default('')}" />
            <span class="error error_${spring.status.expression}"></span>
        </div>

        <div class="form-group">
            <@spring.bind "signupForm.guildId" />
            <label for=" ${spring.status.expression}">Guild name</label>
            <#if inviteCode?has_content>
                <p>${guild.name}</p>
                <input type="hidden" name="${spring.status.expression}" value="${guild.id}" />
            <#else>
                <select name="${spring.status.expression}" id="${spring.status.expression}">
                    <#list guilds as guild>
                        <option value="${guild.id}">${guild.name}</option>
                    </#list>
                </select>
                <span class="error error_${spring.status.expression}"></span>
            </#if>
        </div>

        <div class="form-group">
            <@spring.bind "signupForm.wowClassId" />
            <label for="${spring.status.expression}">Class</label>
            <select name="${spring.status.expression}" id="${spring.status.expression}">
                <#list classes as class>
                    <option value="${class.id}">${class.id}</option>
                </#list>
            </select>
            <span class="error error_${spring.status.expression}"></span>
        </div>

        <h2>Information about you</h2>
        <div class="form-group">
            <@spring.bind "signupForm.email" />
            <label for=" ${spring.status.expression}">Email address</label>
            <input type="email" name="${spring.status.expression}" value="${spring.status.value?default('')}" />
            <span class="error error_${spring.status.expression}"></span>
        </div>

        <div class="form-group">
            <@spring.bind "signupForm.password" />
            <label for=" ${spring.status.expression}">Password</label>
            <input type="password" name="${spring.status.expression}" value="${spring.status.value?default('')}" />
            <span class="error error_${spring.status.expression}"></span>
        </div>

        <input type="submit" value="Create account" />

    </form>
</@layout.general>