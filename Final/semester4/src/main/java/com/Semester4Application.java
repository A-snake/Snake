package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Semester4Application {

    public static void main(String[] args) {

        SpringApplication.run(Semester4Application.class, args);
    }


    @GetMapping (value = "/upload3")
    public ResponseEntity<String> receiveFolderPath(@RequestBody FolderPath folderPath) {
        String receivedFolderName = folderPath.getFolderPath();

        System.out.println(receivedFolderName);
        // Perform any necessary operations with the received folder name

        return ResponseEntity.ok("Folder name received successfully");
    }

    static class FolderPath {
        private String folderPath;

        public String getFolderPath() {
            return folderPath;
        }

        public void setFolderPath(String folderPath) {
            this.folderPath = folderPath;
        }
    }

}
