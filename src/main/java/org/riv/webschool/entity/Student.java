package org.riv.webschool.entity;


public class Student {
    private String firstName;
    private String lastName;
    private Long studentId;
    private Integer gradeId;
    private String birthCertificate;


    public Student(String firstName, String lastName, Integer gradeId, Long studentId, String birthCertificate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gradeId = gradeId;
        this.studentId = studentId;
        this.birthCertificate = birthCertificate;
    }

    public Student(String firstName, String lastName, Integer gradeId, String birthCertificate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gradeId = gradeId;
        this.birthCertificate = birthCertificate;
    }

    public Student() {
    }

    public String getBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(String birthCertificate) {
        this.birthCertificate = birthCertificate;
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getGrade() {
        return gradeId;
    }

    public void setGrade(Integer grade) {
        this.gradeId = gradeId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentId=" + studentId +
                ", grade='" + gradeId + '\'' +
                '}';
    }
}
