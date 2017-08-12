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


public class PhrasesFragment extends Fragment {
    private MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK )
                    {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    }
                    else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        mediaPlayer.start();
                    }
                    else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaPlayer();
                    }
                }
            };

    private final MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            };

    public PhrasesFragment(){}
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

        View rootView = inflater.inflate(R.layout.words_list, container, false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<Word> phrasesWords = new ArrayList<>();
        phrasesWords.add (new Word("Where are you going?","minto wuksus", R.raw.phrase_where_are_you_going));
        phrasesWords.add (new Word("What is your name?","tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        phrasesWords.add (new Word("My name is...","oyaaset...", R.raw.phrase_my_name_is));
        phrasesWords.add (new Word("How are you feeling?","michәksәs?", R.raw.phrase_how_are_you_feeling));
        phrasesWords.add (new Word("I am feeling good.","kuchi achit", R.raw.phrase_im_feeling_good));
        phrasesWords.add (new Word("Are you coming?","әәnәs'aa?", R.raw.phrase_are_you_coming));
        phrasesWords.add (new Word("Yes I'm coming.","hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        phrasesWords.add (new Word("I'm coming.","әәnәm", R.raw.phrase_im_coming));
        phrasesWords.add (new Word("Let's go.","yoowutis", R.raw.phrase_lets_go));
        phrasesWords.add (new Word("Come here.","әnni'nem", R.raw.phrase_come_here));
        //  Verify the conents of the array by printing each array element.


        WordsAdapter itemsAdapter = new WordsAdapter(getActivity(), phrasesWords, R.color.category_phrases);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord = phrasesWords.get(i);
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

        for (int index2 = 0; index2 < 10 ; ++ index2 )
        {
            Log.v("PhrasesFragment", "The element at index "+ index2 + " is: " + phrasesWords.get(index2) );
        }
        return rootView;
    }

}


