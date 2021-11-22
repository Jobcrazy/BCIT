package ca.bcit.comp1510.timesheet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * test Timesheet (most code is from blink)
 * class/method/line are 100% covered
 *
 * @author Bruce Blink, Hang Liu
 */
public class TestTimesheet {
    //totals 30 hours
    private final float[] hours = {8f, 2f, 2.3f, 2.7f, 4f, 4f, 7f};

    //totals 32 hours
    private final float[] hours2 = {0f, 0f, 8.04f, 8f, 4f, 4f, 8f};

    //totals 32 hours
    private final float[] hours3 = {0f, 0f, 8f, 8f, 4f, 4f, 8f};

    //totals 10 hours
    private final float[] hours4 = {0f, 0f, 8f, 0f, 2f, 0f, 0f};

    private final WeekFields FRIDAY_END
            = WeekFields.of(DayOfWeek.SATURDAY, 1);

    private final Employee e = new Employee();

    private Timesheet emptyTimesheet = new Timesheet();

    LocalDate aFriday = LocalDate.of(2021, 11, 19);
    LocalDate aThursday = LocalDate.of(2021, 11, 18);
    LocalDate aSaturday = LocalDate.of(2021, 11, 13);

    private Timesheet noDetails = new Timesheet(e, aFriday);
    // 94 hours
    private List<TimesheetRow> tsrsBig = new ArrayList<TimesheetRow>();
    //32 hours
    private List<TimesheetRow> tsrsSmall = new ArrayList<TimesheetRow>();
    //40 hours
    private List<TimesheetRow> tsrsRight = new ArrayList<TimesheetRow>();

    @BeforeEach
    final void Setup() {
        TimesheetRow tsr = new TimesheetRow(123, "123");
        tsr.setHoursArray(hours);
        tsrsBig.add(tsr);

        //tsr.setHoursArray(hours2);
        //tsrsBig.add(tsr);

        tsr.setHoursArray(hours3);
        tsrsBig.add(tsr);

        tsr.setHoursArray(hours);
        tsrsBig.add(tsr);

        tsr.setHoursArray(hours4);
        tsrsBig.add(tsr);

        tsr.setHoursArray(hours3);
        tsrsBig.add(tsr);
    }

    @Test
    final void testTimesheet() {
        Timesheet ts = emptyTimesheet;
        LocalDate ew = ts.getEndDate();
        LocalDate now = LocalDate.now();
        //endDate should be in same week as now:
        assertNotNull(ew);
        Assertions.assertEquals(now.get(FRIDAY_END.weekOfWeekBasedYear()),
                ew.get(FRIDAY_END.weekOfWeekBasedYear()));
        assertEquals(ew.getDayOfWeek(), DayOfWeek.FRIDAY);
        assertNull(ts.getEmployee());
        Assertions.assertEquals(0, ts.getDetails().size());

    }

    @Test
    final void testTimesheetEmployeeLocalDate() {
        // test a Friday: Nov 19, 2021
        Timesheet ts = noDetails;
        Assertions.assertEquals(aFriday, ts.getEndDate());
        Assertions.assertSame(e, ts.getEmployee());
        Assertions.assertEquals(0, ts.getDetails().size());

        // test a Saturday: Oct 11, 2014
        ts = new Timesheet(e, aSaturday);
        LocalDate ew = ts.getEndDate();
        Assertions.assertNotSame(aSaturday, ew);
        //ew is on same week, but a Friday
        int x = aSaturday.get(FRIDAY_END.weekOfWeekBasedYear());
        int y = ew.get(FRIDAY_END.weekOfWeekBasedYear());
        Assertions.assertEquals(aSaturday.get(FRIDAY_END.weekOfWeekBasedYear()),
                ew.get(FRIDAY_END.weekOfWeekBasedYear()));
        assertEquals(ew.getDayOfWeek(), DayOfWeek.FRIDAY);
        Assertions.assertSame(e, ts.getEmployee());
        Assertions.assertEquals(0, ts.getDetails().size());
    }

    @Test
    final void testTimesheetEmployeeLocalDateListOfTimesheetRow() {
        List<TimesheetRow> tsrs = new ArrayList<TimesheetRow>();
        TimesheetRow tsr = new TimesheetRow();
        tsrs.add(tsr);
        Timesheet ts = new Timesheet(e, aFriday);
        ts.setDetails(tsrs);
        LocalDate ew = ts.getEndDate();
        Assertions.assertEquals(DayOfWeek.FRIDAY, ew.getDayOfWeek());
        Assertions.assertEquals(aFriday, ew);
        Assertions.assertSame(e, ts.getEmployee());
        Assertions.assertSame(tsrs, ts.getDetails());
        Assertions.assertEquals(1, ts.getDetails().size());
        TimesheetRow newTsr = ts.getDetails().get(0);
        Assertions.assertSame(tsr, newTsr);
        assertEquals(0, newTsr.getProject());
        assertNull(newTsr.getWorkPackage());
        assertEquals(0, newTsr.getHours());

        //empty details
        tsrs = new ArrayList<TimesheetRow>();
        ts = new Timesheet(e, LocalDate.of(2014, 10, 10));
        ts.setDetails(tsrs);
        Assertions.assertSame(tsrs, ts.getDetails());
        Assertions.assertEquals(0, ts.getDetails().size());

        //test if date adjusted to Friday
        ts = new Timesheet(e, aThursday);
        ts.setDetails(tsrs);
        ew = ts.getEndDate();
        Assertions.assertNotSame(aThursday, ew);
        //ew is on same week, but Friday
        Assertions.assertEquals(aThursday.get(FRIDAY_END.weekOfWeekBasedYear()),
                ew.get(FRIDAY_END.weekOfWeekBasedYear()));

        assertEquals(DayOfWeek.FRIDAY, ts.getEndDate().getDayOfWeek());
        Assertions.assertSame(tsrs, ts.getDetails());
        Assertions.assertEquals(0, ts.getDetails().size());

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
        Assertions.assertSame(e, ts.getEmployee());
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
        assertEquals(aThursday.get(FRIDAY_END.weekOfWeekBasedYear()),
                ts.getEndDate().get(FRIDAY_END.weekOfWeekBasedYear()));
        assertEquals(DayOfWeek.FRIDAY, ts.getEndDate().getDayOfWeek());

        ts.setEndDate(aSaturday);
        assertEquals(aSaturday.get(FRIDAY_END.weekOfWeekBasedYear()),
                ts.getEndDate().get(FRIDAY_END.weekOfWeekBasedYear()));
        assertEquals(DayOfWeek.FRIDAY, ts.getEndDate().getDayOfWeek());
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
    final void testAddRow() {
        Timesheet ts = new Timesheet(e, LocalDate.of(2014, 10, 10));
        ts.setDetails(tsrsBig);
        TimesheetRow tsr = new TimesheetRow();
        ts.addTimesheetRow(tsr);
        assertSame(tsr, ts.getDetails().get(ts.getDetails().size() - 1));
    }

    @Test
    final void testToString() {
        Assertions.assertEquals("null 2021-11-19",
                noDetails.toString());

        TimesheetRow tsr3 = new TimesheetRow(10, "SICK");
        Timesheet ts = new Timesheet(e, LocalDate.of(2014, 10, 10));
        tsrsSmall.add(tsr3);
        ts.setDetails(tsrsSmall);
        Assertions.assertEquals("null 2021-11-19\n" +
                        "10 SICK [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]",
                ts.toString());
    }
}
