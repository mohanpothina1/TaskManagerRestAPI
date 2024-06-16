package controller;


import NotFound.ResourceNotFoundException;
import model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.TaskService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task task) {
        return taskService.getTaskById(id)
                .map(existingTask -> ResponseEntity.ok(taskService.updateTask(id, task)))
                .orElse(ResponseEntity.notFound().build());
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Task> updateTaskFields(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        try {
            Task updatedTask = taskService.updateTaskFields(id, updates);
            return ResponseEntity.ok(updatedTask);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        if (taskService.getTaskById(id).isPresent()) {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
