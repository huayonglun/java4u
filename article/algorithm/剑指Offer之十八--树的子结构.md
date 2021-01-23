---
title: 剑指Offer之十八--树的子结构
date: 2016-03-27 09:18:30
categories: 算法
tags: [树,Java,遍历,二叉树]
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
- 输入两颗二叉树A，B，判断B是不是A的子结构

## 解析
- 二叉树遍历算法的应用
- 原二叉树是否具有某棵子树，只需要判断每个结点是否都在二叉树中出现即可
- 第一步在树A中找到和B的根结点的值一样的结点R
- 第二步再判断树A中以R为根结点的子树是不是包含和树B一样的结构

## 递归实现
	//判断根结点为root1的树是否包含root2结构
    public boolean hasSubtree(TreeNode root1,TreeNode root2) {
		//初始化标记变量为false
    	boolean hasSubtreeFlag = false;
    	
		//先判断根结点，若不包含判断左孩子，其次是右孩子
    	if(root1 != null && root2 != null){
    		if(root1.val == root2.val){
    			hasSubtreeFlag = nodesValEqual(root1,root2);
    		}
    		if(!hasSubtreeFlag){
    			hasSubtreeFlag = hasSubtree(root1.left,root2);
    		}
    		if(!hasSubtreeFlag){
    			hasSubtreeFlag = hasSubtree(root1.right,root2);
    		}
    	}
    	
    	return hasSubtreeFlag;
    }
	
	//判断以root1为根的树上各结点值是否与root2相等
	public  boolean nodesValEqual(TreeNode root1, TreeNode root2) {
		
		if(root2 == null){
			return true;
		}
		if(root1 == null){
			return false;
		}
		if(root1.val != root2.val){
			return false;
		}
		
		return nodesValEqual(root1.left,root2.left) && nodesValEqual(root1.right,root2.right);
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