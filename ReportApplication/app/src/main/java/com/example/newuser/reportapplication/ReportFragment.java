package com.example.newuser.reportapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

public class ReportFragment  extends Fragment{
    private static final String ARG_REPORT_ID = "report_id";
    private static final String DIALOG_DATE = "DialogDate";

    private Report mReport;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mResolvedCheckbox;

    public static ReportFragment newInstance(UUID reportId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_REPORT_ID, reportId);

        ReportFragment fragment = new ReportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID reportId = (UUID) getArguments().getSerializable(ARG_REPORT_ID);
        mReport = ReportStore.get(getActivity()).getReport(reportId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_report, container, false);
        mTitleField = (EditText) v.findViewById(R.id.report_title);
        mTitleField.setText(mReport.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mReport.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = (Button) v.findViewById(R.id.report_date);
        mDateButton.setText(mReport.getDate().toString());

        mDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mResolvedCheckbox = (CheckBox) v.findViewById(R.id.report_resolved);
        mResolvedCheckbox.setChecked(mReport.isResolved());
        mResolvedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mReport.setResolved(isChecked);
            }
        });
        return v;

    }
}

