
public class RPGgame {
	static boolean gameDoing=true;
	public static void main(String[] args) {
		java.util.Scanner keyin = new java.util.Scanner(System.in);
		String userName;
		int userRole;
		System.out.println("請輸入您的角色名稱：");
		userName = keyin.next();
		while(true) {
			System.out.println("本遊戲提供兩種職業，1：武士，2：舞者\n請選擇您想要的職業(請輸入1或2)：");
			userRole = keyin.nextInt();
			if(userRole==1||userRole==2) {
				break;
			}else {
				System.out.println("輸入錯誤，請重新輸入！！");
			}
		}
		Warrior userW = new Warrior(userName,"user");
		Warrior comW = new Warrior("電腦武士","computer");
		Dancer userD = new Dancer(userName,"user");
		Dancer comD = new Dancer("電腦舞者","computer");
		System.out.println("遊戲開始！！");
		int gameTime=0;
		if(userRole==1) {
			while(gameDoing) {
				gameTime++;
				gaming(comD,userW,gameTime);
			}
		}else {
			while(gameDoing) {
				gameTime++;
				gaming(userD,comW,gameTime);
			}
		}
		
	}
	
	//遊戲介面
	public static void gaming(Dancer dan,Warrior war,int gameTime) {
		//狀態表
		System.out.println();
		System.out.println("======================================================================");
		System.out.println("Round "+gameTime);
		System.out.println("----------------------------------------------------------------------");
		System.out.println(dan.getName()+" ("+dan.getJob()+") 等級："+dan.getLevel()+" hp："+dan.getHp()+" mp："+dan.getMp()
							+" v.s hp："+war.getHp()+" mp："+war.getMp()+" "+war.getName()+" ("+war.getJob()+") 等級："+war.getLevel());
		//結束判定
		if(war.getHp()<=0) {
			gameDoing=false;
			if(dan.getPlayer().equals("user")) {
				System.out.println("遊戲結束，恭喜你戰勝了"+war.getName()+"\n尼超厲害ㄉ！！");
			}else {
				System.out.println("很可惜，你敗給了"+dan.getName()+"\n繼續加油喲！");
			}
		}else if(dan.getHp()<=0){
			gameDoing=false;
			if(war.getPlayer().equals("user")) {
				System.out.println("遊戲結束，恭喜你戰勝了"+dan.getName()+"\n尼超厲害ㄉ！！");
			}else {
				System.out.println("很可惜，你敗給了"+war.getName()+"\n繼續加油喲！");
			}
		}
		if(gameDoing) {
			//隨機攻擊者
			int who=(int)(Math.random()*2);
			//輪到舞者攻擊
			if(who==0) {
				System.out.println("\n隨機輪到了"+dan.getName()+"進行攻擊\n");
				if(dan.getPlayer().equals("user")) {
					userAttack(dan,war,0);
				}else {
					comAttack(dan,war,0);
				}
			}else {
				System.out.println("\n隨機輪到了"+war.getName()+"進行攻擊\n");
				if(war.getPlayer().equals("user")) {
					userAttack(dan,war,1);
				}else {
					comAttack(dan,war,1);
				}
			}
		}
	}
	
	//player attack
	public static void userAttack(Dancer dan,Warrior war,int jobUse){
		java.util.Scanner keyin = new java.util.Scanner(System.in);
		int toDo;
		//使用者是舞者
		if(jobUse==0) {
			while(true) {
				System.out.println("您擁有的技能為："+dan.getFightName()+"，將消耗"+dan.getMpFight()+"mp，並造成"+dan.getFightPower()+"的傷害\n"
						+ "您擁有的大招為："+dan.getDeathblowName()+"，將消耗"+dan.getMpDeath()+"mp，並造成"+dan.getBlowPower()+"的傷害\n"
						+ "請選擇您要使用的技能，或選擇恢復hp(將恢復50hp，若hp已無法進行技能或是大招攻擊，則下次攻擊自動視為pass，並進行hp恢復)\n"
						+ "請輸入以下其中一個代碼以進行動作：1.技能攻擊 2.大招攻擊 3.hp回復");
				toDo=keyin.nextInt();
				if(toDo==1) {
					if(dan.stopAttack) {
						System.out.println("您的mp不足已進行技能攻擊，請您更改選擇");
						continue;
					}else {
						war.setHp(war.getHp()-dan.fight());
						break;
					}	
				}else if(toDo==2) {
					if(dan.stopBlow) {
						System.out.println("您的mp不足已進行大招攻擊，請您更改選擇");
						continue;
					}else {
						war.setHp(war.getHp()-dan.deathblow());
						break;
					}	
				}else if(toDo==3) {
					dan.mpReturn();
					break;
				}
			}
		}
		//使用者是武士
		else {
			while(true) {
				System.out.println("您擁有的技能為："+war.getFightName()+"，將消耗"+war.getMpFight()+"mp，並造成"+war.getFightPower()+"的傷害\n"
						+ "您擁有的大招為："+war.getDeathblowName()+"，將消耗"+war.getMpDeath()+"mp，並造成"+war.getBlowPower()+"的傷害\n"
						+ "請選擇您要使用的技能，或選擇恢復hp(將恢復50hp，若hp已無法進行技能或是大招攻擊，則下次攻擊自動視為pass，並進行hp恢復)\n"
						+ "請輸入以下其中一個代碼以進行動作：1.技能攻擊 2.大招攻擊 3.hp回復");
				toDo=keyin.nextInt();
				if(toDo==1) {
					if(war.stopAttack) {
						System.out.println("您的mp不足已進行技能攻擊，請您更改選擇");
						continue;
					}else {
						dan.setHp(dan.getHp()-war.fight());
						break;
					}	
				}else if(toDo==2) {
					if(war.stopBlow) {
						System.out.println("您的mp不足已進行大招攻擊，請您更改選擇");
						continue;
					}else {
						dan.setHp(dan.getHp()-war.deathblow());
						break;
					}	
				}else if(toDo==3) {
					war.mpReturn();
					break;
				}
			}
		}
	}
	public static void comAttack(Dancer dan,Warrior war,int jobUse) {
		//電腦是舞者
		if(jobUse==0) {
			if(dan.stopAttack) {
				dan.mpReturn();
			}else if(dan.stopBlow) {
				int use = (int)(Math.random()*2);
				if(use==0) {
					war.setHp(war.getHp()-dan.fight());
				}else {
					dan.mpReturn();
				}
			}else {
				int use = (int)(Math.random()*3);
				if(use==0) {
					war.setHp(war.getHp()-dan.fight());
				}else if(use==1){
					dan.mpReturn();
				}else {
					war.setHp(war.getHp()-dan.deathblow());
				}
			}
		}else {
			if(war.stopAttack) {
				war.mpReturn();
			}else if(war.stopBlow) {
				int use = (int)(Math.random()*2);
				if(use==0) {
					dan.setHp(dan.getHp()-war.fight());
				}else {
					war.mpReturn();
				}
			}else {
				int use = (int)(Math.random()*3);
				if(use==0) {
					dan.setHp(dan.getHp()-war.fight());
				}else if(use==1){
					war.mpReturn();
				}else {
					dan.setHp(dan.getHp()-war.deathblow());
				}
			}
		}
	}
}
