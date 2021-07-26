# JGroupsTCPFile
JGroups with TCP and File_PING

1. Create JAR for jGroups example

2. Create image:
sudo docker build .

3. Create the folder "clusterFolder" at desired location and it should be accessible by all containers.

4. Run container with different files for different values:
Command used 
reset; sudo docker run -i -v /home/sshekhar/temp/Docker/jgroupsExample/file2.txt:/opt/file.txt -v /home/sshekh/temp/Docker/jgroupsExample/protocol.xml:/opt/protocol.xml -v /home/sshekhar/temp/Docker/jgroupsExample/clusterFolder:/opt/clusterFolder 7ad79b7870f9


