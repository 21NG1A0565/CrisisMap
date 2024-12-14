package com.makeskilled.CrisisMap.Controller;

import com.makeskilled.CrisisMap.Entity.Notification;
import com.makeskilled.CrisisMap.Entity.User;
import com.makeskilled.CrisisMap.Repository.NotificationRepository;
import com.makeskilled.CrisisMap.Repository.UserRepository;
import com.makeskilled.CrisisMap.Service.EmailService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping("/api/notifications")
    public ResponseEntity<?> createNotification(@RequestBody Notification notification) {
        try {
            Notification savedNotification = notificationRepository.save(notification);

            if ("public".equalsIgnoreCase(notification.getPostedType())) {
                List<User> users = userRepository.findAll();
                for (User user : users) {
                    emailService.sendEmail(
                        user.getEmail(),
                        user.getUsername(),
                        notification.getTitle(),
                        notification.getTitle(),
                        notification.getMessage(),
                        "http://localhost:8094/" // Example action URL
                    );
                }
            } else if ("private".equalsIgnoreCase(notification.getPostedType())) {
                User user = userRepository.findByUsername(notification.getPostedTo());
                if (user != null) {
                    emailService.sendEmail(
                        user.getEmail(),
                        user.getUsername(),
                        notification.getTitle(),
                        notification.getTitle(),
                        notification.getMessage(),
                        "http://localhost:8094/"
                    );
                }
            }

            return ResponseEntity.ok(savedNotification);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Failed to create notification: " + e.getMessage());
        }
    }

    @GetMapping("/api/notifications")
    public ResponseEntity<List<Notification>> getNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/sendNotification")
    public String sendNotificationPage(){
        return "sendNotification";
    }

    @GetMapping("/api/session/username")
    public ResponseEntity<Map<String, String>> getUsername(HttpSession session) {
        String username = (String) session.getAttribute("username"); // Assuming username is stored in session
        System.out.println("username: " + username);
        
        if (username == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(Collections.singletonMap("error", "Username not found in session"));
        }
        return ResponseEntity.ok(Collections.singletonMap("username", username));
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<Map<String, String>>> getAllUsers() {
        List<User> users = userRepository.findAll(); // Assuming you have a User entity

        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Return 204 No Content
        }

        List<Map<String, String>> userResponse = users.stream()
                                                    .map(user -> Collections.singletonMap("username", user.getUsername()))
                                                    .collect(Collectors.toList());

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/notifications")
    public String notificationsPage(){
        return "notifications";
    }

    @GetMapping("/ngoNotifications")
    public String ngoNotificationsPage(){
        return "ngoNotifications";
    }

    @GetMapping("/govNotifications")
    public String govNotificationsPage(){
        return "govNotifications";
    }

    @GetMapping("/vNotifications")
    public String vNotificationsPage(){
        return "vNotifications";
    }

    @GetMapping("/sendAlert")
    public String sendAlertPage() {
        return "sendNotification";
    }
    

    
}