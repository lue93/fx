# MultiStage Build
# Fase 1: Preparação de ambiente em Alpine
FROM alpine:3.18 AS base

# Fase 2: Construção do projeto com Maven
FROM maven:3.9.5 AS build
WORKDIR /app

# Define JAVA_HOME e PATH
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="$JAVA_HOME/bin:$PATH"
ENV SPRING_PROFILES_ACTIVE=docker
ENV INSTANCE_ID=$HOSTNAME


# Cria runtime customizada com jlink
RUN jlink \
    --add-modules ALL-MODULE-PATH \
    --strip-debug \
    --no-header-files \
    --no-man-pages \
    --compress=2 \
    --output /javaruntime

# Copia apenas o pom.xml primeiro para resolver dependências
COPY pom.xml ./

# Copia arquivos de configuração do Maven
COPY ./src ./src
COPY ./.env ./.env
COPY ./logback.xml ./logback.xml

# Compila e gera o JAR
RUN mvn clean package -DskipTests -q

# Lista o conteudo   # carrega variáveis do arquivo .env
RUN ls -lah

# Fase 2: Runtime minimalista
FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY --from=build /app/target/*.jar app.jar
RUN chmod +x app.jar

EXPOSE 8082

#c ["java",$JAVA_OPTS,"-jar","/app/app.jar"]
CMD java -jar app.jar