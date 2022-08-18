package Q1;
/**
 * 請用介面 定義交通工具(載客功能)
 * 定義類別 火車 飛機 計程車 繼承交通工具
 * 需添加個別其他功能
 * 寫主程式顯示三個交通工具的特色
 * 
 * @author user
 *
 */

public class Q1 {
	public static void main(String[] args) {
		java.util.Scanner keyin = new java.util.Scanner(System.in);
		while(true) {
			System.out.println("歡迎來到交通世界，我們這裡提供三種不同的交通工具，分別為：1.火車 2.飛機 3.計程車，"
					+ "請輸入您想搭乘的交通工具(輸入數字1或2或3)，謝謝~");
			int user=keyin.nextInt();
			if(user==1) {
				Train train=new Train();
				break;
			}else if(user==2) {
				Airplane airplane=new Airplane();
				System.out.println("感謝您的使用，歡迎再次光臨~");
				break;
			}else if(user==3) {
				Taxi taxi=new Taxi();
				System.out.println("感謝您的使用，歡迎再次光臨~");
				break;
			}else {
				System.out.println("不好意思，我們並不提供此種交通工具，請您重新選擇~");
			}
		}
	}
}
