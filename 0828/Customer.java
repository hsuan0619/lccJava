
/**
 * 消費者
 * 1. 會員帳號  int id
 * 2. 會員名稱  String name
 * 3. 購物車    ArrayList shopping
 * 4. 購買紀錄   <queue無法使用，故先不做此功能>
 
★★LinkedList Queue record★★無法使用！
 
 * 5. 結帳方法  (將購物車的所有金額加總)
 * 6. 買買買方法 (查看商品們，一個查看面板ㄉ概念)
 */


import java.math.*;
import java.util.*;

public class Customer {
	Scanner keyin=new Scanner(System.in);
	
	private int id;
	private String name;
	private ArrayList<String> shopping = new ArrayList();    //裡面放的是id！
	//private Queue record = new LinkedList();  error：LinkedList cannot be resolved to a type
	
	//建構子
	public Customer(){
		start();
		run();
	}
	//初始設定
	private void start() {
		System.out.println("歡迎您加入 Go Go購物 的 會員行列~"
							+ "\n以下是一些基本會員資料，再請您配合填寫~♥");
		setId();
		setName();
		System.out.println("恭喜您完成設定~♥");
		showData();
	}
	
	//run
	public void run() {
		System.out.println("本公司之會員系統提供以下功能："
							+ "\n1. 修改會員名稱"
							+ "\n2. 我要買買買(購買產品之介面)"
							+ "\n3. 查看購物車"
							+ "\n4. 刪除購物車商品"
							+ "\n5. 結帳"
							+ "\n6. 查看會員資料"
							+ "\n如需退出系統則輸入任意值");
		int user=0;
		try {
			user=keyin.nextInt();
		}catch(Exception e) {
			keyin.next();
		}
		if(user==1) {
			setName();
			run();
		}else if(user==2) {
			buy();
			run();
		}else if(user==3) {
			showCart();
			run();
		}else if(user==4) {
			removeCart();
			run();
		}else if(user==5) {
			money();
			run();
		}else if(user==6) {
			showData();
			run();
		}else {
			System.out.println("感謝您使用會員系統~♥\n會員已退出！！");
		}
	}
	
	//setId
	private void setId() {
		int a=(int)(Math.random()*9000)+1000;
		if(Manager.setC.contains(a)) {
			setId();
		}else {
			this.id=a;
			Manager.setC.add(a);
		}
	}
	
	//setName
	private void setName() {
		System.out.println("請輸入您的會員名稱：");
		String name=keyin.next();
		this.name=name;
		System.out.println("會員名稱設定完成！");
	}
	
	//顯示會員資料
	private void showData() {
		System.out.println("--------------------------------------------");
		System.out.println("以下為您的會員資料↓↓↓"
							+ "\n會員名稱："+this.name
							+ "\n會員編號："+this.id +" (此編號為系統自動生成，用以進行會員登入)");
		System.out.println("--------------------------------------------");
	}
	
	//我要買買買
	public void buy() {
		System.out.println("本公司提供以下幾種商品檢視方式："
							+ "\n1. 查看所有廠商"
							+ "\n2. 查看所有商品類型"
							+ "\n3. 搜尋特定商品名稱"
							+ "\n輸入任意值則可退出買買買介面"
							+ "\n請輸入您需要使用的商品檢視方式：");
		int user=0;
		try {
			user=keyin.nextInt();
		}catch(Exception e) {
			keyin.next();
		}
		if(user==1) {
			bossBuy();
		}else if(user==2) {
			typeBuy();
		}else if(user==3) {
			searchBuy();
		}else {
		}
	}
	//以廠商方式檢視
	private void bossBuy() {
		Manager.showBoss();
		boolean correct=false;
		System.out.println("請選擇您要查看哪個廠商的商品："
							+ "\n(請輸入完整的廠商名稱，輸入其他任意值則可退出)");
		String user=keyin.next();
		for(String a: Manager.setB) {
			Boss boss=(Boss)Manager.mapB.get(a);
			if(boss.getName().equals(user)) {
				ArrayList goodsArr=new ArrayList();
				goodsArr=boss.showGoodsC();
				showGoodsDetail(goodsArr);
				correct=true;
				break;
			}
		}
		if(!correct) {
			System.out.println("查無此廠商，已退出~");
		}
	}
	//以類別方式檢視
	private void typeBuy() {
		Manager.showGTypeSet();
		System.out.println("請選擇您要查看哪個類別的商品："
				+ "\n(請輸入完整的類別名稱，輸入其他任意值則可退出)");
		String user=keyin.next();
		boolean correct=false;
		for(String a: Manager.setGType) {
			if(a.equals(user)) {
				correct=true;
				ArrayList<String> goodsArr=new ArrayList();
				goodsArr=Manager.showGTypeGoods(user);
				showGoodsDetail(goodsArr);
			}
		}
		if(!correct) {
			System.out.println("查無此類別，已退出~");
		}
	}
	
	//查詢名稱
	private void searchBuy() {
		ArrayList<String> goodsArr=new ArrayList();
		System.out.println("請輸入您想要搜尋的商品名稱：");
		String user=keyin.next();
		boolean correct=false;
		int i=1;
		for(String id:Manager.setG) {
			Goods goods=(Goods)Manager.mapGAll.get(id);
			if(goods.getName().equals(user)) {
				System.out.println(i+"."+goods.getName());
				goodsArr.add(goods.getId());
				i++;
				correct=true;
			}
		}
		if(!correct) {
			System.out.println("沒有您搜尋的商品喔！");
		}else {
			showGoodsDetail(goodsArr);
		}
	}
	//顯示商品資訊
	//查看完整商品訊息
	private void showGoodsDetail(ArrayList<String> goodsArr) {
		System.out.println("請輸入您想要檢視完整商品內容的商品代號：\n(例如想看上方列表中的第一個選項，請輸入1)");
		int user=0;
		try {
			user=keyin.nextInt();
		}catch(Exception e) {
			keyin.next();
		}
		if(user>0&&user<goodsArr.size()+1) {
			String id=goodsArr.get(user-1);
			Goods goods=(Goods)Manager.mapGAll.get(id);
			goods.showDataC();
			addCart(id);
		}else {
			System.out.println("由於無此內容，故將退出商品內容");
		}
	}
	
	//將商品加入購物車
	//加入購物車
	private void addCart(String id) {
		System.out.println("請問您是否要將此商品加入購物車？(1.是  2.否)");
		int user=0;
		try {
			user=keyin.nextInt();
			if(user==1) {
				shopping.add(id);
				System.out.println("商品已成功加入購物車！");
			}else if(user==2) {
				System.out.println("好的，收到~");
			}else {
				System.out.println("麻煩請輸入1或是2");
				addCart(id);
			}
		}catch(Exception e) {
			keyin.next();
			System.out.println("麻煩請輸入1或是2");
			addCart(id);
		}
	}
	
	//查看購物車
	private int showCart() {
		int ans=0;
		for(String id: shopping) {
			Goods goods=(Goods)Manager.mapGAll.get(id);
			goods.showDataC();
			ans+=goods.getPrice();
		}
		System.out.println("--------------------------------------------------");
		return ans;
	}
	//從購物車刪除
	private void removeCart() {
		boolean correct=false;
		System.out.println("請輸入您要刪除的購物車內容："
							+ "\n(請輸入商品名稱)");
		String user=keyin.next();
		for(String a: shopping) {
			Goods goods=(Goods)Manager.mapGAll.get(a);
			if(goods.getName().equals(user)) {
				shopping.remove(a);
				correct=true;
				System.out.println("商品已移除~~");
			}
		}
		if(!correct) {
			System.out.println("商品輸入錯誤，未完成移除~");
		}
	}
	
	//結帳
	private void money() {
		System.out.println("請確認您的購物車中的所有商品：");
		int ans=showCart();
		System.out.println("總金額為："+ans
							+"\n請確認是否正確，並進行結帳？\n(正確請輸入「正確」)");
		String user=keyin.next();
		if(user.equals("正確")) {
			System.out.println("感謝您的消費♥ 已收到您的付款！！");
			shopping.clear();
		}else {
			System.out.println("已取消結帳~");
		}
	}

	public int getId() {
		return this.id;
	}
}