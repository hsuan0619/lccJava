
import java.math.*;
public class Rabbit extends Thread{
	public void run() {
		int step=0;
		for(int i=1;true;i++) {
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				
			}
			int use=(int)(Math.random()*2);
			if(use==0) {
				System.out.println("兔子正在睡覺覺Zzzzz\n");
			}else {
				step+=2;
				System.out.println("兔子跳了第"+step+"步\n");
			}
			if(step>=20) {
				System.out.println("恭喜兔子跳完了全程路途！");
				break;
			}
		}
	}
}
