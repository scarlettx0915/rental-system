package sus.project.rentalSystem.service;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sus.project.rentalSystem.entity.User;
import sus.project.rentalSystem.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> findAll(boolean hide_deleted){
		if(hide_deleted) {
			return userRepository.findAllActiveUsers();
		}
		return userRepository.findAll();
	};
	
	public void save(String employee_no, String name, String name_kana, String department, String tel_no, String mail_address, Integer age, String gender, String position, String account_level) {
		User user = new User();
		user.setName(name);
		user.setEmployee_no(employee_no);
		user.setName_kana(name_kana);
		user.setDepartment(department);
		user.setTel_no(tel_no);
		user.setMail_address(mail_address);
		user.setAge(age);
		user.setGender(gender);
		user.setPosition(position);
		user.setAccount_level(account_level);
		user.setRegister_date(LocalDate.now().toString());

		userRepository.save(user);
	}


	public Optional<User> findById(String employee_no) {
		return userRepository.findById(employee_no);
	}


	public void update(String employee_no, String name, String name_kana, String department, String tel_no, String mail_address,
			Integer age, String gender, String position, String account_level, String retire_date) {
		
		Optional<User> optionalUser = this.findById(employee_no);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setName(name);
			user.setEmployee_no(employee_no);
			user.setName_kana(name_kana);
			user.setDepartment(department);
			user.setTel_no(tel_no);
			user.setMail_address(mail_address);
			user.setAge(age);
			user.setGender(gender);
			user.setPosition(position);
			user.setAccount_level(account_level);
			user.setRetire_date(Optional.ofNullable(retire_date).filter(s -> !s.isEmpty()).orElse(null));
			user.setUpdate_date(LocalDate.now().toString());
			
			userRepository.save(user);
		}
	}


	public void delete(String employee_no) {
		Optional<User> optionalUser = this.findById(employee_no);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			user.setDelete_flag(true);
			userRepository.save(user);
		}
	}
}
