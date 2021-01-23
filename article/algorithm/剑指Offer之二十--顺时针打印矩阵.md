---
title: 剑指Offer之二十--顺时针打印矩阵
date: 2016-03-27 14:13:41
categories: 算法
tags: [Java,矩阵,顺时针]
---

## 题目描述
- 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
- 例如，如果输入如下矩阵：
  +  1　　2　　3　　4
  +  5　　6　　7　　8 
  +  9　　10　11　 12 
  +  13　 14 　15 　16
- 则依次打印出数字
 + 1,2,3,4,
 + 8,12,16,
 + 15,14,13,
 + 9,5,
 + 6,7,
 + 11,
 + 10

## 解析
- 因为每打印一圈都会改变起始坐标，所以需要先确定矩阵大小与起始坐标的关系
 + 比如4阶矩阵，第一圈起始坐标为(0,0)，第二圈起始坐标为(1,1)，打印两圈之后就结束了
 + 比如一个5阶矩阵，前两圈同4阶，第三圈为(2,2)，而且只打印了一个数。
 + 会发现，只要起始坐标的两倍小于阶数，就会一直转圈打印
 + 所以停止转圈打印的条件就是起始坐标的2倍大于或者等于阶数
- 然后考虑每一圈的打印方法
 + 第一步:从左到右打印一行
 + 第二步：从上到下打印一列（需满足终止行号大于起始行号）
 + 第三步：从右到左打印一列（需满足终止行号大于起始行号的同时，终止列号大于起始列号）
 + 第四步：从下到上打印一列（需满足终止行号比起始行号大2，终止列号大于起始列号）
 + 注意：打印矩阵最里面一圈可能只需要三步、两步、甚至一步

## 代码实现
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        
    	if(matrix == null || matrix.length <= 0 || matrix[0].length <= 0){
    		return null;
    	}
    	
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	int rows = matrix.length;
    	int cols = matrix[0].length;
    	//起点坐标
    	int start = 0;
    	
    	while(rows > 2 * start && cols > 2 * start){
    		printMatrixInCircle(list,matrix,rows,cols,start);
    		start++;
    	}
    	
    	return list;
    }

    /**
     * 打印每一圈
     * @param list		返回的集合
     * @param matrix	矩阵
     * @param rows		行数
     * @param cols		列数
     * @param start		起点坐标
     */
	private void printMatrixInCircle(ArrayList<Integer> list, int[][] matrix, int rows, int cols, int start) {
		
		//endX为终止行号，endY为终止列号
		int endX = rows - start - 1;
		int endY = cols - start - 1;
		
		//从左到右的一行
		for(int i = start; i <= endY; i++){
			list.add(matrix[start][i]);
		}
		
		//从上到下的一行
		if(endX > start){
			for(int i = start + 1; i <= endX; i++){
				list.add(matrix[i][endY]);
			}
		}
		
		//从右到左的一行
		if(endX > start && endY > start){
			for(int i = endY - 1; i >= start; i--){
				list.add(matrix[endX][i]);
			}
		}
		
		//从下到上的一行
		if(endX - 1 > start && endY > start){
			for(int i = endX - 1; i >= start + 1; i--){
				list.add(matrix[i][start]);
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