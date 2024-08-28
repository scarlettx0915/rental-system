package sus.project.rentalSystem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import sus.project.rentalSystem.entity.User;
import sus.project.rentalSystem.form.UserForm;
import sus.project.rentalSystem.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user_list")
	public String user_list(Model model) {
		model.addAttribute("users", userService.findAll(true));
		return "user_list";
	}
	
	@GetMapping("/user_info")
	public String user_info(@RequestParam("id")String id, Model model) {
		Optional<User> user = userService.findById(id);
		if(user.isPresent()) {
			model.addAttribute("user",user.get());
			return "user_info";
		}
		return "redirect:user_list";
	}
	
	@GetMapping("/user_register")
	public String user_register() {
		return "user_register";
	}
	
	@GetMapping("/user_edit")
	public String user_edit(@RequestParam("id")String id, Model model) {
		Optional<User> user = userService.findById(id);
		if(user.isPresent()) {
			model.addAttribute("user",user.get());
			return "user_edit";
		}
		return "redirect:user_list";
	}
	
	@PostMapping("addUser")
	public String addUser(@Valid @ModelAttribute UserForm userForm, BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return("redirect:user_list");
		}
		
		userService.save(
			userForm.getEmployee_no(),
			userForm.getName(),
			userForm.getName_kana(),
			userForm.getDepartment(),
			userForm.getTel_no(),
			userForm.getMail_address(),
			userForm.getAge(),
			userForm.getGender(),
			userForm.getPosition(),
			userForm.getAccount_level()
			);
		redirectAttributes.addFlashAttribute("message", "社員を登録しました");
		redirectAttributes.addFlashAttribute("alertType", "alert-success");
		return("redirect:user_list");
	}
	
	@PostMapping("editUser")
	public String editUser(@Valid @ModelAttribute UserForm userForm, BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "redirect:user_list";
		}
		Optional<User> optionalUser = userService.findById(userForm.getEmployee_no());
		if(optionalUser.isPresent()) {
		userService.update(
			userForm.getEmployee_no(),
			userForm.getName(),
			userForm.getName_kana(),
			userForm.getDepartment(),
			userForm.getTel_no(),
			userForm.getMail_address(),
			userForm.getAge(),
			userForm.getGender(),
			userForm.getPosition(),
			userForm.getAccount_level(),
			userForm.getRetire_date()
			);
			redirectAttributes.addFlashAttribute("message", "社員を編集しました");
			redirectAttributes.addFlashAttribute("alertType", "alert-primary");
			return("redirect:user_list");
			}
		return("redirect:user_list");
	}
	
	@PostMapping("deleteUser")
	public String deleteUser(@RequestParam("employee_no") String employee_no, RedirectAttributes redirectAttributes) {
		Optional<User> optionalUser = userService.findById(employee_no);
		if(optionalUser.isPresent()) {
			userService.delete(employee_no);
			redirectAttributes.addFlashAttribute("message", "社員を削除しました");
			redirectAttributes.addFlashAttribute("alertType", "alert-warning");
			return("redirect:user_list");
		}
		return("redirect:user_list");
	}
}
