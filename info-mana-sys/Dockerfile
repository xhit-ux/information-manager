# 使用 OpenJDK 作为基础镜像
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 将打包好的 .jar 文件复制到容器中
COPY target/student-management-system-1.0-SNAPSHOT.jar /app/management.jar

# 暴露端口 8080
EXPOSE 8080

# 启动 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "management.jar"]
