package com.hogent.ti3g05.ti3_g05_joetzapp.SQLLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.hogent.ti3g05.ti3_g05_joetzapp.domein.Feedback;
import com.hogent.ti3g05.ti3_g05_joetzapp.domein.Monitor;
import com.hogent.ti3g05.ti3_g05_joetzapp.domein.Vakantie;
import com.hogent.ti3g05.ti3_g05_joetzapp.domein.Vorming;

import java.util.List;

import static com.hogent.ti3g05.ti3_g05_joetzapp.SQLLite.Constants.TABLE_VAKANTIE;

//Naam: fetchLocalObjects
//
//Werking: Laag tussen databasehandler en de klassen die de locale database gebruiken
public class SqliteDatabase {

    protected DBHandler db;
    private final Context context;
    private SQLiteDatabase sqLiteDatabase;

    public SqliteDatabase(Context context) {
        this.context = context;
        db = new DBHandler(context);
    }

    public void open()
    {
        try{
            db.getWritableDatabase();

        }catch(SQLiteException ex)
        {
            Log.e("mydb", "kon db niet openen");
        }
    }

    public void creerProfielen()
    {
        db.onCreateProfielen(db.getWritableDatabase());
    }

    public void creerVormingen()
    {
        db.onCreateVormingen(db.getWritableDatabase());
    }

    public void creerVakanties()
    {
        db.onCreateVakantie(db.getWritableDatabase());
    }

    public void creerFavorieten(){db.onCreateFavorieten(db.getWritableDatabase());}

    public void dropVakanties()
    {
       db.dropVakanties(db.getWritableDatabase());

    }

    public void dropProfielen()
    {
        db.dropProfielen(db.getWritableDatabase());

    }

    public void dropVormingen()
    {
        db.dropVormingen(db.getWritableDatabase());

    }
    public void dropFavorieten()
    {
        db.dropFavorieten(db.getWritableDatabase());

    }

    public void dropFeedback()
    {
        db.dropFeedback(db.getWritableDatabase());

    }



    public void close()
    {
        try{
            db.close();
        }catch (SQLiteException e)
        {
            Log.e("mydb","kon db niet sluiten");
        }
    }

    public long insertVakantie(Vakantie nieuweVakantie)
    {
        return db.toevoegenGegevensVakantie(nieuweVakantie);
    }

    public List<Vakantie> getVakanties()
    {
        return db.krijgAlleVakanties();
    }

    public Vakantie getVakanie(String naam)
    {
        return db.krijgVakanties(naam);
    }



    public long insertProfiel(Monitor nieuwProfiel)
    {
        return db.toevoegenGegevensMonitor(nieuwProfiel);
    }

    public List<Monitor> getProfielen()
    {
        return db.krijgProfielen();
    }

    public Monitor getProfiel(String naam)
    {
        return db.krijgProfielen(naam);
    }


    //vormingen
    public long insertVorming(Vorming nieuweVorming)
    {
        return db.toevoegenGegevensVorming(nieuweVorming);
    }

    public List<Vorming> getVormingen()
    {
        return db.krijgVormingen();
    }

    public Vorming getVorming(String naam)
    {
        return db.krijgVormingen(naam);
    }

    //favorieten

    public long insertFavoriet(Vakantie favorieteVakantie)
    {
        return db.toevoegenGegevensFavoriet(favorieteVakantie);
    }

    public List<Vakantie> getFavorieten()
    {
        return db.krijgFavorieten();
    }

    public Vakantie getFavoriet(String naam)
    {
        return db.krijgFavorieten(naam);
    }

    //feedback

    //vormingen
    public long insertFeedback(Feedback feedback)
    {
        return db.toevoegenGegevensFeedback(feedback);
    }

    public List<Feedback> getFeedback()
    {
        return db.krijgFeedback();
    }

    public Feedback getFeedback(String naam)
    {
        return db.krijgFeedback(naam);
    }

}
