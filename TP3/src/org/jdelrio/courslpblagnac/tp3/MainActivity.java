package org.jdelrio.courslpblagnac.tp3;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button buttonStartCalculAsyncTask;
	private Button buttonStartCalculThread;
	private Button buttonStartCalculNormal;
	private ProgressBar progressBar1;
	private TextView textViewOrientation;
	private TextView textViewLangue;
	private ProgressBar progressBar2;
	private TextView textViewEtatCalcul;
	private EditText editTextOnRotation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textViewLangue = (TextView) findViewById(R.id.textViewLangue);
		textViewOrientation = (TextView) findViewById(R.id.textViewOrientation);
		textViewEtatCalcul = (TextView) findViewById(R.id.textViewEtatCalcul);
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
		buttonStartCalculNormal = (Button) findViewById(R.id.buttonStartCalculNormal);
		buttonStartCalculThread = (Button) findViewById(R.id.buttonStartCalculThread);
		buttonStartCalculAsyncTask = (Button) findViewById(R.id.buttonStartCalculAsyncTask);
		editTextOnRotation = (EditText) findViewById(R.id.editTextOnRotation);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void startCalcul() {
		textViewEtatCalcul.setText("Calcul en cours");
	}

	private void doMyCalcul() {
		try {
			progressBar2.setProgress(0);
			Thread.sleep(500);
			progressBar2.setProgress(1);
			Thread.sleep(500);
			progressBar2.setProgress(2);
			Thread.sleep(500);
			progressBar2.setProgress(3);
			Thread.sleep(500);
			progressBar2.setProgress(4);
			Thread.sleep(500);
			progressBar2.setProgress(5);
			Thread.sleep(500);
			progressBar2.setProgress(6);
			Thread.sleep(500);
			progressBar2.setProgress(7);
			Thread.sleep(500);
			progressBar2.setProgress(8);
			Thread.sleep(500);
			progressBar2.setProgress(9);
			Thread.sleep(500);
			progressBar2.setProgress(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void endCalcul() {
		textViewEtatCalcul.setText("Calcul stoppé");
	}
}
