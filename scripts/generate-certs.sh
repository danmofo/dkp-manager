# Generate the certificate
certbot certonly --manual --non-interactive --agree-tos --no-eff --manual-public-ip-logging-ok --manual-auth-hook /scripts/create-txt-record.sh --manual-cleanup-hook /scripts/delete-txt-record.sh --preferred-challenges dns --email danmofo@gmail.com --domain *.moff.rocks

echo "Certificates now being copied to conf/certs/moff.rocks"

# Copy the certificates to another folder
mkdir -p /certs/moff.rocks
cp -RL /etc/letsencrypt/live/moff.rocks/* /certs/moff.rocks/
chmod a=r /certs/moff.rocks/privkey.pem