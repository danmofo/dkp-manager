#!/usr/bin/env sh

# Creates a TXT DNS record for dev.moff.rocks for letsencrypt DNS validation.

. ../.env

if [ -z "$CERTBOT_VALIDATION" ]; then
	echo "warn: No CERTBOT_VALIDATION variable found, using a dummy data value."
	CERTBOT_VALIDATION="testing"
fi

curl -sS -X POST -H "Content-Type: application/json" \
	-H "Authorization: Bearer $DIGITALOCEAN_ACCESS_TOKEN" \
	-d "{\"type\":\"TXT\",\"name\":\"_acme-challenge\",\"data\":\"$CERTBOT_VALIDATION\",\"priority\":null,\"port\":null,\"ttl\":30,\"weight\":null,\"flags\":null,\"tag\":null}" \
	"https://api.digitalocean.com/v2/domains/moff.rocks/records"

sleep 20