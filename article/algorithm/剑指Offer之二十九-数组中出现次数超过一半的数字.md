---
title: 剑指Offer之二十九--数组中出现次数超过一半的数字
date: 2016-04-18 13:20:35
categories: 算法
tags: [Java,数组,查找]
---

## 题目描述
- 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
- 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
- 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
- 如果不存在则输出0。

## 解法1
- 使用map，键存元素，值存出现的次数
- 发现有某个元素的次数超过数组长度的一半，则返回该元素

## 代码实现
    public int moreThanHalfNum(int [] array) {
     
    	if(array == null || array.length == 0){
    		return 0;
    	}
    	int flag = 0;
    	Map<Integer,Integer> map = new HashMap<Integer,Integer>();
    	int len = array.length;
    	for(int i = 0; i < len; i++){
    		if(map.containsKey(array[i])){
    			map.put(array[i], map.get(array[i]) + 1);
    		}else{
    			map.put(array[i], 1);
    		}
    		if(map.get(array[i]) > len / 2){
    			flag = array[i];
    			break;
    		}
    	}
    	return flag;
    }

## 解法2
- 设置标识变量，遇到相同的数字，就把次数加1，如果没有遇到就把次数减1
- 由于要找的数字出现的次数一定大于其他数字出现次数之和
- 所以那个数字肯定是最后一次吧times变量设为1对应的数字

## 代码实现
    public int moreThanHalfNum(int[] array) {
    	if(array == null || array.length == 0){
    		return 0;
    	}
    	int times = 1;
    	int result = array[0];
    	for(int i = 1; i < array.length; i++){
    		if(times == 0){
    			result = array[i];
    			times = 1;
    		}else if(array[i] == result){
    			//遇到相同的数字次数就加1
    			times++;
    		}else{
    			//没有遇到相同的就把次数减1
    			times--;
    		}
    	}
    	if(!checkMoreThanHalfNum(array, result)){
    		return 0;
    	}
    	return result;
    }

    //检查该数字是否出现的次数超过一半
    private boolean checkMoreThanHalfNum(int[] array, int result){
    	int times = 0;
    	for(int i = 0; i < array.length; i++){
    		if(array[i] == result){
    			times++;
    		}
    	}
    	boolean isMoreThanHalf = true;
    	if(times * 2 <= array.length){
    		isMoreThanHalf = false;
    	}
    	return isMoreThanHalf;
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