package com.example.rosebottablet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.location.GnssAntennaInfo;
import android.media.Image;
import android.media.ImageReader;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
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

import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    public static final Integer RecordAudioRequestCode = 1;

    //Avainsanojen Boolean

    //Kaunokirjallisuuden avain sanat
    //Baabel avainsanat
    public  boolean baabel0 = false;
    public  boolean baabel1 = false;
    public  boolean baabel2 = false;
    public  boolean baabel3 = false;
    public  boolean baabel4 = false;
    public  boolean baabel5 = false;

    //Bundle avainsanat
    public  boolean bundle0 = false;
    public  boolean bundle1 = false;
    public  boolean bundle2 = false;
    public  boolean bundle3 = false;
    public  boolean bundle4 = false;
    public  boolean bundle5 = false;

    // Essee avain sanat
    public boolean essee0 = false;
    public boolean essee1 = false;
    public boolean essee2 = false;
    public boolean essee3 = false;
    public boolean essee4 = false;
    public boolean essee5 = false;

    //Kaunokirjallisuuden avainsanat
    public boolean kauno0 = false;
    public boolean kauno1 = false;
    public boolean kauno2 = false;
    public boolean kauno3 = false;
    public boolean kauno4 = false;
    public boolean kauno5 = false;

    //Keltainen kirjasto avainsanat
    public boolean kelta0 =false;
    public boolean kelta1 =false;
    public boolean kelta2 =false;
    public boolean kelta3 =false;
    public boolean kelta4 =false;
    public boolean kelta5 =false;

    //Kotimainen kaunokirjallisuus avainsanat
    public boolean kotkauno0 = false;
    public boolean kotkauno1 = false;
    public boolean kotkauno2 = false;
    public boolean kotkauno3 = false;
    public boolean kotkauno4 = false;
    public boolean kotkauno5 = false;

    //Like avainsanat
    public boolean lik0 = false;
    public boolean lik1 = false;
    public boolean lik2 = false;
    public boolean lik3 = false;
    public boolean lik4 = false;
    public boolean lik5 = false;

    //Otava avainsanat
    public boolean otav0 = false;
    public boolean otav1 = false;
    public boolean otav2 = false;
    public boolean otav3 = false;
    public boolean otav4 = false;
    public boolean otav5 = false;

    //Äänikirjojen avainsanat
    public boolean aani0 = false;
    public boolean aani1 = false;
    public boolean aani2 = false;
    public boolean aani3 = false;
    public boolean aani4 = false;
    public boolean aani5 = false;


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

    //Tietokirjallisuuden avainsanat
    //Ukraina ja venäjä avainsanat
    public boolean ukrvena0 = false;
    public boolean ukrvena1 = false;
    public boolean ukrvena2 = false;
    public boolean ukrvena3 = false;
    public boolean ukrvena4 = false;
    public boolean ukrvena5 = false;

    //Elokuva ja teatteri avainsanat
    public boolean elokteat0 = false;
    public boolean elokteat1 = false;
    public boolean elokteat2 = false;
    public boolean elokteat3 = false;
    public boolean elokteat4 = false;
    public boolean elokteat5 = false;

    //Elämänkerta avainsanat
    public boolean elamank0 = false;
    public boolean elamank1 = false;
    public boolean elamank2 = false;
    public boolean elamank3 = false;
    public boolean elamank4 = false;
    public boolean elamank5 = false;

    //Elämänviisaus avainsanat
    public boolean elamanv0 = false;
    public boolean elamanv1 = false;
    public boolean elamanv2 = false;
    public boolean elamanv3 = false;
    public boolean elamanv4 = false;
    public boolean elamanv5 = false;

    //Filosofia avainsanat
    public boolean filo0 = false;
    public boolean filo1 = false;
    public boolean filo2 = false;
    public boolean filo3 = false;
    public boolean filo4 = false;
    public boolean filo5 = false;

    //Historia avainsanat
    public boolean hist0 = false;
    public boolean hist1 = false;
    public boolean hist2 = false;
    public boolean hist3 = false;
    public boolean hist4 = false;
    public boolean hist5 = false;

    //Musiikki avainsanat
    public boolean musii0 = false;
    public boolean musii1 = false;
    public boolean musii2 = false;
    public boolean musii3 = false;
    public boolean musii4 = false;
    public boolean musii5 = false;

    //Politiikka avainsanat
    public boolean polit0 = false;
    public boolean polit1 = false;
    public boolean polit2 = false;
    public boolean polit3 = false;
    public boolean polit4 = false;
    public boolean polit5 = false;

    //Sanakirjat avainsanat
    public boolean sanak0 = false;
    public boolean sanak1 = false;
    public boolean sanak2 = false;
    public boolean sanak3 = false;
    public boolean sanak4 = false;
    public boolean sanak5 = false;

    //Tietokirjat avainsanat
    public boolean tiede0 = false;
    public boolean tiede1 = false;
    public boolean tiede2 = false;
    public boolean tiede3 = false;
    public boolean tiede4 = false;
    public boolean tiede5 = false;
    public boolean tiede6 = false;
    public boolean tiede7 = false;

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

    //Kamera preview ja tallennuskohteet
    private TextureView textureView;
    private ImageView imageView;
    private ImageView imageView2;

    //Kamera bitmapit
    static Bitmap bitmap;
    static Bitmap bitmap2;
    static Bitmap resized;
    static Bitmap resized2;

    Timer timer = new Timer();
    Handler handler = new Handler();
    Handler handler1 = new Handler();

    //Logcat LOG ilmoitus
    private static final Float LOG_TAG = compareEquivalance();


    int kuva = 0;

    private  static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    static {
        ORIENTATIONS.append(Surface.ROTATION_0,90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    private String cameraId;
    private CameraDevice cameraDevice;
    private CameraCaptureSession cameraCaptureSessions;
    private CaptureRequest.Builder captureRequestBuilder;
    private Size imageDimension;
    private ImageReader imageReader;

    private File file;
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private boolean nFlashSupported;
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;

    boolean ihminen = false;


    int kuvalasku;
    public int laskuri =  0;

    //Avain sana
    public static String word = "";


    @SuppressLint("StaticFieldLeak")
    public static ViewGroup mainLayout;
    //Boolean joka määrittää mikä kategoria on valittu
    //Kaunokirjallisuus booleanit
    public static boolean baabel = false;
    public static boolean bundle = false;
    public static boolean essee = false;
    public static boolean kaunokirjallisuus = false;
    public static boolean keltainen = false;
    public static boolean kotikaunokirjallisuus = false;
    public static boolean like = false;
    public static boolean otava = false;
    public static boolean aanikirja = false;
    public static boolean fantasia = false;
    public static boolean runo = false;
    public static boolean rikojanitus;
    public static boolean scifi = false;

    //Tietokirjallisuus booleanit
    public static boolean ukrainajavenaja = false;
    public static boolean elokuvajateatteri = false;
    public static boolean elamankerta = false;
    public static boolean elamanviisaus = false;
    public static boolean filosofia = false;
    public static boolean historia = false;
    public static boolean musiikki = false;
    public static boolean politiikka = false;
    public static boolean sanakirja = false;
    public static boolean tiede = false;

    public static boolean kiitoksia = false;
    public static boolean sijainti = false;
    @SuppressLint("StaticFieldLeak")
    //Napit

    //Kaunokirjallisuus napit
    public Button baabebtn;
    public Button bundlbtn;
    public Button esseebtn;
    public Button kaunokibtn;
    public Button keltainbtn;
    public Button kotikaunobtn;
    public Button likebtn;
    public Button otavabtn;
    public Button aanikibtn;
    public Button fantasibtn;
    public Button runobtn;
    public Button rikojanbtn;
    public Button scifibtn;

    //Tietokirjallisuus napit
    public Button ukravenabtn;
    public Button eloteatbtn;
    public Button elamankbtn;
    public Button elamanvbtn;
    public Button filobtn;
    public Button histbtn;
    public Button musiibtn;
    public Button politbtn;
    public Button sanakbtn;
    public Button tietobtn;


    public Button shlang;


    public Button kaunobtn;
    public Button tietogabtn;
    public Button lapsetbtn;
    public Button pokkarbtn;
    public Button sarjabtn;
    public Button elokuvbtn;
    public Button lehdetbtn;
    public Button englanbtn;


    public Button ylakate;


    //Puheen tunnistus
    public static SpeechRecognizer speechRecognizer;
    public static Intent speechRecognizerIntent;



    CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            cameraDevice =camera;
            //creatCameraPreview();
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            cameraDevice.close();
        }

        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int i) {
            cameraDevice.close();
            cameraDevice=null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Ylä bannerin piilpitus
        Objects.requireNonNull(getSupportActionBar()).hide();
        //Valitu kieli
        loadLocale();

        otakuvia();
        setContentView(R.layout.activity_main);


        //Kysyy mikrofoonin käyttöoikeutta
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
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
            public void onResults(Bundle bundle) {
                ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                if (matches != null) {
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

        //Kameran objektien haku
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);

        textureView = (TextureView) findViewById(R.id.textureView);
        assert textureView != null;
        textureView.setSurfaceTextureListener(textureListener);


        //Hakee objekteja id:n perusteella.
        mainLayout = (RelativeLayout) findViewById(R.id.main);
        marker = (ImageView) findViewById(R.id.marker);
        kartta = (ImageView) findViewById(R.id.kartta);
        hahmo = (ImageView) findViewById(R.id.hahmo);
        here = (ImageView) findViewById(R.id.here);
        herek = (ImageView) findViewById(R.id.herek);
        where = findViewById(R.id.where);

        //Kaunokirjallisuus nappien objektien haku
        baabebtn = findViewById(R.id.baabebtn);
        bundlbtn = findViewById(R.id.bundlbtn);
        esseebtn = findViewById(R.id.esseebtn);
        kaunokibtn = findViewById(R.id.kaunokibtn);
        keltainbtn = findViewById(R.id.keltainbtn);
        kotikaunobtn = findViewById(R.id.kotikaunobtn);
        likebtn = findViewById(R.id.likebtn);
        otavabtn = findViewById(R.id.otavabtn);
        aanikibtn = findViewById(R.id.aanikibtn);
        fantasibtn = findViewById(R.id.fantasibtn);
        runobtn = findViewById(R.id.runobtn);
        rikojanbtn = findViewById(R.id.rikojanbtn);
        scifibtn = findViewById(R.id.scifibtn);

        //Tietokirjallisuus nappien objektien haku
        ukravenabtn = findViewById(R.id.ukravenabtn);
        eloteatbtn = findViewById(R.id.eloteatbtn);
        elamankbtn = findViewById(R.id.elamankbtn);
        elamanvbtn = findViewById(R.id.elamanvbtn);
        filobtn = findViewById(R.id.filobtn);
        histbtn = findViewById(R.id.histbtn);
        musiibtn = findViewById(R.id.musiibtn);
        politbtn = findViewById(R.id.politbtn);
        sanakbtn = findViewById(R.id.sanakbtn);
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
        lapsetbtn = findViewById(R.id.lapsetbtn);
        pokkarbtn = findViewById(R.id.pokkarbtn);
        sarjabtn = findViewById(R.id.sarjabtn);
        elokuvbtn = findViewById(R.id.elokuvbtn);
        lehdetbtn = findViewById(R.id.lehdetbtn);
        englanbtn = findViewById(R.id.englanbtn);


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

                if (i != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.forLanguageTag(loadLocale()));
                }

            }
        });

       /* timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //what you want to do
                kuvalasku++;
                takePicture();
                Log.e(String.valueOf(LOG_TAG), "Kuva numero " + kuvalasku);



            }
        }, 0, 2000);
        if(ihminen) {
            converBtn.callOnClick();
        }*/

        //Aloitus nappula joka aloittaa toiminnan
        converBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                    timer.cancel();
                    aloita();

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
                lapsetbtn.setVisibility(View.INVISIBLE);
                pokkarbtn.setVisibility(View.INVISIBLE);
                sarjabtn.setVisibility(View.INVISIBLE);
                elokuvbtn.setVisibility(View.INVISIBLE);
                lehdetbtn.setVisibility(View.INVISIBLE);
                englanbtn.setVisibility(View.INVISIBLE);

                //Avataan alakategorianapit käyttöön.
                fantasibtn.setVisibility(View.VISIBLE);
                scifibtn.setVisibility(View.VISIBLE);
                runobtn.setVisibility(View.VISIBLE);
                rikojanbtn.setVisibility(View.VISIBLE);
                baabebtn.setVisibility(View.VISIBLE);
                bundlbtn.setVisibility(View.VISIBLE);
                esseebtn.setVisibility(View.VISIBLE);
                kaunokibtn.setVisibility(View.VISIBLE);
                keltainbtn.setVisibility(View.VISIBLE);
                kotikaunobtn.setVisibility(View.VISIBLE);
                likebtn.setVisibility(View.VISIBLE);
                otavabtn.setVisibility(View.VISIBLE);
                aanikibtn.setVisibility(View.VISIBLE);

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
                lapsetbtn.setVisibility(View.INVISIBLE);
                pokkarbtn.setVisibility(View.INVISIBLE);
                sarjabtn.setVisibility(View.INVISIBLE);
                elokuvbtn.setVisibility(View.INVISIBLE);
                lehdetbtn.setVisibility(View.INVISIBLE);
                englanbtn.setVisibility(View.INVISIBLE);

                //Avataan alakategorianapit käyttöön.
                tietobtn.setVisibility(View.VISIBLE);
                ukravenabtn.setVisibility(View.VISIBLE);
                eloteatbtn.setVisibility(View.VISIBLE);
                elamankbtn.setVisibility(View.VISIBLE);
                elamanvbtn.setVisibility(View.VISIBLE);
                filobtn.setVisibility(View.VISIBLE);
                histbtn.setVisibility(View.VISIBLE);
                musiibtn.setVisibility(View.VISIBLE);
                politbtn.setVisibility(View.VISIBLE);
                sanakbtn.setVisibility(View.VISIBLE);

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

        lapsetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        pokkarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        sarjabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        elokuvbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        lehdetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        englanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        //Nappulat valitsevat eri kategorioita

        //Kaunokirjallisuus napit.
        //Baabel nappi
        baabebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                baabel = true;
                vertaa();
            }
        });

        //Bundle nappi
        bundlbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                bundle = true;
                vertaa();
            }
        });

        //Esseet nappi
        esseebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                essee = true;
                vertaa();
            }
        });

        //Kaunokirjallisuus nappi
        kaunokibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                kaunokirjallisuus = true;
                vertaa();
            }
        });

        //Keltainen kirjasto nappi
        keltainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                keltainen = true;
                vertaa();
            }
        });

        //Kotimainen kaunokirjallisuus nappi
        kotikaunobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                kotikaunokirjallisuus = true;
                vertaa();
            }
        });

        //Like nappi
        likebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                like = true;
                vertaa();
            }
        });

        //Otavan kirjaston nappi
        otavabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                otava = true;
                vertaa();
            }
        });

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

        //Tietokirjallisuuden napit
        //Ukraina ja venäjä nappi
        ukravenabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                ukrainajavenaja = true;
                vertaa();
            }
        });

        //Elokuva ja teatteri nappi
        eloteatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                elokuvajateatteri = true;
                vertaa();
            }
        });

        //Elämänkerrat nappi
        elamankbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                elamankerta = true;
                vertaa();
            }
        });

        //Elämän viisaus nappi
        elamanvbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                elamanviisaus = true;
                vertaa();
            }
        });

        //Filosofia nappi
        filobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                filosofia = true;
                vertaa();
            }
        });

        //Historia nappi
        histbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                historia = true;
                vertaa();
            }
        });

        //Musiikki nappi
        musiibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                musiikki = true;
                vertaa();
            }
        });

        //Politiikka nappi
        politbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                politiikka = true;
                vertaa();
            }
        });

        //Sanakirja nappi
        sanakbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechRecognizer.stopListening();
                sanakirja = true;
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
                lapsetbtn.setVisibility(View.VISIBLE);
                pokkarbtn.setVisibility(View.VISIBLE);
                sarjabtn.setVisibility(View.VISIBLE);
                elokuvbtn.setVisibility(View.VISIBLE);
                lehdetbtn.setVisibility(View.VISIBLE);
                englanbtn.setVisibility(View.VISIBLE);


                fantasibtn.setVisibility(View.INVISIBLE);
                scifibtn.setVisibility(View.INVISIBLE);
                tietobtn.setVisibility(View.INVISIBLE);
                runobtn.setVisibility(View.INVISIBLE);
                rikojanbtn.setVisibility(View.INVISIBLE);
                baabebtn.setVisibility(View.INVISIBLE);
                bundlbtn.setVisibility(View.INVISIBLE);
                esseebtn.setVisibility(View.INVISIBLE);
                kaunokibtn.setVisibility(View.INVISIBLE);
                keltainbtn.setVisibility(View.INVISIBLE);
                kotikaunobtn.setVisibility(View.INVISIBLE);
                likebtn.setVisibility(View.INVISIBLE);
                otavabtn.setVisibility(View.INVISIBLE);
                aanikibtn.setVisibility(View.INVISIBLE);

                ylakate.setVisibility(View.INVISIBLE);



            }
        });
    }


    private void takePicture() {


        if (cameraDevice == null)
            return;
        CameraManager manager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try{
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraDevice.getId());
            Size[] jpegSizes = null;
            if (characteristics !=null)
                jpegSizes =characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
                        .getOutputSizes(ImageFormat.JPEG);

            int width = 640;
            int height = 400;
            if(jpegSizes != null && jpegSizes.length >  0){
                width = jpegSizes[0].getWidth();
                height = jpegSizes[0].getHeight();

            }
            ImageReader reader = ImageReader.newInstance(width,height,ImageFormat.JPEG, 1);
            List<Surface> outputSurface = new ArrayList<>(2);
            outputSurface.add(reader.getSurface());
            outputSurface.add(new Surface(textureView.getSurfaceTexture()));

            CaptureRequest.Builder captureBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            captureBuilder.addTarget(reader.getSurface());
            captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);

            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            captureBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATIONS.get(rotation));


            ImageReader.OnImageAvailableListener readerListener = new ImageReader.OnImageAvailableListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onImageAvailable(ImageReader imageReader) {
                    Image image = null;
                    try {

                        image = reader.acquireLatestImage();
                        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                        byte[] bytes = new byte[buffer.capacity()];
                        buffer.get(bytes);

                        if(kuva == 0){
                            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            resized = Bitmap.createScaledBitmap(bitmap, 10, 10, true);
                            resized.getColorSpace();

                            imageView.setImageBitmap(bitmap);

                            kuva++;
                        }else if (kuva == 1){
                            bitmap2 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                            resized2 = Bitmap.createScaledBitmap(bitmap2, 10, 10, true);

                            imageView2.setImageBitmap(bitmap2);


                            kuva--;
                        }

                        int[] pixels1 = new int[resized.getWidth() * resized.getHeight()];
                        int[] pixels2 = new int[resized2.getWidth() * resized2.getHeight()];
                        resized.getPixels(pixels1, 0, resized.getWidth(), 0, 0, resized.getWidth(), resized.getHeight());
                        resized2.getPixels(pixels2, 0, resized2.getWidth(), 0, 0, resized2.getWidth(), resized2.getHeight());


                        Log.e(String.valueOf(LOG_TAG), "eroavaisuus on " + String.valueOf(compareEquivalance()));

                        if (compareEquivalance() < 0.26){
                            ihminen = true;
                            text1.setText("Joku on muuttunut");
                        }else {
                            text1.setText("mikään ei ole muuttunut");
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(image !=null){
                        image.close();
                        //  }
                    }
                }
            };
            reader.setOnImageAvailableListener(readerListener, mBackgroundHandler);
            CameraCaptureSession.CaptureCallback captureListener = new CameraCaptureSession.CaptureCallback() {
                @Override
                public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
                    super.onCaptureCompleted(session, request, result);
                   // Toast.makeText(MainActivity.this, "Saved "+file, Toast.LENGTH_SHORT).show();
                    //creatCameraPreview();
                }
            };

            cameraDevice.createCaptureSession(outputSurface, new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    try {
                        cameraCaptureSession.capture(captureBuilder.build(), captureListener,mBackgroundHandler);
                    }catch (CameraAccessException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {

                }
            },mBackgroundHandler);

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void otakuvia(){

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            //what you want to do
                            if (!ihminen) {
                                kuvalasku++;
                                takePicture();
                                Log.e(String.valueOf(LOG_TAG), "Kuva numero " + kuvalasku);
                            }
                        }



                    }, 0, 2000);
                }

    public void aloita(){

        //Hakee stringin ja tuotaa sen puheeksi
        String x = getString(R.string.terve);
        textToSpeech.speak(x, TextToSpeech.QUEUE_FLUSH, null);

        converBtn.setVisibility(View.INVISIBLE);


        //Vaihtaa hahmo imagen tilalle gif animaation
        Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);

        //Handlerissä tuotetaan usempia toimintoja saman aikasesti


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
                lapsetbtn.setVisibility(View.VISIBLE);
                pokkarbtn.setVisibility(View.VISIBLE);
                sarjabtn.setVisibility(View.VISIBLE);
                elokuvbtn.setVisibility(View.VISIBLE);
                lehdetbtn.setVisibility(View.VISIBLE);
                englanbtn.setVisibility(View.VISIBLE);

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

    public static float compareEquivalance() {

        if (bitmap == null || bitmap2 == null || bitmap.getWidth() != bitmap2.getWidth()
                || bitmap.getHeight() != bitmap2.getHeight()) {
            return 0;
        }

        ByteBuffer buffer1 = ByteBuffer.allocate(bitmap.getHeight() * bitmap.getRowBytes());
        bitmap.copyPixelsToBuffer(buffer1);

        ByteBuffer buffer2 = ByteBuffer.allocate(bitmap2.getHeight() * bitmap2.getRowBytes());
        bitmap2.copyPixelsToBuffer(buffer2);

        byte[] array1 = buffer1.array();
        byte[] array2 = buffer2.array();

        int len = array1.length; // array1 and array2 will be of some length.
        int count = 10;

        for (int i = 0; i < len; i++) {
            if (array1[i] == array2[i]) {
                count++;
            }
        }
        //return tulos = count / len;
        return ((float) (count)) / len;
    }


    private void openCamera() {
        CameraManager manager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try{
            cameraId = manager.getCameraIdList()[1];
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            assert map != null;
            imageDimension = map.getOutputSizes(SurfaceTexture.class)[0];
            //Check realtime permission if run higher API 23
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                },REQUEST_CAMERA_PERMISSION);
                return;
            }
            manager.openCamera(cameraId,stateCallback,null);

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {
            openCamera();
        }

        @Override
        public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int i, int i1) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {

        }
    };



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


        //Kaunokirjallisuus tunnistus
        //Baabel
        baabel0 = word.contains(getString(R.string.baabe0));
        baabel1 = word.contains(getString(R.string.baabe1));
        baabel2 = word.contains(getString(R.string.baabe2));
        baabel3 = word.contains(getString(R.string.baabe3));
        baabel4 = word.contains(getString(R.string.baabe4));
        baabel5 = word.contains(getString(R.string.baabe5));

        //Bundle
        bundle0 = word.contains(getString(R.string.bundl0));
        bundle1 = word.contains(getString(R.string.bundl1));
        bundle2 = word.contains(getString(R.string.bundl2));
        bundle3 = word.contains(getString(R.string.bundl3));
        bundle4 = word.contains(getString(R.string.bundl4));
        bundle5 = word.contains(getString(R.string.bundl5));

        //Essee
        essee0 = word.contains(getString(R.string.essee0));
        essee1 = word.contains(getString(R.string.essee1));
        essee2 = word.contains(getString(R.string.essee2));
        essee3 = word.contains(getString(R.string.essee3));
        essee4 = word.contains(getString(R.string.essee4));
        essee5 = word.contains(getString(R.string.essee5));

        //Kaunokirjallisus
        kauno0 = word.contains(getString(R.string.kauno0));
        kauno1 = word.contains(getString(R.string.kauno1));
        kauno2 = word.contains(getString(R.string.kauno2));
        kauno3 = word.contains(getString(R.string.kauno3));
        kauno4 = word.contains(getString(R.string.kauno4));
        kauno5 = word.contains(getString(R.string.kauno5));

        //Keltainen kirjasto
        kelta0 = word.contains(getString(R.string.kelta0));
        kelta1 = word.contains(getString(R.string.kelta1));
        kelta2 = word.contains(getString(R.string.kelta2));
        kelta3 = word.contains(getString(R.string.kelta3));
        kelta4 = word.contains(getString(R.string.kelta4));
        kelta5 = word.contains(getString(R.string.kelta5));

        //Kotimainen kaunokirjallisuus
        kotkauno0 = word.contains(getString(R.string.kotkauno0));
        kotkauno1 = word.contains(getString(R.string.kotkauno1));
        kotkauno2 = word.contains(getString(R.string.kotkauno2));
        kotkauno3 = word.contains(getString(R.string.kotkauno3));
        kotkauno4 = word.contains(getString(R.string.kotkauno4));
        kotkauno5 = word.contains(getString(R.string.kotkauno5));

        //Like
        lik0 = word.contains(getString(R.string.lik0));
        lik1 = word.contains(getString(R.string.lik1));
        lik2 = word.contains(getString(R.string.lik2));
        lik3 = word.contains(getString(R.string.lik3));
        lik4 = word.contains(getString(R.string.lik4));
        lik5 = word.contains(getString(R.string.lik5));

        // Otava kirjasto
        otav0 = word.contains(getString(R.string.otav0));
        otav1 = word.contains(getString(R.string.otav1));
        otav2 = word.contains(getString(R.string.otav2));
        otav3 = word.contains(getString(R.string.otav3));
        otav4 = word.contains(getString(R.string.otav4));
        otav5 = word.contains(getString(R.string.otav5));

        //Äänikirjat
        aani0 = word.contains(getString(R.string.aani0));
        aani1 = word.contains(getString(R.string.aani1));
        aani2 = word.contains(getString(R.string.aani2));
        aani3 = word.contains(getString(R.string.aani3));
        aani4 = word.contains(getString(R.string.aani4));
        aani5 = word.contains(getString(R.string.aani5));

        //Fantasia
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

        //Tietokirjallisuus tunnistus
        //Ukraina ja venajä
        ukrvena0 = word.contains(getString(R.string.ukrvena0));
        ukrvena1 = word.contains(getString(R.string.ukrvena1));
        ukrvena2 = word.contains(getString(R.string.ukrvena2));
        ukrvena3 = word.contains(getString(R.string.ukrvena3));
        ukrvena4 = word.contains(getString(R.string.ukrvena4));
        ukrvena5 = word.contains(getString(R.string.ukrvena5));

        //Elokuva ja teatteri
        elokteat0 = word.contains(getString(R.string.elokteat0));
        elokteat1 = word.contains(getString(R.string.elokteat1));
        elokteat2 = word.contains(getString(R.string.elokteat2));
        elokteat3 = word.contains(getString(R.string.elokteat3));
        elokteat4 = word.contains(getString(R.string.elokteat4));
        elokteat5 = word.contains(getString(R.string.elokteat5));

        //Elämänkerta
        elamank0 = word.contains(getString(R.string.elamank0));
        elamank1 = word.contains(getString(R.string.elamank1));
        elamank2 = word.contains(getString(R.string.elamank2));
        elamank3 = word.contains(getString(R.string.elamank3));
        elamank4 = word.contains(getString(R.string.elamank4));
        elamank5 = word.contains(getString(R.string.elamank5));

        //Elämänviisaus
        elamanv0 = word.contains(getString(R.string.elamanv0));
        elamanv1 = word.contains(getString(R.string.elamanv1));
        elamanv2 = word.contains(getString(R.string.elamanv2));
        elamanv3 = word.contains(getString(R.string.elamanv3));
        elamanv4 = word.contains(getString(R.string.elamanv4));
        elamanv5 = word.contains(getString(R.string.elamanv5));

        //Filosofia
        filo0 = word.contains(getString(R.string.filo0));
        filo1 = word.contains(getString(R.string.filo1));
        filo2 = word.contains(getString(R.string.filo2));
        filo3 = word.contains(getString(R.string.filo3));
        filo4 = word.contains(getString(R.string.filo4));
        filo5 = word.contains(getString(R.string.filo5));

        //Historia
        hist0 = word.contains(getString(R.string.filo0));
        hist1 = word.contains(getString(R.string.filo1));
        hist2 = word.contains(getString(R.string.filo2));
        hist3 = word.contains(getString(R.string.filo3));
        hist4 = word.contains(getString(R.string.filo4));
        hist5 = word.contains(getString(R.string.filo5));

        //Musiikki
        musii0 = word.contains(getString(R.string.musii0));
        musii1 = word.contains(getString(R.string.musii1));
        musii2 = word.contains(getString(R.string.musii2));
        musii3 = word.contains(getString(R.string.musii3));
        musii4 = word.contains(getString(R.string.musii4));
        musii5 = word.contains(getString(R.string.musii5));

        //Politiikka
        polit0 = word.contains(getString(R.string.polit0));
        polit1 = word.contains(getString(R.string.polit1));
        polit2 = word.contains(getString(R.string.polit2));
        polit3 = word.contains(getString(R.string.polit3));
        polit4 = word.contains(getString(R.string.polit4));
        polit5 = word.contains(getString(R.string.polit5));

        //Sanakirjat
        sanak0 = word.contains(getString(R.string.sanak0));
        sanak1 = word.contains(getString(R.string.sanak1));
        sanak2 = word.contains(getString(R.string.sanak2));
        sanak3 = word.contains(getString(R.string.sanak3));
        sanak4 = word.contains(getString(R.string.sanak4));
        sanak5 = word.contains(getString(R.string.sanak5));

        //Tietokirjat
        tiede0 = MainActivity.word.contains(getString(R.string.tiet0));
        tiede1 = MainActivity.word.contains(getString(R.string.tiet1));
        tiede2 = MainActivity.word.contains(getString(R.string.tiet2));
        tiede3 = MainActivity.word.contains(getString(R.string.tiet3));
        tiede4 = MainActivity.word.contains(getString(R.string.tiet4));
        tiede5 = MainActivity.word.contains(getString(R.string.tiet5));
        tiede6 = MainActivity.word.contains(getString(R.string.tiet6));
        tiede7 = MainActivity.word.contains(getString(R.string.tiet7));

            /*}
        };thread.start();*/
        //Log.e(LOG_TAG, "MyClass.getView() — get item number ");

        //Mikäli boolean on true toteutetaan sen alla oleva toiminta.
        if (fantasia0 || fantasia1 || fantasia2 || fantasia3 || fantasia4 || fantasia5 || fantasia6 || fantasia7 || fantasia8 || fantasia9) {

            fantasia = true;
            laskuri = 0;
            vertaa();
        } else if (baabel0 || baabel1 || baabel2 || baabel3 || baabel4 || baabel5) {
            baabel = true;
            laskuri = 0;
            vertaa();
        } else if (bundle0 || bundle1 || bundle2 || bundle3 || bundle4 || bundle5) {
            bundle = true;
            laskuri = 0;
            vertaa();
        } else if (essee0 || essee1 || essee2 || essee3 || essee4 || essee5) {
            essee = true;
            laskuri = 0;
            vertaa();
        } else if (kauno0 || kauno1 || kauno2 || kauno3 || kauno4 || kauno5) {
            kaunokirjallisuus = true;
            laskuri = 0;
            vertaa();
        } else if (kelta0 || kelta1 || kelta2 || kelta3 || kelta4 || kelta5) {
            keltainen = true;
            laskuri = 0;
            vertaa();
        } else if (kotkauno0 || kotkauno1 || kotkauno2 || kotkauno3 || kotkauno4 || kotkauno5) {
            kotikaunokirjallisuus = true;
            laskuri = 0;
            vertaa();
        } else if (lik0 || lik1 || lik2 || lik3 || lik4 || lik5) {
            like = true;
            laskuri = 0;
            vertaa();
        } else if (otav0 || otav1 || otav2 || otav3 || otav4 || otav5) {
            otava = true;
            laskuri = 0;
            vertaa();
        } else if (aani0 || aani1 || aani2 || aani3 || aani4 || aani5) {
            aanikirja = true;
            laskuri = 0;
            vertaa();
        } else if (scifi0) {
            scifi = true;
            laskuri = 0;
            vertaa();
        } else if (runot0 || runot1 || runot2 || runot3 || runot4 || runot5) {
            runo = true;
            laskuri = 0;
            vertaa();
        } else if (rikoja0 || rikoja1 || rikoja2 || rikoja3) {
            rikojanitus = true;
            laskuri = 0;
            vertaa();
        } else if (ukrvena0 || ukrvena1 || ukrvena2 || ukrvena3 || ukrvena4 || ukrvena5){
            ukrainajavenaja = true;
            laskuri = 0;
            vertaa();
        } else if (elokteat0 || elokteat1 || elokteat2 || elokteat3 || elokteat4 || elokteat5){
            elokuvajateatteri = true;
            laskuri = 0;
            vertaa();
        } else if (elamank0 || elamank1 || elamank2 || elamank3 || elamank4 || elamank5){
            elamankerta = true;
            laskuri = 0;
            vertaa();
        } else if (elamanv0 || elamanv1 || elamanv2 || elamanv3 || elamanv4 || elamanv5){
            elamanviisaus = true;
            laskuri = 0;
            vertaa();
        } else if (filo0 || filo1 || filo2 || filo3 || filo4 || filo5){
            filosofia = true;
            laskuri = 0;
            vertaa();
        } else if (hist0 || hist1 || hist2 || hist3 || hist4 || hist5){
            historia = true;
            laskuri = 0;
            vertaa();
        } else if (musii0 || musii1 || musii2 || musii3 || musii4 || musii5){
            musiikki = true;
            laskuri = 0;
            vertaa();
        } else if (polit0 || polit1 || polit2 || polit3 || polit4 || polit5){
            politiikka = true;
            laskuri = 0;
            vertaa();
        } else if (sanak0 || sanak1 || sanak2 || sanak3 || sanak4 || sanak5){
            sanakirja = true;
            laskuri = 0;
            vertaa();
        } else if (tiede0 || tiede1 || tiede2|| tiede3|| tiede4|| tiede5|| tiede6|| tiede7) {
            tiede = true;
            laskuri = 0;
            vertaa();
        } else if (sijainti0 || sijainti1 || sijainti2 || sijainti3) {
            sijainti = true;
            laskuri = 0;
            vertaa();
        } else if (kiito || oli || kiitos1) {
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
            //Kaunokirjallisuus kategorian toiminnot
        if (baabel) {

            ylakate.setVisibility(View.VISIBLE);

            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);
            markerRelativeLayout.leftMargin = 1600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
            //String x = getString(R.string.mispuhe);
            MainActivity.textToSpeech.speak(getString(R.string.baabelpuhe), TextToSpeech.QUEUE_FLUSH, null);
            baabel = false;
            baabel0 = false;
            baabel1 = false;
            baabel2 = false;
            baabel3 = false;
            baabel4 = false;
            baabel5 = false;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.androidukko);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);

        }

        if (bundle) {

            ylakate.setVisibility(View.VISIBLE);

            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);
            markerRelativeLayout.leftMargin = 1600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
            //String x = getString(R.string.mispuhe);
            MainActivity.textToSpeech.speak(getString(R.string.bundlepuhe), TextToSpeech.QUEUE_FLUSH, null);
            bundle = false;
            bundle0 = false;
            bundle1 = false;
            bundle2 = false;
            bundle3 = false;
            bundle4 = false;
            bundle5 = false;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.androidukko);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);

        }

        if (essee) {

            ylakate.setVisibility(View.VISIBLE);

            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);
            markerRelativeLayout.leftMargin = 1600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
            //String x = getString(R.string.mispuhe);
            MainActivity.textToSpeech.speak(getString(R.string.esseepuhe), TextToSpeech.QUEUE_FLUSH, null);
            essee = false;
            essee0 = false;
            essee1 = false;
            essee2 = false;
            essee3 = false;
            essee4 = false;
            essee5 = false;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.androidukko);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);

        }

        if (kaunokirjallisuus) {

            ylakate.setVisibility(View.VISIBLE);

            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);
            markerRelativeLayout.leftMargin = 1600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
            //String x = getString(R.string.mispuhe);
            MainActivity.textToSpeech.speak(getString(R.string.kaunokirjallisuuspuhe), TextToSpeech.QUEUE_FLUSH, null);
            kaunokirjallisuus = false;
            kauno0 = false;
            kauno1 = false;
            kauno2 = false;
            kauno3 = false;
            kauno4 = false;
            kauno5 = false;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.androidukko);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);

        }

        if (keltainen) {

            ylakate.setVisibility(View.VISIBLE);

            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);
            markerRelativeLayout.leftMargin = 1600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
            //String x = getString(R.string.mispuhe);
            MainActivity.textToSpeech.speak(getString(R.string.keltapuhe), TextToSpeech.QUEUE_FLUSH, null);
            keltainen = false;
            kelta0 = false;
            kelta1 = false;
            kelta2 = false;
            kelta3 = false;
            kelta4 = false;
            kelta5 = false;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.androidukko);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);

        }

        if (kotikaunokirjallisuus) {

            ylakate.setVisibility(View.VISIBLE);

            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);
            markerRelativeLayout.leftMargin = 1600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
            //String x = getString(R.string.mispuhe);
            MainActivity.textToSpeech.speak(getString(R.string.kotikaunopuhe), TextToSpeech.QUEUE_FLUSH, null);
            kotikaunokirjallisuus = false;
            kotkauno0 = false;
            kotkauno1 = false;
            kotkauno2 = false;
            kotkauno3 = false;
            kotkauno4 = false;
            kotkauno5 = false;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.androidukko);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);

        }

        if (like) {

            if (kotikaunokirjallisuus) {

                ylakate.setVisibility(View.VISIBLE);

                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);
                markerRelativeLayout.leftMargin = 1600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
                //String x = getString(R.string.mispuhe);
                MainActivity.textToSpeech.speak(getString(R.string.likpuhe), TextToSpeech.QUEUE_FLUSH, null);
                like = false;
                lik0 = false;
                lik1 = false;
                lik2 = false;
                lik3 = false;
                lik4 = false;
                lik5 = false;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.androidukko);
                        //speechRecognizer.startListening(speechRecognizerIntent);
                    }
                }, 4000);

            }

            if (otava) {

                ylakate.setVisibility(View.VISIBLE);

                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);
                markerRelativeLayout.leftMargin = 1600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
                //String x = getString(R.string.mispuhe);
                MainActivity.textToSpeech.speak(getString(R.string.otavpuhe), TextToSpeech.QUEUE_FLUSH, null);
                otava = false;
                otav0 = false;
                otav1 = false;
                otav2 = false;
                otav3 = false;
                otav4 = false;
                otav5 = false;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.androidukko);
                        //speechRecognizer.startListening(speechRecognizerIntent);
                    }
                }, 4000);

            }

            if (aanikirja) {

                ylakate.setVisibility(View.VISIBLE);

                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);
                markerRelativeLayout.leftMargin = 1600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
                //String x = getString(R.string.mispuhe);
                MainActivity.textToSpeech.speak(getString(R.string.aanipuhe), TextToSpeech.QUEUE_FLUSH, null);
                aanikirja = false;
                aani0 = false;
                aani1 = false;
                aani2 = false;
                aani3 = false;
                aani4 = false;
                aani5 = false;

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

            //Tietokirjallisuuden katagorian toiminnot

            if (ukrainajavenaja){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
                //String x = getString(R.string.tiedpuhe);
                MainActivity.textToSpeech.speak(getString(R.string.ukrvenapuhe), TextToSpeech.QUEUE_FLUSH, null);

                ukrainajavenaja = false;
                ukrvena0 = false;
                ukrvena1 = false;
                ukrvena2 = false;
                ukrvena3 = false;
                ukrvena4 = false;
                ukrvena5 = false;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.androidukko);
                    }
                }, 3600);
            }

            if (elokuvajateatteri){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
                //String x = getString(R.string.tiedpuhe);
                MainActivity.textToSpeech.speak(getString(R.string.elokteatpuhe), TextToSpeech.QUEUE_FLUSH, null);

                elokuvajateatteri = false;
                elokteat0 = false;
                elokteat1 = false;
                elokteat2 = false;
                elokteat3 = false;
                elokteat4 = false;
                elokteat5 = false;


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.androidukko);
                    }
                }, 3600);
            }

            if (elamankerta){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
                //String x = getString(R.string.tiedpuhe);
                MainActivity.textToSpeech.speak(getString(R.string.elamankpuhe), TextToSpeech.QUEUE_FLUSH, null);

                elamankerta = false;
                elamank0 = false;
                elamank1 = false;
                elamank2 = false;
                elamank3 = false;
                elamank4 = false;
                elamank5 = false;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.androidukko);
                    }
                }, 3600);
            }
            if (elamanviisaus){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
                //String x = getString(R.string.tiedpuhe);
                MainActivity.textToSpeech.speak(getString(R.string.elamanvpuhe), TextToSpeech.QUEUE_FLUSH, null);

                elamanviisaus = false;
                elamanv0 = false;
                elamanv1 = false;
                elamanv2 = false;
                elamanv3 = false;
                elamanv4 = false;
                elamanv5 = false;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.androidukko);
                    }
                }, 3600);
            }
            if (filosofia){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
                //String x = getString(R.string.tiedpuhe);
                MainActivity.textToSpeech.speak(getString(R.string.filopuhe), TextToSpeech.QUEUE_FLUSH, null);

                filosofia = false;
                filo0 = false;
                filo1 = false;
                filo2 = false;
                filo3 = false;
                filo4 = false;
                filo5 = false;


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.androidukko);
                    }
                }, 3600);
            }

            if (historia){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
                //String x = getString(R.string.tiedpuhe);
                MainActivity.textToSpeech.speak(getString(R.string.histpuhe), TextToSpeech.QUEUE_FLUSH, null);

                historia = false;
                hist0 = false;
                hist1 = false;
                hist2 = false;
                hist3 = false;
                hist4 = false;
                hist5 = false;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.androidukko);
                    }
                }, 3600);
            }
            if (musiikki){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
                //String x = getString(R.string.tiedpuhe);
                MainActivity.textToSpeech.speak(getString(R.string.musiipuhe), TextToSpeech.QUEUE_FLUSH, null);

                musiikki = false;
                musii0 = false;
                musii1 = false;
                musii2 = false;
                musii3 = false;
                musii4 = false;
                musii5 = false;


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.androidukko);
                    }
                }, 3600);
            }

            if (politiikka){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
                //String x = getString(R.string.tiedpuhe);
                MainActivity.textToSpeech.speak(getString(R.string.politpuhe), TextToSpeech.QUEUE_FLUSH, null);

                politiikka = false;
                polit0 = false;
                polit1 = false;
                polit2 = false;
                polit3 = false;
                polit4 = false;
                polit5 = false;


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.androidukko);
                    }
                }, 3600);
            }

            if (sanakirja){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.androidspeak).into(MainActivity.hahmo);
                //String x = getString(R.string.tiedpuhe);
                MainActivity.textToSpeech.speak(getString(R.string.sanakpuhe), TextToSpeech.QUEUE_FLUSH, null);

                sanakirja = false;
                sanak0 = false;
                sanak1 = false;
                sanak2 = false;
                sanak3 = false;
                sanak4 = false;
                sanak5 = false;


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
    }
    public void restart(){

        //Intent intent = getIntent()
        //finish();
        //startActivity(getIntent());
        speechRecognizer.stopListening();
        textToSpeech.stop();
        timer.cancel();
        recreate();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        startBackgroundThread();
        if(textureView.isAvailable())
            openCamera();
        else
            textureView.setSurfaceTextureListener(textureListener);
    }

    @Override
    protected void onPause(){

        stopBackgroundThread();

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
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "You can't use camera without permission", Toast.LENGTH_SHORT).show();
                finish();
            }
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

    private void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("Camera Background");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }
}
