package LoginSystem;

import java.sql.*;

public class Select {
    public static void SelectTable(){
        Connection conn;
        Statement stmt;
        ResultSet rs;
        try{
            conn = DBUtils.getConnection();

            String sql = "select * from stu28";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String name = rs.getString("name");
                String pwd = rs.getString("password");
                System.out.println("name = " + name + ", password = " + pwd);
            }

        }catch(SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
