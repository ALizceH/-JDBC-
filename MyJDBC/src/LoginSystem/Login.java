package LoginSystem;

import java.util.Locale;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        Integer i = 0;
        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.println("是否要注册账号？(请输入是与否)");
            String bool = sc.nextLine();
            if("是".equals(bool) || "否".equals(bool)) {
                if ("是".equals(bool)) {
                    System.out.println("注册：请输入帐号：");
                    String name = sc.nextLine();

                    System.out.println("注册：请输入密码：");
                    String pwd = sc.nextLine();

                    Resign resign = new Resign();
                    resign.ResignUser(name, pwd);
                }
            }else
                System.out.println("视为放弃注册，直接跳转登录界面......");


            System.out.println("登录：请输入帐号：");
            String name = sc.nextLine();

            System.out.println("登录：请输入密码：");
            String pwd = sc.nextLine();

            DoLogin dl = new DoLogin();
            User user = dl.findUser(name, pwd);
            if (user != null) {
                if("root".equals(user.getName())){
                    while(true) {
                        System.out.println("welcome to Login System! my Master" + "\nDid you want to do something?");
                        String dosth = sc.nextLine();
                        if ("delete".equalsIgnoreCase(dosth)) {
                            System.out.println("Which user do you want to delete?");
                            String username = sc.nextLine();
                            Delete delete = new Delete();
                            delete.deleteUser(username);
                            if (Delete.flag) {
                                System.out.println(username + "is actually deleted");
                            }
                        }
                        if ("add".equalsIgnoreCase(dosth)) {
                            System.out.println("Please print username...");
                            String addusername = sc.nextLine();

                            System.out.println("Please print password...");
                            String addpassword = sc.nextLine();

                            Resign resign = new Resign();
                            resign.ResignUser(addusername, addpassword);
                            System.out.println("The new user is actually saved!");
                        }
                        if ("select".equalsIgnoreCase(dosth)) {
                            Select.SelectTable();
                        }
                        if("exit".equalsIgnoreCase(dosth)||"quit".equalsIgnoreCase(dosth)) {
                            System.out.println("Ok,Bye!");
                            break;
                        }
                    }
                    break;
                }else {
                    System.out.print("welcome to Login System!" + user.getName());
                    break;
                }
            } else
                System.out.print("用户名或密码错误，亦或是用户名不存在，请尝试注册或重新登录。\n");
            i++;
            if(i==3){
                System.out.println("错误达到三次，服务器冻结");
                break;
            }
        }
    }
}
