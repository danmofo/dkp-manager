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

- Session system
	1. Request comes in
		- If cookie exists
			- Load session data
		- If cookie doesn't exist
			- Create new session
	2. Handle request inside controller
		- Do whatever, add/remove session data
	3. When request is finished
	    - Persist cookie to client
- Log in
- Post sign up actions (after form submission is succesful)

## Development

### Windows
- Open MySQL CLI
- Run `source c:/dev/dkp-manager/schema.sql`
- Run app in IntelliJ (for now)