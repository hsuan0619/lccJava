

//原型：歌德爾

public class Warrior extends Role{
	static int[] powerArr= {500,590,680,770,860,950,100*4,130*4,160*4};
	public Warrior(String name,String player) {
		super(name,3678*2,420,"武士",player,"揮金如土","紙醉金迷",75,150,powerArr);
	}
	
	public int fight() {
		System.out.println("進行技能：揮金如土攻擊");
		return fight(75,150);
	}
	
	public int deathblow() {
		System.out.println("進行大招：紙醉金迷攻擊");
		return deathblow(75,150);
	}
	public void mpReturn() {
		mpReturn(75,150);
	}
}

