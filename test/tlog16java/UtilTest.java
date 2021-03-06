/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java;

import java.time.LocalTime;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import timelogger.exceptions.EmptyTimeFieldException;
import timelogger.exceptions.InvalidTaskIdException;
import timelogger.exceptions.NoTaskIdException;
import timelogger.exceptions.NotExpectedTimeOrderException;

/**
 *
 * @author Andris
 */
public class UtilTest {
    
    public UtilTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void test1(){
        LocalTime lt = Util.roundToMultipleQuarterHour(LocalTime.of(7, 30), LocalTime.of(7, 50));
        assertEquals(lt, LocalTime.of(7, 45));
    }
    
    @Test
    public void test2() throws NotExpectedTimeOrderException, InvalidTaskIdException, NoTaskIdException, EmptyTimeFieldException{
        boolean quarter = Util.isMultipleQuarterHour(LocalTime.parse("07:30"), LocalTime.parse("07:50"));
        assertEquals(quarter, false);
    }
    
    @Test
    public void test3() throws NotExpectedTimeOrderException, InvalidTaskIdException, NoTaskIdException, EmptyTimeFieldException{
        boolean quarter = Util.isMultipleQuarterHour(LocalTime.parse("07:30"), LocalTime.parse("07:45"));
        assertEquals(quarter, true);
    }
    
    @Test(expected = EmptyTimeFieldException.class)
    public void test4() throws EmptyTimeFieldException, NotExpectedTimeOrderException{
        Util.isMultipleQuarterHour(null, LocalTime.parse("07:45"));
    }
    
    @Test(expected = NotExpectedTimeOrderException.class)
    public void test5() throws EmptyTimeFieldException, NotExpectedTimeOrderException{
        Util.isMultipleQuarterHour(LocalTime.parse("08:30"), LocalTime.parse("07:45"));
    }
    
    @Test
    public void test6() throws NotExpectedTimeOrderException, InvalidTaskIdException, NoTaskIdException, EmptyTimeFieldException{
        Task t;
        Task t2;
        ArrayList<Task> tasks;
        
        t = new Task("1111", "06:30", "06:45", "list");
        t2 = new Task("1112", "05:30", "06:30", "list");
        tasks = new ArrayList<Task>();
        tasks.add(t2);
        assertEquals(true, Util.isSeparatedTime(t, tasks));
        
        t = new Task("1111", "06:30", "06:45", "list");
        t2 = new Task("1112", "06:45", "07:00", "list");
        tasks = new ArrayList<Task>();
        tasks.add(t2);
        assertEquals(true, Util.isSeparatedTime(t, tasks));
        
        t = new Task("1111", "06:30", "06:30", "list");
        t2 = new Task("1112", "05:30", "06:30", "list");
        tasks = new ArrayList<Task>();
        tasks.add(t2);
        assertEquals(true, Util.isSeparatedTime(t, tasks));
        
        t = new Task("1111", "06:30", "06:30", "list");
        t2 = new Task("1112", "07:30", "07:30", "list");
        tasks = new ArrayList<Task>();
        tasks.add(t2);
        assertEquals(true, Util.isSeparatedTime(t, tasks));
        
        t = new Task("1111", "06:30", "07:00", "list");
        t2 = new Task("1112", "06:00", "06:45", "list");
        tasks = new ArrayList<Task>();
        tasks.add(t2);
        assertEquals(false, Util.isSeparatedTime(t, tasks));
        
        t = new Task("1111", "06:30", "07:00", "list");
        t2 = new Task("1112", "06:30", "06:45", "list");
        tasks = new ArrayList<Task>();
        tasks.add(t2);
        assertEquals(false, Util.isSeparatedTime(t, tasks));
        
        t = new Task("1111", "06:30", "07:00", "list");
        t2 = new Task("1112", "06:45", "07:15", "list");
        tasks = new ArrayList<Task>();
        tasks.add(t2);
        assertEquals(false, Util.isSeparatedTime(t, tasks));
        
        t = new Task("1111", "06:30", "07:00", "list");
        t2 = new Task("1112", "06:45", "07:00", "list");
        tasks = new ArrayList<Task>();
        tasks.add(t2);
        assertEquals(false, Util.isSeparatedTime(t, tasks));
        
        t = new Task("1111", "06:30", "06:30", "list");
        t2 = new Task("1112", "06:30", "07:00", "list");
        tasks = new ArrayList<Task>();
        tasks.add(t2);
        assertEquals(false, Util.isSeparatedTime(t, tasks));
        
        System.out.println("ezt nem hajtja vegre");
        t = new Task("1111", "06:30", "07:30", "list");
        t2 = new Task("1112", "06:30", "06:30", "list");
        tasks = new ArrayList<Task>();
        tasks.add(t2);
        assertEquals(false, Util.isSeparatedTime(t, tasks));
        System.out.println("ezt se");
    }
}
