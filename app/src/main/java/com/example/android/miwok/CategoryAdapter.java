package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Thinkpad on 5/1/2017.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    public CategoryAdapter(FragmentManager fm){
        super(fm);
    }
    private String[] titles = new String[] {"Numbers","Family","Colors","Phrases"};
    @Override
    public Fragment getItem(int position) {
        Fragment returnFragment = new Fragment();
        switch (position) {
            case 0:
                returnFragment = new NumbersFragment();
                break;
            case 1:
                returnFragment = new FamilyFragment();
                break;
            case 2:
                returnFragment = new ColorsFragment();
                break;
            case 3:
                returnFragment = new PhrasesFragment();
                break;

        }
        return returnFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return 4;
    }

}
