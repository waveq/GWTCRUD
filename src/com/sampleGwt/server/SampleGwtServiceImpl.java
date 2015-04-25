package com.sampleGwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sampleGwt.client.SampleGwtService;
import com.sampleGwt.client.myEnum.*;
import com.sampleGwt.client.entity.*;


public class SampleGwtServiceImpl extends RemoteServiceServlet implements SampleGwtService {
	public String getMessage(String msg) {
		return "Client said: \"" + msg + "\"<br>Server answered: \"Hi client\"";
	}

	@Override
	public double calculateResult(Calc calc) {
		return calculate(calc.getProductOne(), calc.getProductTwo(), calc.getAction());
	}

	private Double calculate(double productOne, double productTwo, Action action) {
		if(action == Action.sum) {
			return productOne + productTwo;
		} else if(action == Action.diff) {
			return productOne - productTwo;
		} else if(action == Action.ratio) {
			return productOne * productTwo;
		} else if(action == Action.quotien) {
			return divide(productOne, productTwo);
		}
		return null;
	}

	private Double divide(double one, double two) {
		if(two == 0) {
			return null;
		}
		else return one/two;
	}
}