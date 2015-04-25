package com.sampleGwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sampleGwt.client.entity.*;

public interface SampleGwtServiceAsync {
	void getMessage(String msg, AsyncCallback<String> async);

	void calculateResult(Calc calc, AsyncCallback<Double> async);
}
