
public class Q1_0710 {
	public static void main(String[] args) {
		java.util.Scanner keyin = new java.util.Scanner(System.in);
		int a=0;
		while(a==0) {
			System.out.println("請輸入一個正整數：");
			int num = keyin.nextInt();
			if(num<=0) {
				System.out.println("這不是一個正整數喔！");
				continue;
			}else if(num%2==0) {
				System.out.println("這是一個偶數OvO");
			}else {
				System.out.println("這是一個奇數OwO");
			}
			System.out.println("請問你還要繼續使用此程式嗎？(Y/N)");
			String use = keyin.next();
			if(use.equals("Y")||use.equals("y")) {
				System.out.println("好的，我們將繼續為您服務~");
			}else if(use.equals("N")||use.equals("n")) {
				System.out.println("謝謝您的使用~祝您有著美好的一天~♥");
				a++;
			}else {
				System.out.println("由於您輸入錯誤，所以就當作您不想繼續使用囉O3O\n祝您開開心心，掰掰囉！");
				a++;
			}
		}
	}
}
