package com.example.lamazone.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import java.util.Date;
import java.util.List;
import java.util.Calendar;


import androidx.fragment.app.DialogFragment;

import com.example.lamazone.R;
import com.example.lamazone.data.MeetingRepository;
import com.example.lamazone.model.Meeting;
import com.example.lamazone.util.FilterUtil;

public class FilterDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_filter, null);

        final Spinner spinnerRoom = dialogView.findViewById(R.id.spinnerRoom);
        final DatePicker datePicker = dialogView.findViewById(R.id.datePicker);

        builder.setTitle("Filtre")
                .setView(dialogView)
                .setPositiveButton("Appliquer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedRoom = spinnerRoom.getSelectedItem().toString();
                        Date selectedDate = getDateFromDatePicker(datePicker);
                        List<Meeting> filteredMeetings = FilterUtil.filterByRoom(
                                MeetingRepository.getInstance().getMeetings(),
                                selectedRoom);
                        filteredMeetings = FilterUtil.filterByDate(filteredMeetings, selectedDate);
                        ((MainActivity) getActivity()).updateMeetingList(filteredMeetings);
                    }
                })
                .setNegativeButton("Annuler", null);

        return builder.create();
    }

    private Date getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
