package com.neuedu.test;

import com.mysql.jdbc.Driver;
import com.neuedu.pojo.Student;
import com.neuedu.pojo.Teacher;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private static Connection conn;
    private static final String URL="jdbc:mysql://127.0.0.1:3306/jdbc?useUnicode=true&characterEncoding=utf8";
    private static final String USER="root";
    private static final String PSW="123";
    private static PreparedStatement pstm;
    private static ResultSet rSet;
    //加载驱动
    static {
        try {
            new Driver();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    Scanner input = new Scanner(System.in);

    //登陆
    public void login() {

        while (true){
            System.out.println("欢迎使用东软小学学生管理系统");
            System.out.println("请选择1 学生登陆  2 教师登陆");
            int sj = input.nextInt();
            if (sj == 1) {
                System.out.println("请输入学生姓名");
                String sname = input.next();
                System.out.println("请输入学生密码");
                String spsw = input.next();
                seleS(sname, spsw);



            } else if (sj == 2) {
                System.out.println("请输入教师姓名");
                String tname = input.next();
                System.out.println("请输入教师密码");
                String tpsw = input.next();
                seleT(tname, tpsw);

            }
        }

    }

    //全部菜单
    private void showAll(){
        while (true) {
            System.out.println("1 查看全部学生信息");
            System.out.println("2 查看特定学生信息");
            System.out.println("3 模糊查看学生信息");
            System.out.println("4 增加学生信息");
            System.out.println("5 修改学生信息");
            System.out.println("6 删除学生信息");
            System.out.println("7 退出系统");
            System.out.println("请选择进行的操作选项");
            int t1 = input.nextInt();
            switch (t1) {
                case 1:
                    seleAll();
                    break;
                case 2:
                    seleOne();
                    break;
                case 3:
                    selectLike();
                    break;
                case 4:
                    add();
                    break;
                case 5:
                    upd();
                    break;
                case 6:
                    del();
                    break;
                case 7:
                    exit();
                    break;
                default:
                    System.out.println("请输入正确选择");
                    break;
            }
        }
    }




    //查询学生账号
    private void seleS(String name,String psw) {

        Student stu=JdbcMethod.QueryOne("select * from student where sname= ? and spsw=?", new RowMap<Student>() {
            Student stu=new Student();
            @Override
            public Student rowMap(ResultSet rSet) {
                try {
                    stu.setSname(rSet.getString("sname"));
                    stu.setSpsw(rSet.getString("spsw"));
                    stu.setScon(rSet.getInt("scon"));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                return stu;
            }
        },name,psw);
            if (stu==null) {
                System.out.println("用户名或密码错误，请重新登入");
            }else {
                System.out.println("欢迎" + name + "同学");
                if (stu.getScon()==1){
                        while (true){
                            System.out.println("请选择进行的操作");
                            System.out.println("1 查看自己的信息");
                            System.out.println("2 退出系统");
                            int s1 = input.nextInt();
                            if (s1 == 1) {
                                seleMy(name,psw);
                            } else if (s1 == 2) {
                                exit();
                            }
                        }
                }else {
                        showAll();
                }

            }





//        List<Student> list=JdbcMethod.executeQuery("select sname,spsw from student where sname=? and spsw=?", new RowMap<Student>() {
//            @Override
//            public Student rowMap(ResultSet rSet) {
//                Student student=null;
//                try {
//                    String name=rSet.getString("sname");
//                    String psw=rSet.getString("spsw");
//                    student=new Student(name,psw);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                return student;
//            }
//        },name,psw);
//
//        if (list.get(0).getSname()!=null&&list.get(0).getSpsw()!=null){
//            System.out.println("欢迎"+name+"同学");
//        }else {
//            System.out.println("请输入正确的用户名和密码");
//        }
//    }
    }
    //查询教师账号
    private void seleT(String name,String psw) {

        Teacher teacher=JdbcMethod.QueryOne("select * from teacher where tname=? and tpsw=?", new RowMap<Teacher>() {
            Teacher tea=new Teacher();
            @Override
            public Teacher rowMap(ResultSet rSet) {
                try {
                    tea.setTname(rSet.getString("tname"));
                    tea.setTpsw(rSet.getString("tpsw"));

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return tea;
            }
        },name,psw );

        if (teacher==null){
            System.out.println("用户名或密码错误，请重新登入");
        }else {
            System.out.println("欢迎"+name+"教师");
            showAll();
        }

//        List<Teacher> list=JdbcMethod.executeQuery("select * from student where sname=? and spsw=?", new RowMap<Teacher>() {
//            @Override
//            public Teacher rowMap(ResultSet rSet) {
//                Teacher teacher=null;
//                try {
//                    String name=rSet.getNString("sname");
//                    String psw=rSet.getString("spsw");
//                    teacher=new Teacher(name,psw);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                return teacher;
//            }
//        },name,psw);
//
//        if (list.get(0).getTname()!=null&&list.get(0).getTpsw()!=null){
//            System.out.println("欢迎"+name+"教师");
//        }else {
//            System.out.println("请输入正确的用户名和密码");
//        }
//    }
    }
    //查询自己
    private void seleMy(String name,String psw){
        Student stu=JdbcMethod.QueryOne("select * from student where sname=? and spsw=?", new RowMap<Student>() {
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
                    String sbirthday=rSet.getString("sbirthday");
                    int scon=rSet.getInt("scon");
                    student=new Student(id,sno,sname,spsw,sage,ssex,sheight,sweight,sbirthday,scon);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return student;
            }
        },name,psw);
        System.out.println(stu);
    }





    //增加
    private void add(){
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
        System.out.println("请输入学生生日 格式YYYY-MM-DD");
        String sbirthday=input.next();
        System.out.println("请输入学生权限");
        int scon=input.nextInt();
        Student student=new Student(null,sno,sname,spsw,sage,ssex,sheight,sweight,sbirthday,scon);
        int result=JdbcMethod.executeUpdate("insert into student(sno,sname,spsw,sage,ssex,sheight,sweight,sbirthday,scon) values(?,?,?,?,?,?,?,?.?)",
                student.getSno(),student.getSname(),student.getSpsw(),student.getSage(),student.getSsex(),student.getSheight(),student.getSweight(),student.getSbirthday(),student.getScon());
        if (result>0){
            System.out.println("插入完成");
        }else {
            System.out.println("插入失败");
        }
    }

    //删除
    private void del(){
        Student student=new Student();
        System.out.println("请输入要删除的学生的学号");
        String sno=input.next();
        student.setSno(sno);
        int result=JdbcMethod.executeUpdate("delete from student where sno=?",student.getSno());
        if (result>0){
            System.out.println("操作完成");
        }else {
            System.out.println("操作未成功");
        }
    }

    //修改
    private void upd(){
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
        System.out.println("请输入学生权限");
        int scon=input.nextInt();
        Student student=new Student(null,sno,sname,spsw,sage,ssex,sheight,sweight,sbirthday,scon);
        int result=JdbcMethod.executeUpdate("update student set sname=?,spsw=?,sage=?,ssex=?,sheight=?,sweight=?,sbirthday=?,scon? where sno=?",student.getSname(),student.getSpsw(),student.getSage(),student.getSsex(),student.getSheight(),student.getSweight(),student.getSbirthday(),student.getScon(),student.getSno());
        if (result>0){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }

    //查询全部
    private void seleAll(){

        List<Student> list=JdbcMethod.executeQuery("select * from student", new RowMap<Student>() {
            @Override
            public Student rowMap(ResultSet rSet) {
                Student student=new Student();
                try {
                    Integer id=rSet.getInt("id");
                    String sno=rSet.getString("sno");
                    String sname=rSet.getString("sname");
                    String spsw=rSet.getString("spsw");
                    String ssex=rSet.getString("ssex");
                    int sheight=rSet.getInt("sheight");
                    int sweight=rSet.getInt("sweight");
                    int sage=rSet.getInt("sage");
                    String sbirthday=rSet.getString("sbirthday");
                    int scon=rSet.getInt("scon");
                    student=new Student(id,sno,sname,spsw,sage,ssex,sheight,sweight,sbirthday,scon);

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
    private void seleOne(){
        System.out.println("请输入要查询的学生学号");
        String sno=input.next();
        Student stu=JdbcMethod.QueryOne("select * from student where sno=?", new RowMap<Student>() {
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
                    int scon=rSet.getInt("scon");
                    student=new Student(id,sno,sname,spsw,sage,ssex,sheight,sweight,sbirthday,scon);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return student;
            }
        },sno );
        System.out.println(stu);
    }

    //模糊查询
    private void selectLike(){
        System.out.println("请输入查询的内容");
        String sname=input.next();
        List<Student> list= JdbcMethod.executeQuery("select * from student where sname like concat('%',?,'%')", new RowMap<Student>() {
            @Override
            public Student rowMap(ResultSet rSet) {
                Student student=null;
                try {
                    Integer id=rSet.getInt("id");
                    String sno=rSet.getString("sno");
                    String sname=rSet.getString("sname");
                    String spsw=rSet.getString("spsw");
                    String ssex=rSet.getString("ssex");
                    int sage=rSet.getInt("sage");
                    int sheight=rSet.getInt("sheight");
                    int sweight=rSet.getInt("sweight");
                    String sbirthday=rSet.getString("sborthday");
                    int scon=rSet.getInt("scon");
                    student=new Student(id,sno,sname,spsw,sage,ssex,sheight,sweight,sbirthday,scon);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return student;
            }

        } ,sname);

        for (Student s:list
             ) {
            System.out.println(list);
        }


    }

    // 退出
    private void exit(){
        System.out.println("已退出系统");
        System.exit(0);
    }


}
