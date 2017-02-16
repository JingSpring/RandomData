package com.xjc.datatest;

//有序顺序表的初始化及具体操作方法
public class DataListUtils {
	
	private int[] array;   //定义操作数组
	private int num;      //记录数组元素数量
	
	//构造方法，初始化顺序表
	public DataListUtils(int maxSize) {
		array = new int[maxSize];
		num = 0;
	}
	//插入数据并排序
	public void insert(int value) {
		int a = 0;
		for(a=0;a<num;a++) {
			if(array[a]>value) {
				break;
			}
		}
		for(int i=num;i>a;i--) {
			array[i] = array[i-1];
		}
		array[a] = value;
		num++;
	}
	//先找到数据在文件中的具体位置，然后进行删除操作
	public boolean delete(int value) {
		int n = find(value);
		if(n != -1) {
			for(int i = n;i<num-1;i++) {
				array[i] = array[i+1];
			}
			num--;
			return true;
		}
		return false;
	}
	//通过二分法查找数组元素并返回元素位置
	public int find(int keySearch) {
		int lowerBound = 0;
		int upperBound = num-1;
		int curIn = 0;
		while(true) {
			curIn = (lowerBound+upperBound)/2;
			if(array[curIn]==keySearch) {
				return curIn;
			}else if(lowerBound>upperBound) {
				return -1;
			}else{
				if(array[curIn]>keySearch) {
					upperBound = curIn - 1;
				}else{
					lowerBound = curIn + 1;
				}
			}
		}
	}
	//输出显示数据
	public void display() {
		for(int i=0;i<num;i++) {
			System.out.print(array[i]+"\t");
		}
		System.out.print("\n");
	}
}
