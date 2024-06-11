package edu.hue.jk;
import java.util.Scanner;
import edu.hue.jk.model.User;

import edu.hue.jk.oper.GoodOper;
import edu.hue.jk.oper.UserOper;
public class ShopApp {
    private Scanner sc = new Scanner(System.in);
    private UserOper userOper = new UserOper(sc);
    private GoodOper goodOper = new GoodOper(sc);
    public static void main(String[] args) {
        ShopApp shop = new ShopApp();
// 菜单循环显示flag
        boolean go_on = true;
// 菜单循环显示
        while (go_on) {
            int choice = shop.showMainMenu();
            go_on = shop.chooseMainMenu(choice, go_on);
        }
    }
    // 菜单显示
    private int showMainMenu() {
        System.out.println("*****欢迎进入电子商城*****");
        System.out.println("\t1.注册");// \t：制表符，相当于tab
        System.out.println("\t2.登录");
        System.out.println("\t3.查看商城");
        System.out.println("\t4.退出系统");
        System.out.println("***********************");
        System.out.print("请选择菜单：");
// 选择的菜单(调用Scanner类对象sc的方法从输入流中获取用户的输入:nextInt()只读取整数)
        int choice = sc.nextInt();
        return choice;
    }
    // 选择菜单
    private boolean chooseMainMenu(int choice, boolean go_on) {
        switch (choice) {
            case 1:
                System.out.println("您选择的菜单是:注册");
                userOper.reg();
                break;
            case 2:
                System.out.println("您选择的菜单是:登录");
                userOper.login();
// 登录成功后，根据登录用户身份不同，展示不同的子菜单。
                while (go_on) {
                    if
                    (userOper.getCurrUser().getType().equals(User.Type.normal)) {
                        go_on = chooseNormalMenu(showNormalMenu(), go_on);
                    } else {
                        go_on = chooseAdminMenu(showAdminMenu(), go_on);
                    }
                }
// 从子菜单退出后go_on会置为false，为了主菜单执行正常，需要将go_on重置为true。
                go_on = true;
                break;
            case 3:
                System.out.println("您选择的菜单是：查看商城");
                goodOper.showGoodList();
                break;
            case 4:
                System.out.println("谢谢使用!");
                go_on = false;// 程序立即停止(System.exit()方法是停止虚拟机运行)
                System.exit(0);
                break;
            default:
                System.out.println("您的输入有误！");
                break;
        }
        return go_on;
    }// 显示普通用户菜单
    public int showNormalMenu() {
        System.out.println("*****普通用户菜单*****");
        System.out.println("\t1.购买商品");
        System.out.println("\t2.查看商场商品列表");
        System.out.println("\t3.查看我购买的商品列表");
        System.out.println("\t4.退出");
        System.out.println("***********************");
        System.out.println("请选择菜单：");// 选择的管理员菜单
        int choice = sc.nextInt();
        return choice;
    }// 选择普通用户菜单
    public boolean chooseNormalMenu(int choice, boolean go_on) {
        switch (choice) {
            case 1:
                System.out.println("您选择的菜单是：1.购买商品");
                userOper.buy(goodOper);
                System.out.println("*******商品购买成功！********");// 查看购买的商品列表
                userOper.showMyGoodList();
                break;
            case 2:
                System.out.println("您选择的菜单是：2.查看商场商品列表");
                goodOper.showGoodList();
                break;
            case 3:
                System.out.println("您选择的菜单是：3.查看我购买的商品列表");
                userOper.showMyGoodList();
                break;
            case 4:
                System.out.println("您选择的菜单是：4.退出");
                go_on = false;
                break;
            default:
                System.out.println("您的输入有误！");
        }
        return go_on;
    }// 显示管理员菜单
    public int showAdminMenu() {
        System.out.println("*****管理员菜单*****");
        System.out.println("\t1.添加商品");
        System.out.println("\t2.修改商品");
        System.out.println("\t3.删除商品");
        System.out.println("\t4.查看商品列表");
        System.out.println("\t5.退出");
        System.out.println("***********************");
        System.out.println("请选择菜单：");// 选择的管理员菜单
        int choice = sc.nextInt();
        return choice;
    }// 选择管理员菜单
    public boolean chooseAdminMenu(int choice, boolean go_on) {
        switch (choice) {
            case 1:
                System.out.println("您选择的菜单是：1.添加商品");
                String is_continue = "Y";
                while ("Y".equals(is_continue) || "y".equals(is_continue)) {// 添加商品
                    goodOper.addGood();
                    System.out.println("是否继续添加？Y/N");
                    is_continue = sc.next();
                }
                break;
            case 2:
                System.out.println("您选择的菜单是：2.修改商品");// 修改商品
                goodOper.updateGood();
                break;
            case 3:
                System.out.println("您选择的菜单是：3.删除商品");// 删除商品
                goodOper.deleteGood();
                break;
            case 4:
                System.out.println("您选择的菜单是：4.查看商品列表");
                goodOper.showGoodList();
                break;
            case 5:
                System.out.println("您选择的菜单是：5.退出");
                go_on = false;
                break;
            default:
                System.out.println("您的输入有误！");
        }
        return go_on;
    }
}