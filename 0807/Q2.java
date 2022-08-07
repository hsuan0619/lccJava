/**
 * 有一分數序列
 * 2/1,3/2,5/3,8/5,13/8,21/13...
 * 求前20項之和
 * @author user
 *
 */
import java.util.Scanner;
public class Q2 {
	public static void main(String[] args) {
		Scanner keyin = new Scanner(System.in);
		System.out.println("請問您想要看到底幾位的數字加總？");
		int user = keyin.nextInt();
		double ans=0;
		for(int i=1;i<=user;i++) {
			ans+=method(i);
		}
		System.out.println("答案為："+ans);
	}
	public static double method(double num) {
		if(num==0) {
			return 1;
		}else{
			return (method(num-1)+1)/(method(num-1));
		}
	}
}
