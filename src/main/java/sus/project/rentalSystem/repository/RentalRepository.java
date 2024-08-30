package sus.project.rentalSystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sus.project.rentalSystem.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, String>{

	 @Query("select count(r) > 0 from Rental r where r.employee_no = :employee_no")
	 boolean existsByEmployee_no(@Param("employee_no") String employee_no);
}
