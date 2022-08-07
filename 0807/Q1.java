/**
 * 有一對兔子，出生後第3個月每月生一對兔子，
 * 兔子每第三個月起會生一對
 * 每個月的兔子總數是多少？
 * @author user
 *
 */


public class Q1 {
	public static void main(String[] args) {
		java.util.Scanner keyin = new java.util.Scanner(System.in);
		System.out.println("請輸入您想要幾個月的兔子隻數？");
		int number = keyin.nextInt();
		System.out.println("在"+number+"月時，將會有"+rabbit(number)+"隻兔子");
		
	}
	public static int rabbit(int num) {
		if(num<3) {
			return 2;
		}
		else {
			return rabbit(num-1) + rabbit(num-2);
		}
	}
}
