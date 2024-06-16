package service;

import model.Task;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TaskService {

    public List<Task> getAllTasks();

    public Task createTask(Task task);

    public Optional<Task> getTaskById(String id);

    public Task updateTask(String id, Task task);

    public void deleteTask(String id);

    Task updateTaskFields(String id, Map<String, Object> updates);
}
