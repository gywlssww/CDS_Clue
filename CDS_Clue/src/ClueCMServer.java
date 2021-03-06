import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import kr.ac.konkuk.ccslab.cm.manager.CMCommManager;
import kr.ac.konkuk.ccslab.cm.stub.CMServerStub;


public class ClueCMServer {
	private CMServerStub m_serverStub;
	private ClueCMServerEventHandler m_eventHandler;

	public ClueCMServer() {
		m_serverStub = new CMServerStub();
		m_eventHandler = new ClueCMServerEventHandler(m_serverStub, this);
	}
	
	public CMServerStub getServerStub()
	{
		return m_serverStub;
	}
	
	public ClueCMServerEventHandler getServerEventHandler()
	{
		return m_eventHandler;
	}
	public void startCM()
	{
		// get current server info from the server configuration file
		String strSavedServerAddress = null;
		String strCurServerAddress = null;
		int nSavedServerPort = -1;
		String strNewServerAddress = null;
		String strNewServerPort = null;
		int nNewServerPort = -1;
		
		strSavedServerAddress = m_serverStub.getServerAddress();
		strCurServerAddress = CMCommManager.getLocalIP();
		nSavedServerPort = m_serverStub.getServerPort();
		
		// ask the user if he/she would like to change the server info
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("========== start CM");
		System.out.println("detected server address: "+strCurServerAddress);
		System.out.println("saved server port: "+nSavedServerPort);

		boolean bRet = m_serverStub.startCM(); //CMDBMANAGER init
		if(!bRet)
		{
			System.err.println("CM initialization error!");
			return;
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClueCMServer server = new ClueCMServer();
		CMServerStub cmStub = server.getServerStub();
		cmStub.setAppEventHandler(server.getServerEventHandler());
		server.startCM();
	}

}
