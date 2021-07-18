package cn.java4u.flowcontrol;

/**
 * 循环中断演示
 *
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class CircleInterruptDemo {

    public static void main(String[] args) {

        int a = 1;
        int sum = 0;
        while (a <= 5) {

            // a 为 3 的时候中断
            if (a == 3) {
                break;
            }

            // a 累加到 sum 中
            sum += a;
            // a 自身加一
            a++;
        }
        System.out.println("while sum:" + sum);


        int i = 0;
        while (i <= 5) {
            i++;
            // i 为 3 的时候中断
            if (i == 3) {
                System.out.println("命中 continue");
                continue;
            }
            System.out.println("i=" + i);
        }


    }
}
