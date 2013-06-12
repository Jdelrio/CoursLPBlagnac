package org.jdelrio.courslpblagnac.tp2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private Button b1;
	private Button b2;
	private Button b3;
	private Button b4;
	private Button b5;
	private Button b6;
	private Button b7;
	private Button b8;
	private Button b9;
	private Button b0;
	private Button buttonEff;
	private Button buttonRAZ;
	private Button buttonPlus;
	private Button buttonLess;
	private Button buttonDiv;
	private Button buttonMulti;
	private TextView textViewPart2Content;
	private TextView textViewResultContent;
	private EditText editText1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mylayout);
		findViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void findViews() {
		b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(this);
		b2 = (Button) findViewById(R.id.button2);
		b2.setOnClickListener(this);
		b3 = (Button) findViewById(R.id.button3);
		b3.setOnClickListener(this);
		b4 = (Button) findViewById(R.id.button4);
		b4.setOnClickListener(this);
		b5 = (Button) findViewById(R.id.button5);
		b5.setOnClickListener(this);
		b6 = (Button) findViewById(R.id.button6);
		b6.setOnClickListener(this);
		b7 = (Button) findViewById(R.id.button7);
		b7.setOnClickListener(this);
		b8 = (Button) findViewById(R.id.button8);
		b8.setOnClickListener(this);
		b9 = (Button) findViewById(R.id.button9);
		b9.setOnClickListener(this);
		b0 = (Button) findViewById(R.id.button0);
		b0.setOnClickListener(this);
		buttonEff = (Button) findViewById(R.id.buttonEff);
		buttonEff.setOnClickListener(this);
		buttonRAZ = (Button) findViewById(R.id.buttonRAZ);
		buttonRAZ.setOnClickListener(this);
		buttonPlus = (Button) findViewById(R.id.buttonPlus);
		buttonPlus.setOnClickListener(this);
		buttonLess = (Button) findViewById(R.id.buttonLess);
		buttonLess.setOnClickListener(this);
		buttonDiv = (Button) findViewById(R.id.buttonDiv);
		buttonDiv.setOnClickListener(this);
		buttonMulti = (Button) findViewById(R.id.buttonMulti);
		buttonMulti.setOnClickListener(this);
		textViewPart2Content = (TextView) findViewById(R.id.textViewPart2Content);
		textViewResultContent = (TextView) findViewById(R.id.textViewResultContent);
		editText1 = (EditText) findViewById(R.id.editTextPart1Content1);
	}

	@Override
	public void onClick(View v) {
		if (v == b0 || v == b1 || v == b2 || v == b3 || v == b4 || v == b5
				|| v == b6 || v == b7 || v == b8 || v == b9) {
			textViewPart2Content.setText(textViewPart2Content.getText()
					+ ((Button) v).getText().toString());
		} else if (v == buttonRAZ) {
			textViewPart2Content.setText("0");
		} else if (v == buttonEff) {
			String text = textViewPart2Content.getText().toString();
			if (text.length() > 0) {
				textViewPart2Content.setText(text.subSequence(0,
						text.length() - 1));
			} else {
				textViewPart2Content.setText("0");
			}
		} else {
			int part1 = Integer.parseInt(editText1.getText().toString());
			int part2 = Integer.parseInt(textViewPart2Content.getText()
					.toString());
			int res = 0;
			if (v == buttonPlus) {
				res = part1 + part2;
			} else if (v == buttonLess) {
				res = part1 - part2;
			} else if (v == buttonDiv) {
				res = part1 / part2;
			} else if (v == buttonMulti) {
				res = part1 * part2;
			}
			textViewResultContent.setText(Integer.toString(res));
		}

	}
}
