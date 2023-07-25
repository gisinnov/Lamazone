package com.example.lamazone.util;

import com.example.lamazone.model.Meeting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilterUtil {

    public static List<Meeting> filterByRoom(List<Meeting> meetings, String room) {
        if (room.equals("All")) {
            return meetings;
        }

        List<Meeting> filteredMeetings = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (meeting.getMeetingPlace().equals(room)) {
                filteredMeetings.add(meeting);
            }
        }
        return filteredMeetings;
    }

    public static List<Meeting> filterByDate(List<Meeting> meetings, Date date) {
        List<Meeting> filteredMeetings = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (meeting.getMeetingStartTime().equals(date)) {
                filteredMeetings.add(meeting);
            }
        }
        return filteredMeetings;
    }
}
