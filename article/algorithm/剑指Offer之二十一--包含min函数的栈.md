---
title: 剑指Offer之二十一--包含min函数的栈
date: 2016-03-27 14:13:41
categories: 算法
tags: [Java,栈,最小值]
---

## 题目描述
- 定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。

## 解析
- 在数据栈的基础上维护一个用来存放最小值的辅助栈
- 每次入栈，新入栈的元素都要和辅助栈栈顶元素比较，如果新入栈的元素更小，就让它也加入最小栈中，否则最小栈再次压入其栈顶元素
- 每次出栈，数据栈和辅助栈同时弹出元素
- 这样就可以保证在任何情况下，辅助栈栈顶元素始终是数据栈中的最小元素

## 代码实现
    public class MinFunctionStack{
    	
    	Stack<Integer> data = new Stack<Integer>();
    	Stack<Integer> assist = new Stack<Integer>();
    	
    	public void push(int node) {
            data.push(node);
            if(assist.size() == 0 || node < assist.peek()){
            	assist.push(node);
            }else{
            	assist.push(assist.peek());
            }
        }
        
        public void pop() {
        	if(data.size() > 0 && assist.size() > 0){
        		data.pop();
        		assist.pop();
        	}
        }
        
        public int top() {
        	if(data.size() > 0){
        		return data.peek();
        	}
        	return Integer.MIN_VALUE;
            
        }
        
        public int min() {
        	if(data.size() > 0 && assist.size() > 0){
        		return assist.peek();
        	}
        	return Integer.MIN_VALUE;
        }
    	
    }



<p></p>
--- 
<center>

<div align="center" style="color: rgb(212, 137, 88); font-size: x-large; font-family: 楷体; ">欢迎关注微信公众号，技术，思维，心理，带给你认知的全方位成长。<br/>


![](https://ws1.sinaimg.cn/large/006tNbRwgy1fvibc07tuqj30hs07q0u7.jpg)


你的关注，就是对我最大的肯定，我会努力产出的，我们一起成长~ 

本文由 **永伦的小屋** 原创。
转载请**注明作者及出处**,本文作者为 永伦的小屋。

</div>
</center>