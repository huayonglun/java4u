package cn.java4u.oo.polymorphism;

/**
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class PolymorphismTest {

    /**
     * 打印对象
     *
     * @param scene 打印场景
     * @param obj   obj
     */
    public static void printObjectString(String scene, Object obj) {

        System.out.println(scene + ": " + obj.toString());

    }

    public static void main(String[] args) {

        // 父类引用初始化父类对象并打印
        Object rootObj = new Object();
        printObjectString("父类引用初始化父类对象", rootObj);

        // 子类引用初始化子类对象并打印
        Animal animal = new Animal();
        printObjectString("子类引用初始化子类对象", animal);


        // 父类引用初始化子类对象并打印
        Object animalWhenParentRef = new Animal();
        printObjectString("父类引用初始化子类对象", animal);

    }
}
