<b>Daemons</b>

<code>sudo apt-get update && sudo apt-get install -y openjdk-17-jdk
wget https://github.com/lionell/labs/raw/master/parcs/Daemon/Daemon.jar
java -jar Daemon.jar</code>

<b>Host server</b>

<code>sudo apt-get update && sudo apt-get install -y openjdk-17-jdk
wget https://github.com/lionell/labs/raw/master/parcs/HostsServer/TCPHostsServer.jar
touch hosts.list
echo $daemon_1_internal_ip >> hosts.list
echo $daemon_2_internal_ip >> hosts.list
java -jar TCPHostsServer.jar</code>

<b>App</b>

<code>sudo apt-get update && sudo apt-get install -y openjdk-17-jdk git make
git clone https://github.com/VladProg/parcs-java-sort.git
cd parcs-java-sort
echo $hosts_server_internal_ip > out/server
make run $WORKERS</code>
