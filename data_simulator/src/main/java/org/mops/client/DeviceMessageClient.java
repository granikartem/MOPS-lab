package org.mops.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import org.mops.config.properties.ConnectionSpecs;
import org.mops.model.DeviceDataGenerator;
import org.mops.model.DeviceMessage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.mops.util.Utils.isSuccessful;

@Data
public class DeviceMessageClient {
    private int generationBound;
    private ConnectionSpecs connectionSpecs;

    private DeviceDataGenerator deviceDataGenerator;
    private HttpClient httpClient;
    private ObjectMapper objectMapper;


    public DeviceMessageClient(ConnectionSpecs connectionSpecs, int generationBound) {
        this.generationBound = generationBound;
        this.connectionSpecs = connectionSpecs;

        this.deviceDataGenerator = new DeviceDataGenerator(generationBound);
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public void generateAndSendDeviceMessage(int deviceId) throws IOException, InterruptedException {

        HttpRequest request = buildDeviceMessageRequest(deviceId);

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (!isSuccessful(response)) {
            throw new UnsupportedOperationException("Failed to send device message.");
        }
    }

    private HttpRequest buildDeviceMessageRequest(int deviceId) {
        DeviceMessage deviceMessage = deviceDataGenerator.generateMessageForDevice(deviceId);

        URI url = URI.create(
                connectionSpecs.getHost() +
                        ":" +
                        connectionSpecs.getPort() +
                        String.format(connectionSpecs.getPath(), deviceId)
        );
        String messageJson = objectMapper.convertValue(deviceMessage, ObjectNode.class).toPrettyString();

        System.out.println(url);
        return HttpRequest.newBuilder()
                .uri(url)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(messageJson))
                .build();
    }
}
