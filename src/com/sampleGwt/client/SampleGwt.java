package com.sampleGwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.view.client.ListDataProvider;
import com.sampleGwt.client.dbservice.CalcService;
import com.sampleGwt.client.entity.*;
import com.sampleGwt.client.myEnum.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class SampleGwt implements EntryPoint {

	final Button calcButton = new Button("Oblicz");

	final RadioButton radioSum = new RadioButton("actionGroup", "+");
	final RadioButton radioDiff = new RadioButton("actionGroup", "-");
	final RadioButton radioDivide = new RadioButton("actionGroup", "/");
	final RadioButton radioRatio = new RadioButton("actionGroup", "*");
	Action action = Action.sum;

	final static Button saveButton = new Button("Zapisz");
	final TextBox productOne = new TextBox();
	final TextBox productTwo = new TextBox();
	final Label result = new Label();

	static Calc calc;
	private CalcService calcService = new CalcService();

	public void onModuleLoad() {
		setup();
		calcButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				setAction();
				calc = new Calc(Double.parseDouble(productOne.getText()), Double.parseDouble(productTwo.getText()), action);

				SampleGwtService.App.getInstance().calculateResult(calc, new MyAsyncCallback(result));
			}
		});

		saveButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				calcService.addCalc(calc);
				prepareCalculations();
			}
		});
	}

	private void setAction() {
		if(radioSum.getValue()) {
			action = Action.sum;
		}
		if(radioDiff.getValue()) {
			action = Action.diff;
		}
		if(radioDivide.getValue()) {
			action = Action.divide;
		}
		if(radioRatio.getValue()) {
			action = Action.ratio;
		}
	}

	private void setup() {
		RootPanel.get("product1").add(productOne);
		RootPanel.get("product2").add(productTwo);
		RootPanel.get("button").add(calcButton);
		RootPanel.get("result").add(result);
		setActions();
		prepareCalculations();
	}

	private void setActions() {
		radioSum.setValue(true);
		VerticalPanel actionsPanel = new VerticalPanel();
		actionsPanel.setSpacing(10);
		actionsPanel.add(radioSum);
		actionsPanel.add(radioDiff);
		actionsPanel.add(radioDivide);
		actionsPanel.add(radioRatio);
		RootPanel.get("action").add(actionsPanel);
	}

	private void prepareCalculations() {
		CellTable<Calc> table = new CellTable<Calc>();

		TextColumn<Calc> productOneC = new TextColumn<Calc>() {
			@Override
			public String getValue(Calc calc) {
				return calc.getProductOne()+"";
			}
		};

		TextColumn<Calc> actionC = new TextColumn<Calc>() {
			@Override
			public String getValue(Calc calc) {
				return calc.getAction()+"";
			}
		};

		TextColumn<Calc> productTwoC = new TextColumn<Calc>() {
			@Override
			public String getValue(Calc calc) {
				return calc.getProductTwo()+"";
			}
		};

		TextColumn<Calc> resultC = new TextColumn<Calc>() {
			@Override
			public String getValue(Calc calc) {
				return calc.getResult()+"";
			}
		};

		table.addColumn(productOneC, "produkt1");
		table.addColumn(actionC, "działanie");
		table.addColumn(productTwoC, "produkt2");
		table.addColumn(resultC, "wynik");

		ListDataProvider<Calc> dataProvider = new ListDataProvider<Calc>();
		dataProvider.addDataDisplay(table);

		List<Calc> list = dataProvider.getList();
		for (Calc calc : calcService.getCalculations()) {
			list.add(calc);
		}

		RootPanel.get("calculations").clear();
		RootPanel.get("calculations").add(table);
	}

	private static class MyAsyncCallback implements AsyncCallback<Double> {
		private Label resultLabel;

		public MyAsyncCallback(Label label) {
			this.resultLabel = label;
		}
		public void onFailure(Throwable throwable) {
			resultLabel.setText("Nie można dzielić przez 0");
			RootPanel.get("save").clear();
		}

		@Override
		public void onSuccess(Double result) {
			resultLabel.getElement().setInnerHTML(result+"");
			calc.setResult(result);
			RootPanel.get("save").add(saveButton);
		}
	}
}
