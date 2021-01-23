---
title: 剑指Offer之十六--链表反转
date: 2016-03-26 16:18:30
categories: 算法
tags: [链表,Java,反转]
---

## 链表结点结构
	class ListNode{
		int value;
		ListNode next = null;
		public ListNode(int value){
			this.value = value;
		}
	}

## 题目描述
- 输入一个链表的头结点，反转该链表并输出翻转后的头结点

## 代码实现
- 遍历该链表
- 保存后一个结点，以防止当前结点的next值更新后链表断开
- 保存前一个结点，以便当前结点的next值更新为前一个结点
- 最后一个结点将是反转之后的头结点，保存该结点返回

---

    public static ListNode reverseList(ListNode head) {
    	
    	ListNode reverseListHead = null;
    	ListNode curNode = head;
    	ListNode preNode = null;
    	ListNode nextNode = null;
    	
    	while(curNode != null){
    		nextNode = curNode.next;
    		if(nextNode == null){
    			reverseListHead = curNode;
    		}
    		
    		curNode.next = preNode;
    		preNode = curNode;
    		curNode = nextNode;
    		
    	}
    	
    	return reverseListHead;
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