package sus.project.rentalSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="rental")
public class Rental {

	@Id
	@Column
	private String serial_number;
	@Column
	private String employee_no;
	@Column
	private String employee_name;
	@Column
	private String rental_date;
	@Column
	private String return_date;
	@Column
	private String info;
	@Column(insertable = false, updatable = true)
	private Boolean delete_flag;
	
	public String getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}
	public String getEmployee_no() {
		return employee_no;
	}
	public void setEmployee_no(String employee_no) {
		this.employee_no = employee_no;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getRental_date() {
		return rental_date;
	}
	public void setRental_date(String rental_date) {
		this.rental_date = rental_date;
	}
	public String getReturn_date() {
		return return_date;
	}
	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Boolean getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(Boolean delete_flag) {
		this.delete_flag = delete_flag;
	}
	
	
}
