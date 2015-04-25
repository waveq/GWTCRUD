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

	final Button button = new Button("Oblicz");
	final TextBox productOne = new TextBox();
	final TextBox productTwo = new TextBox();
	final Label action = new Label("+");
	final Label result = new Label();
	int num;

	double p1;
	double p2;
	double r1;

	private Calc calc;

	public void onModuleLoad() {
		RootPanel.get("product1").add(productOne);
		RootPanel.get("action").add(action);

		RootPanel.get("product2").add(productTwo);

		RootPanel.get("button").add(button);
		RootPanel.get("result").add(result);

		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				calc = new Calc(Double.parseDouble(productOne.getText()), Double.parseDouble(productTwo.getText()), Action.sum);
//				result.setText("Wynik to: "+ (p1+p2));


//				SampleGwtService.App.getInstance().getMessage("Hello, World dupa!", new MyAsyncCallback(result, calc));
				SampleGwtService.App.getInstance().calculateResult(calc, new MyAsyncCallback(result));
			}
		});
	}

	private static class MyAsyncCallback implements AsyncCallback<Double> {
		private Label resultLabel;

		public MyAsyncCallback(Label label) {
			this.resultLabel = label;
		}

//		public void onSuccess(String result) {
//			resultLabel.getElement().setInnerHTML(result);
//		}

		public void onFailure(Throwable throwable) {
			resultLabel.setText("Failed to receive answer from server!");
		}

		@Override
		public void onSuccess(Double result) {
			resultLabel.getElement().setInnerHTML(result+"");
		}
	}
}
