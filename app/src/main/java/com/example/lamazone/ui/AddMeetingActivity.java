package com.example.lamazone.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lamazone.R;
import com.example.lamazone.data.MeetingRepository;
import com.example.lamazone.model.Meeting;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddMeetingActivity extends AppCompatActivity {

    private EditText organizerNameEditText;
    private EditText participantEmailsEditText;
    private EditText meetingSubjectEditText;
    private EditText meetingStartDateEditText;
    private EditText meetingStartTimeEditText;
    private EditText meetingEndDateEditText;
    private EditText meetingEndTimeEditText;
    private Spinner meetingPlaceSpinner;
    private Button addMeetingButton;

    private Calendar startCalendar = Calendar.getInstance();
    private Calendar endCalendar = Calendar.getInstance();

    private MeetingRepository meetingRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        meetingRepository = MeetingRepository.getInstance();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        organizerNameEditText = findViewById(R.id.organizerName);
        participantEmailsEditText = findViewById(R.id.participantEmails);
        meetingSubjectEditText = findViewById(R.id.meetingSubject);
        meetingStartDateEditText = findViewById(R.id.meetingStartDate);
        meetingStartTimeEditText = findViewById(R.id.meetingStartTime);
        meetingEndDateEditText = findViewById(R.id.meetingEndDate);
        meetingEndTimeEditText = findViewById(R.id.meetingEndTime);
        meetingPlaceSpinner = findViewById(R.id.meetingPlaceSpinner);

        // Créez un ArrayAdapter en utilisant room_array_without_all
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.room_array_without_all, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        meetingPlaceSpinner.setAdapter(adapter);

        addMeetingButton = findViewById(R.id.addMeetingButton);

        meetingStartDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(startCalendar, meetingStartDateEditText);
            }
        });

        meetingStartTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(startCalendar, meetingStartTimeEditText);
            }
        });



        meetingEndDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(endCalendar, meetingEndDateEditText);
            }
        });

        meetingEndTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(endCalendar, meetingEndTimeEditText);
            }
        });

        addMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String organizerName = organizerNameEditText.getText().toString();
                List<String> participantEmails = Arrays.asList(participantEmailsEditText.getText().toString().split(","));
                String meetingSubject = meetingSubjectEditText.getText().toString();
                Date meetingStartDate = startCalendar.getTime();
                Date meetingEndDate = endCalendar.getTime();
                String meetingPlace = getResources().getStringArray(R.array.room_array)[meetingPlaceSpinner.getSelectedItemPosition()];

                // Check if end date and time are earlier than start date and time
                if (meetingEndDate.before(meetingStartDate)) {
                    // Display an alert message
                    Toast.makeText(AddMeetingActivity.this, "La date et l'heure de fin de la réunion ne peut pas être antérieure à la date de début", Toast.LENGTH_SHORT).show();
                    return; // Exit the method and prevent further execution
                }

                // Pass organizerName to the Meeting constructor
                Meeting meeting = new Meeting(organizerName, meetingStartDate, meetingEndDate, meetingPlace, meetingSubject, participantEmails);
                meetingRepository.addMeeting(meeting);

                // Display a success message
                Toast.makeText(AddMeetingActivity.this, "Réunion enregistrée avec succès", Toast.LENGTH_SHORT).show();

                // Finish the activity
                finish();
            }
        });
    }

    private void showDatePickerDialog(final Calendar calendar, final EditText dateEditText) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateEditText(calendar, dateEditText);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void updateDateEditText(Calendar calendar, EditText dateEditText) {
        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.FRANCE);

        dateEditText.setText(sdf.format(calendar.getTime()));
    }

    private void showTimePickerDialog(final Calendar calendar, final EditText timeEditText) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                updateTimeEditText(calendar, timeEditText);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

        timePickerDialog.show();
    }

    private void updateTimeEditText(Calendar calendar, EditText timeEditText) {
        String timeFormat = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat, Locale.FRANCE);

        timeEditText.setText(sdf.format(calendar.getTime()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
