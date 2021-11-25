package ca.bcit.infosys.access;

import ca.bcit.infosys.model.token.TokenModel;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Handle CRUD actions for EmployeeModel class.
 *
 * @author Hang Liu
 * @version 1.0
 */

@Dependent
@Stateless
public class TokenManager implements Serializable {
    @PersistenceContext(unitName = "timesheet-jpa")
    EntityManager em;

    /**
     * Persist Employee record into database. id must be unique.
     *
     * @param tokenModel the record to be persisted.
     */
    public void persist(TokenModel tokenModel) {
        em.persist(tokenModel);
    }

    /**
     * Find Employee record from database.
     *
     * @param token the token for the employee.
     * @return the Employee record list with given username.
     */
    public ArrayList<TokenModel> find(String token) {
        TypedQuery<TokenModel> query = em.createQuery(
                "SELECT t from TokenModel t WHERE t.token = :token",
                TokenModel.class);
        query.setParameter("token", token);
        return new ArrayList<>(query.getResultList());
    }
}
