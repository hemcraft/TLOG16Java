/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;

/**
 *
 * @author Andris
 */
public class WorkMonth {
    List<WorkDay> days;
    YearMonth date;
    long sumPerMonth;
    long requiredMinPerMonth;
    
    WorkMonth(int year, int month){
        this.date = YearMonth.of(year, month);
    }
    
    WorkMonth(int year, Month month){
        this.date = YearMonth.of(year, month);
    }
    
    void calculateRequiredMinPerMonth(){
        for(int i = 0; i < days.size(); i++){
            requiredMinPerMonth += days.get(i).getRequiredMinPerDay();
        }
    } 
    
    void calculateSumPerMonth(){
        for(int i = 0; i < days.size(); i++){
            sumPerMonth += days.get(i).getSumPerDay();
        }
    }
    
    YearMonth getDate(){
        return date;
    }
    
    WorkDay getDays(int i){
        return days.get(i);
    }
    
    long getSumPerMonth(){
        return sumPerMonth;
    }
    
    long getExtraMinPerMonth(){
        long extraMinThisMonth = 0;
        for(int i = 0; i < days.size(); i++){
            extraMinThisMonth += days.get(i).getExtraMinPerDay();
        }
        return extraMinThisMonth;
    }
    
    boolean isNewDate(WorkDay workDay){
            if(days.contains(workDay))
                return false;
            else
                return true;
        
    }
    
    boolean isSameMonth(WorkDay workDay){
        if(date.getMonthValue() == workDay.actualDay.getMonthValue())
            return true;
        else
            return false;
    }
    
    void addWorkDay(WorkDay wd, boolean isWeekendEnabled){
        if(isWeekendEnabled == true && isSameMonth(wd) && isNewDate(wd))
            days.add(wd);
    }
    
    void addWorkDay(WorkDay wd){
        boolean isWeekendEnabled = false;
        //nincs specifikalva
    }
}
