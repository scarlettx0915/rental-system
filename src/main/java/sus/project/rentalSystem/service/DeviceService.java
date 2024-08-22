package sus.project.rentalSystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sus.project.rentalSystem.entity.Device;
import sus.project.rentalSystem.repository.DeviceRepository;

@Service
public class DeviceService {

	@Autowired
	DeviceRepository deviceRepository;
	
	public List<Device> findAll(){
		return deviceRepository.findAll();
	};
	
	public Optional<Device> findById(String serial_number) {
		return deviceRepository.findById(serial_number);
	}
	
	
	public void save(String serial_number, String maker, Integer memory, Integer capacity, Boolean gpu, String location, String lease_start_date, String lease_end_date, String inventory_date, String info) {
		
		Device device = new Device();
		device.setSerial_number(serial_number);
		device.setMaker(maker);
		device.setMemory(memory);
		device.setCapacity(capacity);
		device.setGpu(gpu);
		device.setLocation(location);
		device.setInfo(info);
		device.setRegister_date(LocalDate.now().toString());
		deviceRepository.save(device);
	}
}
