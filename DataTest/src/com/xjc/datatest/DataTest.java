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
	
	static String filePath = "D:\\array.txt";     //文件保存路径
	
	static int numofDataList = 0;       //记录要操作的元素个数
	
	//定义一个非递减有序、最大长度为10000的顺序表
	static DataListUtils utils = new DataListUtils(10000);       
	
	static int[] array;     //定义一个保存文件排序前的元素位置的数组

	public static void main(String[] args) {
		
		boolean isRun = true;    //判断是否结束操作

		int number=0;     //记录操作数
		
		System.out.println("欢迎使用本系统！");
		while(isRun) {
			
			System.out.println("1---随机产出10000个随机数，保存到文件中");
			System.out.println("2---查看文件中前n个元素");
			System.out.println("3---获取文件中的前n个元素，依次插入到有序顺序表中");
			System.out.println("4---有序顺序表中元素按在数据文件中的顺序逐个将元素删除");
			System.out.println("5---结束操作");
			System.out.print("请选择以上数字进行操作：");
			
			//如果不抛出异常，从键盘输入字符就会出现错误
			try {
				Scanner scanner = new Scanner(System.in);
				number = scanner.nextInt();
			} catch(InputMismatchException e) {
				//使程序继续循环
				isRun = true;
			}
			switch(number) {
			case 1:
				createNumberIntToFile(filePath);
				System.out.println();      //空出一行
				break;
			case 2:
				System.out.print("请输入要输入的元素个数：");
				Scanner sca = new Scanner(System.in);
				int count = sca.nextInt();
				showDataFromFile(count);
				System.out.println();      //空出一行
				break;
			case 3:
				System.out.print("请输入想要插入有序顺序表的元素个数：");
				Scanner scanner2 = new Scanner(System.in);
                numofDataList = scanner2.nextInt();
				insertToDataList(numofDataList, filePath);
				break;
			case 4:
				deleteFormDataList(numofDataList);
				System.out.println();      //空出一行
				break;
			case 5:
				System.out.println();      //空出一行
				isRun=false;
				break;
			default:
				System.out.println("输入错误，不存在该操作！！！");
				System.out.println();
			}
		}
		System.out.println("操作结束，谢谢使用！");
	}

	// 自行产生 10000 个 0 到 9999 之间的随机数，按照产生的顺序依次把它们存放到一个array.txt文件中。
	public static void createNumberIntToFile(String filePath) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("array.txt文件创建完成");
			}
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);

			Random random = new Random();
			for (int i = 0; i < 1000; i++) {

				for (int a = 1; a <= 10; a++) {
					// 随机产生一个数字
					int randint = (int) Math.floor((random.nextDouble() * 10000.0));
					// 将数字转化为字符型存入array.txt文件中
					bw.write(String.valueOf(randint) + "\t");
				}
				bw.newLine();
			}
			bw.close();
			fw.close();
			
			System.out.println("随机数产生完成，并已存入"+filePath+"中");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 查看文件中的前n个元素
	public static void showDataFromFile(int n) {
		
		if(n>10000) {
			System.out.println("文件中不存在"+n+"个元素");
			return;
		}
		
		int num = n % 10; // 计算n的除于10的余数
		int count = (n - num) / 10; // 得到文件需要读取的行数

		try {
			String encoding = "GBK";       //声明文件编码形式
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				String lineTxt = null;
				BufferedReader bufferedReader = new BufferedReader(read);

				int[] array1 = new int[n];
				int i = 0;

				for (int k = 0; k <= count; k++) {
					// 读取一行文件数据并保存到str1数组中
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
				System.out.print("从文件中读出的前" + n + "元素为：");
				for (int k = 0; k < n; k++) {
					System.out.print(array1[k]);
					System.out.print("\t");
				}
				System.out.println();

				read.close();
			} else {
				System.out.println("找不到指定文件,文件还未创建！");
			}
		} catch (Exception e) {
			System.out.println("读取文件出错");
			e.printStackTrace();
		}
	}

	// 获取文件中的前n个元素，依次插入到有序顺序表中
	public static void insertToDataList(int n,String filePath) {
		
		if(n>10000) {
			System.out.println("文件中不存在"+n+"个元素");
			return;
		}
		
		int num = n%10;             //计算n的除于10的余数
		int count = (n-num)/10;    //得到文件需要读取的行数
		
		try {
			String encoding = "GBK";        //声明文件编码形式
			File file = new File(filePath);
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				String lineTxt = null;
				BufferedReader bufferedReader = new BufferedReader(read);
				
 				array = new int[numofDataList];
 				int i = 0;
				
				//从array.txt文件中一个一个地读取数据，并依次排序
 				System.out.println("从文件中读出的前"+ n +"个数插入到有序顺序表中,插入完成后输出顺序表的全部元素：");
				for(int k=0;k<=count;k++) {
					//读取一行文件数据并保存到str1数组中
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
				System.out.println();      //空出一行
				System.out.println("从文件中读出的前" + n + "元素为：");
				for(int k = 0;k<n;k++) {
					System.out.print(array[k]);
					System.out.print("\t");
				}
				System.out.println();
				
				read.close();
			} else {
				System.out.println("找不到指定文件,文件还未创建！");
			}
		} catch (Exception e) {
			System.out.println("读取文件出错");
			e.printStackTrace();
		}
	}
	
	//对已经插入数据元素的顺序表，按照表中元素在数据文件中的顺序逐个将元素删除，直到表空
	public static void deleteFormDataList(int n) {
		
		for (int c = 0; c < n; c++) {
			
			utils.delete(array[c]);
			System.out.print("删除的元素为：" + array[c]);
			System.out.println();
			System.out.print("余下元素有：");
			utils.display();
		}
		System.out.println("表为空！！！");
	}
}