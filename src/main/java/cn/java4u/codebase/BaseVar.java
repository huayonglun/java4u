package cn.java4u.codebase;

/**
 * 基础类型变量
 *
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class BaseVar {

    /**
     * 主函数
     *
     * @param args args
     */
    public static void main(String[] args) {

        /*
         * 变量定义格式：
         *      数据类型 变量名;
         *      数据类型 变量名 = 变量值;
         */

        /*
        布尔类型
         */
        // 布尔未赋值
        boolean boolNoValue;

        // 布尔赋值 true
        boolean boolTrue = true;

        // 布尔赋值一个计算结果
        boolean boolWithResult = 2 > 1;

        // 布尔赋值 非 true 或 false 会编译报错
//        boolean boolCompileError = 123;

        /*
        整数类型
         */
        // 字节赋值 8
        byte byteDemo = 8;

        // 赋值越界会编译报错
//        byte byteOutOfBounds = 188;

        // 短整型赋值 188
        short shortDemo = 188;

        // 整型赋值 38888
        int intDemo = 38888;

        // 整型赋值带下划线，提高辨识度
        int intDemoWithUnderLine = 1888_888_888;

        // 十六进制表示 16711680
        int intDemoWithHex = 0xff0000;

        // 二进制表示的512
        int intDemoWithBinary = 0b1000000000;

        // long型的结尾需要加 L
        long longWithSuffix = 2147483649L;

        /*
        浮点数类型
        浮点类型的数就是通常意义上的小数，因为小数用科学计数法表示的时候，小数点是可以“浮动”的，所以称为浮点数。
         */

        // 单精度浮点数赋值，加 f 后缀
        float floatDemo = 9.18f;

        // 负的单精度浮点数
        float floatNegDemo = -9.18f;

        // 科学计数法表示的 3.14x10^38，float 类型可表示的最大数
        float floatDemoWithSci = 3.14e38f;

        // 科学计数法表示的1.79x10^308，double类型可表示的最大数
        double doubleDemo = 1.79e308;

        /*
        字符类型
        Java 的 字符类可表示标准的 ASCII 和一个 Unicode 字符。
         */

        // char 用 ascii 字符赋值
        char charWithAscii = '.';

        // char 用 Unicode 字符赋值
        char charWithUnicode = '牛';

        // char 多字符会编译报错
//        char charCompileError = '蜗牛';

    }
}
