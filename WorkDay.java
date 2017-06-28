/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Andris
 */
public class WorkDay {
    List<Task> tasks;
    long requiredMinPerDay = 450;
    LocalDate actualDay = LocalDate.now();
    long sumPerDay;
    
    WorkDay(long requiredMinPerDay, LocalDate actualDay){
        this.requiredMinPerDay = requiredMinPerDay;
        this.actualDay = actualDay;
    }
    
    WorkDay(long requiredMinPerDay){
        this.requiredMinPerDay = requiredMinPerDay;
        this.actualDay = LocalDate.now();
    }
    
    WorkDay(){
        this.requiredMinPerDay = 450;
        this.actualDay = LocalDate.now();
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
        return sumPerDay;
    }
    
    long getExtraMinPerDay(){
        return (requiredMinPerDay - sumPerDay);
    }
    
    boolean isSeparatedTime(Task t){
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getMinPerTask() == getExtraMinPerDay())
                return true;
        }
        return false;
    }
    
    void addTask(Task t){
        if(t.isMultipleQuarterHour()){
            tasks.add(t);
        }else{
            
        }
    }
    
    boolean isWeekday(){
        if(actualDay.getDayOfWeek().getValue() < 6){
            return true;
        }else{
            return false;
        }
    }
}
