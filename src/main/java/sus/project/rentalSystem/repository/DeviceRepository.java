package sus.project.rentalSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sus.project.rentalSystem.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, String>{

	 @Query("SELECT d FROM Device d WHERE d.delete_flag = false")
	 List<Device> findAllActiveDevices();
	
}
