#!/bin/bash

settimezone () {
	rm -rf /etc/localtime
	ln -s /usr/share/zoneinfo/$1 /etc/localtime
}

clearstatic () {
	echo "Clear Static for $1"
	cp /etc/systemd/network/$1.network /etc/systemd/network/$1.network.orig
	sed -i "/Name=$1/{n;N;N;N;N;N;N;d;}" /etc/systemd/network/$1.network
}

cleardhcp () {
	echo "Clear dhcp for $1"
	cp /etc/systemd/network/$1.network /etc/systemd/network/$1.network.orig
	sed -i "/Name=$1/{n;N;N;d;}" /etc/systemd/network/$1.network
}

setdhcp () {
	echo "Set dhcp for $1"
	sed "/Name=$1/r"<(
        echo " "
	    echo "[Network]"
        echo "DHCP=yes"
	) -i -- /etc/systemd/network/$1.network
}

setstatic () {
	echo "Set static for $1"
	sed "/Name=$1/r"<(
        echo " "
	    echo "[Network]"
	    echo "DHCP=no"
        echo " "
	    echo "IPv6PrivacyExtensions=false"
	    echo "Address=$2"
	    echo "Gateway=$3"
	) -i -- /etc/systemd/network/$1.network
}

if [[ "$1" == "timezone" ]]; then
	settimezone $2
fi

if [[ "$1" == "time" ]]; then
	date $2
fi

if [[ "$1" == "checkdhcp" ]]; then
	cat /etc/systemd/network/$2.network | grep DHCP | awk -F"=" '{print $2}'
fi

if [[ "$1" == "dhcptostatic" ]]; then
    systemctl stop systemd-networkd.service
	cleardhcp $2
	setstatic $2 $3 $4
fi

if [[ "$1" == "statictodhcp" ]]; then
    systemctl stop systemd-networkd.service
	clearstatic $2
	setdhcp $2
fi

if [[ "$1" == "statictostatic" ]]; then
    systemctl stop systemd-networkd.service
	clearstatic $2
	setstatic $2 $3 $4
fi

if [[ "$1" == "restartnet" ]]; then
	systemctl start systemd-networkd.service
fi
