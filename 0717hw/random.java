
public class random {
	public static void main(String[] args) {
		java.util.Scanner keyin = new java.util.Scanner(System.in);
		int num,userNum,min,max;
		
		num = (int)(Math.random() * 100 + 1);
		min=1;
		max=100;
		while(true) {
			System.out.println("猜猜這個數字吧！(數值為"+min+"~"+max+"之間的一個整數)");
			userNum= keyin.nextInt();
			if(userNum==num) {
				System.out.println("你真聰明，答案就是"+num+"沒錯！！");
				break;
			}else if(userNum>num && userNum<max) {
				max=userNum;
				System.out.println("不對喔！這個數字是在"+min+"~"+max+"之間");
			}else if(userNum<num && userNum>min) {
				min=userNum;
				System.out.println("不對喔！這個數字是在"+min+"~"+max+"之間");
			}else {
				System.out.println("你猜的數字不是在數值中間喔");
			}
		}
		
	}
}
