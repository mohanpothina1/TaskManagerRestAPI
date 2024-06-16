package service;

import NotFound.ResourceNotFoundException;
import model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import repository.TaskRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }


    public Task updateTask(String id, Task task) {
        task.setId(id);
        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    public Task updateTaskFields(String id, Map<String, Object> updates) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            updates.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Task.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, task, value);
                }
            });
            return taskRepository.save(task);
        } else {
            throw new ResourceNotFoundException("Task not found with id " + id);
        }
    }

}
