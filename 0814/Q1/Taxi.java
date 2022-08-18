package Q1;
/**
 * 計程車
 * type：舒適轎車、普通轎車
 * 載客量
 * 時速
 * 顯示資訊
 * 
 * 計費
 * 
 * 
 * @author user
 *
 */
public class Taxi implements Vehicle{
	private String type;
	public Taxi() {
		start();
	}
	//初始
	private void start() {
		java.util.Scanner keyin = new java.util.Scanner(System.in);
		while(true) {
			System.out.println("請您輸入您想要的計程車類型：(1.普通轎車 2.高級舒適轎車)");
			int use=keyin.nextInt();
			if(use==1) {
				this.type="普通轎車";
				break;
			}else if(use==2) {
				this.type="高級舒適轎車";
				break;
			}else {
				System.out.println("不好意思，請您輸入1或是2");
			}
		}
		while(true) {
			System.out.println("本公司的計程車提供以下服務：(1.檢視您的計程車訊息 2.查詢您的距離收費"
					+ "，如無須服務請輸入其他任意整數，謝謝您~)");
			int use=keyin.nextInt();
			if(use==1) {
				showData();
				System.out.println("-----------------------");
			}else if(use==2) {
				System.out.println("請輸入您的行駛距離：(單位：公尺)");
				int user=keyin.nextInt();
				System.out.println("您此次搭乘之收費為："+this.money(user)+"元，謝謝您的惠顧~");
				System.out.println("------------------------");
			}else {
				break;
			}
		}
		
	}
	
	public int money(int distance) {
		int ans=0;
		if(this.type.equals("普通轎車")) {
			ans=(int)(distance*0.5)+100;
		}else {
			ans=(int)(distance*0.7)+200;
		}
		return ans;
	}
	
	public int people() {
		if(this.type.equals("普通轎車")) {
			return 4;
		}else
			return 2;
	}
	public int speed() {
		return 80;
	}
	public void showData() {
		System.out.println("計程車類型為："+this.type);
		System.out.println("計程車載客量為："+this.people()+"人");
		System.out.println("計程車時速為："+this.speed()+"公里/時");
	}
}
