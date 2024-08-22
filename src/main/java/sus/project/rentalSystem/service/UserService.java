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
	
	public List<User> findAll(){
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

}
