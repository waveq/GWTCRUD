package com.sampleGwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sampleGwt.client.SampleGwtService;
import com.sampleGwt.client.dbservice.CalcService;
import com.sampleGwt.client.myEnum.*;
import com.sampleGwt.client.entity.*;

import java.util.List;


public class SampleGwtServiceImpl extends RemoteServiceServlet implements SampleGwtService {
	public String getMessage(String msg) {
		return "Client said: \"" + msg + "\"<br>Server answered: \"Hi client\"";
	}

	private CalcService calcService = new CalcService();

	@Override
	public double calculateResult(Calc calc) {
		return calculate(calc.getProductOne(), calc.getProductTwo(), calc.getAction());
	}

	@Override
	public boolean saveCalculation(Calc calc) {
		return calcService.addCalc(calc);
	}

	@Override
	public List<Calc> getAllCalculations() {
		return calcService.getCalculations();
	}

	@Override
	public boolean deleteCalculation(Calc calc) {
		for(Calc c : calcService.getCalculations()) {
			if(c.equals(calc)) {
				calcService.deleteCalc(c);
				return true;
			}
		}
		return false;
	}

	private Double calculate(double productOne, double productTwo, Action action) {
		if(action == Action.dodawanie) {
			return productOne + productTwo;
		} else if(action == Action.odejmowanie) {
			return productOne - productTwo;
		} else if(action == Action.mnozenie) {
			return productOne * productTwo;
		} else if(action == Action.dzielenie) {
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