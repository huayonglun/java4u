package cn.java4u.codebase;

/**
 * 字符串比较
 *
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class StringCompare {

    public static void main(String[] args) {

        String equalChar = "蜗牛";

        String euqalCharCompare = "蜗牛";

        System.out.println(equalChar == euqalCharCompare);

        String equalMethod = new String("蜗牛");

        String euqalMethodCompare = new String("蜗牛");

        System.out.println(equalMethod == euqalMethodCompare);


        System.out.println(equalMethod.equals(euqalMethodCompare));


    }
}
