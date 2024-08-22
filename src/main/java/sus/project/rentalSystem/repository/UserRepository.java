package sus.project.rentalSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sus.project.rentalSystem.entity.User;


public interface UserRepository extends JpaRepository<User, String>{

	
}
