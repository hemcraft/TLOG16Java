/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author Andris
 */
public class Util {
    
    public static int roundToMultipleQuarterHour(LocalTime startTime, LocalTime endTime){
        int minute = endTime.getMinute() - startTime.getMinute();
        
        return minute = 15 - (minute % 15);
    }
    
    public static boolean isSeparatedTime(Task t, List<Task> tasks, WorkDay day){
        return !(tasks.stream().anyMatch(task -> task.commonParts(t)));
    }
    
    public static boolean isWeekday(LocalDate actualDay){        
        return (actualDay.getDayOfWeek().getValue() < 6);
    }
    
    public static boolean isMultipleQuarterHour(Task t){
        return (t.getMinPerTask() % 15 == 0);
    }
}
