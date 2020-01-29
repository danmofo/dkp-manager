# https://certbot.eff.org/docs/using.html#manual

# Things I need to test/build
# - Automatically copy the folders to the local certs directory....rather than copying them manually.
# - Automatically add/remove the TXT record using the digital ocean API by using hooks
# - Use a staging flag when testing this stuff so that we don't hit the rate limit
# - --email flag to enter email
# - Look at other options to skip the y/n prompts to do with IP address logging and such
# Make a script that you can jsut run to generate the certificates in a local folder, this should be combined with copying them into NGINX and restarting it so that certs can be obtained automatically.

FROM alpine:3.11.3

RUN apk add certbot curl jq

COPY scripts/generate-certs.sh /generate-certs.sh

# Copy scripts needed to create/delete DNS records for letsencrypt DNS challenge
COPY .env /.env
COPY scripts/create-txt-record.sh /scripts/create-txt-record.sh
COPY scripts/delete-txt-record.sh /scripts/delete-txt-record.sh

ENTRYPOINT ["sh", "/generate-certs.sh"]