package com.wojciechandrzejczak.to_do_rest;

import jakarta.persistence.*;

@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "id")
    Integer Id;
    @Column(name = "name")
    String name;
    @Column(name = "status")
    String status;

    public Task(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public Task(Integer id, String name, String status) {
        Id = id;
        this.name = name;
        this.status = status;
    }

    public Task(){}

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
