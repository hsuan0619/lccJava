/**
 * 商品
 * 1. 商品名稱  String name
 * 2. 商品編號(不可修改，ArrayList)由系統自己產生(廠商編號+索引值)  String id
 * 3. 商品庫存 int num
 * 4. 商品價格 int price
 * 5. 商品顏色/尺吋  HashSet<String> size
 * 6. 商品類別(HashSet預設有：衣著、美妝、食品、電子產品、書籍) String type
 */

import java.util.*;
import java.math.*;

public class Goods {
	Scanner keyin= new Scanner(System.in);
	private String name,id;
	private int num,price;
	private String type;
	private HashSet<String> size=new HashSet();
	
	//建構子
	public Goods(String bossId) {
		setId(bossId);
		start();
	}
	
	//初始設定
	private void start() {
		setName();
		setType();
		setSize();
		setPrice();
		setNum();
		System.out.println("恭喜您完成商品設定!\n");
		showData();
		run();
	}
	
	
	public void run() {
		System.out.println("商品提供的服務有："
							+ "\n1. 設定商品名稱"
							+ "\n2. 設定商品價格"
							+ "\n3. 設定商品顏色/尺寸"
							+ "\n4. 設定商品類別"
							+ "\n5. 設定商品庫存"
							+ "\n6. 查看商品資料"
							+ "\n請輸入您要使用的服務項目，如要退出請輸入任意整數");
		try {
			int user=keyin.nextInt();
			if(user==1) {
				setName();
				run();
			}else if(user==2) {
				setPrice();
				run();
			}else if(user==3) {
				setSize();
				run();
			}else if(user==4) {
				setType();
				run();
			}else if(user==5) {
				setNum();
				run();
			}else if(user==6) {
				showData();
				run();
			}else {
				System.out.println("退出商品系統~~!");
			}
		}catch(Exception e) {
			System.out.println("由於您輸入的並非整數，因此視為您要選擇退出商品系統，謝謝您的使用");
			keyin.next();
		}
	}
	
	
	//設定商品編號
	private void setId(String bossId) {
		for(int i=1;true;i++) {
			String id=""+bossId+i;
			if(Manager.setG.contains(id)) {
				setId(bossId);
			}else {
				this.id=id;
				Manager.setG.add(id);
			}
		}
	}
	//設定商品名稱
	private void setName() {
		System.out.println("請輸入您的商品名稱：");
		String name=keyin.next();
		this.name=name;
		System.out.println("商品名稱設定完成！");
	}
	//設定商品類別
	private void setType() {
		System.out.print("請設定您的商品類別：\n目前的");
		Manager.showGTypeSet();
		System.out.println("如若您的商品類別為其中一項，請輸入選項之完整內容(例如：衣著類別，請輸入「衣著」)\n"
							+ "如若商品類別中無此商品之類別，則請您輸入此類別的完整內容：");
		String type=keyin.next();
		if(Manager.setGType.contains(type)) {
			while(true) {
				int user=0;
				System.out.println("您輸入的是原先就有的商品類別："+type+"\n請問是否正確？"
									+ "\n正確請輸入1，錯誤請輸入0");
				try {
					user=keyin.nextInt();
				}catch(Exception e) {
					String er=keyin.next();
				}
				if(user==1) {
					this.type=type;
					System.out.println("商品類別設定完成！");
					break;
				}else if(user==0) {
					System.out.println("好的，接下來再麻煩您重新設定一次商品類別~");
					setType();
					break;
				}else {
					System.out.println("請輸入0或是1");
				}
			}
		}else {
			while(true) {
				int user=0;
				System.out.println("您輸入的是新的商品類別："+type+"\n請問是否正確？"
									+ "\n正確請輸入1，錯誤請輸入0");
				try {
					user=keyin.nextInt();
				}catch(Exception e) {
					String er=keyin.next();
				}
				if(user==1) {
					Manager.setGType.add(type);
					this.type=type;
					System.out.println("好的，我們將會新增此類別\n商品類別設定完成！");
					break;
				}else if(user==0) {
					System.out.println("好的，接下來再麻煩您重新設定一次商品類別~");
					setType();
					break;
				}else {
					System.out.println("請輸入0或是1");
				}
			}
		}
		
	}
	//設定商品顏色/尺寸
	private void setSize() {
		System.out.println("請輸入您提供的顏色/尺寸(每個顏色/尺寸輸入請換行，如完成所有顏色則輸入：「完成」)：");
		while(true) {
			String user=keyin.next();
			if(user.equals("完成")) {
				break;
			}else {
				size.add(user);
			}
		}
		showSize();
		while(true) {
			int user=0;
			System.out.println("請問是否正確？"
								+ "\n正確請輸入1，錯誤請輸入0");
			try {
				user=keyin.nextInt();
			}catch(Exception e) {
				String er=keyin.next();
			}
			if(user==1) {
				System.out.println("商品顏色/尺寸設定完成！");
				break;
			}else if(user==0) {
				System.out.println("好的，接下來再麻煩您重新設定一次商品顏色/尺寸~");
				size.clear();
				setSize();
			}else {
				System.out.println("請輸入0或是1");
			}
		}
	}
	//設定價格
	private void setPrice() {
		System.out.println("請輸入商品價格：");
		int price=0;
		try {
			price=keyin.nextInt();
			while(true) {
				int user=0;
				System.out.println("您的商品價格為："+price+"\n請問是否正確？"
									+ "\n正確請輸入1，錯誤請輸入0");
				try {
					user=keyin.nextInt();
				}catch(Exception e) {
					String er=keyin.next();
				}
				if(user==1) {
					System.out.println("商品價格設定完成！");
					this.price=price;
					break;
				}else if(user==0) {
					System.out.println("好的，接下來再麻煩您重新設定一次商品價格~");
					size.clear();
					setPrice();
				}else {
					System.out.println("請輸入0或是1");
				}
			}
		}catch(Exception e) {
			String er=keyin.next();
			System.out.println("請輸入整數");
			setPrice();
		}
	}
	//設定庫存
	private void setNum() {
		System.out.println("請輸入您的庫存量");
		int num=0;
		try {
			num=keyin.nextInt();
		}catch(Exception e) {
			String er=keyin.next();
			num=-1;
		}
		if(num<0) {
			System.out.println("請輸入大於0的正整數，謝謝");
			setNum();
		}else {
			this.num=num;
			System.out.println("商品庫存量設定完成！");
		}
	}
	
	//顯示商品資訊
	private void showData() {
		System.out.println("----------------------------------------------------------");
		System.out.println("以下為您的商品資訊："
							+ "\n商品編號："+this.id+"商品編輯時需要使用此商品編號，才可進行編輯\n"
							+ "此編號為系統自動生成~(編號=廠商編號+商品建立的流水號)"
							+ "\n商品名稱："+this.name
							+ "\n商品類型："+this.type
							+ "\n商品價格："+this.price
							+ "\n商品庫存："+this.num);
		showSize();
		System.out.println("----------------------------------------------------------");
	}
	//顯示顏色/尺寸
	private void showSize() {
		System.out.print("商品顏色/尺寸：");
		for(String a:size) {
			System.out.print(a+" ");
		}
		System.out.println("");
	}
	
	//取得商品編號
	public String getId() {
		return this.id;
	}
	//取得商品名稱
	public String getName() {
		return this.name;
	}
	//取得商品類別
	public String getType() {
		return this.type;
	}
	//取得商品價格
	public int getPrice() {
		return this.price;
	}
	
	//查看商品資訊 (for customer)
	public void showDataC() {
		System.out.println("----------------------------------------------------------");
		System.out.println(this.name+"商品資訊："
							+ "\n商品類型："+this.type
							+ "\n商品價格："+this.price
							+ "\n商品庫存："+this.num);
		showSize();
		System.out.println("----------------------------------------------------------");
	}
}
