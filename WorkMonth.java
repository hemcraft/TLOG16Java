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

@lombok.Getter
@lombok.Setter
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
    
    /**
     * calculates the required minutes this month
     */
    private void calculateRequiredMinPerMonth(){
        for(int i = 0; i < days.size(); i++){
            requiredMinPerMonth += days.get(i).getRequiredMinPerDay();
        }
    } 
    
    /**
     * calculates time spent working this month
     * @throws EmptyTimeFieldException 
     */
    private void calculateSumPerMonth() throws EmptyTimeFieldException{
        for(int i = 0; i < days.size(); i++){
            sumPerMonth = sumPerMonth + days.get(i).getSumPerDay();
        }
    }
    
    /*public YearMonth getDate(){
        return date;
    }*/
    
    /**
     * gets a member of the days
     * @param i
     * @return 
     */
    public WorkDay getDays(int i){
        return days.get(i);
    }
    /**
     * gets the list of days
     * @return 
     */
    public ArrayList<WorkDay> getDayList(){
        return days;
    }
    
    /**
     * getter
     * @return
     * @throws EmptyTimeFieldException 
     */
    public long getSumPerMonth() throws EmptyTimeFieldException{
        calculateSumPerMonth();
        return sumPerMonth;
    }
    
    /**
     * getter
     * @return 
     */
    public long getRequiredMinPerMonth(){
        calculateRequiredMinPerMonth();
        return requiredMinPerMonth;
    }
    /**
     * gets the extre minutes this month
     * @return
     * @throws EmptyTimeFieldException 
     */
    public long getExtraMinPerMonth() throws EmptyTimeFieldException{
        long extraMinThisMonth = 0;
        for(int i = 0; i < days.size(); i++){
            extraMinThisMonth += days.get(i).getExtraMinPerDay();
        }
        return extraMinThisMonth;
    }
    
    /**
     * checks if given date is already stored
     * @param workDay
     * @return 
     */
    public boolean isNewDate(WorkDay workDay){
            for(int i = 0; i < days.size(); i++){
                if(workDay.getActualDay().equals(days.get(i).getActualDay()))
                    return false;
            }
            return true;
    }
    
    /**
     * checks if date belongs to month
     * @param workDay
     * @return 
     */
    public boolean isSameMonth(WorkDay workDay){
        return (date.getMonthValue() == workDay.getActualDay().getMonthValue());          
    }
    
    /**
     * adds day to the list of days
     * @param wd
     * @param isWeekendEnabled
     * @throws WeekendNotEnabledException thrown if weekend is not enabled
     * @throws NotNewDateException thrown if date already stored
     * @throws NotTheSameMonthException thrown if date doesn't belong to month
     */
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
    
    /**
     * adds day to the list of days
     * @param wd
     * @param isWeekendEnabled
     * @throws NotNewDateException thrown if date already stored
     * @throws NotTheSameMonthException thrown if date doesn't belong to month
     */
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
    
    /**
     * writes to the console
     */
    public void writeDays(){
        for(int i = 0; i < days.size(); i++){
            System.out.println(i + ": " + days.get(i).getActualDay().toString());
        }
    }
}
