package src.main.java.one.beebop.sonos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class RestClientApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RestClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length != 3) {
            System.err.println("Error: Invalid number of arguments.");
            System.err.println("Usage: java -jar rest-client.jar <IP> <deviceID> <ON|OFF|CONF>");
            System.exit(1);
        }

        String ip = args[0];
        String deviceId = args[1];
        String switchValue = args[2].toUpperCase();

        // Validate device ID
        if (!deviceId.matches("^[0-9a-fA-F]{10}$")) {
            System.err.println("Error: deviceID must be exactly 10 hexadecimal characters.");
            System.exit(1);
        }

        // Validate switch value
        if (!switchValue.matches("ON|OFF|CONF")) {
            System.err.println("Error: Third argument must be ON, OFF, or CONF.");
            System.exit(1);
        }

        // Prepare the payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("deviceid", deviceId);

        if (switchValue.equals("CONF")) {
            payload.put("data", new HashMap<>());
        } else {
            Map<String, String> data = new HashMap<>();
            data.put("switch", switchValue.toLowerCase());
            payload.put("data", data);
        }

        // Determine the URL
        String url = switchValue.equals("CONF")
                ? "http://" + ip + ":8081/zeroconf/info"
                : "http://" + ip + ":8081/zeroconf/switch";

        // Make the REST API request
        RestTemplate restTemplate = new RestTemplate();
        try {
            String response = restTemplate.postForObject(url, payload, String.class);
            System.out.println("Request successful!");
            System.out.println("Response: " + response);
        } catch (Exception e) {
            System.err.println("Request failed: " + e.getMessage());
        }
    }
}