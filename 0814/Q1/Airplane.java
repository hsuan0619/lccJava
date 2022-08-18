package Q1;
/**
 * 飛機 波音787
 * 載客量
 * 時速
 * 顯示資訊
 * 
 * 目的地 11
 * 是否轉機
 * 瑞士 祕魯 加拿大 冰島 4
 * 
 * @author user
 *
 */
public class Airplane implements Vehicle{
	private boolean change=false;
	public String place;
	public String[] country={"埃及","俄羅斯","冰島","瑞士","西班牙","日本",
			"摩洛哥","奧地利","義大利","加拿大","祕魯"};
	
	public Airplane() {
		start();
	}
	//初始設定
	private void start() {
		java.util.Scanner keyin = new java.util.Scanner(System.in);
		boolean useA=true;
		while(useA) {
			System.out.println("您好，歡迎搭乘交通世界小飛機，請問您想要去到哪個國家呢？"
					+ "(如果需要提供飛行國家請輸入「飛行地圖」)");
			String user=keyin.next();
			if(user.equals("飛行地圖")) {
				System.out.println("以下為我們提供的飛行國家：");
				showCountry();
				System.out.println("\n-------------------------");
			}else {
				useA=checkCountry(user);
				if(useA==false) {
					this.place=user;
				}else {
					System.out.println("不好意思，我們沒有飛往此國家的飛機，請您再次確認！");
				}
			}
		}
		setChange(this.place);
		while(true) {
			System.out.println("飛機提供的服務：(1.檢視您的航班訊息 2.查詢本公司提供之飛行地圖 "
					+ "，如無須服務請輸入其他任意整數，謝謝您~)");
			int useS=keyin.nextInt();
			if(useS==1) {
				showData();
				System.out.println("------------------");
			}else if(useS==2) {
				showCountry();
				System.out.println("\n------------------");
			}else {
				break;
			}
		}
	}
	//確認是否有該國家
	private boolean checkCountry(String user) {
		for(int i=0;i<11;i++) {
			if(user.equals(country[i])) {
				return false; //有該國家
			}
		}
		return true;
	}
	
	//顯示飛行國家
	public void showCountry() {
		for(int i=0;i<11;i++) {
			System.out.print(country[i]+" ");
		}
	}
	
	//是否轉機
	public void setChange(String country) {
		if(country.equals("瑞士")||country.equals("祕魯")
				||country.equals("加拿大")||country.equals("冰島")) {
			this.change=true;
		}else {
			this.change=false;
		}
	}
	
	public int people() {
		return 400;
	}
	public int speed() {
		return 900;
	}
	public void showData() {
		System.out.println("您的班機為飛往"+this.place+"的航班");
		System.out.println("飛機是否需要轉機："+this.change);
		System.out.println("飛機載客量為："+this.people()+"人");
		System.out.println("飛機時速為："+this.speed()+"公里/時");
	}
}
