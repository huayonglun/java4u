package cn.java4u.oo;

/**
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class MainTest {

    public static void main(String[] args) {


        // 静态方法，通过 类名.静态方法名 访问
        Person.testStaticMethod();

        Student student = new Student(120);
        System.out.println(student.name);
    }
}
