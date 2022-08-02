
public class Role {
	//參數
	private int hp,time,level,mp,mpFight,mpDeath,fightPower,blowPower;
	private String name,job,player,fightName,deathblowName;
	boolean stopAttack,stopBlow;
	private int[] powerArr = new int[9];//0~5是fight 6~8是blow
	//建構子
	public Role(String name, int hp, int mp,String job,String player,String fightName,String deathblowName,
			int mpFight,int mpDeath,int[] powerArr) {
		this.fightName = fightName;
		this.mpFight = mpFight;
		this.mpDeath = mpDeath;
		this.deathblowName=deathblowName;
		this.player=player;
		this.time=0;
		this.stopAttack=false;
		this.stopBlow=false;
		this.level=1;
		this.powerArr=powerArr;
		this.fightPower=powerArr[0];
		this.blowPower=powerArr[6];
		this.hp = hp;
		this.mp = mp;
		this.name = name;
		this.job = job;
	}
	
	//方法
	//大招
	public int deathblow(int mpAttack,int mpBlow) {
		this.mp-=mpBlow;
		if(this.mp<mpAttack) {
			this.stopAttack = true;
			this.stopBlow = true;
		}else if(this.mp<mpBlow) {
			this.stopBlow = true;
			this.stopAttack=false;
		}else {
			this.stopAttack=false;
			this.stopBlow=false;
		}
			
		this.time+=2;
		this.level=this.time/3+1;
		setFightPower();
		setBlowPower();
		return this.blowPower;
	}
	//技能
	public int fight(int mpAttack,int mpBlow) {
		this.mp-=mpAttack;
		if(this.mp<mpAttack) {
			this.stopAttack = true;
			this.stopBlow = true;
		}else if(this.mp<mpBlow) {
			this.stopBlow = true;
			this.stopAttack=false;
		}else {
			this.stopAttack=false;
			this.stopBlow=false;
		}
			
		this.time++;
		this.level=this.time/3+1;
		setFightPower();
		setBlowPower();
		return this.fightPower;
	}
	//mp回復機制
	public void mpReturn(int mpAttack,int mpBlow) {
		System.out.println("進行mp回復技能");
		this.time+=3;
		this.level=this.time/3+1;
		setFightPower();
		setBlowPower();
		this.mp+=50;
		if(this.mp<mpAttack) {
			this.stopAttack = true;
			this.stopBlow = true;
		}else if(this.mp<mpBlow) {
			this.stopBlow = true;
			this.stopAttack=false;
		}else {
			this.stopAttack=false;
			this.stopBlow=false;
		}
	}
	
	//狀態設定
	private int setFightPower() {
		switch(this.level) {
			case 1:
				this.fightPower=this.powerArr[0];
				break;
			case 2:
				this.fightPower=this.powerArr[1];
				break;
			case 3:
				this.fightPower=this.powerArr[2];
				break;
			case 4:
				this.fightPower=this.powerArr[3];
				break;
			case 5:
				this.fightPower=this.powerArr[4];
			default:
				this.fightPower=this.powerArr[5];
		}
		return this.fightPower;
	}
	private int setBlowPower() {
		if(this.level<5) {
			this.blowPower=this.powerArr[6];
		}else if(this.level<10) {
			this.blowPower=this.powerArr[7];
		}else {
			this.blowPower=this.powerArr[8];
		}
		return this.blowPower;
	}
	
	//get狀態
	public String getName() {
		return this.name;
	}
	public int getHp() {
		return this.hp;
	}
	public int getMp() {
		return this.mp;
	}
	public int getLevel() {
		return this.level;
	}
	public int getTime() {
		return this.time;
	}
	public String getJob() {
		return this.job;
	}
	public String getPlayer() {
		return this.player;
	}
	public String getFightName() {
		return this.fightName;
	}
	public String getDeathblowName() {
		return this.deathblowName;
	}
	public int getMpDeath() {
		return this.mpDeath;
	}
	public int getMpFight() {
		return this.mpFight;
	}
	public int getFightPower() {
		return this.fightPower;
	}
	public int getBlowPower() {
		return this.blowPower;
	}
	//set狀態
	public void setName(String name) {
		this.name = name;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public void setPowerArr(int[] arr) {
		this.powerArr=arr;
	}
}
