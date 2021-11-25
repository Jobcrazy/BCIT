package ca.bcit.infosys.model.timesheet;

import ca.bcit.infosys.model.employee.EmployeeModel;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * The persistent class for the Timesheet database table.
 *
 */

@Table(name = "timesheet")
@Entity
public class TimesheetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="employeeNumber", nullable = false)
    private EmployeeModel employee;

    @Column(name = "endDate", nullable = false)
    private LocalDate endDate;

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}