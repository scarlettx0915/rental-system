package sus.project.rentalSystem.controller;

import java.time.LocalDate;
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
import sus.project.rentalSystem.entity.Device;
import sus.project.rentalSystem.entity.Rental;
import sus.project.rentalSystem.entity.User;
import sus.project.rentalSystem.form.RentalForm;
import sus.project.rentalSystem.form.UserForm;
import sus.project.rentalSystem.service.DeviceService;
import sus.project.rentalSystem.service.RentalService;
import sus.project.rentalSystem.service.UserService;

@Controller
public class RentalController {
	
	@Autowired
	RentalService rentalService;
	@Autowired
	UserService userService;
	@Autowired
	DeviceService deviceService;
	
	@GetMapping("/rental_list")
	public String rental_list(Model model) {
		model.addAttribute("rentals", rentalService.findAll(true));
		LocalDate today = LocalDate.now();
		model.addAttribute("today", today);
		return "rental_list";
	}
	
	
	@GetMapping("/rental_info")
	public String rental_info(@RequestParam("id")String id, Model model) {
		Optional<Rental> rental = rentalService.findById(id);
		if(rental.isPresent()) {
			model.addAttribute("rental",rental.get());
			return "rental_info";
		}
		return "redirect:rental_list";
	}
	
	@GetMapping("/rental_register")
	public String rental_register(@RequestParam("id")String id, Model model) {
		model.addAttribute("serial_number",id);
		model.addAttribute("users", userService.findAll(true));
		if (!model.containsAttribute("rentalForm")) {
	        model.addAttribute("rentalForm", new RentalForm());
	    }
		return "rental_register";
	}
	
	@GetMapping("/rental_edit")
	public String rental_edit(@RequestParam("id")String id, Model model) {
		Optional<Rental> optionalRental = rentalService.findById(id);
		if(optionalRental.isPresent()) {
			model.addAttribute("rental",optionalRental.get());
			model.addAttribute("users", userService.findAll(true));
			return "rental_edit";
		}
		return "redirect:device_list";	}
	
	@PostMapping("addRental")
	public String addRental(@Valid @ModelAttribute RentalForm rentalForm, BindingResult result, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			List<String> errorList = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
			 redirectAttributes.addFlashAttribute("validationErrors", errorList);
			 redirectAttributes.addFlashAttribute("rentalForm", rentalForm);
			 
			return ("redirect:rental_register?id=" + rentalForm.getSerial_number());
		}
		
		Optional<Rental> optionalRental = rentalService.findById(rentalForm.getSerial_number());
		Optional<Device> optionalDevice = deviceService.findById(rentalForm.getSerial_number());
		Optional<User> optionalUser = userService.findById(rentalForm.getEmployee_no());
		
		if(optionalRental.isPresent()) {
			//if rental exists display message
			redirectAttributes.addFlashAttribute("message", "この機器は貸出中です");
			redirectAttributes.addFlashAttribute("alertType", "alert-danger");
			return "redirect:rental_list";
		}
		
		if(optionalUser.isPresent() && optionalDevice.isPresent()) {
			User user = optionalUser.get();
			rentalService.save(
					rentalForm.getSerial_number(), 
					rentalForm.getEmployee_no(),
					user.getName(),
					rentalForm.getRental_date(),
					rentalForm.getReturn_date(),
					rentalForm.getInfo()
					);
			deviceService.setAvailability(rentalForm.getSerial_number(), false);
			redirectAttributes.addFlashAttribute("message", "貸出登録しました");
			redirectAttributes.addFlashAttribute("alertType", "alert-success");
			return "redirect:rental_list";
		}
		return "redirect:rental_list";
	}
	
	@PostMapping("editRental")
	public String editRental(@Valid @ModelAttribute RentalForm rentalForm, BindingResult result, RedirectAttributes redirectAttributes){
		if(result.hasErrors()) {
			return "rental_list";
		}
		Optional<Rental> optionalRental = rentalService.findById(rentalForm.getSerial_number());
		Optional<User> optionalUser = userService.findById(rentalForm.getEmployee_no());
		if(optionalRental.isPresent() && optionalUser.isPresent()) {
			User user = optionalUser.get();
			rentalService.save(rentalForm.getSerial_number(), 
					rentalForm.getEmployee_no(),
					user.getName(),
					rentalForm.getRental_date(),
					rentalForm.getReturn_date(),
					rentalForm.getInfo());
			redirectAttributes.addFlashAttribute("message", "貸出情報を編集しました");
			redirectAttributes.addFlashAttribute("alertType", "alert-primary");
			return("redirect:rental_list");
		}
		return("redirect:rental_list");
	}
	
	@PostMapping("returnRental")
	public String returnRental(@RequestParam("serial_number") String serial_number, RedirectAttributes redirectAttributes) {
		Optional<Device> optionalDevice = deviceService.findById(serial_number);
		Optional<Rental> optionalRental = rentalService.findById(serial_number);
		if(optionalDevice.isPresent() && optionalRental.isPresent()) {
			rentalService.delete(serial_number);
			redirectAttributes.addFlashAttribute("message", "機器を返却しました");
			redirectAttributes.addFlashAttribute("alertType", "alert-success");
			return("redirect:rental_list");
		}
		return("redirect:rental_list");
		
	}

	
}
