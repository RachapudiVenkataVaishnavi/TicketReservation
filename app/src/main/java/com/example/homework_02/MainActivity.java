package com.example.homework_02;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;


public class MainActivity extends AppCompatActivity {

    Button createTicket, editTicket, deleteTicket, viewTicket, finish;
    TextView emergencyCallNumber;

    final static LinkedList<Ticket> ticketLinkedList = new LinkedList<Ticket>();
    final static String TICKET_LIST_KEY = "ticket list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(this.getIntent().getExtras() != null) {
            Ticket ticket = (Ticket) this.getIntent().getExtras().
                    getSerializable(CreateTicketActivity.TICKET_KEY);
            ticketLinkedList.add(ticket);

            Toast.makeText(MainActivity.this, "Ticket added", Toast.LENGTH_LONG).show();
        }


        emergencyCallNumber = (TextView) findViewById(R.id.textView_custCare);
        emergencyCallNumber.setPaintFlags
                (emergencyCallNumber.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        emergencyCallNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String PhoneNum="tel:9999999999";
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+Uri.encode(PhoneNum.trim())));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(callIntent);
                try {

                    startActivity(callIntent);
                } catch (SecurityException e) {

                }


            }
        });

        createTicket = (Button)findViewById(R.id.button_createTicket);
        createTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CreateTicketActivity.class);
                startActivity(intent);
            }
        });

        editTicket = (Button) findViewById(R.id.button_editTicket);
        editTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ticketLinkedList.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, EditTicketActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(TICKET_LIST_KEY, ticketLinkedList);
                    intent.putExtras(bundle);
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "There are no tickets to edit!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        deleteTicket = (Button) findViewById(R.id.button_deleteTicket);
        deleteTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ticketLinkedList.isEmpty()){
                    Intent intent = new Intent(MainActivity.this, DeleteTicketActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(TICKET_LIST_KEY, ticketLinkedList);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "There are no tickets to delete!",
                            Toast.LENGTH_LONG).show();
                }


            }
        });

        viewTicket = (Button) findViewById(R.id.button_viewTicket);
        viewTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(! ticketLinkedList.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, ViewTicketActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(TICKET_LIST_KEY, ticketLinkedList);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "There are no tickets to view!",
                            Toast.LENGTH_LONG).show();
                }



            }
        });

        finish = (Button) findViewById(R.id.button_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    //For generating AlertDialogs of ticket names, used in edit/delete ticket.
    public static String[] getTicketNamesArray(ArrayList<Ticket> ticketList) {
        String[] ticketNames = new String[ticketList.size()];
        for (int i = 0; i < ticketList.size(); i++) {
            ticketNames[i] = ticketList.get(i).getName();
        }
        return ticketNames;
    }

    //For finding ticket object after selecting ticket by name, used in edit/delete ticket.
    public static Ticket findTicketByName(String name, ArrayList<Ticket> ticketList) {
        Ticket ticket = null;
        for (int i = 0; i < ticketList.size(); i++) {
            if(ticketList.get(i).getName().equals(name)) {
                ticket = ticketList.get(i);
            }
        }
        return ticket;
    }

    public static Ticket findTicketByIndex(int index, ArrayList<Ticket> ticketList) {
        Ticket ticket = null;

                ticket = ticketList.get(index);


        return ticket;
    }


}
