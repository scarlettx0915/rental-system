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

import jakarta.validation.Valid;
import sus.project.rentalSystem.entity.Device;
import sus.project.rentalSystem.entity.Rental;
import sus.project.rentalSystem.entity.User;
import sus.project.rentalSystem.form.DeviceForm;
import sus.project.rentalSystem.form.RentalForm;
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
	
	@PostMapping("addRental")
	public String addRental(@Valid @ModelAttribute RentalForm rentalForm, BindingResult result) {
		if(result.hasErrors()) {
			return "rental_register";
		}
		Optional<Rental> optionalRental = rentalService.findById(rentalForm.getSerial_number());
		if(optionalRental.isPresent()) {
			//if device exists display message
			return "device_register";
		}
		
		Optional<User> optionalUser = userService.findById(rentalForm.getEmployee_no());
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			rentalService.save(
					rentalForm.getSerial_number(), 
					rentalForm.getEmployee_no(),
					user.getName(),
					rentalForm.getRental_date(),
					rentalForm.getReturn_date(),
					rentalForm.getInfo()
					);
		}

		return("redirect:device_list");
	}
	
	
	
}
