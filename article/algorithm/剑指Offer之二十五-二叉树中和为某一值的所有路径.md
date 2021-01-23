---
title: 剑指Offer之二十五--二叉树中和为某一值的所有路径
date: 2016-04-07 13:10:27
categories: 算法
tags: [树,Java,先序遍历,路径]
---

## 题目描述
- 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
- 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。

## 解析
- 先序遍历，将遍历过的结点放到A集合中
- 当遍历到叶子结点并且和恰好是目标值时，将遍历经过的所有结点放到B集合中，B则是满足题意的一条路径
- 如果遍历到叶子结点和仍然不等于目标值，那么就移除A集合中添加的结点，修改和，切换到右孩子结点重新计算
- 如果没有遍历到叶子结点就从孩子结点中继续寻找这样的路径

## 二叉树定义
	class TreeNode {
	    int val = 0;
	    TreeNode left = null;
	    TreeNode right = null;

	    public TreeNode(int val) {
	        this.val = val;

	    }

	}

## 代码实现
	public ArrayList<ArrayList<Integer>> findPath(TreeNode root,int target) {
        /*
         * currentSum	记录当前和
         * pathNodes	保存当前路径扫描过的结点
         * pathList		保存满足条件的各个路径
         */
		int currentSum = 0;
		ArrayList<Integer> pathNodes = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> pathList = new ArrayList<ArrayList<Integer>>();
		
		if(root == null){
			return pathList;
		}
		
		return findPath(pathList,pathNodes,root,target,currentSum);
    }


	private ArrayList<ArrayList<Integer>> findPath(
			ArrayList<ArrayList<Integer>> pathList,
			ArrayList<Integer> pathNodes,
			TreeNode root, 
			int target, 
			int currentSum) {
		
		currentSum += root.val;
		pathNodes.add(root.val);
		boolean isLeafNode = root.left == null && root.right == null;
		//如果是叶子结点并且等于目标值，则把当前的路径添加到pathList中
		if(currentSum == target && isLeafNode){
			ArrayList<Integer> nodes = new ArrayList<Integer>();
			for(Integer node : pathNodes){
				nodes.add(node);
			}
			pathList.add(nodes);
		}
		//如果不是叶子结点则遍历它的子结点
		if(root.left != null){
			findPath(pathList, pathNodes, root.left, target, currentSum);
		}
		if(root.right != null){
			findPath(pathList, pathNodes, root.right, target, currentSum);
		}
		
		//在返回父节点之前，在路径上删除当前结点
		Integer node = pathNodes.remove(pathNodes.size() - 1);
		currentSum -= node;
		
		
		return pathList;
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