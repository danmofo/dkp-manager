<#import "../template/base.ftl" as layout />
<#import "/spring.ftl" as spring />

<@layout.general>
	
    <h1>Edit ${guild.name} information</h1>

    <#if message??>
        <div class="alert alert-success">
            ${message}
        </div>
    </#if>

	<form action="/guild-management/edit" class="js-edit-guild-form">
		
		<input type="hidden" name="currentUri" value="${guild.uri}" />

		<div class="form-group">
		    <@spring.bind "editGuildForm.name" />
		    <label for=" ${spring.status.expression}">Name</label>
		    <input type="text" name="${spring.status.expression}" value="${spring.status.value?default('')}" />
		    <span class="error error_${spring.status.expression}"></span>
		</div>

		<div class="form-group">
            <@spring.bind "editGuildForm.uri" />
            <label for=" ${spring.status.expression}">URI</label>
            <input type="text" name="${spring.status.expression}" value="${spring.status.value?default('')}" />
            <span class="error error_${spring.status.expression}"></span>
            <span class="error error_editGuildForm"></span>
        </div>

        <input type="submit" value="Edit guild" />

	</form>
</@layout.general>