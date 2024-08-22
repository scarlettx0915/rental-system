package sus.project.rentalSystem.form;

import jakarta.validation.constraints.NotNull;

public class DeviceForm {

	@NotNull
	private String serial_number;
	private String maker;
	private String operating_system;
	private Integer memory;
	private Integer capacity;
	private boolean gpu;
	private String location;
	private boolean available;
	private boolean malfunction;
	private String lease_start_date;
	private String lease_end_date;
	private String info;
	private String register_date;
	private String update_date;
	private String inventory_date;
	
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
	public boolean isGpu() {
		return gpu;
	}
	public void setGpu(boolean gpu) {
		this.gpu = gpu;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public boolean isMalfunction() {
		return malfunction;
	}
	public void setMalfunction(boolean malfunction) {
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

	
}
