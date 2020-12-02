package copyWork;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;


public class updated {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		Scanner inScanner = new Scanner(System.in);
		System.out.print("Enter folder path containing pdfs:");

		String inFile = inScanner.next();
//		System.out.println("You entered: " + inFile);

//		System.out.print("Select the type of file you want to get:");
//		System.out.println("\n1-Array");
//		System.out.println("2-List");
//		System.out.println("3-Text File");

		int fileType = 3;
//		System.out.println("You selected: " + fileType);

//// Creating a File object for directory
//		File directoryPath = new File(inFile);
//
//// List of all files and directories
//		File filesList[] = directoryPath.listFiles();
		
		// TODO Auto-generated method stub
//		object val = new object();
		propertvalue properties = new propertvalue();
		properties.getPropValues();
//		inFile = properties.getDirectory();
		File directoryPath = new File(inFile);

		// List of all files and directories
		File filesList[] = directoryPath.listFiles();
		takeInput(filesList, fileType, inFile, properties.getFilename());
		
//		String test = properties.getFilename();
		

		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter the file path ");
		String filePath = properties.getFilepath();

//		System.out.println("Enter the email index ");
		int n1 = 0; //sc.nextInt();
		
		

		HashMap<String, object> map = new HashMap<String, object>();

		String line;
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		while ((line = reader.readLine()) != null) {
			String[] parts = line.split(",", 4);
			if (parts.length >= 2) {
				String key = parts[1];
				object val = new object(parts[n1], parts[1]);

				map.put(key, val);

			} else {
				System.out.println("ignoring line: " + line);
			}
		}

		for (String key : map.keySet()) {
			System.out.println(key + " : " + map.get(key).email);
		}

		String line1 = "";
		String splitBy = ",";

		BufferedReader br1 = new BufferedReader(new FileReader(properties.getFilename()));
		while ((line1 = br1.readLine()) != null) // returns a Boolean value
		{
			String[] pdffile = line1.split(splitBy);
			// use comma as separator
			object obj = map.get(pdffile[1]);
//			System.out.println(obj.email + " " + obj.phone + " " + pdffile[1]);
			sendFileViaEmail(obj.email, pdffile[0] , properties);
			reader.close();
		}
	}

	public static void sendFileViaEmail(String email, String filepath , propertvalue props) {

//		Scanner sc = new Scanner(System.in);
//	       System.out.println("Enter the Email Address ");
//	 	   String Address = sc.nextLine();
//	 	   

		System.out.println("Wait... ");
		final String pswd = props.getPassword(); //enter your password here

		// Recipient's email ID needs to be mentioned.
		String to = email;

		// Sender's email ID needs to be mentioned
		String from = props.getUsername(); //enter your email

		
		

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", props.getHost());
		properties.setProperty("mail.smtp.port", props.getPort() );
		properties.setProperty("mail.smtp.starttls.enable", props.getIsEnabled());
		properties.setProperty("mail.smtp.auth", props.getIsAuth());
		properties.setProperty("mail.password", pswd);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, pswd);
			}
		});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("This is the Subject Line!");

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText("This is message body");

			// Create a Multipart message
			Multipart Multipart = new MimeMultipart();

			// Set text message part
			Multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = filepath;
			FileDataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			Multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(Multipart);

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Address EXCEPTION" + e);
		} 
		catch (MessagingException e) {
			System.out.println("MESSAGING EXCEPTION" + e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(" EXCEPTION" + e);
		}
	}

	public static void takeInput(File filesList[], int fileType, String inFile, String fileName) throws IOException {

		String[] array;
		List<String> list = new ArrayList<String>();
		File myOb = new File(fileName);
		FileWriter myObj = new FileWriter(fileName);
		String type = "pdf";
		int i = 0;
		for (File file : filesList) {
			if (file.isFile() && file.getName().endsWith(type))
//            if (file.isFile() && file.getName().endsWith(type) || file.getName().endsWith(type1))
			{
				System.out.println("File name: " + file.getName());
				PDDocument document = PDDocument.load(file);

				PDFTextStripper stripper = new PDFTextStripper();
				String text = stripper.getText(document);
				document.close();

				String[] lines;
				lines = text.split(System.getProperty("line.separator"));
				String initialString = lines[0];
// String stringExcerpt = initialString.substring(15, 25);
				String value = initialString.substring(initialString.indexOf("#") + 2, initialString.indexOf("#") + 13);

				if (fileType == 1) {

					array = new String[filesList.length];
					array[i] = (inFile + file.getName() + "," + value);
					System.out.println(array[i]);
					i = i + 1;
				} else if (fileType == 2) {
					list.add(inFile + file.getName() + value);
					System.out.println(inFile + file.getName() + "," + value);

				}

				else if (fileType == 3) {

					myObj.write(inFile + file.getName());
					myObj.write(",");
					myObj.write(value);
					myObj.write("\n");

				}

			}

		}
		myObj.close();
	}
	
}
