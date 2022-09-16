
public class Turtle extends Thread{
	public void run() {
		try {
			for(int i=1;i<=20;i++) {
				Thread.sleep(1000);
				System.out.println("龜龜走了第"+i+"步\n");
			}
			System.out.println("讓我們恭喜龜龜到終點了~！");
		}catch(InterruptedException e) {
			
		}
	}
}
