#!/usr/bin/env bash

# Deletes the letsencrypt TXT DNS record for dev.moff.rocks

source ../.env

record_id=$(curl -sS -X GET -H "Content-Type: application/json" \
	-H "Authorization: Bearer $DIGITALOCEAN_ACCESS_TOKEN" \
	"https://api.digitalocean.com/v2/domains/moff.rocks/records" | jq '.domain_records[] | select(.type == "TXT" and .name == "_acme-challenge.dev").id')

if [[ "$record_id" == "" ]]; then
	echo "error: Couldn't get record ID from DNS records. Make sure one exists with a type of TXT and a name of '_acme-challenge.dev'."
	exit 1
fi

curl -X DELETE -H "Content-Type: application/json" \
	-H "Authorization: Bearer $DIGITALOCEAN_ACCESS_TOKEN" \
	"https://api.digitalocean.com/v2/domains/moff.rocks/records/$record_id"