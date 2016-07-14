package com.example.homework_02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class PrintTicketActivity extends AppCompatActivity {

    TextView textView_name, textView_source, textView_destination, textView_departure, textView_return;
    Button finish;
    Ticket ticket = null;
    SimpleDateFormat dateFormatter=new SimpleDateFormat("MM/dd/yyyy", Locale.US);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_ticket);

        Log.d("Demo","hellooo");
        textView_name = (TextView) findViewById(R.id.textView_name);
        textView_source = (TextView) findViewById(R.id.textView_source);
        textView_destination = (TextView) findViewById(R.id.textView_destination);
        textView_departure = (TextView) findViewById(R.id.textView_departure);
        textView_return = (TextView) findViewById(R.id.textView_return);

        if (getIntent().getExtras() != null) {
            ticket = (Ticket) getIntent().getExtras().
                    getSerializable(CreateTicketActivity.TICKET_KEY);
            textView_name.setText("Name: " + ticket.getName());
            textView_source.setText("Source: " +
                    CreateTicketActivity.cities[ticket.getSource()]);
            textView_destination.setText("Destination: " +
                    CreateTicketActivity.cities[ticket.getDestination()]);
            textView_departure.setText("Departure: " +
                    ticket.getDeptDate() + ", " +
                    ticket.getDeptTime());
            if (ticket.isRoundTrip()) {
                textView_return.setText("Return: " +
                        ticket.getReturnDate() + ", " +
                        ticket.getReturnTime());
            } else {
                textView_return.setText("");
            }

        }

        finish = (Button) findViewById(R.id.button_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrintTicketActivity.this, MainActivity.class);
                if (ticket != null) {
                    intent.putExtra(CreateTicketActivity.TICKET_KEY, ticket);
                }
                startActivity(intent);
                finish();
            }
        });





    }
}
