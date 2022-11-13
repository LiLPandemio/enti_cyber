#!/bin/bash
echo "Servidor HMTP"
HANDSHAKE=`nc -l 4242`
if [ "$HANDSHAKE" != "GREEN_POWA"]
then
	echo "KO_HMTP"
else
	echo "OK_HMTP"
	while true
	do
		MSG=`nc -l 4242`
		echo "$MSG"
	done
fi
