package com.sampleGwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sampleGwt.client.entity.*;

import java.util.List;

@RemoteServiceRelativePath("SampleGwtService")
public interface SampleGwtService extends RemoteService {
	// Sample interface method of remote interface
	String getMessage(String msg);
	double calculateResult(Calc calc);
	boolean saveCalculation(Calc calc);
	List<Calc> getAllCalculations();
	boolean deleteCalculation(Calc calc);

	/**
	 * Utility/Convenience class.
	 * Use SampleGwtService.App.getInstance() to access static instance of SampleGwtServiceAsync
	 */
	public static class App {
		private static SampleGwtServiceAsync ourInstance = GWT.create(SampleGwtService.class);

		public static synchronized SampleGwtServiceAsync getInstance() {
			return ourInstance;
		}
	}
}
