---
title: 剑指Offer之二十六--复杂链表的复制
date: 2016-04-07 14:21:14
categories: 算法
tags: [Java,链表,复制]
---

## 题目描述
- 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点）。
- 复制一个复杂链表

## 结点定义
	class RandomListNode {
		int label;
		RandomListNode next = null;
		RandomListNode random = null;
		
		public RandomListNode(int label){
			this.label = label;
		}
	}

## 解析
- 遍历链表，每个结点后边复制相同的结点，设置next指针
- 复制结点的特殊指针。如果原始链表上的结点N的random指向S，则它对应的复制结点N\`的random指向S的下一个结点S\`
- 抽取复制结点，连起来得到复制链表

## 代码实现
    public RandomListNode clone(RandomListNode pHead){
    	cloneNodes(pHead);
    	connectRandomNodes(pHead);
        return reconnectNodes(pHead);
    }

	//复制原始链表的任意结点N并创建新结点N`，再把N`链接到N的后面
	private void cloneNodes(RandomListNode pHead) {
		RandomListNode curNode = pHead;
		while(curNode != null){
			RandomListNode nextNode = curNode.next;
			RandomListNode copyNode = new RandomListNode(curNode.label);
			copyNode.next = nextNode;
			copyNode.random = null;
			
			curNode.next = copyNode;
			curNode = copyNode.next;
		}
		
	}
	
	//如果原始链表上的结点N的random指向S，则它对应的复制结点N`的random指向S的下一个结点S`
	private void connectRandomNodes(RandomListNode pHead) {
		RandomListNode curNode = pHead;
		while(curNode != null){
			RandomListNode copyNode = curNode.next;
			if(curNode.random != null){
				copyNode.random = curNode.random.next;
			}
			curNode = copyNode.next;
		}
		
	}
	
    //组合复制的结点
	private RandomListNode reconnectNodes(RandomListNode pHead) {
		
		RandomListNode curNode = pHead;
		RandomListNode copyHead = null;
		RandomListNode copyNode = null;
		
		//设置头结点
		if(curNode != null){
			copyHead = curNode.next;
			copyNode = curNode.next;
			curNode.next = copyNode.next;
			curNode = curNode.next;
			
		}
		
		while(curNode != null){
			 copyNode.next = curNode.next;
			 copyNode = copyNode.next;
			 curNode.next = copyNode.next;
			 curNode = curNode.next;
				
		}
		return copyHead;
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