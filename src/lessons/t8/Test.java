package lessons.t8;

public class Test {

    public static class LongTaskDemo implements Command {
        String s;

        public LongTaskDemo(String s) {
            this.s = s;
        }

        @Override
        public void execute() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(s + " finished");
        }
    }

    public static void main(String[] args) {
        ActivePriorityController apc = new ActivePriorityController();
        apc.addCommand(new LongTaskDemo("C"), 2);
        apc.addCommand(new LongTaskDemo("B"), 1);
        apc.addCommand(new LongTaskDemo("A"), 0);
        apc.close();
        System.out.println("Main is dead.");
    }
}
