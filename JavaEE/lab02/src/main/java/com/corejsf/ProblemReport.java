package com.corejsf;

import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;

@Named("problemReport")
@SessionScoped
public class ProblemReport implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private Project project;
    @Inject
    private Conversation conversation;

    private Integer number;
    private String dateIssue;
    private String Originator;
    private String modulesAffected;
    private String description;
    private String recommendedSolution;
    private String priority;
    private String severity;
    private String dateAssigned;
    private String dateFixed;

    @Inject @MyAnnotation
    private MyInterface instance;

    public ProblemReport() {
        System.out.println("problem created!");
    }

    public MyInterface getInstance() {
        return instance;
    }

    public Project getProject() {
        return project;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
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
}
