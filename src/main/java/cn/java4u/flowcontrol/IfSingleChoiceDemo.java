package cn.java4u.flowcontrol;

import java.util.Scanner;

/**
 * if 单选择结构
 *
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class IfSingleChoiceDemo {

    public static void main(String[] args) {

        // 用标准的输入流构建一个 Scanner 对象
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入整数a:");
        int a = scanner.nextInt();

        System.out.println("请输入整数b:");
        int b = scanner.nextInt();

        // 初始化最大值为 a 的值
        int max = a;

        // b 比 a 大的请求下，把 b 的值赋给 max
        if (a < b) {
            max = b;
        }
        System.out.println("max:" + max);
    }
}
