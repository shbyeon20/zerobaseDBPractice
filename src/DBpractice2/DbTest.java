package DBpractice2;

import java.sql.*;

public class DbTest {



    public void DbInsert(){
        String url = "jdbc:mariadb://localhost:3306/test1";
        String user = "testuser1";
        String DBpassword = "!tkdghk6226";

        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection(url,user,DBpassword);

        String name = "조정해";
        String email = "jjh@gmail.com";
        String phone = "01065009238";
        String password = "1234";
        int Marketing = 0;

        String sql = "insert into zerobase_member1 (name, email, mobile_no, password, marketing_yn, register_date) \n" +
                "values (?,?,?,?,?,NOW());";


            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,phone);
            preparedStatement.setString(4,password);
            preparedStatement.setInt(5,Marketing);
            int affected = preparedStatement.executeUpdate();
            if (affected > 0) {
                System.out.println("성공");
            }else{
                System.out.println("실패");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void DbSelect() {
        String url = "jdbc:mariadb://localhost:3306/test1";
        String user = "testuser1";
        String DBpassword = "!tkdghk6226";

        String marketingValue = "1";
        String nameValue = "변상화";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(url, user, DBpassword);

            String sql = "select name, email, password\n" +
                    "from zerobase_member1 zm \n" +
                    "where marketing_yn = ? and  name = ?" +
                    ";";

             preparedStatement  = connection.prepareStatement(sql); // sql String을 url에서 조작하는 것을 방지하기 위해서 조건절값 코드 내 숨기기
             preparedStatement.setString(1,marketingValue); // 1. sql문의 ?에 해당 값이 입력됨
             preparedStatement.setString(2,nameValue); // 2. sql문의 ?에 해당 값이 입력됨


            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                System.out.println(name + " " + email + " " + password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
    }
}
