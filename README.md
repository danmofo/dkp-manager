# DKP Manager

A website where users can register their guild and manage DKP in the game World of Warcraft.

**This is just a fun side-project because I couldn't think of a better idea. If I was seriously
considering this idea, I would just write a WoW plugin.**

- Backend: Java (Spring/Hibernate), MySQL and a little Bash/Groovy.
- Frontend: HTML, CSS and JavaScript, making use of Webpack.
- OS: Developing = Docker containers, Deploying = Probably Docker containers on Linux.

- Programs used:
	- Sublime Text 3
	- IntellJ Community Edition
	- Git Bash
	- MySQL CLI

## Up next...
- Remove hardcoded URL from webpack CSS image references, currently when we try and access /images/blah it's pointing to localhost:8081
- Improve dev/build scripts so that changes to docker-compose-dev are applied when restarting everything.
- Make it look pretty - maybe use SASS or something else.
	- Look at Grid/Flexbox maybe?!? Havne't looked at those much yet.
- Allow users to change their details
- At some point we need to look at implementing a HTTP server which proxies static asset requests to a 3rd party CDN and ensures only
certain requests actually make it to our backend, at the minute, all request are going to the backend (even for things like the
favicon, which is causing some errors to be thrown when it doesn't exist.).
- We need to make the domain configurable for the certificate generation process, at the minute it has moff.rocks hardcoded everywhere which won't work very well
when we want to use a proper domain.
- Write a script which renews the certificates and restarts/rebuilds the nginx container.

## Webpack notes
- We need a prod config.

## Features/functions

- Fixing generated SQL by modifying object mappings.
- CSRF protection
- Expire forgotten password token after a set amount of time.
- Domain name (replace localhost references with new domain) and SSL cert setup
- Add the ability to edit a DKP decay interval.
- Add the ability to decay DKP at intervals using percentages rather than fixed numbers.
- Automated tests?!?!
- Manually edit a players DKP
- need a way to specify guild ownership when signing up...how do other websites do this? Maybe some blizzard API that can read guild metadata or players in a guild?

## Development

Development should be the same on all platforms now this project is using Docker and Docker Compose which you need to have installed on the host machine.

You need to have the following software installed on your host machine (this won't be required in the future, but for now it is):
- NodeJS and NPM
- Maven

1. When first checking out/working on the project
	- Generate the SSL certificates by running `./generate-letsencrypt-certs.sh`, this should produce a bunch of `.pem` files in the conf/certs/moff.rocks folder.
	- Run `./run-dev`. This will start the database, rebuild the Docker images and watch frontend assets for changes. You should do this any time you start working on the project.
2. After editing Java:
	- If a dependency has changed, run `./rebuild`
	- If only Java has changed, run `./rebuild --fast`, this will be significantly quicker than `./rebuild`.
3. When editing client code:
	- If it was a Freemarker template, just reload the page.
	- IF it was JS/CSS, it will be loaded automatically - provided `./run-dev` was executed.
4. When done, run `docker-compose down` from the root directory, this will shut down all services (db/app).

## Random notes...
- Take a look here for getting the static assets working: https://github.com/webpack/webpack-dev-server/tree/master/examples/cli/https
- Cannot seem to access dev.moff.rocks until you fetch the DNS records using `dig`, I don't know why.
