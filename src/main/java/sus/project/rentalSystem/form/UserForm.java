package sus.project.rentalSystem.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class UserForm {

	@NotBlank(message = "社員番号を入力してください")
    @Size(max = 20, message = "社員番号は20文字以内入力してください")
	private String employee_no;
	@NotBlank(message = "氏名を入力してください")
	@Size(max = 20, message = "氏名は20文字以内入力してください")
	private String name;
	private String name_kana;
	private String department;
	@PositiveOrZero(message="正しい電話番号を入力してください")
	private String tel_no;
	@Email(message="正しいメールアドレスを入力してください")
	private String mail_address;
	@PositiveOrZero(message="正しい年齢を入力してください")
	private Integer age;
	private String gender;
	private String position;
	private String account_level;
	private String retire_date;
	private String register_date;
	private String update_date;
	
	
	public String getEmployee_no() {
		return employee_no;
	}
	public void setEmployee_no(String employee_no) {
		this.employee_no = employee_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName_kana() {
		return name_kana;
	}
	public void setName_kana(String name_kana) {
		this.name_kana = name_kana;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getTel_no() {
		return tel_no;
	}
	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
	}
	public String getMail_address() {
		return mail_address;
	}
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getAccount_level() {
		return account_level;
	}
	public void setAccount_level(String account_level) {
		this.account_level = account_level;
	}
	public String getRetire_date() {
		return retire_date;
	}
	public void setRetire_date(String retire_date) {
		this.retire_date = retire_date;
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
	
	
}
