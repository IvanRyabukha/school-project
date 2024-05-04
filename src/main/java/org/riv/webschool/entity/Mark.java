package org.riv.webschool.entity;

import java.util.Objects;

public class Mark {

    private Long id;
    private Long studentId;
    private Integer subjectId;
    private Integer value;
    private Integer gradeId;

    public Mark() {
    }

    public Mark(Long id, Long studentId, Integer subjectId, Integer value, Integer gradeId) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.value = value;
        this.gradeId = gradeId;
    }

    public Mark(Long studentId, Integer subjectId, Integer value) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", subjectId=" + subjectId +
                ", value=" + value +
                ", gradeId=" + gradeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        return Objects.equals(id, mark.id) && Objects.equals(studentId, mark.studentId) && Objects.equals(subjectId, mark.subjectId) && Objects.equals(value, mark.value) && Objects.equals(gradeId, mark.gradeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, subjectId, value, gradeId);
    }
}
