package Q2;
//原型：貂蟬

public class Mage extends Role{
	private static int[] power= {400,490,580,670,760,850,350,440,530};
	private static int[] mpLoss= {65,150};
	
	public Mage() {
		super(490,3358,"法師",power,mpLoss);
	}
	
	//造成單體傷害
	public int skill() {
		//System.out.println("進行技能：冰暴術攻擊");
		return super.skill();
	}
	
	//造成群體傷害
	public int ult() {
		return super.ult();
	}
	
	//額外功能 待寫
	public void showSkillPower() {
		System.out.println("");
	}
}
