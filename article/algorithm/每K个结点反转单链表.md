---
title: 每K个结点反转单链表
date: 2016-04-06 15:42:47
categories: 算法
tags: [Java,链表,链表反转]
---

## 题目描述
- 给定一个常数K以及一个单链表L，请编写程序将L中每K个结点反转。
- 例如：给定L为1→2→3→4→5→6，K为3，则输出应该为3→2→1→6→5→4；
- 如果K为4，则输出应该为4→3→2→1→5→6，即最后不到K个元素不反转。

## 思路
- 通过链表长度和K值确定需要反转的结点数
- 每K个反转成新链表，把头保存到List中
- 需要反转的结点数已到并且剩下的结点数不足K个，不反转，即把当前结点存到List中
- 把List中各个链表连接

## 代码
	package com.liuyong666.pat;
	
	import java.util.ArrayList;
	import java.util.List;
	
	public class Main {
		static class ListNode{
			int val;
			ListNode next = null;
			public ListNode(int val){
				this.val = val;
			}
		}
		public static ListNode reversePartNode(ListNode head, int k){
			
			if(head == null || k < 1){
				return null;
			}

			ListNode p = head;
			//获取链表长度
			int len = 0;
			while(p != null){
				len++;
				p = p.next;
			}
			
			ListNode reverseListHead = null;
			ListNode curNode = head;
			ListNode preNode = null;
			ListNode nextNode = null;
			//List存放各链表头结点
			List<ListNode> list = new ArrayList<ListNode>();
			
			//count 计数器 记录k个元素，每k个重新置1
			int count = 1;
			//需要发生反转的结点个数
			int reverseNum = (len / k) * k;
			while(curNode != null){

				nextNode = curNode.next;
				
				if( count <= k){

					if(count == k){

						reverseListHead = curNode;
						list.add(reverseListHead);
						count = 1;
						
						curNode.next = preNode;
						preNode = null;
						curNode = nextNode;
					}else{
						count++;

						curNode.next = preNode;
						preNode = curNode;
						curNode = nextNode;
					}
				}
				
				if(reverseNum == 1 && count != k){
					list.add(curNode);
					break;
				}
				
				reverseNum--;
				
			}
			ListNode newHead = list.get(0);
			
			for(int i = 0; i < list.size() - 1; i++){
				p = list.get(i);
				while(p.next != null){
					p = p.next;
				}
				p.next = list.get(i + 1);
				
			}
			
			return newHead;
		}
	}
		public static void main(String[] args) {
			ListNode node1 = new ListNode(1);
			ListNode node2 = new ListNode(2);
			ListNode node3 = new ListNode(3);
			ListNode node4 = new ListNode(4);
			ListNode node5 = new ListNode(5);
			ListNode node6 = new ListNode(6);
			node1.next = node2;
			node2.next = node3;
			node3.next = node4;
			node4.next = node5;
			node5.next = node6;
			ListNode p = node1;
			while(p != null){
				System.out.print(p.val+" ");
				p = p.next;
			}
			System.out.println();
			
			ListNode revNode = reversePartNode(node1, 4);
			while(revNode != null){
				System.out.print(revNode.val+" ");
				revNode = revNode.next;
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