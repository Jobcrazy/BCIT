package ca.bcit.infosys.model.token;

import ca.bcit.infosys.model.employee.EmployeeModel;

import javax.persistence.*;

/**
 * The persistent class for the Token database table.
 *
 */

@Table(name = "token", indexes = {
        @Index(name = "employeeNumber", columnList = "employeeNumber"),
        @Index(name = "token", columnList = "token")
})
@Entity
public class TokenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employeeNumber", nullable = false)
    private EmployeeModel employee;

    @Column(name = "token", nullable = false, length = 40)
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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