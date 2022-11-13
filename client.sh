#!/bin/bash
echo "Cliente HMTP"
IP=`ip a | grep inet | grep ens33 | sed "s/^ *//g" | cut -d " " -f 2 | cut -d "/" -f 1`
echo "GREEN_POWA $IP" | nc localhost 4242
