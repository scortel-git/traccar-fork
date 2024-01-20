#!/bin/sh

PRESERVECONFIG=0
if [ -f /opt/eafa-pn-system/conf/eafa-pn-system.xml ]
then
    cp /opt/eafa-pn-system/conf/eafa-pn-system.xml /opt/eafa-pn-system/conf/eafa-pn-system.xml.saved
    PRESERVECONFIG=1
fi

mkdir -p /opt/eafa-pn-system
cp -r * /opt/eafa-pn-system
chmod -R go+rX /opt/eafa-pn-system

if [ ${PRESERVECONFIG} -eq 1 ] && [ -f /opt/eafa-pn-system/conf/eafa-pn-system.xml.saved ]
then
    mv -f /opt/eafa-pn-system/conf/eafa-pn-system.xml.saved /opt/eafa-pn-system/conf/eafa-pn-system.xml
fi

mv /opt/eafa-pn-system/eafa-pn-system.service /etc/systemd/system
chmod 664 /etc/systemd/system/eafa-pn-system.service

systemctl daemon-reload
systemctl enable eafa-pn-system.service

rm /opt/eafa-pn-system/setup.sh
rm -r ../out