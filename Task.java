/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.LocalTime;

/**
 *
 * @author Andris
 */
public class Task {
    String taskId;
    LocalTime startTime;
    LocalTime endTime;
    String comment;
    
    Task(String taskId, int startHour, int startMin, int endHour, int endMin, String comment){
        this.taskId = taskId;
        this.comment = comment;
        this.startTime.of(startHour, startMin);
        this.endTime.of(endHour, endMin);
    }
    
    Task(String taskId, String startTime, String endTime, String comment){
        this.taskId = taskId;
        this.comment = comment;
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
    }
    
    Task(String taskId){
        this.taskId = taskId;
    }
        
    String getTaskId(){
        return taskId;
    }
    
    LocalTime getStartTime(){
        return startTime;
    }
    
    LocalTime getEndTime(){
        return endTime;
    }
    
    String getComment(){
        return comment;
    }
    
    public long getMinPerTask(){
        return endTime.getMinute() - startTime.getMinute();
    }
    
    void setTaskId(String taskid){
        this.taskId = taskid;
    }
    
    void setComment(String comment){
        this.comment = comment;
    }
    
    void setStartTime(int hour, int min){
        this.startTime.of(hour, min);
    }
    
    void setStartTime(String time){
        this.startTime = LocalTime.parse(time);
    }
    
    void setStartTime(LocalTime time){
        this.startTime = time;
    }
    
    void setEndTime(int hour, int min){
        this.endTime.of(hour, min);
    }
    
    void setEndTime(String time){
        this.endTime = LocalTime.parse(time);
    }
    
    void setEndTime(LocalTime time){
        this.endTime = time;
    }
    
    boolean isValidTaskId(){
        return isValidRedMineTaskId() || isValidLTTaskId();
    }
    
    boolean isValidRedMineTaskId(){
        return taskId.matches("\\d\\d\\d\\d");
    }
    
    boolean isValidLTTaskId(){
        return taskId.matches("LT-\\d\\d\\d\\d");
    }
    
    public boolean commonParts(Task t){
        return !(this.getStartTime().isAfter(t.getEndTime())  || this.getEndTime().isBefore(t.getStartTime()));
    }
    
    public String toString(){
        String temp = taskId + " : " + comment + " starts at: " + startTime + " ends at: " + endTime;
        return temp;
    }
    
    public boolean isUnfinished(){
        return (endTime == null);
    }
}
