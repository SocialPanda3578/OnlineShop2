package edu.hue.jk.oper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import edu.hue.jk.model.Good;

public class GoodOper {
    private Scanner sc;
    private List<Good> goodList = new ArrayList<Good>();

    public GoodOper(Scanner sc) {
        this.sc = sc;
        initGoodList();
    }

    private void initGoodList() {
        Good good1 = new Good(1, "小米手机", BigDecimal.valueOf(1999), 100);
        Good good2 = new Good(2, "海尔冰箱", BigDecimal.valueOf(4999), 100);
        Good good3 = new Good(3, "海信电视", BigDecimal.valueOf(999), 100);
        goodList.add(good1);
        goodList.add(good2);
        goodList.add(good3);
    }

    public List<Good> getGoodList() {
        return goodList;
    }

    public Good findGoodById(int id) {
        Good returnGood = null;
        for (Good good : goodList) {
            if (good.getId() == id) {
                returnGood = good;
                break;
            }
        }
        return returnGood;
    }

    // �鿴������Ʒ�б�
    public void showGoodList() {
        System.out.println("******商品列表如下*********");
        // ����Ʒ����
        Collections.sort(goodList);
        for (Good good : goodList) {
            System.out.println(good);
        }
    }

    public void addGood() {
        System.out.println("*********开始添加商品*********");
        System.out.println("请输入商品编号：");
        int id = sc.nextInt();
        System.out.println("请输入商品名称：");
        String name = sc.next();
        System.out.println("请输入商品价格：");
        BigDecimal price = sc.nextBigDecimal();
        System.out.println("请输入商品数量：");
        int num = sc.nextInt();

        Good good = new Good();
        good.setId(id);
        good.setName(name);
        good.setPrice(price);
        good.setNum(num);

        goodList.add(good);
        System.out.println("*******商品添加成功！********");
    }

    // �޸���Ʒ
    public void updateGood() {
        System.out.println("******开始修改商品*********");
        System.out.println("请输入要修改的商品编号：");
        while (true) {
            int id = sc.nextInt();

            Good good = findGoodById(id);
            if (good == null) {
                System.out.println("未找到该商品!");
                System.out.println("请重新输入要修改的商品编号：");
            } else {
                System.out.println("该商品信息如下：");
                System.out.println(good);
                System.out.println("请输入修改后的商品名称：");
                String name = sc.next();
                System.out.println("请输入修改后的商品价格：");
                BigDecimal price = sc.nextBigDecimal();
                System.out.println("请输入修改后的商品数量：");
                int num = sc.nextInt();

                // ���޸ĺ������ֵ����good����
                good.setName(name);
                good.setPrice(price);
                good.setNum(num);
                System.out.println("*******商品修改成功********");
                break;
            }
        }
    }

    public void deleteGood() {
        System.out.println("******开始删除商品*********");
        System.out.println("请输入要删除的商品编号：");
        while (true) {
            int id = sc.nextInt();

            Good good = findGoodById(id);
            if (good == null) {
                System.out.println("未找到该商品!");
                System.out.println("请重新输入要删除的商品编号：");
            } else {
                goodList.remove(good);
                System.out.println("*******商品删除成功********");
                break;
            }
        }
    }

}
