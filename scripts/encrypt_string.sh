#!/usr/bin/env bash

# Encrypts a string using jasypt

secret_key_path="../.secretkey"

if [[ ! -f "$secret_key_path" ]]; then
	echo ".secretkey file not found. Please create a .secretkey file with a secret key in."
	exit 1
fi

if [[ "$1" == "" ]]; then
	echo "You need to enter a value to encrypt, e.g. ./encrypt_string.sh <value>"
	exit 1
fi

secret_key="$(cat $secret_key_path)"

java -cp "c:/users/dan/.m2/repository/org/jasypt/jasypt/1.9.3/jasypt-1.9.3.jar"  org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="$1" password=$secret_key algorithm=PBEWithMD5AndDES