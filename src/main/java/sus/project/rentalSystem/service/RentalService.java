package sus.project.rentalSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sus.project.rentalSystem.entity.Device;
import sus.project.rentalSystem.entity.Rental;
import sus.project.rentalSystem.repository.DeviceRepository;
import sus.project.rentalSystem.repository.RentalRepository;

@Service
public class RentalService {

	@Autowired
	RentalRepository rentalRepository;
	@Autowired
	DeviceRepository deviceRepository;
	
	public List<Rental> findAll(boolean hide_deleted){
		if(hide_deleted) {
			return rentalRepository.findAllActiveRentals();
		}
		
		return rentalRepository.findAll();
	}
	
	public Optional<Rental> findById(String serial_number){
		return rentalRepository.findById(serial_number);
	}
	
	public void save(String serial_number, String employee_no, String employee_name, String rental_date,
			String return_date, String info) {
		Rental rental = new Rental();
		rental.setSerial_number(serial_number);
		rental.setEmployee_no(employee_no);
		rental.setEmployee_name(employee_name);
		rental.setRental_date(rental_date);
		rental.setReturn_date(return_date);
		rental.setInfo(info);
		rentalRepository.save(rental);
		
	};
	
	public void delete(String serial_number) {
		Optional<Rental> optionalRental = this.findById(serial_number);
		Optional<Device> optionalDevice = deviceRepository.findById(serial_number);
		if(optionalRental.isPresent() && optionalDevice.isPresent()) {
			Device device = optionalDevice.get();
			device.setAvailable(true);
			deviceRepository.save(device);
			
			rentalRepository.deleteById(serial_number);
		}
	}
}
