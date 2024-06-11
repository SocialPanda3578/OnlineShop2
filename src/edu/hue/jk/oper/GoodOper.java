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

    // ��ʼ����Ʒ�б�
    private void initGoodList() {
        Good good1 = new Good(1, "��������", BigDecimal.valueOf(1999), 100);
        Good good2 = new Good(2, "���ŵ���", BigDecimal.valueOf(4999), 100);
        Good good3 = new Good(3, "С���ֻ�", BigDecimal.valueOf(999), 100);
        goodList.add(good1);
        goodList.add(good2);
        goodList.add(good3);
    }

    public List<Good> getGoodList() {
        return goodList;
    }

    // ������Ʒ��Ų�����Ʒ��Ϣ
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
        System.out.println("******��Ʒ�б�����*********");
        // ����Ʒ����
        Collections.sort(goodList);
        for (Good good : goodList) {
            System.out.println(good);
        }
    }

    // �����Ʒ
    public void addGood() {
        System.out.println("*********��ʼ�����Ʒ*********");
        System.out.println("��������Ʒ��ţ�");
        int id = sc.nextInt();
        System.out.println("��������Ʒ���ƣ�");
        String name = sc.next();
        System.out.println("��������Ʒ�۸�");
        BigDecimal price = sc.nextBigDecimal();
        System.out.println("��������Ʒ������");
        int num = sc.nextInt();

        Good good = new Good();
        good.setId(id);
        good.setName(name);
        good.setPrice(price);
        good.setNum(num);
        // ����Ʒ��Ϣ�ŵ�������
        goodList.add(good);
        System.out.println("*******��Ʒ��ӳɹ���********");
    }

    // �޸���Ʒ
    public void updateGood() {
        System.out.println("******��ʼ�޸���Ʒ*********");
        System.out.println("������Ҫ�޸ĵ���Ʒ��ţ�");
        while (true) {
            int id = sc.nextInt();
            // ������Ʒ��Ų�����Ʒ��Ϣ
            Good good = findGoodById(id);
            if (good == null) {
                System.out.println("δ�ҵ�����Ʒ!");
                System.out.println("����������Ҫ�޸ĵ���Ʒ��ţ�");
            } else {
                System.out.println("����Ʒ��Ϣ���£�");
                System.out.println(good);

                System.out.println("�������޸ĺ����Ʒ���ƣ�");
                String name = sc.next();
                System.out.println("�������޸ĺ����Ʒ�۸�");
                BigDecimal price = sc.nextBigDecimal();
                System.out.println("�������޸ĺ����Ʒ������");
                int num = sc.nextInt();

                // ���޸ĺ������ֵ����good����
                good.setName(name);
                good.setPrice(price);
                good.setNum(num);
                System.out.println("*******��Ʒ�޸ĳɹ���********");
                break;
            }
        }
    }

    // ɾ����Ʒ
    public void deleteGood() {
        System.out.println("******��ʼɾ����Ʒ*********");
        System.out.println("������Ҫɾ������Ʒ��ţ�");
        while (true) {
            int id = sc.nextInt();
            // ������Ʒ��Ų�����Ʒ��Ϣ
            Good good = findGoodById(id);
            if (good == null) {
                System.out.println("δ�ҵ�����Ʒ!");
                System.out.println("����������Ҫɾ������Ʒ��ţ�");
            } else {
                goodList.remove(good);
                System.out.println("*******��Ʒɾ���ɹ���********");
                break;
            }
        }
    }

}
