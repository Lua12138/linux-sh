cp /etc/apt/sources.list /etc/apt/sources.list.backup # backup old
rm /etc/apt/sources.list
cd /etc/apt/
wget http://mirrors.163.com/.help/sources.list.trusty # download new
mv sources.list.trusty sources.list # rename
