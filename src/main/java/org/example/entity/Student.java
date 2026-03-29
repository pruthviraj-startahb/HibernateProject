package org.example.entity;

import javax.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
public class Student{

    @Id
    private int roll;

    @Column(name = "student_Name")
    private String sname;

    @Column(name = "Department")
    private String dept;

    @Column(name = "College")
    private String clg;

    @Transient
    private String div;

    public Student() {
    }

    public Student(int roll, String sname, String dept, String clg,String div) {
        this.roll = roll;
        this.sname = sname;
        this.dept = dept;
        this.clg = clg;
        this.div=div;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getClg() {
        return clg;
    }

    public void setClg(String clg) {
        this.clg = clg;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    @Override
    public String toString() {
        return "Student{" +
                "roll=" + roll +
                ", sname='" + sname + '\'' +
                ", dept='" + dept + '\'' +
                ", clg='" + clg + '\'' +
                ", div='" + div + '\'' +
                '}';
    }
}
