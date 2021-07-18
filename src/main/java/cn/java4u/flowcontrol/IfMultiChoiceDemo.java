package cn.java4u.flowcontrol;

import java.util.Scanner;

/**
 * if 多选择结构
 *
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class IfMultiChoiceDemo {

    public static void main(String[] args) {
        // 用标准的输入流构建一个 Scanner 对象
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入你的成绩（百分制）:");

        //58,68,88,96,120 切换
        int score = scanner.nextInt();

        if (score > 0 && score < 60) {
            System.out.println("不合格");
        } else if (score >= 60 && score < 80) {
            System.out.println("合格");
        } else if (score >= 80 && score < 90) {
            System.out.println("良好");
        } else if (score >= 90 && score <= 100) {
            System.out.println("优秀");
        } else {
            System.out.println("非法输入");
        }
    }
}
