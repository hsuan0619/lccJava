/**
 * 
 * 廠商
 * 1. 廠商名稱(可修改)    String name
 * 2. 廠商編號(不可修改，HashSet)由系統自己產生(100-999其中一個數字)   int id
 * 3. 廠商密碼(▲不確定是否增加)    String pass
 * 4. 活動公告(可以有很多條)    ArrayList<String> announce 
 * 5. 出貨地點(可修改) ->> String place
 * 		final 縣市：
 * 		final 國家：
 * 6. 商品功能
 * @author user
 *
 */
import java.util.*;
import java.math.*;
public class Boss {
	Scanner keyin = new Scanner(System.in);
	private String name,place,id;
	private ArrayList<String> announce = new ArrayList();
	public final static String country[]= {"臺灣","中國","日本","韓國","東南亞","歐洲","美洲","其他"};
	public final static String city[]= {"臺北市","新北市","桃園市","臺中市","臺南市","高雄市","新竹縣",
								 "新竹市","苗栗縣","彰化縣","南投縣","雲林縣","嘉義縣","嘉義市",
								 "屏東縣","宜蘭縣","花蓮縣","臺東縣","澎湖縣","金門縣","連江縣"
								 ,"基隆市"};
	private TreeMap mapGB=new TreeMap(); //僅存取該廠商的商品
	private TreeSet<String> setGB=new TreeSet(); //僅存取該廠商商品的id
	
	//建構子
	public Boss() {
		setId();
		this.place="尚未設定";
		start();
	}
	
	//初始設定廠商名、出貨地點
	private void start() {
		//廠商名稱設定
		System.out.println("歡迎加入 Go Go購物 的 廠商行列，以下有幾項初始設定要完成，煩請您配合~~!");
		setName();
		setPlace();
		while(true) {
			System.out.println("感謝您的配合~\n以下為您輸入的資料，煩請您確認："
							   +"\n廠商編號："+this.id+"(此為系統自動生成，須憑此編號才可登入您的廠商系統，故須請您記下此編號)"
							   +"\n廠商名稱："+this.name
							   +"\n廠商出貨地點："+this.place
							   +"\n若您的資料有誤，麻煩您輸入：1. 廠商名稱設定 2. 出貨地點設定"
							   + "\n如若資料無誤則請您輸入0");
			int user=5;
			try{
				user=keyin.nextInt();
			}catch(Exception e) {
				String er=keyin.next();
			}
			if(user==1) {
				setName();
			}else if(user==2) {
				setPlace();
			}else if(user==0) {
				break;
			}else {
				System.out.println("請您輸入0-2之間的整數");
			}
		}
		run();
	}
	

	public void run() {
		while(true) {
			int user=0;
			System.out.println("您好，我們提供給廠商的服務有以下："
					+ "\n1. 設定廠商名稱"
					+ "\n2. 設定廠商出貨地點"
					+ "\n3. 新增/修改活動公告"
					+ "\n4. 查看/新增/修改商品"
					+ "\n5. 查看您的資料"
					+ "\n輸入任意整數即可登出~"
					+ "\n請您輸入想要使用的服務項目：");
			try {
				user=keyin.nextInt();
				if(user==1) {
					setName();
				}else if(user==2) {
					setPlace();
				}else if(user==3) {
					callAnnounce();
				}else if(user==4) {
					goods();
				}else if(user==5) {
					showData();
				}else {
					break;
				}
			}catch(Exception e) {
				System.out.println("請您輸入整數，謝謝~");
				String er=keyin.next();
			}
		}
		System.out.println("感謝您使用廠商系統♥♥");
	}
	
	//設定
	//設定廠商名稱
	private void setName() {
		System.out.println("請輸入您的廠商名稱(後續仍可做更動)：");
		String userName=keyin.next();
		this.name=userName;
	}
	//設定出貨地點
	private void setPlace() {
		//廠商出貨地點設定
		//國家設定
		boolean use=true;
		while(use) {
			System.out.println("請選擇您的出貨國家：");
			showCountry();
			try {
				int userCountry=keyin.nextInt();
				if(userCountry>=1&&userCountry<=8) {
					this.place=country[userCountry-1];
					use=false;
				}else {
					System.out.println("請輸入1-8之間的整數");
				}
			}catch(Exception e) {
				System.out.println("請輸入整數的國家代碼");
				String er=keyin.next();
			}
		}
		//縣市設定
		while(this.place.equals("臺灣")) {
			System.out.println("請選擇您的出貨縣市：");
			showCity();
			try {
				int userCity=keyin.nextInt();
				if(userCity>=1&&userCity<=22) {
					this.place=city[userCity-1];
				}else {
					System.out.println("請輸入1-22之間的整數");
				}
			}catch(Exception e) {
				System.out.println("請輸入整數的縣市代碼");
				String er=keyin.next();
			}
		}
	}
	//設定廠商編號
	private void setId() {
		while(true) {
			int id=(int)(Math.random()*900)+100;
			if(Manager.setB.contains(id)) {
				continue;
			}else {
				this.id=id+"";
				Manager.setB.add(this.id);
				break;
			}
		}
	}
	
	//商品 新增/修改/查看
	private void goods() {
		System.out.println("此處提供三種服務： 1. 新增商品 2.修改商品 3.查看商品"
							+ "\n如要退出請輸入其他任意數字"
							+ "\n請輸入您要使用的服務：");
		int user=0;
		try {
			user=keyin.nextInt();
			if(user==1) {
				addGoods();
				goods();
			}else if(user==2) {
				changeGoods();
				goods();
			}else if(user==3) {
				showGoods();
				goods();
			}else {
				System.out.println("退出商品服務~");
			}
		}catch(Exception e) {
			keyin.next();
			System.out.println("由於您輸入的值非整數，故系統視為退出~");
		}
	}
	//新增商品
	private void addGoods() {
		Goods good=new Goods(this.id);
		setGB.add(good.getId());
		mapGB.put(good.getId(), good);
		Manager.mapGAll.put(good.getId(), good);
	}
	//修改商品
	private void changeGoods() {
		System.out.println("請您輸入您要更改的商品之編號：");
		String user=keyin.next();
		if(mapGB.containsKey(user)) {
			Goods good=(Goods) mapGB.get(user);
			good.run();
		}else {
			System.out.println("無此商品編號！！！");
		}
	}
	//查看商品
	private void showGoods() {
		System.out.println("以下為您的所有商品↓↓↓");
		int i=1;
		for(String gId:setGB) {
			Goods good=(Goods)mapGB.get(gId);
			System.out.println(i+". "+good.getName()+" (商品編號："+good.getId()+")");
			i++;
		}
		if(setGB.isEmpty()) {
			System.out.println("您目前沒有商品喔！");
		}
		System.out.println("------------------------------------------");
	}
	
	public ArrayList<String> showGoodsC() {
		ArrayList<String> goodsArr=new ArrayList();
		System.out.println("以下為 "+name+" 的所有商品↓↓↓");
		int i=1;
		for(String gId:setGB) {
			Goods good=(Goods)mapGB.get(gId);
			System.out.println(i+". "+good.getName()+" (商品編號："+good.getId()+")");
			i++;
			goodsArr.add(gId);
		}
		if(setGB.isEmpty()) {
			System.out.println("此處目前沒有商品喔！");
		}
		System.out.println("------------------------------------------");
		return goodsArr;
	}
	
	
	//新增/修改活動公告
	private void callAnnounce() {
		System.out.println("請問您要 1. 新增活動公告 2. 修改活動公告 3. 退出");
		int user=0;
		try {
			user=keyin.nextInt();
		}catch(Exception e) {
			String er=keyin.next();
		}
		if(user==1) {
			addAnnounce();
		}else if(user==2) {
			changeAnnounce();
		}else if(user==3) {
			
		}else {
			System.out.println("請您輸入1-3之間的整數喔！");
			callAnnounce();
		}
	}
	//修改活動公告
	private void changeAnnounce() {
		System.out.println("您目前所有的活動公告有：");
		showAnnounce();
		System.out.println("請輸入您要修改的活動內容是第幾項：");
		int user=50000;
		try {
			user=keyin.nextInt();
			if(user>this.announce.size()+1) {
				System.out.println("不好意思，您沒有那麼多的公告喔！\n如要退出請輸入「退出」~");
				changeAnnounce();
			}else if(user<1){
				System.out.println("不好意思，請輸入您有的公告數喔！\\n如要退出請輸入「退出」~");
				changeAnnounce();
			}else {
				System.out.println("請輸入此項活動公告修改的完整內容：");
				String use=keyin.next();
				this.announce.set(user-1, use);
				System.out.println("好的，已收到您的修改！\n您的活動公告為：");
				showAnnounce();
			}
		}catch(Exception e) {
			String er=keyin.next();
			if(er.equals("退出")) {
				callAnnounce();
			}else {
				System.out.println("請輸入整數喔！");
				changeAnnounce();
			}
		}
	}
	//新增活動公告	
	private void addAnnounce() {
		System.out.println("請輸入您要新增的活動內容：");
		String user=keyin.next();
		this.announce.add(user);
		System.out.println("新增完成！\n您目前的所有活動公告：");
		showAnnounce();
	}
	
	//show
	private void showData() {
		System.out.println("------------------------------------------------------------------"
							+"\n您的廠商資料↓↓↓"
							+"\n廠商編號："+this.id+"(此為系統自動生成，須憑此編號才可登入您的廠商系統，故須請您記下此編號)"
							+"\n廠商名稱："+this.name
							+"\n廠商出貨地點："+this.place
							+"\n廠商活動公告：");
		showAnnounce();
		System.out.println("------------------------------------------------------------------");
	}
	//顯示活動公告
	private void showAnnounce() {
		for(int i=0;i<this.announce.size();i++) {
			System.out.println((i+1)+". "+this.announce.get(i));
		}
		if(this.announce.size()==0) {
			System.out.println("目前暫無活動喔！");
		}
		System.out.println("------------------------------------------------------------------");
	}
	//for customer's data
	public void showDataC() {
		System.out.println("------------------------------------------------------------------"
				+this.name
				+" 廠商"
				+"\n出貨地點："+this.place
				+"\n活動公告：");
		showAnnounce();
	}
	
	//顯示國家、縣市
	//顯示出貨國家
	public static void showCountry() {
		for(int i=0;i<8;i++) {
			System.out.print((i+1)+"."+country[i]+" ");
		}
		System.out.println();
	}
	//顯示出貨城市
	public static void showCity() {
		for(int i=0;i<22;i++) {
			if(i==9||i==19) {
				System.out.println((i+1)+"."+city[i]+"\t");
			}else {
				System.out.print((i+1)+"."+city[i]+"\t");
			}
		}
		System.out.println();
	}

	//get
	public String getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	
}
