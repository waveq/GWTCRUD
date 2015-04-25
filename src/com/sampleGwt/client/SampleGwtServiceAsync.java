package com.sampleGwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sampleGwt.client.entity.*;

import java.util.List;

public interface SampleGwtServiceAsync {
	void getMessage(String msg, AsyncCallback<String> async);

	void calculateResult(Calc calc, AsyncCallback<Double> async);

	void saveCalculation(Calc calc, AsyncCallback<Boolean> async);

	void getAllCalculations(AsyncCallback<List<Calc>> async);
}
