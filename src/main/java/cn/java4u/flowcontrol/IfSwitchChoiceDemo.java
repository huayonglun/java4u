package cn.java4u.flowcontrol;

import java.util.Scanner;

/**
 * switch选择结构
 *
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class IfSwitchChoiceDemo {
    public static void main(String[] args) {

        // 用标准的输入流构建一个 Scanner 对象
        Scanner scanner = new Scanner(System.in);

        System.out.println("请选择你要运行的程序(键入1表示求最大值，键入2表示求绝对值，键入3表示成绩分数评优良差):");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("--开始求两个数的最大值--");
                IfSingleChoiceDemo.main(null);
                break;
            case 2:
                System.out.println("--开始求绝对值--");
                IfDoubleChoiceDemo.main(null);
                break;
            case 3:
            case 4:
                System.out.println("--开始成绩分数评优良差--");
                IfMultiChoiceDemo.main(null);
                break;
            default:
                System.out.println("非法输入");
        }

    }
}
