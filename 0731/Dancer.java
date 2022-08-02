
//原型：貂蟬

public class Dancer extends Role{
	static int[] powerArr= {400,490,580,670,760,850,350*3,440*3,530*3};
	public Dancer(String name,String player){
		//name hp mp
		super(name,3358*2,490,"舞者",player,"冰爆術","暴風雪",65,150,powerArr);
	}
	public int fight() {
		System.out.println("進行技能：冰爆術攻擊");
		return fight(65,150);
	}
	
	public int deathblow() {
		System.out.println("進行大招：暴風雪攻擊");
		return deathblow(65,150);
	}
	public void mpReturn() {
		mpReturn(65,150);
	}
	
}
