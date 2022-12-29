package com.example.kafka.repository;

import com.example.kafka.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByUid(Integer id);

    Task findByTaskId(Integer id);
}
