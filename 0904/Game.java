/**
 * 龜兔賽跑遊戲
 * 建立三個類別 game turtle rabbit
 * turtle & rabbit 繼承執行緒/介面執行緒
 * turtle每秒走一步
 * rabbit每一秒可能在睡覺或是走兩步
 * 總步數為20部，看誰先到目的地
 * @author user
 *
 */
public class Game {
	public static void main(String[] args) {
		Turtle t=new Turtle();
		Rabbit r=new Rabbit();
		t.start();
		r.start();
	}
}
