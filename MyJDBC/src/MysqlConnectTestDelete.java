import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlConnectTestDelete {
    //插入insert单个数据
    @Test
    public void insert1(){
        Connection connection=null;
        PreparedStatement ps=null;

        try {
            //1.register
            Class.forName("com.mysql.cj.jdbc.Driver");

            //2.getConnection
            String url="jdbc:mysql://localhost:3306/webstore?&useSSL=false&serverTimezone=UTC";
            Properties info=new Properties();
            info.put("user", "root");
            info.put("password", "root");
            connection= DriverManager.getConnection(url, info);

            //3.create Statement
            String sql="delete from projects where id = ?";
            ps=connection.prepareStatement(sql);
            ps.setInt(1, 0);

            //4.excuteUpdate
            int resultSet=ps.executeUpdate();
            if(resultSet>0){
                //如果插入成功，则打印success
                System.out.println("Sucess");
            }else{
                //如果插入失败，则打印Failure
                System.out.println("Failure");
            }

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            //5.关闭资源
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
