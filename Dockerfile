# 使用官方 OpenJDK 镜像作为基础镜像
FROM openjdk:21-jdk-slim

# 复制编译好的 JAR 文件到容器中
COPY target/student-management-system-1.0.0.jar student-app.jar

# 启动应用
ENTRYPOINT ["java", "-jar", "/student-app.jar"]
