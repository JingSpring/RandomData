package com.xjc.datatest;

//����˳���ĳ�ʼ���������������
public class DataListUtils {
	
	private int[] array;   //�����������
	private int num;      //��¼����Ԫ������
	
	//���췽������ʼ��˳���
	public DataListUtils(int maxSize) {
		array = new int[maxSize];
		num = 0;
	}
	//�������ݲ�����
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
	//���ҵ��������ļ��еľ���λ�ã�Ȼ�����ɾ������
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
	//ͨ�����ַ���������Ԫ�ز�����Ԫ��λ��
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
	//�����ʾ����
	public void display() {
		for(int i=0;i<num;i++) {
			System.out.print(array[i]+"\t");
		}
		System.out.print("\n");
	}
}
