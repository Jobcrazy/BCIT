package ca.bcit.comp1510.timesheet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.bcit.comp1510.timesheet.TimesheetRow.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * test TimesheetRow (most code is from blink)
 * class/method/line are 100% covered
 *
 * @author Bruce Blink, Hang Liu
 */
public class TestTimesheetRow {
    TimesheetRow tsr = new TimesheetRow();

    //totals 30 hours, order SAT to FRI
    private final float[] hours = {8f, 2f, 2.3f, 2.7f, 4f, 4f, 7f};
    private final int[] decihours = {80, 20, 23, 27, 40, 40, 70};
    private final long packedHours = 0x4628281B171450L;
    //totals 32 hours
    private final float[] hours2 = {0f, 0f, 8f, 8f, 4f, 4f, 8f};
    private final int[] decihours2 = {0, 0, 80, 80, 40, 40, 80};
    private final long packedHours2 = 0x50282850500000L;

    //totals 33 hours
    private final float[] hours3 = {0.1f, 0.1f, 8.2f, 8.2f, 4.4f, 4f, 8f};
    private final int[] decihours3 = {1, 1, 82, 82, 44, 40, 80};
    private final long packedHours3 = 0x50282C52520101L;

    //illegal hours
    private final float[] hours4 = {0.15000005f, 0.1f, 8.2f, 8.2f, 4.4f, 4f, 8f};
    private final long packedHours4 = 0xFFFFFF52520101L;

    @BeforeEach
    public void before() {
        tsr.setProject(1001);
        tsr.setWorkPackage("A test package");
    }

    @Test
    public void testTimesheetRow() {
        TimesheetRow timesheetRow = new TimesheetRow();
        assertEquals(timesheetRow.getProject(), 0);
        assertNull(timesheetRow.getWorkPackage());
        assertEquals(timesheetRow.getHours(), 0);
    }

    @Test
    final void testTimesheetRowIntString() {
        assertEquals(1001, tsr.getProject());
        Assertions.assertSame("A test package", tsr.getWorkPackage());
        assertEquals(0, tsr.getHours());
    }

    @Test
    final void testTimesheetRowIntStringStringFloatArray() {
        TimesheetRow tr = new TimesheetRow(1234, "1234");
        tr.setHoursArray(hours);
        assertEquals(1234, tr.getProject());
        assertEquals("1234", tr.getWorkPackage());
        assertEquals(packedHours, tr.getHours());
        assertArrayEquals(hours, tr.getHoursArray());
        Assertions.assertEquals(8f, tr.getHour(SAT));
        Assertions.assertEquals(2f, tr.getHour(SUN));
        Assertions.assertEquals(2.3f, tr.getHour(MON));
        Assertions.assertEquals(2.7f, tr.getHour(TimesheetRow.TUE));
        Assertions.assertEquals(4f, tr.getHour(TimesheetRow.WED));
        Assertions.assertEquals(4f, tr.getHour(TimesheetRow.THU));
        Assertions.assertEquals(7f, tr.getHour(TimesheetRow.FRI));

        tr = new TimesheetRow(1234, "1234");
        tr.setHoursArray(hours2);
        assertEquals(1234, tr.getProject());
        assertEquals("1234", tr.getWorkPackage());
        assertEquals(packedHours2, tr.getHours());
        assertArrayEquals(hours2, tr.getHoursArray());
        Assertions.assertEquals(0f, tr.getHour(SAT));
        Assertions.assertEquals(0f, tr.getHour(SUN));
        Assertions.assertEquals(8f, tr.getHour(MON));
        Assertions.assertEquals(8f, tr.getHour(TimesheetRow.TUE));
        Assertions.assertEquals(4f, tr.getHour(TimesheetRow.WED));
        Assertions.assertEquals(4f, tr.getHour(TimesheetRow.THU));
        Assertions.assertEquals(8f, tr.getHour(TimesheetRow.FRI));

        tr = new TimesheetRow(1234, "1234");
        tr.setHoursArray(hours3);
        assertEquals(1234, tr.getProject());
        assertEquals("1234", tr.getWorkPackage());
        assertEquals(packedHours3, tr.getHours());
        assertArrayEquals(hours3, tr.getHoursArray());
        Assertions.assertEquals(.1f, tr.getHour(SAT));
        Assertions.assertEquals(.1f, tr.getHour(SUN));
        Assertions.assertEquals(8.2f, tr.getHour(MON));
        Assertions.assertEquals(8.2f, tr.getHour(TimesheetRow.TUE));
        Assertions.assertEquals(4.4f, tr.getHour(TimesheetRow.WED));
        Assertions.assertEquals(4f, tr.getHour(TimesheetRow.THU));
        Assertions.assertEquals(8f, tr.getHour(TimesheetRow.FRI));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setHoursArray(hours4));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setHours(packedHours4));
    }

    @Test
    final void testGetProjectId() {
        assertEquals(1001, tsr.getProject());
    }

    @Test
    final void testSetProjectId() {
        tsr.setProject(2341);
        assertEquals(2341, tsr.getProject());
        tsr.setProject(0);
        assertEquals(0, tsr.getProject());

        // This line can't pass
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setProject(-1));
    }

    @Test
    final void testGetWorkPackageId() {
        Assertions.assertSame("A test package", tsr.getWorkPackage());
    }

    @Test
    final void testSetWorkPackageId() {
        tsr.setWorkPackage("12300");
        Assertions.assertSame("12300", tsr.getWorkPackage());
    }

    @Test
    final void testgetHours() {
        assertEquals(0, tsr.getHours());
        tsr.setHours(0x123456789AL);
        assertEquals(0x123456789AL, tsr.getHours());
    }

    @Test
    final void testsetHours() {
        tsr.setHours(0x123456789AL);
        assertEquals(0x123456789AL, tsr.getHours());
        tsr.setHours(0x01010101010101L);
        assertEquals(0x01010101010101L, tsr.getHours());
        tsr.setHours(0xF0F0F0F0F0F0F0L);
        assertEquals(0xF0F0F0F0F0F0F0L, tsr.getHours());
        tsr.setHours(0L);
        assertEquals(0L, tsr.getHours());
        tsr.setHours(0);
        assertEquals(0L, tsr.getHours());

        // These can't pass
        // largest valid byte is 240 == 0xF0
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setHours(0xAA01010101010101L));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setHours(0xF1F0F0F0F0F0F0L));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setHours(0xF0F1F0F0F0F0F0L));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setHours(0xF0F0F1F0F0F0F0L));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setHours(0xF0F0F0F1F0F0F0L));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setHours(0xF0F0F0F0F1F0F0L));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setHours(0xF0F0F0F0F0F1F0L));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setHours(0xF0F0F0F0F0F0F1L));
    }

    @Test
    final void testGetHour() {
        tsr.setHours(0x07060504030201L);
        Assertions.assertEquals(.1f, tsr.getHour(SAT));
        Assertions.assertEquals(.2f, tsr.getHour(SUN));
        Assertions.assertEquals(.3f, tsr.getHour(MON));
        Assertions.assertEquals(.4f, tsr.getHour(TUE));
        Assertions.assertEquals(.5f, tsr.getHour(WED));
        Assertions.assertEquals(.6f, tsr.getHour(THU));
        Assertions.assertEquals(.7f, tsr.getHour(FRI));
        tsr.setHours(0x463C32281E140AL);
        Assertions.assertEquals(1f, tsr.getHour(SAT));
        Assertions.assertEquals(2f, tsr.getHour(SUN));
        Assertions.assertEquals(3f, tsr.getHour(MON));
        Assertions.assertEquals(4f, tsr.getHour(TUE));
        Assertions.assertEquals(5f, tsr.getHour(WED));
        Assertions.assertEquals(6f, tsr.getHour(THU));
        Assertions.assertEquals(7f, tsr.getHour(FRI));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.getHour(-1));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.getHour(7));
    }

    @Test
    final void testSetHour() {
        tsr.setHours(0x463C32281E140AL);
        float bd = 2.3f;
        tsr.setHour(MON, bd);
        Assertions.assertEquals(bd, tsr.getHour(MON));
        Assertions.assertEquals(1f, tsr.getHour(SAT));
        Assertions.assertEquals(2f, tsr.getHour(SUN));
        Assertions.assertEquals(4f, tsr.getHour(TUE));
        Assertions.assertEquals(5f, tsr.getHour(WED));
        Assertions.assertEquals(6f, tsr.getHour(THU));
        Assertions.assertEquals(7f, tsr.getHour(FRI));

        tsr.setHour(MON, 24f);
        Assertions.assertEquals(24f, tsr.getHour(MON));
        tsr.setHour(MON, 24f);
        Assertions.assertEquals(24f, tsr.getHour(MON));


        tsr.setHour(TimesheetRow.SAT, 0f);
        Assertions.assertEquals(0f, tsr.getHour(TimesheetRow.SAT));

        tsr.setHours(0x463C32281E140AL);
        tsr.setHour(TUE, bd);
        Assertions.assertEquals(bd, tsr.getHour(TUE));
        Assertions.assertEquals(1f, tsr.getHour(SAT));
        Assertions.assertEquals(2f, tsr.getHour(SUN));
        Assertions.assertEquals(3f, tsr.getHour(MON));
        Assertions.assertEquals(5f, tsr.getHour(WED));
        Assertions.assertEquals(6f, tsr.getHour(THU));
        Assertions.assertEquals(7f, tsr.getHour(FRI));

        tsr.setHours(0x463C32281E140AL);
        tsr.setHour(WED, bd);
        Assertions.assertEquals(bd, tsr.getHour(WED));
        Assertions.assertEquals(1f, tsr.getHour(SAT));
        Assertions.assertEquals(2f, tsr.getHour(SUN));
        Assertions.assertEquals(3f, tsr.getHour(MON));
        Assertions.assertEquals(4f, tsr.getHour(TUE));
        Assertions.assertEquals(6f, tsr.getHour(THU));
        Assertions.assertEquals(7f, tsr.getHour(FRI));

        tsr.setHours(0x463C32281E140AL);
        tsr.setHour(THU, bd);
        Assertions.assertEquals(bd, tsr.getHour(THU));
        Assertions.assertEquals(1f, tsr.getHour(SAT));
        Assertions.assertEquals(2f, tsr.getHour(SUN));
        Assertions.assertEquals(3f, tsr.getHour(MON));
        Assertions.assertEquals(4f, tsr.getHour(TUE));
        Assertions.assertEquals(5f, tsr.getHour(WED));
        Assertions.assertEquals(7f, tsr.getHour(FRI));

        tsr.setHours(0x463C32281E140AL);
        tsr.setHour(FRI, bd);
        Assertions.assertEquals(bd, tsr.getHour(FRI));
        Assertions.assertEquals(1f, tsr.getHour(SAT));
        Assertions.assertEquals(2f, tsr.getHour(SUN));
        Assertions.assertEquals(3f, tsr.getHour(MON));
        Assertions.assertEquals(4f, tsr.getHour(TUE));
        Assertions.assertEquals(5f, tsr.getHour(WED));
        Assertions.assertEquals(6f, tsr.getHour(THU));

        tsr.setHours(0x463C32281E140AL);
        tsr.setHour(SAT, bd);
        Assertions.assertEquals(bd, tsr.getHour(SAT));
        Assertions.assertEquals(2f, tsr.getHour(SUN));
        Assertions.assertEquals(3f, tsr.getHour(MON));
        Assertions.assertEquals(4f, tsr.getHour(TUE));
        Assertions.assertEquals(5f, tsr.getHour(WED));
        Assertions.assertEquals(6f, tsr.getHour(THU));
        Assertions.assertEquals(7f, tsr.getHour(FRI));

        tsr.setHours(0x463C32281E140AL);
        tsr.setHour(SUN, bd);
        Assertions.assertEquals(bd, tsr.getHour(SUN));
        Assertions.assertEquals(1f, tsr.getHour(SAT));
        Assertions.assertEquals(3f, tsr.getHour(MON));
        Assertions.assertEquals(4f, tsr.getHour(TUE));
        Assertions.assertEquals(5f, tsr.getHour(WED));
        Assertions.assertEquals(6f, tsr.getHour(THU));
        Assertions.assertEquals(7f, tsr.getHour(FRI));

        //exceptions
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setHour(MON, 24.55f));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setHour(MON, 25f));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setHour(MON, -1f));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setHour(MON, 24.1f));
    }

    @Test
    final void testGetDecihour() {
        tsr.setHours(0x07060504030201L);
        assertEquals(1, tsr.getDeciHour(SAT));
        assertEquals(2, tsr.getDeciHour(SUN));
        assertEquals(3, tsr.getDeciHour(MON));
        assertEquals(4, tsr.getDeciHour(TUE));
        assertEquals(5, tsr.getDeciHour(WED));
        assertEquals(6, tsr.getDeciHour(THU));
        assertEquals(7, tsr.getDeciHour(FRI));
        tsr.setHours(0x463C32281E140AL);
        assertEquals(10, tsr.getDeciHour(SAT));
        assertEquals(20, tsr.getDeciHour(SUN));
        assertEquals(30, tsr.getDeciHour(MON));
        assertEquals(40, tsr.getDeciHour(TUE));
        assertEquals(50, tsr.getDeciHour(WED));
        assertEquals(60, tsr.getDeciHour(THU));
        assertEquals(70, tsr.getDeciHour(FRI));

        // These two lines cannot pass
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.getDeciHour(-1));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.getDeciHour(7));
    }

    @Test
    final void testSetDecihour() {
        int bdeci = 23;
        tsr.setDeciHour(MON, bdeci);
        assertEquals(bdeci, tsr.getDeciHour(MON));

        tsr.setDeciHour(MON, 23);
        assertEquals(bdeci, tsr.getDeciHour(MON));

        tsr.setDeciHour(MON, 240);
        assertEquals(240, tsr.getDeciHour(MON));
        tsr.setDeciHour(SAT, 0);
        assertEquals(0, tsr.getDeciHour(SAT));

        tsr.setHours(0x463C32281E140AL);
        tsr.setDeciHour(SAT, 23);
        assertEquals(23, tsr.getDeciHour(SAT));
        assertEquals(20, tsr.getDeciHour(SUN));
        assertEquals(30, tsr.getDeciHour(MON));
        assertEquals(40, tsr.getDeciHour(TUE));
        assertEquals(50, tsr.getDeciHour(WED));
        assertEquals(60, tsr.getDeciHour(THU));
        assertEquals(70, tsr.getDeciHour(FRI));

        tsr.setHours(0x463C32281E140AL);
        tsr.setDeciHour(SUN, 23);
        assertEquals(23, tsr.getDeciHour(SUN));
        assertEquals(10, tsr.getDeciHour(SAT));
        assertEquals(30, tsr.getDeciHour(MON));
        assertEquals(40, tsr.getDeciHour(TUE));
        assertEquals(50, tsr.getDeciHour(WED));
        assertEquals(60, tsr.getDeciHour(THU));
        assertEquals(70, tsr.getDeciHour(FRI));

        tsr.setHours(0x463C32281E140AL);
        tsr.setDeciHour(MON, 23);
        assertEquals(23, tsr.getDeciHour(MON));
        assertEquals(10, tsr.getDeciHour(SAT));
        assertEquals(20, tsr.getDeciHour(SUN));
        assertEquals(40, tsr.getDeciHour(TUE));
        assertEquals(50, tsr.getDeciHour(WED));
        assertEquals(60, tsr.getDeciHour(THU));
        assertEquals(70, tsr.getDeciHour(FRI));

        tsr.setHours(0x463C32281E140AL);
        tsr.setDeciHour(TUE, 23);
        assertEquals(23, tsr.getDeciHour(TUE));
        assertEquals(10, tsr.getDeciHour(SAT));
        assertEquals(20, tsr.getDeciHour(SUN));
        assertEquals(30, tsr.getDeciHour(MON));
        assertEquals(50, tsr.getDeciHour(WED));
        assertEquals(60, tsr.getDeciHour(THU));
        assertEquals(70, tsr.getDeciHour(FRI));

        tsr.setHours(0x463C32281E140AL);
        tsr.setDeciHour(WED, 23);
        assertEquals(23, tsr.getDeciHour(WED));
        assertEquals(10, tsr.getDeciHour(SAT));
        assertEquals(20, tsr.getDeciHour(SUN));
        assertEquals(30, tsr.getDeciHour(MON));
        assertEquals(40, tsr.getDeciHour(TUE));
        assertEquals(60, tsr.getDeciHour(THU));
        assertEquals(70, tsr.getDeciHour(FRI));

        tsr.setHours(0x463C32281E140AL);
        tsr.setDeciHour(THU, 23);
        assertEquals(23, tsr.getDeciHour(THU));
        assertEquals(10, tsr.getDeciHour(SAT));
        assertEquals(20, tsr.getDeciHour(SUN));
        assertEquals(30, tsr.getDeciHour(MON));
        assertEquals(40, tsr.getDeciHour(TUE));
        assertEquals(50, tsr.getDeciHour(WED));
        assertEquals(70, tsr.getDeciHour(FRI));

        tsr.setHours(0x463C32281E140AL);
        tsr.setDeciHour(FRI, 23);
        assertEquals(23, tsr.getDeciHour(FRI));
        assertEquals(10, tsr.getDeciHour(SAT));
        assertEquals(20, tsr.getDeciHour(SUN));
        assertEquals(30, tsr.getDeciHour(MON));
        assertEquals(40, tsr.getDeciHour(TUE));
        assertEquals(50, tsr.getDeciHour(WED));
        assertEquals(60, tsr.getDeciHour(THU));

        //exceptions
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setDeciHour(SAT - 1, 245));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setDeciHour(MON, 245));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setDeciHour(MON, 250));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setDeciHour(MON, -1));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setDeciHour(MON, 241));
    }

    @Test
    final void testGetHours() {
        float[] a1 = {.1f, .2f, .3f, .4f, .5f, .6f, .7f};
        float[] a2 = {1f, 2f, 3f, 4f, 5f, 6f, 7f};
        tsr.setHours(0x07060504030201L);
        assertArrayEquals(a1, tsr.getHoursArray());
        tsr.setHours(0x463C32281E140AL);
        assertArrayEquals(a2, tsr.getHoursArray());

        tsr.setHours(packedHours);
        assertArrayEquals(hours, tsr.getHoursArray());
        tsr.setHours(packedHours2);
        assertArrayEquals(hours2, tsr.getHoursArray());
        tsr.setHours(packedHours3);
        assertArrayEquals(hours3, tsr.getHoursArray());
    }

    @Test
    final void testSetHours() {
        float[] a1 = {.1f, .2f, .3f, .4f, .5f, .6f, .7f};
        float[] a2 = {1f, 2f, 3f, 4f, 5f, 6f, 7f};
        tsr.setHoursArray(a1);
        assertEquals(0x07060504030201L, tsr.getHours());
        tsr.setHoursArray(a2);
        assertEquals(0x463C32281E140AL, tsr.getHours());

        tsr.setHoursArray(hours);
        assertEquals(packedHours, tsr.getHours());
        tsr.setHoursArray(hours2);
        assertEquals(packedHours2, tsr.getHours());
        tsr.setHoursArray(hours3);
        assertEquals(packedHours3, tsr.getHours());

        final float[] hours = {8.1f, -2f, 2.3f, 2.7f, 4f, 4f, 7f};
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> tsr.setHoursArray(hours));
    }

    @Test
    final void testToString() {
        Assertions.assertEquals("1001 A test package [0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]",
                tsr.toString());
        tsr.setHours(packedHours);
        Assertions.assertEquals("1001 A test package [8.0, 2.0, 2.3, 2.7, 4.0, 4.0, 7.0]",
                tsr.toString());
        tsr.setHours(packedHours2);
        Assertions.assertEquals("1001 A test package [0.0, 0.0, 8.0, 8.0, 4.0, 4.0, 8.0]",
                tsr.toString());
        tsr.setHours(packedHours3);
        Assertions.assertEquals("1001 A test package [0.1, 0.1, 8.2, 8.2, 4.4, 4.0, 8.0]",
                tsr.toString());

    }
}
