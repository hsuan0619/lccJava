/*
 * 修改99乘法表
 * 2*1=2 3*1=3......9*1=9
 * 2*2=4 3*2=6......9*2=18
 **/

public class Q1_0717 {
	public static void main(String[] args) {
		java.util.Scanner keyin= new java.util.Scanner(System.in);
		int first,second;
		while(true) {
			System.out.println("請問你想要幾乘幾的乘法表呢？");
			System.out.println("請先輸入你想要的被乘數(前面的數)最大單位：");
			first=keyin.nextInt();
			if(first<=0) {
				System.out.println("你這個數母湯喔！請你輸入大於零的數！");
				continue;
			}
			System.out.println("請輸入你想要的乘數(後面的數)最大單位：");
			second=keyin.nextInt();
			if(second<=0) {
				System.out.println("你這個數母湯喔！請你輸入大於零的數！");
				continue;
			}
			break;
		}
		for(int i=1;i<=second;i++) {
			for(int j=2;j<=first;j++) {
				System.out.print(j+"*"+i+"="+(i*j)+"\t");
			}
			System.out.println();
		}
	}
}
