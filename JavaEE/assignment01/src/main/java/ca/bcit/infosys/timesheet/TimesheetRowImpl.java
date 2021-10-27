package ca.bcit.infosys.timesheet;

/**
 * subclass of TimesheetRow.
 *
 * @author Hang Liu & Joon Kim
 * @version 1.1
 */

public class TimesheetRowImpl extends TimesheetRow{
    /** the work hours array for edit */
    private float []hours = new float[TimesheetRow.FRI - TimesheetRow.SAT + 1];

    /**
     * Set the hours for a given day. Rounded to one decimal.
     * @param d the day number (0 = Saturday .. 6 = Friday)
     * @param charge hours charged for that day
     * @throws IllegalArgumentException if charge < 0 or > 24
     */
    @Override
    public void setHour(int d, float charge) {
        super.setHour(d, charge);
        hours[d] = charge;
    }

    /**
     * Extract the hours for a given day.
     * @param d the day number (0 = Saturday .. 6 = Friday)
     * @return hours for that day
     */
    @Override
    public float getHour(int d) {
        return super.getHour(d);
    }

    /**
     * Get hours array of charges, index is day number.
     * @return hours as array of charges
     */
    @Override
    public float[] getHours() {
        return hours;
    }

    /**
     *  Convert hours array to packed hours and store in hours field.
     *  Index of array is day of week number, starting with Saturday
     * @param charges array of hours to pack (single fractional digit)
     * @throws IllegalArgumentException if charges < 0 or > 24
     */
    @Override
    public void setHours(float[] charges) {
        this.hours = charges;
    }
}
