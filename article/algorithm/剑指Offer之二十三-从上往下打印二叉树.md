---
title: 剑指Offer之二十三--从上往下打印二叉树
date: 2016-04-04 15:43:15
categories: 算法
tags: [树,Java,层序遍历,二叉树]
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
- 从上往下打印出二叉树的每个节点，同层节点从左至右打印。

## 解析
- 考察层序遍历
- 每一次打印一个结点的时候，如果该结点有子节点，则把该结点的子节点放到一个队列的末尾。
- 接下来到队列的头部取出最早进入队列的结点，重复前面的打印操作，直至队列中所有的结点都被打印出来为止。

## 代码实现
    public ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(root==null){
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            }
            list.add(treeNode.val);
        }
        return list;
    }
## 辅助包
	import java.util.ArrayList;
	import java.util.LinkedList;
	import java.util.Queue;



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