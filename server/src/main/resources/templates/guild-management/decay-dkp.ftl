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
					<td>${player.dkp}</td>
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
	</div>

	<script src="/ajax-form.js"></script>
	<script src="/validation-error-handler.js"></script>
	<script src="/decay-dkp-table.js"></script>

</@layout.general>