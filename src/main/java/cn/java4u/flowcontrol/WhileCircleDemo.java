package cn.java4u.flowcontrol;

/**
 * while 循环结构演示
 *
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class WhileCircleDemo {

    public static void main(String[] args) {

        int a = 1;
        int sum = 0;

        System.out.println("当前a的值为:" + a);
        // a 累加到 sum 中
        sum = sum + a;
        // a 自身加一
        a = a + 1;

        System.out.println("当前a的值为:" + a);
        sum = sum + a;
        a = a + 1;

        System.out.println("当前a的值为:" + a);
        sum = sum + a;
        a = a + 1;

        System.out.println("当前a的值为:" + a);
        sum = sum + a;
        a = a + 1;

        System.out.println("当前a的值为:" + a);
        sum = sum + a;

        System.out.println("sum:" + sum);

        // 初始化值
        a = 1;
        sum = 0;

        while (a <= 5) {
            // a 累加到 sum 中
            sum += a;
            // a 自身加一
            a++;
        }
        System.out.println("while sum:" + sum);

        // 初始化值
        a = 1;
        sum = 0;

        do {
            // a 累加到 sum 中
            sum += a;
            // a 自身加一
            a++;
        } while (a <= 5);
        System.out.println("do while sum:" + sum);

        sum = 0;
        for (a = 1; a <= 5; a++) {
            sum += a;
        }
        System.out.println("for sum:" + sum);

        int[] array = {1, 2, 3, 4, 5};
        sum = 0;
        for (int temp : array) {
            sum += temp;
        }
        System.out.println("for each sum:" + sum);

    }
}
