package cn.java4u.codebase;

/**
 * 布尔类型变量
 *
 * @author 蜗牛
 * @from 公众号：蜗牛互联网
 */
public class BooleanVar {

    public static void main(String[] args) {

        // 布尔未赋值
        boolean boolNoValue;

        // 布尔赋值 true
        boolean boolTrue = true;

        // 布尔赋值一个计算结果
        boolean boolWithResult = 2 > 1;

        // 布尔赋值 非 true 或 false 会编译报错
//        boolean boolCompileError = 123;

        System.out.println("boolTrue: " + boolTrue + ", boolWithResult: " + boolWithResult);
    }


}
