package cn.java4u.oo.polymorphism;

/**
 * 动物
 *
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class Animal {


    /**
     * 与 eat(String food) 重载
     */
    public void eat() {
        System.out.println("Animal.eat");
    }

    /**
     * 与 eat() 重载
     *
     * @param food 食物
     */
    public void eat(String food) {
        System.out.println("Animal.eat: " + food);
    }

    /**
     * 覆写
     *
     * @return 字符串
     * @see java.lang.Object#toString
     */
    @Override
    public String toString() {
        return "Animal " + super.toString();
    }
}
