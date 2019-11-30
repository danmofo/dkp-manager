<#import "../template/base.ftl" as layout />
<#import "/spring.ftl" as spring />

<@layout.general>
	
    <h1>Award DKP</h1>
    <p>Here you can give DKP to your guild members.</p>

    <#if message??>
        <div class="alert alert-success">
            ${message}
        </div>
    </#if>

    <p>You will award the following players with DKP:</p>
    <#list guild.players as player>
		<li>${player.name} - ${player.dkp}</li>
    </#list>

	<form action="/guild-management/award-dkp" class="js-award-dkp-form">

		<div class="form-group">
		    <@spring.bind "awardDkpForm.amount" />
		    <label for=" ${spring.status.expression}">Amount</label>
		    <input type="number" name="${spring.status.expression}" value="${spring.status.value?default('')}" />
		    <span class="error error_${spring.status.expression}"></span>
		</div>

		<div class="form-group">
		    <@spring.bind "awardDkpForm.reason" />
		    <label for=" ${spring.status.expression}">Reason</label>
		    <p>Explain why you awarded this DKP, or leave it blank.</p>
		    <input type="text" name="${spring.status.expression}" value="${spring.status.value?default('')}" />
		    <span class="error error_${spring.status.expression}"></span>
		</div>

        <input type="submit" value="Award DKP" />

	</form>
</@layout.general>