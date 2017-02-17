package com.utilities.joechen.testspeechpitch;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;
import java.lang.System;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener,
	SeekBar.OnSeekBarChangeListener, Button.OnClickListener
{
	private static final String			TAG = "TestSpeechPitch";
	private static final String[]		CH  =
		{
			"現在正在測試語音的語調",
			"今天天氣不錯，適合出去走走。",
			"我不想再這樣搞下去了！",
			"公司什麼時候幫我加薪啊？",
			"機器人是未來的明星產業。"
		};
	private static final String[]			EN  =
		{
			"I am trying to say something now.",
			"I would like to ask you do not torture me.",
			"The news conference was not scheduled until Thursday morning.",
			"Please stop talking and pay attention to work.",
			"Just to be the next to be with you"
		};
	private TextToSpeech				m_TTS;
	private EditText					m_etPitchValue;
	private SeekBar						m_sbPitchValue;
	private Button						m_btnSayChinese;
	private Button						m_btnSayEnglish;

    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		m_etPitchValue = (EditText)findViewById(R.id.editTextPitchValue);
		m_sbPitchValue = (SeekBar)findViewById(R.id.seekBarPitchValue);
		m_btnSayChinese = (Button)findViewById(R.id.buttonSayChinese);
		m_btnSayEnglish = (Button)findViewById(R.id.buttonSayEnglish);

		m_TTS = new TextToSpeech(getApplicationContext(), this);
		m_sbPitchValue.setOnSeekBarChangeListener(this);
		m_sbPitchValue.setProgress(50);
		m_btnSayChinese.setOnClickListener(this);
		m_btnSayEnglish.setOnClickListener(this);
		//if(m_TTS.isLanguageAvailable(Locale.TAIWAN)==TextToSpeech.LANG_NOT_SUPPORTED)
		//{
		//	m_btnSayChinese.setVisibility(View.INVISIBLE);
		//	Toast.makeText(getApplicationContext(), R.string.no_zh_tw, Toast.LENGTH_LONG);
		//	Log.d(TAG, "Does not support Chinese voice");
		//}
    }

	@Override
	public void onInit(int i)
	{

	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress_value, boolean b)
	{
		// Log.d(TAG, "i = " + i);
		int pv= progress_value == 0 ? 1:progress_value;
		float value = (float)pv / 50.0f;
		String str=String.valueOf(value);
		m_etPitchValue.setText(str);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar)
	{

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar)
	{

	}

	@Override
	public void onClick(View view)
	{
		if(view.getId() == R.id.buttonSayChinese)
		{
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis());
			int value = rand.nextInt(CH.length);
			m_TTS.setLanguage(Locale.TAIWAN);
			m_TTS.setPitch(Float.parseFloat(m_etPitchValue.getText().toString()));
			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
			{
				m_TTS.speak(CH[value], TextToSpeech.QUEUE_FLUSH, null, null);
			}
			else
			{
				m_TTS.speak(CH[value], TextToSpeech.QUEUE_FLUSH, null);
			}
		}
		else if(view.getId() == R.id.buttonSayEnglish)
		{
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis());
			int value = rand.nextInt(EN.length);
			m_TTS.setLanguage(Locale.US);
			m_TTS.setPitch(Float.parseFloat(m_etPitchValue.getText().toString()));
			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
			{
				m_TTS.speak(EN[value], TextToSpeech.QUEUE_FLUSH, null, null);
			}
			else
			{
				m_TTS.speak(EN[value], TextToSpeech.QUEUE_FLUSH, null);
			}
		}
	}
}
