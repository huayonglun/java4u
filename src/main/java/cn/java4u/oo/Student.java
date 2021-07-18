package cn.java4u.oo;

/**
 * 学生
 *
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class Student extends Person {

    /**
     * 分数
     */
    int score;

    public Student(int score) {
        this.score = score;
        System.out.println("这是子类的构造方法");
    }
}
