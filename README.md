zookeeper-leader-election
=========================

Example shows how to implement Leader election using the Curator recipes with Zookeeper
Run Zookeeper: /zookeeper-3.4.6/bin$ zkCli.sh -server localhost:2181 
Checkout the code and run multiples of ./gradlew run on different terminals.

One of the instances becomes the leader and will print "I'm the leader" 
Kill it (in another ~30 seconds) some other instances gets elected as the leader
