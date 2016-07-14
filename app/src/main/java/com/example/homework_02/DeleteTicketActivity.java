package com.example.homework_02;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class DeleteTicketActivity extends AppCompatActivity {

    ArrayList<Ticket> ticketList;
    EditText editText_source;
    EditText editText_destination;
    EditText editText_name;
    EditText editText_deptDate;
    static EditText editText_deptTime;
    EditText editText_returnDate;
    static EditText editText_returnTime;
    RadioButton radioButton_roundTrip , radioButton_oneWay;

    Button button_selectTicket;
    Button button_delete;
    Button button_cancel;


    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_ticket);

        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_source = (EditText) findViewById(R.id.editText_source);
        editText_destination = (EditText) findViewById(R.id.editText_destination);
        editText_deptDate = (EditText) findViewById(R.id.editText_deptDate);
        editText_deptTime = (EditText) findViewById(R.id.editText_deptTime);
        radioButton_roundTrip =(RadioButton)findViewById(R.id.radioButton_roundTrip);
        radioButton_oneWay =(RadioButton)findViewById(R.id.radioButton_oneWay);
        editText_returnDate=(EditText)findViewById(R.id.editText_returnDate);
        editText_returnTime=(EditText)findViewById(R.id.editText_returnTime);

        editText_name.setKeyListener(null);
        editText_source.setKeyListener(null);
        editText_destination.setKeyListener(null);
        editText_deptDate.setKeyListener(null);
        editText_deptTime.setKeyListener(null);
        radioButton_roundTrip.setKeyListener(null);
        editText_returnDate.setKeyListener(null);
        editText_returnTime.setKeyListener(null);
        radioButton_oneWay.setKeyListener(null);

        if (getIntent().getExtras() != null) {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            ticketList = (ArrayList<Ticket>)
                    bundle.getSerializable(MainActivity.TICKET_LIST_KEY);
        }


        AlertDialog.Builder selectTicketBuilder = new AlertDialog.Builder(DeleteTicketActivity.this);
        final String[] ticketNames = MainActivity.getTicketNamesArray(ticketList);
        selectTicketBuilder.setTitle("Pick a Ticket")
                .setItems(ticketNames, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Ticket ticket = MainActivity.findTicketByName
                                (ticketNames[which], ticketList);
                        if (ticket != null) {
                            index=which;
                            editText_name.setText(ticket.getName());

                            editText_source.setText(CreateTicketActivity.cities[ticket.getSource()]);

                            editText_destination.setText(CreateTicketActivity.cities[ticket.getDestination()]);

                            editText_deptDate.setText
                                    (ticket.getDeptDate());

                            editText_deptTime.setText(ticket.getDeptTime());

                            if (ticket.isRoundTrip()) {
                                radioButton_roundTrip.setChecked(true);


                                findViewById(R.id.editText_returnDate).setVisibility(View.VISIBLE);
                                findViewById(R.id.editText_returnTime).setVisibility(View.VISIBLE);

                                editText_returnDate.setText
                                        (ticket.getReturnDate());
                                editText_returnTime.setText(ticket.getReturnTime());
                            } else {
                                radioButton_oneWay.setChecked(true);

                            }
                        } else {

                        }



                    }
                });

        final AlertDialog selectTicketBuilderAlert = selectTicketBuilder.create();

        button_selectTicket = (Button) findViewById(R.id.button_selectTicket);
        button_selectTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTicketBuilderAlert.show();
            }
        });



        button_delete = (Button) findViewById(R.id.button_delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ticketLinkedList.remove(index);
                finish();

            }
        });

        button_cancel=(Button)findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
