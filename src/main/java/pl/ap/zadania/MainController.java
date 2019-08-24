package pl.ap.zadania;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private TaskRepository taskRepository;

    public MainController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")

    public String main(Model model) {

        List<Task> taskList = taskRepository.notReadyTasks();
        List<Task> tasksDone = taskRepository.readyTask();
        model.addAttribute("tasks", taskList);
        model.addAttribute("tasksDone", tasksDone);
        model.addAttribute("newTask", new Task());
        return "index";
    }

    @GetMapping("/done")

    public String done(@RequestParam Long id) {
        Task task = taskRepository.findById(id);
        task.setDone(true);
        taskRepository.save(task);
        return "redirect:/";
    }


    @PostMapping("/add")
    public String add(Task task){
        taskRepository.save(task);
        return "redirect:/";
    }



}
