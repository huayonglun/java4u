---
title: 剑指Offer之二十四--二叉搜索树的后序遍历序列
date: 2016-04-04 17:11:13
categories: 算法
tags: [树,Java,后序遍历,二叉搜索树]
---
## 题目描述
- 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
- 如果是则返回ture,否则返回false。
- 假设输入的数组的任意两个数字都互不相同。

## 解析
- 找到根结点
- 从头遍历序列，第一个比根结点大的元素为右子树的起点
- 判断右子树是否都比根结点大，若不是返回false，若是，进行下一步
- 分别把左子树和右子树都以上面规则进行判断，若左右子树都能返回true，则整个序列为二叉搜索树的后序遍历序列，返回true

## 代码实现
    public boolean verifySquenceOfBST(int [] sequence) {
        
    	if(sequence == null || sequence.length == 0){
    		return false;
    	}
    	
    	int len = sequence.length;
    	int root = sequence[len - 1];
    	
    	int i = 0;
    	for(; i < len - 1; i++){
    		if(sequence[i] > root){
    			break;
    		}
    	}
    	
    	int j = i;
    	for(;j < len - 1; j++){
    		if(sequence[j] < root){
    			return false;
    		}
    	}
    	
    	boolean left = true;
    	boolean right = true;
    	if(i > 0){
    		left = verifySquenceOfBST(Arrays.copyOfRange(sequence, 0, i));
    	}
    	if(i < len - 1){
    		right = verifySquenceOfBST(Arrays.copyOfRange(sequence,i,len - 1));
    	}
    	
    	return left && right;
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