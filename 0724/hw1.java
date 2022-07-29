
public class hw1 {
	public static void main(String[] args) {
		java.util.Scanner keyin = new java.util.Scanner(System.in);
		int num[] = new int[10];
		int ans=0;
		for(int i=0;i<10;i++) {
			num[i]= (int)(Math.random()*100);
		}
		int user;
		while(true) {
			System.out.println("請輸入一個0~100之間的值，看看你有沒有跟電腦心有靈犀！");
			user=keyin.nextInt();
			if(user>=0&&user<=100) {
				break;
			}
			System.out.println("你在騙我嗎？？這才不是0~100之間的數字！！");
		}
		System.out.println("這個陣列是：");
		for(int a:num) {
			if(a==user) {
				ans++;
			}
			System.out.print(a+" ");
		}
		if(ans>0) {
			System.out.println("\n恭喜你跟電腦挺有默契的喔！\n居然有"+ans+"個數字一樣呢！");
		}else {
			System.out.println("\n看來你跟電腦沒啥默契喔QQ");
		}
	}
}
