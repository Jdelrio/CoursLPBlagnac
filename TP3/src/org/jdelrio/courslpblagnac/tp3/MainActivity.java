package org.jdelrio.courslpblagnac.tp3;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
	private EditText editTextVariable;
	private Button buttonShare;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("Lifecycle", "onCreate");
		setContentView(R.layout.activity_main);
		textViewLangue = (TextView) findViewById(R.id.textViewLangue);
		textViewOrientation = (TextView) findViewById(R.id.textViewOrientation);
		textViewEtatCalcul = (TextView) findViewById(R.id.textViewEtatCalcul);
		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
		buttonStartCalculNormal = (Button) findViewById(R.id.buttonStartCalculNormal);
		buttonStartCalculNormal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startCalcul();
				doMyCalcul();
				endCalcul();
			}
		});
		buttonStartCalculThread = (Button) findViewById(R.id.buttonStartCalculThread);
		buttonStartCalculThread.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startCalcul();
				Thread t1 = new Thread(new Runnable() {
					public void run() {
						doMyCalcul();
					}
				});
				t1.start();
				endCalcul();
			}
		});
		buttonStartCalculAsyncTask = (Button) findViewById(R.id.buttonStartCalculAsyncTask);
		buttonStartCalculAsyncTask.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AsyncTask<Void, String, Void> at1 = new AsyncTask<Void, String, Void>() {

					@Override
					protected void onPreExecute() {
						startCalcul();
						super.onPreExecute();
					}

					@Override
					protected Void doInBackground(Void... params) {
						doMyCalcul();
						publishProgress("10%");
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						endCalcul();
						super.onPostExecute(result);
					}

					@Override
					protected void onProgressUpdate(String... values) {
						// editText.setText(values);
						super.onProgressUpdate(values);
					}
				};
				at1.execute();
				doMyLongCalcul();
			}
		});
		editTextVariable = (EditText) findViewById(R.id.editTextVariable);
		buttonShare = (Button) findViewById(R.id.buttonShar);
		buttonShare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String text = editTextVariable.getText().toString();
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_TEXT, text);
				intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Title");

				startActivity(intent);
			}
		});
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		Log.d("Lifecycle", "onRestoreInstanceState");
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onResume() {
		Log.d("Lifecycle", "onResume");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.d("Lifecycle", "onPause");
		super.onPause();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.d("Lifecycle", "onSaveInstanceState");
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onDestroy() {
		Log.d("Lifecycle", "onDestroy");
		super.onDestroy();
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

	private void doMyLongCalcul() {
		try {
			progressBar2.setProgress(0);
			Thread.sleep(10000);
			progressBar2.setProgress(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
