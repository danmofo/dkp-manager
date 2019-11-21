<#import "../template/base.ftl" as layout />
<#import "/spring.ftl" as spring />

<@layout.general>
	
    <h1>Decay DKP</h1>
    <p>Here you can decay DKP.</p>

	<div>
		<h2>Manual decay</h2>
		<p>Here you can decay the DKP by a fixed amount at any point in time.</p>

		<table class="js-decay-dkp-table">
			<tr>
				<th>Name</th>
				<th>Current DKP</th>
				<th>Actions</th>
			</tr>
			<#list guild.players as player>
				<tr>
					<td>${player.name}</td>
					<td class="js-decay-dkp-amount-cell">${player.dkp}</td>
					<td>
						<button class="js-decay-dkp">Decay</button>
						<div style="display:none;" class="js-decay-dkp-form">
							<input type="number" class="js-decay-dkp-amount" placeholder="Enter amount" />
							<span class="error error_amount"></span>
							<button class="js-decay-dkp-confirm" data-player-id="${player.id}">Confirm</button>
							<button class="js-decay-dkp-cancel">Cancel</button>
						</div>
					</td>
				</tr>
			</#list>
		</table>
	</div>
	
	<div>
		<h2>Interval decay</h2>
		<p>Here you can decay DKP at fixed intervals of time.</p>


		<#if guild.dkpDecayInterval??>
			<p>Your existing interval information:</p>
			<ul>
				<li><strong>Decay amount: </strong> ${guild.dkpDecayInterval.dkp}</li>
				<li><strong>Next decay occurs: </strong> ${guild.dkpDecayInterval.nextOccurrence}</li>
			</ul>
			<button class="js-delete-decay-dkp-interval-btn">Delete</button>
			<p>To edit this, delete it and create a new one.</p>
		<#else>
			<form action="/guild-management/add-decay-dkp-interval" class="js-add-decay-dkp-interval-form">
				
				<div class="form-group">
				    <@spring.bind "addDkpDecayIntervalForm.unitName" />
				    <label for=" ${spring.status.expression}">Unit name</label>
				    <select name="${spring.status.expression}" id="${spring.status.expression}">
			            <#list dkpDecayIntervalUnitNames as unitName>
			                <option value="${unitName}">${unitName}</option>
			            </#list>
			        </select>
				    <span class="error error_${spring.status.expression}"></span>
				</div>

				<div class="form-group">
				    <@spring.bind "addDkpDecayIntervalForm.unitValue" />
				    <label for=" ${spring.status.expression}">Unit value</label>
				    <input type="text" name="${spring.status.expression}" value="${spring.status.value?default('')}" />
				    <span class="error error_${spring.status.expression}"></span>
				</div>

				<div class="form-group">
				    <@spring.bind "addDkpDecayIntervalForm.dkp" />
				    <label for=" ${spring.status.expression}">DKP value</label>
				    <input type="text" name="${spring.status.expression}" value="${spring.status.value?default('')}" />
				    <span class="error error_${spring.status.expression}"></span>
				</div>

				<div class="form-group">
				    <@spring.bind "addDkpDecayIntervalForm.startDate" />
				    <label for=" ${spring.status.expression}">Start Date</label>
				    <p>The first decay will happen on this date. If not specified, the first decay will occur after the interval you specfied. E.g. If you entered one day,
				     the first decay would happen tomorrow.</p>
				    <input type="date" name="${spring.status.expression}" value="${spring.status.value?default('')}" />
				</div>

				<input type="submit" value="Add" />

			</form>
		</#if>

	</div>

	<script src="/ajax-form.js"></script>
	<script src="/validation-error-handler.js"></script>
	<script src="/decay-dkp-table.js"></script>
	<script src="/add-decay-dkp-interval-form.js"></script>

	<#if guild.dkpDecayInterval??>
		<script src="/delete-decay-dkp-interval-button.js"></script>
	</#if>

</@layout.general>