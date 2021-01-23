---
title: 剑指Offer之十五--链表中倒数第k个结点
date: 2016-03-26 16:18:30
categories: 算法
tags: [链表,Java,查找]
---

## 链表结点结构
	class ListNode{
		int value;
		ListNode next = null;
		public ListNode(int value){
			this.value = value;
		}
	}

## 基本解法
- 遍历两次，第一次确定链表长度，第二次返回第n-k+1个结点，即为所求
- 注意k不能超过链表长度，代码中要进行判断
---
	public static ListNode findKthToTail(ListNode head,int k){
		if(head == null || k <= 0 ){
			return null;
		}
		
		ListNode node = head;
		int nodesNum = 1;
		while(node.next != null){
			nodesNum++;
			node = node.next;
		}
		
		node = head;
		int count = 1;
		while(k <= nodesNum && count != nodesNum - k + 1){
			count++;
			node = node.next;
		}
		if(k <= nodesNum){
			return node;
		}
		return null;
	}

## 高效解法
- 前后指针，前指针先走k-1个结点，从第k个结点开始，后指针也跟着走
- 当前指针的next为null时，此时后指针所在的位置就为链表的第k个结点
- 同样注意还没走到第k个结点链表就结束的情况

---
	public static ListNode findKthToTail2(ListNode head,int k){
		if(head == null || k <= 0)
			return null;
		ListNode pre = head;
		ListNode behind = null;
		for(int i = 0; i < k - 1; i++){
			if(pre.next != null){
				pre = pre.next;
			}else{
				return null;
			}
		}
		
		behind = head;
		while(pre.next != null){
			pre = pre.next;
			behind = behind.next;
		}
		return behind;
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