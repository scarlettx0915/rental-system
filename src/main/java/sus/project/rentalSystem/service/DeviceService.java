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
	
	public List<Device> findAll(boolean hide_deleted){
		if(hide_deleted) {
			return deviceRepository.findAllActiveDevices();
		}
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
	
	public void update(String serial_number, String maker, Integer memory, Integer capacity, Boolean gpu, String location, Boolean malfunction, String lease_start_date, String lease_end_date, String inventory_date, String info) {
		
		Optional<Device> optionalDevice = this.findById(serial_number);
		if(optionalDevice.isPresent()) {
			Device device = optionalDevice.get();			
			device.setMaker(maker);
			device.setMemory(memory);
			device.setCapacity(capacity);
			device.setGpu(gpu);
			device.setLocation(location);
			device.setMalfunction(malfunction);
			device.setLease_start_date(Optional.ofNullable(lease_start_date).filter(s -> !s.isEmpty()).orElse(null));
			device.setLease_end_date(Optional.ofNullable(lease_end_date).filter(s -> !s.isEmpty()).orElse(null));
			device.setInventory_date(Optional.ofNullable(inventory_date).filter(s -> !s.isEmpty()).orElse(null));
			device.setInfo(info);
			device.setUpdate_date(LocalDate.now().toString());
			deviceRepository.save(device);
		}
	}
	
	public void delete(String serial_number) {
		Optional<Device> optionalDevice = this.findById(serial_number);
		if(optionalDevice.isPresent()) {
			Device device = optionalDevice.get();
			device.setDelete_flag(true);
			deviceRepository.save(device);
		}
	}
}
