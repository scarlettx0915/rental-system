package sus.project.rentalSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sus.project.rentalSystem.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, String>{

	@Query("SELECT r FROM Rental r WHERE r.delete_flag = false")
	 List<Rental> findAllActiveRentals();
}
