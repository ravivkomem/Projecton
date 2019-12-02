package boundries;

import temp.ClientConsole;

public class DemoLandingController {

	public static void sendMsgToServer (String msg)
	{
		ClientConsole cc = new ClientConsole("localhost", 5555);
		ClientConsole.client.handleMessageFromClientUI("SELECT * FROM icm.requirements");
	}
}