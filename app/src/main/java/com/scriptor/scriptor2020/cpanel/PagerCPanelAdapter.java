package com.scriptor.scriptor2020.cpanel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.scriptor.scriptor2020.cpanel.tabs.CPScheduleAdminQuotes;
import com.scriptor.scriptor2020.cpanel.tabs.users.CPUsers;
import com.scriptor.scriptor2020.cpanel.tabs.feedback.CPUsersFeedBacks;
import com.scriptor.scriptor2020.cpanel.tabs.user_novels.CPUsersNovels;
import com.scriptor.scriptor2020.cpanel.tabs.user_quotes.CPUsersQuotes;
import com.scriptor.scriptor2020.cpanel.tabs.short_stories.CPUsersShortStories;

public class PagerCPanelAdapter extends FragmentPagerAdapter {


    final private Fragment frag1 = new CPScheduleAdminQuotes();
    final private Fragment frag2 = new CPUsersQuotes();
    final private Fragment frag3 = new CPUsersNovels();
    final private Fragment frag4 = new CPUsersShortStories();
    final private Fragment frag5 = new CPUsersFeedBacks();
    final private Fragment frag6 = new CPUsers();



    public PagerCPanelAdapter(@NonNull FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    private int tabCount;

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
            case 5:
                return frag6;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
