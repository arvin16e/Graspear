package com.Projectmanagement.config;

import java.io.FileInputStream;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseInitializer {

	 public static void initializeFirebase() {
	        try {
	            FileInputStream serviceAccount = new FileInputStream("src/main/resources/google-services (2).json");

	            FirebaseOptions options = new FirebaseOptions.Builder()
	                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
	                    .setDatabaseUrl("https://<your-database-name>.firebaseio.com")
	                    .build();

	            FirebaseApp.initializeApp(options);
	            System.out.println("Firebase Initialized Successfully!");
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("Error initializing Firebase: " + e.getMessage());
	        }
	    }
}
