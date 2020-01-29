#!/usr/bin/env bash

source ../.env

curl -sS -X GET -H "Content-Type: application/json" \
	-H "Authorization: Bearer $DIGITALOCEAN_ACCESS_TOKEN" \
	"https://api.digitalocean.com/v2/domains/moff.rocks/records" | jq 