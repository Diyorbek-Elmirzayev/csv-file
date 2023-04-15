package uz.pdp.csvfile.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import uz.pdp.csvfile.entity.User;
import uz.pdp.csvfile.repository.UserRepository;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
class UserService {
    private final UserRepository repository;
    public void saveAll(@Valid List<User> users) {
        repository.saveAll(users);
    }
}