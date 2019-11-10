# DKP Manager

A website where users can register their guild and manage DKP in the game World of Warcraft.

- Backend: Java (Spring/Hibernate), MySQL and maybe a little Bash/Groovy.
- Frontend: HTML, CSS and JavaScript. Maybe add some Webpack stuff later on.
- OS: Developing = Windows, Deploying = Linux. Maybe Docker containers in the future.

- Programs used:
	- Sublime Text 3
	- IntellJ Community Edition
	- Git Bash
	- MySQL CLI

## Up next...
- Write the rest of our Java models / DAOs
- Write the user flows and then write the controllers.
- Write the rest of our DAOs / services.
- ???

## Features/functions

x Lists guilds
- Guild page
	- List players in the guild
	- List the amount of DKP each player has
- Player page
	- Contain a full list of DKP history - so you can see how much DKP has been awarded decayed.
- Log in / Sign up pages

## Development

### Windows
- Open MySQL CLI
- Run `source c:/dev/dkp-manager/schema.sql`
- Run app in IntelliJ (for now)