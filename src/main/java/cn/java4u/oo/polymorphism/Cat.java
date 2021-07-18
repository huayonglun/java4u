package cn.java4u.oo.polymorphism;

/**
 * 猫
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class Cat extends Animal {

    @Override
    public void eat() {
        super.eat();
        System.out.println("Cat.eat");
    }
}
