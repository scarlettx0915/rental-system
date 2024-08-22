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
import sus.project.rentalSystem.form.DeviceForm;
import sus.project.rentalSystem.service.DeviceService;

@Controller
public class DeviceController {

	@Autowired
	DeviceService deviceService;
	
	@GetMapping("/device_list")
	public String device_list(Model model) {
		model.addAttribute("devices", deviceService.findAll());
		return "device_list";
	}
	
	@GetMapping("/device_info")
	public String device_info(@RequestParam("id")String id, Model model) {
		Optional<Device> device = deviceService.findById(id);
		if(device.isPresent()) {
			model.addAttribute("device",device.get());
			return "device_info";
		}
		return "redirect:device_list";
	}
	
	@GetMapping("/device_register")
	public String device_register() {
		return "device_register";
	}
	
	@GetMapping("/device_edit")
	public String device_edit(@RequestParam("id")String id, Model model) {
		Optional<Device> device = deviceService.findById(id);
		if(device.isPresent()) {
			model.addAttribute("device",device.get());
			return "device_edit";
		}
		return "redirect:device_list";
	}
	
	@PostMapping("addDevice")
	public String addDevice(@Valid @ModelAttribute DeviceForm deviceForm, BindingResult result) {
		if(result.hasErrors()) {
			return "device_register";
		}
		Optional<Device> device = deviceService.findById(deviceForm.getSerial_number());
		if(device.isPresent()) {
			//if device exists display message
			return "device_register";
		}

		deviceService.save(deviceForm.getSerial_number(), deviceForm.getMaker(), deviceForm.getMemory(), deviceForm.getCapacity(), deviceForm.isGpu(), 
				deviceForm.getLocation(), deviceForm.getLease_start_date(), deviceForm.getLease_end_date(), deviceForm.getInventory_date(), deviceForm.getInfo());
		return("redirect:device_list");
	}
}
