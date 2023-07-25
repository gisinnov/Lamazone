package com.example.lamazone.data;

import com.example.lamazone.model.Meeting;
import java.util.ArrayList;
import java.util.List;

public class MeetingRepository implements MeetingApiService {

    private static MeetingRepository instance;
    private List<Meeting> meetings = new ArrayList<>();

    private MeetingRepository() {}

    public static synchronized MeetingRepository getInstance() {
        if (instance == null) {
            instance = new MeetingRepository();
        }
        return instance;
    }

    @Override
    public List<Meeting> getMeetings() {
        return new ArrayList<>(meetings);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }
}
