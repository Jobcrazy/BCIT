package com.corejsf;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

@Named("problemReport")
@SessionScoped
public class ProblemReport implements Serializable {
    private static final long serialVersionUID = 1L;

    private enum Priority {LOW, MEDIUM, HIGH, CRITICAL}

    @Inject
    private Project project;

    private String title;
    private Integer number;
    private String dateIssue;
    private String Originator;
    private String modulesAffected;
    private String description;
    private String recommendedSolution;
    private String dateAssigned;
    private String dateFixed;
    private Priority priority;
    private Priority severity;
    private Boolean inScope = false;

    private final Map<String, Priority> priorityItems;

    ProblemReport() {
        priorityItems = new LinkedHashMap<>();
        priorityItems.put("", null); // label, value
        priorityItems.put("Low", Priority.LOW); // label, value
        priorityItems.put("Median", Priority.MEDIUM);
        priorityItems.put("High", Priority.HIGH);
        priorityItems.put("Critical", Priority.CRITICAL);
    }

    public Map<String, Priority> getPriorityItems() {
        return priorityItems;
    }

    public Project getProject() {
        return project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(String dateIssue) {
        this.dateIssue = dateIssue;
    }

    public String getOriginator() {
        return Originator;
    }

    public void setOriginator(String originator) {
        Originator = originator;
    }

    public String getModulesAffected() {
        return modulesAffected;
    }

    public void setModulesAffected(String modulesAffected) {
        this.modulesAffected = modulesAffected;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecommendedSolution() {
        return recommendedSolution;
    }

    public void setRecommendedSolution(String recommendedSolution) {
        this.recommendedSolution = recommendedSolution;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Priority getSeverity() {
        return severity;
    }

    public void setSeverity(Priority severity) {
        this.severity = severity;
    }

    public String getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(String dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public String getDateFixed() {
        return dateFixed;
    }

    public void setDateFixed(String dateFixed) {
        this.dateFixed = dateFixed;
    }

    public String submit() {
        return "success";
    }

    public Boolean getInScope() {
        return inScope;
    }

    public void setInScope(Boolean inScope) {
        this.inScope = inScope;
    }

    public String newProblem() {
        project.setName("");
        title = "";
        number = null;
        dateIssue = "";
        Originator = "";
        modulesAffected = "";
        description = "";
        recommendedSolution = "";
        dateAssigned = "";
        dateFixed = "";
        priority = Priority.MEDIUM;
        severity = Priority.MEDIUM;
        inScope = false;
        return "new";
    }

    public void validatePriority(FacesContext context,
                                 UIComponent component,
                                 Object value) throws ValidatorException {
        if (null == value) {
            FacesMessage msg = new FacesMessage(
                    "You must choose a priority.");
            throw new ValidatorException(msg);
        }

        String priority = value.toString();
        if (!priority.equals("LOW") &&
                !priority.equals("MEDIAN") &&
                !priority.equals("HIGH") &&
                !priority.equals("CRITICAL")
        ) {
            FacesMessage msg = new FacesMessage(
                    "Priority can only be Low/Median/High/Critical");
            throw new ValidatorException(msg);
        }
    }

    public void validateSeverity(FacesContext context,
                                 UIComponent component,
                                 Object value) throws ValidatorException {
        if (null == value) {
            FacesMessage msg = new FacesMessage(
                    "You must choose a severity.");
            throw new ValidatorException(msg);
        }

        String priority = value.toString();
        if (!priority.equals("LOW") &&
                !priority.equals("MEDIAN") &&
                !priority.equals("HIGH")
        ) {
            FacesMessage msg = new FacesMessage(
                    "Severity can only be Low/Median/High");
            throw new ValidatorException(msg);
        }
    }
}
