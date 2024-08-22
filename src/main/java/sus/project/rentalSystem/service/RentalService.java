package sus.project.rentalSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sus.project.rentalSystem.entity.Rental;
import sus.project.rentalSystem.repository.RentalRepository;

@Service
public class RentalService {

	@Autowired
	RentalRepository rentalRepository;
	public List<Rental> findAll(){
		return rentalRepository.findAll();
	};
}
