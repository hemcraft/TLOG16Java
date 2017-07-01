/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.LocalDate;
import java.time.Month;
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
        if(Util.isMultipleQuarterHour(t)){
            tasks.add(t);
        }else{
            
        }
    }
}
