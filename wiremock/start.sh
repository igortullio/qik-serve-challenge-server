host="localhost"
port="8081"

java -jar wiremock-jre8-standalone-2.33.2.jar --port $port --verbose --enable-stub-cors &> wiremock.log 2>&1 &
echo "Wiremock started on host $host and port $port. PID : $!"
