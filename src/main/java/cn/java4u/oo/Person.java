package cn.java4u.oo;


/**
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class Person {

    /**
     * [成员变量]需要被实例化后使用，每个实例都有独立空间，通过 对象.成员变量名 访问
     * 名字
     */
    String name;


    /**
     * [静态变量]用 static 修饰，无需实例化即可使用，每个实例共享同一个空间，通过 类名.静态变量名 访问
     * 面包数量
     */
    static int breadNum;

    /**
     * [方法]
     * 吃一个面包
     *
     * @param num 方法入参，要吃面包的个数
     */
    void eatBread(int num) {

        //  num 是[局部变量]
        breadNum = breadNum - num;

        System.out.println(name + "吃了 " + num + " 个面包，全世界的面包还剩 " + breadNum + " 个！");
    }

    /**
     * [构造方法]
     * 参数为空
     */
    public Person() {
        System.out.println("这是父类的构造方法");
    }

    /**
     * [构造方法]
     *
     * @param name 此为构造方法的输入参数，和成员变量有关
     */
    public Person(String name) {
        this.name = name;
    }

    /**
     * [静态方法]
     */
    static void testStaticMethod() {

        // 通过构造方法，初始化名字叫蜗牛的人
        Person woniu = new Person("蜗牛");

        // 通过构造方法，初始化名字叫小白的人
        Person xiaobai = new Person("小白");

        // 假设全世界的面包数量就 100 个，并且生产已经停滞
        Person.breadNum = 100;

        // 蜗牛吃五个面包
        woniu.eatBread(5);

        // 小白吃六个面包
        xiaobai.eatBread(6);

        // 打印成员变量和静态变量的值
        System.out.println(woniu.name + "和" + xiaobai.name + "吃饱后，世界只剩 " + Person.breadNum + " 个面包了！");

    }
}
