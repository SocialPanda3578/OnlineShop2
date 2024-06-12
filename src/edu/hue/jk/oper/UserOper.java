package edu.hue.jk.oper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.hue.jk.model.Good;
import edu.hue.jk.model.User;

public class UserOper {
    private Scanner sc;

    private List<User> userList = new ArrayList<User>();
    private List<Good> myGoodList = new ArrayList<Good>();
    private User currUser;

    public UserOper(Scanner sc) {
        this.sc = sc;
        initUserList();
    }

    private void initUserList() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setType(User.Type.admin);
        userList.add(admin);

        User scott = new User();
        scott.setUsername("scott");
        scott.setPassword("tiger");
        scott.setType(User.Type.normal);
        userList.add(scott);
    }

    public User getCurrUser() {
        return currUser;
    }

    public void reg() {
        String username = "";
        String password = "";
        while (true) {
            System.out.println("请输入用户名:");
            username = sc.next();
            boolean flag = true;
            for (User user : userList) {
                if (username.equals(user.getUsername())) {
                    System.out.println("用户名已存在！");
                    flag = false;
                    break;
                }
            }

            if (flag == false) {
                continue;
            }

            if (username.length() < 3) {
                System.out.println("用户名不能少于3位！");
                continue;
            } else {
                break;
            }

        }

        while (true) {
            System.out.println("请输入密码：");
            password = sc.next();

            // ?????????????6λ
            if (password.length() < 6) {
                System.out.println("密码长度不能少于6位！");
                continue;
            } else {
                break;
            }
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setType(User.Type.normal);
        user.setLogin(false);
        userList.add(user);
        System.out.println("注册成功！");
    }

    public void login() {
        String username = "";
        String password = "";
        boolean login_flag = false;

        System.out.println("请输入用户名:");
        while (true) {
            username = sc.next();
            System.out.println("请输入密码:");
            password = sc.next();

            for (User user : userList) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    if (user.getType().equals(User.Type.normal)) {
                        System.out.println("登录成功！");
                    } else {
                        System.out.println("管理员登录成功！");
                    }

                    currUser = user;
                    currUser.setLogin(true);
                    login_flag = true;
                    break;
                }
            }

            if (login_flag == true) {
                break;
            } else {
                System.out.println("登录失败，请重新登录！");
                System.out.println("请重新输入用户名：");
            }
        }
    }

    public void showMyGoodList() {
        System.out.println("**********您购买的商品列表如下*********");

        BigDecimal total = new BigDecimal("0");
        for (Good good : myGoodList) {
            System.out.println(good);
            BigDecimal price = good.getPrice();
            int num = good.getNum();
            total = total.add(price.multiply(BigDecimal.valueOf(num)));
        }
        System.out.println("总价格为：" + total);
    }

    public void buy(GoodOper goodOper) {
        System.out.println("请输入您要购买的商品编号：");
        Good good = null;
        while (true) {
            int id = sc.nextInt();
            good = goodOper.findGoodById(id);
            if (good == null) {
                System.out.println("未找到该商品！");
                System.out.println("请重新输入您要购买的商品编号：");
            } else {
                System.out.println("您将要购买的商品信息如下：");
                System.out.println(good);
                break;
            }
        }
        System.out.println("请输入您要购买的商品数量：");
        while (true) {
            int num = sc.nextInt();

            if (num > good.getNum()) {
                System.out.println("库存不足！");
                System.out.println("请重新输入您要购买的商品数量：");
            } else {
                Good myGood = new Good();
                try {
                    myGood = good.clone();
                    myGood.setNum(num);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

                myGoodList.add(myGood);

                int newNum = good.getNum() - num;
                good.setNum(newNum);
                break;
            }
        }
    }

}
