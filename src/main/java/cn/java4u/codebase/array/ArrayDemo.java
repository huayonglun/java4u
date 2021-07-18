package cn.java4u.codebase.array;

/**
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class ArrayDemo {

    public static void main(String[] args) {

        int[] studentScoreArray = new int[]{88, 89, 90, 93, 79, 85};
        int[] otherArray = new int[]{3, 2, 1};

        System.out.println(studentScoreArray.length);

        studentScoreArray = otherArray;

        System.out.println(studentScoreArray.length);

    }
}
