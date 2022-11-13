#!/bin/bash
IP_SERVER="localhost"
IP_LOCAL="127.0.0.1"
PORT="42069" #Default 4242

echo "Cliente HMTP"

echo "(1) SEND - ENVIANDO EL HANDSHAKE"

echo "GREEN_POWA $IP_LOCAL" | nc $IP_SERVER $PORT

echo "(2) LISTEN - Esuchando confirmacion"

MSG=`nc -l $PORT`

if ["$MSG" != "OK_HMTP"]
then 
        echo "ERROR 1: Handshake mal formado"
        exit 1
fi
echo "(5) SEND - filename (enviem el nom del arxiu)"
FILENAME="meme.jpg"
MD5FILESUM=`md5sum $FILENAME | cut -d " " -f 1`
FILECONTENT=`cat $FILENAME`

echo "FILE_NAME $FILENAME" | nc $IP_SERVER $PORT
echo "FILE_CONTENT $FILECONTENT" | nc $IP_SERVER $PORT
echo "FILE_HASH $MD5FILESUM" | nc $IP_SERVER $PORT

echo "(6) LISTEN - Escoltant confirmacio del arxiu"

MSG=`nc -l $PORT`

exit 0
