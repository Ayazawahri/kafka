package com.example.kafka.kafka;

import com.example.kafka.model.Task;
import com.example.kafka.model.User;
import com.example.kafka.repository.TaskRepository;
import com.example.kafka.repository.UserRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JsonConsumer {

    private UserRepository userRepository;
    private TaskRepository taskRepository;

    public JsonConsumer(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonConsumer.class);



   @KafkaListener(topics ="javat_json",groupId = "group")
    public void consume(User user){
        LOGGER.info(String.format("User message received %s",user.toString()));


        userRepository.save(user);

    }

    @KafkaListener(topics ="task_json",groupId = "group")
    public void consumeTask(Task task){
       User u = userRepository.findUserById(task.getUid());
       task.setUser(u);
        LOGGER.info(String.format("Task received %s",task.toString()));

       taskRepository.save(task);

    }

    @KafkaListener(topics ="task_json",groupId = "group")
    public List<Task> getTask(Integer id){
       return taskRepository.findAllByUid(id);

    }

    @KafkaListener(topics ="javat",groupId = "group")
    public void updateTask(String msg) throws Exception {
        JSONObject jsonObject = new JSONObject(msg);
        Integer id = Integer.parseInt(jsonObject.getString("id"));
        String status = jsonObject.getString("status");
        LOGGER.info(String.format("User message received %s %s",id,status));
        Task t = taskRepository.findByTaskId(id);
        t.setStatus(status);
        taskRepository.save(t);
    }
}
