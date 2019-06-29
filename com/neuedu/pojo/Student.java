package com.neuedu.pojo;

public class Student {
    private Integer id;
    private String sno;
    private String sname;
    private String spsw;
    private int sage;
    private String ssex;
    private int sheight;
    private int sweight;
    private String sbirthday;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSpsw() {
        return spsw;
    }

    public void setSpsw(String spsw) {
        this.spsw = spsw;
    }

    public int getSage() {
        return sage;
    }

    public void setSage(int sage) {
        this.sage = sage;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public int getSheight() {
        return sheight;
    }

    public void setSheight(int sheight) {
        this.sheight = sheight;
    }

    public int getSweight() {
        return sweight;
    }

    public void setSweight(int sweight) {
        this.sweight = sweight;
    }

    public String getSbirthday() {
        return sbirthday;
    }

    public void setSbirthday(String sbirthday) {
        this.sbirthday = sbirthday;
    }

    public Student() {
    }

    public Student(String sname, String spsw) {
        this.sname = sname;
        this.spsw = spsw;
    }

    public Student(Integer id, String sno, String sname, String spsw, int sage, String ssex, int sheight, int sweight, String sbirthday) {
        this.id = id;
        this.sno = sno;
        this.sname = sname;
        this.spsw = spsw;
        this.sage = sage;
        this.ssex = ssex;
        this.sheight = sheight;
        this.sweight = sweight;
        this.sbirthday = sbirthday;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", spsw='" + spsw + '\'' +
                ", sage=" + sage +
                ", ssex='" + ssex + '\'' +
                ", sheight=" + sheight +
                ", sweight=" + sweight +
                ", sbirthday='" + sbirthday + '\'' +
                '}';
    }
}
