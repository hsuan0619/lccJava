
public class ab_guess {
	public static void main(String[] args) {
		java.util.Scanner keyin = new java.util.Scanner(System.in);
		int max=6;
		int ans[] = new int[max];
		for(int i = 0;i < max;i++) {
			ans[i]=(int)(Math.random() * 10);
		}
		int userAns[] = new int[max];
		int userInput;
		int time=0;
		System.out.println("猜猜看這個"+max+"數字的字串是甚麼樣的吧！");
		while(true) {
			time++;
			int a=0,b=0;
			userInput=keyin.nextInt();
			int use=userInput;
			boolean checkA[]=new boolean[max];
			boolean checkU[]=new boolean[max];
			for(int i=0;i < max;i++) {
				checkA[i]=false;
				checkU[i]=false;
				userAns[i]=use%10;
				use/=10;
			}
			//a的數共有幾個地判定
			for(int i=0;i<max;i++) {
				if(userAns[i]==ans[i]) {
					checkU[i]=true;
					checkA[i]=true;
					a++;
				}
			}
			//遊戲結束判定 全部都是a的情況
			if(a==max) {
				System.out.print("恭喜你達成"+max+"a的成就！完全答對了~，這個數字就是");
				for(int i=max-1;i>=0;i--) {
					System.out.print(userAns[i]);
				}
				System.out.println();
				break;
			}
			//b的數共有幾個地判定
			for(int i=0;i<max;i++) {
				if(checkU[i]==false) {
					for(int j=0;j<max;j++) {
						if(checkA[j]==false) {
							if(userAns[i]==ans[j]) {
								checkA[j]=true;
								checkU[i]=true;
								b++;
								break;
							}
						}else {
							continue;
						}
					}
				}else {
					continue;
				}
			}
			//遊戲的結果展示
			System.out.print("第"+time+"次的猜答：\t");
			for(int i=max-1;i>=0;i--) {
				System.out.print(userAns[i]);
			}
			System.out.println(" -- 為"+a+"a"+b+"b");
		}
		
	}
	
}