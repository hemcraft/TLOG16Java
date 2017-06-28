/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Andris
 */
public class TimeLogger {
    List<WorkMonth> months;
    
    WorkMonth getWorkMonth(int i){
        return months.get(i);
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
}
