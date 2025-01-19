# Используем официальный образ OpenJDK 17, так как Java 23 пока не доступна
FROM openjdk:17

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл сборки JAR в контейнер
COPY target/rule_engine-1.0-SNAPSHOT.jar /app/rule_engine.jar

# Команда для выполнения при запуске контейнера
ENTRYPOINT ["java", "-jar", "/app/rule_engine.jar"]

EXPOSE 6060