package Q2;

public class Role {
	private int mp,hp,level,time,stop;
	private String job;
	private int[] power,mpLoss;//skill 0-5,ult 6-8;mpLoss skill 0,ult 1
	private boolean stopSkill,stopUlt,stopTurn,death;
	public Role(int mp,int hp,String job,int[] power,int[] mpLoss) {
		this.mp=mp;
		this.hp=hp;
		this.level=1;
		this.job=job;
		this.power=power;
		this.mpLoss=mpLoss;
		this.stopSkill=false;
		this.stopUlt=false;
		this.stopTurn=false;
		this.death=false;
		this.stop=-5;
		this.time=0;
	}
	
	//方法
	//技能
	public int skill() {
		setStopPower();
		if(!this.stopSkill) {
			int useLevel=this.level;
			this.mp-=mpLoss[0];
			setStopPower();
			this.time++;
			this.level=this.time/3+1;
			if(useLevel<2) {
				return this.power[0];
			}else if(useLevel<3) {
				return this.power[1];
			}else if(useLevel<4) {
				return this.power[2];
			}else if(useLevel<5) {
				return this.power[3];
			}else if(useLevel<6) {
				return this.power[4];
			}else{
				return this.power[5];
			}
		}else {
			return 0;
		}
	}
	//大招
	public int ult() {
		setStopPower();
		if(!this.stopUlt) {
			int useLevel=this.level;
			this.mp-=this.mpLoss[1];
			setStopPower();
			this.time+=2;
			this.level=time/3+1;
			if(useLevel<3) {
				return power[6];
			}else if(useLevel<6) {
				return power[7];
			}else{
				return power[8];
			}
		}else {
			return 0;
		}
	}
	//回復mp
	public void returnMp(int round) {
		this.mp+=100;
		setStopPower();
		this.time+=3;
		this.level=time/3+1;
		this.stop=round;
		this.stopTurn=true;
	}
	
	//設定技能、大招使用條件(mp不低於使用)
	private void setStopPower() {
		if(this.mp<mpLoss[0]) {
			this.stopSkill=true;
			this.stopUlt=true;
		}else if(this.mp<mpLoss[1]) {
			this.stopUlt=true;
			this.stopSkill=false;
		}else {
			this.stopUlt=false;
			this.stopSkill=false;
		}
	}
	
	
	//set
	public void setMp(int mp) {
		this.mp=mp;
	}
	public void setHp(int hp) {
		this.hp=hp;
	}
	public void setStopTurn(boolean stopTurn) {
		this.stopTurn=stopTurn;
	}
	public void setStop(int stop) {
		this.stop=stop;
	}
	public void setDeath(boolean death) {
		this.death=death;
	}
	public void setTime(int time) {
		this.time=time;
		this.level=time/3+1;
	}
	
	//get
	public int getMp() {
		return this.mp;
	}
	public int getHp() {
		return this.hp;
	}
	public int getLevel() {
		return this.level;
	}
	public String getJob() {
		return this.job;
	}
	public boolean getStopSkill() {
		return this.stopSkill;
	}
	public boolean getStopUlt() {
		return this.stopUlt;
	}
	public boolean getStopTurn() {
		return this.stopTurn;
	}
	public boolean getDeath() {
		return this.death;
	}
	public int getMpLoss(int i) {
		return this.mpLoss[i];
	}
	public int getStop() {
		return this.stop;
	}
	public int getSkillPower() {
		if(this.level<2) {
			return this.power[0];
		}else if(this.level<3) {
			return this.power[1];
		}else if(this.level<4) {
			return this.power[2];
		}else if(this.level<5) {
			return this.power[3];
		}else if(this.level<6) {
			return this.power[4];
		}else{
			return this.power[5];
		}
	}
	public int getUltPower() {
		if(this.level<3) {
			return power[6];
		}else if(this.level<6) {
			return power[7];
		}else{
			return power[8];
		}
	}
	public int getTime() {
		return this.time;
	}
}
