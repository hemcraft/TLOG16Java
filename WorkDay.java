/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andris
 */
public class WorkDay {
    ArrayList<Task> tasks;
    long requiredMinPerDay = 450;
    LocalDate actualDay = LocalDate.now();
    long sumPerDay;
    
    WorkDay(long requiredMinPerDay, LocalDate actualDay){
        this.requiredMinPerDay = requiredMinPerDay * 60;
        this.actualDay = actualDay;
        tasks = new ArrayList<Task>();
    }
    
    WorkDay(long requiredMinPerDay){
        this.requiredMinPerDay = requiredMinPerDay * 60;
        this.actualDay = LocalDate.now();
        tasks = new ArrayList<Task>();
    }
    
    WorkDay(){
        this.requiredMinPerDay = 450;
        this.actualDay = LocalDate.now();
        tasks = new ArrayList<Task>();
    }
    
    Task getTasks(int i){
        return tasks.get(i);
    }
    
    void calculateSumPerDay(){
        for(int i = 0; i < tasks.size(); i++){
            sumPerDay += tasks.get(i).getMinPerTask();
        }
    }
    
    long getRequiredMinPerDay(){
        return requiredMinPerDay;
    }
    
    LocalDate getActualDay(){
        return actualDay;
    }
    
    long getSumPerDay(){
        calculateSumPerDay();
        return sumPerDay;
    }
    
    public long getExtraMinPerDay(){
        return (requiredMinPerDay - sumPerDay);
    }
    
    void setRequiredMinPerDay(long min){
        requiredMinPerDay = min;
    }
    
    void setActualDay(int year, int month, int day){
        actualDay = LocalDate.of(year, month, day);
    }
    
    void addTask(Task t){
        //if(Util.isMultipleQuarterHour(t)){
        if(true)
            tasks.add(t);
        //}else{
            
        //}
    }
    
    public LocalTime latestTaskEndTime(){
        return tasks.get(tasks.size() - 1).getEndTime();
    }
    
    public void writeTasks(){
        for(int i = 0; i < tasks.size(); i++){
            System.out.println(i + ": " + tasks.get(i).toString());
        }
    }
    
    public void listUnfinishedTasks(){
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).isUnfinished()){
                System.out.println(i + ": " + tasks.get(i).toString());
            }
        }
    }
}
