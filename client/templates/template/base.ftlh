<#macro general>
	<!DOCTYPE html>
	<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Page title goes here - todo</title>
		<link href="https://fonts.googleapis.com/css?family=Alatsi&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="/styles/main.css" />

	</head>
	<body>
		<ul class="navigation">
			<div class="container">
				<#if session.loggedIn>
					<li class="navigation__item is-active"><a href="/dashboard">Dashboard</a></li>

					<#if session.player.isGuildMaster>
						<li class="navigation__item"><a href="/guild-management/">Guild management</a></li>
					</#if>
				<#else>
					<li class="navigation__item is-active"><a href="/">DKP Manager</a></li>
				</#if>
				<li class="navigation__item"><a href="/guilds">Guilds</a></li>
				<li class="navigation__item"><a href="/about">About</a></li>
				<li class="navigation__item"><a href="/contact">Contact</a></li>
				<#if session.loggedIn>
					<form action="/logout" method="POST">
						<input type="submit" value="Log out" />	
					</form>
				<#else>
					<li class="navigation__item"><a href="/login">Log in</a></li>
					<li class="navigation__item"><a href="/signup">Sign up</a></li>
				</#if>
			</div>
		</ul>
		<#nested />	

		<script src="/scripts/main.js"></script>
	</body>
	</html>
</#macro>