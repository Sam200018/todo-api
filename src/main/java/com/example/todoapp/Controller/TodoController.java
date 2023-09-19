package com.example.todoapp.Controller;

import com.example.todoapp.Model.Task;
import com.example.todoapp.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/")
    public String holamundo() {
        return "Hola mundo!!";
    }

    @GetMapping(value = "/tasks")
    public List<Task> getTasks() {
        return todoRepository.findAll();
    }

    @PostMapping("/saveTask")
    public String saveTask(@RequestBody Task task) {
        todoRepository.save(task);
        return "Saved task";
    }

    @PutMapping("/updateTask/{id}")
    public String updateTask(@PathVariable long id, @RequestBody Task taks) {
        Task updatedTask = todoRepository.findById(id).get();
        updatedTask.setTitle(taks.getTitle());
        updatedTask.setDescription(taks.getDescription());

        todoRepository.save(updatedTask);

        return "Updated task";

    }

    @DeleteMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable long id) {
        Task deletedTask = todoRepository.findById(id).get();
        todoRepository.delete(deletedTask);
        return "Task deleted";
    }
}
