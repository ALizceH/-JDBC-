package LoginSystem;
import java.sql.*;
public class Delete {
    public static boolean flag = false;
    public void deleteUser(String name)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User u  = null;
        try{
            conn  = DBUtils.getConnection();

            String Sql = "delete from stu28 where name=?"; // 预编译

            stmt = conn.prepareStatement(Sql);
            // 给问号赋值
            stmt.setString(1,name);
//			String sql ="select * from stu28 where Name = '"+name+"' AND PASsword = '"+pwd+"'";
            // 这样写会导致SQL注入问题    就是后面   or   '1'='1

            int resultSet = stmt.executeUpdate();
            if(resultSet>0){
                //如果删除成功，则打印success
                System.out.println("Success");
                flag = true;
            }else{
                //如果删除失败，则打印Failure
                System.out.println("Failure" + ",There has no user which you scan");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
