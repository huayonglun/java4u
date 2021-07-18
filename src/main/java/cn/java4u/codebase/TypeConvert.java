package cn.java4u.codebase;

/**
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class TypeConvert {

    /**
     * from 公众号：蜗牛互联网
     *
     * @param args 入参
     */
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
        System.out.println("intValue=" + intValue);

        long longValue = intValue;
        System.out.println("longValue=" + longValue);

        double doubleValue = intValue;
        System.out.println("doubleValue=" + doubleValue);

        // 高精度到低精度，走强转
        int highIntValue = 129;
        byte lowByteValue = (byte)highIntValue;

        // 但强转后会出现精度丢失，比如这里会输出 -127
        System.out.println(lowByteValue);


        // 用二进制形式定义一个 int
        int strAppendInt = 0b111;

        System.out.println(strAppendInt);

        // 字符串连接打印
        System.out.println("字符串串联运算符测试，原定义为：0b111，打印值为：" + strAppendInt);
    }
}
