---
title: 剑指Offer之十四--调整数组顺序使奇数位于偶数前面
date: 2016-03-26 16:18:30
categories: 算法
tags: [排序,Java,数组]
---

## 基本解法
- 保证奇数和奇数，偶数和偶数之间的相对位置不变
- 遍历每个元素，一旦发现偶数就取出来，让它之后的元素向前移动，把取出来的元素补到最后的空位上
- 类似插入排序，具体实现是外循环找奇数，内循环将该数之前的偶数移位

---

	public static void reOrderArray(int array[]){
		
		if(array == null || array.length == 0){
			return;
		}
		
		for(int i = 1; i < array.length; i++){
			int current = array[i];
			if(!isEven(current)){		//找到奇数位置
				int j = i - 1;			//从奇数前一个位置开始
				for(; j >= 0 && isEven(array[j]); j--){	//发现偶数就移位
					array[j + 1] = array[j];
				}
				array[j + 1] = current;		//把奇数插入到偶数前面
			}
		}
	}
## 高效解法
- 相对位置可以改变时，更为高效的解法
- 维护两个指针，一首一尾
- 首指针向后遍历，检测到偶数时暂停，尾指针向前遍历，检测到奇数时暂停
- 交换首尾两个数
- 继续这样的判断，直到首尾指针相遇。

---
	public static void reOrderArray2(int array[]){
		
		int low = 0;
		int high = array.length - 1;
		while(low < high){
			while(low < high && !isEven(array[low])){
				low++;
			}
			while(low < high && isEven(array[high])){
				high--;
			}
			if(low < high){
				int temp = array[low];
				array[low] = array[high];
				array[high] = temp;
			}
			
			
		}
		
	}

## 判断一个数是否为偶数
	public static boolean isEven(int i){
		return (i & 0x1) == 0;
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