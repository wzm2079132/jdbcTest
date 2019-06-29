package com.neuedu.test;

import com.neuedu.pojo.Student;
import com.neuedu.pojo.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    Scanner input = new Scanner(System.in);
    public void showMenu() {

        System.out.println("欢迎使用东软小学学生管理系统");
        System.out.println("请选择1 学生登陆  2 教师登陆");
        int sj=input.nextInt();
        if (sj==1){
            System.out.println("请输入学生姓名");
            String sname=input.next();
            System.out.println("请输入学生密码");
            String spsw=input.next();
            seleS(sname,spsw);

            System.out.println("请选择进行的操作");
            System.out.println("1 查看自己的信息");
            System.out.println("2 退出系统");
            int s1=input.nextInt();
            if (s1==1){
                seleMy(sname);
            }else if (s1==2){
                exit();
            }



        }else if (sj==2){
            System.out.println("请输入教师姓名");
            String tname=input.next();
            System.out.println("请输入教师密码");
            String tpsw=input.next();
            seleT(tname,tpsw);

            System.out.println("请选择进行的操作");
            System.out.println("1 查看全部学生信息");
            System.out.println("2 查看特定学生信息");
            System.out.println("3 增加学生信息");
            System.out.println("4 修改学生信息");
            System.out.println("5 删除学生信息");
            System.out.println("6 退出系统");
            int t1=input.nextInt();
            switch (t1){
                case 1:
                    seleAll();
                    break;
                case 2:
                    seleOne();
                    break;
                case 3:
                    add();
                    break;
                case 4:
                    upd();
                    break;
                case 5:
                    del();
                    break;
                case 6:
                    exit();
                    break;
            }
        }





    }

    //查询学生账号
    public void seleS(String name,String psw){

        List<Student> list=JdbcMethod.executeQuery("select * from student where sname=? and spsw=?", new RowMap<Student>() {
            @Override
            public Student rowMap(ResultSet rSet) {
                Student student=null;
                try {
                    String name=rSet.getNString("sname");
                    String psw=rSet.getString("spsw");
                    student=new Student(name,psw);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return student;
            }
        },name,psw);

        if (list.get(0).getSname()!=null&&list.get(0).getSpsw()!=null){
            System.out.println("欢迎"+name+"同学");
        }else {
            System.out.println("请输入正确的用户名和密码");
        }
    }

    //查询教师
    public void seleT(String name,String psw){

        List<Teacher> list=JdbcMethod.executeQuery("select * from student where sname=? and spsw=?", new RowMap<Teacher>() {
            @Override
            public Teacher rowMap(ResultSet rSet) {
                Teacher teacher=null;
                try {
                    String name=rSet.getNString("sname");
                    String psw=rSet.getString("spsw");
                    teacher=new Teacher(name,psw);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return teacher;
            }
        },name,psw);

        if (list.get(0).getTname()!=null&&list.get(0).getTpsw()!=null){
            System.out.println("欢迎"+name+"教师");
        }else {
            System.out.println("请输入正确的用户名和密码");
        }
    }


    //查询自己
    public void seleMy(String name){
        List<Student> list=JdbcMethod.executeQuery("select * from student where sno=?", new RowMap<Student>() {
            @Override
            public Student rowMap(ResultSet rSet) {
                Student student=null;
                try {
                    Integer id=rSet.getInt("id");
                    String sno=rSet.getString("sno");
                    String sname=rSet.getString("sname");
                    String spsw=rSet.getString("spsw");
                    int sage=rSet.getInt("sage");
                    String ssex=rSet.getString("ssex");
                    int sheight=rSet.getInt("sheight");
                    int sweight=rSet.getInt("sweight");
                    String sbirthday=rSet.getString("sborthday");
                    student=new Student(id,sno,sname,spsw,sage,ssex,sheight,sweight,sbirthday);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return student;
            }
        },name );

        for (Student s:list
        ) {
            System.out.println(s);
        }
    }





    //增加
    public void add(){
        System.out.println("请输入学生信息");
        System.out.println("请输入学生学号");
        String sno=input.next();
        System.out.println("请输入学生姓名");
        String sname=input.next();
        System.out.println("请输入学生密码");
        String spsw=input.next();
        System.out.println("请输入学生年龄");
        int sage=input.nextInt();
        System.out.println("请输入学生性别");
        String ssex=input.next();
        System.out.println("请输入学生身高/厘米");
        int sheight=input.nextInt();
        System.out.println("请输入学生体重/公斤");
        int sweight=input.nextInt();
        System.out.println("请输入学生生日");
        String sbirthday=input.next();
        Student student=new Student(null,sno,sname,spsw,sage,ssex,sheight,sweight,sbirthday);
        int result=JdbcMethod.executeUpdate("insert into student(sno,sname,spsw,sage,ssex,sheight,sweight,sbirthday) values(?,?,?,?,?,?,?,?)",
                student.getSno(),student.getSname(),student.getSpsw(),student.getSage(),student.getSsex(),student.getSheight(),student.getSweight(),student.getSbirthday());
        if (result==1){
            System.out.println("操作完成");
        }else {
            System.out.println("操作未成功");
        }
    }

    //删除
    public void del(){
        Student student=new Student();
        System.out.println("请输入要删除的学生的学号");
        String sno=input.next();
        student.setSno(sno);
        int result=JdbcMethod.executeUpdate("delete from student where sno=?",student.getSname());
        if (result==1){
            System.out.println("操作完成");
        }else {
            System.out.println("操作未成功");
        }
    }

    //修改
    public void upd(){
        System.out.println("请输入要修改的学生学号");
        String sno=input.next();
        System.out.println("请输入学生信息");
        System.out.println("请输入学生姓名");
        String sname=input.next();
        System.out.println("请输入学生密码");
        String spsw=input.next();
        System.out.println("请输入学生年龄");
        int sage=input.nextInt();
        System.out.println("请输入学生性别");
        String ssex=input.next();
        System.out.println("请输入学生身高/厘米");
        int sheight=input.nextInt();
        System.out.println("请输入学生体重/公斤");
        int sweight=input.nextInt();
        System.out.println("请输入学生生日");
        String sbirthday=input.next();
        Student student=new Student(null,sno,sname,spsw,sage,ssex,sheight,sweight,sbirthday);
        int result=JdbcMethod.executeUpdate("update set sname=?,spsw=?,sage=?,ssex=?,sheight=?,sweight=?,sbirthday=?",student.getSname(),student.getSpsw(),student.getSage(),student.getSsex(),student.getSheight(),student.getSweight(),student.getSbirthday());
        if (result==1){
            System.out.println("操作完成");
        }else {
            System.out.println("操作未成功");
        }
    }

    //查询全部
    public void seleAll(){
        /*System.out.println("请选择1 全部查询  2 特定查询");
        int a=input.nextInt();
        if (a==1){
            String sql="select * from student";

        }else if (a==2){
            System.out.println("请输入要查询的学生学号");
            String sql="select * from student where sno=?";
        }*/
        List<Student> list=JdbcMethod.executeQuery("select * from student", new RowMap<Student>() {
            @Override
            public Student rowMap(ResultSet rSet) {
                Student student=null;
                try {
                    Integer id=rSet.getInt("id");
                    String sno=rSet.getString("sno");
                    String sname=rSet.getString("sname");
                    String spsw=rSet.getString("spsw");
                    int sage=rSet.getInt("sage");
                    String ssex=rSet.getString("ssex");
                    int sheight=rSet.getInt("sheight");
                    int sweight=rSet.getInt("sweight");
                    String sbirthday=rSet.getString("sborthday");
                    student=new Student(id,sno,sname,spsw,sage,ssex,sheight,sweight,sbirthday);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return student;
            }
        } );

        for (Student s:list
             ) {
            System.out.println(s);
        }
    }

    //特定查询
    public void seleOne(){
        System.out.println("请输入要查询的学生学号");
        String sno=input.next();
        List<Student> list=JdbcMethod.executeQuery("select * from student where sno=?", new RowMap<Student>() {
            @Override
            public Student rowMap(ResultSet rSet) {
                Student student=null;
                try {
                    Integer id=rSet.getInt("id");
                    String sno=rSet.getString("sno");
                    String sname=rSet.getString("sname");
                    String spsw=rSet.getString("spsw");
                    int sage=rSet.getInt("sage");
                    String ssex=rSet.getString("ssex");
                    int sheight=rSet.getInt("sheight");
                    int sweight=rSet.getInt("sweight");
                    String sbirthday=rSet.getString("sborthday");
                    student=new Student(id,sno,sname,spsw,sage,ssex,sheight,sweight,sbirthday);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return student;
            }
        },sno );

        for (Student s:list
        ) {
            System.out.println(s);
        }
    }


    // 退出
    public void exit(){
        System.exit(0);
    }


}
