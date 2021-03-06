package com.hogent.ti3g05.ti3_g05_joetzapp.SQLLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hogent.ti3g05.ti3_g05_joetzapp.domein.FavorieteVakantie;
import com.hogent.ti3g05.ti3_g05_joetzapp.domein.Feedback;
import com.hogent.ti3g05.ti3_g05_joetzapp.domein.Monitor;
import com.hogent.ti3g05.ti3_g05_joetzapp.domein.Vakantie;
import com.hogent.ti3g05.ti3_g05_joetzapp.domein.Vorming;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.List;

import static com.hogent.ti3g05.ti3_g05_joetzapp.SQLLite.Constants.COLUMN_GEMIDDELDERATING;
import static com.hogent.ti3g05.ti3_g05_joetzapp.SQLLite.Constants.COLUMN_VAKANTIEID;
import static com.hogent.ti3g05.ti3_g05_joetzapp.SQLLite.Constants.TABLE_FAVORIETEN;
import static com.hogent.ti3g05.ti3_g05_joetzapp.SQLLite.Constants.TABLE_FEEDBACK;
import static com.hogent.ti3g05.ti3_g05_joetzapp.SQLLite.Constants.TABLE_PROFIELEN;
import static com.hogent.ti3g05.ti3_g05_joetzapp.SQLLite.Constants.TABLE_VAKANTIE;
import static com.hogent.ti3g05.ti3_g05_joetzapp.SQLLite.Constants.TABLE_VORMINGEN;

/*
    Naam: DBHandler
    Werking: Maakt de locale database aan en doet hier de bewerkingen op
    */
public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(Context context)
    {
        super(context,Constants.DATABASE_NAAM, null, Constants.DATABASE_VERSION);

    }

    /*
    Naam: onCreateVakantie
    Werking: creëert de vakantietabel in de locale database
    Parameters:
     - sqLiteDatabase: SQLiteDatabase - de DB waar mee gewerkt wordt
    */
    public void onCreateVakantie(SQLiteDatabase sqLiteDatabase) {
        String CREATE_VAKANTIE_TABLE = "CREATE TABLE " + TABLE_VAKANTIE + "(" +Constants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +Constants.COLUMN_VAKANTIENAAM + " TEXT," + Constants.COLUMN_LOCATIE + " TEXT," + Constants.COLUMN_VERTREKDATUM + " TEXT," +
                Constants.COLUMN_TERUGDATUM + " TEXT," + Constants.COLUMN_PRIJS + " NUMERIC," + Constants.COLUMN_AFBEELDING1 + " TEXT," +Constants.COLUMN_AFBEELDING2 + " TEXT," +Constants.COLUMN_AFBEELDING3 + " TEXT," +
                Constants.COLUMN_MAXDOELGROEP + " TEXT," + Constants.COLUMN_MINDOELGROEP + " TEXT," + Constants.COLUMN_BESCHRIJVING + " TEXT," + Constants.COLUMN_PERIODE + " TEXT," + Constants.COLUMN_VERVOER + " TEXT," +
                Constants.COLUMN_FORMULE + " TEXT," + Constants.COLUMN_MAXDEELNEMERS + " NUMERIC," + Constants.COLUMN_INBEGREPENINPRIJS + " TEXT," + Constants.COLUMN_BMLEDENPRIJS + " NUMERIC," +
                Constants.COLUMN_STERPRIJSOUDER1 + " NUMERIC," + Constants.COLUMN_STERPRIJS2OUDERS + " NUMERIC, " + COLUMN_GEMIDDELDERATING +" NUMERIC" + ")";

        sqLiteDatabase.execSQL(CREATE_VAKANTIE_TABLE);

    }


    /*
    Naam: onCreateProfielen
    Werking: Creëert de profielentabel in de locale database
    Parameters:
     - sqLiteDatabase: SQLiteDatabase - de DB waar mee gewerkt wordt
    */
    public void onCreateProfielen(SQLiteDatabase sqLiteDatabase) {

        String CREATE_PROFIELEN_TABLE = "CREATE TABLE " + TABLE_PROFIELEN + "(" +Constants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +Constants.COLUMN_AANSLUITINGSNUMMER + " NUMERIC," + Constants.COLUMN_BUS + " TEXT," + Constants.COLUMN_CODEGERECHTIGDE + " NUMERIC," +
                Constants.COLUMN_EMAIL + " TEXT," + Constants.COLUMN_GEMEENTE + " TEXT," + Constants.COLUMN_GSM + " TEXT," +Constants.COLUMN_LIDNR + " NUMERIC," +Constants.COLUMN_LINKFACEBOOK + " TEXT," +
                Constants.COLUMN_NAAM + " TEXT," + Constants.COLUMN_NUMMER + " NUMERIC," + Constants.COLUMN_POSTCODE + " NUMERIC," + Constants.COLUMN_RIJKSREGISTERNUMMER + " TEXT," +
                Constants.COLUMN_STRAAT + " TEXT," + Constants.COLUMN_TELEFOON + " TEXT," + Constants.COLUMN_VOORNAAM + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_PROFIELEN_TABLE);
    }

    /*
    Naam: onCreateFavorieten
    Werking: Creëert de favorietentabel in de locale database
    Parameters:
     - sqLiteDatabase: SQLiteDatabase - de DB waar mee gewerkt wordt
    */
    public void onCreateFavorieten(SQLiteDatabase sqLiteDatabase) {
        String CREATE_FAVORIETEN_TABLE = "CREATE TABLE " + TABLE_FAVORIETEN + "(" +Constants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +Constants.COLUMN_VAKANTIENAAM + " TEXT," + Constants.COLUMN_LOCATIE + " TEXT," + Constants.COLUMN_VERTREKDATUM + " TEXT," +
                Constants.COLUMN_TERUGDATUM + " TEXT," + Constants.COLUMN_PRIJS + " NUMERIC," + Constants.COLUMN_AFBEELDING1 + " TEXT," +Constants.COLUMN_AFBEELDING2 + " TEXT," +Constants.COLUMN_AFBEELDING3 + " TEXT," +
                Constants.COLUMN_MAXDOELGROEP + " TEXT," + Constants.COLUMN_MINDOELGROEP + " TEXT," + Constants.COLUMN_BESCHRIJVING + " TEXT," + Constants.COLUMN_PERIODE + " TEXT," + Constants.COLUMN_VERVOER + " TEXT," +
                Constants.COLUMN_FORMULE + " TEXT," + Constants.COLUMN_MAXDEELNEMERS + " NUMERIC," + Constants.COLUMN_INBEGREPENINPRIJS + " TEXT," + Constants.COLUMN_BMLEDENPRIJS + " NUMERIC," +
                Constants.COLUMN_STERPRIJSOUDER1 + " NUMERIC," + Constants.COLUMN_STERPRIJS2OUDERS + " NUMERIC, " + COLUMN_VAKANTIEID + " TEXT, " + COLUMN_GEMIDDELDERATING+" NUMERIC)";

        sqLiteDatabase.execSQL(CREATE_FAVORIETEN_TABLE);
    }

    /*
    Naam: onCreateVormingen
    Werking: Creëert de vormingentabel in de locale database
    Parameters:
     - sqLiteDatabase: SQLiteDatabase - de DB waar mee gewerkt wordt
    */
    public void onCreateVormingen(SQLiteDatabase sqLiteDatabase) {
        String CREATE_VORMINGEN_TABLE = "CREATE TABLE " + TABLE_VORMINGEN + "(" +Constants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +Constants.COLUMN_BETALINGSWIJZE + " TEXT," + Constants.COLUMN_CRITERIADEELNEMER + " TEXT," + Constants.COLUMN_INBEGREPENINPRIJSV + " TEXT," +
                Constants.COLUMN_KORTEBESCHRIJVING + " TEXT," + Constants.COLUMN_LOCATIEV + " TEXT," + Constants.COLUMN_PERIODES + " TEXT," +Constants.COLUMN_PRIJSV + " NUMERIC," +Constants.COLUMN_TIPS + " TEXT," +
                Constants.COLUMN_TITEL + " TEXT," + Constants.COLUMN_WEBSITELOCATIE + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_VORMINGEN_TABLE);
    }

    /*
    Naam: onCreateFeedback
    Werking: Creëert de feedbacktabel in de locale database
    Parameters:
     - sqLiteDatabase: SQLiteDatabase - de DB waar mee gewerkt wordt
    */
    public void onCreateFeedback(SQLiteDatabase sqLiteDatabase) {

        String CREATE_FEEDBACK_TABLE = "CREATE TABLE " + TABLE_FEEDBACK + "(" +Constants.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +Constants.COLUMN_FEEDBACK + " TEXT," + Constants.COLUMN_SCORE + " NUMERIC," + Constants.COLUMN_VAKANTIENAAMF + " TEXT," +
                Constants.COLUMN_VAKANTIEID + " TEXT," + Constants.COLUMN_GEBRUIKERID + " TEXT," + Constants.COLUMN_GEBRUIKER + " TEXT," +Constants.COLUMN_GOEDGEKEURD + " NUMERIC"+ ")";

        sqLiteDatabase.execSQL(CREATE_FEEDBACK_TABLE);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VAKANTIE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFIELEN);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_VORMINGEN);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORIETEN);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FEEDBACK);
        onCreate(sqLiteDatabase);

    }

    /*
    Naam: dropVakanties
    Werking: Verwijderd de volledige vakantietabel en maakt deze opnieuw aan
    Parameters:
     - sqLiteDatabase: SQLiteDatabase - de DB waar mee gewerkt wordt
    */
    public void dropVakanties(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VAKANTIE);
        onCreateVakantie(db);
    }

    /*
    Naam: dropFeedback
    Werking: Verwijderd de volledige feedbacktabel en maakt deze opnieuw aan
    Parameters:
     - sqLiteDatabase: SQLiteDatabase - de DB waar mee gewerkt wordt
    */
    public void dropFeedback(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEEDBACK);
        onCreateFeedback(db);
    }

    /*
    Naam: dropVormingen
    Werking: Verwijderd de volledige vormingentabel en maakt deze opnieuw aan
    Parameters:
     - sqLiteDatabase: SQLiteDatabase - de DB waar mee gewerkt wordt
    */
    public void dropVormingen(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VORMINGEN);
        onCreateVormingen(db);
    }

    /*
    Naam: dropProfielen
    Werking: Verwijderd de volledige profielentabel en maakt deze opnieuw aan
    Parameters:
     - sqLiteDatabase: SQLiteDatabase - de DB waar mee gewerkt wordt
    */
    public void dropProfielen(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFIELEN);
        onCreateProfielen(db);

    }

    /*
    Naam: dropFavorieten
    Werking: Verwijderd de volledige favorietentabel en maakt deze opnieuw aan
    Parameters:
     - sqLiteDatabase: SQLiteDatabase - de DB waar mee gewerkt wordt
    */
    public void dropFavorieten(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORIETEN);
        onCreateFavorieten(db);

    }

    //Logica voor vakantietabel

    /*
    Naam: toevoegenGegevensVakantie
    Werking: Voegt de gegevens van de doorgegeven vakantie toe aan de vakantietabel
    Parameters:
     - vakantie: Vakantie - het Vakantie object dat moet toegevoegd worden
    */
    public Long toevoegenGegevensVakantie(Vakantie vakantie)
    {
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_VAKANTIENAAM, vakantie.getNaamVakantie());
        values.put(Constants.COLUMN_LOCATIE, vakantie.getLocatie());
        values.put(Constants.COLUMN_VERTREKDATUM, vakantie.getVertrekDatum().toString());
        values.put(Constants.COLUMN_TERUGDATUM, vakantie.getTerugkeerDatum().toString());

        values.put(Constants.COLUMN_PRIJS,(Integer) vakantie.getBasisprijs());
        values.put(Constants.COLUMN_AFBEELDING1, vakantie.getFoto1());
        if (vakantie.getFotos().size() >= 2)
            values.put(Constants.COLUMN_AFBEELDING2, vakantie.getFoto2());
        if (vakantie.getFotos().size() >= 3)
            values.put(Constants.COLUMN_AFBEELDING3, vakantie.getFoto3());
        //values.put(Constants.COLUMN_DOELGROEP, vakantie.getDoelGroep());
        values.put(Constants.COLUMN_MAXDOELGROEP,(Integer) vakantie.getMaxDoelgroep());
        values.put(Constants.COLUMN_MINDOELGROEP, (Integer)vakantie.getMinDoelgroep());

        values.put(Constants.COLUMN_BESCHRIJVING, vakantie.getKorteBeschrijving());
        values.put(Constants.COLUMN_PERIODE, vakantie.getPeriode());
        values.put(Constants.COLUMN_VERVOER, vakantie.getVervoerswijze());

        values.put(Constants.COLUMN_FORMULE, vakantie.getFormule());
        values.put(Constants.COLUMN_MAXDEELNEMERS, (Integer) vakantie.getMaxAantalDeelnemers());
        values.put(Constants.COLUMN_INBEGREPENINPRIJS, vakantie.getInbegrepenInPrijs());
        values.put(Constants.COLUMN_BMLEDENPRIJS,(Integer) vakantie.getBondMoysonLedenPrijs());

        if((Integer)vakantie.getSterPrijs1Ouder()<0)
            vakantie.setSterPrijs1Ouder(0);
        if((Integer)vakantie.getSterPrijs2Ouder()<0)
            vakantie.setSterPrijs2Ouder(0);

        values.put(Constants.COLUMN_STERPRIJSOUDER1,(Integer) vakantie.getSterPrijs1Ouder());
        values.put(Constants.COLUMN_STERPRIJS2OUDERS,(Integer) vakantie.getSterPrijs2Ouder());
        values.put(Constants.COLUMN_GEMIDDELDERATING, vakantie.getGemiddeldeRating());
        SQLiteDatabase db = this.getWritableDatabase();

        Long id = db.insert(TABLE_VAKANTIE, null, values);
        db.close();

        return id;
    }

    /*
    Naam: krijgAlleVakanties
    Werking: Haalt alle vakanties op uit de vakantietabel
    Return: lijst van alle Vakantie objecten
    */
    public List<Vakantie> krijgAlleVakanties()
    {
        List<Vakantie> vakanties = new ArrayList<Vakantie>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_VAKANTIE;
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            //overloop de vakanties en haal alle juiste gegevens op van die vakantie
            Vakantie v = krijgVakanties(c.getString(1));
            vakanties.add(v);
            c.moveToNext();
        }
        c.close();
        return vakanties;
    }

    /*
    Naam: krijgVakanties
    Werking: Haalt een bepaalde vakantie op uit de vakantietabel
    Parameters:
    vakantienaam: String - de naam van de Vakantie, die we zoeken
    Return: het juist Vakantie object dat overeen komt met de opgegeven naam
    */
    public Vakantie krijgVakanties(String vakantienaam)
    {
        String query = "Select * FROM " + TABLE_VAKANTIE + " WHERE " + Constants.COLUMN_VAKANTIENAAM + " = \"" + vakantienaam + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Vakantie vakantie = new Vakantie();

        if(cursor.moveToFirst())
        {
            cursor.moveToFirst();
            vakantie.setNaamVakantie(cursor.getString(1));
            vakantie.setLocatie(cursor.getString(2));
            vakantie.setVertrekDatumString(cursor.getString(3));
            vakantie.setTerugDatumString(cursor.getString(4));
            vakantie.setBasisprijs(Double.parseDouble(cursor.getString(5)));
            vakantie.setFoto1(cursor.getString(6));
            vakantie.setFoto2(cursor.getString(7));
            vakantie.setFoto3(cursor.getString(8));
            vakantie.setMaxDoelgroep(Integer.parseInt(cursor.getString(9)));
            vakantie.setMinDoelgroep(Integer.parseInt(cursor.getString(10)));
            //vakantie.setDoelGroep(cursor.getString(9));
            vakantie.setKorteBeschrijving(cursor.getString(11));
            vakantie.setPeriode(cursor.getString(12));
            vakantie.setVervoerswijze(cursor.getString(13));
            vakantie.setFormule(cursor.getString(14));
            vakantie.setMaxAantalDeelnemers(Integer.parseInt(cursor.getString(15)));
            vakantie.setInbegrepenInPrijs(cursor.getString(16));
            vakantie.setBondMoysonLedenPrijs(Double.parseDouble(cursor.getString(17)));
            vakantie.setSterPrijs1Ouder(Double.parseDouble(cursor.getString(18)));
            vakantie.setSterPrijs2Ouder(Double.parseDouble(cursor.getString(19)));
            vakantie.setGemiddeldeRating(Integer.parseInt(cursor.getString(20)));
        } else
        {
            vakantie = null;
        }
        cursor.close();
        db.close();
        return vakantie;
    }

    //Logica voor profielentabel

    /*
    Naam: toevoegenGegevensMonitor
    Werking: Voegt de gegevens van de doorgegeven monitor toe aan de profielentabel
    Parameters:
     - monitor: Monitor - het Monitor opbject dat moet toegevoegd worden
    */
    public Long toevoegenGegevensMonitor(Monitor monitor)
    {
        ContentValues values = new ContentValues();
        //values.put(Constants.COLUMN_AANSLUITINGSNUMMER,(Integer) monitor.getAansluitingsNr());
        //values.put(Constants.COLUMN_BUS, monitor.getBus());
        //values.put(Constants.COLUMN_CODEGERECHTIGDE,(Integer) monitor.getCodeGerechtigde());
        values.put(Constants.COLUMN_EMAIL, monitor.getEmail());

        values.put(Constants.COLUMN_GEMEENTE, monitor.getGemeente());
        values.put(Constants.COLUMN_GSM, monitor.getGsmnr());
        values.put(Constants.COLUMN_LIDNR, monitor.getLidNummer());
        if(monitor.getLinkFacebook() == null)
            monitor.setLinkFacebook("");
        values.put(Constants.COLUMN_LINKFACEBOOK, monitor.getLinkFacebook());
        values.put(Constants.COLUMN_NAAM, monitor.getNaam());

        values.put(Constants.COLUMN_NUMMER,(Integer) monitor.getHuisnr());
        values.put(Constants.COLUMN_POSTCODE, monitor.getPostcode());
        //values.put(Constants.COLUMN_RIJKSREGISTERNUMMER, monitor.getRijksregNr());

        values.put(Constants.COLUMN_STRAAT, monitor.getStraat());
        values.put(Constants.COLUMN_TELEFOON, monitor.getTelefoonnr());
        values.put(Constants.COLUMN_VOORNAAM, monitor.getVoornaam());

        SQLiteDatabase db = this.getWritableDatabase();

        Long id = db.insert(TABLE_PROFIELEN, null, values);
        db.close();

        return id;
    }

    /*
    Naam: krijgProfielen
    Werking: Haalt een lijst van profielen op uit de profielentabel
    Return: lijst van alle profielen in de DB
    */
    public List<Monitor> krijgProfielen()
    {
        List<Monitor> profielen = new ArrayList<Monitor>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PROFIELEN;
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            //overloop de profielen en haal alle juiste gegevens op van de monitor
            Monitor v = krijgProfielen(c.getString(9));
            profielen.add(v);
            c.moveToNext();
        }
        c.close();
        return profielen;
    }


    /*
    Naam: krijgProfielen
    Werking: Haalt een bepaalde monitor op uit de profielentabel
    Parameters:
    - profielNaam: String - de naam van het profiel, die we zoeken
    Return: het juiste Monitor object dat overeen komt met de opgegeven naam
    */
    public Monitor krijgProfielen(String profielnaam)
    {
        String query = "Select * FROM " + TABLE_PROFIELEN + " WHERE " + Constants.COLUMN_NAAM + " = \"" + profielnaam + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Monitor monitor = new Monitor();

        if(cursor.moveToFirst())
        {
            cursor.moveToFirst();
            //monitor.setAansluitingsNr(Integer.parseInt(cursor.getString(1)));
            //monitor.setBus(cursor.getString(2));
            //monitor.setCodeGerechtigde(Integer.parseInt(cursor.getString(3)));
            monitor.setEmail(cursor.getString(4));
            monitor.setGemeente(cursor.getString(5));
            monitor.setGsmnr(cursor.getString(6));
            monitor.setLidNummer(cursor.getString(7));
            monitor.setLinkFacebook(cursor.getString(8));
            monitor.setNaam(cursor.getString(9));
            monitor.setHuisnr(Integer.parseInt(cursor.getString(10)));
            monitor.setPostcode(Integer.parseInt(cursor.getString(11)));
            //monitor.setRijksregNr(cursor.getString(12));
            monitor.setStraat(cursor.getString(13));
            monitor.setTelefoonnr(cursor.getString(14));
            monitor.setVoornaam(cursor.getString(15));

        } else
        {
            monitor = null;
        }
        cursor.close();
        db.close();
        return monitor;
    }

    //Logica voor vormingtabel

    /*
    Naam: toevoegenGegevensVorming
    Werking: Voegt de gegevens van de doorgegeven vorming toe aan de vormingentabel
    Parameters:
      vorming - Vorming: het Vorming object dat moet toegevoegd worden
    */
    public Long toevoegenGegevensVorming(Vorming vorming)
    {
        String periodesStr = "";
        ContentValues values = new ContentValues();

        values.put(Constants.COLUMN_BETALINGSWIJZE, vorming.getBetalingswijze());

        values.put(Constants.COLUMN_CRITERIADEELNEMER, vorming.getCriteriaDeelnemers());
        values.put(Constants.COLUMN_INBEGREPENINPRIJSV, vorming.getInbegrepenInPrijs());
        values.put(Constants.COLUMN_KORTEBESCHRIJVING, vorming.getKorteBeschrijving());

        values.put(Constants.COLUMN_LOCATIEV, vorming.getLocatie());
        ArrayList<String> periodes = (ArrayList<String>) vorming.getPeriodes();
        for(String p: periodes)
        {
            periodesStr+=p+",";
        }
        values.put(Constants.COLUMN_PERIODES, periodesStr);

        values.put(Constants.COLUMN_PRIJSV, vorming.getPrijs().intValue());
        values.put(Constants.COLUMN_TIPS, vorming.getTips());

        values.put(Constants.COLUMN_TITEL, vorming.getTitel());
        values.put(Constants.COLUMN_WEBSITELOCATIE, vorming.getWebsiteLocatie());

        SQLiteDatabase db = this.getWritableDatabase();

        Long id = db.insert(TABLE_VORMINGEN, null, values);
        db.close();

        return id;

    }

    /*
    Naam: krijgVormingen
    Werking: Haalt een lijst van vormingen op uit de vormingentabel
    Return: lijst van alle Vorming objecten
    */
    public List<Vorming> krijgVormingen()
    {
        List<Vorming> vormingen = new ArrayList<Vorming>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_VORMINGEN;
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            //overloop de vormingen en haal alle juiste gegevens op van die vorming
            Vorming v = krijgVormingen(c.getString(9));
            vormingen.add(v);
            c.moveToNext();
        }
        c.close();
        return vormingen;
    }

    //Haalt een bepaalde vorming op uit de vormingentabel
    /*
    Naam: krijgVormingen
    Werking: Creëert de feedbacktabel in de locale database
    Parameters:
     - sqLiteDatabase: SQLiteDatabase - de DB waar mee gewerkt wordt
    */
    public Vorming krijgVormingen(String vormingTitel)
    {
        String query = "Select * FROM " + TABLE_VORMINGEN + " WHERE " + Constants.COLUMN_TITEL + " = \"" + vormingTitel + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Vorming vorming = new Vorming();

        if(cursor.moveToFirst())
        {
            cursor.moveToFirst();
            vorming.setBetalingswijze(cursor.getString(1));
            vorming.setCriteriaDeelnemers(cursor.getString(2));
            vorming.setInbegrepenInPrijs(cursor.getString(3));
            vorming.setKorteBeschrijving(cursor.getString(4));
            vorming.setLocatie(cursor.getString(5));
            vorming.setPeriodes(cursor.getString(6));
            if(cursor.getString(7) == null)
            {
                vorming.setPrijs(0);
            }else
            {
                vorming.setPrijs(Integer.parseInt(cursor.getString(7)));
            }
            vorming.setTips(cursor.getString(8));
            vorming.setTitel(cursor.getString(9));
            vorming.setWebsiteLocatie(cursor.getString(10));

        } else
        {
            vorming = null;
        }
        cursor.close();
        db.close();
        return vorming;
    }

    //Logica voor favorietentabel
    //Voegt de gegevens van de doorgegeven vakantie toe aan de favorietentabel
    /*
    Naam: toevoegenGegevensFavoriet
    Werking: Creëert de feedbacktabel in de locale database
    Parameters:
     - sqLiteDatabase: SQLiteDatabase - de DB waar mee gewerkt wordt
    */
    public Long toevoegenGegevensFavoriet(Vakantie favorieteVakantie)
    {
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_VAKANTIENAAM, favorieteVakantie.getNaamVakantie()); //1
        values.put(Constants.COLUMN_LOCATIE, favorieteVakantie.getLocatie()); //2
        values.put(Constants.COLUMN_VERTREKDATUM, favorieteVakantie.getVertrekDatum().toString()); //3
        values.put(Constants.COLUMN_TERUGDATUM, favorieteVakantie.getTerugkeerDatum().toString()); //4

        values.put(Constants.COLUMN_PRIJS,(Integer) favorieteVakantie.getBasisprijs()); //5
        values.put(Constants.COLUMN_AFBEELDING1, favorieteVakantie.getFoto1()); //6
        if (favorieteVakantie.getFotos().size() >= 2)
            values.put(Constants.COLUMN_AFBEELDING2, favorieteVakantie.getFoto2()); //7
        if (favorieteVakantie.getFotos().size() >= 3)
            values.put(Constants.COLUMN_AFBEELDING3, favorieteVakantie.getFoto3()); //8
        //values.put(Constants.COLUMN_DOELGROEP, vakantie.getDoelGroep());
        values.put(Constants.COLUMN_MAXDOELGROEP,(Integer) favorieteVakantie.getMaxDoelgroep()); //9
        values.put(Constants.COLUMN_MINDOELGROEP, (Integer)favorieteVakantie.getMinDoelgroep()); //10

        values.put(Constants.COLUMN_BESCHRIJVING, favorieteVakantie.getKorteBeschrijving()); //11
        values.put(Constants.COLUMN_PERIODE, favorieteVakantie.getPeriode()); //12
        values.put(Constants.COLUMN_VERVOER, favorieteVakantie.getVervoerswijze()); //13

        values.put(Constants.COLUMN_FORMULE, favorieteVakantie.getFormule()); //14
        values.put(Constants.COLUMN_MAXDEELNEMERS, (Integer) favorieteVakantie.getMaxAantalDeelnemers()); //15
        values.put(Constants.COLUMN_INBEGREPENINPRIJS, favorieteVakantie.getInbegrepenInPrijs()); //16
        values.put(Constants.COLUMN_BMLEDENPRIJS,(Integer) favorieteVakantie.getBondMoysonLedenPrijs()); //17


        if((Double)favorieteVakantie.getSterPrijs1Ouder()<0)
            favorieteVakantie.setSterPrijs1Ouder(0);
        if((Double)favorieteVakantie.getSterPrijs2Ouder()<0)
            favorieteVakantie.setSterPrijs2Ouder(0);

        values.put(Constants.COLUMN_STERPRIJSOUDER1,(Double) favorieteVakantie.getSterPrijs1Ouder()); //18
        values.put(Constants.COLUMN_STERPRIJS2OUDERS,(Double) favorieteVakantie.getSterPrijs2Ouder()); //19
        values.put(Constants.COLUMN_VAKANTIEID, favorieteVakantie.getActiviteitID()); //20
        values.put(Constants.COLUMN_GEMIDDELDERATING, favorieteVakantie.getGemiddeldeRating());
        SQLiteDatabase db = this.getWritableDatabase();

        Long id = db.insert(TABLE_FAVORIETEN, null, values);
        db.close();

        return id;

    }

    //Haalt een lijst van favorieten op uit de favorietentabel
    /*
    Naam: krijgFavorieten
    Werking: Creëert de feedbacktabel in de locale database
    Parameters:
     - sqLiteDatabase: SQLiteDatabase - de DB waar mee gewerkt wordt
    */
    public List<Vakantie> krijgFavorieten()
    {
        List<Vakantie> favorieten = new ArrayList<Vakantie>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_FAVORIETEN;
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            //overloop de favorieten en haal alle juiste gegevens op van de vakantie
            Vakantie f = krijgFavorieten(c.getString(1));
            favorieten.add(f);
            c.moveToNext();
        }
        c.close();
        return favorieten;
    }

    //Haalt een bepaalde vakantie op uit de favorietentabel
    public Vakantie krijgFavorieten(String vakantienaam)
    {
        String query = "Select * FROM " + TABLE_FAVORIETEN + " WHERE " + Constants.COLUMN_VAKANTIENAAM + " = \"" + vakantienaam + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Vakantie vakantie = new Vakantie();

        if(cursor.moveToFirst())
        {
            cursor.moveToFirst();
            vakantie.setNaamVakantie(cursor.getString(1));
            vakantie.setLocatie(cursor.getString(2));
            vakantie.setVertrekDatumString(cursor.getString(3));
            vakantie.setTerugDatumString(cursor.getString(4));
            vakantie.setBasisprijs(Double.parseDouble(cursor.getString(5)));
            vakantie.setFoto1(cursor.getString(6));
            vakantie.setFoto2(cursor.getString(7));
            vakantie.setFoto3(cursor.getString(8));
            vakantie.setMaxDoelgroep(Integer.parseInt(cursor.getString(9)));
            vakantie.setMinDoelgroep(Integer.parseInt(cursor.getString(10)));
            //vakantie.setDoelGroep(cursor.getString(9));
            vakantie.setKorteBeschrijving(cursor.getString(11));
            vakantie.setPeriode(cursor.getString(12));
            vakantie.setVervoerswijze(cursor.getString(13));
            vakantie.setFormule(cursor.getString(14));
            vakantie.setMaxAantalDeelnemers(Integer.parseInt(cursor.getString(15)));
            vakantie.setInbegrepenInPrijs(cursor.getString(16));
            vakantie.setBondMoysonLedenPrijs(Double.parseDouble(cursor.getString(17)));
            vakantie.setSterPrijs1Ouder(Double.parseDouble(cursor.getString(18)));
            vakantie.setSterPrijs2Ouder(Double.parseDouble(cursor.getString(19)));
            vakantie.setVakantieID(cursor.getString(20));
            vakantie.setGemiddeldeRating(Integer.parseInt(cursor.getString(21)));
        } else
        {
            vakantie = null;
        }
        cursor.close();
        db.close();
        return vakantie;

    }

    //Logica voor feedbacktabel
    //Voegt de gegevens van de doorgegeven feedback toe aan de feedbacktabel
    public Long toevoegenGegevensFeedback(Feedback feedback)
    {
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_FEEDBACK, feedback.getFeedback()); //1
        values.put(Constants.COLUMN_SCORE, Integer.parseInt(feedback.getScore().toString())); //2
        values.put(Constants.COLUMN_VAKANTIENAAMF, feedback.getVakantieNaam()); //3
        values.put(Constants.COLUMN_VAKANTIEID, feedback.getVakantieId()); //4

        values.put(Constants.COLUMN_GEBRUIKERID,feedback.getGebruikerId()); //5
        values.put(Constants.COLUMN_GEBRUIKER, feedback.getGebruiker()); //6

        if(feedback.getGoedgekeurd())
            values.put(Constants.COLUMN_GOEDGEKEURD, 1);
        else values.put(Constants.COLUMN_GOEDGEKEURD, 0);



        SQLiteDatabase db = this.getWritableDatabase();

        Long id = db.insert(TABLE_FEEDBACK, null, values);
        db.close();

        return id;

    }

    //Haalt een lijst van feedback op uit de feedbacktabel
    public List<Feedback> krijgFeedback()
    {
        List<Feedback> feedback = new ArrayList<Feedback>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_FEEDBACK;
        Cursor c = db.rawQuery(query,null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            //overloop de feedbacktabel en haal alle juiste gegevens op van die feedback
            Feedback f = krijgFeedback(c.getString(3));
            feedback.add(f);
            c.moveToNext();
        }
        c.close();
        return feedback;
    }

    //Haalt een bepaalde feedback op uit de feedbacktabel
    public Feedback krijgFeedback(String vakantienaam)
    {
        String query = "Select * FROM " + TABLE_FEEDBACK + " WHERE " + Constants.COLUMN_VAKANTIENAAMF + " = \"" + vakantienaam + "\"";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Feedback feedback = new Feedback();

        if(cursor.moveToFirst())
        {
            cursor.moveToFirst();
            feedback.setFeedback(cursor.getString(1));
            feedback.setScore(Integer.parseInt(cursor.getString(2)));
            feedback.setVakantieNaam(cursor.getString(3));
            feedback.setVakantieId(cursor.getString(4));
            feedback.setGebruikerId(cursor.getString(5));
            feedback.setGebruiker(cursor.getString(6));
            if(Integer.parseInt(cursor.getString(7)) == 1)
                feedback.setGoedgekeurd(true);
            else
                feedback.setGoedgekeurd(false);


        } else
        {
            feedback = null;
        }
        cursor.close();
        db.close();
        return feedback;

    }
}
