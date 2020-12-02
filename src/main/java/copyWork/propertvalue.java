package copyWork;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class propertvalue {

	String result = "";
	InputStream inputStream;
	private String username;
	private String password;
	private String filepath;
	private String filename;
	private String host;
	private String port;
	private String isEnabled;
	private String isAuth;
	private String directory;

	

	public String getPropValues() throws IOException {

		try {
			Properties prop = new Properties();
			String propFileName = "gradle.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			Date time = new Date(System.currentTimeMillis());

			// get the property value and print it out
			username = prop.getProperty("Username");
			password = prop.getProperty("Password");
			filepath = prop.getProperty("filepath");
			filename = prop.getProperty("filename");
			port = prop.getProperty("corrtec.port");
			host = prop.getProperty("corrtec");
			isEnabled = prop.getProperty("isEnabled");
			isAuth = prop.getProperty("isAuth");
			directory = prop.getProperty("directory");

			result = "Properties = " + username + " , " + password + ", " + filepath + ", " + filename;
			System.out.println(result + "\nProgram Ran on " + time + " by user=" + username);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(String isAuth) {
		this.isAuth = isAuth;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
}
