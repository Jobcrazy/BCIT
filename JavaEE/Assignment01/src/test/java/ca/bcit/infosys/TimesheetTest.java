package ca.bcit.infosys;

import static org.junit.jupiter.api.Assertions.*;

import ca.bcit.infosys.timesheet.Timesheet;
import ca.bcit.infosys.timesheet.TimesheetRow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import ca.bcit.infosys.employee.Employee;

import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;


/**
 * JUnit tests for the Timesheet.
 *
 * @author blink
 * @version 2.0
 */
class TimesheetTest {
    //totals 30 hours
    private final float[] hours = {8f, 2f, 2.3f, 2.7f, 4f, 4f, 7f}; 
    
    //totals 32 hours
    private final float[] hours2 = {0f, 0f, 8.04f, 8f, 4f, 4f, 8f};
    
    //totals 32 hours
    private final float[] hours3 = {0f, 0f, 8f, 8f, 4f, 4f, 8f}; 
    
    //totals 10 hours
    private final float[] hours4 = {0f, 0f, 8f, 0f, 2f, 0f, 0f};  

    private final Employee e = new Employee();
    
    private Timesheet emptyTimesheet = new Timesheet();
    
    LocalDate aFriday = LocalDate.of(2014, 10, 10);
    LocalDate aThursday = LocalDate.of(2014, 10, 9);
    LocalDate aSaturday = LocalDate.of(2014, 10, 11);
    
    private Timesheet noDetails = new Timesheet(e, aFriday);
    // 94 hours
    private List<TimesheetRow> tsrsBig = new ArrayList<TimesheetRow>();
    //32 hours
    private List<TimesheetRow> tsrsSmall = new ArrayList<TimesheetRow>();
    //40 hours
    private List<TimesheetRow> tsrsRight = new ArrayList<TimesheetRow>();
    
    @BeforeEach
    final void Setup() {
        tsrsBig.add(new TimesheetRow(123, "123", "comment1", hours));
        tsrsBig.add(new TimesheetRow(123, "123", "comment2", hours2));
        tsrsBig.add(new TimesheetRow(123, "123", "comment3", hours3));
        
        tsrsRight.add(new TimesheetRow(123, "123", "comment1", hours));
        tsrsRight.add(new TimesheetRow(123, "123", "comment2", hours4));
        
        tsrsSmall.add(new TimesheetRow(123, "123", "comment3", hours3));
    }

    @Test
    final void testTimesheet() {
        Timesheet ts = emptyTimesheet;
        LocalDate ew = ts.getEndDate();
        LocalDate now = LocalDate.now();
        //endDate should be in same week as now:
        Assertions.assertEquals(now.get(Timesheet.FRIDAY_END.weekOfWeekBasedYear()),
                ew.get(Timesheet.FRIDAY_END.weekOfWeekBasedYear()));
        Assertions.assertTrue(ew.getDayOfWeek().equals(DayOfWeek.FRIDAY));
        Assertions.assertEquals(null, ts.getEmployee());
        Assertions.assertEquals(0, ts.getDetails().size());
        assertEquals(0, ts.getOvertimeDecihours());
        assertEquals(0, ts.getFlextimeDecihours());
    }

    @Test
    final void testTimesheetEmployeeLocalDate() {
        // test a Friday: Oct 10, 2014
        Timesheet ts = noDetails;
        Assertions.assertSame(aFriday, ts.getEndDate());
        Assertions.assertSame(e, ts.getEmployee());
        Assertions.assertEquals(0, ts.getDetails().size());
        assertEquals(0, ts.getOvertimeDecihours());
        assertEquals(0, ts.getFlextimeDecihours());
        
        // test a Saturday: Oct 11, 2014
        ts = new Timesheet(e, aSaturday);
        LocalDate ew = ts.getEndDate();
        Assertions.assertNotSame(aSaturday, ew);
        //ew is on same week, but a Friday
        Assertions.assertEquals(aSaturday.get(Timesheet.FRIDAY_END.weekOfWeekBasedYear()),
                ew.get(Timesheet.FRIDAY_END.weekOfWeekBasedYear()));
        Assertions.assertTrue(ew.getDayOfWeek().equals(DayOfWeek.FRIDAY));
        Assertions.assertSame(e, ts.getEmployee());
        Assertions.assertEquals(0, ts.getDetails().size());
        assertEquals(0, ts.getOvertimeDecihours());
        assertEquals(0, ts.getFlextimeDecihours());
        
    }

    @Test
    final void testTimesheetEmployeeLocalDateListOfTimesheetRow() {
        List<TimesheetRow> tsrs = new ArrayList<TimesheetRow>();
        TimesheetRow tsr = new TimesheetRow();
        tsrs.add(tsr);
        Timesheet ts = new Timesheet(e, aFriday, tsrs);
        LocalDate ew = ts.getEndDate();
        Assertions.assertEquals(DayOfWeek.FRIDAY, ew.getDayOfWeek());
        Assertions.assertSame(aFriday, ew);
        Assertions.assertSame(e, ts.getEmployee());
        Assertions.assertSame(tsrs, ts.getDetails());
        Assertions.assertEquals(1, ts.getDetails().size());
        TimesheetRow newTsr = ts.getDetails().get(0);
        Assertions.assertSame(tsr, newTsr);
        assertEquals(0, newTsr.getProjectId());
        assertEquals(null, newTsr.getWorkPackageId());
        assertEquals(0, newTsr.getPackedHours());
        Assertions.assertEquals(null, newTsr.getNotes());
        assertEquals(0, ts.getOvertimeDecihours());
        assertEquals(0, ts.getFlextimeDecihours());
        
        //empty details
        tsrs = new ArrayList<TimesheetRow>();
        ts = new Timesheet(e, LocalDate.of(2014, 10, 10), tsrs);
        Assertions.assertSame(tsrs, ts.getDetails());
        assertEquals(0, ts.getOvertimeDecihours());
        assertEquals(0, ts.getFlextimeDecihours());
        Assertions.assertEquals(0, ts.getDetails().size());
       
        //test if date adjusted to Friday
        ts =  new Timesheet(e, aThursday, tsrs);
        ew = ts.getEndDate();
        Assertions.assertNotSame(aThursday, ew);
        //ew is on same week, but Friday
        Assertions.assertEquals(aThursday.get(Timesheet.FRIDAY_END.weekOfWeekBasedYear()),
                ew.get(Timesheet.FRIDAY_END.weekOfWeekBasedYear()));

        assertEquals(DayOfWeek.FRIDAY, ts.getEndDate().getDayOfWeek());
        Assertions.assertSame(tsrs, ts.getDetails());
        Assertions.assertEquals(0, ts.getDetails().size());
        assertEquals(0, ts.getOvertimeDecihours());
        assertEquals(0, ts.getFlextimeDecihours());
    }
    
    @Test
    @Disabled
    final void testGetEmployee() {
        Assertions.fail("Not yet implemented");
    }

    @Test
    final void testSetEmployee() {
        Timesheet ts = emptyTimesheet;
        ts.setEmployee(e);
        Assertions.assertSame(e,  ts.getEmployee());
    }

    @Test
    @Disabled
    final void testGetEndDate() {
        Assertions.fail("Not yet implemented");
    }

    @Test
    final void testSetEndDate() {
        Timesheet ts = emptyTimesheet;
        ts.setEndDate(aFriday);
        Assertions.assertSame(aFriday, ts.getEndDate());
        
        ts.setEndDate(aThursday);
        assertEquals(aThursday.get(Timesheet.FRIDAY_END.weekOfWeekBasedYear()),
                ts.getEndDate().get(Timesheet.FRIDAY_END.weekOfWeekBasedYear()));
        assertEquals(DayOfWeek.FRIDAY, ts.getEndDate().getDayOfWeek());

        ts.setEndDate(aSaturday);
        assertEquals(aSaturday.get(Timesheet.FRIDAY_END.weekOfWeekBasedYear()),
                ts.getEndDate().get(Timesheet.FRIDAY_END.weekOfWeekBasedYear()));
        assertEquals(DayOfWeek.FRIDAY, ts.getEndDate().getDayOfWeek());
    }

    @Test
    final void testGetWeekNumber() {
        //empty details
        Timesheet ts = new Timesheet(e, LocalDate.of(2014, 1, 3), //Jan 3, 2014
                new ArrayList<TimesheetRow>());
        Assertions.assertEquals(1, ts.getWeekNumber());
    }

    @Test
    final void testSetWeekNumber() {
        //empty details
        Timesheet ts = new Timesheet(e, LocalDate.of(2014, 1, 3), //Jan 3, 2014
                new ArrayList<TimesheetRow>());
        
        ts.setWeekNumber(5, 2014);
        Assertions.assertEquals(5, ts.getWeekNumber());
        LocalDate ew = ts.getEndDate();
        Assertions.assertEquals(DayOfWeek.FRIDAY, ew.getDayOfWeek());
        Assertions.assertEquals(LocalDate.of(2014, 1, 31), ew); //Jan 31, 2014
    }

    @Test
    final void testGetWeekEnding() {
        Timesheet ts = emptyTimesheet;
        ts.setEndDate(aFriday);
        Assertions.assertEquals("2014-10-10", ts.getWeekEnding());
        
        ts.setEndDate(aThursday);
        Assertions.assertEquals("2014-10-10", ts.getWeekEnding());

        ts.setEndDate(aSaturday);
        Assertions.assertEquals("2014-10-17", ts.getWeekEnding());
    }

    @Test
    @Disabled
    final void testGetDetails() {
        Assertions.fail("Not yet implemented"); // TODO
    }

    @Test
    final void testSetDetails() {
        Timesheet ts = emptyTimesheet;
        ts.setDetails(tsrsSmall);
        Assertions.assertSame(tsrsSmall, ts.getDetails());
        ts.setDetails(tsrsRight);
        Assertions.assertSame(tsrsRight, ts.getDetails());
        ts.setDetails(tsrsBig);
        Assertions.assertSame(tsrsBig, ts.getDetails());
    }

    @Test
    final void testGetOvertimeDecihours() {
        Timesheet ts = new Timesheet();
        assertEquals(0, ts.getOvertimeDecihours());
        ts.setOvertime(10.2f);
        assertEquals(102, ts.getOvertimeDecihours());
    }

    @Test
    final void testSetOvertimeInt() {
        Timesheet ts = new Timesheet();
        ts.setOvertime(102);
        assertEquals(102, ts.getOvertimeDecihours());
        ts.setOvertime(104);
        assertEquals(104, ts.getOvertimeDecihours());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ts.setOvertime(-1));
    }

    @Test
    final void testSetOvertimeFloat() {
        Timesheet ts = new Timesheet();
        ts.setOvertime(10.2f);
        assertEquals(102, ts.getOvertimeDecihours());
        ts.setOvertime(10.24f);
        assertEquals(102, ts.getOvertimeDecihours());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ts.setOvertime(-1f));
    }

    @Test
    final void testGetOvertimeHours() {
        Timesheet ts = new Timesheet();
        assertEquals(0f, ts.getOvertimeHours());
        ts.setOvertime(10.2f);
        assertEquals(10.2f, ts.getOvertimeHours());
    }

    @Test
    final void testGetFlextimeDecihours() {
        Timesheet ts = new Timesheet();
        assertEquals(0, ts.getFlextimeDecihours());
        ts.setFlextime(10.2f);
        assertEquals(102, ts.getFlextimeDecihours());
    }

    @Test
    final void testSetFlextimeInt() {
        Timesheet ts = new Timesheet();
        ts.setFlextime(102);
        assertEquals(10.2f, ts.getFlextimeHours());
        ts.setFlextime(103);
        assertEquals(10.3f, ts.getFlextimeHours());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ts.setFlextime(-1));
    }

    @Test
    final void testGetFlextimeHours() {
        Timesheet ts = new Timesheet();
        assertEquals(0f, ts.getFlextimeHours());
        ts.setFlextime(10.2f);
        assertEquals(10.2f, ts.getFlextimeHours());
    }

    @Test
    final void testSetFlextimeFloat() {
        Timesheet ts = new Timesheet();
        ts.setFlextime(10.2f);
        assertEquals(10.2f, ts.getFlextimeHours());
        ts.setFlextime(10.255f);
        assertEquals(10.3f, ts.getFlextimeHours());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ts.setFlextime(-1f));
    }

    @Test
    final void testGetTotalHours() {
        Timesheet ts = new Timesheet(e, LocalDate.of(2014, 10, 10),
                tsrsBig); 
        Assertions.assertEquals(94f, ts.getTotalHours());

        ts.setDetails(tsrsSmall);
        Assertions.assertEquals(32f, ts.getTotalHours());

        ts.setDetails(tsrsRight);
        Assertions.assertEquals(40f, ts.getTotalHours());
    }

    @Test
    final void testGetTotalDecihours() {
        Timesheet ts = new Timesheet(e, LocalDate.of(2014, 10, 10),
                tsrsBig); 
        assertEquals(940, ts.getTotalDecihours());

        ts.setDetails(tsrsSmall);
        assertEquals(320, ts.getTotalDecihours());

        ts.setDetails(tsrsRight);
        assertEquals(400, ts.getTotalDecihours());
    }

    @Test
    final void testGetDailyHours() {
        final float[] dailyBig = {8f, 2f, 18.3f, 18.7f, 12f, 12f, 23f};
        final float[] dailySmall = {0f, 0f, 8f, 8f, 4f, 4f, 8f};
        final float[] dailyRight = {8f, 2f, 10.3f, 2.7f, 6f, 4f, 7f};
        Timesheet ts = new Timesheet(e, LocalDate.of(2014, 10, 10),
                tsrsBig); 
        assertArrayEquals(dailyBig, ts.getDailyHours());

        ts.setDetails(tsrsSmall);
        assertArrayEquals(dailySmall, ts.getDailyHours());

        ts.setDetails(tsrsRight);
        assertArrayEquals(dailyRight, ts.getDailyHours());
    }

    @Test
    final void testGetDailyDecihours() {
        final int[] dailyBig = {80, 20, 183, 187, 120, 120, 230};
        final int[] dailySmall = {0, 0, 80, 80, 40, 40, 80};
        final int[] dailyRight = {80, 20, 103, 27, 60, 40, 70};
        Timesheet ts = new Timesheet(e, LocalDate.of(2014, 10, 10),
                tsrsBig); 
        assertArrayEquals(dailyBig, ts.getDailyDecihours());

        ts.setDetails(tsrsSmall);
        assertArrayEquals(dailySmall, ts.getDailyDecihours());

        ts.setDetails(tsrsRight);
        assertArrayEquals(dailyRight, ts.getDailyDecihours());
    }

    @Test
    final void testIsValid() {
        List<TimesheetRow> tsrs = new ArrayList<TimesheetRow>();
        tsrs.add(new TimesheetRow(123, "123", "comment1", hours));
        Timesheet ts = new Timesheet(e, LocalDate.of(2014, 10, 10), //Oct 10, 2014
                tsrs);
        Assertions.assertFalse(ts.isValid());

        tsrs.add(new TimesheetRow(123, "456", "comment1", hours4));
        Assertions.assertTrue(ts.isValid());

        tsrs.add(new TimesheetRow(123, "223", "comment1", hours2));
        Assertions.assertFalse(ts.isValid());
        ts.setOvertime(32f);
        Assertions.assertTrue(ts.isValid());

        ts.setOvertime(20f);
        Assertions.assertFalse(ts.isValid());
        ts.setFlextime(12f);
        Assertions.assertFalse(ts.isValid());
        ts.setOvertime(0);
        Assertions.assertFalse(ts.isValid());
    }

    @Test
    final void testDeleteRow() {
        Timesheet ts = new Timesheet(e, LocalDate.of(2014, 10, 10),
                tsrsBig);
        ts.deleteRow(ts.getDetails().get(1));
        Assertions.assertEquals(62f, ts.getTotalHours());
        ts.deleteRow(ts.getDetails().get(1));
        Assertions.assertEquals(30f, ts.getTotalHours());
        ts.deleteRow(ts.getDetails().get(0));
        Assertions.assertEquals(0f, ts.getTotalHours());
        
        ts = new Timesheet(e, LocalDate.of(2014, 10, 10),
                tsrsSmall);
        ts.deleteRow(ts.getDetails().get(0));
        Assertions.assertEquals(0f, ts.getTotalHours());
        
        ts = new Timesheet(e, LocalDate.of(2014, 10, 10),
                tsrsRight); 
        ts.deleteRow(ts.getDetails().get(0));
        Assertions.assertEquals(10f, ts.getTotalHours());
        ts.deleteRow(ts.getDetails().get(0));
        Assertions.assertEquals(0f, ts.getTotalHours());
    }

    @Test
    final void testAddRow() {
        Timesheet ts = new Timesheet(e, LocalDate.of(2014, 10, 10),
                tsrsBig);
        ts.addRow();
        Assertions.assertEquals(94f, ts.getTotalHours());
        Assertions.assertEquals(4, ts.getDetails().size());
        final float[] dailyBig = {8f, 2f, 18.3f, 18.7f, 12f, 12f, 23f};
        assertArrayEquals(dailyBig, ts.getDailyHours());
        
        ts = new Timesheet();
        ts.addRow();
        Assertions.assertEquals(0f, ts.getTotalHours());
        Assertions.assertEquals(1, ts.getDetails().size());
        assertEquals(0, ts.getDetails().get(0).getPackedHours());
        
    }

}
