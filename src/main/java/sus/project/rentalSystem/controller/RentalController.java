package sus.project.rentalSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sus.project.rentalSystem.service.RentalService;
import sus.project.rentalSystem.service.UserService;

@Controller
public class RentalController {
	
	@Autowired
	RentalService rentalService;
	@Autowired
	UserService userService;
	
	@GetMapping("/rental_list")
	public String rental_list(Model model) {
		model.addAttribute("rentals", rentalService.findAll());
		return "rental_list";
	}
	
	
	@GetMapping("/rental_info")
	public String rental_info() {
		return "rental_info";
	}
	
	@GetMapping("/rental_register")
	public String rental_register(@RequestParam("id")String id, Model model) {
		model.addAttribute("serial_number",id);
		model.addAttribute("users", userService.findAll());
		return "rental_register";
	}
	
	@GetMapping("/rental_edit")
	public String rental_edit() {
		return "rental_edit";
	}
	
}
