package com.makeskilled.CrisisMap.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.makeskilled.CrisisMap.Entity.Status;
import com.makeskilled.CrisisMap.Entity.Task;
import com.makeskilled.CrisisMap.Entity.User;
import com.makeskilled.CrisisMap.Repository.TaskRepository;
import com.makeskilled.CrisisMap.Repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/tasks/assign")
    public String assignTaskForm(Model model) {
        // Fetch users with the NGO role
        List<User> ngos = userRepository.findByRole("ngo");
        model.addAttribute("ngos", ngos);
        return "assignTask"; // Render assignTask.html
    }

    @PostMapping("/tasks/assign")
    public String assignTask(@RequestParam String taskName,
                             @RequestParam String description,
                             @RequestParam Long ngoId) {
        User assignedNgo = userRepository.findById(ngoId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid NGO ID: " + ngoId));

        Task task = new Task();
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setAssignedNgo(assignedNgo);
        task.setStatus(Status.PENDING);

        taskRepository.save(task);

        return "redirect:/viewTasks"; // Redirect to task list
    }

    @PostMapping("/tasks/update-status")
    public String updateTaskStatus(@RequestParam Long taskId, @RequestParam Status status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task ID: " + taskId));

        task.setStatus(status);
        taskRepository.save(task);

        return "redirect:/viewTasks";
    }

    @GetMapping("/viewTasks")
    public String viewTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll()); // Fetch all tasks
        return "taskList"; // Create a taskList.html to display tasks
    }

    @GetMapping("/assignTask")
    public String assignTaskPage(Model model) {
        List<User> ngos = userRepository.findByRole("ngo");
        model.addAttribute("ngos", ngos);
        return "assignTask";
    }

    @GetMapping("/ngoTasks")
    public String ngoTasksPage(HttpSession session,Model model) {
        String loggedInUser = (String) session.getAttribute("username");
        List<Task> tasks = taskRepository.findByAssignedNgo_Username(loggedInUser);
        model.addAttribute("tasks", tasks);
        return "ngoTasks";
    }
    
    @PostMapping("/tasks/update-status-ngo")
    public String updateTaskStatusByNGO(@RequestParam Long taskId, @RequestParam Status status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Task ID: " + taskId));

        task.setStatus(status);
        taskRepository.save(task);

        return "redirect:/ngoTasks";
    }
}
