package com.sampleGwt.client.entity;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.sampleGwt.client.myEnum.*;

/**
 * Created by Szymon on 2015-03-24.
 */
public class Calc implements IsSerializable {

	private double productOne;
	private double productTwo;
	private Action action;
	private double result;

	public Calc(double productOne, double productTwo, Action action) {
		this.productOne = productOne;
		this.productTwo = productTwo;
		this.action = action;
	}

	public Calc() {
	}


	public double getProductOne() {
		return productOne;
	}

	public void setProductOne(double productOne) {
		this.productOne = productOne;
	}

	public double getProductTwo() {
		return productTwo;
	}

	public void setProductTwo(double productTwo) {
		this.productTwo = productTwo;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}


	@Override
	public boolean equals(Object obj) {
		Calc c;
		if(obj instanceof Calc) {
			c = (Calc) obj;
			System.out.println("true");
		}
		else {
			return false;
		}
		if(c.getAction() == action && c.getProductOne() == productOne
				&& c.getProductTwo() == productTwo && c.getResult() == result) {
			System.out.println("equals");
			return true;
		} else {
			return false;
		}
	}
}
