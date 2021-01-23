---
title: 剑指Offer之二十八--字符串的排列
date: 2016-04-18 11:13:05
categories: 算法
tags: [Java,字符串,排列]
---

## 题目描述
- 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
- 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
- 结果请按字母顺序输出。 

## 输入描述:
- 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。

## 思路：
1. 先不考虑是否出现重读字符，要对一个字符进行全排列，可以把第一个字符和后面的字符看成两部分
2. 而第一个字符后面的字符又可以看成第一个字符和后面两部分，是一个递归过程
3. 只要第一个字符的位置没有到达字符串的末尾就分别将第一个字符与后面的字符进行交换

## 注意：
- 第一个字符与后面的某个位置的字符发生交换后，需要再次发生交换，不然顺序就会被打乱
- 举个例子，在字符串abc中，在把第一个字符看成是a，后面的字符b，c，看成是一个整体的时候，abc这个相对的顺序不能改变
- 所以当a与b发生交换变成bac之后，需要再次交换两个字符，重新回到abc

## 代码实现
	public ArrayList<String> permutation(String str) {
		
		ArrayList<String> list = new ArrayList<String>();		
		
		if(str == null || str.length() == 0){
			return list;
		}
		
		list =  permutation(list, str.toCharArray(), 0, str.length());
		Collections.sort(list);
		return list;
    }

	//递归的将满足题意的字符串添加到list中
	private ArrayList<String> permutation(ArrayList<String> list, char[] str, int begin, int length) {
		
		if(begin == length - 1){
			if(!list.contains(String.valueOf(str))){
				list.add(String.valueOf(str));
			}
		}else{
			for(int i = begin; i < length; i++){
				if(i == begin || str[i] != str[begin]){
					swap(str, begin, i);
					permutation(list, str,begin + 1, length);
					swap(str, begin, i);
				}
			}
		}
		return list;
	}

	private void swap(char[] str, int begin, int i) {
		char temp = str[begin];
		str[begin] = str[i];
		str[i] = temp;
		
	}
	
	//非递归解法
	public ArrayList<String> permutation1(String str) {
		//使用TreeSet，有序
		TreeSet<String> tree = new TreeSet<String>();
		//创建一个栈保存每次排列的字符组合
		Stack<String[]> stack = new Stack<String[]>();
		//存放最终的排列结果
		ArrayList<String> results = new ArrayList<String>();
		
		stack.push(new String[] {str,""});
		do{
			//这里popStrs就是str输入的字符串
			String[] popStrs = stack.pop();
			//空串
			String oldStr = popStrs[1];
			//Str
			String stackStr = popStrs[0];
			for(int i = stackStr.length() - 1; i >= 0; i--){
				String[] strs = new String[]{
						stackStr.substring(0, i) + stackStr.substring(i + 1),
						oldStr + stackStr.substring(i, i + 1)
						};
				if(strs[0].length() == 0){
					tree.add(strs[1]);
				}else{
					stack.push(strs);
				}
				
			}
		}while(!stack.isEmpty());
		for(String s : tree){
			results.add(s);
		}
		
		return results;
		
		
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