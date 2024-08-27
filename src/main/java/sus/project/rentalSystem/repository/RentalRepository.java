package sus.project.rentalSystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import sus.project.rentalSystem.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, String>{

}
