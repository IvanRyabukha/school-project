package org.riv.webschool.entity;

public class Teacher {
    private String firstName;
    private String lastName;
    private Subject subject;
    private Long teacherId;
    private String passport;

    public Teacher(String firstName, String lastName, Subject subject, Long teacherId, String passportSeries) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
        this.teacherId = teacherId;
        this.passport = passportSeries;
    }

    public Teacher(String firstName, String lastName, Subject subject, String passportSeries) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
        this.passport = passportSeries;
    }

    public Teacher() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", subject=" + subject +
                ", teacherId=" + teacherId +
                ", passportSeries='" + passport + '\'' +
                '}';
    }
}
