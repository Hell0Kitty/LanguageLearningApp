package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Thinkpad on 4/25/2017.
 */

public class WordsAdapter extends ArrayAdapter<Word> {
    public int colorId;
    public WordsAdapter(Context context, ArrayList<Word> wordList, int color) {
        super(context, 0, wordList);
        colorId = color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        final Word currentWord = getItem(position);
        int color = ContextCompat.getColor(getContext(), colorId);


//        LinearLayout linearLayout = (LinearLayout) listItemView.findViewById(R.id.linear_layout);
//
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                playMusic(currentWord.getAudioResourceId());
//            }
//        });

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultLanguage());
        defaultTextView.setBackgroundColor(color);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getMiwokLanguage());
        miwokTextView.setBackgroundColor(color);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);

        if(currentWord.hasImage())
        {   imageView.setImageResource(View.VISIBLE);
            imageView.setImageResource(currentWord.getImageResourceId());
        }
        else{
            imageView.setVisibility(View.GONE);
        }

        return listItemView;

}
//public void playMusic(int resourceId) {
//    MediaPlayer mediaPlayer = MediaPlayer.create(getContext(),resourceId);
//    mediaPlayer.start();
//    while(mediaPlayer.isPlaying()){}
//    mediaPlayer.release();
//}

}
