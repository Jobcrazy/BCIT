package ca.bcit.infosys.model.timesheet;

import javax.persistence.*;

/**
 * The persistent class for the TimesheetRow database table.
 *
 */

@Table(name = "timesheetrow", indexes = {
        @Index(name = "timesheetId", columnList = "timesheetId")
})
@Entity
public class TimesheetRowModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="timesheetId", nullable = false)
    private TimesheetModel timesheet;

    @Column(name = "projectId", nullable = false)
    private Integer projectId;

    @Column(name = "workPackageId", length = 80, nullable = false)
    private String workPackageId;

    @Column(name = "packedHours", nullable = false)
    private Long packedHours;

    @Column(name = "notes", length = 120, nullable = false)
    private String notes;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getPackedHours() {
        return packedHours;
    }

    public void setPackedHours(Long packedHours) {
        this.packedHours = packedHours;
    }

    public String getWorkPackageId() {
        return workPackageId;
    }

    public void setWorkPackageId(String workPackageId) {
        this.workPackageId = workPackageId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public TimesheetModel getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(TimesheetModel timesheet) {
        this.timesheet = timesheet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}