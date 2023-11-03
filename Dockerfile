FROM centos:7 as install_chrome
RUN yum update -y
RUN curl https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm -o /root/google-chrome-stable_current_x86_64.rpm
RUN yum install /root/google-chrome-stable_current_x86_64.rpm -y
RUN yum install epel-release -y
RUN yum install chromedriver -y
RUN yum install tar zip unzip -y

FROM install_chrome as install_java
WORKDIR /root
RUN curl https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-17.0.2_linux-x64_bin.tar.gz -o openjdk-17.0.2_linux-x64_bin.tar.gz
RUN tar -xzf openjdk-17.0.2_linux-x64_bin.tar.gz
RUN mv jdk-17.0.2 /opt/jdk-17
RUN export JAVA_HOME=/opt/jdk-17
RUN export PATH=$PATH:$JAVA_HOME/bin

FROM install_java as install_maven
WORKDIR /root

RUN curl https://downloads.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz -o apache-maven-3.6.3-bin.tar.gz
RUN tar -xzf apache-maven-3.6.3-bin.tar.gz -C /opt
RUN ln -s /opt/apache-maven-3.6.3 /opt/maven
RUN chown -h root:root /opt/maven
ENV M2_HOME=/opt/maven
ENV MAVEN_HOME=/opt/maven
ENV PATH=$M2_HOME/bin:$PATH
ENV JAVA_HOME=/opt/jdk-17
ENV PATH=$PATH:$JAVA_HOME/bin
RUN mvn --version

FROM install_maven as qa_automation

WORKDIR /app

RUN google-chrome-stable --headless --disable-gpu --print-to-pdf https://www.chromestatus.com/ --no-sandbox
RUN mkdir -p /app/target
RUN touch /app/target/failedrun.txt
COPY pom.xml .
RUN mvn clean package -DskipTests
COPY . .
ENV ENVIRONMENT="recette"
ENV DRIVER="chrome"
CMD mvn clean -Denvironment="${ENVIRONMENT}" -DwebDriver="${DRIVER}" test
