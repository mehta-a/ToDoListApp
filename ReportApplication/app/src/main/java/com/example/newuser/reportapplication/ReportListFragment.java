package com.example.newuser.reportapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class ReportListFragment extends Fragment {
    private RecyclerView mReportRecyclerView;
    private ReportAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_list, container, false);
        mReportRecyclerView = (RecyclerView) view.findViewById(R.id.report_recycler_view);
        mReportRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.fragment_report_list, menu);

    }

    private void updateUI() {
        ReportStore reportStore = ReportStore.get(getActivity());
        List<Report> reports = reportStore.getReports();

        if(mAdapter == null) {
            mAdapter = new ReportAdapter(reports);
            mReportRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item_new_report:
                Report report = new Report();
                ReportStore.get(getActivity()).addReport(report);
                Intent intent = ReportPagerActivity
                        .newIntent(getActivity(), report.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private class ReportHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Report mReport;
        private TextView mTitleTextView;
        private CheckBox mResolvedCheckBox;
        private TextView mDateTextView;

        public ReportHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_report_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_report_date_text_view);
            mResolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_report_resolved_check_box);

        }

        @Override
        public void onClick(View v) {
            Intent intent = ReportPagerActivity.newIntent(getActivity(), mReport.getId());
            startActivity(intent);
        }

        private void bindReport(Report report) {
            mReport = report;
            mTitleTextView.setText(mReport.getTitle().toString());
            mDateTextView.setText(mReport.getDate().toString());
            mResolvedCheckBox.setChecked(mReport.isResolved());
        }
    }

    private class ReportAdapter extends RecyclerView.Adapter<ReportHolder> {
        private List<Report> mReports;
        public ReportAdapter(List<Report> reports) {
            mReports = reports;
        }

        @Override
        public ReportHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.activity_report_list, parent, false);
            return new ReportHolder(view);
        }

        @Override
        public void onBindViewHolder(ReportHolder holder, int position) {
            Report report = mReports.get(position);
            holder.bindReport(report);
        }

        @Override
        public int getItemCount() {
            return mReports.size();
        }
    }

}