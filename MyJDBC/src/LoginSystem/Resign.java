package LoginSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Resign {
    public void ResignUser(String name,String pwd)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn  = DBUtils.getConnection();

            String Sql = "insert into stu28 (name,password) values(?,?)"; // 预编译
            stmt = conn.prepareStatement(Sql);
            // 给问号赋值
            stmt.setString(1,name);
            stmt.setString(2,pwd);

            int resultSet = stmt.executeUpdate();
            if(resultSet>0){
                //如果插入成功，则打印success
                System.out.println("Success");
            }else{
                //如果插入失败，则打印Failure
                System.out.println("Failure");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
