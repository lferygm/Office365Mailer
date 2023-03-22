package be.fery.office365.mailer;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import be.fery.config.MailConfig;
import be.fery.office365.ClientCredentialGrant;

public class IMAPReader {

	private static boolean debug = false;
	
	/**
	 * 
	 */
	public IMAPReader() {
		super();
	}

	/**
	 * 
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		System.out.println("--- START ---");


		Properties props = new Properties();

		props.put("mail.store.protocol", "imap");
		props.put("mail.imap.host", MailConfig.getInstance().getServer());
		props.put("mail.imap.port", "993");
		props.put("mail.imap.ssl.enable", "true");
		props.put("mail.imap.starttls.enable", "true");
		props.put("mail.imap.auth", "true");
		props.put("mail.imap.auth.mechanisms", "XOAUTH2");
		props.put("mail.imap.user", MailConfig.getInstance().getAdress());
		if(debug) {
			props.put("mail.debug", "true");
			props.put("mail.debug.auth", "true");
		}

		String token = ClientCredentialGrant.getToken();
		Session session = Session.getInstance(props);
		//        session.setDebug(true);
		Store store;
		try {
			store = session.getStore("imap");
			store.connect(MailConfig.getInstance().getServer(), MailConfig.getInstance().getAdress(), token);

			Folder[] list = store.getDefaultFolder().list();

			for (Folder folder : list) {
				System.out.println(folder.getName() + "\t" + folder.getFullName() + " " + folder.getMessageCount());
			}

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}


		System.out.println("--- STOP  ---");
	}

}
