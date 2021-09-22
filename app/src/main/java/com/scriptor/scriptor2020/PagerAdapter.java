package com.scriptor.scriptor2020;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.scriptor.scriptor2020.sections.novels.Novels;
import com.scriptor.scriptor2020.sections.poems.Poems;
import com.scriptor.scriptor2020.sections.quotes.Quotes;
import com.scriptor.scriptor2020.sections.short_stories.ShortStories;
import com.scriptor.scriptor2020.sections.Writing_Basics;

public class PagerAdapter extends FragmentPagerAdapter {

    final private Fragment frag3 = new Novels();
    final private Fragment frag1 = new Quotes();
    final private Fragment frag4 = new ShortStories();
    final private Fragment frag2 = new Writing_Basics();
    final private Fragment frag5 = new Poems();

    private int tabCount;


    public PagerAdapter(@NonNull FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return frag1;
            case 1:
                return frag2;
            case 2:
                return frag3;
            case 3:
                return frag4;
            case 4:
                return frag5;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}
