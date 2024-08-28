package sus.project.rentalSystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import sus.project.rentalSystem.form.DeviceForm;
import sus.project.rentalSystem.service.DeviceService;

@Controller
public class DeviceController {

	@Autowired
	DeviceService deviceService;
	
	@GetMapping("/device_list")
	public String device_list(Model model) {
		model.addAttribute("devices", deviceService.findAll(true));
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
	public String addDevice(@Valid @ModelAttribute("deviceForm") DeviceForm deviceForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		List<String> errorList = new ArrayList<String>();
		if(result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
			return "device_register";
		}
		Optional<Device> device = deviceService.findById(deviceForm.getSerial_number());
		if(device.isPresent()) {
			//if device exists display message
			redirectAttributes.addFlashAttribute("message", "資産番号が存在しています");
			redirectAttributes.addFlashAttribute("alertType", "alert-warning");
			redirectAttributes.addFlashAttribute("deviceForm", deviceForm);
			return "redirect:device_register";
		}

		deviceService.save(
				deviceForm.getSerial_number(), 
				deviceForm.getMaker(), 
				deviceForm.getMemory(), 
				deviceForm.getCapacity(), 
				deviceForm.isGpu(), 
				deviceForm.getLocation(), 
				deviceForm.getLease_start_date(), 
				deviceForm.getLease_end_date(), 
				deviceForm.getInventory_date(), 
				deviceForm.getInfo());
		redirectAttributes.addFlashAttribute("message", "機器を登録しました");
		redirectAttributes.addFlashAttribute("alertType", "alert-success");
		return("redirect:device_list");
	}
	
	@PostMapping("editDevice")
	public String editDevice(@Valid @ModelAttribute DeviceForm deviceForm, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "redirect:device_list";
		}
		Optional<Device> device = deviceService.findById(deviceForm.getSerial_number());
		if(device.isPresent()) {
			deviceService.update(
					deviceForm.getSerial_number(), 
					deviceForm.getMaker(), 
					deviceForm.getMemory(), 
					deviceForm.getCapacity(), 
					deviceForm.isGpu(), 
					deviceForm.getLocation(), 
					deviceForm.isMalfunction(),
					deviceForm.getLease_start_date(), 
					deviceForm.getLease_end_date(), 
					deviceForm.getInventory_date(), 
					deviceForm.getInfo());
			return("redirect:device_list");
		}
		return "redirect:device_list";
	}
	
	@PostMapping("deleteDevice")
	public String deleteDevice(@RequestParam("serial_number") String serial_number, RedirectAttributes redirectAttributes) {
		Optional<Device> optionalDevice = deviceService.findById(serial_number);
		if(optionalDevice.isPresent()) {
			Device device = optionalDevice.get();
			if(!device.isAvailable()) {
				//if in rental then refuse to delete
				redirectAttributes.addFlashAttribute("message", "貸出中のため削除できません");
				redirectAttributes.addFlashAttribute("alertType", "alert-danger");
				return "redirect:/device_info?id=" + device.getSerial_number();
			}
			deviceService.delete(serial_number);
			redirectAttributes.addFlashAttribute("message", "機器を削除しました");
			redirectAttributes.addFlashAttribute("alertType", "alert-warning");
			return("redirect:device_list");
		}
		return("redirect:device_list");
		
	}
}
