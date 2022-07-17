/**
 * 求因數
 * 若整數A可以被整數B整除，則B是A的因數
 * 迴圈：for(int i=1;i<=100;i++)
 * 使用者輸入數字，找出1~100間是這個數字的因數
 * */
public class Q3_0717 {
	public static void main(String[] args) {
		java.util.Scanner keyin= new java.util.Scanner(System.in);
		int num;
		System.out.println("請輸入你想要找到他所有因數的數吧！");
		num=keyin.nextInt();
		System.out.println("以下是所有"+num+"的因數喔！");
		for(int i=1;i<=num;i++) {
			if(num%i==0) {
				System.out.println(i);
			}
		}
	}
}
