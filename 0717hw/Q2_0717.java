/**
 * 1
 * 22
 * 333
 * 4444
 * 55555
 * 4444
 * 333
 * 22
 * 1
 *  * */


public class Q2_0717 {
	public static void main(String[] args) {
		java.util.Scanner keyin = new java.util.Scanner(System.in);
		int num;
		System.out.println("你的圖案最大數字要到多少呢？");
		num=keyin.nextInt();
		
		for(int i=1;i<=num;i++) {
			for(int j=0;j<i;j++) {
				System.out.print(i);
			}
			System.out.println();
		}
		for(int i=num-1;i>0;i--) {
			for(int j=0;j<i;j++) {
				System.out.print(i);
			}
			System.out.println();
		}
	}
}
