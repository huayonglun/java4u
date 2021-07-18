package cn.java4u.flowcontrol;

import java.util.Scanner;

/**
 * 输入演示
 *
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class InputDemo {

    public static void main(String[] args) {

        // 用标准的输入流构建一个 Scanner 对象
        Scanner scanner = new Scanner(System.in);

        // 读取输入的一行并获取字符串
        String nextLineStr = scanner.nextLine();

        // 读取输入的字符串，会忽略掉字符串两边的空格，因为空格起分隔符或结束符的作用
        String nextStr = scanner.next();

        // 读取输入的整数，非整数会抛异常(InputMismatchException)
        int nextInt = scanner.nextInt();

        System.out.println("---以下为打印值---");
        System.out.println("nextLineStr:" + nextLineStr);
        System.out.println("nextStr:" + nextStr);
        System.out.println("nextInt:" + nextInt);
    }
}
