package com.example.homework_02;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ViewTicketActivity extends AppCompatActivity {

    ArrayList<Ticket> ticketList;
    EditText editText_source;
    EditText editText_destination;
    EditText editText_name;
    EditText editText_deptDate;
    static EditText editText_deptTime;
    EditText editText_returnDate;
    static EditText editText_returnTime;
    RadioButton radioButton_roundTrip, radioButton_oneWay;

    Button button_finish;

    boolean roundTrip = false;

    ImageView imageView_leftArrow;
    ImageView imageView_rightArrow;
    ImageView imageView_doubleLeftArrow;
    ImageView imageView_doubleRightArrow;

    int  current = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ticket);


        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_source = (EditText) findViewById(R.id.editText_source);
        editText_destination = (EditText) findViewById(R.id.editText_destination);
        editText_deptDate = (EditText) findViewById(R.id.editText_deptDate);
        editText_deptTime = (EditText) findViewById(R.id.editText_deptTime);
        radioButton_roundTrip = (RadioButton) findViewById(R.id.radioButton_roundTrip);
        radioButton_oneWay = (RadioButton) findViewById(R.id.radioButton_oneWay);
        editText_returnDate = (EditText) findViewById(R.id.editText_returnDate);
        editText_returnTime = (EditText) findViewById(R.id.editText_returnTime);
        imageView_leftArrow=(ImageView)findViewById(R.id.imageView_leftArrow);
        imageView_rightArrow=(ImageView)findViewById(R.id.imageView_rightArrow);
        imageView_doubleLeftArrow =(ImageView)findViewById(R.id.imageView_doubleLeftArrow);
                imageView_doubleRightArrow   =(ImageView)findViewById(R.id.imageView_doubleRightArrow);

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

        Ticket ticket = MainActivity.findTicketByIndex(current, ticketList);

        if (ticket != null) {

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


        }


        imageView_rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_returnDate.setText("");
                editText_returnTime.setText("");
                findViewById(R.id.editText_returnDate).setVisibility(View.INVISIBLE);
                findViewById(R.id.editText_returnTime).setVisibility(View.INVISIBLE);


                ArrayList<Ticket> ticketList=null;
                if (getIntent().getExtras() != null) {
                    Intent intent = getIntent();
                    Bundle bundle = intent.getExtras();
                    ticketList = (ArrayList<Ticket>)
                            bundle.getSerializable(MainActivity.TICKET_LIST_KEY);
                }
                if(current == ticketList.size() - 1)
                    current = 0;
                else
                    current++;

                Ticket ticket = MainActivity.findTicketByIndex(current, ticketList);


                if (ticket != null) {

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


                }


            }
        });


        imageView_leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editText_returnDate.setText("");
                editText_returnTime.setText("");
                findViewById(R.id.editText_returnDate).setVisibility(View.INVISIBLE);
                findViewById(R.id.editText_returnTime).setVisibility(View.INVISIBLE);



                ArrayList<Ticket> ticketList=null;
                if (getIntent().getExtras() != null) {
                    Intent intent = getIntent();
                    Bundle bundle = intent.getExtras();
                    ticketList = (ArrayList<Ticket>)
                            bundle.getSerializable(MainActivity.TICKET_LIST_KEY);
                }
                if(current == 0)
                    current = ticketList.size() - 1;
                else
                    current--;

                Ticket ticket = MainActivity.findTicketByIndex(current, ticketList);


                if (ticket != null) {

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


                }





            }
        });

        imageView_doubleLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editText_returnDate.setText("");
                editText_returnTime.setText("");
                findViewById(R.id.editText_returnDate).setVisibility(View.INVISIBLE);
                findViewById(R.id.editText_returnTime).setVisibility(View.INVISIBLE);


                ArrayList<Ticket> ticketList=null;
                if (getIntent().getExtras() != null) {
                    Intent intent = getIntent();
                    Bundle bundle = intent.getExtras();
                    ticketList = (ArrayList<Ticket>)
                            bundle.getSerializable(MainActivity.TICKET_LIST_KEY);
                }
                current = 0;

                Ticket ticket = MainActivity.findTicketByIndex(current, ticketList);

                if (ticket != null) {

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


                }

            }
        });

        imageView_doubleRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editText_returnDate.setText("");
                editText_returnTime.setText("");
                findViewById(R.id.editText_returnDate).setVisibility(View.INVISIBLE);
                findViewById(R.id.editText_returnTime).setVisibility(View.INVISIBLE);


                ArrayList<Ticket> ticketList=null;
                if (getIntent().getExtras() != null) {
                    Intent intent = getIntent();
                    Bundle bundle = intent.getExtras();
                    ticketList = (ArrayList<Ticket>)
                            bundle.getSerializable(MainActivity.TICKET_LIST_KEY);
                }
                current = ticketList.size()-1;

                Ticket ticket = MainActivity.findTicketByIndex(current, ticketList);

                if (ticket != null) {

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


                }




            }
        });


        button_finish=(Button)findViewById(R.id.button_finish);
        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}