package com.example.lamazone.ui;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lamazone.R;
import com.example.lamazone.model.Meeting;
import com.example.lamazone.data.MeetingRepository;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

public class MeetingsAdapter extends RecyclerView.Adapter<MeetingsAdapter.MeetingViewHolder> {

    private List<Meeting> meetings;
    private MeetingRepository meetingRepository;

    public MeetingsAdapter(List<Meeting> meetings, MeetingRepository meetingRepository) {
        this.meetings = meetings;
        this.meetingRepository = meetingRepository;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meeting, parent, false);
        return new MeetingViewHolder(view);
    }
/*
    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        final Meeting meeting = meetings.get(position);
        holder.bind(meeting);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    meetingRepository.deleteMeeting(meetings.get(adapterPosition));
                    meetings.remove(adapterPosition);
                    notifyItemRemoved(adapterPosition);
                }
            }
        });

        // Set random color circle for ImageView
        holder.imgList.setBackground(getRandomColorCircle());
    }
*/

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        final Meeting meeting = meetings.get(position);
        holder.bind(meeting);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    meetingRepository.deleteMeeting(meetings.get(adapterPosition));
                    meetings.remove(adapterPosition);
                    notifyItemRemoved(adapterPosition);
                    Toast.makeText(v.getContext(), "Réunion supprimée", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set predefined color circle for ImageView
        holder.imgList.setBackground(getPredefinedColorCircle(position));
    }



    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public void updateMeetings(List<Meeting> updatedMeetings) {
        this.meetings = updatedMeetings;
        notifyDataSetChanged();
    }
/*
    private ShapeDrawable getRandomColorCircle() {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

        ShapeDrawable oval = new ShapeDrawable(new OvalShape());
        oval.getPaint().setColor(color);
        return oval;
    }

      */

        // This function assigns a color from a predefined list in a cyclic manner
    private ShapeDrawable getPredefinedColorCircle(int position) {
        String[] colors = { "#eadad1", "#b4cdba", "#ccb4cd" };
        int color = Color.parseColor(colors[position % colors.length]);

        ShapeDrawable oval = new ShapeDrawable(new OvalShape());
        oval.getPaint().setColor(color);
        return oval;
    }


    class MeetingViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgList;
        private TextView organizerNameTextView;
        private TextView meetingPlaceTextView;
        private TextView meetingTimeTextView;
        private TextView participantEmailsTextView;
        private ImageButton btnDelete;

        MeetingViewHolder(@NonNull View itemView) {
            super(itemView);
            imgList = itemView.findViewById(R.id.imgList);
            organizerNameTextView = itemView.findViewById(R.id.organizerName);
            meetingPlaceTextView = itemView.findViewById(R.id.meetingPlace);
            meetingTimeTextView = itemView.findViewById(R.id.meetingTime);
            participantEmailsTextView = itemView.findViewById(R.id.participantEmails);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }

        void bind(Meeting meeting) {
            organizerNameTextView.setText(meeting.getOrganizerName());
            meetingPlaceTextView.setText(meeting.getMeetingPlace());

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            String formattedTime = dateFormat.format(meeting.getMeetingStartTime());
            meetingTimeTextView.setText(formattedTime);

            StringBuilder meetingParticipants = new StringBuilder();
            for (String participant : meeting.getParticipantEmails()) {
                meetingParticipants.append(participant).append(", ");
            }
            participantEmailsTextView.setText(meetingParticipants.toString());
        }
    }
}
