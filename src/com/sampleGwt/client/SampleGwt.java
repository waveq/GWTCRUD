package com.sampleGwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.sampleGwt.client.entity.*;
import com.sampleGwt.client.myEnum.*;

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
	int num;

	double p1;
	double p2;
	double r1;

	private Calc calc;

	public void onModuleLoad() {
		setup();



		calcButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				setAction();
				calc = new Calc(Double.parseDouble(productOne.getText()), Double.parseDouble(productTwo.getText()), action);
//				result.setText("Wynik to: "+ (p1+p2));


//				SampleGwtService.App.getInstance().getMessage("Hello, World dupa!", new MyAsyncCallback(result, calc));
				SampleGwtService.App.getInstance().calculateResult(calc, new MyAsyncCallback(result));
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
			RootPanel.get("save").add(saveButton);
		}
	}
}
