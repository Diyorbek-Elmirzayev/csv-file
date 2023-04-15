package uz.pdp.csvfile.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.csvfile.entity.User;
import uz.pdp.csvfile.repository.UserRepository;
import uz.pdp.csvfile.service.MyCsvService;
import uz.pdp.csvfile.utils.CsvUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private MyCsvService myCsvService;

    @PostMapping("/upload")
    public HttpEntity<?> uploadCSV(@RequestBody MultipartFile file){
        try {
            List<User> users = myCsvService.parseCsv(file.getInputStream());
            myCsvService.saveAll(users);
            return ResponseEntity.ok("CSV file has been uploaded and saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload CSV file: " + e.getMessage());
        }
    }

}