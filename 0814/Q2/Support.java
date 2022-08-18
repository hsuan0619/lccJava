package Q2;
//原型：海倫
public class Support extends Role{
	private static int[] power= {100,120,140,160,180,200,200*2,250*2,300*2};
	private static int[] mpLoss= {80,120};
	
	public Support() {
		super(470,3588,"輔助",power,mpLoss);
	}
	
	//進行他方指定攻擊，並造成該角色暈眩一回合
	public int skill() {
		return super.skill();
	}
	
	//進行我方群體回血
	public int ult() {
		return super.ult();
	}
}
