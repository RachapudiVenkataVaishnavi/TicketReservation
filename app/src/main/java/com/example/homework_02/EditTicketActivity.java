package com.example.homework_02;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.getInstance;


public class EditTicketActivity extends AppCompatActivity {
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
    private SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialogDept;
    private DatePickerDialog datePickerDialogReturn;

    boolean roundTrip=false;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ticket);

        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_source = (EditText) findViewById(R.id.editText_source);
        editText_destination = (EditText) findViewById(R.id.editText_destination);
        editText_deptDate = (EditText) findViewById(R.id.editText_deptDate);
        editText_deptTime = (EditText) findViewById(R.id.editText_deptTime);
        radioButton_roundTrip =(RadioButton)findViewById(R.id.radioButton_roundTrip);
        radioButton_oneWay =(RadioButton)findViewById(R.id.radioButton_oneWay);
        editText_returnDate=(EditText)findViewById(R.id.editText_returnDate);
        editText_returnTime=(EditText)findViewById(R.id.editText_returnTime);

        if (getIntent().getExtras() != null) {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            ticketList = (ArrayList<Ticket>)
                    bundle.getSerializable(MainActivity.TICKET_LIST_KEY);
        }







        editText_name.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence cs, int start,
                                               int end, Spanned spanned, int dStart, int dEnd) {
                        // TODO Auto-generated method stub
                        if (cs.equals("")) { // for backspace
                            return cs;
                        }
                        if (cs.toString().matches("[a-zA-Z ]+")) {
                            return cs;
                        }
                        return "";
                    }
                }
        });
        editText_name.setFilters(new InputFilter[] {new InputFilter.LengthFilter(20)});





        AlertDialog.Builder selectTicketBuilder = new AlertDialog.Builder(EditTicketActivity.this);
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




        AlertDialog.Builder sourceCitiesBuilder = new AlertDialog.Builder(EditTicketActivity.this);

        sourceCitiesBuilder.setTitle("Source")
                .setItems(CreateTicketActivity.cities, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        editText_source.setText(CreateTicketActivity.cities[which]);

                    }
                });

        final AlertDialog sourceCitiesAlert = sourceCitiesBuilder.create();

        AlertDialog.Builder destinationCitiesBuilder = new AlertDialog.Builder(EditTicketActivity.this);

        destinationCitiesBuilder.setTitle("Source")
                .setItems(CreateTicketActivity.cities, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        editText_destination.setText(CreateTicketActivity.cities[which]);


                    }
                });

        final AlertDialog destinationCitiesAlert = destinationCitiesBuilder.create();


        editText_source = (EditText) findViewById(R.id.editText_source);
        editText_source.setKeyListener(null);
        editText_source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sourceCitiesAlert.show();
            }
        });



        editText_destination = (EditText) findViewById(R.id.editText_destination);
        editText_destination.setKeyListener(null);
        editText_destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destinationCitiesAlert.show();


            }
        });



        RadioGroup rg = (RadioGroup) findViewById(R.id.RadioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (group.getCheckedRadioButtonId() == R.id.radioButton_roundTrip) {
                    findViewById(R.id.editText_returnTime).setVisibility(View.VISIBLE);
                    findViewById(R.id.editText_returnDate).setVisibility(View.VISIBLE);

                } else if (group.getCheckedRadioButtonId() == R.id.radioButton_oneWay) {
                    findViewById(R.id.editText_returnDate).setVisibility(View.INVISIBLE);
                    findViewById(R.id.editText_returnTime).setVisibility(View.INVISIBLE);

                }

            }
        });



        editText_deptDate = (EditText) findViewById(R.id.editText_deptDate);
        editText_returnDate = (EditText) findViewById(R.id.editText_returnDate);
        editText_deptTime = (EditText) findViewById(R.id.editText_deptTime);
        editText_returnTime = (EditText) findViewById(R.id.editText_returnTime);

        dateFormatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

        setDateTimeField();
        editText_deptDate.setKeyListener(null);
        editText_deptDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialogDept.show();
            }
        });
        editText_returnDate.setKeyListener(null);
        editText_returnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialogReturn.show();
            }
        });
        editText_deptTime.setKeyListener(null);
        editText_deptTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogDepart(v);
            }
        });
        editText_returnTime.setKeyListener(null);
        editText_returnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialogReturn(v);
            }
        });







        Button button_save= (Button)findViewById(R.id.button_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_name=(EditText)findViewById(R.id.editText_name);
                editText_source=(EditText)findViewById(R.id.editText_source);
                editText_destination=(EditText) findViewById(R.id.editText_destination);
                editText_deptDate = (EditText) findViewById(R.id.editText_deptDate);
                editText_returnDate = (EditText) findViewById(R.id.editText_returnDate);
                editText_deptTime = (EditText) findViewById(R.id.editText_deptTime);
                editText_returnTime = (EditText) findViewById(R.id.editText_returnTime);
                RadioButton radioButton_roundTrip =(RadioButton)findViewById(R.id.radioButton_roundTrip);
                RadioButton radioButton_oneWay =(RadioButton)findViewById(R.id.radioButton_oneWay);
                Date deptDate = null;
                Date returnDate = null;


                try {

                    deptDate = dateFormatter.parse(editText_deptDate.getText().toString());

                    returnDate = dateFormatter.parse(editText_returnDate.getText().toString());

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if(editText_name.getText().toString()==null || editText_name.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Name", Toast.LENGTH_LONG).show();
                    editText_name.requestFocus();
                    return;

                }
                else if(editText_source.getText().toString()==null || editText_source.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please Select Source", Toast.LENGTH_LONG).show();
                    editText_source.requestFocus();
                    return;

                }
                else  if(editText_destination.getText().toString()==null || editText_destination.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please Select Destination", Toast.LENGTH_LONG).show();
                    editText_destination.requestFocus();
                    return;

                }else if (editText_source.getText().toString().equals(editText_destination.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Source And Destination Should Not Be Same", Toast.LENGTH_LONG).show();
                    editText_destination.setText("");
                    return;
                }
                else if(editText_deptDate.getText().toString()==null || editText_deptDate.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Departure Date", Toast.LENGTH_LONG).show();
                    editText_deptDate.requestFocus();
                    return;

                }
                else if(editText_deptTime.getText().toString()==null || editText_deptTime.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please Enter Departure Time", Toast.LENGTH_LONG).show();
                    editText_deptTime.requestFocus();
                    return;

                } else if(radioButton_roundTrip.isChecked())
                {
                    roundTrip=true;

                    if(editText_returnDate.getText().toString()==null || editText_returnDate.getText().toString().equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Please Enter Return Date", Toast.LENGTH_LONG).show();
                        editText_returnDate.requestFocus();
                        return;

                    }
                    if(editText_returnTime.getText().toString()==null || editText_returnTime.getText().toString().equals(""))
                    {
                        Toast.makeText(getApplicationContext(), "Please Enter Return Time", Toast.LENGTH_LONG).show();
                        editText_returnTime.requestFocus();
                        return;

                    }
                    if(deptDate.compareTo(returnDate)>0 || deptDate.compareTo(returnDate)==0)
                    {
                        Toast.makeText(getApplicationContext(), "Returning Date should be after Departure Date", Toast.LENGTH_LONG).show();
                        editText_returnDate.setText("");
                        editText_returnDate.requestFocus();
                        return;

                    }



                } if(radioButton_oneWay.isChecked())
                {
                    roundTrip=false;
                    editText_returnDate.setText("");
                    editText_returnTime.setText("");
                }










                Ticket ticket = new Ticket(
                        editText_name.getText().toString(),
                        editText_deptTime.getText().toString(),
                        editText_returnTime.getText().toString(),
                        Arrays.asList(CreateTicketActivity.cities).indexOf(editText_source.getText().toString()),
                        Arrays.asList(CreateTicketActivity.cities).indexOf(editText_destination.getText().toString()),
                        roundTrip,

                        editText_deptDate.getText().toString(),
                        editText_returnDate.getText().toString());
                MainActivity.ticketLinkedList.set(index, ticket);
                finish();








            }
        });

















    }


    private void showTimePickerDialogDepart(View v) {
        DialogFragment newFragment = new EditTimePickerFragmentDepart();

        FragmentManager fm=getFragmentManager();

        newFragment.show(fm,"timePicker");



    }

    private void showTimePickerDialogReturn(View v) {
        DialogFragment newFragment = new EditTimePickerFragmentReturn();

        FragmentManager fm=getFragmentManager();

        newFragment.show(fm,"timePicker");



    }

    private void setDateTimeField() {

        Calendar newCalendar = getInstance();

        datePickerDialogDept = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editText_deptDate.setText(dateFormatter.format(newDate.getTime()));

            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialogReturn = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                editText_returnDate.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));




    }











}
