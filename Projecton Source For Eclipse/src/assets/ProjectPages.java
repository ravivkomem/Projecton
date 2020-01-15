package assets;

/**
 * Enum that represents all our project boundries
 * @author Raviv Komem
 *
 */
public enum ProjectPages {

	SERVER_START_PAGE("/server/ServerStartGUI.fxml"),
	CONNECT_TO_SERVER_PAGE("/client/ClientConnectionPage.fxml"),
	LOGIN_PAGE("/boundries/LoginPage.fxml"),
	MENU_PAGE("/boundries/MenuPage.fxml"),
	WORK_STATION_PAGE("/boundries/WorkStationPage.fxml"),
	UPLOAD_REQUEST_PAGE("/boundries/UploadRequestPage.fxml"),
	TIME_EXTENSION_PAGE("/boundries/TimeExtensionPage.fxml"),
	TECH_MANAGER_PAGE("/boundries/TechManagerPage.fxml"),
	EMPLOYEE_PERMISSION("/boundries/EmployeePermission.fxml"),
	SUPERVISOR_PAGE("/boundries/SupervisorPage.fxml"),
	REQUEST_LIST_PAGE("/boundries/RequestListPage.fxml"),
	EXTRA_DETAILS_PAGE("/boundries/ExtraDetailsPage.fxml"),
	EXECUTION_LEADER_PAGE("/boundries/ExecutionLeaderPage.fxml"),
	COMMITTEE_PAGE("/boundries/CommitteeDecisionPage.fxml"),
	ANALYZER_PAGE("/boundries/AnalyzerPage.fxml"),
	ANALISIS_REPORT_PAGE("/boundries/AnalysisReportPage.fxml"),
	TESTER_PAGE("/boundries/TesterPage.fxml"),
	PERFORMANCE_REPORT_PAGE("/boundries/PerformanceReportPage.fxml"),
	APPOINT_TESTER("/boundries/AppointTesterPage.fxml"),
	ACTIVITY_REPORT_PAGE("/boundries/ActivityReportPage.fxml"),
	DELAY_REPORT_PAGE("/boundries/DelayReportPage.fxml");
	
	private String path;
	
	private ProjectPages(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
