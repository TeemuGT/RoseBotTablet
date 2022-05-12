package com.example.rosebottablet;

import androidx.annotation.NonNull;
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
import android.media.Image;
import android.media.ImageReader;
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
import android.view.MotionEvent;
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

    //Lastenkirjat avainsanat
    //Djeco avainsanat
    public boolean djeco0 = false;
    public boolean djeco1 = false;
    public boolean djeco2 = false;
    public boolean djeco3 = false;
    public boolean djeco4 = false;
    public boolean djeco5 = false;

    //englannin kieliset lastenkirjat avainsanat
    public boolean kids0 = false;
    public boolean kids1 = false;
    public boolean kids2 = false;
    public boolean kids3 = false;
    public boolean kids4 = false;
    public boolean kids5 = false;

    //Lasten elokuvat avainsanat
    public boolean lastelo0 = false;
    public boolean lastelo1 = false;
    public boolean lastelo2 = false;
    public boolean lastelo3 = false;
    public boolean lastelo4 = false;
    public boolean lastelo5 = false;

    //Lastenkirjat avainsanat
    public boolean lasten0 = false;
    public boolean lasten1 = false;
    public boolean lasten2 = false;
    public boolean lasten3 = false;
    public boolean lasten4 = false;
    public boolean lasten5 = false;

    //Lastentietokirjat avainsanat
    public boolean lasttiet0 = false;
    public boolean lasttiet1 = false;
    public boolean lasttiet2 = false;
    public boolean lasttiet3 = false;
    public boolean lasttiet4 = false;
    public boolean lasttiet5 = false;

    //Lastenkirjat 0-3 vuotiaille avainsanat
    public boolean last0_30 = false;
    public boolean last0_31 = false;
    public boolean last0_32 = false;
    public boolean last0_33 = false;
    public boolean last0_34 = false;
    public boolean last0_35 = false;

    //Lastenkirjat 4-6 vuotiaille avainsanat
    public boolean last4_60 = false;
    public boolean last4_61 = false;
    public boolean last4_62 = false;
    public boolean last4_63 = false;
    public boolean last4_64 = false;
    public boolean last4_65 = false;

    //Lasten kirjat 7-9 vuotiaille avainsanat
    public boolean last7_90 = false;
    public boolean last7_91 = false;
    public boolean last7_92 = false;
    public boolean last7_93 = false;
    public boolean last7_94 = false;
    public boolean last7_95 = false;

    //Lastenkirjat 10-12 vuotiaille avainsanat
    public boolean last10_120 = false;
    public boolean last10_121 = false;
    public boolean last10_122 = false;
    public boolean last10_123 = false;
    public boolean last10_124 = false;
    public boolean last10_125 = false;

    //Lasten kirjat Maisa avainsanat
    public boolean maisa0 = false;
    public boolean maisa1 = false;
    public boolean maisa2 = false;
    public boolean maisa3 = false;
    public boolean maisa4 = false;
    public boolean maisa5 = false;

    //Nuorten kirjat avainsanat
    public boolean nuoret0 = false;
    public boolean nuoret1 = false;
    public boolean nuoret2 = false;
    public boolean nuoret3 = false;
    public boolean nuoret4 = false;
    public boolean nuoret5 = false;

    //Oppi ja ilo avainsanat
    public boolean oppilo0 = false;
    public boolean oppilo1 = false;
    public boolean oppilo2 = false;
    public boolean oppilo3 = false;
    public boolean oppilo4 = false;
    public boolean oppilo5 = false;

    //Pipsa possu avainsanat
    public boolean pipsa0 = false;
    public boolean pipsa1 = false;
    public boolean pipsa2 = false;
    public boolean pipsa3 = false;
    public boolean pipsa4 = false;
    public boolean pipsa5 = false;

    //Puuhaa lapsille avainsanat
    public boolean lastpuuh0 = false;
    public boolean lastpuuh1 = false;
    public boolean lastpuuh2 = false;
    public boolean lastpuuh3 = false;
    public boolean lastpuuh4 = false;
    public boolean lastpuuh5 = false;

    //Tunnetaidot lapset avainsanat
    public boolean tunne0 = false;
    public boolean tunne1 = false;
    public boolean tunne2 = false;
    public boolean tunne3 = false;
    public boolean tunne4 = false;
    public boolean tunne5 = false;

    //Rikoskirjallisuus
    //Crime novels in English avainsanat
    public boolean crimeNovEng0 = false;
    public boolean crimeNovEng1 = false;
    public boolean crimeNovEng2 = false;
    public boolean crimeNovEng3 = false;
    public boolean crimeNovEng4 = false;
    public boolean crimeNovEng5 = false;

    //Rikoskirja uutuudet avainsanat
    public boolean rikosuudet0 = false;
    public boolean rikosuudet1 = false;
    public boolean rikosuudet2 = false;
    public boolean rikosuudet3 = false;
    public boolean rikosuudet4 = false;
    public boolean rikosuudet5 = false;

    //Rikospokkarit avainsanat
    public boolean rikospokka0 = false;
    public boolean rikospokka1 = false;
    public boolean rikospokka2 = false;
    public boolean rikospokka3 = false;
    public boolean rikospokka4 = false;
    public boolean rikospokka5 = false;

    //Rikosromaanit avainsanat
    public boolean rikosromaani0 = false;
    public boolean rikosromaani1 = false;
    public boolean rikosromaani2 = false;
    public boolean rikosromaani3 = false;
    public boolean rikosromaani4 = false;
    public boolean rikosromaani5 = false;

    //True Crime avainsanat
    public boolean truecrime0 = false;
    public boolean truecrime1 = false;
    public boolean truecrime2 = false;
    public boolean truecrime3 = false;
    public boolean truecrime4 = false;
    public boolean truecrime5 = false;




    public boolean lehd0 = false;
    public boolean lehd1 = false;
    public boolean lehd2 = false;
    public boolean lehd3 = false;
    public boolean lehd4 = false;
    public boolean lehd5 = false;

    public boolean sarja0 = false;
    public boolean sarja1 = false;
    public boolean sarja2 = false;
    public boolean sarja3 = false;
    public boolean sarja4 = false;
    public boolean sarja5 = false;

    public boolean pokka0 = false;
    public boolean pokka1 = false;
    public boolean pokka2 = false;
    public boolean pokka3 = false;
    public boolean pokka4 = false;
    public boolean pokka5 = false;

    public boolean engl0 = false;
    public boolean engl1 = false;
    public boolean engl2 = false;
    public boolean engl3 = false;
    public boolean engl4 = false;
    public boolean engl5 = false;

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

    Timer timerlopetus = new Timer();
    Timer timer = new Timer();
    Handler handler = new Handler();
    Handler handlerloppu = new Handler();
    Handler handler1 = new Handler();
    Handler handler2 = new Handler();

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
    boolean aloitettu = true;


    int kuvalasku;
    public int laskuri =  0;

    int i = 0;

    //Avain sana
    public static String word = "";
    private static final String FILE_NAME = "text.txt";


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

    //Lastenkirjat booleanit
    public static boolean djeco = false;
    public static boolean kids = false;
    public static boolean lastenelokuvat = false;
    public static boolean lastenkirjat = false;
    public static boolean lastentietikirjat = false;
    public static boolean lastenkirjat0_3 = false;
    public static boolean lastenkirjat4_6= false;
    public static boolean lastenkirjat7_9 = false;
    public static boolean lastenkirjat10_12 = false;
    public static boolean maisa = false;
    public static boolean nuoret = false;
    public static boolean oppijailo = false;
    public static boolean pipsapossu = false;
    public static boolean puuhaalapsille = false;
    public static boolean tunnetaidot = false;

    //Elokuva booleanit
    public static boolean CrimeNovelsInEnglish = false;
    public static boolean RikoskirjaUutuudet = false;
    public static boolean RikosPokkarit = false;
    public static boolean Rikosromaanit = false;
    public static boolean TrueCrime = false;


    public static boolean lehdet = false;
    public static boolean sarjakuvat = false;
    public static boolean pokkarit = false;
    public static boolean englanti = false;

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

    //Lastenkirjat napit
    public Button djecobtn;
    public Button kidsbtn;
    public Button lastelobtn;
    public Button lastenbtn;
    public Button lasttietbtn;
    public Button last0_3btn;
    public Button last4_6btn;
    public Button last7_9btn;
    public Button last10_12btn;
    public Button lastmaisbtn;
    public Button nuoretbtn;
    public Button oppilobtn;
    public Button pipsabtn;
    public Button lastpuuhbtn;
    public Button lasttunnebtn;

    //Rikoskirjallisuus
    public Button crimeNovEngbtn;
    public Button rikosuudetbtn;
    public Button rikospokkabtn;
    public Button rikosromaanibtn;
    public Button truecrimebtn;

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
    private RelativeLayout mylayout = null;

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



        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        //what you want to do
                        if (!ihminen) {
                            kuvalasku++;
                            takePicture();
                            Log.e(String.valueOf(LOG_TAG), "Kuva numero " + kuvalasku);
                        }
                        if (ihminen) {
                            timer.cancel();
                            aloita();
                        }
                        if (kuvalasku > 20){
                            restart();
                        }
                    }
                });
            }
        }, 0, 2000);


        //otakuvia();
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
                lopetus();
                Handler handler4 = new Handler();
                handler4.postAtFrontOfQueue(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                //Hahmon sijainti siirretään
                                RelativeLayout.LayoutParams hahmoRelativeLayout = new RelativeLayout.LayoutParams(1200, 1400);
                                hahmoRelativeLayout.leftMargin = 50;
                                hahmoRelativeLayout.topMargin = 120;
                                MainActivity.hahmo.setLayoutParams(hahmoRelativeLayout);
                            }
                        });
                    }
                });

            }

            @Override
            public void onBeginningOfSpeech() {
                hahmo.setImageResource(R.drawable.kuuntelevarobo);
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
                Handler handler5 = new Handler();
                handler5.postAtFrontOfQueue(new Runnable() {
                    @Override
                    public void run() {
                        //Hahmon sijainti siirretään
                        RelativeLayout.LayoutParams hahmoRelativeLayout = new RelativeLayout.LayoutParams(1200, 1400);
                        hahmoRelativeLayout.leftMargin = 50;
                        hahmoRelativeLayout.topMargin = 120;
                        MainActivity.hahmo.setLayoutParams(hahmoRelativeLayout);
                    }
                });
                hahmo.setImageResource(R.drawable.kuvarobo);

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
                    //writeToFile("text.txt", word);
                    /*FileOutputStream fos = null;
                    try {
                        fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                        fos.write(word.getBytes());
                        Toast.makeText(getApplicationContext(), "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally{
                        if(fos != null){
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }*/



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


        mylayout = (RelativeLayout) findViewById(R.id.main);

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

        //Lastenkirja nappien objektien haku
        djecobtn = findViewById(R.id.djecobtn);
        kidsbtn = findViewById(R.id.kidsbtn);
        lastelobtn = findViewById(R.id.lastelobtn);
        lastenbtn = findViewById(R.id.lastenbtn);
        lasttietbtn = findViewById(R.id.lasttietbtn);
        last0_3btn = findViewById(R.id.last0_3btn);
        last4_6btn = findViewById(R.id.last4_6btn);
        last7_9btn = findViewById(R.id.last7_9btn);
        last10_12btn = findViewById(R.id.last10_12btn);
        lastmaisbtn = findViewById(R.id.lastmaisbtn);
        nuoretbtn = findViewById(R.id.nuoretbtn);
        oppilobtn = findViewById(R.id.oppilobtn);
        pipsabtn = findViewById(R.id.pipsabtn);
        lastpuuhbtn = findViewById(R.id.lastpuuhbtn);
        lasttunnebtn = findViewById(R.id.lasttunnebtn);

        //Elokuva nappien objektien haku
        crimeNovEngbtn = findViewById(R.id.crimeNovEngbtn);
        rikosuudetbtn = findViewById(R.id.rikosuudetbtn);
        rikospokkabtn = findViewById(R.id.rikospokkabtn);
        rikosromaanibtn = findViewById(R.id.rikosromaanibtn);
        truecrimebtn = findViewById(R.id.truecrimebtn);


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
        converBtn.setOnClickListener(new View.OnClickListener() {
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
        voiceBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lopetus();
                speechRecognizer.startListening(speechRecognizerIntent);
            }
        });


        //Yläkategorioiden nappien toiminnot
        kaunobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                        hahmo.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        });

        tietogabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                        hahmo.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        });

        lapsetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
                textToSpeech.speak(getString(R.string.lastenkatepuhe), TextToSpeech.QUEUE_FLUSH, null);
                //Poistetaan pääkategotia napit käytöstä.
                kaunobtn.setVisibility(View.INVISIBLE);
                tietogabtn.setVisibility(View.INVISIBLE);
                lapsetbtn.setVisibility(View.INVISIBLE);
                pokkarbtn.setVisibility(View.INVISIBLE);
                sarjabtn.setVisibility(View.INVISIBLE);
                elokuvbtn.setVisibility(View.INVISIBLE);
                lehdetbtn.setVisibility(View.INVISIBLE);
                englanbtn.setVisibility(View.INVISIBLE);

                djecobtn.setVisibility(View.VISIBLE);
                kidsbtn.setVisibility(View.VISIBLE);
                lastelobtn.setVisibility(View.VISIBLE);
                lastenbtn.setVisibility(View.VISIBLE);
                lasttietbtn.setVisibility(View.VISIBLE);
                last0_3btn.setVisibility(View.VISIBLE);
                last4_6btn.setVisibility(View.VISIBLE);
                last7_9btn.setVisibility(View.VISIBLE);
                last10_12btn.setVisibility(View.VISIBLE);
                lastmaisbtn.setVisibility(View.VISIBLE);
                nuoretbtn.setVisibility(View.VISIBLE);
                oppilobtn.setVisibility(View.VISIBLE);
                pipsabtn.setVisibility(View.VISIBLE);
                lastpuuhbtn.setVisibility(View.VISIBLE);
                lasttunnebtn.setVisibility(View.VISIBLE);

                ylakate.setVisibility(View.VISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        });

        pokkarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                pokkarit = true;
                vertaa();
            }
        });

        sarjabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                sarjakuvat = true;
                vertaa();
            }
        });

        elokuvbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                crimeNovEngbtn.setVisibility(View.VISIBLE);
                rikosuudetbtn.setVisibility(View.VISIBLE);
                rikospokkabtn.setVisibility(View.VISIBLE);
                rikosromaanibtn.setVisibility(View.VISIBLE);
                truecrimebtn.setVisibility(View.VISIBLE);
                lastelobtn.setVisibility(View.VISIBLE);

                ylakate.setVisibility(View.VISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        });

        lehdetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                lehdet = true;
                vertaa();
            }
        });

        englanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                englanti = true;
                vertaa();
            }
        });


        //Nappulat valitsevat eri kategorioita

        //Kaunokirjallisuus napit.
        //Baabel nappi
        baabebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                baabel = true;
                vertaa();
            }
        });

        //Bundle nappi
        bundlbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                bundle = true;
                vertaa();
            }
        });

        //Esseet nappi
        esseebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                essee = true;
                vertaa();
            }
        });

        //Kaunokirjallisuus nappi
        kaunokibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                kaunokirjallisuus = true;
                vertaa();
            }
        });

        //Keltainen kirjasto nappi
        keltainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                keltainen = true;
                vertaa();
            }
        });

        //Kotimainen kaunokirjallisuus nappi
        kotikaunobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                kotikaunokirjallisuus = true;
                vertaa();
            }
        });

        //Like nappi
        likebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                like = true;
                vertaa();
            }
        });

        //Otavan kirjaston nappi
        otavabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                otava = true;
                vertaa();
            }
        });

        //Äänikirja nappi
        aanikibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                aanikirja = true;
                vertaa();
            }
        });
        //Fantasia nappi
        fantasibtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                lopetus();
                speechRecognizer.stopListening();
                fantasia = true;
                vertaa();
            }
        });
        //Runo nappi
        runobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                runo = true;
                vertaa();
            }
        });
        //Rikos ja jännitys nappi
        rikojanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                rikojanitus = true;
                vertaa();
            }
        });
        //Scifi nappi
        scifibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lopetus();
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
                lopetus();
                speechRecognizer.stopListening();
                ukrainajavenaja = true;
                vertaa();
            }
        });

        //Elokuva ja teatteri nappi
        eloteatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                elokuvajateatteri = true;
                vertaa();
            }
        });

        //Elämänkerrat nappi
        elamankbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                elamankerta = true;
                vertaa();
            }
        });

        //Elämän viisaus nappi
        elamanvbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                elamanviisaus = true;
                vertaa();
            }
        });

        //Filosofia nappi
        filobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                filosofia = true;
                vertaa();
            }
        });

        //Historia nappi
        histbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                historia = true;
                vertaa();
            }
        });

        //Musiikki nappi
        musiibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                musiikki = true;
                vertaa();
            }
        });

        //Politiikka nappi
        politbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                politiikka = true;
                vertaa();
            }
        });

        //Sanakirja nappi
        sanakbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                sanakirja = true;
                vertaa();
            }
        });

        //Tietokirja nappi
        tietobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lopetus();
                speechRecognizer.stopListening();
                tiede = true;
                vertaa();


            }
        });

        //Lastenkirjat napit
        //Djeco nappi
        djecobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                djeco = true;
                vertaa();
            }
        });
        //Englanninkieliset lastenkirjat nappi
        kidsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                kids = true;
                vertaa();
            }
        });
        //Lasten elokuvat nappi
        lastelobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                lastenelokuvat = true;
                vertaa();
            }
        });
        //Lasten kirjat nappi
        lastenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                lastenkirjat = true;
                vertaa();
            }
        });
        //Lasten tietokirjat nappi
        lasttietbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                lastentietikirjat = true;
                vertaa();
            }
        });
        //Lasten kirjat 0-3 vuotiaille nappi
        last0_3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                lastenkirjat0_3 = true;
                vertaa();
            }
        });
        //Lasten kirjat 4-6 vuotiaille nappi
        last4_6btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                lastenkirjat4_6 = true;
                vertaa();
            }
        });
        //Lastenkirjat 7-9 vuotiaille nappi
        last7_9btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                lastenkirjat7_9 = true;
                vertaa();
            }
        });
        //Lastenkirjat 10-12 vuotiaille nappi
        last10_12btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                lastenkirjat10_12 = true;
                vertaa();
            }
        });
        //Maisa lastenkirjat nappi
        lastmaisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                maisa = true;
                vertaa();
            }
        });
        //Nuorten kirjat nappi
        nuoretbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                nuoret = true;
                vertaa();
            }
        });
        //Lastenkirjat oppi ja ilo nappi
        oppilobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                oppijailo = true;
                vertaa();
            }
        });
        //Pipsa possu lastenkirjat nappi
        pipsabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                pipsapossu = true;
                vertaa();
            }
        });
        //Puuhaa lapsille nappi
        lastpuuhbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                puuhaalapsille = true;
                vertaa();
            }
        });
        //Lasten tunnetaidot kirjat nappi
        lasttunnebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                tunnetaidot = true;
                vertaa();
            }
        });

        //Elokuva napit
        //Blue-ray nappi
        crimeNovEngbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                CrimeNovelsInEnglish = true;
                vertaa();
            }
        });
        //Dvd nappi
        rikosuudetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                RikoskirjaUutuudet = true;
                vertaa();
            }
        });
        //Elokuva klassikot nappi
        rikospokkabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                RikosPokkarit = true;
                vertaa();
            }
        });
        //Kotimaiset elokuvat nappi

        rikosromaanibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                Rikosromaanit = true;
                vertaa();
            }
        });
        //Tv-sarjat nappi
        truecrimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                TrueCrime = true;
                vertaa();
            }
        });

        //Ylä kategoria nappulat
        ylakate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                textToSpeech.stop();
                textToSpeech.speak(getString(R.string.ylakatepuhe), TextToSpeech.QUEUE_FLUSH, null);

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

                tietobtn.setVisibility(View.INVISIBLE);
                ukravenabtn.setVisibility(View.INVISIBLE);
                eloteatbtn.setVisibility(View.INVISIBLE);
                elamankbtn.setVisibility(View.INVISIBLE);
                elamanvbtn.setVisibility(View.INVISIBLE);
                filobtn.setVisibility(View.INVISIBLE);
                histbtn.setVisibility(View.INVISIBLE);
                musiibtn.setVisibility(View.INVISIBLE);
                politbtn.setVisibility(View.INVISIBLE);
                sanakbtn.setVisibility(View.INVISIBLE);

                djecobtn.setVisibility(View.INVISIBLE);
                kidsbtn.setVisibility(View.INVISIBLE);
                lastelobtn.setVisibility(View.INVISIBLE);
                lastenbtn.setVisibility(View.INVISIBLE);
                lasttietbtn.setVisibility(View.INVISIBLE);
                last0_3btn.setVisibility(View.INVISIBLE);
                last4_6btn.setVisibility(View.INVISIBLE);
                last7_9btn.setVisibility(View.INVISIBLE);
                last10_12btn.setVisibility(View.INVISIBLE);
                lastmaisbtn.setVisibility(View.INVISIBLE);
                nuoretbtn.setVisibility(View.INVISIBLE);
                oppilobtn.setVisibility(View.INVISIBLE);
                pipsabtn.setVisibility(View.INVISIBLE);
                lastpuuhbtn.setVisibility(View.INVISIBLE);
                lasttunnebtn.setVisibility(View.INVISIBLE);

                crimeNovEngbtn.setVisibility(View.INVISIBLE);
                rikosuudetbtn.setVisibility(View.INVISIBLE);
                rikospokkabtn.setVisibility(View.INVISIBLE);
                rikosromaanibtn.setVisibility(View.INVISIBLE);
                truecrimebtn.setVisibility(View.INVISIBLE);

                ylakate.setVisibility(View.INVISIBLE);


            }
        });


        mylayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                lopetus();

                return false;
            }
        });




        }



        /*handlerloppu.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },4000);*/




    public void lopetus(){
        if (i != 0) {
            i = 1;
            Log.e(String.valueOf(LOG_TAG), "Lopetus on valittu uudestaan ");
        }


        if (i == 0) {
            timerlopetus.schedule(new TimerTask() {
                @Override
                public void run() {
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run () {

                            i++;
                            Log.e(String.valueOf(LOG_TAG), "I on tällähetkellä " + i);
                            if (i > 5) {
                                restart();
                            }
                        }
                    });
                }

            }, 0, 5000);
        }


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

                        if (compareEquivalance() < 0.27){
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

    /*public void otakuvia(){

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
                    if (ihminen){
                        aloita();
                    }
                }*/

    private void aloita() {
            lopetus();
            aloitettu = true;
            //Hakee stringin ja tuotaa sen puheeksi
            String x = getString(R.string.terve);
            textToSpeech.speak(x, TextToSpeech.QUEUE_FLUSH, null);

            converBtn.setVisibility(View.INVISIBLE);


            //Vaihtaa hahmo imagen tilalle gif animaation
            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);

            //Handlerissä tuotetaan usempia toimintoja saman aikasesti
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Hahmon sijainti siirretään

                    handler2.postAtFrontOfQueue(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    RelativeLayout.LayoutParams hahmoRelativeLayout = new RelativeLayout.LayoutParams(1200, 1400);
                                    hahmoRelativeLayout.leftMargin = 50;
                                    hahmoRelativeLayout.topMargin = 120;
                                    hahmo.setLayoutParams(hahmoRelativeLayout);
                                }
                            });

                        }
                    });



                    //gif.setVisibility(View.INVISIBLE);
                    //hahmo.setVisibility(View.VISIBLE);



                    //Tuotetaan näkyviin objekteja
                    hahmo.setImageResource(R.drawable.kuvarobo);
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
                    //text1.setVisibility(View.VISIBLE);

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
        hist0 = word.contains(getString(R.string.hist0));
        hist1 = word.contains(getString(R.string.hist1));
        hist2 = word.contains(getString(R.string.hist2));
        hist3 = word.contains(getString(R.string.hist3));
        hist4 = word.contains(getString(R.string.hist4));
        hist5 = word.contains(getString(R.string.hist5));

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

        //Lastenkirjat
        //Djeco
        djeco0 = word.contains(getString(R.string.djeco0));
        djeco1 = word.contains(getString(R.string.djeco1));
        djeco2 = word.contains(getString(R.string.djeco2));
        djeco3 = word.contains(getString(R.string.djeco3));
        djeco4 = word.contains(getString(R.string.djeco4));
        djeco5 = word.contains(getString(R.string.djeco5));

        //Englanninkieliset lastenkirjat
        kids0 = word.contains(getString(R.string.kids0));
        kids1 = word.contains(getString(R.string.kids1));
        kids2 = word.contains(getString(R.string.kids2));
        kids3 = word.contains(getString(R.string.kids3));
        kids4 = word.contains(getString(R.string.kids4));
        kids5 = word.contains(getString(R.string.kids5));

        //Lasten elokuvat
        lastelo0 = word.contains(getString(R.string.lastelo0));
        lastelo1 = word.contains(getString(R.string.lastelo1));
        lastelo2 = word.contains(getString(R.string.lastelo2));
        lastelo3 = word.contains(getString(R.string.lastelo3));
        lastelo4 = word.contains(getString(R.string.lastelo4));
        lastelo5 = word.contains(getString(R.string.lastelo5));

        //Lastenkirjat
        lasten0 = word.contains(getString(R.string.lasten0));
        lasten1 = word.contains(getString(R.string.lasten1));
        lasten2 = word.contains(getString(R.string.lasten2));
        lasten3 = word.contains(getString(R.string.lasten3));
        lasten4 = word.contains(getString(R.string.lasten4));
        lasten5 = word.contains(getString(R.string.lasten5));

        //Lasten tietokirjat
        lasttiet0 = word.contains(getString(R.string.lasttiet0));
        lasttiet1 = word.contains(getString(R.string.lasttiet1));
        lasttiet2 = word.contains(getString(R.string.lasttiet2));
        lasttiet3 = word.contains(getString(R.string.lasttiet3));
        lasttiet4 = word.contains(getString(R.string.lasttiet4));
        lasttiet5 = word.contains(getString(R.string.lasttiet5));

        //Lastenkirjat 0-3 vuotiaat
        last0_30 = word.contains(getString(R.string.last0_30));
        last0_31 = word.contains(getString(R.string.last0_31));
        last0_32 = word.contains(getString(R.string.last0_32));
        last0_33 = word.contains(getString(R.string.last0_33));
        last0_34 = word.contains(getString(R.string.last0_34));
        last0_35 = word.contains(getString(R.string.last0_35));

        //Lastenkirjat 4-6 vuotiaat
        last4_60 = word.contains(getString(R.string.last4_60));
        last4_61 = word.contains(getString(R.string.last4_61));
        last4_62 = word.contains(getString(R.string.last4_62));
        last4_63 = word.contains(getString(R.string.last4_63));
        last4_64 = word.contains(getString(R.string.last4_64));
        last4_65 = word.contains(getString(R.string.last4_65));

        //Lastenkirjat 7-9 vuotiaat
        last7_90 = word.contains(getString(R.string.last7_90));
        last7_91 = word.contains(getString(R.string.last7_91));
        last7_92 = word.contains(getString(R.string.last7_92));
        last7_93 = word.contains(getString(R.string.last7_93));
        last7_94 = word.contains(getString(R.string.last7_94));
        last7_95 = word.contains(getString(R.string.last7_95));

        //Lstenkirjat 10-12 vuotiaat
        last10_120 = word.contains(getString(R.string.last10_120));
        last10_121 = word.contains(getString(R.string.last10_121));
        last10_122 = word.contains(getString(R.string.last10_122));
        last10_123 = word.contains(getString(R.string.last10_123));
        last10_124 = word.contains(getString(R.string.last10_124));
        last10_125 = word.contains(getString(R.string.last10_125));

        //Maisa
        maisa0 = word.contains(getString(R.string.maisa0));
        maisa1 = word.contains(getString(R.string.maisa1));
        maisa2 = word.contains(getString(R.string.maisa2));
        maisa3 = word.contains(getString(R.string.maisa3));
        maisa4 = word.contains(getString(R.string.maisa4));
        maisa5 = word.contains(getString(R.string.maisa5));

        //Nuoret
        nuoret0 = word.contains(getString(R.string.nuoret0));
        nuoret1 = word.contains(getString(R.string.nuoret1));
        nuoret2 = word.contains(getString(R.string.nuoret2));
        nuoret3 = word.contains(getString(R.string.nuoret3));
        nuoret4 = word.contains(getString(R.string.nuoret4));
        nuoret5 = word.contains(getString(R.string.nuoret5));

        //Oppi ja ilo
        oppilo0 = word.contains(getString(R.string.oppilo0));
        oppilo1 = word.contains(getString(R.string.oppilo1));
        oppilo2 = word.contains(getString(R.string.oppilo2));
        oppilo3 = word.contains(getString(R.string.oppilo3));
        oppilo4 = word.contains(getString(R.string.oppilo4));
        oppilo5 = word.contains(getString(R.string.oppilo5));

        //Pipsa Possu
        pipsa0 = word.contains(getString(R.string.pipsa0));
        pipsa1 = word.contains(getString(R.string.pipsa1));
        pipsa2 = word.contains(getString(R.string.pipsa2));
        pipsa3 = word.contains(getString(R.string.pipsa3));
        pipsa4 = word.contains(getString(R.string.pipsa4));
        pipsa5 = word.contains(getString(R.string.pipsa5));

        //Puuhaa lapsille
        lastpuuh0 = word.contains(getString(R.string.lastpuuh0));
        lastpuuh1 = word.contains(getString(R.string.lastpuuh1));
        lastpuuh2 = word.contains(getString(R.string.lastpuuh2));
        lastpuuh3 = word.contains(getString(R.string.lastpuuh3));
        lastpuuh4 = word.contains(getString(R.string.lastpuuh4));
        lastpuuh5 = word.contains(getString(R.string.lastpuuh5));

        //Tunnetaidot
        tunne0 = word.contains(getString(R.string.tunne0));
        tunne1 = word.contains(getString(R.string.tunne1));
        tunne2 = word.contains(getString(R.string.tunne2));
        tunne3 = word.contains(getString(R.string.tunne3));
        tunne4 = word.contains(getString(R.string.tunne4));
        tunne5 = word.contains(getString(R.string.tunne5));

        //Elokuvat
        //Blueray
        crimeNovEng0 = word.contains(getString(R.string.crimeNovEng0));
        crimeNovEng1 = word.contains(getString(R.string.crimeNovEng1));
        crimeNovEng2 = word.contains(getString(R.string.crimeNovEng2));
        crimeNovEng3 = word.contains(getString(R.string.crimeNovEng3));
        crimeNovEng4 = word.contains(getString(R.string.crimeNovEng4));
        crimeNovEng5 = word.contains(getString(R.string.crimeNovEng5));

        //DVD
        rikosuudet0 = word.contains(getString(R.string.rikosuudet0));
        rikosuudet1 = word.contains(getString(R.string.rikosuudet1));
        rikosuudet2 = word.contains(getString(R.string.rikosuudet2));
        rikosuudet3 = word.contains(getString(R.string.rikosuudet3));
        rikosuudet4 = word.contains(getString(R.string.rikosuudet4));
        rikosuudet5 = word.contains(getString(R.string.rikosuudet5));

        //Elokuva klassikot
        rikospokka0 = word.contains(getString(R.string.rikospokka0));
        rikospokka1 = word.contains(getString(R.string.rikospokka1));
        rikospokka2 = word.contains(getString(R.string.rikospokka2));
        rikospokka3 = word.contains(getString(R.string.rikospokka3));
        rikospokka4 = word.contains(getString(R.string.rikospokka4));
        rikospokka5 = word.contains(getString(R.string.rikospokka5));

        //Kotimaiset elokuvat
        rikosromaani0 = word.contains(getString(R.string.rikosromaani0));
        rikosromaani1 = word.contains(getString(R.string.rikosromaani1));
        rikosromaani2 = word.contains(getString(R.string.rikosromaani2));
        rikosromaani3 = word.contains(getString(R.string.rikosromaani3));
        rikosromaani4 = word.contains(getString(R.string.rikosromaani4));
        rikosromaani5 = word.contains(getString(R.string.rikosromaani5));

        //TV-sarjat
        truecrime0 = word.contains(getString(R.string.truecrime0));
        truecrime1 = word.contains(getString(R.string.truecrime1));
        truecrime2 = word.contains(getString(R.string.truecrime2));
        truecrime3 = word.contains(getString(R.string.truecrime3));
        truecrime4 = word.contains(getString(R.string.truecrime4));
        truecrime5 = word.contains(getString(R.string.truecrime5));

        //Lehdet
        lehd0 = word.contains(getString(R.string.lehd0));
        lehd1 = word.contains(getString(R.string.lehd1));
        lehd2 = word.contains(getString(R.string.lehd2));
        lehd3 = word.contains(getString(R.string.lehd3));
        lehd4 = word.contains(getString(R.string.lehd4));
        lehd5 = word.contains(getString(R.string.lehd5));

        //Sarjakuvat
        sarja0 = word.contains(getString(R.string.sarja0));
        sarja1 = word.contains(getString(R.string.sarja1));
        sarja2 = word.contains(getString(R.string.sarja2));
        sarja3 = word.contains(getString(R.string.sarja3));
        sarja4 = word.contains(getString(R.string.sarja4));
        sarja5 = word.contains(getString(R.string.sarja5));

        //Pokkarit
        pokka0 = word.contains(getString(R.string.pokka0));
        pokka1 = word.contains(getString(R.string.pokka1));
        pokka2 = word.contains(getString(R.string.pokka2));
        pokka3 = word.contains(getString(R.string.pokka3));
        pokka4 = word.contains(getString(R.string.pokka4));
        pokka5 = word.contains(getString(R.string.pokka5));

        engl0 = word.contains(getString(R.string.engl0));
        engl1 = word.contains(getString(R.string.engl1));
        engl2 = word.contains(getString(R.string.engl2));
        engl3 = word.contains(getString(R.string.engl3));
        engl4 = word.contains(getString(R.string.engl4));
        engl5 = word.contains(getString(R.string.engl5));

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
        } else if (ukrvena0 || ukrvena1 || ukrvena2 || ukrvena3 || ukrvena4 || ukrvena5) {
            ukrainajavenaja = true;
            laskuri = 0;
            vertaa();
        } else if (elokteat0 || elokteat1 || elokteat2 || elokteat3 || elokteat4 || elokteat5) {
            elokuvajateatteri = true;
            laskuri = 0;
            vertaa();
        } else if (elamank0 || elamank1 || elamank2 || elamank3 || elamank4 || elamank5) {
            elamankerta = true;
            laskuri = 0;
            vertaa();
        } else if (elamanv0 || elamanv1 || elamanv2 || elamanv3 || elamanv4 || elamanv5) {
            elamanviisaus = true;
            laskuri = 0;
            vertaa();
        } else if (filo0 || filo1 || filo2 || filo3 || filo4 || filo5) {
            filosofia = true;
            laskuri = 0;
            vertaa();
        } else if (hist0 || hist1 || hist2 || hist3 || hist4 || hist5) {
            historia = true;
            laskuri = 0;
            vertaa();
        } else if (musii0 || musii1 || musii2 || musii3 || musii4 || musii5) {
            musiikki = true;
            laskuri = 0;
            vertaa();
        } else if (polit0 || polit1 || polit2 || polit3 || polit4 || polit5) {
            politiikka = true;
            laskuri = 0;
            vertaa();
        } else if (sanak0 || sanak1 || sanak2 || sanak3 || sanak4 || sanak5) {
            sanakirja = true;
            laskuri = 0;
            vertaa();
        } else if (tiede0 || tiede1 || tiede2 || tiede3 || tiede4 || tiede5 || tiede6 || tiede7) {
            tiede = true;
            laskuri = 0;
            vertaa();
        } else if (lehd0 || lehd1 || lehd2 || lehd3 || lehd4 || lehd5) {
            lehdet = true;
            laskuri = 0;
            vertaa();
        } else if (sarja0 || sarja1 || sarja2 || sarja3 || sarja4 || sarja5) {
            sarjakuvat = true;
            laskuri = 0;
            vertaa();
        } else if (pokka0 || pokka1 || pokka2 || pokka3 || pokka4 || pokka5) {
            pokkarit = true;
            laskuri = 0;
            vertaa();
        } else if (engl0 || engl1 || engl2 || engl3 || engl4 || engl5){
            englanti = true;
            laskuri = 0;
            vertaa();
        } else if (djeco0 || djeco1 || djeco2 || djeco3 || djeco4 || djeco5) {
            djeco = true;
            laskuri = 0;
            vertaa();
        } else if (kids0 || kids1 || kids2 || kids3 || kids4 || kids5) {
            kids = true;
            laskuri = 0;
            vertaa();
        } else if (lastelo0 || lastelo1 || lastelo2 || lastelo3 || lastelo4 || lastelo5) {
            lastenelokuvat = true;
            laskuri = 0;
            vertaa();
        } else if (lasten0 || lasten1 || lasten2 || lasten3 || lasten4 || lasten5) {
            lastenkirjat = true;
            laskuri = 0;
            vertaa();
        } else if (lasttiet0 || lasttiet1 || lasttiet2 || lasttiet3 || lasttiet4 || lasttiet5) {
            lastentietikirjat = true;
            laskuri = 0;
            vertaa();
        } else if (last0_30 || last0_31 || last0_32 || last0_33 || last0_34 || last0_35) {
            lastenkirjat0_3 = true;
            laskuri = 0;
            vertaa();
        } else if (last4_60 || last4_61 || last4_62 || last4_63 || last4_64 || last4_65) {
            lastenkirjat4_6 = true;
            laskuri = 0;
            vertaa();
        } else if (last7_90 || last7_91 || last7_92 || last7_93 || last7_94 || last7_95) {
            lastenkirjat7_9 = true;
            laskuri = 0;
            vertaa();
        } else if (last10_120 || last10_121 || last10_122 || last10_123 || last10_124 || last10_125) {
            lastenkirjat10_12 = true;
            laskuri = 0;
            vertaa();
        } else if (maisa0 || maisa1 || maisa2 || maisa3 || maisa4 || maisa5) {
            maisa = true;
            laskuri = 0;
            vertaa();
        } else if (nuoret0 || nuoret1 || nuoret2 || nuoret3 || nuoret4 || nuoret5) {
            nuoret = true;
            laskuri = 0;
            vertaa();
        } else if (oppilo0 || oppilo1 || oppilo2 || oppilo3 || oppilo4 || oppilo5) {
            oppijailo = true;
            laskuri = 0;
            vertaa();
        } else if (pipsa0 || pipsa1 || pipsa2 || pipsa3 || pipsa4 || pipsa5) {
            pipsapossu = true;
            laskuri = 0;
            vertaa();
        } else if (lastpuuh0 || lastpuuh1 || lastpuuh2 || lastpuuh3 || lastpuuh4 || lastpuuh5) {
            puuhaalapsille = true;
            laskuri = 0;
            vertaa();
        } else if (tunne0 || tunne1 || tunne2 || tunne3 || tunne4 || tunne5) {
            tunnetaidot = true;
            laskuri = 0;
            vertaa();
        } else if (crimeNovEng0 || crimeNovEng1 || crimeNovEng2 || crimeNovEng3 || crimeNovEng4 || crimeNovEng5){
            CrimeNovelsInEnglish = true;
            laskuri = 0;
            vertaa();
        } else if (rikosuudet0 || rikosuudet1 || rikosuudet2 || rikosuudet3 || rikosuudet4 || rikosuudet5){
            RikoskirjaUutuudet = true;
            laskuri = 0;
            vertaa();
        } else if (rikospokka0 || rikospokka1 || rikospokka2 || rikospokka3 || rikospokka4 || rikospokka5){
            RikosPokkarit = true;
            laskuri = 0;
            vertaa();
        } else if (rikosromaani0 || rikosromaani1 || rikosromaani2 || rikosromaani3 || rikosromaani4 || rikosromaani5){
            Rikosromaanit = true;
            laskuri = 0;
            vertaa();
        } else if (truecrime0 || truecrime1 || truecrime2 || truecrime3 || truecrime4 || truecrime5){
            TrueCrime = true;
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

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                    hahmo.setImageResource(R.drawable.kuvarobo);
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

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                    hahmo.setImageResource(R.drawable.kuvarobo);
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

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                    hahmo.setImageResource(R.drawable.kuvarobo);
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

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                    hahmo.setImageResource(R.drawable.kuvarobo);
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

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                    hahmo.setImageResource(R.drawable.kuvarobo);
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

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                    hahmo.setImageResource(R.drawable.kuvarobo);
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

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                    hahmo.setImageResource(R.drawable.kuvarobo);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);

        }
        if (like) {

            ylakate.setVisibility(View.VISIBLE);

            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);
            markerRelativeLayout.leftMargin = 1600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                    hahmo.setImageResource(R.drawable.kuvarobo);
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

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                        hahmo.setImageResource(R.drawable.kuvarobo);
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

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                        hahmo.setImageResource(R.drawable.kuvarobo);
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

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                        hahmo.setImageResource(R.drawable.kuvarobo);
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

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                        hahmo.setImageResource(R.drawable.kuvarobo);
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

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                        hahmo.setImageResource(R.drawable.kuvarobo);
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

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
                // String x = getString(R.string.scifipuhe);
                MainActivity.textToSpeech.speak(getString(R.string.scifipuhe), TextToSpeech.QUEUE_FLUSH, null);
                MainActivity.scifi = false;
                scifi0 = false;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.kuvarobo);
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

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                        hahmo.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        if (elokuvajateatteri){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                        hahmo.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        if (elamankerta){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                        hahmo.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        if (elamanviisaus){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                        hahmo.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        if (filosofia){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                        hahmo.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        if (historia){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                        hahmo.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        if (musiikki){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                        hahmo.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        if (politiikka){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                        hahmo.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        if (sanakirja){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                        hahmo.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        if (MainActivity.tiede) {
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
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
                        hahmo.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        if (lehdet){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
                //String x = getString(R.string.tiedpuhe);
                MainActivity.textToSpeech.speak(getString(R.string.lehdpuhe), TextToSpeech.QUEUE_FLUSH, null);

                lehdet = false;
                lehd0 = false;
                lehd1 = false;
                lehd2 = false;
                lehd3 = false;
                lehd4 = false;
                lehd5 = false;


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        if (sarjakuvat){
                marker.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

                markerRelativeLayout.leftMargin = 2600;
                markerRelativeLayout.topMargin = 500;
                MainActivity.marker.setLayoutParams(markerRelativeLayout);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
                //String x = getString(R.string.tiedpuhe);
                MainActivity.textToSpeech.speak(getString(R.string.sarjapuhe), TextToSpeech.QUEUE_FLUSH, null);

                sarjakuvat = false;
                sarja0 = false;
                sarja1 = false;
                sarja2 = false;
                sarja3 = false;
                sarja4 = false;
                sarja5 = false;


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        if (pokkarit){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.pokkapuhe), TextToSpeech.QUEUE_FLUSH, null);
            pokkarit = false;
            pokka0 = false;
            pokka1 = false;
            pokka2 = false;
            pokka3 = false;
            pokka4 = false;
            pokka5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (englanti){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.englanpuhe), TextToSpeech.QUEUE_FLUSH, null);
            englanti = false;
            engl0 = false;
            engl1 = false;
            engl2 = false;
            engl3 = false;
            engl4 = false;
            engl5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (MainActivity.kiitoksia) {

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
                //String x = getString(R.string.kiitos);
                MainActivity.textToSpeech.speak(getString(R.string.kiitos), TextToSpeech.QUEUE_FLUSH, null);

                MainActivity.kiitoksia = false;
                kiito = false;
                oli = false;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);


                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                restart();
            }

        //Lastenkirjat kategorian toiminnot
        if (djeco){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.djecopuhe), TextToSpeech.QUEUE_FLUSH, null);
            djeco = false;
            djeco0 = false;
            djeco1 = false;
            djeco2 = false;
            djeco3 = false;
            djeco4 = false;
            djeco5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (kids){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.kidspuhe), TextToSpeech.QUEUE_FLUSH, null);
            kids = false;
            kids0 = false;
            kids1 = false;
            kids2 = false;
            kids3 = false;
            kids4 = false;
            kids5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (lastenelokuvat){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.lastelopuhe), TextToSpeech.QUEUE_FLUSH, null);
            lastenelokuvat = false;
            lastelo0 = false;
            lastelo1 = false;
            lastelo2 = false;
            lastelo3 = false;
            lastelo4 = false;
            lastelo5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (lastenkirjat){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.lastenpuhe), TextToSpeech.QUEUE_FLUSH, null);
            lastenkirjat = false;
            lasten0 = false;
            lasten1 = false;
            lasten2 = false;
            lasten3 = false;
            lasten4 = false;
            lasten5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (lastentietikirjat){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.lasttiepuhe), TextToSpeech.QUEUE_FLUSH, null);
            lastentietikirjat = false;
            lasttiet0 = false;
            lasttiet1 = false;
            lasttiet2 = false;
            lasttiet3 = false;
            lasttiet4 = false;
            lasttiet5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (lastenkirjat0_3){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.last0_3puhe), TextToSpeech.QUEUE_FLUSH, null);
            lastenkirjat0_3 = false;
            last0_30 = false;
            last0_31 = false;
            last0_32 = false;
            last0_33 = false;
            last0_34 = false;
            last0_35 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (lastenkirjat4_6){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.last4_6puhe), TextToSpeech.QUEUE_FLUSH, null);
            lastenkirjat4_6 = false;
            last4_60 = false;
            last4_61 = false;
            last4_62 = false;
            last4_63 = false;
            last4_64 = false;
            last4_65 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (lastenkirjat7_9){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.last7_9puhe), TextToSpeech.QUEUE_FLUSH, null);
            lastenkirjat7_9 = false;
            last7_90 = false;
            last7_91 = false;
            last7_92 = false;
            last7_93 = false;
            last7_94 = false;
            last7_95 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (lastenkirjat10_12){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.last10_12puhe), TextToSpeech.QUEUE_FLUSH, null);
            lastenkirjat10_12 = false;
            last10_120 = false;
            last10_121 = false;
            last10_122 = false;
            last10_123 = false;
            last10_124 = false;
            last10_125 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (maisa){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.maisapuhe), TextToSpeech.QUEUE_FLUSH, null);
            maisa = false;
            maisa0 = false;
            maisa1 = false;
            maisa2 = false;
            maisa3 = false;
            maisa4 = false;
            maisa5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (nuoret){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.nuoretpuhe), TextToSpeech.QUEUE_FLUSH, null);
            nuoret = false;
            nuoret0 = false;
            nuoret1 = false;
            nuoret2 = false;
            nuoret3 = false;
            nuoret4 = false;
            nuoret5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (oppijailo){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.oppilopuhe), TextToSpeech.QUEUE_FLUSH, null);
            oppijailo = false;
            oppilo0 = false;
            oppilo1 = false;
            oppilo2 = false;
            oppilo3 = false;
            oppilo4 = false;
            oppilo5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (pipsapossu){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.pipsapuhe), TextToSpeech.QUEUE_FLUSH, null);
            pipsapossu = false;
            pipsa0 = false;
            pipsa1 = false;
            pipsa2 = false;
            pipsa3 = false;
            pipsa4 = false;
            pipsa5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (puuhaalapsille){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.laptpuuhpuhe), TextToSpeech.QUEUE_FLUSH, null);
            puuhaalapsille = false;
            lastpuuh0 = false;
            lastpuuh1 = false;
            lastpuuh2 = false;
            lastpuuh3 = false;
            lastpuuh4 = false;
            lastpuuh5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (tunnetaidot){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.tunnepuhe), TextToSpeech.QUEUE_FLUSH, null);
            tunnetaidot = false;
            tunne0 = false;
            tunne1 = false;
            tunne2 = false;
            tunne3 = false;
            tunne4 = false;
            tunne5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }

        //Elokuva kategoria toiminnot
        if (CrimeNovelsInEnglish){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.crimeNovEngpuhe), TextToSpeech.QUEUE_FLUSH, null);
            CrimeNovelsInEnglish = false;
            crimeNovEng0 = false;
            crimeNovEng1 = false;
            crimeNovEng2 = false;
            crimeNovEng3 = false;
            crimeNovEng4 = false;
            crimeNovEng5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (RikoskirjaUutuudet){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.rikosuudetpuhe), TextToSpeech.QUEUE_FLUSH, null);
            RikoskirjaUutuudet = false;
            rikosuudet0 = false;
            rikosuudet1 = false;
            rikosuudet2 = false;
            rikosuudet3 = false;
            rikosuudet4 = false;
            rikosuudet5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (RikosPokkarit){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.rikospokkapuhe), TextToSpeech.QUEUE_FLUSH, null);
            RikosPokkarit = false;
            rikospokka0 = false;
            rikospokka1 = false;
            rikospokka2 = false;
            rikospokka3 = false;
            rikospokka4 = false;
            rikospokka5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (Rikosromaanit){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.rikosromaanipuhe), TextToSpeech.QUEUE_FLUSH, null);
            Rikosromaanit = false;
            rikosromaani0 = false;
            rikosromaani1 = false;
            rikosromaani2 = false;
            rikosromaani3 = false;
            rikosromaani4 = false;
            rikosromaani5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        if (TrueCrime){
            marker.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);

            markerRelativeLayout.leftMargin = 2600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.truecrimepuhe), TextToSpeech.QUEUE_FLUSH, null);
            TrueCrime = false;
            truecrime0 = false;
            truecrime1 = false;
            truecrime2 = false;
            truecrime3 = false;
            truecrime4 = false;
            truecrime5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        }



    protected void restart(){

        //Intent intent = getIntent()
        //finish();
        //startActivity(getIntent());

                speechRecognizer.stopListening();
                textToSpeech.stop();
                handler.removeCallbacksAndMessages(null);
                handler1.removeCallbacksAndMessages(null);
                timer.cancel();
                timerlopetus.cancel();
                aloitettu = false;
                ihminen = false;
                recreate();



    }

    /*public void writeToFile(String fileName, String content){
        File root = Environment.getRootDirectory();
        File dir = new File(root.getAbsolutePath()+"/Tallenus");
                if(!dir.exists()){
                    dir.mkdir();
                }
                File file = new File(dir,fileName);
        try {
            FileOutputStream write = new FileOutputStream(file);
            write.write(content.getBytes());
            write.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    /*public void writeToFile(String fileName, String content){
        File path = getApplicationContext().getFilesDir().getAbsoluteFile();
        try {
            FileOutputStream writer = new FileOutputStream(new File(path, fileName));
            //FileOutputStream writer = openFileOutput(fileName, MODE_PRIVATE);
            writer.write(content.getBytes());
            writer.close();
            Toast.makeText(getApplicationContext(),"Wrote to the file " + fileName, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

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
                    speechRecognizer.stopListening();
                    textToSpeech.stop();
                    handler.removeCallbacksAndMessages(null);
                    timer.cancel();
                    setLocale("en");
                    recreate();
                }else if(i == 1){
                    //Suomi
                    speechRecognizer.stopListening();
                    textToSpeech.stop();
                    handler.removeCallbacksAndMessages(null);
                    timer.cancel();
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


