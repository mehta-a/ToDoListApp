package com.example.newuser.reportapplication;



import java.util.Date;
import java.util.UUID;

/**
 * Created by newuser on 11/11/16.
 */
public class Report {
    private UUID mID;
    private String mTitle;
    private Date mDate;
    private boolean mResolved;

    public Report() {
        mID = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public boolean isResolved() {
        return mResolved;
    }

    public void setResolved(boolean mResolved) {
        this.mResolved = mResolved;
    }
}
