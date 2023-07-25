package com.example.lamazone.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.lamazone.R;
import com.example.lamazone.data.MeetingDb;
import com.example.lamazone.data.MeetingRepository;
import com.example.lamazone.model.Meeting;
import com.example.lamazone.util.FilterUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MeetingsAdapter meetingsAdapter;
    private MeetingRepository meetingRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_settings) {
                    FilterDialogFragment filterDialogFragment = new FilterDialogFragment();
                    filterDialogFragment.show(getSupportFragmentManager(), "filterDialog");
                    return true;
                }
                return false;
            }
        });


        // Set the title
        getSupportActionBar().setTitle(R.string.reunion_fragment_label);

        meetingRepository = MeetingRepository.getInstance();

        FloatingActionButton addMeetingButton = findViewById(R.id.fab);
        addMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMeetingActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        meetingsAdapter = new MeetingsAdapter(new ArrayList<>(), meetingRepository);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(meetingsAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshMeetingList();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        Drawable icon = menu.getItem(0).getIcon();
        if (icon != null) {
            icon = DrawableCompat.wrap(icon);
            DrawableCompat.setTint(icon, ContextCompat.getColor(this, R.color.white));
            menu.getItem(0).setIcon(icon);
        }

        return true;
    }

/*
   private void refreshMeetingList() {
        meetingsAdapter.updateMeetings(meetingRepository.getMeetings());
    }
     }*/



    private void refreshMeetingList() {
        meetingsAdapter.updateMeetings(MeetingDb.getMeetings());
    }


    public void updateMeetingList(List<Meeting> meetings) {
        meetingsAdapter.updateMeetings(meetings);
    }
}


