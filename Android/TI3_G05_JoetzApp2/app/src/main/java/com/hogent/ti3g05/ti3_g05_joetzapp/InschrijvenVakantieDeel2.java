package com.hogent.ti3g05.ti3_g05_joetzapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hogent.ti3g05.ti3_g05_joetzapp.Services.ConnectionDetector;
import com.hogent.ti3g05.ti3_g05_joetzapp.domein.Activiteit;


//Stap 2 van het inschrijven
public class InschrijvenVakantieDeel2 extends Activity {
    private EditText txtVoornaam, txtNaam, txtTelefoon, txtGSM;
    private EditText txtVoornaamExtra, txtNaamExtra, txtTelefoonExtra, txtGSMExtra;

    private String voornaam, naam, telefoon, gsm;
    private String voornaamExtra, naamExtra, telefoonExtra, gsmExtra;
    private RelativeLayout layoutvoorExtraCP;

    private Button btnVolgende, btnCPextra;
    private boolean cancel = false;
    private View focusView = null;
    private boolean extraCPZichtbaar = false;

    private Boolean isInternetPresent = false;
    private ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inschrijven_vakantie_part2);
        cd = new ConnectionDetector(getApplicationContext());

        getActionBar().setTitle(R.string.title_activity_inschrijven);

        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);

        txtVoornaam = (EditText) findViewById(R.id.VoornaamContactPersoonIns);
        txtNaam = (EditText) findViewById(R.id.NaamContactPersoon);
        txtTelefoon = (EditText) findViewById(R.id.TelefoonContactPersoon);
        txtGSM = (EditText) findViewById(R.id.GsmContactPersoon);
        txtVoornaamExtra = (EditText) findViewById(R.id.VoornaamContactPersoonInsExtra);
        txtNaamExtra = (EditText) findViewById(R.id.NaamContactPersoonExtra);
        txtTelefoonExtra = (EditText) findViewById(R.id.TelefoonContactPersoonExtra);
        txtGSMExtra = (EditText) findViewById(R.id.GsmContactPersoonExtra);

        btnVolgende = (Button)findViewById(R.id.btnNaarDeel3V);
        btnVolgende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnVolgende.startAnimation(animAlpha);
                //Controleer of er internet aanwezig is, zoja controleer de ingegeven waarden, zoneen toon de gepaste melding
                isInternetPresent = cd.isConnectingToInternet();

                if (isInternetPresent) {
                    controlerenOpfouten();
                }
                else{
                    Toast.makeText(getApplicationContext(), getString(R.string.error_no_internet), Toast.LENGTH_SHORT).show();
                }
            }
        });

        layoutvoorExtraCP = (RelativeLayout) findViewById(R.id.layout_ExtraCP);
        btnCPextra = (Button) findViewById(R.id.btnExtraCP);
        btnCPextra.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                btnCPextra.startAnimation(animAlpha);
                //Geeft de mogelijkheid om een 2de contactpersoon in geval van nood toe te voegen

                extraCPZichtbaar = !extraCPZichtbaar;
                if (extraCPZichtbaar){
                    layoutvoorExtraCP.setVisibility(View.VISIBLE);

                    btnCPextra.setText(getString(R.string.btnCPVerwijderen));
                }
                else{
                    layoutvoorExtraCP.setVisibility(View.GONE);
                    btnCPextra.setText(getString(R.string.btnCPtoevoegen));
                }
            }
        });

        TextView tvOpvullen = (TextView) findViewById(R.id.AlgemeneUitleg);
        tvOpvullen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtVoornaam.setText("Albert");
                txtNaam.setText("Bosman");
                txtGSM.setText("0485263265");
                txtTelefoon.setText("082356895");

            }
        });
    }

    /*Naam: controlerenOpfouten
    Werking: controleer de ingegeven waarden en indien geen fout sla deze op
    */
    public void controlerenOpfouten(){
        clearErrors();
        cancel = false;

        voornaam = txtVoornaam.getText().toString().toLowerCase();
        naam = txtNaam.getText().toString().toLowerCase();
        telefoon = txtTelefoon.getText().toString();
        gsm = txtGSM.getText().toString();
        voornaamExtra = txtVoornaamExtra.getText().toString().toLowerCase();
        naamExtra = txtNaamExtra.getText().toString().toLowerCase();
        telefoonExtra = txtTelefoonExtra.getText().toString();
        gsmExtra = txtGSMExtra.getText().toString();

        if (extraCPZichtbaar){
            checkOfExtraVeldenZijnIngevuld();
        }

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

        if (!TextUtils.isEmpty(telefoon) && (!telefoon.matches("[0-9]+") || telefoon.length() != 9)){
            txtTelefoon.setError(getString(R.string.error_incorrect_tel));
            focusView = txtTelefoon;
            cancel = true;
        }

        if (TextUtils.isEmpty(naam)) {
            txtNaam.setError(getString(R.string.error_field_required));
            focusView = txtNaam;
            cancel = true;
        }else if (Activiteit.containsNumbers(naam)){
            txtNaam.setError(getString(R.string.error_noNumbers));
            focusView = txtNaam;
            cancel = true;
        }

        if (TextUtils.isEmpty(voornaam)) {
            txtVoornaam.setError(getString(R.string.error_field_required));
            focusView = txtVoornaam;
            cancel = true;
        }else if (Activiteit.containsNumbers(voornaam)){
            txtVoornaam.setError(getString(R.string.error_noNumbers));
            focusView = txtVoornaam;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            opslaan();

        }
    }

    /*Naam: opslaan
    Werking: Sla de gegevens op en stuur deze door naar de volgende stap
    */
    private void opslaan() {
        Toast.makeText(getApplicationContext(), getString(R.string.loading_message), Toast.LENGTH_SHORT).show();
        Intent in = new Intent(getApplicationContext(),InschrijvenVakantieDeel3.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String voornaam = extras.getString("voornaam");
            String naam = extras.getString("naam");
            String straat = extras.getString("straat");
            String huisnr = extras.getString("huisnr");
            String bus = extras.getString("bus");
            String gemeente = extras.getString("gemeente");
            String postcode = extras.getString("postcode");
            String objectId = extras.getString("objectId");
            String datum = extras.getString("datum");
            in.putExtra("voornaam", voornaam);
            in.putExtra("naam", naam);
            in.putExtra("straat", straat);
            in.putExtra("huisnr", huisnr);
            in.putExtra("bus", bus);
            in.putExtra("gemeente", gemeente);
            in.putExtra("postcode", postcode);
            in.putExtra("objectId", objectId);
            in.putExtra("datum", datum);
        }

        in.putExtra("voornaamCP", voornaam);
        in.putExtra("naamCP", naam);
        in.putExtra("telefoonCP", telefoon);
        in.putExtra("gsmCP", gsm);
        if (extraCPZichtbaar){
            in.putExtra("voornaamCPExtra", voornaamExtra);
            in.putExtra("naamCPExtra", naamExtra);
            in.putExtra("telefoonCPExtra", telefoonExtra);
            in.putExtra("gsmCPExtra", gsmExtra);
        }

        startActivity(in);

        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    /*Naam: checkOfExtraVeldenZijnIngevuld

    Werking: Indien de optie extracontactpersoon is aangeklikt controleer of deze correct zijn
    */
    public void checkOfExtraVeldenZijnIngevuld(){
        if (TextUtils.isEmpty(gsmExtra)) {
            txtGSMExtra.setError(getString(R.string.error_field_required));
            focusView = txtGSMExtra;
            cancel = true;
        }else{
            if (!gsmExtra.matches("[0-9]+") || gsmExtra.length() != 10){
                txtGSMExtra.setError(getString(R.string.error_incorrect_gsm));
                focusView = txtGSMExtra;
                cancel = true;
            }
        }

        if (!TextUtils.isEmpty(telefoonExtra) && !telefoonExtra.matches("[0-9]+") || telefoonExtra.length() != 9){
            txtTelefoonExtra.setError(getString(R.string.error_incorrect_tel));
            focusView = txtTelefoonExtra;
            cancel = true;
        }

        if (TextUtils.isEmpty(naamExtra)) {
            txtNaamExtra.setError(getString(R.string.error_field_required));
            focusView = txtNaamExtra;
            cancel = true;
        }else if (Activiteit.containsNumbers(naamExtra)){
            txtNaamExtra.setError(getString(R.string.error_noNumbers));
            focusView = txtNaamExtra;
            cancel = true;
        }

        if (TextUtils.isEmpty(voornaamExtra)) {
            txtVoornaamExtra.setError(getString(R.string.error_field_required));
            focusView = txtVoornaamExtra;
            cancel = true;
        }else if (Activiteit.containsNumbers(voornaamExtra)){
            txtVoornaamExtra.setError(getString(R.string.error_noNumbers));
            focusView = txtVoornaamExtra;
            cancel = true;
        }
    }

    //verwijder de error's
    private void clearErrors(){
        txtVoornaam.setError(null);
        txtNaam.setError(null);
        txtTelefoon.setError(null);
        txtGSM.setError(null);
        if (extraCPZichtbaar){
            txtVoornaamExtra.setError(null);
            txtNaamExtra.setError(null);
            txtTelefoonExtra.setError(null);
            txtGSMExtra.setError(null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.back_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.backMenu2) {
            Intent intent1 = new Intent(this, navBarMainScreen.class);
            startActivity(intent1);

            overridePendingTransition(R.anim.left_in, R.anim.right_out);
        }

        return super.onOptionsItemSelected(item);
    }
}
