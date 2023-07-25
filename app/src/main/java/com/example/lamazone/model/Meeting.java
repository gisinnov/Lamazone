package com.example.lamazone.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Meeting implements Serializable {

    private String organizerName;
    private Date meetingStartTime;
    private Date meetingEndTime;
    private String meetingPlace;
    private String meetingSubject;
    private List<String> participantEmails;

    public Meeting(String organizerName, Date meetingStartTime, Date meetingEndTime, String meetingPlace, String meetingSubject, List<String> participantEmails) {
        this.organizerName = organizerName;
        this.meetingStartTime = meetingStartTime;
        this.meetingEndTime = meetingEndTime;
        this.meetingPlace = meetingPlace;
        this.meetingSubject = meetingSubject;
        this.participantEmails = participantEmails;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public Date getMeetingStartTime() {
        return meetingStartTime;
    }

    public void setMeetingStartTime(Date meetingStartTime) {
        this.meetingStartTime = meetingStartTime;
    }

    public Date getMeetingEndTime() {
        return meetingEndTime;
    }

    public void setMeetingEndTime(Date meetingEndTime) {
        this.meetingEndTime = meetingEndTime;
    }

    public String getMeetingPlace() {
        return meetingPlace;
    }

    public void setMeetingPlace(String meetingPlace) {
        this.meetingPlace = meetingPlace;
    }

    public String getMeetingSubject() {
        return meetingSubject;
    }

    public void setMeetingSubject(String meetingSubject) {
        this.meetingSubject = meetingSubject;
    }

    public List<String> getParticipantEmails() {
        return participantEmails;
    }

    public void setParticipantEmails(List<String> participantEmails) {
        this.participantEmails = participantEmails;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "organizerName='" + organizerName + '\'' +
                ", meetingStartTime=" + meetingStartTime +
                ", meetingEndTime=" + meetingEndTime +
                ", meetingPlace='" + meetingPlace + '\'' +
                ", meetingSubject='" + meetingSubject + '\'' +
                ", participantEmails=" + participantEmails +
                '}';
    }
}
