package tests.test2022;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public class TaskerList extends LinkedList<BlockingQueue<Runnable>>{

	volatile boolean stopStatus;
	private static final long serialVersionUID = 1L;

	public TaskerList(){
		this.stopStatus = false;
	}

	public void pollAll() {
		while(!this.isEmpty()){
			BlockingQueue<Runnable> currentBlockingQueue = this.poll();
			while(!(currentBlockingQueue.isEmpty() || this.stopStatus)){
				currentBlockingQueue.poll().run();
			}
		}
	}

	public void stopRunning() {
		this.stopStatus = true;
	}

	@Override
	public void addLast(BlockingQueue<Runnable> blockingQueueOfRunnable){
		if(!this.stopStatus){
			super.addLast(blockingQueueOfRunnable);
		}
	}
}
