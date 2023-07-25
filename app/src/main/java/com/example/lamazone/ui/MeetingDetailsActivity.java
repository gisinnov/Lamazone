package com.example.lamazone.ui;
import com.example.lamazone.model.Meeting;

import com.example.lamazone.R;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MeetingDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MEETING = "com.example.lamazone.ui.EXTRA_MEETING";

    private TextView meetingSubjectTextView;
    private TextView organizerNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);

        meetingSubjectTextView = findViewById(R.id.meetingSubject);
        organizerNameTextView = findViewById(R.id.organizerName);

        Meeting meeting = (Meeting) getIntent().getSerializableExtra(EXTRA_MEETING);
        meetingSubjectTextView.setText(meeting.getMeetingSubject());
        organizerNameTextView.setText(meeting.getOrganizerName());

        // Set other fields as needed
    }
}
