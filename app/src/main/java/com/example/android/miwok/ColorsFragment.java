package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class ColorsFragment extends Fragment {

    private MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        };

        View rootView = inflater.inflate(R.layout.words_list, container, false);
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT
                        || focusChange == mAudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                }
                else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                    mediaPlayer.start();
                }
                else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                    releaseMediaPlayer();
                }

            }
        };

        final ArrayList<Word> colorsWords= new ArrayList<Word>();
        colorsWords.add (new Word("red","weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        colorsWords.add (new Word("green","chokokki", R.drawable.color_green, R.raw.color_green));
        colorsWords.add (new Word("brown","ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        colorsWords.add (new Word("gray","ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        colorsWords.add (new Word("black","kululli", R.drawable.color_black, R.raw.color_black));
        colorsWords.add (new Word("white","kelelli", R.drawable.color_white, R.raw.color_white));
        colorsWords.add (new Word("dusty yellow","ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colorsWords.add (new Word("mustard yellow","chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        //  Verify the conents of the array by printing each array element.


        WordsAdapter itemsAdapter = new WordsAdapter(getActivity(), colorsWords, R.color.category_colors);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord = colorsWords.get(i);
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    releaseMediaPlayer();
                    mediaPlayer = MediaPlayer.create(getActivity(), currentWord.getAudioResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }

            }
        });

        for (int index2 = 0; index2 < 8 ; ++ index2 )
        {
            Log.v("ColorsFragment", "The element at index "+ index2 + " is: " + colorsWords.get(index2) );
        }
        return rootView;
    }

}
