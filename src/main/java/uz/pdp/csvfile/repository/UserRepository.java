
package uz.pdp.csvfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.csvfile.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}