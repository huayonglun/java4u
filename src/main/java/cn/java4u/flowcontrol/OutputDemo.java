package cn.java4u.flowcontrol;

/**
 * 输出演示
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class OutputDemo {

    public static void main(String[] args) {

        // 输出并换行
        System.out.println("---开始演示---");
        // 输出不换行
        System.out.print("打印不换行[");
        System.out.print("1 ");
        System.out.print("2 ");
        System.out.print("3");
        System.out.print("]");
        // 只换行
        System.out.println();
        // 格式化输出
        double d = 66600000.8888;
        // 不进行格式化的处理结果：6.66000008888E7
        System.out.println("不进行格式化的处理结果：" + d);
        System.out.printf("默认格式化：%f", d);
        System.out.printf("; 无小数格式化：%.0f", d);
        System.out.printf("; 一位小数格式化：%.1f", d);
        System.out.printf("; 两位小数格式化：%.2f", d);
    }
}
