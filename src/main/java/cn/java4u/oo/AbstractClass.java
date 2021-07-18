package cn.java4u.oo;

/**
 * 抽象类
 *
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public abstract class AbstractClass {

    String name;

    /**
     * 实例方法
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 抽象方法-操作
     *
     * @return 结果
     */
    public abstract String operate();
}
