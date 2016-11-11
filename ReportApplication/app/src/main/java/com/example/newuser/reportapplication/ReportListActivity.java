package com.example.newuser.reportapplication;

import android.support.v4.app.Fragment;

public class ReportListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new ReportListFragment();
    }

}
