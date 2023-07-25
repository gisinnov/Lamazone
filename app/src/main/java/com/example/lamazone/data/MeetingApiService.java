package com.example.lamazone.data;

import com.example.lamazone.model.Meeting;

import java.util.List;

public interface MeetingApiService {

    /**
     * Get all Meetings
     *
     * @return list of meetings
     */
    List<Meeting> getMeetings();

    /**
     * Add a meeting
     *
     * @param meeting to be added
     */
    void addMeeting(Meeting meeting);

    /**
     * Delete a meeting
     *
     * @param meeting to be deleted
     */
    void deleteMeeting(Meeting meeting);
}
