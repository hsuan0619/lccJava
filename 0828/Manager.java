/**
 * 利用集合做出商品管理
 * 1. 自行建立一個goods類別，裡面有商品名稱、價格、廠商
 * 2. 可以讓使用者自行建立商品名稱、價格、廠商後，放入集合
 * 3. 使用者可以快速搜尋到商品名稱，並顯示其價格
 * 4. 消費者可以依照目前所存在的商品放入購物車中，當消費者選結帳時
 * 	  系統要出現各商品名稱、價格，並顯示結帳總額
 * 
 * @author user
 *
 */
import java.util.*;

public class Manager {
	static Scanner keyin = new Scanner(System.in);
	public static TreeMap mapB=new TreeMap(); //存取廠商(key：廠商編號，value：boss object)
	public static TreeMap mapC=new TreeMap(); //存取會員資料(key：廠商編號，value：customer object)
	public static TreeMap mapGAll=new TreeMap(); //存取所有商品資料
	
	public static TreeSet<String> setB=new TreeSet(); //存取廠商的編號
	public static TreeSet setC=new TreeSet(); //存取會員的編號
	public static TreeSet<String> setG=new TreeSet(); //存取商品的編號
	
	public static HashSet<String> setGType = new HashSet(); //存取所有商品類別資料
	
	//將預設的商品類別新增
	public static void setGTypeSet() {
		setGType.add("衣著");
		setGType.add("美妝");
		setGType.add("食品");
		setGType.add("電子產品");
		setGType.add("書籍");
	}
	//印出所有商品類別
	public static void showGTypeSet() {
		System.out.println("商品類別有：");
		int i=1;
		for(String a: setGType) {
			System.out.println(i+"."+a);
			i++;
		}
		System.out.println("---------------------------------------------------");
	}
	
	//列印出所有廠商
	public static void showBoss() {
		System.out.println("加入本公司的廠商有：");
		int i=1;
		for(String b: setB) {
			Boss boss=(Boss)mapB.get(b);
			System.out.println(i+". "+boss.getName());
		}
		System.out.println("---------------------------------------------------");
	}
	
	//印出所有type相同的商品
	public static ArrayList<String> showGTypeGoods(String type) {
		ArrayList<String> goodsArr=new ArrayList();
		System.out.println(type+"類別的商品有：");
		int i=1;
		for(String a: setG) {
			Goods goods=(Goods)mapGAll.get(a);
			if(goods.getType().equals(type)) {
				System.out.println(i+"."+goods.getName());
				i++;
				goodsArr.add(goods.getId());
			}
		}
		System.out.println("-------------------------------------------------");
		return goodsArr;
	}
	
	
	//主程式
	public static void main(String[] args) {
		setGTypeSet();
		run();
	}
	
	private static void run() {
		System.out.println("--------------------------------------------------");
		System.out.println("歡迎使用Go Go購物系統~"
							+ "\n本公司提供您 1. 廠商服務 2. 會員(消費者)服務"
							+ "\n請輸入您需要的服務項目：");
		int user=0;
		try {
			user=keyin.nextInt();
		}catch(Exception e) {
			keyin.next();
		}
		if(user==1) {
			bossService();
		}else if(user==2) {
			customerService();
		}else {
			System.out.println("請輸入1或是2的整數喔~♥");
			System.out.println("如果需要完整退出我們的程式請現在輸入【退出】喔！\n輸入其他內容將繼續進行程式~");
			String use=keyin.next();
			if(use.equals("退出")) {
				System.out.println("感謝您的使用，已退出本系統~♥");
			}else {
				run();
			}
		}
		
	}
	//廠商服務
	private static void bossService() {
		System.out.println("廠商服務分為： 1. 新廠商加入 2. 舊廠商操作"
				+ "\n請輸入您需要的服務項目："
				+ "\n(輸入任意整數將會退出廠商服務)");
		int user=0;
		try {
			user=keyin.nextInt();
		}catch(Exception e) {
			keyin.next();
		}
		if(user==1) {
			newBoss();
		}else if(user==2) {
			oldBoss();
		}else {
			run();
		}
	}
	//新加入廠商
	private static void newBoss() {
		Boss boss1=new Boss();
		mapB.put(boss1.getId(),boss1);
		run();
	}
	//就廠商操作
	private static void oldBoss() {
		System.out.println("請輸入您的廠商編號：");
		String user=keyin.next();
		if(setB.contains(user)) {
			Boss b =(Boss)mapB.get(user);
			b.run();
			run();
		}else {
			System.out.println("您輸入的廠商編號不存在！");
			bossService();
		}
	}

	private static void customerService() {
		System.out.println("會員服務分為： 1. 新會員加入 2. 舊會員操作"
				+ "\n請輸入您需要的服務項目："
				+ "\n(輸入任意整數將會退出會員服務)");
		int user=0;
		try {
			user=keyin.nextInt();
		}catch(Exception e) {
			keyin.next();
		}
		if(user==1) {
			newCustomer();
		}else if(user==2) {
			oldCustomer();
		}else {
			run();
		}
	}
	
	private static void newCustomer() {
		Customer c=new Customer();
		mapC.put(c.getId(),c);
		run();
	}
	
	private static void oldCustomer() {
		System.out.println("請輸入您的會員編號：");
		int user=0;
		try {
			user=keyin.nextInt();
		}catch(Exception e) {
			keyin.next();
		}
		if(setC.contains(user)) {
			Customer c =(Customer)mapC.get(user);
			c.run();
			run();
		}else {
			System.out.println("您輸入的會員編號不存在！");
			customerService();
		}
	}
}
