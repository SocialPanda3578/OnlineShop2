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
            System.out.println("�������û���:");
            username = sc.next();
            boolean flag = true;
            for (User user : userList) {
                if (username.equals(user.getUsername())) {
                    System.out.println("�û����Ѵ��ڣ�");
                    flag = false;
                    break;
                }
            }

            if (flag == false) {
                continue;
            }

            if (username.length() < 3) {
                System.out.println("�û�����������3λ��");
                continue;
            } else {
                break;
            }

        }

        while (true) {
            System.out.println("���������룺");
            password = sc.next();

            // ?????????????6��
            if (password.length() < 6) {
                System.out.println("���볤�Ȳ�������6λ��");
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
        System.out.println("ע��ɹ���");
    }

    public void login() {
        String username = "";
        String password = "";
        boolean login_flag = false;

        System.out.println("�������û���:");
        while (true) {
            username = sc.next();
            System.out.println("����������:");
            password = sc.next();

            for (User user : userList) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    if (user.getType().equals(User.Type.normal)) {
                        System.out.println("��¼�ɹ���");
                    } else {
                        System.out.println("����Ա��¼�ɹ���");
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
                System.out.println("��¼ʧ�ܣ������µ�¼��");
                System.out.println("�����������û�����");
            }
        }
    }

    public void showMyGoodList() {
        System.out.println("**********���������Ʒ�б�����*********");

        BigDecimal total = new BigDecimal("0");
        for (Good good : myGoodList) {
            System.out.println(good);
            BigDecimal price = good.getPrice();
            int num = good.getNum();
            total = total.add(price.multiply(BigDecimal.valueOf(num)));
        }
        System.out.println("�ܼ۸�Ϊ��" + total);
    }

    public void buy(GoodOper goodOper) {
        System.out.println("��������Ҫ�������Ʒ��ţ�");
        Good good = null;
        while (true) {
            int id = sc.nextInt();
            good = goodOper.findGoodById(id);
            if (good == null) {
                System.out.println("δ�ҵ�����Ʒ��");
                System.out.println("������������Ҫ�������Ʒ��ţ�");
            } else {
                System.out.println("����Ҫ�������Ʒ��Ϣ���£�");
                System.out.println(good);
                break;
            }
        }
        System.out.println("��������Ҫ�������Ʒ������");
        while (true) {
            int num = sc.nextInt();

            if (num > good.getNum()) {
                System.out.println("��治�㣡");
                System.out.println("������������Ҫ�������Ʒ������");
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
