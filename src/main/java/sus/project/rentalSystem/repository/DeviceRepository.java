package sus.project.rentalSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sus.project.rentalSystem.entity.Device;



public interface DeviceRepository extends JpaRepository<Device, String>{

}
