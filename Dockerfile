# Etapa 1: construir runtime slim com jlink
FROM eclipse-temurin:21-jdk-alpine AS jre-builder

# Cria uma runtime mínima usando jlink
RUN jlink \
    --add-modules ALL-MODULE-PATH \
    --strip-debug \
    --no-header-files \
    --no-man-pages \
    --output /javaruntime

# Etapa 2: imagem final
FROM alpine:3.19

# Adiciona usuário não-root
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copia runtime slim
COPY --from=jre-builder /javaruntime /opt/java/openjdk

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Define JAVA_HOME e PATH
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="$JAVA_HOME/bin:$PATH"

ENTRYPOINT ["java","-jar","/app.jar"]