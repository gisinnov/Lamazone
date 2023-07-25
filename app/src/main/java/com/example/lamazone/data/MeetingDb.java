package com.example.lamazone.data;

import com.example.lamazone.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MeetingDb {

    private static final List<Meeting> meetings = new ArrayList<>();

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        meetings.add(new Meeting("Alice", calendar.getTime(), calendar.getTime(), "Room 1", "Project meeting", Arrays.asList("alice@example.com", "bob@example.com")));

        calendar.set(Calendar.HOUR_OF_DAY, 14);
        meetings.add(new Meeting("Bob", calendar.getTime(), calendar.getTime(), "Room 2", "Planning meeting", Arrays.asList("bob@example.com", "charlie@example.com")));

        calendar.set(Calendar.HOUR_OF_DAY, 16);
        meetings.add(new Meeting("Charlie", calendar.getTime(), calendar.getTime(), "Room 3", "Review meeting", Arrays.asList("charlie@example.com", "alice@example.com")));

        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 30);
        meetings.add(new Meeting("David", calendar.getTime(), calendar.getTime(), "Room 4", "Design meeting", Arrays.asList("david@example.com", "eve@example.com")));

        calendar.set(Calendar.HOUR_OF_DAY, 15);
        meetings.add(new Meeting("Eve", calendar.getTime(), calendar.getTime(), "Room 5", "Strategy meeting", Arrays.asList("eve@example.com", "frank@example.com")));

        calendar.set(Calendar.HOUR_OF_DAY, 17);
        meetings.add(new Meeting("Frank", calendar.getTime(), calendar.getTime(), "Room 6", "Wrap-up meeting", Arrays.asList("frank@example.com", "alice@example.com")));
    }

    public static List<Meeting> getMeetings() {
        return new ArrayList<>(meetings);
    }
}
