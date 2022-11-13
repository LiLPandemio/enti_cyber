#!/bin/bash
PORT="42069"

echo "Servidor HMTP"

echo "(0) Listen"

MSG=`nc -l $PORT`

HANDSHAKE=`echo $MSG | cut -d " " -f 1`
IP_CLIENT= `echo $MSG  | cut -d " " -f 2`

echo "(3) Send - Confirmación Handshake"

if [ "$HANDSHAKE" != "GREEN_POWA" ]
then
	echo "KO_HMTP" | nc $IP_CLIENT $port
	exit 1
fi

echo "OK_HMTP" | nc $IP_CLIENT $PORT
echo "(4) Listen"

MSG=`nc -l $PORT`

echo "$MSG"
PREFIX=`echo $MSG | cut -d " " -f 1`
COMPROVACIO=`echo $MSG | cut -d " " -f 2` 


echo "(7) SEND - Confirmación del nombre del archivo"

if [ $PREFIX != "FILE_NAME" ]
then
	echo "KO0_HMTP" | nc $IP_CLIENT
	exit 2
fi

echo "OK_FILE_NAME" | nc $IP_CLIENT $PORT

echo "(8) LISTEN -Escuchando datos de archivo"

MSG=`nc -l $PORT`

exit 0
