package cn.java4u.oo.packagedemo;

/**
 * getter 和 setter 演示
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class GetterSetterDemo {

    /**
     * 成员变量私有化
     */
    private String name;

    /**
     * 公开方法获取成员变量值
     *
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 公开方法设置成员变量值
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }
}
