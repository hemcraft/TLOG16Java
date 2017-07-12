/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import static java.lang.Math.abs;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.List;
import timelogger.exceptions.EmptyTimeFieldException;
import timelogger.exceptions.NotExpectedTimeOrderException;

/**
 *
 * @author Andris
 */
public class Util {
    
    public static LocalTime roundToMultipleQuarterHour(LocalTime startTime, LocalTime endTime){        
        for(int i = 0; i <61; i = i + 15){
            if(Math.abs(MINUTES.between(endTime, startTime.plusMinutes(i))) < 8){
                endTime = startTime.plusMinutes(i);
            }
        }
        return endTime;
    }
    
    public static boolean isSeparatedTime(Task t, List<Task> tasks){
        return !(tasks.stream().anyMatch(task -> task.commonParts(t)));
    }
    
    public static boolean isWeekDay(LocalDate actualDay){        
        return (actualDay.getDayOfWeek().getValue() < 6);
    }
    
    public static boolean isMultipleQuarterHour(Task t) throws EmptyTimeFieldException{
        System.out.println(t.getMinPerTask());
        return (t.getMinPerTask() % 15 == 0);
    }
    
    public static boolean isMultipleQuarterHour(LocalTime startTime, LocalTime endTime) throws EmptyTimeFieldException, NotExpectedTimeOrderException{
        if(startTime == null || endTime == null)
            throw new EmptyTimeFieldException("null received");
        if(startTime.isAfter(endTime))
                throw new NotExpectedTimeOrderException("not expected order");
        return (((endTime.getMinute() - startTime.getMinute()) + 60 * (endTime.getHour() - startTime.getHour())) % 15 == 0);
    }
}
