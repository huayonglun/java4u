---
title: 剑指Offer之十七--合并两个排序的链表
date: 2016-03-26 16:18:30
categories: 算法
tags: [链表,Java,合并,排序]
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
- 输入两个单调递增的链表，输出两个链表合成后的链表
- 当然我们需要合成后的链表满足单调不减规则

## 递归解法
- 比较两个链表的开头结点，则可以确定合并后链表的第一个结点
- 除合并后的结点外，再次比较两个链表的开头结点，则可以确定合并后链表的第二个结点
- 以此类推，直到所有结点均成为合并后链表中的结点

---
    public static ListNode merge(ListNode list1,ListNode list2) {
    	
    	if(list1 == null){
    		return list2;
    	}
    	if(list2 == null){
    		return list1;
    	}
    	
    	ListNode mergeListHead = null;
    	if(list1.value < list2.value){
    		mergeListHead = list1;
    		mergeListHead.next =merge(list1.next,list2);
    	}else{
    		mergeListHead = list2;
    		mergeListHead.next = merge(list1,list2.next);
    	}
    	
        return mergeListHead;
    }
## 非递归解法
- 初始化合并后的头结点
- 遍历两个链表，取出较小的结点，加入到合并链表中
- 如果长度不同，处理剩余的结点到合并链表中

---
    public static ListNode merge2(ListNode list1,ListNode list2) {
    	
    	if(list1 == null){
    		return list2;
    	}
    	if(list2 == null){
    		return list1;
    	}
    	
    	ListNode mergeList = null;
    	ListNode curNode = null;
    	
    	//初始化第一个结点
    	if(list1.value < list2.value){
    		curNode = list1;
    		list1 = list1.next;
    		curNode.next = null;
    		mergeList = curNode;
    	}else{
    		curNode = list2;
    		list2 = list2.next;
    		curNode.next = null;
    		mergeList = curNode;
    	}
    	
    	//遍历两个链表，取出较小的结点，加入到合并链表中
    	ListNode mergeNode = mergeList;
    	while(list1 != null && list2 != null){
    		if(list1.value < list2.value){
    			curNode = list1;
    			list1 = list1.next;
    			curNode.next = null;
    			mergeNode.next = curNode;
    			mergeNode = mergeNode.next;
    		}else{
    			curNode = list2;
    			list2 = list2.next;
    			curNode.next = null;
    			mergeNode.next = curNode;
    			mergeNode = mergeNode.next;
    		}
    	}
    	
    	//处理剩余的结点
    	while(list1 != null){
    		curNode = list1;
    		list1 = list1.next;
    		curNode.next = null;
    		mergeNode.next = curNode;
    		mergeNode = mergeNode.next;
    	}
    	while(list2 != null){
    		curNode = list2;
    		list2 = list2.next;
    		curNode.next = null;
    		mergeNode.next = curNode;
    		mergeNode = mergeNode.next;
    	}
    	
    	return mergeList;
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