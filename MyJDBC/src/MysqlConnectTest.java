import java.sql.*;

public class MysqlConnectTest {
    public static void main(String[] args) throws Exception{
        //加载数据库驱动程序
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException cne){
            cne.printStackTrace();
        }
        String dburl = "jdbc:mysql://127.0.0.1:3306/webstore?&useSSL=false&serverTimezone=UTC";
        String sql = "SELECT * FROM projects where id < 104";
        try (Connection conn = DriverManager.getConnection(dburl,"root","root");
            Statement stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery(sql))

        {
            while (rst.next()){
                System.out.println(rst.getInt("id")+"\t"+
                        rst.getString("pname")+"\t"+rst.getString("brand")+
                        "\t"+rst.getFloat("price") + "\t" + rst.getInt("stock")
                );
            }

        }catch (SQLException se){
            se.printStackTrace();
        }

    }
}
