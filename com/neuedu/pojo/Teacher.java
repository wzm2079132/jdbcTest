package com.neuedu.pojo;

public class Teacher {
    private Integer id;
    private String tname;
    private String tpsw;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTpsw() {
        return tpsw;
    }

    public void setTpsw(String tpsw) {
        this.tpsw = tpsw;
    }

    public Teacher() {
    }

    public Teacher(String tname, String tpsw) {
        this.tname = tname;
        this.tpsw = tpsw;
    }
}
