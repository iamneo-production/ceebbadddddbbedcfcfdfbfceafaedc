package com.examly.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.Model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
  
}