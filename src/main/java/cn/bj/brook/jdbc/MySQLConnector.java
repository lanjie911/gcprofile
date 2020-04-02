package cn.bj.brook.jdbc;

import java.sql.*;

public class MySQLConnector {
    public static void main(String[] args) {
        String Url = "jdbc:mysql://172.16.105.151:3306/optdemo?useUnicode=true&characterEncoding=utf8";
        ;
        String User = "opx";
        String Password = "Juanxian@122731";
        //1.加载驱动程序
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        //2.获得数据库链接
        Connection con = null;
        try {
            con = DriverManager.getConnection(Url, User, Password);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
        Statement st = null;
        try {
            st = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        ResultSet rs = null;
        try {
            rs = st.executeQuery("select * from tb1");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        //4.处理数据库的返回结果(使用ResultSet类)
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
            try {
                System.out.println("pname" + rs.getString("pname"));
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
        }
        //关闭资源
        try {
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
