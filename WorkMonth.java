/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import timelogger.exceptions.EmptyTimeFieldException;
import timelogger.exceptions.NotNewDateException;
import timelogger.exceptions.NotTheSameMonthException;
import timelogger.exceptions.WeekendNotEnabledException;

/**
 *
 * @author Andris
 */
public class WorkMonth {
    private ArrayList<WorkDay> days;
    private YearMonth date;
    private long sumPerMonth;
    private long requiredMinPerMonth;
    
    public WorkMonth(int year, int month){
        this.date = YearMonth.of(year, month);
        days = new ArrayList<WorkDay>();
    }
    
    public WorkMonth(int year, Month month){
        this.date = YearMonth.of(year, month);
        days = new ArrayList<WorkDay>();
    }
    
    private void calculateRequiredMinPerMonth(){
        for(int i = 0; i < days.size(); i++){
            requiredMinPerMonth += days.get(i).getRequiredMinPerDay();
        }
    } 
    
    private void calculateSumPerMonth() throws EmptyTimeFieldException{
        for(int i = 0; i < days.size(); i++){
            sumPerMonth = sumPerMonth + days.get(i).getSumPerDay();
        }
    }
    
    public YearMonth getDate(){
        return date;
    }
    
    public WorkDay getDays(int i){
        return days.get(i);
    }
    
    public ArrayList<WorkDay> getDayList(){
        return days;
    }
    
    public long getSumPerMonth() throws EmptyTimeFieldException{
        calculateSumPerMonth();
        return sumPerMonth;
    }
    
    public long getRequiredMinPerMonth(){
        calculateRequiredMinPerMonth();
        return requiredMinPerMonth;
    }
    
    public long getExtraMinPerMonth() throws EmptyTimeFieldException{
        long extraMinThisMonth = 0;
        for(int i = 0; i < days.size(); i++){
            extraMinThisMonth += days.get(i).getExtraMinPerDay();
        }
        return extraMinThisMonth;
    }
    
    public boolean isNewDate(WorkDay workDay){
            for(int i = 0; i < days.size(); i++){
                if(workDay.getActualDay().equals(days.get(i).getActualDay()))
                    return false;
            }
            return true;
    }
    
    public boolean isSameMonth(WorkDay workDay){
        return (date.getMonthValue() == workDay.getActualDay().getMonthValue());          
    }
    
    public void addWorkDay(WorkDay wd, boolean isWeekendEnabled) throws WeekendNotEnabledException, NotNewDateException, NotTheSameMonthException{
        boolean weekendDayToo = (isWeekendEnabled == true) && isSameMonth(wd) && isNewDate(wd);
        boolean weekDayOnly = (isWeekendEnabled == false) && (Util.isWeekDay(wd.getActualDay())) && isSameMonth(wd) && isNewDate(wd);
        if(isWeekendEnabled == false)
            throw new WeekendNotEnabledException("weekend not enabled");
        if(!isNewDate(wd))
            throw new NotNewDateException("not new date");
        if(!isSameMonth(wd))
            throw new NotTheSameMonthException("not the same month");
        if(weekendDayToo || weekDayOnly){
            days.add(wd);
        }
    }
    
    public void addWorkDay(WorkDay wd) throws WeekendNotEnabledException, NotNewDateException, NotTheSameMonthException{
        boolean isWeekendEnabled = false;
        boolean weekDayOnly = (Util.isWeekDay(wd.getActualDay())) && isSameMonth(wd) && isNewDate(wd);
        if(!isNewDate(wd))
            throw new NotNewDateException("not new date");
        if(!isSameMonth(wd))
            throw new NotTheSameMonthException("not the same month");
        if(weekDayOnly){
            days.add(wd);
        }
    }
    
    public void writeDays(){
        for(int i = 0; i < days.size(); i++){
            System.out.println(i + ": " + days.get(i).getActualDay().toString());
        }
    }
}
