package com.hogent.ti3g05.ti3_g05_joetzapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hogent.ti3g05.ti3_g05_joetzapp.Services.ConnectionDetector;


public class InschrijvenVakantiePart2 extends Activity {
    private EditText txtVoornaam, txtNaam, txtTelefoon, txtGSM;

    private String voornaam, naam, telefoon, gsm;

    private Button btnVolgende;
    private Button btnTerug;
    private boolean cancel = false;
    private View focusView = null;

    // flag for Internet connection status
    Boolean isInternetPresent = false;
    // Connection detector class
    ConnectionDetector cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inschrijven_vakantie_part2);
        cd = new ConnectionDetector(getApplicationContext());

        txtVoornaam = (EditText) findViewById(R.id.VoornaamContactPersoon);
        txtNaam = (EditText) findViewById(R.id.NaamContactPersoon);
        txtTelefoon = (EditText) findViewById(R.id.TelefoonContactPersoon);
        txtGSM = (EditText) findViewById(R.id.GsmContactPersoon);

        btnVolgende = (Button)findViewById(R.id.btnNaarDeel3V);
        btnVolgende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isInternetPresent = cd.isConnectingToInternet();

                if (isInternetPresent) {
                    controlerenOpfouten();
                }
                else{
                    // Internet connection is not present
                    // Ask user to connect to Internet
                    Toast.makeText(getApplicationContext(), getString(R.string.error_no_internet), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.back, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.backMenu) {
            Intent intent1 = new Intent(this, navBarMainScreen.class);
            startActivity(intent1);

            overridePendingTransition(R.anim.left_in, R.anim.right_out);
        }

        return super.onOptionsItemSelected(item);
    }

    public void controlerenOpfouten(){
        clearErrors();
        cancel = false;

        // Store values at the time of the login attempt.
        voornaam = txtVoornaam.getText().toString().toLowerCase();
        naam = txtNaam.getText().toString().toLowerCase();
        telefoon = txtTelefoon.getText().toString();
        gsm = txtGSM.getText().toString();


        if (TextUtils.isEmpty(gsm)) {
            txtGSM.setError(getString(R.string.error_field_required));
            focusView = txtGSM;
            cancel = true;
        }else{
            if (!gsm.matches("[0-9]+") || gsm.length() != 10){
                txtGSM.setError(getString(R.string.error_incorrect_gsm));
                focusView = txtGSM;
                cancel = true;
            }
        }

        if (TextUtils.isEmpty(telefoon)) {
            txtTelefoon.setError(getString(R.string.error_field_required));
            focusView = txtTelefoon;
            cancel = true;
        }else{
            if (!telefoon.matches("[0-9]+") || telefoon.length() != 9){
                txtTelefoon.setError(getString(R.string.error_incorrect_tel));
                focusView = txtTelefoon;
                cancel = true;
            }
        }

        if (TextUtils.isEmpty(naam)) {
            txtNaam.setError(getString(R.string.error_field_required));
            focusView = txtNaam;
            cancel = true;
        }

        if (TextUtils.isEmpty(voornaam)) {
            txtVoornaam.setError(getString(R.string.error_field_required));
            focusView = txtVoornaam;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            opslaan(voornaam ,naam, telefoon, gsm);
            //Toast.makeText(getApplicationContext(), "Opgeslagen", Toast.LENGTH_SHORT).show();

        }
    }

    private void opslaan(String voornaamCP,String naamCP, String telefoonCP, String gsmCP) {
        Toast.makeText(getApplicationContext(), getString(R.string.loading_message), Toast.LENGTH_SHORT).show();
        Intent in = new Intent(getApplicationContext(),InschrijvenVakantiePart3.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String voornaam = extras.getString("voornaam");
            String naam = extras.getString("naam");
            String straat = extras.getString("straat");
            String huisnr = extras.getString("huisnr");
            String bus = extras.getString("bus");
            String gemeente = extras.getString("gemeente");
            String postcode = extras.getString("postcode");
            in.putExtra("voornaam", voornaam);
            in.putExtra("naam", naam);
            in.putExtra("straat", straat);
            in.putExtra("huisnr", huisnr);
            in.putExtra("bus", bus);
            in.putExtra("gemeente", gemeente);
            in.putExtra("postcode", postcode);
        }

        in.putExtra("voornaamCP", voornaamCP);
        in.putExtra("naamCP", naamCP);
        in.putExtra("telefoonCP", telefoonCP);
        in.putExtra("gsmCP", gsmCP);

        startActivity(in);

        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }


    private void clearErrors(){
        txtVoornaam.setError(null);
        txtNaam.setError(null);
        txtTelefoon.setError(null);
        txtGSM.setError(null);
    }
}