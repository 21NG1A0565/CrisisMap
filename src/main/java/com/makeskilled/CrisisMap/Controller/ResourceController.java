package com.makeskilled.CrisisMap.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.makeskilled.CrisisMap.Entity.Resource;
import com.makeskilled.CrisisMap.Repository.ResourceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ResourceController {

    @Autowired
    private ResourceRepository resourceRepository;

    @PostMapping("/resources/add")
    public String addResource(@RequestParam String resourceName,
                              @RequestParam int quantity,
                              @RequestParam String location,
                              @RequestParam(required = false) String description) {
        // Create and save resource
        Resource resource = new Resource();
        resource.setResourceName(resourceName);
        resource.setQuantity(quantity);
        resource.setLocation(location);
        resource.setDescription(description);

        resourceRepository.save(resource);

        return "redirect:/resources"; // Redirect to a resources list or dashboard
    }

    @GetMapping("/addResource")
    public String addResourcePage() {
        return "addResource";
    }

    @GetMapping("/resources")
    public String viewResources(Model model) {
        List<Resource> resources = resourceRepository.findAll();
        model.addAttribute("resources", resources);
        return "viewResources"; // Create a resourceList.html file to display resources
    }

    @GetMapping("/ngoResources")
    public String viewResources1(Model model) {
        List<Resource> resources = resourceRepository.findAll();
        model.addAttribute("resources", resources);
        return "ngoResources"; // Create a resourceList.html file to display resources
    }

    @GetMapping("/resources/edit/{id}")
    public String editResource(@PathVariable Long id, Model model) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid resource ID: " + id));
        model.addAttribute("resource", resource);
        return "editResource"; // Display the editResource.html form
    }

    @PostMapping("/resources/update")
    public String updateResource(@RequestParam Long id,
                                 @RequestParam String resourceName,
                                 @RequestParam int quantity,
                                 @RequestParam String location,
                                 @RequestParam(required = false) String description) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid resource ID: " + id));

        resource.setResourceName(resourceName);
        resource.setQuantity(quantity);
        resource.setLocation(location);
        resource.setDescription(description);

        resourceRepository.save(resource);
        return "redirect:/resources"; // Redirect back to the resource list
    }

    @GetMapping("/resources/edit1/{id}")
    public String editResource1(@PathVariable Long id, Model model) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid resource ID: " + id));
        model.addAttribute("resource", resource);
        return "ngoEditResource"; // Display the editResource.html form
    }

    @PostMapping("/resources/update1")
    public String updateResource1(@RequestParam Long id,
                                 @RequestParam String resourceName,
                                 @RequestParam int quantity,
                                 @RequestParam String location,
                                 @RequestParam(required = false) String description) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid resource ID: " + id));

        resource.setResourceName(resourceName);
        resource.setQuantity(quantity);
        resource.setLocation(location);
        resource.setDescription(description);

        resourceRepository.save(resource);
        return "redirect:/ngoResources"; // Redirect back to the resource list
    }

    // Delete Resource
    @GetMapping("/resources/delete/{id}")
    public String deleteResource(@PathVariable Long id) {
        resourceRepository.deleteById(id);
        return "redirect:/resources"; // Redirect back to the resource list
    }
    
}
