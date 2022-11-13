#!/bin/bash
PORT="42069"

echo "Servidor HMTP"

echo "(0) Listen"

MSG=`nc -l $PORT`

HANDSHAKE=`echo $MSG | cut -d " " -f 1`
IP_CLIENT=`echo $MSG | cut -d " " -f 2`

echo "(3) Send - Confirmación Handshake"

if [ "$HANDSHAKE" != "GREEN_POWA" ]
then
	echo "KO_HMTP" | nc $IP_CLIENT $PORT
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
	echo "KO_HMTP" | nc $IP_CLIENT
	exit 2
fi

echo "OK_FILE_NAME" | nc $IP_CLIENT $PORT

echo "(8) LISTEN - Escuchando datos de archivo"

FILENAME=`nc -l $PORT | cut -d " " -f 2`
echo "(DEBUG) $FILENAME"
wget -O uploads/$FILENAME $IP_CLIENT:$PORT
MD5_UPLOADED_FILE=`md5sum uploads/$FILENAME`
MD5_PROVIDED_FILE=`nc -l $PORT | cut -d " " -f 2`
if ["$MD5_UPLOADED_FILE"!="$MD5_PROVIDED_FILE"]
then
echo "KO_DATA_MD5" | nc $IP_CLIENT $PORT
else
echo "OK_DATA_MD5" | nc $IP_CLIENT $PORT
fi

exit 0
