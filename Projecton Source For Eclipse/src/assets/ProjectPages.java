package assets;

public enum ProjectPages {

	SERVER_START_PAGE("/server/ServerStartGUI.fxml"),
	CONNECT_TO_SERVER_PAGE("/client/ClientConnectionPage.fxml"),
	LOGIN_PAGE("/boundries/LoginPage.fxml"),
	MENU_PAGE("/boundries/MenuPage.fxml"),
	DEMO_LANDING_PAGE("/boundries/DemoLandingPage.fxml");
	
	private String path;
	
	private ProjectPages(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
