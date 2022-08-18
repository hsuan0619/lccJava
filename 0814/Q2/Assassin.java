package Q2;
//原型：潘因
public class Assassin extends Role{
	private static int[] power= {350,400,450,500,550,600,750,1000,1250};
	private static int[] mpLoss= {40,40};
	
	public Assassin() {
		super(100,3550,"刺客",power,mpLoss);
	}
	
	//針對單體傷害
	public int skill() {
		//System.out.println("進行技能攻擊：挽歌");
		return super.skill();
	}
	//針對單體進行高傷害
	public int ult() {
		//System.out.println("進行大招攻擊：謝幕");
		return super.ult();
	}
}
