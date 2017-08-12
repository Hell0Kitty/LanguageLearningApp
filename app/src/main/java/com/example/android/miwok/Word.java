package com.example.android.miwok;

/**
 * Created by Thinkpad on 4/25/2017.
 */
//mark
public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    public int mImageResourceId = NO_IMAGE_RESOURCE;
    public int mAudioResourceId;
    private static final int NO_IMAGE_RESOURCE = -1;

    public String getDefaultLanguage() { return mDefaultTranslation;}

    public int getAudioResourceId(){
        return mAudioResourceId;
    }

    public String getMiwokLanguage() { return mMiwokTranslation;}
    public int getImageResourceId(){
        return mImageResourceId;
    }
    public Word(String defaultTranslation, String MiwokTranslation, int AudioResourceId)
    {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = MiwokTranslation;
        mAudioResourceId = AudioResourceId;
    }

    public Word(String defaultTranslation, String MiwokTranslation, int ImageResourceId, int AudioResourceId)
    {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = MiwokTranslation;
        mImageResourceId = ImageResourceId;
        mAudioResourceId = AudioResourceId;
    }

    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_RESOURCE;
    }
}
