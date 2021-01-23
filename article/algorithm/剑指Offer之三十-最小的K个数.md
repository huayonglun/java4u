---
title: 剑指Offer之三十--最小的K个数
date: 2016-04-18 20:01:13
categories: 算法
tags: [Java,数组,查找,top k]
---
## 题目描述
- 输入n个整数，找出其中最小的K个数。
- 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。

## 解法1
- 对数组排序，取前边k个数存到list集合中
- 该解法时间复杂度依赖于排序

## 代码实现
    public ArrayList<Integer> getLeastNumbers(int [] input, int k) {
    	
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	if(input == null || input.length == 0 || input.length < k || k <= 0){
    		return list;
    	}
        Arrays.sort(input);
        for(int i = 0; i < k; i++){
        	list.add(input[i]);
        }
    	return list;
    }

## 解法2
- 基于partition函数
- 只有当我们可以修改输入的数组时可用

## 代码实现
    public ArrayList<Integer> GetLeastNumbers_Solution2(int [] input, int k) {
    	
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	if(input == null || input.length == 0 || input.length < k || k <= 0){
    		return list;
    	}
    	int start = 0;
    	int end = input.length - 1;
    	int index = partition(input, start, end);
    	while(index != k - 1){
    		if(index > k - 1){
    			end = index - 1;
    			index = partition(input, start, end);
    		}else{
    			start = index + 1;
    			index = partition(input, start, end);
    		}
    	}
    	for(int i = 0; i < k; i++){
        	list.add(input[i]);
        }
    	return list;
    }
    private int partition(int[] input, int low, int high) {
		int pivotKey = input[low];
		while(low < high){
			while(low < high && input[high] >= pivotKey){
				high--;
			}
			input[low] = input[high];
			while(low < high && input[low] <= pivotKey){
				low++;
			}
			input[high] = input[low];
		}
		input[low] = pivotKey;
		return low;
	}

## 解法3
- 适合海量数据的处理
- 创建一个用于保存最小k个数的容器
- 第一步是放入k个元素，
- 第二步是比较容器中最大的数与当前数组遍历的那个数的大小
- 如果容器中最大的数比当前遍历的数大，就移除该数，并放入当前遍历的那个数
- **最大堆**或**红黑树**都能实现这样的容器

## 代码实现
    public ArrayList<Integer> GetLeastNumbers_Solution3(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
    	if(input == null || input.length == 0 || input.length < k || k <= 0){
    		return list;
    	}
    	TreeSet<Integer> s = new TreeSet<Integer>();
    	for(int i = 0; i < input.length; i++){
    		if(s.size() < k){
    			s.add(input[i]);
    		}else{
    			if(input[i] < s.last()){
    				s.remove(s.last());
    				s.add(input[i]);
    			}
    		}
    	}
    	Iterator<Integer> it = s.iterator();
    	while(it.hasNext()){
    		list.add(it.next());
    	}
    	return list;
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