package com.example.demo.model;

public class TaskDTO {
    String taskID;
    String name;
    String assignee;

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public TaskDTO(String taskID, String name, String assignee) {
        this.taskID = taskID;
        this.name = name;
        this.assignee = assignee;
    }
}
