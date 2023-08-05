package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.model.Task;
import com.examly.springapp.repository.TaskRepository;

@RestController
@RequestMapping("")

public class TaskController {

  @Autowired
  private TaskRepository taskRepository;

  @GetMapping("/alltasks")
  public List<Task> getallTasks()
  {
    return taskRepository.findAll();
  }
  
  @PostMapping("/saveTask")
  public Task createTask(@RequestBody Task task)
  {
    return taskRepository.save(task);
  }

  @GetMapping("/getTask")
  public ResponseEntity<Task> getTaskById(@RequestParam Long id)
  {
    Task task = taskRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task not found :"+id));
    return ResponseEntity.ok(task);
  }

  @GetMapping("/changeStatus")
  public ResponseEntity<Task> updateTask(@RequestParam Long id, @RequestBody Task Taskdetails)
  {
    Task task = taskRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task not found :"+id));
    task.setTaskStatus(Taskdetails.getTaskStatus());

    Task updatedtask = taskRepository.save(task);
    return ResponseEntity.ok(updatedtask);

  }

  @GetMapping("/deleteTask")
  public String deleteTaskById(@RequestParam Long id)
  {
    Task task = taskRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Task not found :"+id));
    taskRepository.delete(task);
    return "deleted sucessfully";
  }


}