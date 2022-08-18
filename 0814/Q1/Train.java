package Q1;
/**
 * 火車
 * type：區間車、自強號
 * 是否驗票
 * 各站時間
 * 載客量
 * 時速
 * 顯示資訊
 * 
 * 各停留站點(只有新竹到台北)
 * 
 * 計算到兩地時間的方法
 * 
 * @author user
 *
 */
public class Train implements Vehicle{
	private String type;
	boolean check;
	private int useTime;
	//車站停靠位置
	//local 0~17 5min quick 0~5 10min
	public final String[] local= {"新竹","北新竹","竹北","新豐","湖口","富岡","楊梅","埔心",
			"中壢","內壢","桃園","鶯歌","山佳","樹林","浮洲","板橋","萬華","台北"};
	public final String[] quick= {"新竹","中壢","桃園","樹林","板橋","台北"};
	
	//建構子
	public Train() {
		start();
	}
	
	//初始
	private void start() {
		java.util.Scanner keyin = new java.util.Scanner(System.in);
		int user;
		String type;
		//type分為 區間車 自強號
		while(true) {
			System.out.println("請輸入您要的車種：(請輸入 1.區間車 2.自強號)");
			user=keyin.nextInt();
			if(user==1) {
				type="區間車";
				break;
			}else if(user==2) {
				type="自強號";
				break;
			}else {
				System.out.println("請輸入1或是2");
			}
		}
		this.type = type;
		setCheck();
		setUseTime();
		boolean useZ=true;
		while(useZ) {
			int use=0;
			System.out.println("火車提供的服務：(1.檢視該車種火車訊息 2.查詢該車種停靠的站點 "
					+ "3.查詢您想要的火車起始站至目的地所需的時間，如無須服務請輸入其他任意整數，謝謝您~)");
			use=keyin.nextInt();
			switch(use) {
				case 1:
					showData();
					System.out.println("---------------------------------");
					break;
				case 2:
					showPlace();
					System.out.println("\n---------------------------------");
					break;
				case 3:
					boolean useA=true,useB=true;
					String start="",end="";
					while(useA || useB) {
						System.out.println("請輸入起始站(只需輸入地點，如：新竹)：");
						start=keyin.next();
						if(this.type.equals("區間車")) {
							useA=localCheck(start);
							if(useA) {
								System.out.println("您的車種並無此站，請您重新輸入！");
								continue;
							}
						}else {
							useA=quickCheck(start);
							if(useA) {
								System.out.println("您的車種並無此站，請您重新輸入！");
								continue;
							}
						}
						System.out.println("請輸入目的站(只需輸入地點，如：新竹)：");
						end=keyin.next();
						if(this.type.equals("區間車")) {
							useB=localCheck(end);
							if(useB) {
								System.out.println("您的車種並無此站，請您重新輸入！");
								continue;
							}
						}else {
							useB=quickCheck(start);
							if(useB) {
								System.out.println("您的車種並無此站，請您重新輸入！");
								continue;
							}
						}
					}
					System.out.println("自"+start+"站至"+end+"站所需使用的時間為："+time(start,end)+"分鐘");
					break;
				default:
					System.out.println("感謝您的使用，下次再會~");
					useZ=false;
					break;
			}
		
		}
		
		
	}
	//確認區間車是否有此站
	private boolean localCheck(String place) {
		for(int i=0;i<18;i++) {
			if(local[i].equals(place)) {
				return false; //這個位置是存在的
			}
		}
		return true;
	}
	private boolean quickCheck(String place){
		for(int i=0;i<6;i++) {
			if(quick[i].equals(place)) {
				return false;
			}
		}
		return true;
	}
	//showPlace
	public void showPlace() {
		if(this.type.equals("區間車")) {
			System.out.println("區間車所停各站為：");
			for(int i=0;i<18;i++) {
				System.out.print(local[i]+" ");
			}
		}else {
			System.out.println("自強號所停各站為：");
			for(int i=0;i<6;i++) {
				System.out.print(quick[i]+" ");
			}
		}
		
	}
	
	//時間
	public int time(String a,String b) {
		int aNum=0,bNum=0,use,ans=0;
		if(this.type.equals("區間車")) {
			use=5;
			for(int i=0;i<18;i++) {
				if(a.equals(local[i])) {
					aNum=i;
				}
				if(b.equals(local[i])) {
					bNum=i;
				}
			}
		}else {
			use=10;
			for(int i=0;i<6;i++) {
				if(a.equals(quick[i])) {
					aNum=i;
				}
				if(b.equals(quick[i])) {
					bNum=i;
				}
			}
		}
		
		if(aNum>bNum) {
			ans=(aNum-bNum)*use;
		}else {
			ans=(bNum-aNum)*use;
		}
		return ans;
	}
	
	//是否驗票
	private void setCheck() {
		if(this.type.equals("區間車")) {
			this.check=false;
		}else {
			this.check=true;
		}
	}
	
	//車種各站耗時
	private void setUseTime() {
		if(this.type.equals("區間車")) {
			this.useTime=5;
		}else{
			this.useTime=10;
		}
	}
	
	//載客量方法
	public int people() {
		if(this.type.equals("區間車")) {
			return 200;
		}else {
			return 150;
		}
	}
	
	//時速
	public int speed() {
		if(this.type.equals("區間車")) {
			return 100;
		}else {
			return 150;
		}
	}
	
	//顯示資訊
	public void showData() {
		System.out.println("火車類型為："+this.type);
		System.out.println("火車載客量為："+this.people()+"人");
		System.out.println("火車時速為："+this.speed()+"公里/時");
		System.out.println("火車是否驗票："+this.check);
		System.out.println("火車各站之間時間："+this.useTime+"分鐘");
	}
	
}
