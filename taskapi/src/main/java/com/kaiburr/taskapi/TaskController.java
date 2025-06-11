package com.kaiburr.taskapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepo;

    // GET all tasks or by ID
    @GetMapping
    public Object getTasks(@RequestParam(required = false) String id) {
        if (id != null) {
            return taskRepo.findById(id)
                    .<Object>map(task -> task)
                    .orElse("Task not found");
        }
        return taskRepo.findAll();
    }

    // PUT a task
    @PutMapping
    public String addTask(@RequestBody Task task) {
        taskRepo.save(task);
        return "Task saved";
    }

    // DELETE a task
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable String id) {
        taskRepo.deleteById(id);
        return "Task deleted";
    }

    // Search task by name
    @GetMapping("/search")
    public Object searchByName(@RequestParam String name) {
        List<Task> result = taskRepo.findByNameContainingIgnoreCase(name);
        return result.isEmpty() ? "No tasks found" : result;
    }
}
