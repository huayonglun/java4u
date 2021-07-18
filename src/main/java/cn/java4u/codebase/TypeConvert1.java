package cn.java4u.codebase;

/**
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class TypeConvert1 {


    public static void main(String[] args) {

        // 数字 65 实际表示大写字母 A
        char charValue = 65;

        // 初始化的char
        System.out.println("initCharValue=" + charValue);

        // 加一
        charValue += 1;
        System.out.println("CharAddOneValue=" + charValue);

        // 自增符号 打印 B 后，charValue 的值已经是 C 了，也就是 67
        System.out.println("CharAddOneValue=" + charValue++);

        // 加法运算 输出 134，即 67+67=134
        System.out.println(charValue + charValue);

        // 往高精度自动转
        int intValue = charValue;


        long longValue = intValue;

        double doubleValue = intValue;


        System.out.println("intValue=" + intValue);
        System.out.println("longValue=" + longValue);
        System.out.println("doubleValue=" + doubleValue);

    }
}
