package server;

import java.io.Serializable;
import java.util.HashMap;

public class ClientRequest implements Serializable {
	private String action; // login, register, store
	private String name;
	private String pwd;
	private HashMap<String, Boolean> progress;
	
	public ClientRequest(String action, String name, String pwd) {
		this.action = action;
		this.name = name;
		this.pwd = pwd;
	}
	
	public ClientRequest(String action, String name, String pwd, HashMap<String, Boolean> progress) {
		this(action, name, pwd);
		this.progress = progress;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public HashMap<String, Boolean> getProgress() {
		return progress;
	}

	public void setProgress(HashMap<String, Boolean> progress) {
		this.progress = progress;
	}
	
	
	
}
