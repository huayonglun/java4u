package cn.java4u.codebase.array;

import java.util.Arrays;

/**
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class ArrayShow {

    public static void main(String[] args) {


        int student1 = 88;
        int student2 = 89;
        int student3 = 90;
        int student4 = 93;
        int student5 = 79;
        int student6 = 85;

        int sum = student1 + student2 + student3 + student4 + student5 + student6;

        int average = sum / 6;

        System.out.println(average);

        int[] studentScoreArray = new int[6];

        System.out.println("赋值前第一个元素值为：" + studentScoreArray[0]);

        studentScoreArray[0] = 88;
        studentScoreArray[1] = 89;
        studentScoreArray[2] = 90;
        studentScoreArray[3] = 93;
        studentScoreArray[4] = 79;
        studentScoreArray[5] = 85;

        System.out.println("赋值后第一个元素值为：" + studentScoreArray[0]);

        System.out.println(studentScoreArray.length);

        int[] initStudentScoreArray = new int[]{88, 89, 90, 93, 79, 85};

        int[] sampleStudentScoreArray = {88, 89, 90, 93, 79, 85};

        System.out.println(initStudentScoreArray[3]);
        studentScoreArray[6] = 85;
    }


}
