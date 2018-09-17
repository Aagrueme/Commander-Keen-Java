package commanderKeen.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer extends Thread {
	
	private int delay;
	private ActionListener listener;

	public Timer(int delay, ActionListener listener) {
		this.delay = delay;
		this.listener = listener;
	}
	
	@Override
	public void run() {
		super.run();
		while(isAlive()) {
			listener.actionPerformed(new ActionEvent(0, 0, ""));
			try {
				this.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
