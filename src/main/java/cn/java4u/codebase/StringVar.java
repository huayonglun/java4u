package cn.java4u.codebase;

/**
 * 字符串类型变量
 *
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class StringVar {

    public static void main(String[] args) {


        // null 字符串，未指定地址
        String nullStr = null;

        // 空字符串，包含 0 个字符
        String  blankStr = "";

        // 包含一个字符
        String oneCharStr = "A";

        // 包含多个字符，打印 蜗牛666 A
        String multiCharStr = "蜗牛666 \u0041";

        System.out.println(multiCharStr);


        String str = "蜗牛666\"";


        String str1 = "蜗牛666";

        String str2 = "蜗牛666";

        String newStr1 = new String("蜗牛666");
        String newStr2 = new String("蜗牛666");

        String strChange = "蜗牛666";

        System.out.println(strChange);

        strChange = "蜗牛888";

        System.out.println(strChange);

    }
}
