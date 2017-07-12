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
import timelogger.exceptions.EmptyTimeFieldException;
import timelogger.exceptions.FutureWorkException;
import timelogger.exceptions.NegativeMinutesOfWorkException;
import timelogger.exceptions.NotSeparatedTimesException;
import static tlog16java.Util.isSeparatedTime;

/**
 *
 * @author Andris
 */
public class WorkDay {
    private ArrayList<Task> tasks;
    private long requiredMinPerDay = 450;
    private LocalDate actualDay = LocalDate.now();
    private long sumPerDay;
    
    public WorkDay(long requiredMinPerDay, LocalDate actualDay) throws NegativeMinutesOfWorkException, FutureWorkException{
        if(requiredMinPerDay < 0)
            throw new NegativeMinutesOfWorkException("can't be negative");
        if(actualDay.isAfter(LocalDate.now()))
            throw new FutureWorkException("can't be in the future");
        this.requiredMinPerDay = requiredMinPerDay;
        this.actualDay = actualDay;
        tasks = new ArrayList<Task>();
    }
    
    public WorkDay(long requiredMinPerDay) throws NegativeMinutesOfWorkException{
        if(requiredMinPerDay < 0)
            throw new NegativeMinutesOfWorkException("can't be negative");
        this.requiredMinPerDay = requiredMinPerDay;
        this.actualDay = LocalDate.now();
        tasks = new ArrayList<Task>();
    }
    
    public WorkDay(){
        this.requiredMinPerDay = 450;
        this.actualDay = LocalDate.now();
        tasks = new ArrayList<Task>();
    }
    
    public Task getTasks(int i){
        return tasks.get(i);
    }
    
    public ArrayList<Task> getTaskList(){
        return tasks;
    }
    
    private void calculateSumPerDay() throws EmptyTimeFieldException{
        sumPerDay = 0;
        /*if(tasks.isEmpty())
            throw new EmptyTimeFieldException("empty");*/
        for(int i = 0; i < tasks.size(); i++){
            sumPerDay = sumPerDay + tasks.get(i).getMinPerTask();
        }
    }
    
    public long getRequiredMinPerDay(){
        return requiredMinPerDay;
    }
    
    public LocalDate getActualDay(){
        return actualDay;
    }
    
    public long getSumPerDay() throws EmptyTimeFieldException{
        calculateSumPerDay();
        return sumPerDay;
    }
    
    public long getExtraMinPerDay() throws EmptyTimeFieldException{
        return (getSumPerDay() - requiredMinPerDay);
    }
    
    public void setRequiredMinPerDay(long min) throws NegativeMinutesOfWorkException{
        if(min < 0)
            throw new NegativeMinutesOfWorkException("can't be negative");
        requiredMinPerDay = min;
    }
    
    public void setActualDay(int year, int month, int day) throws FutureWorkException{
        if(LocalDate.of(year, month, day).isAfter(LocalDate.now()))
            throw new FutureWorkException("can't be in the future");
        actualDay = LocalDate.of(year, month, day);
    }
    
    public void addTask(Task t) throws NotSeparatedTimesException{
        if(Util.isSeparatedTime(t, this.tasks))
            tasks.add(t);
        else{
            throw new NotSeparatedTimesException("tasks have common parts");
        }
    }
    
    public LocalTime latestTaskEndTime(){
        if(!tasks.isEmpty())
            return tasks.get(tasks.size() - 1).getEndTime();
        return null;
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
