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


public class FamilyFragment extends Fragment {

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

        final MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        };

        final ArrayList<Word> familyWords = new ArrayList<>();
        familyWords.add (new Word("father","әpә", R.drawable.family_father, R.raw.family_father));
        familyWords.add (new Word("mother","әṭa", R.drawable.family_mother, R.raw.family_mother));
        familyWords.add (new Word("son","angsi", R.drawable.family_son, R.raw.family_son));
        familyWords.add (new Word("daughter","tune", R.drawable.family_daughter, R.raw.family_daughter));
        familyWords.add (new Word("older brother","taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        familyWords.add (new Word("younger brother","chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        familyWords.add (new Word("older sister","teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        familyWords.add (new Word("younger sister","kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        familyWords.add (new Word("grandmother","ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        familyWords.add (new Word("grandfather","paapa", R.drawable.family_grandfather, R.raw.family_grandfather));
        //  Verify the conents of the array by printing each array element.


        WordsAdapter itemsAdapter = new WordsAdapter(getActivity(), familyWords, R.color.category_family);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord = familyWords.get(i);
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
            Log.v("FamilyFragment", "The element at index "+ index2 + " is: " + familyWords.get(index2) );
        }
        return rootView;
    }


}
