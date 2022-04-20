package com.example.rosebottablet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final Integer RecordAudioRequestCode = 1;

    //Avainsanojen Boolean
    public boolean keskustelu0 = false;
    public boolean keskustelu1 = false;
    public boolean keskustelu2 = false;
    public boolean keskustelu3 = false;
    public boolean keskustelu4 = false;

    public boolean kuinkaVoin0 = false;
    public boolean kuinkaVoin1 = false;
    public boolean kuinkaVoin2 = false;
    public boolean kuinkaVoin3 = false;
    public boolean kuinkaVoin4 = false;

    public boolean fantasia0 = false;
    public boolean fantasia1 = false;
    public boolean fantasia2 = false;
    public boolean fantasia3 = false;
    public boolean fantasia4 = false;
    public boolean fantasia5 = false;
    public boolean fantasia6 = false;
    public boolean fantasia7 = false;
    public boolean fantasia8 = false;
    public boolean fantasia9 = false;

    public boolean scifi0 = false;

    public boolean tiede0 = false;
    public boolean tiede1 = false;
    public boolean tiede2 = false;
    public boolean tiede3 = false;
    public boolean tiede4 = false;
    public boolean tiede5 = false;
    public boolean tiede6 = false;
    public boolean tiede7 = false;

    public boolean runot0 = false;
    public boolean runot1 = false;
    public boolean runot2 = false;
    public boolean runot3 = false;
    public boolean runot4 = false;
    public boolean runot5 = false;

    public boolean rikoja0 = false;
    public boolean rikoja1 = false;
    public boolean rikoja2 = false;
    public boolean rikoja3 = false;

    public boolean kiitos1 = false;
    public boolean oli = false;
    public boolean kiito = false;

    public boolean sijainti0 = false;
    public boolean sijainti1 = false;
    public boolean sijainti2 = false;
    public boolean sijainti3 = false;


    //Layout Objekteja
    static ImageView marker;
    static ImageView kartta;
    static ImageView hahmo;
    ImageView here;
    ImageView herek;
    TextView where;
    ImageButton voiceBtn;
    public static TextView text;
    static TextView text1;
    static Button converBtn;
    ImageButton resButton;
    static TextToSpeech textToSpeech;
    static ImageView gif;
    TextView otsikko;

    public int laskuri =  0;

    //Avain sana
    public static String word = "";

    private static final String LOG_TAG = "LogActivity";

    @SuppressLint("StaticFieldLeak")
    public static ViewGroup mainLayout;
    //Boolean joka määrittää mikä kategoria on valittu
    public static boolean sijainti = false;
    public static boolean fantasia = false;
    public static boolean runo = false;
    public static boolean rikojanitus;
    public static boolean scifi = false;
    public static boolean tiede = false;
    public static boolean kiitoksia = false;
    @SuppressLint("StaticFieldLeak")
    //Napit
    public Button fantasibtn;
    public Button runobtn;
    public Button rikojanbtn;
    public Button scifibtn;
    public Button tietobtn;
    public Button shlang;
    public Button kaunobtn;
    public Button tietogabtn;
    public Button ylakate;


    //Puheen tunnistus
    public static SpeechRecognizer speechRecognizer;
    public static Intent speechRecognizerIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Ylä bannerin piilpitus
        Objects.requireNonNull(getSupportActionBar()).hide();
        //Valitu kieli
        loadLocale();
        setContentView(R.layout.activity_main);


        //Kysyy mikrofoonin käyttöoikeutta
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }



        //Puheen tunnistuksen määrittelyä
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, loadLocale());

        //Puheen tunnistus
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {
                text1.setText("");
                text1.setHint("Listening...");

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle)
            {
                ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                if(matches != null){
                    //keeper = matches.get(0);

                    text.setText(matches.get(0));
                    word = text.getText().toString();
                    //Toast.makeText(MainActivity.this, "Result = " + keeper, Toast.LENGTH_LONG).show();
                    //con.avainSana();
                    //con.avainSana();
                    tunnistus();
                }
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });



        //Hakee objekteja id:n perusteella.
        mainLayout = (RelativeLayout) findViewById(R.id.main);
        marker = (ImageView) findViewById(R.id.marker);
        kartta = (ImageView) findViewById(R.id.kartta);
        hahmo = (ImageView) findViewById(R.id.hahmo);
        here = (ImageView) findViewById(R.id.here);
        herek = (ImageView) findViewById(R.id.herek);
        where = findViewById(R.id.where);

        fantasibtn = findViewById(R.id.fantasibtn);
        runobtn = findViewById(R.id.runobtn);
        rikojanbtn = findViewById(R.id.rikojanbtn);
        scifibtn = findViewById(R.id.scifibtn);
        tietobtn = findViewById(R.id.tietobtn);
        voiceBtn = findViewById(R.id.voiceBtn);
        text = findViewById(R.id.text);
        text1 = findViewById(R.id.text1);
        converBtn = findViewById(R.id.converBtn);
        resButton = findViewById(R.id.resButton);
        shlang = findViewById(R.id.shlang);
        //gif = findViewById(R.id.gif);
        otsikko = findViewById(R.id.otsikko);

        //Yläkategoriat napit
        kaunobtn = findViewById(R.id.kaunobtn);
        tietogabtn = findViewById(R.id.tietogabtn);
        ylakate = findViewById(R.id.ylakate);

        //Kielen vaihto bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        //Kielen vaihto nappula
        shlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLanguageDialog();
            }
        });

        //Puheen tuottaminen tekstistä
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int i) {

                if(i != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.forLanguageTag(loadLocale()));
                }

            }
        });

        //Aloitus nappula joka aloittaa toiminnan
        converBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Hakee stringin ja tuotaa sen puheeksi
                String x = getString(R.string.terve);
                textToSpeech.speak(x, TextToSpeech.QUEUE_FLUSH, null);

                converBtn.setVisibility(View.INVISIBLE);

                //Vaihtaa hahmo imagen tilalle gif animaation
                Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);

                //Handlerissä tuotetaan usempia toimintoja saman aikasesti
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //gif.setVisibility(View.INVISIBLE);
                        //hahmo.setVisibility(View.VISIBLE);

                        //Hahmon sijainti siirretään
                        RelativeLayout.LayoutParams hahmoRelativeLayout = new RelativeLayout.LayoutParams(1200, 1400);
                        hahmoRelativeLayout.leftMargin = 50;
                        hahmoRelativeLayout.topMargin = 120;
                        MainActivity.hahmo.setLayoutParams(hahmoRelativeLayout);

                        //Tuotetaan näkyviin objekteja
                        hahmo.setImageResource(R.drawable.androidukko);
                        voiceBtn.setVisibility(View.VISIBLE);
                        kaunobtn.setVisibility(View.VISIBLE);
                        tietogabtn.setVisibility(View.VISIBLE);

                        here.setVisibility(View.VISIBLE);
                        herek.setVisibility(View.VISIBLE);
                        where.setVisibility(View.VISIBLE);

                        kartta.setVisibility(View.VISIBLE);
                        text1.setVisibility(View.VISIBLE);

                        //Aloitetaan puheen kuuntelu puheen jälkeen.
                        speechRecognizer.startListening(speechRecognizerIntent);
                    }
                }, 10000); //10 sekunnin viive handlerin toimintaan.
            }
        });
        //Restart nappula joka hakee restart methodin
        resButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();
            }
        });


        //Kuva painike josta aukeaa puheen kuuntelu
        voiceBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                speechRecognizer.startListening(speechRecognizerIntent);
            }
        });


        //Yläkategorioiden nappien toiminnot
        kaunobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
                textToSpeech.speak(getString(R.string.kaunospeak), TextToSpeech.QUEUE_FLUSH, null);
                //Poistetaan pääkategotia napit käytöstä.
                kaunobtn.setVisibility(View.INVISIBLE);
                tietogabtn.setVisibility(View.INVISIBLE);

                //Avataan alakategorianapit käyttöön.
                fantasibtn.setVisibility(View.VISIBLE);
                scifibtn.setVisibility(View.VISIBLE);
                runobtn.setVisibility(View.VISIBLE);
                rikojanbtn.setVisibility(View.VISIBLE);

                ylakate.setVisibility(View.VISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.androidukko);
                    }
                }, 3600);

            }
        });

       tietogabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
                textToSpeech.speak(getString(R.string.tietospeak), TextToSpeech.QUEUE_FLUSH, null);
                //Poistetaan pääkategotia napit käytöstä.
                kaunobtn.setVisibility(View.INVISIBLE);
                tietogabtn.setVisibility(View.INVISIBLE);

                //Avataan alakategorianapit käyttöön.
                tietobtn.setVisibility(View.VISIBLE);

                ylakate.setVisibility(View.VISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.androidukko);
                    }
                }, 3600);
            }
        });

        //Nappulat valitsevat eri kategorioita

        //Fantasia nappi
        fantasibtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                speechRecognizer.stopListening();
                fantasia = true;
                vertaa();
            }
        });
        //Runo nappi
        runobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                runo = true;
                vertaa();
            }
        });
        //Rikos ja jännitys nappi
        rikojanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                rikojanitus = true;
                vertaa();
            }
        });
        //Scifi nappi
        scifibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechRecognizer.stopListening();
                scifi = true;
                vertaa();
            }
        });
        //Tietokirja nappi
        tietobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speechRecognizer.stopListening();
                tiede = true;
                vertaa();


            }
        });
        //Ylä kategoria nappulat
        ylakate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                kaunobtn.setVisibility(View.VISIBLE);
                tietogabtn.setVisibility(View.VISIBLE);


                fantasibtn.setVisibility(View.INVISIBLE);
                scifibtn.setVisibility(View.INVISIBLE);
                tietobtn.setVisibility(View.INVISIBLE);
                runobtn.setVisibility(View.INVISIBLE);
                rikojanbtn.setVisibility(View.INVISIBLE);

                ylakate.setVisibility(View.INVISIBLE);



            }
        });
    }




    //Verrataan contain() methodilla avain sana stringeihin kuunneltua puhetta.
    //Mikäli avain sana löytyy puheesta muuttuu sitä vastaava boolean true:ksi.
    //True booleanin alta löytyvä toiminta toteutetaan.
    public void tunnistus() {
        //Contain con = new Contain();

        /*Thread thread = new Thread() {

            public void run() {*/

                //Käytetään contains() methodia avain sana stringien läpiköymiseksi.
                keskustelu0 = word.contains(getString(R.string.kesk0));
                keskustelu1 = word.contains(getString(R.string.kesk1));
                keskustelu2 = MainActivity.word.contains(getString(R.string.kesk2));
                keskustelu3 = MainActivity.word.contains(getString(R.string.kesk3));
                keskustelu4 = MainActivity.word.contains(getString(R.string.kesk4));

                kuinkaVoin0 = MainActivity.word.contains(getString(R.string.kuin0));
                kuinkaVoin1 = MainActivity.word.contains(getString(R.string.kuin1));
                kuinkaVoin2 = MainActivity.word.contains(getString(R.string.kuin2));
                kuinkaVoin3 = MainActivity.word.contains(getString(R.string.kuin3));
                kuinkaVoin4 = MainActivity.word.contains(getString(R.string.kuin4));

                fantasia0 = MainActivity.word.contains(getString(R.string.fanta0));
                fantasia1 = MainActivity.word.contains(getString(R.string.fanta1));
                fantasia2 = MainActivity.word.contains(getString(R.string.fanta2));
                fantasia3 = MainActivity.word.contains(getString(R.string.fanta3));
                fantasia4 = MainActivity.word.contains(getString(R.string.fanta4));
                fantasia5 = MainActivity.word.contains(getString(R.string.fanta5));
                fantasia6 = MainActivity.word.contains(getString(R.string.fanta6));
                fantasia7 = MainActivity.word.contains(getString(R.string.fanta7));
                fantasia8 = MainActivity.word.contains(getString(R.string.fanta8));
                fantasia9 = MainActivity.word.contains(getString(R.string.fanta9));

                scifi0 = MainActivity.word.contains(getString(R.string.scif0));

                tiede0 = MainActivity.word.contains(getString(R.string.tiet0));
                tiede1 = MainActivity.word.contains(getString(R.string.tiet1));
                tiede2 = MainActivity.word.contains(getString(R.string.tiet2));
                tiede3 = MainActivity.word.contains(getString(R.string.tiet3));
                tiede4 = MainActivity.word.contains(getString(R.string.tiet4));
                tiede5 = MainActivity.word.contains(getString(R.string.tiet5));
                tiede6 = MainActivity.word.contains(getString(R.string.tiet6));
                tiede7 = MainActivity.word.contains(getString(R.string.tiet7));

                runot0 = MainActivity.word.contains(getString(R.string.runo0));
                runot1 = MainActivity.word.contains(getString(R.string.runo1));
                runot2 = MainActivity.word.contains(getString(R.string.runo2));
                runot3 = MainActivity.word.contains(getString(R.string.runo3));
                runot4 = MainActivity.word.contains(getString(R.string.runo4));
                runot5 = MainActivity.word.contains(getString(R.string.runo5));

                rikoja0 = MainActivity.word.contains(getString(R.string.rikojan0));
                rikoja1 = MainActivity.word.contains(getString(R.string.rikojan1));
                rikoja2 = MainActivity.word.contains(getString(R.string.rikojan2));
                rikoja3 = MainActivity.word.contains(getString(R.string.rikojan3));

                kiitos1 = word.contains(getString(R.string.kiits1));
                kiito = MainActivity.word.contains(getString(R.string.kiits0));
                oli = MainActivity.word.contains(getString(R.string.kiits1));


                sijainti0 = MainActivity.word.contains(getString(R.string.sijan0));
                sijainti1 = MainActivity.word.contains(getString(R.string.sijan1));
                sijainti2 = MainActivity.word.contains(getString(R.string.sijan2));
                sijainti3 = MainActivity.word.contains(getString(R.string.sijan3));
            /*}
        };thread.start();*/
        //Log.e(LOG_TAG, "MyClass.getView() — get item number ");

        //Mikäli boolean on true toteutetaan sen alla oleva toiminta.
        if (fantasia0 || fantasia1 || fantasia2 || fantasia3 || fantasia4|| fantasia5|| fantasia6|| fantasia7|| fantasia8|| fantasia9) {

            fantasia = true;
            laskuri = 0;
            vertaa();
        } else if (scifi0) {
            scifi = true;
            laskuri = 0;
            vertaa();
        } else if (sijainti0 || sijainti1 || sijainti2 || sijainti3) {
            sijainti = true;
            laskuri = 0;
            vertaa();
        } else if (tiede0 || tiede1 || tiede2|| tiede3|| tiede4|| tiede5|| tiede6|| tiede7) {
            tiede = true;
            laskuri = 0;
            vertaa();
        }else if (runot0 || runot1 || runot2 || runot3 || runot4 || runot5) {
            runo = true;
            laskuri = 0;
            vertaa();
        }else if(rikoja0 || rikoja1 || rikoja2 || rikoja3) {
            rikojanitus = true;
            laskuri = 0;
            vertaa();
        }else if (kiito || oli || kiitos1) {
            kiitoksia = true;
            vertaa();
        } else if (keskustelu0 || keskustelu1 || keskustelu2 || keskustelu3 ||
                keskustelu4) {
            MainActivity.textToSpeech.speak(getString(R.string.keskus), TextToSpeech.QUEUE_FLUSH, null);
            speechRecognizer.startListening(speechRecognizerIntent);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            laskuri = 0;

        }else if (kuinkaVoin0 || kuinkaVoin1 || kuinkaVoin2 ||  kuinkaVoin3 || kuinkaVoin4 ){
            MainActivity.textToSpeech.speak(getString(R.string.kuuluu), TextToSpeech.QUEUE_FLUSH, null);
            speechRecognizer.startListening(speechRecognizerIntent);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            laskuri = 0;

        }else{
            //Mikäli puuheesta ei löydetty avain sanoja lisätään int "laskuri" yksi arvo.
            ++laskuri;
            //String x = getString(R.string.enymr);
            //MainActivity.text1.setText("Anteeksi minä en ymmärtänyt sinua. Voitko kysyä uudelleen?");

                //Mikäli int "laskuri" arvo on kaksi pysäytetään kuuntelu kierre ja annetaan uudet ohjeet.
            if (laskuri == 2) {
                MainActivity.textToSpeech.speak(getString(R.string.kysyuu), TextToSpeech.QUEUE_FLUSH, null);
                speechRecognizer.stopListening();
                laskuri = 0;

                //Mikäli int "laskuri" arvo on alle 2 pyydetään kysymään uudelleen.
            } else{
                MainActivity.textToSpeech.speak(getString(R.string.enymr), TextToSpeech.QUEUE_FLUSH, null);
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //ALoitetaan uudelleen kuuntelu
                speechRecognizer.startListening(speechRecognizerIntent);
            }
        }
    }


    //Vertaa() methodissa toteutetaan toiminta mikö on valittu joko nappulalla tai avainsanan löytämisellä.
    public void vertaa() {



        if (MainActivity.sijainti) {

            ylakate.setVisibility(View.VISIBLE);

            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);
            markerRelativeLayout.leftMargin = 1600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
            //String x = getString(R.string.mispuhe);
            MainActivity.textToSpeech.speak(getString(R.string.mispuhe), TextToSpeech.QUEUE_FLUSH, null);
            MainActivity.sijainti = false;
            sijainti0 = false;
            sijainti1 = false;
            sijainti2 = false;
            sijainti3 = false;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.androidukko);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);

        }


        if (MainActivity.fantasia) {

            //String x = getString(R.string.fantapuhe);
            marker.setVisibility(View.VISIBLE);

            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 1050;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
            MainActivity.textToSpeech.speak(getString(R.string.fantapuhe), TextToSpeech.QUEUE_FLUSH, null);

            MainActivity.fantasia = false;
            fantasia0 = false;
            fantasia1 = false;
            fantasia2 = false;
            fantasia3 = false;
            fantasia4 = false;
            fantasia5 = false;
            fantasia6 = false;
            fantasia7 = false;
            fantasia8 = false;
            fantasia9 = false;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.androidukko);
                }
            }, 3600);
        }

        if (runo) {

            //String x = getString(R.string.fantapuhe);

            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 1050;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
            MainActivity.textToSpeech.speak(getString(R.string.runopuhe), TextToSpeech.QUEUE_FLUSH, null);

            runo = false;
            runot0 = false;
            runot1 = false;
            runot2 = false;
            runot3 = false;
            runot4 = false;
            runot5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.androidukko);
                }
            }, 3600);
        }

        if (rikojanitus) {

            //String x = getString(R.string.fantapuhe);
            marker.setVisibility(View.VISIBLE);

            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 1050;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
            MainActivity.textToSpeech.speak(getString(R.string.rikojanpuhe), TextToSpeech.QUEUE_FLUSH, null);

            rikojanitus = false;
            rikoja0 = false;
            rikoja1 = false;
            rikoja2 = false;
            rikoja3 = false;



            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.androidukko);
                }
            }, 3600);
        }



        if (MainActivity.tiede) {
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.tiedpuhe), TextToSpeech.QUEUE_FLUSH, null);

            MainActivity.tiede = false;
            tiede0 = false;
            tiede1 = false;
            tiede2 = false;
            tiede3 = false;
            tiede4 = false;
            tiede5 = false;
            tiede6 = false;
            tiede7 = false;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.androidukko);
                }
            }, 3600);
        }


        if (MainActivity.scifi) {
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //markerRelativeLayout.bottomMargin = 235;
            markerRelativeLayout.leftMargin = 1700;
            //markerRelativeLayout.rightMargin = 50;
            markerRelativeLayout.topMargin = 900;

            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            MainActivity.marker.getLayoutParams().height = 60;
            MainActivity.marker.getLayoutParams().width = 60;
            MainActivity.marker.requestLayout();

            Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
            // String x = getString(R.string.scifipuhe);
            MainActivity.textToSpeech.speak(getString(R.string.scifipuhe), TextToSpeech.QUEUE_FLUSH, null);
            MainActivity.scifi = false;
            scifi0 = false;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.androidukko);
                }
            }, 3600);
        }
        if (MainActivity.kiitoksia) {

            Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
            //String x = getString(R.string.kiitos);
            MainActivity.textToSpeech.speak(getString(R.string.kiitos), TextToSpeech.QUEUE_FLUSH, null);

            MainActivity.kiitoksia = false;
            kiito = false;
            oli = false;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.androidukko);
                }
            }, 3600);


            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            restart();
        }
    }

    public void restart(){

        //Intent intent = getIntent()
        //finish();
        //startActivity(getIntent());

        recreate();

    }

    @Override
    protected void onPause(){

        if (textToSpeech != null){
            textToSpeech.stop();

        }
        super.onPause();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RecordAudioRequestCode && grantResults.length > 0 ){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
        }
    }

    private void showLanguageDialog(){
        final String[] listItems = {"English", "Suomi"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0){
                    //english
                    setLocale("en");
                    recreate();
                }else if(i == 1){
                    //Suomi
                    setLocale("fi");
                    recreate();
                }
                dialogInterface.dismiss();

            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }
    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }
    public String loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
        return language;
    }
}