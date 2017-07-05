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

/**
 *
 * @author Andris
 */
public class TimeLogger {
    ArrayList<WorkMonth> months;
    
    TimeLogger() {
        months = new ArrayList<WorkMonth>();
    }
    
    WorkMonth getWorkMonth(int i){
        return months.get(i);
    }
    
    int getSize(){
        return months.size();
    }
    
    boolean isNewMonth(WorkMonth wm){
        if(months.contains(wm))
            return false;
        else
            return true;
    }
    
    void addMonth(WorkMonth wm){
        if(isNewMonth(wm))
            months.add(wm);
    }
    
    void listMonths(){
            for(int j = 0; j < getSize(); j++){
                System.out.println(j + ": " + getWorkMonth(j).date.toString() + ",");
            }
        }
    
    void listDays(int i){
        months.get(i).writeDays();
    }
    
    void listTasks(int i, int j){
        months.get(i).days.get(j).writeTasks();
    }
    
    void addTask(int whichMonth, int whichDay, String taskId, String comment, String startTime){
        Task t = new Task(taskId);
        t.setComment(comment);
        t.setStartTime(startTime);
        if(!months.get(whichMonth).days.get(whichDay).tasks.isEmpty())
            System.out.println(months.get(whichMonth).days.get(whichDay).latestTaskEndTime());
        months.get(whichMonth).days.get(whichDay).addTask(t);
    }
    
    void finishTask(int whichMonth, int whichDay, int whichTask, String endTime){
        months.get(whichMonth).days.get(whichDay).tasks.get(whichTask).setEndTime(endTime);
    }
    
    void showUnfinishedTasks(int whichMonth, int whichDay){
        months.get(whichMonth).days.get(whichDay).listUnfinishedTasks();
    }
    
    void deleteTask(int whichMonth, int whichDay, int whichTask){
        months.get(whichMonth).days.get(whichDay).tasks.remove(whichTask);
    }
    
    void setTaskFields(int whichMonth, int whichDay, int whichTask){
        Scanner sc = new Scanner(System.in);
        Task t = months.get(whichMonth).days.get(whichDay).tasks.get(whichTask);
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
    
    void writeStatistics(int whichMonth){
        long sumPerMonth = months.get(whichMonth).getSumPerMonth();
        long requiredMinPerMonth = months.get(whichMonth).getRequiredMinPerMonth();
        System.out.println("Statistics: sumPerMonth: " 
                + sumPerMonth 
                + ", requiredMinPerMonth: " 
                + requiredMinPerMonth);
        
        System.out.println();
        for(int i = 0; i < months.get(whichMonth).days.size(); i++){
            System.out.println(i + " : " + " requiredMinsPerDay " 
                    +  months.get(whichMonth).days.get(i).requiredMinPerDay 
                    + " sumPerDay: " 
                    + months.get(whichMonth).days.get(i).getSumPerDay());
        }
    }
}
