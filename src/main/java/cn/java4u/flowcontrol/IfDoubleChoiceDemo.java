package cn.java4u.flowcontrol;

import java.util.Scanner;

/**
 * if双选择结构
 *
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class IfDoubleChoiceDemo {
    public static void main(String[] args) {

        // 用标准的输入流构建一个 Scanner 对象
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入整数a:");
        //10,-10 切换
        int a = scanner.nextInt();

        // 初始化绝对值变量
        int abs;

        if (a < 0) {
            abs = -a;
        } else {
            abs = a;
        }
        System.out.println("abs:" + abs);
    }
}