/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timelogger.exceptions;

/**
 *
 * @author Andris
 */
public class NotTheSameMonthException extends Exception{
    public NotTheSameMonthException(String message){
        super(message);
    }
}
