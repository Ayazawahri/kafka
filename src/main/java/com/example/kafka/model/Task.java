package com.example.kafka.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Entity
@Table(name = "task_T")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;


    private String title;
    private String description;

    private int uid;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Task(String title, String description, int uid) {
        this.title = title;
        this.description = description;
        this.uid = uid;
    }

    public Task( String title, String description, int uid, User user) {

        this.title = title;
        this.description = description;
        this.uid = uid;
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;


    public Status getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(status == null) this.status=Status.TO_DO;
        else this.status = Status.valueOf(status.toUpperCase());;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task() {
    }



    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Task{" +
                "id=" + taskId +
                ", title='" + title + '\'' +
                ", Description='" + description + '\'' +
                '}';
    }
}
