package com.sampleGwt.client.dbservice;

import com.sampleGwt.client.entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Szymon on 2015-03-24.
 */

public class CalcService {

	private static List calculations = new ArrayList<Calc>();

	public boolean addCalc(Calc c) {
		return calculations.add(c);
	}

	public List<Calc> getCalculations() {
		return calculations;
	}

	public Object deleteCalc(Calc c) {
		for(int i =0;i< getCalculations().size();i++) {
			if(c.getProductOne() == getCalculations().get(i).getProductOne()
					&& c.getProductTwo() == getCalculations().get(i).getProductTwo()
					&& c.getAction() == getCalculations().get(i).getAction()) {
				System.out.println("deleted");
				return calculations.remove(i);
			}
		}
		return null;
	}

}
