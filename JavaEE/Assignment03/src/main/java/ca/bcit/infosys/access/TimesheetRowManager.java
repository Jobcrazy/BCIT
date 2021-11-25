package ca.bcit.infosys.access;

import ca.bcit.infosys.model.timesheet.TimesheetModel;
import ca.bcit.infosys.model.timesheet.TimesheetRowModel;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Handle CRUD actions for TimesheetRow class.
 *
 * @author Hang Liu
 * @version 1.0
 */

@Dependent
@Stateless
public class TimesheetRowManager implements Serializable {
    @PersistenceContext(unitName = "timesheet-jpa")
    EntityManager em;

    /**
     * Persist TimesheetRow record into database. id must be unique.
     *
     * @param timesheetRow the record to be persisted.
     */
    public void persist(TimesheetRowModel timesheetRow) {
        em.persist(timesheetRow);
    }

    /**
     * Find TimesheetRow record from database.
     *
     * @param id primary key for record.
     * @return the TimesheetRow record list with key = id.
     */
    public TimesheetRowModel find(Integer id) {
        return em.find(TimesheetRowModel.class, id);
    }

    /**
     * Find TimesheetRow record from database.
     *
     * @param timesheet the timesheet.
     * @return the TimesheetRow record list with given timesheet.
     */
    public ArrayList<TimesheetRowModel> find(TimesheetModel timesheet) {
        TypedQuery<TimesheetRowModel> query = em.createQuery(
                "SELECT r from TimesheetRowModel r WHERE " +
                        "r.timesheet = :timesheet ORDER BY r.id ASC",
                TimesheetRowModel.class);
        query.setParameter("timesheet", timesheet);
        return new ArrayList<>(query.getResultList());
    }

    /**
     * merge TimesheetRow record fields into existing database record.
     *
     * @param timesheetRow the record to be merged.
     */
    public void merge(TimesheetRowModel timesheetRow) {
        em.merge(timesheetRow);
    }

    /**
     * Remove TimesheetRow from database.
     *
     * @param timesheetRow record to be removed from database
     */
    public void remove(TimesheetRowModel timesheetRow) {
        timesheetRow = em.find(TimesheetRowModel.class, timesheetRow.getId());
        em.remove(timesheetRow);
    }
}
