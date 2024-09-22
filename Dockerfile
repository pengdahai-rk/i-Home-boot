# 基础镜像，自行构建ubuntu jdk21环境镜像
FROM p7i/jdk17:1.0
# 添加元数据
LABEL authors="p7i"
# 维护者信息
MAINTAINER p7i <pengdahai216@126.com>
# 将构建的 Spring Boot 可执行 JAR 复制到容器中，重命名为 iHomeApp.jar
ADD target/i-Home-boot.jar /workdir/iHomeApp.jar
# 容器启动后运行命令
ENTRYPOINT ["java",  "-Xms1024M", "-Xmx1024M","-jar", "/workdir/iHomeApp.jar"]
# docker build -t ihome:1.0.1 .
# && docker run -d -p 127.0.0.1:8999:80 -v /D/home/i-home/docker:/home/i-home --name i-Home ihome:1.0.1