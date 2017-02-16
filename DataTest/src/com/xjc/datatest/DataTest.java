package com.xjc.datatest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class DataTest {
	
	static String filePath = "D:\\array.txt";     //�ļ�����·��
	
	static int numofDataList = 0;       //��¼Ҫ������Ԫ�ظ���
	
	//����һ���ǵݼ�������󳤶�Ϊ10000��˳���
	static DataListUtils utils = new DataListUtils(10000);       
	
	static int[] array;     //����һ�������ļ�����ǰ��Ԫ��λ�õ�����

	public static void main(String[] args) {
		
		boolean isRun = true;    //�ж��Ƿ��������

		int number=0;     //��¼������
		
		System.out.println("��ӭʹ�ñ�ϵͳ��");
		while(isRun) {
			
			System.out.println("1---�������10000������������浽�ļ���");
			System.out.println("2---�鿴�ļ���ǰn��Ԫ��");
			System.out.println("3---��ȡ�ļ��е�ǰn��Ԫ�أ����β��뵽����˳�����");
			System.out.println("4---����˳�����Ԫ�ذ��������ļ��е�˳�������Ԫ��ɾ��");
			System.out.println("5---��������");
			System.out.print("��ѡ���������ֽ��в�����");
			
			//������׳��쳣���Ӽ��������ַ��ͻ���ִ���
			try {
				Scanner scanner = new Scanner(System.in);
				number = scanner.nextInt();
			} catch(InputMismatchException e) {
				//ʹ�������ѭ��
				isRun = true;
			}
			switch(number) {
			case 1:
				createNumberIntToFile(filePath);
				System.out.println();      //�ճ�һ��
				break;
			case 2:
				System.out.print("������Ҫ�����Ԫ�ظ�����");
				Scanner sca = new Scanner(System.in);
				int count = sca.nextInt();
				showDataFromFile(count);
				System.out.println();      //�ճ�һ��
				break;
			case 3:
				System.out.print("��������Ҫ��������˳����Ԫ�ظ�����");
				Scanner scanner2 = new Scanner(System.in);
                numofDataList = scanner2.nextInt();
				insertToDataList(numofDataList, filePath);
				break;
			case 4:
				deleteFormDataList(numofDataList);
				System.out.println();      //�ճ�һ��
				break;
			case 5:
				System.out.println();      //�ճ�һ��
				isRun=false;
				break;
			default:
				System.out.println("������󣬲����ڸò���������");
				System.out.println();
			}
		}
		System.out.println("����������ллʹ�ã�");
	}

	// ���в��� 10000 �� 0 �� 9999 ֮�������������ղ�����˳�����ΰ����Ǵ�ŵ�һ��array.txt�ļ��С�
	public static void createNumberIntToFile(String filePath) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("array.txt�ļ��������");
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);

			Random random = new Random();
			for (int i = 0; i < 1000; i++) {

				for (int a = 1; a <= 10; a++) {
					// �������һ������
					int randint = (int) Math.floor((random.nextDouble() * 10000.0));
					// ������ת��Ϊ�ַ��ʹ���array.txt�ļ���
					bw.write(String.valueOf(randint) + "\t");
				}
				bw.newLine();
			}
			bw.close();
			fw.close();
			
			System.out.println("�����������ɣ����Ѵ���"+filePath+"��");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// �鿴�ļ��е�ǰn��Ԫ��
	public static void showDataFromFile(int n) {
		
		if(n>10000) {
			System.out.println("�ļ��в�����"+n+"��Ԫ��");
			return;
		}
		
		int num = n % 10; // ����n�ĳ���10������
		int count = (n - num) / 10; // �õ��ļ���Ҫ��ȡ������

		try {
			String encoding = "GBK";       //�����ļ�������ʽ
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				String lineTxt = null;
				BufferedReader bufferedReader = new BufferedReader(read);

				int[] array1 = new int[n];
				int i = 0;

				for (int k = 0; k <= count; k++) {
					// ��ȡһ���ļ����ݲ����浽str1������
					lineTxt = bufferedReader.readLine();
					String str1[] = lineTxt.split("\t");
					
					if (k < count) {

						for (int a = 0; a < str1.length; a++) {
							array1[i++] = Integer.parseInt(str1[a]);
						}
						
					} else if (k == count) {

						for (int b = 0; b < num; b++) {
							array1[i++] = Integer.parseInt(str1[b]);
						}
					}
				}
				System.out.print("���ļ��ж�����ǰ" + n + "Ԫ��Ϊ��");
				for (int k = 0; k < n; k++) {
					System.out.print(array1[k]);
					System.out.print("\t");
				}
				System.out.println();

				read.close();
			} else {
				System.out.println("�Ҳ���ָ���ļ�,�ļ���δ������");
			}
		} catch (Exception e) {
			System.out.println("��ȡ�ļ�����");
			e.printStackTrace();
		}
	}

	// ��ȡ�ļ��е�ǰn��Ԫ�أ����β��뵽����˳�����
	public static void insertToDataList(int n,String filePath) {
		
		if(n>10000) {
			System.out.println("�ļ��в�����"+n+"��Ԫ��");
			return;
		}
		
		int num = n%10;             //����n�ĳ���10������
		int count = (n-num)/10;    //�õ��ļ���Ҫ��ȡ������
		
		try {
			String encoding = "GBK";        //�����ļ�������ʽ
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				String lineTxt = null;
				BufferedReader bufferedReader = new BufferedReader(read);
				
 				array = new int[numofDataList];
 				int i = 0;
				
				//��array.txt�ļ���һ��һ���ض�ȡ���ݣ�����������
 				System.out.println("���ļ��ж�����ǰ"+ n +"�������뵽����˳�����,������ɺ����˳����ȫ��Ԫ�أ�");
				for(int k=0;k<=count;k++) {
					//��ȡһ���ļ����ݲ����浽str1������
					lineTxt = bufferedReader.readLine();
					String str1[] = lineTxt.split("\t");
					if(k<count) {
						
						for (int a = 0; a < str1.length; a++) {
							utils.insert(Integer.parseInt(str1[a]));
							utils.display();
							array[i++] = Integer.parseInt(str1[a]);
						}
						
					} else if(k==count) {
						
						for (int b=0;b<num;b++) {
							utils.insert(Integer.parseInt(str1[b]));
							utils.display();
							array[i++] = Integer.parseInt(str1[b]);
						}
					}
				}
				System.out.println();      //�ճ�һ��
				System.out.println("���ļ��ж�����ǰ" + n + "Ԫ��Ϊ��");
				for(int k = 0;k<n;k++) {
					System.out.print(array[k]);
					System.out.print("\t");
				}
				System.out.println();
				
				read.close();
			} else {
				System.out.println("�Ҳ���ָ���ļ�,�ļ���δ������");
			}
		} catch (Exception e) {
			System.out.println("��ȡ�ļ�����");
			e.printStackTrace();
		}
	}
	
	//���Ѿ���������Ԫ�ص�˳������ձ���Ԫ���������ļ��е�˳�������Ԫ��ɾ����ֱ�����
	public static void deleteFormDataList(int n) {
		
		for (int c = 0; c < n; c++) {
			
			utils.delete(array[c]);
			System.out.print("ɾ����Ԫ��Ϊ��" + array[c]);
			System.out.println();
			System.out.print("����Ԫ���У�");
			utils.display();
		}
		System.out.println("��Ϊ�գ�����");
	}
}