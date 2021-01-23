---
title: 剑指Offer之十九--二叉树的镜像
date: 2016-03-27 12:13:41
categories: 算法
tags: [树,Java,镜像,二叉树]
---

## 二叉树结构
	class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;
	    }
	}
## 题目描述
- 请完成一个函数，输入一个二叉树，输出它的镜像
- 二叉树镜像定义
 + 源二叉树<br>
![源二叉树](http://i.imgur.com/N0XGChb.png)
 + 镜像二叉树<br>
![镜像二叉树](http://i.imgur.com/xBszDuD.png)

## 解析
- 先序遍历给定树的每个结点
- 若遍历到的结点有子节点，就交换它的两个子结点
- 当交换完所有非叶子结点的左右子节点之后，就得到了树的镜像

## 递归实现

    public void mirror(TreeNode root) {
    	if(root == null || (root.left == null && root.right == null)){
    		return ;
    	}
    	
    	
    	TreeNode temp = root.left;
    	root.left = root.right;
    	root.right = temp;
    	
    	if(root.left != null){
    		mirror(root.left);
    	}
    	
    	if(root.right != null){
    		mirror(root.right);
    	}
    }

## 非递归实现
    public void mirror2(TreeNode root) {
    	if(root == null || (root.left == null && root.right == null)){
    		return ;
    	}
    	
    	Stack<TreeNode> s = new Stack<TreeNode>();
    	s.push(root);
    	while(!s.isEmpty()){
    		TreeNode node = s.pop();
    		
    		//交换左右孩子结点
    		TreeNode nodeTemp = node.left;
    		node.left = node.right;
    		node.right = nodeTemp;
    		
    		//遍历左子树
    		if(node.left != null){
    			s.push(node.left);
    		}
    		
    		//遍历右子树
    		if(node.right != null){
    			s.push(node.right);
    		}
    		
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