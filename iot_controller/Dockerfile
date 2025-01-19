# Используем официальный образ OpenJDK 17, так как Java 23 пока не доступна
FROM openjdk:17

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл сборки JAR в контейнер
COPY target/iot_controller-1.0-SNAPSHOT.jar /app/iot_controller.jar

# Команда для выполнения при запуске контейнера
ENTRYPOINT ["java", "-jar", "/app/iot_controller.jar"]

EXPOSE 8080