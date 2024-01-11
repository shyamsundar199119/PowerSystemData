package eu.statnett.powersystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.statnett.powersystem.dto.AveragePowerResult;
import eu.statnett.powersystem.dto.PowerRecord;
import eu.statnett.powersystem.dto.PowerSystemData;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;


public class PowerFlowAnalyzer {

    private Deque<PowerRecord> powerRecords = new ArrayDeque<>();
    private List<AveragePowerResult> averagePowerResult=new ArrayList<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String API_URL = "https://api.energidataservice.dk/dataset/PowerSystemRightNow?offset=0&limit=100&sort=Minutes1UTC%20DESC";
    private static final int DATA_WINDOW_SIZE = 5;

    public static void main(String[] args) {
        PowerFlowAnalyzer powerFlowAnalyzer = new PowerFlowAnalyzer();
        powerFlowAnalyzer.startDataAnalysis();
    }

    public void startDataAnalysis() {
        // Schedule a task to run every minute
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                analyzeAndPrintResults();
            }
        }, 0, 60 * 1000); // Run every minute
        
        try {
            Thread.sleep(10 * 60 * 1000); // Run for 10 minutes for demonstration purposes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Cancel the timer and exit
        timer.cancel();
        System.exit(0);
    }

    private void analyzeAndPrintResults() {
        try {
            // Make an HTTP GET request to the API
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the JSON response using the PowerSystemData class
            PowerSystemData powerSystemData = objectMapper.readValue(response.body(), PowerSystemData.class);
            double averageOnshoreWindPower=0;
            // Process each record in the response
            for (PowerRecord record : powerSystemData.getRecords()) {

                // Store the OnshoreWindPower value in the deque
                powerRecords.addLast(record);

                // Keep the deque size within the specified window size
                if (powerRecords.size() > DATA_WINDOW_SIZE) {
                    PowerRecord removedRecord = powerRecords.removeFirst();
                    averagePowerResult.add(new AveragePowerResult(averageOnshoreWindPower,removedRecord.getMinutes1UTC()));

                    // Printing the average OnshoreWindPower value
                    System.out.println("Timestamp: " + removedRecord.getMinutes1UTC() +
                            ", Average OnshoreWindPower for the Last 5 Minutes: " + averageOnshoreWindPower);
                }

                // Calculating average OnshoreWindPower value for the last 5 minutes
                averageOnshoreWindPower = calculateAverageOnshoreWindPower();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double calculateAverageOnshoreWindPower() {
        // Calculate the average OnshoreWindPower for the last 5 minutes based on the values in the deque
        double sum = 0;
        for (PowerRecord value : powerRecords) {
            sum += value.getOnshoreWindPower();
        }

        return sum / powerRecords.size();
    }
}

