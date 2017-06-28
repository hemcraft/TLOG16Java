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
    
    long getMinPerTask(){
        return endTime.getMinute() - startTime.getMinute();
    }
    
    boolean isValidTaskId(){
        if(taskId.length() == 4){
            return taskId.matches("\\d\\d\\d\\d");
        }else{
            return taskId.matches("LT-\\d\\d\\d\\d");
        }
    }
    
    boolean isMultipleQuarterHour(){
        if(getMinPerTask() % 15 == 0) return true;
        
        return false;
    }
}
