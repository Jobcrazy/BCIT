package ca.bcit.comp1510.timesheet;

import java.util.Arrays;

/**
 * Details for a timesheet.  Contains the charges for one week 
 * for a project/work package combination.
 * @author blink
 * @version 1.0
 */
public class TimesheetRow {
    
    /** Day number for Saturday. */
    public static final int SAT = 0;

    /** Day number for Sunday. */
    public static final int SUN = 1;

    /** Day number for Monday. */
    public static final int MON = 2;

    /** Day number for Tuesday. */
    public static final int TUE = 3;

    /** Day number for Wednesday. */
    public static final int WED = 4;

    /** Day number for Thursday. */
    public static final int THU = 5;

    /** Day number for Friday. */
    public static final int FRI = 6;

    /** Day number for Friday. */
    private static final int FIRST_DAY = SAT;

    /** Day number for Friday. */
    private static final int LAST_DAY = FRI;

    /** mask for packing, unpacking hours. */
    private static final long[] MASK = {0xFFL, 
                                        0xFF00L, 
                                        0xFF0000L, 
                                        0xFF000000L,
                                        0xFF00000000L, 
                                        0xFF0000000000L, 
                                        0xFF000000000000L};
    
    /** mask for packing, unpacking hours. */
    private static final long[] UMASK = {0xFFFFFFFFFFFFFF00L, 
                                         0xFFFFFFFFFFFF00FFL, 
                                         0xFFFFFFFFFF00FFFFL, 
                                         0xFFFFFFFF00FFFFFFL,
                                         0xFFFFFF00FFFFFFFFL, 
                                         0xFFFF00FFFFFFFFFFL, 
                                         0xFF00FFFFFFFFFFFFL};
    
    /** 2**8. */
    private static final long BYTE_BASE = 256;
    
    /** decimal base in float. */
    private static final float BASE = 10.0F;
    
    /** max number of deci-hours per day. */
    private static final int DECI_MAX = 240;
    
    /** how far off a fraction can be to be considered 
     * one fractional digit.
     */
    private static final float THRESHOLD = 0.0001F;
    
    /** number of bits in a byte. */
    private static final int BITS_PER_BYTE = 8;

    /** project.*/
    private int project;
    
    /** Work package.*/
    private String workPackage;
    
    /** hours packed into a long.
     * Each of the low-order 7 bytes is the decihours for one day.
     * Order is 00-FR-TH-WE-TU-MO-SU-SA
     * Counting days d = 0 .. 6 (starting at Saturday) the position of 
     * a day's byte is shifted d * 8 bits to the left.
     */
    private long hours;

    /** create empty timesheetRow to be modified later.*/
    public TimesheetRow() {
        
    }
    
    /**
     * Initialize timesheet row with instance data, no hours charged.
     * @param project project number
     * @param workPackage work package id
     */
    public TimesheetRow(int project, String workPackage) {
        this.project = project;
        this.workPackage = workPackage;
    }
    
    /**
     * getter for project number.
     * @return the project
     */
    public int getProject() {
        return project;
    }

    /**
     * setter for project number.
     * @param project the project to set
     */
    public void setProject(int project) {
        this.project = project;
    }

    /**
     * Getter for work pack id.
     * @return the workPackage
     */
    public String getWorkPackage() {
        return workPackage;
    }

    /**
     * setter for work project id.
     * @param workPackage the workPackage to set
     */
    public void setWorkPackage(String workPackage) {
        this.workPackage = workPackage;
    }

    /**
     * getter for packed hours.
     * @return the hours
     */
    public long getHours() {
        return hours;
    }

    /**
     * setter for packed hours.
     * @param hours the hours to set
     */
    public void setHours(long hours) {
        long check = hours;
        for (int i = FIRST_DAY; i <= LAST_DAY; i++) {
            if (check % BYTE_BASE > DECI_MAX) {
                throw new IllegalArgumentException("hours malformed");
            }
            check /= BYTE_BASE;
        }
        this.hours = hours;
    }
    
    /**
     * Extract the hours for a given day.
     * @param d the day number (0 = Saturday .. 6 = Friday)
     * @return hours for that day
     */
    public float getHour(int d) {
        return getDeciHour(d) / BASE;
    }
    
    /**
     * Set the hours for a given day.
     * @param d the day number (0 = Saturday .. 6 = Friday)
     * @param charge hours charged for that day
     */
    public void setHour(int d, float charge) {
        if (!oneFractionDigit(charge)) {
            throw new IllegalArgumentException("charge has more than " 
                        + "one fractional digit");           
        }
        setDeciHour(d, Math.round(charge * BASE));
    }
    
    private boolean oneFractionDigit(float value) {
        float fractionResidue = 
                Math.abs(value * BASE - Math.round(value * BASE));
        return fractionResidue < THRESHOLD;
    }
    
    /**
     * Extract the integer hours * 10 for a given day.
     * @param d the day number (0 = Saturday .. 6 = Friday)
     * @return hours for that day
     */
    public int getDeciHour(int d) {
        if (d < FIRST_DAY || d > LAST_DAY) {
            throw new IllegalArgumentException("day number out of range");
        }
        return (int) ((hours & MASK[d]) >> d * BITS_PER_BYTE);
    }
    
    /**
     * Set the integer hours * 10 for a given day.
     * @param d the day number (0 = Saturday .. 6 = Friday)
     * @param charge hours charged for that day
     */
    public void setDeciHour(int d, int charge) {
        if (d < FIRST_DAY || d > LAST_DAY) {
            throw new IllegalArgumentException("day number out of range, " 
                        + "must be in 0 .. 6");
        }
        if (charge < 0 || charge > DECI_MAX) {
            throw new IllegalArgumentException("charge out of range, " 
                        + "must be 0 .. 240");
        }
        hours = hours & UMASK[d] | (long)charge << (d * BITS_PER_BYTE);
    }
    
    /**
     * Get hours array of charges, index is day number.
     * @return hours as array of charges
     */
    public float[] getHoursArray() {
        float[] result = new float[LAST_DAY + 1];
        long check = hours;
        for (int i = FIRST_DAY; i <= LAST_DAY; i++) {
            result[i] = check % BYTE_BASE / BASE;
            check /= BYTE_BASE;
        }
        return result;
    }
    
    /**
     *  Convert hours array to packed hours and store in hours field.
     *  Index of array is day of week number, starting with Saturday
     * @param charges array of hours to pack (single fractional digit)
     */
    public void setHoursArray(float[] charges) {
        for (float charge : charges) {
            if (!oneFractionDigit(charge)) {
                throw new IllegalArgumentException("charge has more than " 
                            + "one fractional digit");           
            }
        }
        long result = 0;
        for (int i = LAST_DAY; i >= FIRST_DAY; i--) {
            result = result * BYTE_BASE + Math.round(charges[i] * BASE);
        }
        hours = result;
    }
    
    /**
     * Return printable string version of timesheet row.
     * @return the string
     */
    public String toString() {
        return project + " " + workPackage + " " + Arrays.toString(getHoursArray());
    }
    
}
