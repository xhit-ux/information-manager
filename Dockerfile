# 第一阶段：使用 Maven 镜像进行构建
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# 设置工作目录
WORKDIR /app

# 复制项目文件到容器
COPY . .

# 使用 Maven 打包应用
RUN mvn clean package -DskipTests

# 第二阶段：运行 Spring Boot 应用
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 从构建阶段复制 jar 包
COPY --from=builder /app/target/student-management-system-1.0-SNAPSHOT.jar /app/management.jar

# 暴露端口
EXPOSE 8080

# 启动应用
ENTRYPOINT ["java", "-jar", "management.jar"]
