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

public class NumbersFragment extends Fragment {

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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.words_list, container, false);
        final MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        };

        mAudioManager = (AudioManager) this.getActivity().getSystemService(Context.AUDIO_SERVICE);
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


        final ArrayList<Word> numbersWords = new ArrayList<>();
        numbersWords.add (new Word("one","lutti", R.drawable.number_one, R.raw.number_one));
        numbersWords.add (new Word("two","otiiko", R.drawable.number_two, R.raw.number_two));
        numbersWords.add (new Word("three","tolookosu", R.drawable.number_three, R.raw.number_three));
        numbersWords.add (new Word("four","oyyisa", R.drawable.number_four, R.raw.number_four));
        numbersWords.add (new Word("five","massokka", R.drawable.number_five, R.raw.number_five));
        numbersWords.add (new Word("six","temmokka", R.drawable.number_six, R.raw.number_six));
        numbersWords.add (new Word("seven","kenakaku", R.drawable.number_seven, R.raw.number_seven));
        numbersWords.add (new Word("eight","kawinta", R.drawable.number_eight, R.raw.number_eight));
        numbersWords.add (new Word("nine","wo'a", R.drawable.number_nine, R.raw.number_nine));
        numbersWords.add (new Word("ten","na'accha", R.drawable.number_ten, R.raw.number_ten));
        //  Verify the conents of the array by printing each array element.


        WordsAdapter itemsAdapter = new WordsAdapter(getActivity(), numbersWords, R.color.category_numbers);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word currentWord = numbersWords.get(i);

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
            Log.v("NumbersFragment", "The element at index "+ index2 + " is: " + numbersWords.get(index2) );
        }
        return rootView;
    }


}
