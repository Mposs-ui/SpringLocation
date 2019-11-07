package springlocation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import springlocation.beans.Location;
import springlocation.repository.LocationRepository;

@Controller
public class WebController {
	@Autowired
	LocationRepository repo;
	
	@GetMapping("/viewAll")
	public String viewAllLocations(Model model) {
		model.addAttribute("location", repo.findAll());
		return "results";
	}
	
	@PostMapping("/inputContact")
	public String addNewContact(@ModelAttribute Location l, Model model) {
		repo.save(l);
		model.addAttribute("location", repo.findAll());
		return "results";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
	    Location l = repo.findById((long) id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    model.addAttribute("location", l);
	    return "update";
	}

	
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") int id, @Valid Location l, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        l.setId(id);
	        return "update";
	    }
	         
	    repo.save(l);
	    model.addAttribute("contacts", repo.findAll());
		return "results";
	}
	     
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id, Model model) {
	    Location l = repo.findById((long) id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    repo.delete(l);
	    model.addAttribute("contacts", repo.findAll());
		return "results";
	}
	
}
