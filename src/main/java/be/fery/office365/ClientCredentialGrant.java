package be.fery.office365;

import java.util.Collections;

//Copyright (c) Microsoft Corporation. All rights reserved.
//Licensed under the MIT License.

import com.microsoft.aad.msal4j.ClientCredentialFactory;
import com.microsoft.aad.msal4j.ClientCredentialParameters;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import com.microsoft.aad.msal4j.IClientCredential;

import be.fery.config.MailConfig;

public class ClientCredentialGrant {

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private static IAuthenticationResult acquireToken() throws Exception {

		IClientCredential credential = ClientCredentialFactory.createFromSecret(MailConfig.getInstance().getApplicationSecret());

		ConfidentialClientApplication cca = ConfidentialClientApplication.builder(MailConfig.getInstance().getApplicationId(), credential)
				.authority(MailConfig.getInstance().getAuthority() + MailConfig.getInstance().getTenantID() + "/").build();

		ClientCredentialParameters parameters = ClientCredentialParameters.builder(Collections.singleton(MailConfig.getInstance().getScope())).build();

		return cca.acquireToken(parameters).join();
	}

	/**
	 * 
	 * @return
	 */
	public static String getToken() {
		String token = null;
		
		IAuthenticationResult result;
		try {
			result = acquireToken();
			token = result.accessToken();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return token;
	}
	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		IAuthenticationResult result = acquireToken();
		System.out.println("Access token: " + result.accessToken());
	}

}