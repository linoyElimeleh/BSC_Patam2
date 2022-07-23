package tests.test10;


public class ControllerMaker {

	private static Controller controller;

	// implement
	public static Controller get() {
		if (controller == null) {
			controller = new Controller();
		}

		return controller;
	}

}
