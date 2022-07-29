
public class hw2 {
	public static void main(String args[]) {
		int[] num=new int[10];
		System.out.println("原本的陣列是：");
		for(int i=0;i<10;i++) {
			num[i]= (int)(Math.random()*100);
			System.out.print(num[i]+" ");
		}
		System.out.println();
		int use=0;
		for(int i=0;i<9;i++) {
			for(int j=i+1;j<10;j++) {
				if(num[j]<num[i]) {
					use=num[i];
					num[i]=num[j];
					num[j]=use;
				}
			}
		}
		System.out.println("排序後的陣列(升冪)：");
		for(int a:num) {
			System.out.print(a+" ");
		}
		System.out.println("\n排序後的陣列(降冪)：");
		for(int i=num.length-1;i>=0;i--) {
			System.out.print(num[i]+" ");
		}
	}
}
