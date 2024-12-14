package com.makeskilled.CrisisMap.Controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makeskilled.CrisisMap.Entity.Emergency;
import com.makeskilled.CrisisMap.Repository.EmergencyRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmergencyController {

    @Autowired
    private EmergencyRepository emergencyRepository;

    @GetMapping("/createEmergency")
    public String createEmergency() {
        return "reportEmergency";
    }
    
    @PostMapping("/submit-emergency")
    public String submitEmergency(@RequestParam String emergencyTitle,
                                @RequestParam String emergencyLocation,
                                @RequestParam String emergencyDescription,
                                @RequestParam String emergencyDate,
                                HttpSession session) {
        Emergency emergency = new Emergency();
        emergency.setTitle(emergencyTitle);
        emergency.setLocation(emergencyLocation);
        emergency.setDescription(emergencyDescription);
        emergency.setDate(LocalDate.parse(emergencyDate));

        String loggedInUser = (String) session.getAttribute("username");
        emergency.setSubmittedBy(loggedInUser);

        // Fetch latitude and longitude for the emergency location
        try {
            double[] latLng = getLatLngFromAddress(emergencyLocation);
            emergency.setLatitude(latLng[0]);
            emergency.setLongitude(latLng[1]);
        } catch (Exception e) {
            System.err.println("Error fetching latitude and longitude for address: " + emergencyLocation);
            e.printStackTrace();
        }

        emergencyRepository.save(emergency);
        return "redirect:/myEmergencies";
    }

    /**
     * Utility method to get latitude and longitude from an address using Google Maps Geocoding API
     */
    private double[] getLatLngFromAddress(String address) throws Exception {
        String apiKey = ""; // Replace with your actual API key
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + 
                    URLEncoder.encode(address, StandardCharsets.UTF_8) + "&key=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        // Parse the response JSON to extract latitude and longitude
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response);
        JsonNode locationNode = rootNode.path("results").get(0).path("geometry").path("location");

        double latitude = locationNode.path("lat").asDouble();
        double longitude = locationNode.path("lng").asDouble();

        return new double[]{latitude, longitude};
    }

    @GetMapping("/myEmergencies")
    public String myEmergencies(HttpSession session, Model model) {
        String loggedInUser = (String) session.getAttribute("username");
        List<Emergency> emergencies = emergencyRepository.findBySubmittedBy(loggedInUser);
        model.addAttribute("emergencies", emergencies);
        return "myEmergencies";
    }

    @GetMapping("/emergencies")
    public List<Emergency> getAllEmergencies() {
        return emergencyRepository.findAll();
    }

    @GetMapping("/api/emergencies/nearby")
    @ResponseBody
    public List<Emergency> getNearbyEmergencies(@RequestParam double latitude,
                                                @RequestParam double longitude,
                                                @RequestParam(defaultValue = "10000") double radius) {
        return emergencyRepository.findAll().stream()
            .filter(emergency -> {
                double distance = calculateDistance(emergency.getLatitude(), emergency.getLongitude(), latitude, longitude);
                return distance <= radius; // Filter emergencies within the given radius
            })
            .collect(Collectors.toList());
    }

    /**
     * Utility function to calculate distance between two lat/lng points
     */
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371; // Radius in kilometers
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    @GetMapping("/api/emergencies")
    @ResponseBody
    public List<Emergency> getEmergencies() {
        return emergencyRepository.findAll();
    }

    @PatchMapping("/api/emergencies/{id}/update-status")
    public ResponseEntity<Emergency> updateEmergencyStatus(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        String newStatus = requestBody.get("status");
        Emergency emergency = emergencyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Emergency not found"));

        emergency.setStatus(newStatus); // Update the status
        Emergency updatedEmergency = emergencyRepository.save(emergency);

        return ResponseEntity.ok(updatedEmergency);
    }

}