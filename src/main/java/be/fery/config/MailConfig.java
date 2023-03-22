package be.fery.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MailConfig {

	private static String CONFIG_FILE = "mail.properties";

	private static MailConfig instance;

	private Properties properties;

	private String authority;
	private String tenantID;
	private String applicationId;
	private String applicationSecret;
	private String scope;
	private String server;
	private String adress;

	public static MailConfig getInstance() {
		if (instance == null) {
			instance = new MailConfig();
		}
		return instance;
	}

	/**
	 * 
	 */
	private MailConfig() {
		readConfig();
	}

	/**
	 * 
	 * @param properties
	 */
	public void readConfig(Properties properties) {
		String tmp;

		tmp = properties.getProperty("tenant.ID");
		if (null != tmp) {
			tenantID = tmp;
		}

		tmp = properties.getProperty("authority");
		if (null != tmp) {
			authority = tmp;
		}

		tmp = properties.getProperty("application.ID");
		if (null != tmp) {
			applicationId = tmp;
		}

		tmp = properties.getProperty("application.secret");
		if (null != tmp) {
			applicationSecret = tmp;
		}

		tmp = properties.getProperty("application.scope");
		if (null != tmp) {
			scope = tmp;
		}

		tmp = properties.getProperty("server");
		if (null != tmp) {
			setServer(tmp);
		}

		tmp = properties.getProperty("adress");
		if (null != tmp) {
			adress = tmp;
		}
		
	}

	/**
	 * 
	 */
	private void readConfig() {

		try {
			FileInputStream in = new FileInputStream(CONFIG_FILE);
			properties = new Properties();
			properties.load(in);
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		readConfig(properties);
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getTenantID() {
		return tenantID;
	}

	public void setTenantID(String tenantID) {
		this.tenantID = tenantID;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationSecret() {
		return applicationSecret;
	}

	public void setApplicationSecret(String applicationSecret) {
		this.applicationSecret = applicationSecret;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}


}
