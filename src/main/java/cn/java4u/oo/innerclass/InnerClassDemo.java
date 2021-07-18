package cn.java4u.oo.innerclass;

/**
 * 内部类演示
 *
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class InnerClassDemo {


    /**
     * 成员内部类
     */
    private class InstanceInnerClass {}

    /**
     * 静态内部类
     */
    static class StaticInnerClass {}

    public static void main(String[] args) {

        // 两个匿名内部类
        (new Thread() {}).start();
        (new Thread() {}).start();

        // 方法内部类
        class MethodClass {}

    }

}
