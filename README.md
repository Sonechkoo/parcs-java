<b>Daemons</b>

<code>sudo apt-get update && sudo apt-get install -y openjdk-17-jdk
wget https://github.com/lionell/labs/raw/master/parcs/Daemon/Daemon.jar
java -jar Daemon.jar</code>

<b>Host server</b>

<code>sudo apt-get update && sudo apt-get install -y openjdk-17-jdk
wget https://github.com/lionell/labs/raw/master/parcs/HostsServer/TCPHostsServer.jar
rm hosts.list
touch hosts.list
echo 10.128.0.14 >> hosts.list
echo 10.128.0.13 >> hosts.list
java -jar TCPHostsServer.jar</code>

<b>App</b>

<code>sudo apt-get update && sudo apt-get install -y openjdk-17-jdk git make
git clone https://github.com/Sonechkoo/parcs-java
cd parcs-java
echo $hosts_server_internal_ip (10.128.0.10) > out/server
make run $WORKERS</code>
