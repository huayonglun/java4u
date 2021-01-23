---
title: 剑指Offer之二十二--栈的压入、弹出序列
date: 2016-04-01 09:31:18
categories: 算法
tags: [Java,栈]
---

## 题目描述
- 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
- 假设压入栈的所有数字均不相等。
- 例如序列12345是某栈的压栈序列，序列45321是该压栈序列对应的一个弹出序列，但43512就不可能是该压栈序列的弹出序列。

## 解析
- 栈的特点是FIFO
- 压入序列是确定的，则压入序列之间的元素可能会经历压入后马上被弹出的情况
- 具体思路为：
 1. 根据弹出序列的第一个值，判断在该元素之前被压入栈的所有元素.
 2. 栈顶元素与弹出序列第一个值进行比较，等则判断弹出序列的下一个元素，判断依据同步骤1。
 3. 如果所有的元素都被压入栈中，但是两者仍然不相等，说明弹出序列不可能是原序列的弹出序列。
 4. 这样一直判断直到弹出序列的最后一个元素。

## 代码实现
	public boolean IsPopOrder(int [] pushA,int [] popA) {
		boolean possible = false;
		//存放压入栈中的元素
		ArrayList<Integer> data = new ArrayList<Integer>();
		if(pushA.length > 0 && popA.length > 0 && pushA.length == popA.length){
			int len = pushA.length;
			//第一个弹出元素位置
			int pop = 0;
			//下一个要弹出元素的位置
			int nextPop = 0;
			
			//第一个压入栈中的元素位置
			int push = 0;
			//下一个要压入栈中的元素位置
			int nextPush = 0;
			
			int index = -1;
			while(nextPop - pop < len){
				//如果要弹出的元素与栈顶元素不相等，就一直压栈直到相等
				while(data.size() == 0 || data.get(index) != popA[nextPop]){
					
					//当全部元素添加完毕之后，结束循环
					if(nextPush - push == len){
						break;
					}
					
					data.add(pushA[nextPush]);
					nextPush++;
					index++;
					
				}
				if(data.get(index) != popA[nextPop]){
					break;
				}
				
				//移除第一个比较的元素
				data.remove(index--);
				//判断下一个待弹出元素
				nextPop++;
				
			}
			//在集合全部元素比较完毕并且弹出序列也比较完毕
			if(data.size() == 0 &&  nextPop - pop == len){
				possible = true;
			}
		}
		return possible;
	      
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