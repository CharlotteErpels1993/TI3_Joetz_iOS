package com.hogent.ti3g05.ti3_g05_joetzapp.SignUpLogin;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hogent.ti3g05.ti3_g05_joetzapp.Services.ConnectionDetector;
import com.hogent.ti3g05.ti3_g05_joetzapp.R;
import com.hogent.ti3g05.ti3_g05_joetzapp.navBarMainScreen;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

public class SignUp_deel4 extends Activity{

	private EditText mEmailEditText; 
	private EditText mPasswordEditText;
	private EditText mConfirmPasswordEditText;

    private boolean cancel = false;
    private View focusView = null;

	// flag for Internet connection status
    Boolean isInternetPresent = false;
    // Connection detector class
    ConnectionDetector cd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_signup_deel4);

		// creating connection detector class instance
        cd = new ConnectionDetector(getApplicationContext());

        getActionBar().setTitle(getString(R.string.title_activity_Register));

		
		mEmailEditText = (EditText) findViewById(R.id.etEmail);
		mPasswordEditText = (EditText) findViewById(R.id.etPassword);
		mConfirmPasswordEditText = (EditText) findViewById(R.id.etPasswordConfirm);

        Button mCreateAccountButton = (Button) findViewById(R.id.btnCreateAccount);
        //mCreateAccountButton.setTextColor(getResources().getColor(R.color.Rood));
		mCreateAccountButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                isInternetPresent = cd.isConnectingToInternet();
                if (isInternetPresent) {
                    // Internet Connection is Present
                    // make HTTP requests
                    createAccount();
                } else
                    Toast.makeText(getApplicationContext(), getString(R.string.error_no_internet), Toast.LENGTH_SHORT).show();
                //showAlertDialog(getApplicationContext(), "No Internet Connection",
                //"You don't have internet connection.", false);
            }
        });


	}


	private void createAccount(){
		clearErrors();
        cancel = false;

		//declaratie van focusView & cancel is private gedeclareerd.

		// Store values at the time of the login attempt.
        String mEmail = mEmailEditText.getText().toString();
        String mPassword = mPasswordEditText.getText().toString();
        String mConfirmPassword = mConfirmPasswordEditText.getText().toString();

		// Check for a valid confirm password.
		if (TextUtils.isEmpty(mConfirmPassword)) {
			mConfirmPasswordEditText.setError(getString(R.string.error_field_required));
			focusView = mConfirmPasswordEditText;
			cancel = true;
		} else if (mPassword != null && !mConfirmPassword.equals(mPassword)) {
			mPasswordEditText.setError(getString(R.string.error_invalid_confirm_password));
			focusView = mPasswordEditText;
			cancel = true;
		}
		// Check for a valid password.
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordEditText.setError(getString(R.string.error_field_required));
			focusView = mPasswordEditText;
			cancel = true;
		} else if (mPassword.length() < 4) {
			mPasswordEditText.setError(getString(R.string.error_invalid_password));
			focusView = mPasswordEditText;
			cancel = true;
		}

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			mEmailEditText.setError(getString(R.string.error_field_required));
			focusView = mEmailEditText;
			cancel = true;
		} else if (!mEmail.contains("@")) {
			mEmailEditText.setError(getString(R.string.error_invalid_email));
			focusView = mEmailEditText;
			cancel = true;
		}

        //check if email adress is already used.
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Ouder");
        query.whereEqualTo("email", mEmail);
        try{
            List<ParseObject> lijstObjecten = query.find();
            if (lijstObjecten.size() > 0){
                mEmailEditText.setError("Dit e-mail adres is reeds in gebruik.");
                focusView = mEmailEditText;
                cancel = true;
            }
        }
        catch(ParseException e){
            signUpMsg(getString(R.string.error_generalException));
            cancel = true;
        }

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			//Toast.makeText(getApplicationContext(), "Registreren", Toast.LENGTH_SHORT).show();
			signUp(mEmail,mEmail, mPassword);

		}

	}

    private void signUp(String username, String mEmail, String mPassword) {
        Toast.makeText(getApplicationContext(), getString(R.string.loading_message), Toast.LENGTH_SHORT).show();

        ParseObject gebruiker = new ParseObject("Ouder");
        gebruiker.put("email", mEmail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //String lidBondMoyson = extras.getString("lidVanBondMoyson");
            String codeGerechtigde = extras.getString("codeGerechtigde");
            String aansluitingsnrOuder2 = extras.getString("aansluitingsnrOuder2");
            String aansluitingsnr = extras.getString("aansluitingsnr");
            String voornaam = extras.getString("voornaam");
            String naam = extras.getString("naam");
            String straat = extras.getString("straat");
            String huisnr = extras.getString("huisnr");
            String bus = extras.getString("bus");
            String gemeente = extras.getString("gemeente");
            String postcode = extras.getString("postcode");
            String rijksregnr = extras.getString("rijksregisternr");
            String telefoon = extras.getString("telefoon");
            String gsm = extras.getString("gsm");

            Toast.makeText(getApplicationContext(), "niet leeg deel4", Toast.LENGTH_SHORT).show();

            try{
                //gebruiker.put("wachtwoord", lidBondMoyson);
                if (aansluitingsnr != null && !aansluitingsnr.equals(""))
                    gebruiker.put("aansluitingsNr", Double.parseDouble(aansluitingsnr));
                gebruiker.put("voornaam", voornaam);
                gebruiker.put("naam", naam);
                gebruiker.put("straat", straat);
                gebruiker.put("nummer", Integer.parseInt(huisnr));
                if (bus != null && !bus.equals(""))
                    gebruiker.put("bus", bus);
                gebruiker.put("gemeente", gemeente);
                gebruiker.put("postcode", Integer.parseInt(postcode));
                gebruiker.put("rijksregisterNr", rijksregnr);
                gebruiker.put("telefoon", telefoon);
                gebruiker.put("gsm", gsm);
                if (aansluitingsnrOuder2 != null && !aansluitingsnrOuder2.equals(""))
                    gebruiker.put("aansluitingsNrTweedeOuder", Double.parseDouble(aansluitingsnrOuder2));
                if (codeGerechtigde != null && !codeGerechtigde.equals(""))
                    gebruiker.put("codeGerechtigde", Double.parseDouble(codeGerechtigde));
            }
            catch (NumberFormatException nfe){
                Toast.makeText(getApplicationContext(), getString(R.string.error_generalException), Toast.LENGTH_SHORT).show();
            }
        }

        gebruiker.saveInBackground();

        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(mPassword);
        user.setEmail(mEmail);
        user.put("soort", "ouder");
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    signUpMsg(getString(R.string.error_generalException));
                }
            }
        });

        signUpMsg("Account aangemaakt.");
        Intent in = new Intent(getApplicationContext(), navBarMainScreen.class);
        startActivity(in);
    }

	protected void signUpMsg(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();	
	}

	private void clearErrors(){ 
		mEmailEditText.setError(null);
		mPasswordEditText.setError(null);
		mConfirmPasswordEditText.setError(null);
	}

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(SignUp_deel4.this, navBarMainScreen.class);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);
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
            Intent intent1 = new Intent(this, SignUp_deel1.class);
            startActivity(intent1);

            overridePendingTransition(R.anim.left_in, R.anim.right_out);
        }

        return super.onOptionsItemSelected(item);
    }

	
}
