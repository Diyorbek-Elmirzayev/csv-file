package uz.pdp.csvfile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.csvfile.entity.User;
import uz.pdp.csvfile.repository.UserRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyCsvService {

    @Autowired
    private UserRepository userRepository;

    public List<User> parseCsv(InputStream inputStream) throws IOException {
        List<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                User user = new User();
                user.setId(values[0]);
                user.setEmail(values[1]);
                user.setPassword(values[2]);
                user.setAddress(values[3]);
                users.add(user);
            }
        }

        return users;
    }

    public void saveAll(List<User> users) {
        userRepository.saveAll(users);
    }
}