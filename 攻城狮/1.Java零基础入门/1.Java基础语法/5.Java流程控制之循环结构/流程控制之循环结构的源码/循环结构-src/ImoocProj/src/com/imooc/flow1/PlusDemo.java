package com.imooc.flow1;

public class PlusDemo {

	public static void main(String[] args) {
		// 求1到5的累加和
		//1+2+3+4+5
		int n=1;
		int sum=0;//sum是存放和的变量
		while(n<=5){
			sum=sum+n; //sum+=n;
			System.out.println(n);
			n++;//n=n+1;
		}
		System.out.println("1到5的累加和为："+sum);
		//求1+3+5+7+。。。+15
	}

}
