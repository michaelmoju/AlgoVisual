package server;

import java.io.Serializable;
import java.util.HashMap;

public class ServerResponse implements Serializable {
	private Boolean isLogin;
	private HashMap<String, Boolean> progress;
	
	public ServerResponse(Boolean isLogin) {
		this.isLogin = isLogin;
	}
	
	public ServerResponse(Boolean isLogin, HashMap<String, Boolean> progress) {
		this(isLogin);
		this.progress = progress;
	}

	public Boolean getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}

	public HashMap<String, Boolean> getProgress() {
		return progress;
	}

	public void setProgress(HashMap<String, Boolean> progress) {
		this.progress = progress;
	}
	
	
	
}
