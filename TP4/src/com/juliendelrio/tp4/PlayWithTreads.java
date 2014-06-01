package com.juliendelrio.tp4;

import android.widget.ProgressBar;
import android.widget.TextView;

public class PlayWithTreads {
	private TextView progressionTextView;
	private ProgressBar progressBar;

	public PlayWithTreads(TextView progressionTextView, ProgressBar progressBar) {
		super();
		this.progressionTextView = progressionTextView;
		this.progressBar = progressBar;
	}

	public void startCalcul() {
		if (progressionTextView != null)
			progressionTextView.setText("Calcul en cours");
	}

	public void doMyLongCalcul() {
		try {
			if (progressBar != null)
				progressBar.setProgress(0);
			Thread.sleep(10000);
			if (progressBar != null)
				progressBar.setProgress(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void doMyCalcul() {
		try {
			if (progressBar != null)
				progressBar.setProgress(0);
			Thread.sleep(500);
			if (progressBar != null)
				progressBar.setProgress(1);
			Thread.sleep(500);
			if (progressBar != null)
				progressBar.setProgress(2);
			Thread.sleep(500);
			if (progressBar != null)
				progressBar.setProgress(3);
			Thread.sleep(500);
			if (progressBar != null)
				progressBar.setProgress(4);
			Thread.sleep(500);
			if (progressBar != null)
				progressBar.setProgress(5);
			Thread.sleep(500);
			if (progressBar != null)
				progressBar.setProgress(6);
			Thread.sleep(500);
			if (progressBar != null)
				progressBar.setProgress(7);
			Thread.sleep(500);
			if (progressBar != null)
				progressBar.setProgress(8);
			Thread.sleep(500);
			if (progressBar != null)
				progressBar.setProgress(9);
			Thread.sleep(500);
			if (progressBar != null)
				progressBar.setProgress(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void endCalcul() {
		if (progressionTextView != null)
			progressionTextView.setText("Calcul stoppé");
	}
}
