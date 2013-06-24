package lpapsio.delrio.julien;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	private String[] dates = new String[] { "Lundi", "Mardi", "Mercredi",
			"Jeudi", "Vendredi", "Samedi", "Dimanche" };
	private ListView mList;
	private Button buttonLeft;
	private Button buttonCenter;
	private Button buttonRight;
	private TextView tvButtonClicked;
	private TextView textViewProgression;
	private TextView textViewState;
	private Button buttonStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Récupération des boutons et implémentation des comportements
		buttonLeft = (Button) findViewById(R.id.buttonLeft);
		buttonLeft.setOnClickListener(this);
		buttonCenter = (Button) findViewById(R.id.buttonCenter);
		buttonCenter.setOnClickListener(this);
		buttonRight = (Button) findViewById(R.id.buttonRight);
		buttonRight.setOnClickListener(this);
		tvButtonClicked = (TextView) findViewById(R.id.textViewBoutonClique);

		// Récupération des éléments pour le thread
		textViewProgression = (TextView) findViewById(R.id.textViewProgression);
		textViewState = (TextView) findViewById(R.id.textViewState);
		buttonStart = (Button) findViewById(R.id.buttonStart);
		buttonStart.setOnClickListener(this);

		// Récupération de la liste et remplissage
		mList = (ListView) findViewById(R.id.listView);
		mList.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, dates));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v == buttonLeft || v == buttonCenter || v == buttonRight) {
			tvButtonClicked.setText(((Button) v).getText().toString());
		}

		if (v == buttonStart){
			MyTask task = new MyTask();
			task.execute();
		}
	}

	private class MyTask extends AsyncTask<Void, Integer, Void> {

		@Override
		protected void onPreExecute() {
			textViewState.setText(R.string.inprogress);
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(Void result) {
			textViewState.setText(R.string.stopped);
			textViewProgression.setText("0%");
			super.onPostExecute(result);
		}

		@Override
		protected Void doInBackground(Void... params) {
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				publishProgress(i);
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			int progression = values[0];
			progression++;
			textViewProgression.setText(progression + "0%");
			super.onProgressUpdate(values);
		}

	}

}
