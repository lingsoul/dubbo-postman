export JAVA_HOME=/opt/app/jdk1.8.0_91
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

JAR_HOME="/home/apple/dubbo-postman"
ps -ef | grep java |grep -v grep |grep dubbo-postman.jar | awk '{print $2}' | xargs -i kill -9 {}

nohup java -jar $JAR_HOME/dubbo-postman.jar > $JAR_HOME/nohup.out &

tail -f $JAR_HOME/nohup.out
