package com.example.newuser.reportapplication;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReportStore {
    private static ReportStore sReportStatic;
    private List<Report> mReports;


    public static ReportStore get(Context context) {
        if (sReportStatic == null) {
            sReportStatic = new ReportStore(context);
        }
        return sReportStatic;
    }
    private ReportStore(Context context) {
        mReports = new ArrayList<>();
    }

    public void addReport(Report r) {
        mReports.add(r);
    }

    public List<Report> getReports() {
        return mReports;
    }

    public Report getReport(UUID id) {
        for(Report report: mReports) {
            if (report.getId().equals(id)) {
                return report;
            }
        }
        return null;
    }


}
