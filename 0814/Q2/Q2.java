package Q2;
import java.util.Scanner;
/**
 * 產生兩個陣列，陣列分別三個不同角色
 * me[]、you[]
 * 
 * 系統發動攻擊，隨機互打
 * 打到某人三角色皆歸零
 * @author user
 *
 */
public class Q2 {
	static boolean[] checkDeath= {false,false,false,false,false,false}; //user mg,sp,as com mg,sp,as
	public static void main(String[] args) {
		Scanner keyin = new Scanner(System.in);
		int round=1;//計算回合數
		boolean attack=false;//確認是否完成攻擊
		//newThing
		Mage mgU=new Mage();
		Mage mgC=new Mage();
		Support spU=new Support();
		Support spC=new Support();
		Assassin asU=new Assassin();
		Assassin asC=new Assassin();
		
		//遊戲說明
		System.out.println("歡迎來到pk大對決遊戲現場~\n此款遊戲是由玩家與電腦雙方進行對決，"
				+ "雙方各擁有三個不同職業的角色，每個職業都有不一樣的技能與大招\n"
				+ "回合採輪流制(玩家為先手)，一次僅能選擇一個使用的角色，直至某方的三個職業都離開人世才算遊戲結束！\n"
				+ "♥♥需特別注意，角色一旦死亡就無法復活囉！！♥♥\n"
				+ "接下來就來pk看看究竟是電腦還是聰明的你會勝出吧！！OvO");
		
		//遊戲內容
		while(true) {
			//reset 暫停回合
			resetStopTurn(mgU,round);
			resetStopTurn(spU,round);
			resetStopTurn(asU,round);
			resetStopTurn(mgC,round);
			resetStopTurn(spC,round);
			resetStopTurn(asC,round);
			
			//死亡判定
			if(setDeath(mgU)) {
				checkDeath[0]=true;
				mgU.setStopTurn(true);
			}
			if(setDeath(spU)) {
				checkDeath[1]=true;
				spU.setStopTurn(true);
			}
			if(setDeath(asU)) {
				checkDeath[2]=true;
				asU.setStopTurn(true);
			}
			if(setDeath(mgC)) {
				checkDeath[3]=true;
				mgC.setStopTurn(true);
			}
			if(setDeath(spC)) {
				checkDeath[4]=true;
				spC.setStopTurn(true);
			}
			if(setDeath(asC)) {
				checkDeath[5]=true;
				asC.setStopTurn(true);
			}
			
			//顯示狀態列
			gameShow(round,mgU,mgC,spU,spC,asU,asC);
			
			//遊戲結束判定
			if(checkDeath[0]&&checkDeath[1]&&checkDeath[2]) {
				System.out.println("真是太可惜了，你的全部角色都被電腦打敗囉！\n再接再厲，祝福你下次能夠大獲全勝！O-O");
				break;
			}else if(checkDeath[3]&&checkDeath[4]&&checkDeath[5]) {
				System.out.println("恭喜你打敗電腦了！！！你果然很聰明阿OvO");
				break;
			}
			
			//遊戲
			if(round%2==1) {//玩家進攻
				attack=false;
				while(!attack) {
					System.out.println("輪到你出手！\n請選擇進攻角色：1.法師 2.輔助 3.刺客");
					int user=keyin.nextInt();
					if(mgU.getStopTurn()&&spU.getStopTurn()&&asU.getStopTurn()) {
						System.out.println("由於您不是涼涼了，就是被暈眩了，所以您這回合pass囉~");
						break;
					}
					if(user==1) {
						if(mgU.getDeath()) {
							System.out.println("您的法師已經涼涼了，請不要虐待屍體");
							continue;
						}else if(mgU.getStopTurn()) {
							System.out.println("您的法師被暈眩了，本回合無法使用喔！");
							continue;
						}
						attack=mgAttackU(round,mgU,mgC,spU,spC,asU,asC);
						continue;
					}else if(user==2) {
						if(spU.getDeath()) {
							System.out.println("您的輔助已經涼涼了，請不要虐待屍體");
							continue;
						}else if(spU.getStopTurn()) {
							System.out.println("您的輔助被暈眩了，本回合無法使用喔！");
							continue;
						}
						attack=spAttackU(round,mgU,mgC,spU,spC,asU,asC);
						continue;
					}else if(user==3) {
						if(asU.getDeath()) {
							System.out.println("您的刺客已經涼涼了，請不要虐待屍體");
							continue;
						}else if(asU.getStopTurn()) {
							System.out.println("您的刺客被暈眩了，本回合無法使用喔！");
							continue;
						}
						attack=asAttackU(round,mgU,mgC,spU,spC,asU,asC);
						continue;
					}else {
						System.out.println("請輸入整數1或2或3，謝謝");
					}				
				}				
			}else if(round%2==0){ //電腦進攻
				System.out.println("輪到電腦進攻！！");
				while(true) {
					if(mgC.getStopTurn()&&spC.getStopTurn()&&asC.getStopTurn()) {
						System.out.println("由於電腦不是涼涼了，就是被暈眩了，所以電腦這回合pass囉~");
						break;
					}
					int com=randomAttack(3);
					if(com==0) {
						if(mgC.getStopTurn()) {
							continue;
						}
						mgAttackC(round,mgU,mgC,spU,spC,asU,asC);
						break;
					}else if(com==1) {
						if(spC.getStopTurn()) {
							continue;
						}
						spAttackC(round,mgU,mgC,spU,spC,asU,asC);
						break;
					}else if(com==2){
						if(asC.getStopTurn()) {
							continue;
						}
						asAttackC(round,mgU,mgC,spU,spC,asU,asC);
						break;
					}
				}
			}
			round++;
		}
		
	}
	
	//狀態列
	static void gameShow(int round,Mage mgU,Mage mgC,Support spU,Support spC,Assassin asU,Assassin asC) {
		System.out.println("===========================================================");
		System.out.println("Round "+round);
		System.out.println("-----------------------------------------------------------");
		System.out.println("玩家                         v.s                         電腦");
		
		if((mgU.getHp()<100&&mgU.getMp()<100)||(mgU.getHp()<10)||(mgU.getMp()<10)) {
			System.out.println("法師 等級："+mgU.getLevel()+" hp："+mgU.getHp()+" mp："+mgU.getMp()+
					" \t \t 法師 等級："+mgC.getLevel()+" hp："+mgC.getHp()+" mp："+mgC.getMp());
		}else {
			System.out.println("法師 等級："+mgU.getLevel()+" hp："+mgU.getHp()+" mp："+mgU.getMp()+
					" \t 法師 等級："+mgC.getLevel()+" hp："+mgC.getHp()+" mp："+mgC.getMp());
		}
		if((spU.getHp()<100&&spU.getMp()<100)||(spU.getHp()<10)||(spU.getMp()<10)) {
			System.out.println("輔助 等級："+spU.getLevel()+" hp："+spU.getHp()+" mp："+spU.getMp()+
					" \t \t 輔助 等級："+spC.getLevel()+" hp："+spC.getHp()+" mp："+spC.getMp());
		}else {
			System.out.println("輔助 等級："+spU.getLevel()+" hp："+spU.getHp()+" mp："+spU.getMp()+
					" \t 輔助 等級："+spC.getLevel()+" hp："+spC.getHp()+" mp："+spC.getMp());
			
		}
		if((asU.getHp()<100&&asU.getMp()<100)||(asU.getHp()<10)||(asU.getMp()<10)) {
			System.out.println("刺客 等級："+asU.getLevel()+" hp："+asU.getHp()+" mp："+asU.getMp()+
					" \t \t 刺客 等級："+asC.getLevel()+" hp："+asC.getHp()+" mp："+asC.getMp());
		}else {
			System.out.println("刺客 等級："+asU.getLevel()+" hp："+asU.getHp()+" mp："+asU.getMp()+
					" \t 刺客 等級："+asC.getLevel()+" hp："+asC.getHp()+" mp："+asC.getMp());
		}
		System.out.println("----------------------------------------------------------");
	}
	
	
	//玩家攻擊
	//玩家攻擊 法師
	static boolean mgAttackU(int round,Mage mgU,Mage mgC,Support spU,Support spC,Assassin asU,Assassin asC) {
		Scanner keyin = new Scanner(System.in);
		boolean attack=false;
		int user=0,target;
		System.out.println("法師技能「冰暴術」將消耗"+mgU.getMpLoss(0)+"mp，並對單一對手造成"
							+mgU.getSkillPower()+"傷害，且提升自身等級1/3級\n法師大招「暴風雪」將消耗"
							+mgU.getMpLoss(1)+"mp,並對全體對手分別造成"+mgU.getUltPower()+
							"傷害，且提升自身等級2/3級\nmp回復技能：回復法師100mp，並暫停下一回合法師進攻資格，且提升自身等級1級\n"
							+ "請選擇使用 1.冰暴術技能 2.暴風雪大招 3.mp回復技能，或是輸入任一正整數重新選擇進攻角色");
		user=keyin.nextInt();
		if(user==1) {
			if(!mgU.getStopSkill()) {
				System.out.println("請選擇攻擊對象：1.法師 2.輔助 3.刺客(輸入其他整數將重新選擇進攻角色)");
				target=keyin.nextInt();
				if(target==1) {
					attack=mgSkillAttack(mgU,mgC);
				}else if(target==2) {
					attack=mgSkillAttack(mgU,spC);
				}else if(target==3) {
					attack=mgSkillAttack(mgU,asC);
				}else {
					attack=false;
				}
			}
		}else if(user==2) {
			if(!mgU.getStopUlt()) {
				mgUltAttack(mgU,mgC);
				int b=mgU.getMp()+mgU.getMpLoss(1);
				mgU.setMp(b);
				mgUltAttack(mgU,spC);
				b=mgU.getMp()+mgU.getMpLoss(1);
				mgU.setMp(b);
				mgUltAttack(mgU,asC);
				int a=mgU.getTime()-4;
				mgU.setTime(a);
				System.out.println("對敵方全體進行暴風雪攻擊！");
				attack=true;
			}
		}else if(user==3) {
			mgU.returnMp(round);
			System.out.println("我方法師進行mp回復！");
			attack=true;
		}
		return attack;
	}
	//mgult attack
	static void mgUltAttack(Role mg,Role role) {
		int ans=role.getHp()-mg.ult();
		if(ans<=0) {
			ans=0;
		}
		role.setHp(ans);
	}
	//mgskill attack
	static boolean mgSkillAttack(Role roleAttack,Role roleDefense) {
		if(!roleDefense.getDeath()) {
			System.out.println("對敵方"+roleDefense.getJob()+"進行冰暴術技能攻擊！");
			int ans=roleDefense.getHp()-roleAttack.skill();
			if(ans<=0) {
				ans=0;
			}
			roleDefense.setHp(ans);
			return true;
		}else {
			System.out.println("敵方"+roleDefense.getJob()+"已經沒血了，請不要鞭屍QAQQ");
			return false;
		}
	}
	//玩家攻擊 輔助
	static boolean spAttackU(int round,Mage mgU,Mage mgC,Support spU,Support spC,Assassin asU,Assassin asC) {
		Scanner keyin=new Scanner(System.in);
		boolean attack=false;
		int user=0,target;
		System.out.println("輔助技能「花靈之舞」將消耗"+spU.getMpLoss(0)+"mp，並對單一對手造成"
							+spU.getSkillPower()+"傷害，並暈眩該角色，使其無法攻擊一回合，"
							+ "且提升自身等級1/3級\n輔助大招「花海綻放」將消耗"
							+spU.getMpLoss(1)+"mp,並增加全體隊友個別"+spU.getUltPower()+
							"血量，且提升自身等級2/3級\nmp回復技能：回復輔助100mp，並暫停下一回合輔助進攻資格，且提升自身等級1級\n"
							+ "請選擇使用 1.花靈之舞技能 2.花海綻放大招 3.mp回復技能，或是輸入任一正整數重新選擇進攻角色");
		user=keyin.nextInt();
		if(user==1) {
			if(!spU.getStopSkill()) {
				System.out.println("請選擇攻擊對象：1.法師 2.輔助 3.刺客(輸入其他整數將重新選擇進攻角色)");
				target=keyin.nextInt();
				if(target==1) {
					attack=spSkillAttack(spU,mgC,round);
				}else if(target==2) {
					attack=spSkillAttack(spU,spC,round);
				}else if(target==3) {
					attack=spSkillAttack(spU,asC,round);
				}else {
					attack=false;
				}
			}
		}else if(user==2) {
			if(!spU.getStopUlt()) {
				spUltAttack(spU,mgU);
				int b=spU.getMp()+spU.getMpLoss(1);
				spU.setMp(b);
				spUltAttack(spU,spU);
				b=spU.getMp()+spU.getMpLoss(1);
				spU.setMp(b);
				spUltAttack(spU,asU);
				int a=spU.getTime()-4;
				spU.setTime(a);
				System.out.println("對我方全體進行花海綻放大招回血！");
				attack=true;
			}
		}else if(user==3) {
			spU.returnMp(round);
			System.out.println("我方輔助進行mp回復！");
			attack=true;
		}
		return attack;
	}
	//輔助技能
	//輔助技能攻擊
	static boolean spSkillAttack(Support sp,Role role,int round) {
		if(!role.getDeath()) {
			System.out.println("對敵方"+role.getJob()+"進行花靈之舞技能攻擊！");
			int ans=role.getHp()-sp.skill();
			role.setHp(ans);
			role.setStopTurn(true);
			role.setStop(round);
			return true;
		}
		return false;
	}
	//輔助大招
	static void spUltAttack(Support sp,Role role) {
		int ans;
		if(role.getDeath()) {
			System.out.println(role.getJob()+"已死亡，無法進行血量回復");
			ans=0;
		}else {
			ans=role.getHp()+sp.ult();
		}
		role.setHp(ans);
	}
	//玩家攻擊 刺客
	static boolean asAttackU(int round,Mage mgU,Mage mgC,Support spU,Support spC,Assassin asU,Assassin asC) {
		Scanner keyin=new Scanner(System.in);
		boolean attack=false;
		int user=0,target;
		System.out.println("刺客技能「挽歌」將消耗"+asU.getMpLoss(0)+"mp，並對單一對手造成"
							+asU.getSkillPower()+"傷害，且提升自身等級1/3級\n刺客大招「謝幕」將消耗"
							+asU.getMpLoss(1)+"mp,並對單一對手造成"+asU.getUltPower()+
							"傷害，且提升自身等級2/3級\nmp回復技能：回復刺客100mp，並暫停下一回合刺客進攻資格，且提升自身等級1級\n"
							+ "請選擇使用 1.挽歌技能 2.謝幕大招 3.mp回復技能，或是輸入任一正整數重新選擇進攻角色");
		user=keyin.nextInt();
		if(user==1) {
			if(!asU.getStopSkill()) {
				System.out.println("請選擇攻擊對象：1.法師 2.輔助 3.刺客(輸入其他整數將重新選擇進攻角色)");
				target=keyin.nextInt();
				if(target==1) {
					attack=asSkillAttack(asU,mgC);
				}else if(target==2) {
					attack=asSkillAttack(asU,spC);
				}else if(target==3) {
					attack=asSkillAttack(asU,asC);
				}else {
					attack=false;
				}
			}
		}else if(user==2) {
			if(!asU.getStopUlt()) {
				System.out.println("請選擇攻擊對象：1.法師 2.輔助 3.刺客(輸入其他整數將重新選擇進攻角色)");
				target=keyin.nextInt();
				if(target==1) {
					attack=asUltAttack(asU,mgC);
				}else if(target==2) {
					attack=asUltAttack(asU,spC);
				}else if(target==3) {
					attack=asUltAttack(asU,asC);
				}else {
					attack=false;
				}
				attack=true;
			}
		}else if(user==3) {
			asU.returnMp(round);
			System.out.println("我方刺客進行mp回復！");
			attack=true;
		}
		return attack;
	}
	//刺客技能攻擊
	static boolean asSkillAttack(Assassin roleAttack,Role roleDefense) {
		if(!roleDefense.getDeath()) {
			System.out.println("對敵方"+roleDefense.getJob()+"進行輓歌技能攻擊！");
			int ans=roleDefense.getHp()-roleAttack.skill();
			if(ans<=0) {
				ans=0;
			}
			roleDefense.setHp(ans);
			return true;
		}else {
			System.out.println("敵方"+roleDefense.getJob()+"已經沒血了，請不要鞭屍QAQQ");
			return false;
		}
	}
	//刺客ult attack
	static boolean asUltAttack(Assassin roleAttack,Role roleDefense) {
		if(!roleDefense.getDeath()) {
			System.out.println("對敵方"+roleDefense.getJob()+"進行謝幕大招攻擊!");
			int ans=roleDefense.getHp()-roleAttack.ult();
			if(ans<=0) {
				ans=0;
			}
			roleDefense.setHp(ans);
			return true;
		}else {
			System.out.println("敵方"+roleDefense.getJob()+"已經沒血了，請不要鞭屍QQQ");
			return false;
		}
	}
	
	
	//電腦攻擊
	//電腦攻擊 法師
	static void mgAttackC(int round,Mage mgU,Mage mgC,Support spU,Support spC,Assassin asU,Assassin asC) {
		boolean attack=false;
		int a=0,target;
		if(mgC.getStopSkill()) {
			mgC.returnMp(round);
			System.out.println("電腦法師進行mp回復！");
		}else if(mgC.getStopUlt()) {
			a=(int)(Math.random()*2+1);
			if(a==1) {
				//skill
				mgSkillAttackC(round,mgU,mgC,spU,spC,asU,asC);
			}else {
				mgC.returnMp(round);
				System.out.println("電腦法師進行mp回復！");
			}
		}else {
			a=(int)(Math.random()*3+1);
			if(a==1) {
				//skill
				mgSkillAttackC(round,mgU,mgC,spU,spC,asU,asC);
			}else if(a==2) {
				//ult
				mgUltAttackC(mgU,mgC,spU,spC,asU,asC);
			}else {
				mgC.returnMp(round);
				System.out.println("電腦法師進行mp回復！");
			}	
		}
	}
	//電腦法師 技能攻擊完整內容
	static void mgSkillAttackC(int round,Mage mgU,Mage mgC,Support spU,Support spC,Assassin asU,Assassin asC) {
		int who=randomAttack(0); //mp,sp,as
		if(who==0) {
			System.out.println("電腦法師對我方法師進行冰暴術技能攻擊！");
			skillAttackC(mgC,mgU);
		}else if(who==1) {
			System.out.println("電腦法師對我方輔助進行冰暴術技能攻擊！");
			skillAttackC(mgC,spU);
		}else {
			System.out.println("電腦法師對我方刺客進行冰暴術技能攻擊！");
			skillAttackC(mgC,asU);
		}
	}
	//電腦法師 大招攻擊完整內容
	static void mgUltAttackC(Mage mgU,Mage mgC,Support spU,Support spC,Assassin asU,Assassin asC) {
		mgUltAttack(mgC,mgU);
		int b=mgC.getMp()+mgC.getMpLoss(1);
		mgC.setMp(b);
		mgUltAttack(mgC,spU);
		b=mgC.getMp()+mgC.getMpLoss(1);
		mgC.setMp(b);
		mgUltAttack(mgC,asU);
		int a=mgC.getTime()-4;
		mgC.setTime(a);
		System.out.println("電腦法師對我方全體造成暴風雪傷害");
	}
	//電腦輔助攻擊
	static void spAttackC(int round,Mage mgU,Mage mgC,Support spU,Support spC,Assassin asU,Assassin asC) {
		boolean attack=false;
		int a=0,target;
		if(spC.getStopSkill()) {
			spC.returnMp(round);
			System.out.println("電腦輔助進行mp回復！");
		}else if(spC.getStopUlt()) {
			a=(int)(Math.random()*2+1);
			if(a==1) {
				//skill
				spSkillAttackC(round,mgU,mgC,spU,spC,asU,asC);
			}else {
				spC.returnMp(round);
				System.out.println("電腦輔助進行mp回復！");
			}
		}else {
			a=(int)(Math.random()*3+1);
			if(a==1) {
				//skill
				spSkillAttackC(round,mgU,mgC,spU,spC,asU,asC);
			}else if(a==2) {
				//ult
				spUltAttackC(mgC,spC,asC);
			}else {
				spC.returnMp(round);
				System.out.println("電腦輔助進行mp回復！");
			}
		}
	}
	//電腦輔助 技能攻擊完整內容
	static void spSkillAttackC(int round,Mage mgU,Mage mgC,Support spU,Support spC,Assassin asU,Assassin asC) {
		int who=randomAttack(0); //mp,sp,as
		if(who==0) {
			System.out.println("電腦輔助對我方法師進行花靈之舞技能攻擊！");
			skillAttackC(spC,mgU);
			mgU.setStop(round);
			mgU.setStopTurn(true);
		}else if(who==1) {
			System.out.println("電腦輔助對我方輔助進行花靈之舞技能攻擊！");
			skillAttackC(spC,spU);
			spU.setStop(round);
			spU.setStopTurn(true);
		}else {
			System.out.println("電腦輔助對我方刺客進行花靈之舞技能攻擊！");
			skillAttackC(spC,asU);
			asU.setStop(round);
			asU.setStopTurn(true);
		}
	}
	//電腦輔助 大招完整內容
	static void spUltAttackC(Mage mgC,Support spC,Assassin asC) {
		spUltAttackCuse(spC,mgC);
		int b=spC.getMp()+spC.getMpLoss(1);
		spC.setMp(b);
		spUltAttackCuse(spC,spC);
		b=spC.getMp()+spC.getMpLoss(1);
		spC.setMp(b);
		spUltAttackCuse(spC,asC);
		int a=spC.getTime()-4;
		spC.setTime(a);
		System.out.println("電腦輔助對敵方全體進行花海綻放大招回血");
	}
	//電腦輔助 大招
	static void spUltAttackCuse(Support sp,Role role) {
		int ans;
		if(role.getDeath()) {
			ans=0;
		}else {
			ans=role.getHp()+sp.ult();
		}
		role.setHp(ans);
	}
	//電腦刺客 技能攻擊
	static void asAttackC(int round,Mage mgU,Mage mgC,Support spU,Support spC,Assassin asU,Assassin asC) {
		boolean attack=false;
		int a=0,target;
		if(asC.getStopSkill()) {
			asC.returnMp(round);
			System.out.println("電腦刺客進行mp回復！");
		}else if(asC.getStopUlt()) {
			a=(int)(Math.random()*2+1);
			if(a==1) {
				//skill
				asSkillAttackC(mgU,mgC,spU,spC,asU,asC);
			}else {
				asC.returnMp(round);
				System.out.println("電腦刺客進行mp回復！");
			}
		}else {
			a=(int)(Math.random()*3+1);
			if(a==1) {
				//skill
				asSkillAttackC(mgU,mgC,spU,spC,asU,asC);
			}else if(a==2) {
				//ult
				asUltAttackC(mgU,mgC,spU,spC,asU,asC);
			}else {
				asC.returnMp(round);
				System.out.println("電腦刺客進行mp回復！");
			}
		}
	}
	//電腦刺客技能攻擊
	static void asSkillAttackC(Mage mgU,Mage mgC,Support spU,Support spC,Assassin asU,Assassin asC) {
		int who=randomAttack(0); //mp,sp,as
		if(who==0) {
			System.out.println("電腦刺客對我方法師進行挽歌技能攻擊！");
			skillAttackC(asC,mgU);
		}else if(who==1) {
			System.out.println("電腦刺客對我方輔助進行挽歌技能攻擊！");
			skillAttackC(asC,spU);
		}else {
			System.out.println("電腦刺客對我方刺客進行挽歌技能攻擊！");
			skillAttackC(asC,asU);
		}
	}
	//電腦刺客大招攻擊
	static void asUltAttackC(Mage mgU,Mage mgC,Support spU,Support spC,Assassin asU,Assassin asC) {
		int who=randomAttack(0); //mp,sp,as
		if(who==0) {
			System.out.println("電腦刺客對我方法師進行謝幕大招攻擊！");
			ultAttackC(asC,mgU);
		}else if(who==1) {
			System.out.println("電腦刺客對我方輔助進行謝幕大招攻擊！");
			ultAttackC(asC,spU);
		}else {
			System.out.println("電腦刺客對我方刺客進行謝幕大招攻擊！");
			ultAttackC(asC,asU);
		}
	}
	//單體技能攻擊使用
	static void skillAttackC(Role roleAttack,Role roleDefense) {
		int ans=roleDefense.getHp()-roleAttack.skill();
		if(ans<=0) {
			ans=0;
		}
		roleDefense.setHp(ans);
	}
	//單體大招攻擊
	static void ultAttackC(Role roleAttack,Role roleDefense) {
		int ans=roleDefense.getHp()-roleAttack.ult();
		if(ans<=0) {
			ans=0;
		}
		roleDefense.setHp(ans);
	}
	
	//隨機攻擊某一活人 (i=0進攻對象 i=3發起進攻者隨機選擇)
	static int randomAttack(int i) {
		
		//只剩一個活著
		if(checkDeath[0+i]&&checkDeath[1+i]) {
			return 2;
		}else if(checkDeath[0+i]&&checkDeath[2+i]) {
			return 1;
		}else if(checkDeath[1+i]&&checkDeath[2+i]) {
			return 0;
		}
		//剩兩個活著
		int a=(int)(Math.random()*2+1);
		if(checkDeath[0+i]) {
			if(a==1) {
				return 1;
			}else {
				return 2;
			}
		}else if(checkDeath[1+i]) {
			if(a==1) {
				return 0;
			}else {
				return 2;
			}
		}else if(checkDeath[2+i]) {
			if(a==1) {
				return 0;
			}else {
				return 1;
			}
		}
		//全都活著
		a=(int)(Math.random()*3+1);
		if(a==0) {
			return 0;
		}else if(a==1) {
			return 1;
		}else {
			return 2;
		}
	}
	
	
	//死亡判定
	static boolean setDeath(Role role) {
		if(role.getHp()<=0) {
			role.setDeath(true);
			return true;
		}
		return false;
	}
	
	
	//resetStopTurn
	static void resetStopTurn(Role role,int round) {
		if(role.getStopTurn()) {
			if(round-role.getStop()>=3) {
				role.setStopTurn(false);
			}
		}
	}
}


