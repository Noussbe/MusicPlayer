package com.dam2022.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    /** Ajout automatique de la lecture */

    MediaPlayer mediaPlayer = new MediaPlayer();
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /** Methode 1 : lecture automatique du son
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);
        mediaPlayer.start();
         */

        /** Methode 2 : lecture avec les boutons   *
         *
         */
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);
        volume();
        position();
    }


    private void volume()
    {
        SeekBar sbVolume = findViewById(R.id.sbVolume);
        /** Initialiser le manager en tant que service */
        AudioManager audiomanager = (AudioManager) getSystemService(AUDIO_SERVICE);

        /** Volume max du terminal */
        int volumeMax = audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // Valorisation de cette valeur au max de la seekBar
        sbVolume.setMax(volumeMax);

        int currentVolume = audiomanager.getStreamVolume((AudioManager.STREAM_MUSIC));
        /** Ajustement du curseur du volume */

        sbVolume.setProgress(currentVolume);

        sbVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i (TAG, "onProgressChanged: Volume = "+Integer.toString(progress));
                audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC, progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    // End Volume

    private void position() {
        SeekBar sbPosition = findViewById(R.id.sbPosition);

        /** Definir la valeur max de la seekbar */
        sbPosition.setMax(mediaPlayer.getDuration());
        /** Partie 1 : gestion du deplacement par l'utilisateur */

        sbPosition.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i(TAG, "Position dans le morceau :" +Integer.toString(progress));

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                pause(sbPosition);

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(sbPosition.getProgress());
                play(sbPosition);
            }
        });
        /** Partie 2 : gestion du deplacement du curseur par l'application */

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                /** Déplacement automatique */
                sbPosition.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0, 300);
    }

 @Override
    protected void onPause()
    {
        super.onPause();
        mediaPlayer.stop();
    }

    public void play(View view){
        mediaPlayer.start();
    }

    public void pause(View view){
        mediaPlayer.pause();
    }
}