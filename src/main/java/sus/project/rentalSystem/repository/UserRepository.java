package sus.project.rentalSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sus.project.rentalSystem.entity.User;


public interface UserRepository extends JpaRepository<User, String>{

	 @Query("SELECT u FROM User u WHERE u.delete_flag = false")
	 List<User> findAllActiveUsers();
	
}
