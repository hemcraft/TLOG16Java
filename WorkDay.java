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

@lombok.Getter
@lombok.Setter
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
    
    /**
     * gets a member of the tasks
     * @param i
     * @return 
     */
    public Task getTasks(int i){
        return tasks.get(i);
    }
    
    /**
     * gets the list of tasks
     * @return 
     */
    public ArrayList<Task> getTaskList(){
        return tasks;
    }
    
    /**
     * calculates the sumPerDay
     * @throws EmptyTimeFieldException 
     */
    private void calculateSumPerDay() throws EmptyTimeFieldException{
        sumPerDay = 0;
        /*if(tasks.isEmpty())
            throw new EmptyTimeFieldException("empty");*/
        for(int i = 0; i < tasks.size(); i++){
            sumPerDay = sumPerDay + tasks.get(i).getMinPerTask();
        }
    }
    
    /**
     * getter
     * @return
     * @throws EmptyTimeFieldException 
     */
    public long getSumPerDay() throws EmptyTimeFieldException{
        calculateSumPerDay();
        return sumPerDay;
    }
    
    /**
     * gets the extra minutes done this day
     * @return
     * @throws EmptyTimeFieldException 
     */
    public long getExtraMinPerDay() throws EmptyTimeFieldException{
        return (getSumPerDay() - requiredMinPerDay);
    }
    
    /**
     * sets requiredMinPerDay if it's greater than zero
     * @param min
     * @throws NegativeMinutesOfWorkException 
     */
    public void setRequiredMinPerDay(long min) throws NegativeMinutesOfWorkException{
        if(min < 0)
            throw new NegativeMinutesOfWorkException("can't be negative");
        requiredMinPerDay = min;
    }
    
    /**
     * sets actualDay 
     * @param year
     * @param month
     * @param day
     * @throws FutureWorkException thrown if future date is given
     */
    public void setActualDay(int year, int month, int day) throws FutureWorkException{
        if(LocalDate.of(year, month, day).isAfter(LocalDate.now()))
            throw new FutureWorkException("can't be in the future");
        actualDay = LocalDate.of(year, month, day);
    }
    
    /**
     * adds task to the list of tasks if it doesn't have common time interval with the other tasks
     * @param t
     * @throws NotSeparatedTimesException 
     */
    public void addTask(Task t) throws NotSeparatedTimesException{
        if(Util.isSeparatedTime(t, this.tasks))
            tasks.add(t);
        else{
            throw new NotSeparatedTimesException("tasks have common parts");
        }
    }
    
    /**
     * gets the endTime of the task last given
     * @return 
     */
    public LocalTime latestTaskEndTime(){
        if(!tasks.isEmpty())
            return tasks.get(tasks.size() - 1).getEndTime();
        return null;
    }
    
    /**
     * writes tasks to te console
     */
    public void writeTasks(){
        for(int i = 0; i < tasks.size(); i++){
            System.out.println(i + ": " + tasks.get(i).toString());
        }
    }
    
    /**
     * lists infinished tasks
     */
    public void listUnfinishedTasks(){
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).isUnfinished()){
                System.out.println(i + ": " + tasks.get(i).toString());
            }
        }
    }
}
