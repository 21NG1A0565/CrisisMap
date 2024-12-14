package com.makeskilled.CrisisMap.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${zeptomail.api.url}")
    private String postUrl;

    @Value("${zeptomail.api.key}")
    private String apiKey;

    @Value("${zeptomail.from.email}")
    private String fromEmail;

    private String getEmailBody(String recipientName, String notificationTitle, String notificationMessage, String actionUrl) {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Email Notification</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            margin: 0;
                            padding: 0;
                            background-color: #f4f4f7;
                            color: #333;
                        }
                        .email-container {
                            max-width: 600px;
                            margin: 20px auto;
                            background: #ffffff;
                            border: 1px solid #ddd;
                            border-radius: 8px;
                            overflow: hidden;
                        }
                        .email-header {
                            background: #007bff;
                            color: #ffffff;
                            padding: 20px;
                            text-align: center;
                            font-size: 20px;
                            font-weight: bold;
                        }
                        .email-body {
                            padding: 20px;
                        }
                        .email-footer {
                            background: #f4f4f7;
                            text-align: center;
                            padding: 15px;
                            font-size: 12px;
                            color: #888;
                        }
                        .btn {
                            display: inline-block;
                            padding: 10px 20px;
                            font-size: 16px;
                            color: white;
                            text-decoration: none;
                            background-color: #007bff;
                            border-radius: 5px;
                            margin-top: 20px;
                        }
                        .btn:hover {
                            background-color: #0056b3;
                        }
                    </style>
                </head>
                <body>
                    <div class="email-container">
                        <div class="email-header">
                            CrisisMap Notification
                        </div>
                        <div class="email-body">
                            <p>Dear <strong>%s</strong>,</p>
                            <p>You have a new notification:</p>
                            <h3 style="color: #007bff;">%s</h3>
                            <p>%s</p>
                            <a href="%s" 
                            style="display: inline-block; 
                                    padding: 10px 20px; 
                                    font-size: 16px; 
                                    color: white !important; 
                                    text-decoration: none; 
                                    background-color: #007bff; 
                                    border-radius: 5px; 
                                    margin-top: 20px;">
                                View Details
                            </a>
                        </div>
                        <div class="email-footer">
                            <p>&copy; %d CrisisMap. All Rights Reserved.</p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(recipientName, notificationTitle, notificationMessage, actionUrl, java.time.Year.now().getValue());
    }

    public String sendEmail(String recipientEmail, String recipientName, String subject, String notificationTitle, String notificationMessage, String actionUrl) {
        String htmlBody = getEmailBody(recipientName, notificationTitle, notificationMessage, actionUrl);
    
        StringBuffer response = new StringBuffer();
        BufferedReader br = null;
        HttpURLConnection conn = null;
    
        try {
            URL url = new URL(postUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Zoho-enczapikey " + apiKey);
    
            JSONObject object = new JSONObject();
            JSONObject from = new JSONObject();
            from.put("address", fromEmail);
    
            JSONObject toEmailObj = new JSONObject();
            toEmailObj.put("address", recipientEmail);
            toEmailObj.put("name", recipientName);
    
            JSONObject to = new JSONObject();
            to.put("email_address", toEmailObj);
    
            object.put("from", from);
            object.put("to", new JSONObject[]{to});
            object.put("subject", subject);
            object.put("htmlbody", htmlBody);
    
            OutputStream os = conn.getOutputStream();
            os.write(object.toString().getBytes());
            os.flush();
    
            br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
    
        } catch (Exception e) {
            e.printStackTrace();
            try {
                br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
                String output;
                while ((output = br.readLine()) != null) {
                    response.append(output);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return response.toString();
    }
}
