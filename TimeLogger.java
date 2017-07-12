/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import timelogger.exceptions.EmptyTimeFieldException;
import timelogger.exceptions.InvalidTaskIdException;
import timelogger.exceptions.NoTaskIdException;
import timelogger.exceptions.NotExpectedTimeOrderException;
import timelogger.exceptions.NotNewMonthException;
import timelogger.exceptions.NotSeparatedTimesException;

/**
 *
 * @author Andris
 */
public class TimeLogger {
    private ArrayList<WorkMonth> months;
    
    public TimeLogger() {
        months = new ArrayList<WorkMonth>();
    }
    
    public WorkMonth getWorkMonth(int i){
        return months.get(i);
    }
    
    public int getSize(){
        return months.size();
    }
    
    public ArrayList<WorkMonth> getMonthList(){
        return months;
    }
    
    public boolean isNewMonth(WorkMonth wm){
        for(int i = 0; i < months.size(); i++){
            if(wm.getDate().getYear() == months.get(i).getDate().getYear()){
                if(wm.getDate().getMonthValue() == months.get(i).getDate().getMonthValue())
                    return false;
            }
        }
        return true;
    }
    
    public void addMonth(WorkMonth wm) throws NotNewMonthException{
        if(isNewMonth(wm))
            months.add(wm);
        else
            throw new NotNewMonthException("not new month");
    }
    
    public void listMonths(){
            for(int j = 0; j < getSize(); j++){
                System.out.println(j + ": " + getWorkMonth(j).getDate().toString() + ",");
            }
        }
    
    public void listDays(int i){
        months.get(i).writeDays();
    }
    
    public void listTasks(int i, int j){
        months.get(i).getDayList().get(j).writeTasks();
    }
    
    public void addTask(int whichMonth, int whichDay, String taskId, String comment, String startTime) throws EmptyTimeFieldException, InvalidTaskIdException, NoTaskIdException, NotSeparatedTimesException, NotExpectedTimeOrderException{
        Task t = new Task(taskId);
        t.setComment(comment);
        t.setStartTime(startTime);
        if(!months.get(whichMonth).getDayList().get(whichDay).getTaskList().isEmpty())
            System.out.println(months.get(whichMonth).getDayList().get(whichDay).latestTaskEndTime());
        months.get(whichMonth).getDayList().get(whichDay).addTask(t);
    }
    
    public void finishTask(int whichMonth, int whichDay, int whichTask, String endTime) throws NotExpectedTimeOrderException, EmptyTimeFieldException{
        Task temp = months.get(whichMonth).getDayList().get(whichDay).getTaskList().get(whichTask);
        temp.setEndTime(endTime);
    }
    
    public void showUnfinishedTasks(int whichMonth, int whichDay){
        months.get(whichMonth).getDayList().get(whichDay).listUnfinishedTasks();
    }
    
    public void deleteTask(int whichMonth, int whichDay, int whichTask){
        months.get(whichMonth).getDayList().get(whichDay).getTaskList().remove(whichTask);
    }
    
    public void setTaskFields(int whichMonth, int whichDay, int whichTask) throws NotExpectedTimeOrderException, InvalidTaskIdException, EmptyTimeFieldException, NoTaskIdException{
        Scanner sc = new Scanner(System.in);
        Task t = months.get(whichMonth).getDayList().get(whichDay).getTaskList().get(whichTask);
        System.out.println("What is the taskId?");
        System.out.println("[" + t.getTaskId() + "]");
        String taskId = sc.nextLine();
        if(!taskId.isEmpty())
            t.setTaskId(taskId);
        System.out.println("What does the task do?");
        System.out.println("[" + t.getComment() + "]");
        String comment = sc.nextLine();
        if(!comment.isEmpty())
            t.setComment(comment);
        System.out.println("When does the task start?");
        System.out.println("[" + t.getStartTime() + "]");
        String startTime = sc.nextLine();
        if(!startTime.isEmpty())
            t.setStartTime(startTime);
        System.out.println("When does the task end?");
        System.out.println("[" + t.getEndTime() + "]");
        String endTime = sc.nextLine();
        if(!endTime.isEmpty())
            t.setEndTime(endTime);
            
    }
    
    public void writeStatistics(int whichMonth) throws EmptyTimeFieldException{
        long sumPerMonth = months.get(whichMonth).getSumPerMonth();
        long requiredMinPerMonth = months.get(whichMonth).getRequiredMinPerMonth();
        System.out.println("Statistics: sumPerMonth: " 
                + sumPerMonth 
                + ", requiredMinPerMonth: " 
                + requiredMinPerMonth);
        
        System.out.println();
        for(int i = 0; i < months.get(whichMonth).getDayList().size(); i++){
            System.out.println(i + " : " + " requiredMinsPerDay " 
                    +  months.get(whichMonth).getDayList().get(i).getRequiredMinPerDay()
                    + " sumPerDay: " 
                    + months.get(whichMonth).getDayList().get(i).getSumPerDay());
        }
    }
}
