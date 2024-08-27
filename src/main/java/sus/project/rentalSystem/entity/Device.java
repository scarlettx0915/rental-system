package sus.project.rentalSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="device")
public class Device {
	
	@Id
	@Column
	private String serial_number;
	@Column
	private String maker;
	@Column
	private String operating_system;
	@Column
	private Integer memory;
	@Column
	private Integer capacity;
	@Column
	private Boolean gpu;
	@Column
	private String location;
	@Column(insertable = false, updatable = true)
	private Boolean available;
	@Column(insertable = false, updatable = true)
	private Boolean malfunction;
	@Column(nullable = true)
	private String lease_start_date;
	@Column(nullable = true)
	private String lease_end_date;
	@Column
	private String info;
	@Column(updatable = false, nullable = true)
	private String register_date;
	@Column
	private String update_date;
	@Column
	private String inventory_date;
	@Column(insertable = false, updatable = true)
	private Boolean delete_flag;
	 
	public String getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public String getOperating_system() {
		return operating_system;
	}
	public void setOperating_system(String operating_system) {
		this.operating_system = operating_system;
	}
	public Integer getMemory() {
		return memory;
	}
	public void setMemory(Integer memory) {
		this.memory = memory;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public Boolean isGpu() {
		return gpu;
	}
	public void setGpu(Boolean gpu) {
		this.gpu = gpu;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Boolean isAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public Boolean isMalfunction() {
		return malfunction;
	}
	public void setMalfunction(Boolean malfunction) {
		this.malfunction = malfunction;
	}
	public String getLease_start_date() {
		return lease_start_date;
	}
	public void setLease_start_date(String lease_start_date) {
		this.lease_start_date = lease_start_date;
	}
	public String getLease_end_date() {
		return lease_end_date;
	}
	public void setLease_end_date(String lease_end_date) {
		this.lease_end_date = lease_end_date;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getRegister_date() {
		return register_date;
	}
	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public String getInventory_date() {
		return inventory_date;
	}
	public void setInventory_date(String inventory_date) {
		this.inventory_date = inventory_date;
	}
	public Boolean isDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(Boolean delete_flag) {
		this.delete_flag = delete_flag;
	}
	
	

}
