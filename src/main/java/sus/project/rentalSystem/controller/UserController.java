package sus.project.rentalSystem.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import sus.project.rentalSystem.entity.User;
import sus.project.rentalSystem.form.UserForm;
import sus.project.rentalSystem.service.RentalService;
import sus.project.rentalSystem.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	RentalService rentalService;
	
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
	public String user_register(Model model) {
		if (!model.containsAttribute("userForm")) {
	        model.addAttribute("userForm", new UserForm());
	    }
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
		if(result.hasErrors()) { // Redirect to the form page
			 List<String> errorList = result.getAllErrors().stream()
                     .map(ObjectError::getDefaultMessage)
                     .collect(Collectors.toList());
			 redirectAttributes.addFlashAttribute("validationErrors", errorList);
			 redirectAttributes.addFlashAttribute("userForm", userForm);
			return("redirect:user_register");
		}
		
		Optional<User> device = userService.findById(userForm.getEmployee_no());
		if(device.isPresent()) {
			//if device exists display message
			redirectAttributes.addFlashAttribute("message", "社員番号が存在しています");
			redirectAttributes.addFlashAttribute("alertType", "alert-warning");
			redirectAttributes.addFlashAttribute("userForm", userForm);
			return "redirect:user_register";
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
			return("redirect:user_info?id="+userForm.getEmployee_no());
			}
		return("redirect:user_list");
	}
	
	@PostMapping("deleteUser")
	public String deleteUser(@RequestParam("employee_no") String employee_no, RedirectAttributes redirectAttributes) {
		if(rentalService.isEmployeeNoExists(employee_no)) {
			//if in rental then refuse to delete
			redirectAttributes.addFlashAttribute("message", "貸出中のため削除できません");
			redirectAttributes.addFlashAttribute("alertType", "alert-danger");
			return "redirect:/user_info?id=" + employee_no;
		}
		
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
