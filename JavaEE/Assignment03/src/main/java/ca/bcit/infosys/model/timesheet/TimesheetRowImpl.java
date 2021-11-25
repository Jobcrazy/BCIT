package ca.bcit.infosys.model.timesheet;

/**
 * subclass of TimesheetRow.
 *
 * @author Hang Liu
 * @version 1.2
 */

public class TimesheetRowImpl extends TimesheetRow {
    /**
     * The unique id of the TimesheetRow
     */
    Integer id;

    /**
     * the work hours array for edit
     */
    private float[] hours = new float[TimesheetRow.FRI - TimesheetRow.SAT + 1];

    /**
     * the default constructor
     */
    public TimesheetRowImpl() {
    }

    /**
     * the overloaded constructor
     *
     * @param id            the unique id of the TimesheetRow
     * @param projectId     the project id
     * @param workPackageId the work package id
     * @param notes         the note for the timesheet row
     * @param packedHours         the compact hours of the row
     */
    public TimesheetRowImpl(int id, int projectId,
                            String workPackageId,
                            String notes,
                            long packedHours) {
        super(projectId, workPackageId);
        this.id = id;
        setNotes(notes);
        setPackedHours(packedHours);

        for (int n = 0; n < hours.length; ++n) {
            hours[n] = getHour(n);
        }
    }

    /**
     * getter for id
     *
     * @return the timesheet row id
     */
    public Integer getId() {
        return id;
    }

    /**
     * setter for id
     *
     * @param id the timesheet row id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Set the hours for a given day. Rounded to one decimal.
     *
     * @param d      the day number (0 = Saturday .. 6 = Friday)
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
     *
     * @param d the day number (0 = Saturday .. 6 = Friday)
     * @return hours for that day
     */
    @Override
    public float getHour(int d) {
        return super.getHour(d);
    }

    /**
     * Get hours array of charges, index is day number.
     *
     * @return hours as array of charges
     */
    @Override
    public float[] getHours() {
        return hours;
    }

    /**
     * Convert hours array to packed hours and store in hours field.
     * Index of array is day of week number, starting with Saturday
     *
     * @param charges array of hours to pack (single fractional digit)
     * @throws IllegalArgumentException if charges < 0 or > 24
     */
    @Override
    public void setHours(float[] charges) {
        super.setHours(charges);
        this.hours = charges;
    }
}
