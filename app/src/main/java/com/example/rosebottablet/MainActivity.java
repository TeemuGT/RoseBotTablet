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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    public static final Integer RecordAudioRequestCode = 1;
    /**
     * Jokaisella string.xml tiedostoon luodulla avainsanalla on oma bollean joka kertoo onko kyseinen avainsana vastannut vertailussa kuultuun puheeseen.
     * Avainsanojen booleaneja on useampi jokaista kategoriaa kohti ja ne on nimetty samalla tavalla ja eroteltu numeroinnilla.
     *
     * Ruotsi, Ranska ja Saksa alakategoriat
     * Ruotsi avainsanat
     */
    public  boolean ruotsinkieli0 = false;
    public  boolean ruotsinkieli1 = false;
    public  boolean ruotsinkieli2 = false;
    public  boolean ruotsinkieli3 = false;
    public  boolean ruotsinkieli4 = false;
    public  boolean ruotsinkieli5 = false;
    /**
     * Ranskan avainsanat
     */
    public  boolean ranskankieli0 = false;
    public  boolean ranskankieli1 = false;
    public  boolean ranskankieli2 = false;
    public  boolean ranskankieli3 = false;
    public  boolean ranskankieli4 = false;
    public  boolean ranskankieli5 = false;
    /**
     * Saksan avainsanat
     */
    public  boolean saksankieli0 = false;
    public  boolean saksankieli1 = false;
    public  boolean saksankieli2 = false;
    public  boolean saksankieli3 = false;
    public  boolean saksankieli4 = false;
    public  boolean saksankieli5 = false;
    /**
     * Kaunokirjallisuuden avain sanat
     * Baabel avainsanat
     */
    public  boolean baabel0 = false;
    public  boolean baabel1 = false;
    public  boolean baabel2 = false;
    public  boolean baabel3 = false;
    public  boolean baabel4 = false;
    public  boolean baabel5 = false;
    /**
     * Antikvaariset avainsanat
     */
    public  boolean antikvaariset0 = false;
    public  boolean antikvaariset1 = false;
    public  boolean antikvaariset2 = false;
    public  boolean antikvaariset3 = false;
    public  boolean antikvaariset4 = false;
    public  boolean antikvaariset5 = false;
    /**
     * Essee kategoria ei ole tällä hetkellä käytössä mutta jätetty kommenti viivojen sisälle mikäli sitä halutaan käyttää myöhemmin.
     *
     * Essee avain sanat
     *     public boolean essee0 = false;
     *     public boolean essee1 = false;
     *     public boolean essee2 = false;
     *     public boolean essee3 = false;
     *     public boolean essee4 = false;
     *     public boolean essee5 = false;
     */

    /**
     * Kaunokirjallisuuden avainsanat
     */
    public boolean kauno0 = false;
    public boolean kauno1 = false;
    public boolean kauno2 = false;
    public boolean kauno3 = false;
    public boolean kauno4 = false;
    public boolean kauno5 = false;
    /**
     * Keltainen kirjasto avainsanat
     */
    public boolean kelta0 =false;
    public boolean kelta1 =false;
    public boolean kelta2 =false;
    public boolean kelta3 =false;
    public boolean kelta4 =false;
    public boolean kelta5 =false;
    /**
     * Uudet kaunokirjallisuus avainsanat
     */
    public boolean uudetkauno0 = false;
    public boolean uudetkauno1 = false;
    public boolean uudetkauno2 = false;
    public boolean uudetkauno3 = false;
    public boolean uudetkauno4 = false;
    public boolean uudetkauno5 = false;
    /**
     * Klassikot avainsanat
     */
    public boolean klassikot0 = false;
    public boolean klassikot1 = false;
    public boolean klassikot2 = false;
    public boolean klassikot3 = false;
    public boolean klassikot4 = false;
    public boolean klassikot5 = false;
    /**
     * Otava avainsanat
     */
    public boolean otav0 = false;
    public boolean otav1 = false;
    public boolean otav2 = false;
    public boolean otav3 = false;
    public boolean otav4 = false;
    public boolean otav5 = false;
    /**
     * Äänikirjojen avainsanat
     */
    public boolean aani0 = false;
    public boolean aani1 = false;
    public boolean aani2 = false;
    public boolean aani3 = false;
    public boolean aani4 = false;
    public boolean aani5 = false;
    /**
     * Keskustelu ja kuinkaVoin auttaa ovat interaktiivisuuteen liittyviä kokeiluja puheessa jossa on luotu yksinkertaisimpia keskustelun tapoja ja niihin vastauksia.
     * Voit kysyä robotilta miten hän voi.
     * Voit kertoa robotille miten voit.
     */
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
    /**
     * Scifi, fantasia ja kauhu avainsana
     * Kaikki kolme kategoriaa on yhdistetty ja löytyvät fantasia alta.
     */
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
    /**
     * Runot avainsanat
     */
    public boolean runot0 = false;
    public boolean runot1 = false;
    public boolean runot2 = false;
    public boolean runot3 = false;
    public boolean runot4 = false;
    public boolean runot5 = false;
    /**
     * Riskos ja jännitys avainsanat
     */
    public boolean rikoja0 = false;
    public boolean rikoja1 = false;
    public boolean rikoja2 = false;
    public boolean rikoja3 = false;

    /**
     * Tietokirjallisuuden avainsanat
     * Ukraina ja venäjä avainsanat
     */
    public boolean ukrvena0 = false;
    public boolean ukrvena1 = false;
    public boolean ukrvena2 = false;
    public boolean ukrvena3 = false;
    public boolean ukrvena4 = false;
    public boolean ukrvena5 = false;
    /**
     * Elokuva ja teatteri avainsanat
     */
    public boolean elokteat0 = false;
    public boolean elokteat1 = false;
    public boolean elokteat2 = false;
    public boolean elokteat3 = false;
    public boolean elokteat4 = false;
    public boolean elokteat5 = false;
    /**
     * Elämänkerta avainsanat
     */
    public boolean elamank0 = false;
    public boolean elamank1 = false;
    public boolean elamank2 = false;
    public boolean elamank3 = false;
    public boolean elamank4 = false;
    public boolean elamank5 = false;
    /**
     * Antiikkikirjallisuus avainsanat
     */
    public boolean antiikkikirjallisuus0 = false;
    public boolean antiikkikirjallisuus1 = false;
    public boolean antiikkikirjallisuus2 = false;
    public boolean antiikkikirjallisuus3 = false;
    public boolean antiikkikirjallisuus4 = false;
    public boolean antiikkikirjallisuus5 = false;
    /**
     * Holokausti avainsanat
     */
    public boolean holokausti0 = false;
    public boolean holokausti1 = false;
    public boolean holokausti2 = false;
    public boolean holokausti3 = false;
    public boolean holokausti4 = false;
    public boolean holokausti5 = false;
    /**
     * Ilmastonmuutos avainsanat
     */
    public boolean ilmastonmuutos0 = false;
    public boolean ilmastonmuutos1 = false;
    public boolean ilmastonmuutos2 = false;
    public boolean ilmastonmuutos3 = false;
    public boolean ilmastonmuutos4 = false;
    public boolean ilmastonmuutos5 = false;
    /**
     * Filosofia avainsanat
     */
    public boolean filo0 = false;
    public boolean filo1 = false;
    public boolean filo2 = false;
    public boolean filo3 = false;
    public boolean filo4 = false;
    public boolean filo5 = false;
    /**
     * Historia avainsanat
     */
    public boolean hist0 = false;
    public boolean hist1 = false;
    public boolean hist2 = false;
    public boolean hist3 = false;
    public boolean hist4 = false;
    public boolean hist5 = false;
    /**
     * Musiikki avainsanat
     */
    public boolean musii0 = false;
    public boolean musii1 = false;
    public boolean musii2 = false;
    public boolean musii3 = false;
    public boolean musii4 = false;
    public boolean musii5 = false;
    /**
     * Politiikka avainsanat
     */
    public boolean polit0 = false;
    public boolean polit1 = false;
    public boolean polit2 = false;
    public boolean polit3 = false;
    public boolean polit4 = false;
    public boolean polit5 = false;
    /**
     * Sanakirjat avainsanat
     */
    public boolean sanak0 = false;
    public boolean sanak1 = false;
    public boolean sanak2 = false;
    public boolean sanak3 = false;
    public boolean sanak4 = false;
    public boolean sanak5 = false;
    /**
     * Tietokirjat avainsanat
     */
    public boolean tiede0 = false;
    public boolean tiede1 = false;
    public boolean tiede2 = false;
    public boolean tiede3 = false;
    public boolean tiede4 = false;
    public boolean tiede5 = false;
    public boolean tiede6 = false;
    public boolean tiede7 = false;
    /**
     * Kielet avainsanat
     */
    public boolean kielet0 = false;
    public boolean kielet1 = false;
    public boolean kielet2 = false;
    public boolean kielet3 = false;
    public boolean kielet4 = false;
    public boolean kielet5 = false;
    /**
     * Käsityö ja rakentaminen
     */
    public boolean kasityojarakentaminen0 = false;
    public boolean kasityojarakentaminen1 = false;
    public boolean kasityojarakentaminen2 = false;
    public boolean kasityojarakentaminen3 = false;
    public boolean kasityojarakentaminen4 = false;
    public boolean kasityojarakentaminen5 = false;
    /**
     * Luonnontieteet
     */
    public boolean luonnontieteet0 = false;
    public boolean luonnontieteet1 = false;
    public boolean luonnontieteet2 = false;
    public boolean luonnontieteet3 = false;
    public boolean luonnontieteet4 = false;
    public boolean luonnontieteet5 = false;
    /**
     * Luonto, Kasvit ja eläimet
     */
    public boolean luontokasvitelaimet0 = false;
    public boolean luontokasvitelaimet1 = false;
    public boolean luontokasvitelaimet2 = false;
    public boolean luontokasvitelaimet3 = false;
    public boolean luontokasvitelaimet4 = false;
    public boolean luontokasvitelaimet5 = false;
    /**
     * Maat ja Kulttuuri
     */
    public boolean maatjakulttuuri0 = false;
    public boolean maatjakulttuuri1 = false;
    public boolean maatjakulttuuri2 = false;
    public boolean maatjakulttuuri3 = false;
    public boolean maatjakulttuuri4 = false;
    public boolean maatjakulttuuri5 = false;
    /**
     * Matkailu
     */
    public boolean matkailu0 = false;
    public boolean matkailu1 = false;
    public boolean matkailu2 = false;
    public boolean matkailu3 = false;
    public boolean matkailu4 = false;
    public boolean matkailu5 = false;
    /**
     * Puutarha
     */
    public boolean puutarha0 = false;
    public boolean puutarha1 = false;
    public boolean puutarha2 = false;
    public boolean puutarha3 = false;
    public boolean puutarha4 = false;
    public boolean puutarha5 = false;
    /**
     * Ruoka
     */
    public boolean ruoka0 = false;
    public boolean ruoka1 = false;
    public boolean ruoka2 = false;
    public boolean ruoka3 = false;
    public boolean ruoka4 = false;
    public boolean ruoka5 = false;
    /**
     * Taide ja Design
     */
    public boolean taidejadesign0 = false;
    public boolean taidejadesign1 = false;
    public boolean taidejadesign2 = false;
    public boolean taidejadesign3 = false;
    public boolean taidejadesign4 = false;
    public boolean taidejadesign5 = false;
    /**
     * Terveys
     */
    public boolean terveys0 = false;
    public boolean terveys1 = false;
    public boolean terveys2 = false;
    public boolean terveys3 = false;
    public boolean terveys4 = false;
    public boolean terveys5 = false;
    /**
     * Tieteellinenkirjoittaminen ja kirjallisuus
     */
    public boolean tieteellinenkirjoittaminen0 = false;
    public boolean tieteellinenkirjoittaminen1 = false;
    public boolean tieteellinenkirjoittaminen2 = false;
    public boolean tieteellinenkirjoittaminen3 = false;
    public boolean tieteellinenkirjoittaminen4 = false;
    public boolean tieteellinenkirjoittaminen5 = false;
    /**
     * Urheilu
     */
    public boolean urheilu0 = false;
    public boolean urheilu1 = false;
    public boolean urheilu2 = false;
    public boolean urheilu3 = false;
    public boolean urheilu4 = false;
    public boolean urheilu5 = false;
    /**
     * Uskonnot
     */
    public boolean uskonnot0 = false;
    public boolean uskonnot1 = false;
    public boolean uskonnot2 = false;
    public boolean uskonnot3 = false;
    public boolean uskonnot4 = false;
    public boolean uskonnot5 = false;
    /**
     * Lastenkirjat avainsanat
     * Lasten romaanit avainsanat
     */
    public boolean lastenromaani0 = false;
    public boolean lastenromaani1 = false;
    public boolean lastenromaani2 = false;
    public boolean lastenromaani3 = false;
    public boolean lastenromaani4 = false;
    public boolean lastenromaani5 = false;
    /**
     * Englannin kieliset lastenkirjat avainsanat
     */
    public boolean kids0 = false;
    public boolean kids1 = false;
    public boolean kids2 = false;
    public boolean kids3 = false;
    public boolean kids4 = false;
    public boolean kids5 = false;
    /**
     * Lasten elokuvat avainsanat
     */
    public boolean lastelo0 = false;
    public boolean lastelo1 = false;
    public boolean lastelo2 = false;
    public boolean lastelo3 = false;
    public boolean lastelo4 = false;
    public boolean lastelo5 = false;
    /**
     * Lastenkirjat avainsanat
     */
    public boolean lasten0 = false;
    public boolean lasten1 = false;
    public boolean lasten2 = false;
    public boolean lasten3 = false;
    public boolean lasten4 = false;
    public boolean lasten5 = false;
    /**
     * Lastentietokirjat avainsanat
     */
    public boolean lasttiet0 = false;
    public boolean lasttiet1 = false;
    public boolean lasttiet2 = false;
    public boolean lasttiet3 = false;
    public boolean lasttiet4 = false;
    public boolean lasttiet5 = false;
    /**
     * Lastenkuvakirjat avainsanat
     */
    public boolean lastenkuvakirja0 = false;
    public boolean lastenkuvakirja1 = false;
    public boolean lastenkuvakirja2 = false;
    public boolean lastenkuvakirja3 = false;
    public boolean lastenkuvakirja4 = false;
    public boolean lastenkuvakirja5 = false;
    /**
     * Ruotsinkieliset lastenkirjat avainsanat
     */
    public boolean ruotsilasten0 = false;
    public boolean ruotsilasten1 = false;
    public boolean ruotsilasten2 = false;
    public boolean ruotsilasten3 = false;
    public boolean ruotsilasten4 = false;
    public boolean ruotsilasten5 = false;
    /**
     * Vauvojen ja taaperoiden avainsanat
     */
    public boolean vauvojenkirja0 = false;
    public boolean vauvojenkirja1 = false;
    public boolean vauvojenkirja2 = false;
    public boolean vauvojenkirja3 = false;
    public boolean vauvojenkirja4 = false;
    public boolean vauvojenkirja5 = false;
    /**
     * Nuorten kirjat avainsanat
     */
    public boolean nuoret0 = false;
    public boolean nuoret1 = false;
    public boolean nuoret2 = false;
    public boolean nuoret3 = false;
    public boolean nuoret4 = false;
    public boolean nuoret5 = false;
    /**
     * Puuhaa lapsille avainsanat
     */
    public boolean lastpuuh0 = false;
    public boolean lastpuuh1 = false;
    public boolean lastpuuh2 = false;
    public boolean lastpuuh3 = false;
    public boolean lastpuuh4 = false;
    public boolean lastpuuh5 = false;
    /**
     * Rikoskirjallisuus
     * Crime novels in English avainsanat
     */
    public boolean crimeNovEng0 = false;
    public boolean crimeNovEng1 = false;
    public boolean crimeNovEng2 = false;
    public boolean crimeNovEng3 = false;
    public boolean crimeNovEng4 = false;
    public boolean crimeNovEng5 = false;
    /**
     * Rikoskirja uutuudet avainsanat
     */
    public boolean rikosuudet0 = false;
    public boolean rikosuudet1 = false;
    public boolean rikosuudet2 = false;
    public boolean rikosuudet3 = false;
    public boolean rikosuudet4 = false;
    public boolean rikosuudet5 = false;
    /**
     * Rikospokkarit avainsanat
     */
    public boolean rikospokka0 = false;
    public boolean rikospokka1 = false;
    public boolean rikospokka2 = false;
    public boolean rikospokka3 = false;
    public boolean rikospokka4 = false;
    public boolean rikospokka5 = false;
    /**
     * Rikosromaanit avainsanat
     */
    public boolean rikosromaani0 = false;
    public boolean rikosromaani1 = false;
    public boolean rikosromaani2 = false;
    public boolean rikosromaani3 = false;
    public boolean rikosromaani4 = false;
    public boolean rikosromaani5 = false;
    /**
     * True Crime avainsanat
     */
    public boolean truecrime0 = false;
    public boolean truecrime1 = false;
    public boolean truecrime2 = false;
    public boolean truecrime3 = false;
    public boolean truecrime4 = false;
    public boolean truecrime5 = false;
    /**
     *  Pokkarit alakategorioiden avainsanat
     *  Kaunopokkarit
     */
    public boolean kaunopokkarit0 = false;
    public boolean kaunopokkarit1 = false;
    public boolean kaunopokkarit2 = false;
    public boolean kaunopokkarit3 = false;
    public boolean kaunopokkarit4 = false;
    public boolean kaunopokkarit5 = false;
    /**
     * Scifi ja fantasiapokkarit
     */
    public boolean scifijafantasiapokkarit0 = false;
    public boolean scifijafantasiapokkarit1 = false;
    public boolean scifijafantasiapokkarit2 = false;
    public boolean scifijafantasiapokkarit3 = false;
    public boolean scifijafantasiapokkarit4 = false;
    public boolean scifijafantasiapokkarit5 = false;
    /**
     * Tietopokkarit
     */
    public boolean tietopokkarit0 = false;
    public boolean tietopokkarit1 = false;
    public boolean tietopokkarit2 = false;
    public boolean tietopokkarit3 = false;
    public boolean tietopokkarit4 = false;
    public boolean tietopokkarit5 = false;
    /**
     * Englanninkieliset kirjat alakategoriat avainsanat
     * Crime
     */
    public boolean crime0 = false;
    public boolean crime1 = false;
    public boolean crime2 = false;
    public boolean crime3 = false;
    public boolean crime4 = false;
    public boolean crime5 = false;
    /**
     * Fiction
     */
    public boolean fiction0 = false;
    public boolean fiction1 = false;
    public boolean fiction2 = false;
    public boolean fiction3 = false;
    public boolean fiction4 = false;
    public boolean fiction5 = false;
    /**
     * Non-fiction
     */
    public boolean nonfiction0 = false;
    public boolean nonfiction1 = false;
    public boolean nonfiction2 = false;
    public boolean nonfiction3 = false;
    public boolean nonfiction4 = false;
    public boolean nonfiction5 = false;
    /**
     * Poetry
     */
    public boolean poetry0 = false;
    public boolean poetry1 = false;
    public boolean poetry2 = false;
    public boolean poetry3 = false;
    public boolean poetry4 = false;
    public boolean poetry5 = false;
    /**
     * Englanninkielinen scifi
     */
    public boolean engscifi0 = false;
    public boolean engscifi1 = false;
    public boolean engscifi2 = false;
    public boolean engscifi3 = false;
    public boolean engscifi4 = false;
    public boolean engscifi5 = false;
    /**
     * Uudet fiction
     */
    public boolean newfiction0 = false;
    public boolean newfiction1 = false;
    public boolean newfiction2 = false;
    public boolean newfiction3 = false;
    public boolean newfiction4 = false;
    public boolean newfiction5 = false;
    /**
     * Uudet Non-fiction
     */
    public boolean newnonfiction0 = false;
    public boolean newnonfiction1 = false;
    public boolean newnonfiction2 = false;
    public boolean newnonfiction3 = false;
    public boolean newnonfiction4 = false;
    public boolean newnonfiction5 = false;
    /**
     * Lehdet avainsanat
     */
    public boolean lehd0 = false;
    public boolean lehd1 = false;
    public boolean lehd2 = false;
    public boolean lehd3 = false;
    public boolean lehd4 = false;
    public boolean lehd5 = false;
    /**
     * Sarjakuvat avainsanat
     */
    public boolean sarja0 = false;
    public boolean sarja1 = false;
    public boolean sarja2 = false;
    public boolean sarja3 = false;
    public boolean sarja4 = false;
    public boolean sarja5 = false;
    /**
     * Pokkarit yleisesti avainsanat (ei käytössä)
     */
    public boolean pokka0 = false;
    public boolean pokka1 = false;
    public boolean pokka2 = false;
    public boolean pokka3 = false;
    public boolean pokka4 = false;
    public boolean pokka5 = false;
    /**
     * Englanninkieliset avainsanat (ei köytössä)
     */
    public boolean engl0 = false;
    public boolean engl1 = false;
    public boolean engl2 = false;
    public boolean engl3 = false;
    public boolean engl4 = false;
    public boolean engl5 = false;
    /**
     * Elokuvat avainsanat
     */
    public boolean elokuvat0 = false;
    public boolean elokuvat1 = false;
    public boolean elokuvat2 = false;
    public boolean elokuvat3 = false;
    public boolean elokuvat4 = false;
    public boolean elokuvat5 = false;
    /**
     * Ajankohtaiset avainsanat
     */
    public boolean ajankohtaista0 = false;
    public boolean ajankohtaista1 = false;
    public boolean ajankohtaista2 = false;
    public boolean ajankohtaista3 = false;
    public boolean ajankohtaista4 = false;
    public boolean ajankohtaista5 = false;
    /**
     * Paperituotteet avainsanat
     */
    public boolean paperituotteet0 = false;
    public boolean paperituotteet1 = false;
    public boolean paperituotteet2 = false;
    public boolean paperituotteet3 = false;
    public boolean paperituotteet4 = false;
    public boolean paperituotteet5 = false;
    /**
     * Kiitos avainsata
     * Vastauksena kiittää käyttäjää ja palaa takaisin nukkumis tilaan.
     */
    public boolean kiitos1 = false;
    public boolean oli = false;
    public boolean kiito = false;
    /**
     * Sijainti avainsanat (ei käytössä)
     */
    public boolean sijainti0 = false;
    public boolean sijainti1 = false;
    public boolean sijainti2 = false;
    public boolean sijainti3 = false;
    /**
     * Jokaisella kategorialla on sijainteja mistä ne löytyvät.
     * Jokainen sijainti kartalla omistaa oman markkerin joka tuodaan tarvittaessa näkyviin.
     * Markkerit on nimetty "marker" ja erotettu toisistaan numerolla
     * Lisä materiaalista löytyy myymälän kartta jossa jokainen sijainti on numeroitu. Tässä olevat markkerin numerot vastaavat kartassa oleviin numeroihin.
     * Esim. Marker23 on osoitettu samaan kohtaan kuin lisämateriaali kartassa sijainnissa 23.
     */
    static ImageView marker1, marker2, marker3, marker4, marker5, marker6, marker7,marker8, marker9, marker10, marker11, marker12, marker13, marker14, marker15, marker16, marker17,marker18, marker19, marker20, marker21, marker22, marker23, marker24, marker25, marker26, marker27,marker28, marker29, marker30, marker31, marker32, marker33, marker34, marker35, marker36, marker37,marker38, marker39, marker40, marker41, marker42, marker43, marker44, marker45, marker46, marker47,marker48, marker49, marker50, marker51, marker52, marker53, marker54;
    /**
     * Luodaan layout objekteja
     * Kartatta, Robotti figuuri kahdessa eri sijainnissa (Hahmo).
     * Aloitus nappi converBtn
     * Aloita kuuntelu nappi (voiceBtn, sijoitettu xml tiedostossa hahmo1 päälle)
     * Restart nappi (resButton)
     * Myymälän logo
     * otsikko, vaihtoehtoinen (ei käytösä)
     * textToSpeech objekti
     * Pari textView joita pystyy hyödyntämään tarvittaessa. Esim text1 ollut käytössä kertomaan havaitseeko kamera liikettä ja kertonut sen näytöllä.(Poistettu käytöstä asiakas kokeilua varten)
     */
    //Layout Objekteja
    static ImageView kartta;
    static ImageView hahmo;
    static ImageView hahmo1;
    ImageButton voiceBtn;
    public static TextView text;
    static TextView text1;
    static Button converBtn;
    ImageButton resButton;
    static TextToSpeech textToSpeech;
    static ImageView gif;
    TextView otsikko;
    ImageView logo;
    /**
     * Kamera preview ja kuvien tallennuskohteet imageVieweihin. Kuvat eivät tule missään vaiheessa näkyviin ruudulle.
     */
    private TextureView textureView;
    private ImageView imageView;
    private ImageView imageView2;
    /**
     * Luodaa molemmille kuville kaksi bitmap objektia jossa niitä käsitellään ja verrataan toisiinsa.
     */
    static Bitmap bitmap;
    static Bitmap bitmap2;
    static Bitmap resized;
    static Bitmap resized2;
    /**
     * Luodaan Timereita sekä Handlereita joita hyödynnetään jotta voidaan tehdä useampaa toimintaa saman aikaisesti.
     */
    Timer timerlopetus = new Timer();
    Timer timer = new Timer();
    Handler handler = new Handler();
    Handler handlerloppu = new Handler();
    Handler handler1 = new Handler();
    Handler handler2 = new Handler();
    /**
     * Logcat LOG ilmoitus jolla tulostetaan tietoa mitä halutaan serata kehityksen kannalta.
     */
    private static final Float LOG_TAG = compareEquivalance();
    /**
     * SimpleDataFormat luodaan aikaleima jota tallenetaan databaseen datankeruussa.
     * Käytetään Firebase databasea.
     */
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String millisInString  = dateFormat.format(new Date());
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    /**
     * Int kuva käytetään vaihtamaan uuden kuvan sijaintia jotta joka toinen kuva korvaisi aina joka toisen.
     */
    int kuva = 0;
    /**
     * Kamera kuvan määrittelyä
     * Kameran objektien luontia (kaikki ei käytössä, koska poistettu tallentaminen ja preview näkyminen)
     */
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
    /**
     * Boolean arvoja
     * ihminen muutetaan true:ksi mikäli kuvien vertailussa huomataan muutos
     * aloitettu muutetaan true:ksi mikäli
     * aloitettu oli kokeilu käytössä määrittämään että sovellus on aktivoitu. (ei käytössä)
     */
    boolean ihminen = false;
    boolean aloitettu = false;
    /**
     * Int kuvalasku käytetään laskurissa kertomaan kuinka monta kuvaa on otettu.
     * Int laskuri laskee kuikamonta kertaa sovellus kuulee jotain mitä se ei ymmörrä. Int nollataan joka kerta kun sovellus löytää kuulemastaan avainsanan.
     */
    int kuvalasku;
    public int laskuri =  0;
    /**
     * Int i on käytössä lopetus metodissa. Se pyörii timerissa ja joka kierroksella siihen lisätään yksi. i määrittää milloin sovelluksen tulisi käynnistyä uudelleen.
     */
    int i = 0;
    /**
     * String word on tallennus sijainti puheen tunnistukselle. Sovelluksen kuulema puhe tallenetaan lopulta tähän ja sitä käytetään vertailussa aviansanoihin. word tallennetaan myös tietokantaan tutkimus käyttöön.
     */
    public static String word = "";
    /**
     * FILE_NAME oli yritys tallentaa paikallisesti teksti tiedostoon. (ei tällähetkellä käytössä)
     */
    private static final String FILE_NAME = "text.txt";
    /**
     * mainLayout on objekti joka luodaan xml layout tiedostosta.
     */
    @SuppressLint("StaticFieldLeak")
    public static ViewGroup mainLayout;
    /**
     * Jokaisella kategorialla on oma boolean joka kertoo mikäli sitä haetaan.
     * Puheen tunnistuksen ja napin painamisella molemmilla ohjataan tämä boolean true:ksi joka mahdollistaa halutun toiminan
     * Boolean määrittelee vertaa() metodissa halutun toiminta tavan.
     *
     * Ruotsin, ranskan ja Saksan booleanit
     */
    public static boolean ruotsinkieli = false;
    public static boolean ranskankieli = false;
    public static boolean saksankieli = false;
    /**
     * Kaunokirjallisuus booleanit
     */
    public static boolean baabel = false;
    public static boolean antikvaariset = false;
    //public static boolean essee = false;
    public static boolean kaunokirjallisuus = false;
    public static boolean keltainen = false;
    public static boolean uudetkaunokirjallisuus = false;
    public static boolean klassikot = false;
    public static boolean otava = false;
    public static boolean aanikirja = false;
    public static boolean fantasia = false;
    public static boolean runo = false;
    public static boolean rikojanitus;
    /**
     * Tietokirjallisuus booleanit
     */
    public static boolean ukrainajavenaja = false;
    public static boolean elokuvajateatteri = false;
    public static boolean elamankerta = false;
    public static boolean antiikkikirjallisuus = false;
    public static boolean holokausti = false;
    public static boolean ilmastonmuutos = false;
    public static boolean filosofia = false;
    public static boolean historia = false;
    public static boolean musiikki = false;
    public static boolean politiikka = false;
    public static boolean sanakirja = false;
    public static boolean tiede = false;
    public static boolean kielet = false;
    public static boolean kasityojarakentaminen = false;
    public static boolean luonnontieteet = false;
    public static boolean luontokasvitelaimet = false;
    public static boolean maatjakulttuuri = false;
    public static boolean matkailu = false;
    public static boolean puutarha = false;
    public static boolean ruoka = false;
    public static boolean taidejadesign = false;
    public static boolean terveys = false;
    public static boolean tieteellinenkirjoittaminen = false;
    public static boolean urheilu = false;
    public static boolean uskonnot = false;
    /**
     * Lastenkirjat booleanit
     */
    public static boolean lastenromaani = false;
    public static boolean kids = false;
    public static boolean lastenelokuvat = false;
    public static boolean lastenkirjat = false;
    public static boolean lastentietikirjat = false;
    public static boolean lastenkuvakirja = false;
    public static boolean ruotsinkielisetlastenkirjat = false;
    public static boolean vauvojenkirja = false;
    public static boolean nuoret = false;
    public static boolean puuhaalapsille = false;
    /**
     * Rikoskirjat booleanit
     */
    public static boolean CrimeNovelsInEnglish = false;
    public static boolean RikoskirjaUutuudet = false;
    public static boolean RikosPokkarit = false;
    public static boolean Rikosromaanit = false;
    public static boolean TrueCrime = false;
    /**
     * Pokkarit booleanit
     */
    public static boolean kaunopokkarit = false;
    public static boolean rikosjajannityspokkarit = false;
    public static boolean scifijafantasiapokkarit = false;
    public static boolean tietopokkarit = false;
    /**
     * Englanninkieliset kirjat booleanit
     */
    public static boolean crime = false;
    public static boolean fiction = false;
    public static boolean nonfiction = false;
    public static boolean poetry = false;
    public static boolean engscifi = false;
    public static boolean newfiction = false;
    public static boolean newnonfiction = false;
    /**
     * Yksittäis yläkategorioita jolla ei olo alakategorioita vaan omat sijainnit booleanit
     */
    public static boolean lehdet = false;
    public static boolean sarjakuvat = false;
    public static boolean pokkarit = false;
    public static boolean englanti = false;
    public static boolean elokuvat = false;
    public static boolean ajankohtaista = false;
    public static boolean paperituotteet = false;
    /**
     * Interaktiivisuus kokeilu booleanit
     */
    public static boolean kiitoksia = false;
    public static boolean sijainti = false;
    @SuppressLint("StaticFieldLeak")
    /**
    * Luodaan jokaiselle yläkategorialle ja alagategorialle oma nappi
    * Ruotsi, Ranska ja Saksa napit
    */
    public Button ruotsinkielibtn;
    public Button ranskankielibtn;
    public Button saksankielibtn;
    /**
     * Kaunokirjallisuus napit
     */
    public Button baabebtn;
    public Button antikvaarisetbtn;
    //public Button esseebtn;
    public Button kaunokibtn;
    public Button keltainbtn;
    public Button uudetkaunobtn;
    public Button klassikotbtn;
    public Button otavabtn;
    public Button aanikibtn;
    public Button fantasibtn;
    public Button runobtn;
    public Button rikojanbtn;
    public Button uudetrikosbtn;
    /**
     * Tietokirjallisuus napit
     */
    public Button ukravenabtn;
    public Button eloteatbtn;
    public Button elamankbtn;
    public Button antiikkikirjallisuusbtn;
    public Button holokaustibtn;
    public Button ilmastonmuutosbtn;
    public Button filobtn;
    public Button histbtn;
    public Button musiibtn;
    public Button politbtn;
    public Button sanakbtn;
    public Button tietobtn;
    public Button kieletbtn;
    public Button kasityojarakentaminenbtn;
    public Button luonnontieteetbtn;
    public Button luontokasvitelaimetbtn;
    public Button maatjakulttuuribtn;
    public Button matkailubtn;
    public Button puutarhabtn;
    public Button ruokabtn;
    public Button taidejadesignbtn;
    public Button terveysbtn;
    public Button tieteellinenkirjoittaminenbtn;
    public Button urheilubtn;
    public Button uskonnotbtn;
    /**
     * Lastenkirjat napit
     */
    public Button lastenromaanibtn;
    public Button kidsbtn;
    public Button lastelobtn;
    public Button lastenbtn;
    public Button lasttietbtn;
    public Button lastenkuvakirjabtn;
    public Button ruotsilastenbtn;
    public Button vauvojenkirjabtn;
    public Button nuoretbtn;
    public Button lastpuuhbtn;
    /**
     * Rikoskirjallisuus
     */
    public Button crimeNovEngbtn;
    public Button rikosuudetbtn;
    public Button rikospokkabtn;
    public Button rikosromaanibtn;
    public Button truecrimebtn;
    /**
     * Pokkarit napit
     */
    public Button kaunopokkaritbtn;
    public Button rikosjajannityspokkaritbtn;
    public Button scifijafantasiapokkaritbtn;
    public Button tietopokkaritbtn;
    /**
     * Englaninkieliset kirjat napit
     */
    public Button crimebtn;
    public Button fictionbnt;
    public Button nonfictionbtn;
    public Button poetrybtn;
    public Button engscifibtn;
    public Button newfictionbtn;
    public Button newnonfictionbtn;
    /**
     * Kielen vaihto nappi
     */
    public Button shlang;
    /**
     * Yläkategoria napit
     */
    public Button kaunobtn;
    public Button tietogabtn;
    public Button lapsetbtn;
    public Button pokkarbtn;
    public Button sarjabtn;
    public Button rikosbtn;
    public Button lehdetbtn;
    public Button englanbtn;
    public Button svenFranDeutsbtn;
    public Button elokuvatbtn;
    public Button ajankohtaistabtn;
    public Button paperituotteetbtn;
    /**
     * Paluu nappi jolla saadaan näkymiin uudelleen kaikki yläkategoriat
     * Tällähetkellä ImageButtonina. Aikasiemmin ollut perus nappi ja on vielä tallella kommentti kenttänä.
     */
    public ImageButton ylakate;
    //public Button ylakate;
    /**
     * Nollataan mylayout
     */
    private RelativeLayout mylayout = null;
    /**
     * Luodaan tarvittavat objektit puheen tunnistukselle.
     */
    //Puheen tunnistus
    public static SpeechRecognizer speechRecognizer;
    public static Intent speechRecognizerIntent;
    /**
     * Määritellään kameralle toimintoja eri tilanteissa.
     */
    CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            cameraDevice =camera;
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
    /**
    * onCreate on pää metodi jossa pyöritetään näytöllä olevia objekteja sekä toimintaa.
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         *  Piilotetaan yläbanneri nkyvistä.
         */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        /**
         * Haetaan aktiviteetin heti alussa valittu kieli loadLocale metodista.
         */
        loadLocale();
        /**
          Sovelluksen alussa aktyiviteetin käynnistyttä sovellus on "nukkumistilassa" jossa pyöritetään tätä timeria joka ottaa kuvia takePicture metodin avulla.
          Timeri pyörii niin kauan kun kukaan ei käytä sovellusta tai sitten tietty kuva määrä täyttyy ja sovellus käynnistyy uudelleen.
         */
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        /**
                         * Jos ihminen on false, eli kuvissa ei ole eroavaisuutta jatketaan timerin pyörittämistä ja otetaan uusi kuva.
                         * logcattiin tulostetaan kuvan numero järjestyksessä.
                         */
                        if (!ihminen) {
                            kuvalasku++;
                            takePicture();
                            Log.e(String.valueOf(LOG_TAG), "Kuva numero " + kuvalasku);
                        }
                        /**
                         * Jos ihminen on muuttunut true:ksi on kuvissa näkyvyt eroavaisuutta.
                         * Tällöin timeri pysäutetään, jonka jälkeen haetaan aloita metodi jolla sovellus aktivoituu.
                         */
                        if (ihminen) {
                            timer.cancel();
                            aloita();
                        }
                        /**
                         * Jos kuvien määrä ylittää arvon 20 käynnistetään sovellus uudelleen.
                         */
                        if (kuvalasku > 20){
                            restart();
                        }
                    }
                });
            }
        }, 0, 5000);/** lopussa määritellään timerin kesto millisekunneissa joka määrittelee kuvien otto nopeuden. */
        /**
        * Haetaan acivity_main.xml tiedostosta näkymä näytölle.
        */
        setContentView(R.layout.activity_main);
        /**
         * Kysytään mikrofoonin käyttö oikeutta
         */
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            checkPermission();
        }
        /**
         * Määritellään puheen tunnistaminen
         * Haetaan tarvittavat toiminnot oikeassa muodossa.
         * Heataan kieli millä halutaan kuunnella (käytetään loadLocale metodia jotta valittu kieli on sama mikä ollaan valittu sovelluksen käyttökieleksi):
         */
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, loadLocale());
        /**
         * Luodaan puheentunnistus listener jota voidaan hakea kun halutaan kuunnella puhetta.
         * Täällä myös luodaan kaikki puheen tunnistuksessa käytävät kohdat ja mitä niissä tehdään.
         */
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            /**
             * Ennen kuuntelua valmistudutaan kuuntelemaan ja tässä vaiheessa haetaan lopetus metodi jotta sovellus ei sammu itsestään.
             */
            @Override
            public void onReadyForSpeech(Bundle bundle) {
                lopetus();


            }

            /**
             * Kun puheen kuuntelu aloitetaan vaihdetaan näytöllä oleva figuuri kuuntelevaan figuuriin imageView hahmo1:ssä
             */
            @Override
            public void onBeginningOfSpeech() {
                hahmo1.setImageResource(R.drawable.kuuntelevarobo);
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            /**
             * Kun kuuntelu lopetetaan vaidetaan näytöllä oleva figuuri takaisi perus tilaan.
             */
            @Override
            public void onEndOfSpeech() {
                hahmo1.setImageResource(R.drawable.kuvarobo);

            }

            @Override
            public void onError(int i) {

            }

            /**
             * onResult kohdassa käsitellään kuultua dataa. tunnistettu puhe laitetataan ArrayList josta se siirretään "word" stringiin helposti käsiteltäväksi
             */
            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null) {
                    //keeper = matches.get(0);
                    text.setText(matches.get(0));
                    word = text.getText().toString();
                    /**
                    * kuultu puhe tallenetaan myös databaseen aikaleiman kanssa.
                    */
                    DatabaseReference myRef = database.getReference("text");
                    myRef.push().setValue(millisInString + " " + word);
                    /**
                    * Tuloksien saamisen jälkeen heataan tunnistus metodi jossa saatuja tuloksia käsitellään.
                    */
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
        /**
         * kaksi ylimääräistä imageViewiä joita voi hyödyntää tarpeen mukaan. (ei käytössä)
         */
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        /**
         * textureView on näkymätön kameran preview
         */
        textureView = (TextureView) findViewById(R.id.textureView);
        assert textureView != null;
        textureView.setSurfaceTextureListener(textureListener);
        /**
         * Haetaan main layout xml tiedostosta.
        */
        mylayout = (RelativeLayout) findViewById(R.id.main);
        /**
        * Heataan kaikki sijainti markkerit xml tiedostosta.
        */
        marker1 = (ImageView) findViewById(R.id.marker1);
        marker2 = (ImageView) findViewById(R.id.marker2);
        marker3 = (ImageView) findViewById(R.id.marker3);
        marker4 = (ImageView) findViewById(R.id.marker4);
        marker5 = (ImageView) findViewById(R.id.marker5);
        marker6 = (ImageView) findViewById(R.id.marker6);
        marker7 = (ImageView) findViewById(R.id.marker7);
        marker8 = (ImageView) findViewById(R.id.marker8);
        marker9 = (ImageView) findViewById(R.id.marker9);
        marker10 = (ImageView) findViewById(R.id.marker10);
        marker11 = (ImageView) findViewById(R.id.marker11);
        marker12 = (ImageView) findViewById(R.id.marker12);
        marker13 = (ImageView) findViewById(R.id.marker13);
        marker14 = (ImageView) findViewById(R.id.marker14);
        marker15 = (ImageView) findViewById(R.id.marker15);
        marker16 = (ImageView) findViewById(R.id.marker16);
        marker17 = (ImageView) findViewById(R.id.marker17);
        marker18 = (ImageView) findViewById(R.id.marker18);
        marker19 = (ImageView) findViewById(R.id.marker19);
        marker20 = (ImageView) findViewById(R.id.marker20);
        marker21 = (ImageView) findViewById(R.id.marker21);
        marker22 = (ImageView) findViewById(R.id.marker22);
        marker23 = (ImageView) findViewById(R.id.marker23);
        marker24 = (ImageView) findViewById(R.id.marker24);
        marker25 = (ImageView) findViewById(R.id.marker25);
        marker26 = (ImageView) findViewById(R.id.marker26);
        marker27 = (ImageView) findViewById(R.id.marker27);
        marker28 = (ImageView) findViewById(R.id.marker28);
        marker29 = (ImageView) findViewById(R.id.marker29);
        marker30 = (ImageView) findViewById(R.id.marker30);
        marker31 = (ImageView) findViewById(R.id.marker31);
        marker32 = (ImageView) findViewById(R.id.marker32);
        marker33 = (ImageView) findViewById(R.id.marker33);
        marker34 = (ImageView) findViewById(R.id.marker34);
        marker35 = (ImageView) findViewById(R.id.marker35);
        marker36 = (ImageView) findViewById(R.id.marker36);
        marker37 = (ImageView) findViewById(R.id.marker37);
        marker38 = (ImageView) findViewById(R.id.marker38);
        marker39 = (ImageView) findViewById(R.id.marker39);
        marker40 = (ImageView) findViewById(R.id.marker40);
        marker41 = (ImageView) findViewById(R.id.marker41);
        marker42 = (ImageView) findViewById(R.id.marker42);
        marker43 = (ImageView) findViewById(R.id.marker43);
        marker44 = (ImageView) findViewById(R.id.marker44);
        marker45 = (ImageView) findViewById(R.id.marker45);
        marker46 = (ImageView) findViewById(R.id.marker46);
        marker47 = (ImageView) findViewById(R.id.marker47);
        marker48 = (ImageView) findViewById(R.id.marker48);
        marker49 = (ImageView) findViewById(R.id.marker49);
        marker50 = (ImageView) findViewById(R.id.marker50);
        marker51 = (ImageView) findViewById(R.id.marker51);
        marker52 = (ImageView) findViewById(R.id.marker52);
        marker53 = (ImageView) findViewById(R.id.marker53);
        marker54 = (ImageView) findViewById(R.id.marker54);
        /**
         * Heataan kartta, ha figuuri (hahmo ja hahmo1) xml tiedostosta
         */
        mainLayout = (RelativeLayout) findViewById(R.id.main);
        kartta = (ImageView) findViewById(R.id.kartta);
        hahmo = (ImageView) findViewById(R.id.hahmo);
        hahmo1 = (ImageView) findViewById(R.id.hahmo1);
        /**
         * Haetaan xml tiedostosta kaikki napit
         *
         * Ruotsi, Ransaka ja Saksa nappien objektien haku
         */
        ruotsinkielibtn = findViewById(R.id.ruotsinkielibtn);
        ranskankielibtn = findViewById(R.id.ranskankielibtn);
        saksankielibtn = findViewById(R.id.saksankielibtn);
        /**
         * Kaunokirjallisuus nappien objektien haku
         */
        baabebtn = findViewById(R.id.baabebtn);
        antikvaarisetbtn = findViewById(R.id.antikvaarisetbtn);
        //esseebtn = findViewById(R.id.esseebtn);
        kaunokibtn = findViewById(R.id.kaunokibtn);
        keltainbtn = findViewById(R.id.keltainbtn);
        uudetkaunobtn = findViewById(R.id.uudetkaunobtn);
        klassikotbtn = findViewById(R.id.klassikotbtn);
        otavabtn = findViewById(R.id.otavabtn);
        aanikibtn = findViewById(R.id.aanikibtn);
        fantasibtn = findViewById(R.id.fantasibtn);
        runobtn = findViewById(R.id.runobtn);
        rikojanbtn = findViewById(R.id.rikojanbtn);
        uudetrikosbtn = findViewById(R.id.uudetrikosbtn);
        /**
         * Tietokirjallisuus nappien objektien haku
         */
        ukravenabtn = findViewById(R.id.ukravenabtn);
        eloteatbtn = findViewById(R.id.eloteatbtn);
        elamankbtn = findViewById(R.id.elamankbtn);
        antiikkikirjallisuusbtn = findViewById(R.id.antiikkikirjallisuusbtn);
        holokaustibtn = findViewById(R.id.holokaustibtn);
        ilmastonmuutosbtn = findViewById(R.id.ilmastonmuutosbtn);
        filobtn = findViewById(R.id.filobtn);
        histbtn = findViewById(R.id.histbtn);
        musiibtn = findViewById(R.id.musiibtn);
        politbtn = findViewById(R.id.politbtn);
        sanakbtn = findViewById(R.id.sanakbtn);
        tietobtn = findViewById(R.id.tietobtn);
        kieletbtn = findViewById(R.id.kieletbtn);
        kasityojarakentaminenbtn = findViewById(R.id.kasityojarakentaminenbtn);
        luonnontieteetbtn = findViewById(R.id.luonnontieteetbtn);
        luontokasvitelaimetbtn = findViewById(R.id.luontokasvitelaimetbtn);
        maatjakulttuuribtn = findViewById(R.id.maatjakulttuuribtn);
        matkailubtn = findViewById(R.id.matkailubtn);
        puutarhabtn = findViewById(R.id.puutarhabtn);
        ruokabtn = findViewById(R.id.ruokabtn);
        taidejadesignbtn = findViewById(R.id.taidejadesignbtn);
        terveysbtn = findViewById(R.id.terveysbtn);
        tieteellinenkirjoittaminenbtn = findViewById(R.id.tieteellinenkirjoittaminenbtn);
        urheilubtn = findViewById(R.id.urheilubtn);
        uskonnotbtn = findViewById(R.id.uskonnotbtn);
        /**
         * Lastenkirja nappien objektien haku
         */
        lastenromaanibtn = findViewById(R.id.lastenromaanibtn);
        kidsbtn = findViewById(R.id.kidsbtn);
        lastelobtn = findViewById(R.id.lastelobtn);
        lastenbtn = findViewById(R.id.lastenbtn);
        lasttietbtn = findViewById(R.id.lasttietbtn);
        lastenkuvakirjabtn = findViewById(R.id.lastenkuvakirjabtn);
        ruotsilastenbtn = findViewById(R.id.ruotsilastenbtn);
        vauvojenkirjabtn = findViewById(R.id.vauvojenkirjabtn);
        nuoretbtn = findViewById(R.id.nuoretbtn);
        lastpuuhbtn = findViewById(R.id.lastpuuhbtn);
        /**
         * Rikoskirjojen objektien haku
         */
        crimeNovEngbtn = findViewById(R.id.crimeNovEngbtn);
        rikosuudetbtn = findViewById(R.id.rikosuudetbtn);
        rikospokkabtn = findViewById(R.id.rikospokkabtn);
        rikosromaanibtn = findViewById(R.id.rikosromaanibtn);
        truecrimebtn = findViewById(R.id.truecrimebtn);
        /**
         * Pokkarit nappien objektien haku
         */
        kaunopokkaritbtn = findViewById(R.id.kaunopokkaritbtn);
        rikosjajannityspokkaritbtn = findViewById(R.id.rikosjajannityspokkaritbtn);
        scifijafantasiapokkaritbtn = findViewById(R.id.scifijafantasiapokkaritbtn);
        tietopokkaritbtn = findViewById(R.id.tietopokkaritbtn);
        /**
         * Englanninkieliset kirjat nappien objektien haku
         */
        crimebtn = findViewById(R.id.crimebtn);
        fictionbnt = findViewById(R.id.fictionbnt);
        nonfictionbtn = findViewById(R.id.nonfictionbtn);
        poetrybtn = findViewById(R.id.poetrybtn);
        engscifibtn = findViewById(R.id.engscifibtn);
        newfictionbtn = findViewById(R.id.newfictionbtn);
        newnonfictionbtn = findViewById(R.id.newnonfictionbtn);
        /**
         * puheen aloitus, aloitus, uudelleen käynnistys ja kielenvaihto napin haku
         * teksti kentien, otsikon ja logon haku
         */
        voiceBtn = findViewById(R.id.voiceBtn);
        text = findViewById(R.id.text);
        text1 = findViewById(R.id.text1);
        converBtn = findViewById(R.id.converBtn);
        resButton = findViewById(R.id.resButton);
        shlang = findViewById(R.id.shlang);
        //gif = findViewById(R.id.gif);
        otsikko = findViewById(R.id.otsikko);
        logo = findViewById(R.id.logo);
        /**
         * Yläkategoriat napit
         */
        kaunobtn = findViewById(R.id.kaunobtn);
        tietogabtn = findViewById(R.id.tietogabtn);
        lapsetbtn = findViewById(R.id.lapsetbtn);
        pokkarbtn = findViewById(R.id.pokkarbtn);
        sarjabtn = findViewById(R.id.sarjabtn);
        rikosbtn = findViewById(R.id.rikosbtn);
        lehdetbtn = findViewById(R.id.lehdetbtn);
        englanbtn = findViewById(R.id.englanbtn);
        svenFranDeutsbtn = findViewById(R.id.svenFranDeutsbtn);
        elokuvatbtn = findViewById(R.id.elokuvatbtn);
        ajankohtaistabtn = findViewById(R.id.ajankohtaistabtn);
        paperituotteetbtn = findViewById(R.id.paperituotteetbtn);
        /**
         * paluu nappi yläkategorioihin
         */
        ylakate = findViewById(R.id.ylakate);
        /**
         * Kielen vaihto popup actionbar
         */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        /**
         * Luodaan uusi TextToSpeech kutsuttaessa joka tuottaa valitun stringin puheeksi.
         */
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            /**
             * Toiminnalla tulee olla valittu kieli mitä halutaan tuottaa ja tähän käytetään metodia loadLocale jotta tuotettu kieli on sama mikä on valittu sovelluksen käyttökieleksi.
             */
            @Override
            public void onInit(int i) {

                if (i != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.forLanguageTag(loadLocale()));
                }
            }
        });
        /**
         * Kaikille napeille luodaan listenerit sekä toiminta mitä tapahtuu jos niitä painaa.
         * shlang on nappi jolla vaihdetaan kieltä. Ja se hakee showLanguageDialog metodin.
         */
        shlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLanguageDialog();
            }
        });
        /**
         * Aloitus nappula joka hakee aloita metodin. Sekä pysäyttää nukkumistilassa pyörivän timerin.
         */
        converBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                aloita();

            }
        });
        /**
         * Nappi jolla sovellus käynnistetään uudelleen. Hakee restart metodin.
         */
        resButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();
            }
        });
        /**
         * voiceBtn on näkymätön imageButton joka on hahmo1 päällä. Tällä aloitetaan puheenkuuntelu hakemalla speechRecognizer.
         */
        voiceBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timer.cancel();
                lopetus();
                textToSpeech.stop();
                speechRecognizer.startListening(speechRecognizerIntent);
            }
        });
        /**
        * Yläkategoria napit joilla on alaategorioita avaavat napit siihen kuuluvaan alagategoriaan ja piilottavat kaikki yläkategorian napit.
         * Jokaisesa tuotetaan myös puhetta joka kertoo minkä yläkategorian on valinnut ja figuuri muutetaan puhuvaksi animaatioksi.
         * Handlerin avulla toteutetaan puhumisen jälkeinen figuurin takaisin muuttaminen perus kuvakkeeseen.
         *
         * Kaikki yläketegoriat joilla on alaketegorioita ovat rakenteeltaan samanlaisia muuten kuin id muutoksilta.
         * Tarkempi kuvaus komentointina löytyy vain "kaunobtn" napilta.
         *
         * Yläkatekoriat joilla ei ole alakategorioita muuttavat kyseisen kategorian booleanin true:ksi.
         * Nappi hakee vertaa metodin jossa tuotetaan toiminta valitun kategorian mukaan.
         *
         * Kaikki yläkategoriat joilla ei ole alakategoriaa ovat rakenteeltaan samanlaisia muuten kuin id muutoksilta
         * Tarkempi kuvaus kommentoituna löytyy vain "paperituotteetbtn" napilta.
        */
        //Yläkategorioiden nappien toiminnot
        kaunobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * nappeja painamalla haetaan lopetus metodi jotta sovellus tajuaa että sitä käytetään.
                 */
                lopetus();
                /**
                 * Nappia painamalla lopetetaan kuunteleminen koska käyttö tapa on muuttunut napeilla ohjaamiseksi.
                 */
                speechRecognizer.stopListening();
                /**
                 * Muutetaan hahmo1 figuuri gif animaatioksi puhuessa.
                 */
                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
                /**
                 * Tuotetaan puhe textToSpeech toiminnon avulla.
                 */
                textToSpeech.speak(getString(R.string.kaunospeak), TextToSpeech.QUEUE_FLUSH, null);
                /**
                 * Piilotetaan kaikki yläkategoria napit.
                 */
                kaunobtn.setVisibility(View.INVISIBLE);
                tietogabtn.setVisibility(View.INVISIBLE);
                lapsetbtn.setVisibility(View.INVISIBLE);
                pokkarbtn.setVisibility(View.INVISIBLE);
                sarjabtn.setVisibility(View.INVISIBLE);
                rikosbtn.setVisibility(View.INVISIBLE);
                lehdetbtn.setVisibility(View.INVISIBLE);
                englanbtn.setVisibility(View.INVISIBLE);
                svenFranDeutsbtn.setVisibility(View.INVISIBLE);
                elokuvatbtn.setVisibility(View.INVISIBLE);
                ajankohtaistabtn.setVisibility(View.INVISIBLE);
                paperituotteetbtn.setVisibility(View.INVISIBLE);
                /**
                 * Avataan kaikki alakategoria napit käyttöön kaunokirjallisuuden alta.
                 */
                fantasibtn.setVisibility(View.VISIBLE);
                uudetrikosbtn.setVisibility(View.VISIBLE);
                runobtn.setVisibility(View.VISIBLE);
                rikojanbtn.setVisibility(View.VISIBLE);
                baabebtn.setVisibility(View.VISIBLE);
                antikvaarisetbtn.setVisibility(View.VISIBLE);
                //esseebtn.setVisibility(View.VISIBLE);
                kaunokibtn.setVisibility(View.VISIBLE);
                keltainbtn.setVisibility(View.VISIBLE);
                uudetkaunobtn.setVisibility(View.VISIBLE);
                klassikotbtn.setVisibility(View.VISIBLE);
                otavabtn.setVisibility(View.VISIBLE);
                aanikibtn.setVisibility(View.VISIBLE);
                /**
                 * Avataan takaisin nappi yläkategorioihin josta voi palata taaksepäin valitsemaan uuden yläkategorian.
                 */
                ylakate.setVisibility(View.VISIBLE);
                /**
                 * Luodaan Hadler postDelayd joka muuttaa hahmo1 figuurin puhuvasta gif animaatiosta takaisin perus kuvakeeksi halutun ajan jälkeen. Tarkoitus osua puheen loppumisen kohdalle.
                 */
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600); /** Toiminnan ajoittamisen aika millisekuntteina */
            }
        });
        tietogabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
                textToSpeech.speak(getString(R.string.tietospeak), TextToSpeech.QUEUE_FLUSH, null);
                //Poistetaan pääkategotia napit käytöstä.
                kaunobtn.setVisibility(View.INVISIBLE);
                tietogabtn.setVisibility(View.INVISIBLE);
                lapsetbtn.setVisibility(View.INVISIBLE);
                pokkarbtn.setVisibility(View.INVISIBLE);
                sarjabtn.setVisibility(View.INVISIBLE);
                rikosbtn.setVisibility(View.INVISIBLE);
                lehdetbtn.setVisibility(View.INVISIBLE);
                englanbtn.setVisibility(View.INVISIBLE);
                svenFranDeutsbtn.setVisibility(View.INVISIBLE);
                elokuvatbtn.setVisibility(View.INVISIBLE);
                ajankohtaistabtn.setVisibility(View.INVISIBLE);
                paperituotteetbtn.setVisibility(View.INVISIBLE);

                //Avataan alakategorianapit käyttöön.
                tietobtn.setVisibility(View.VISIBLE);
                ukravenabtn.setVisibility(View.VISIBLE);
                eloteatbtn.setVisibility(View.VISIBLE);
                elamankbtn.setVisibility(View.VISIBLE);
                antiikkikirjallisuusbtn.setVisibility(View.VISIBLE);
                holokaustibtn.setVisibility(View.VISIBLE);
                ilmastonmuutosbtn.setVisibility(View.VISIBLE);
                filobtn.setVisibility(View.VISIBLE);
                histbtn.setVisibility(View.VISIBLE);
                musiibtn.setVisibility(View.VISIBLE);
                politbtn.setVisibility(View.VISIBLE);
                sanakbtn.setVisibility(View.VISIBLE);
                kieletbtn.setVisibility(View.VISIBLE);
                kasityojarakentaminenbtn.setVisibility(View.VISIBLE);
                luonnontieteetbtn.setVisibility(View.VISIBLE);
                luontokasvitelaimetbtn.setVisibility(View.VISIBLE);
                maatjakulttuuribtn.setVisibility(View.VISIBLE);
                matkailubtn.setVisibility(View.VISIBLE);
                puutarhabtn.setVisibility(View.VISIBLE);
                ruokabtn.setVisibility(View.VISIBLE);
                taidejadesignbtn.setVisibility(View.VISIBLE);
                terveysbtn.setVisibility(View.VISIBLE);
                tieteellinenkirjoittaminenbtn.setVisibility(View.VISIBLE);
                urheilubtn.setVisibility(View.VISIBLE);
                uskonnotbtn.setVisibility(View.VISIBLE);

                ylakate.setVisibility(View.VISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        });
        lapsetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
                textToSpeech.speak(getString(R.string.lastenkatepuhe), TextToSpeech.QUEUE_FLUSH, null);
                //Poistetaan pääkategotia napit käytöstä.
                kaunobtn.setVisibility(View.INVISIBLE);
                tietogabtn.setVisibility(View.INVISIBLE);
                lapsetbtn.setVisibility(View.INVISIBLE);
                pokkarbtn.setVisibility(View.INVISIBLE);
                sarjabtn.setVisibility(View.INVISIBLE);
                rikosbtn.setVisibility(View.INVISIBLE);
                lehdetbtn.setVisibility(View.INVISIBLE);
                englanbtn.setVisibility(View.INVISIBLE);
                svenFranDeutsbtn.setVisibility(View.INVISIBLE);
                elokuvatbtn.setVisibility(View.INVISIBLE);
                ajankohtaistabtn.setVisibility(View.INVISIBLE);
                paperituotteetbtn.setVisibility(View.INVISIBLE);

                lastenromaanibtn.setVisibility(View.VISIBLE);
                kidsbtn.setVisibility(View.VISIBLE);
                lastelobtn.setVisibility(View.VISIBLE);
                lastenbtn.setVisibility(View.VISIBLE);
                lasttietbtn.setVisibility(View.VISIBLE);
                lastenkuvakirjabtn.setVisibility(View.VISIBLE);
                ruotsilastenbtn.setVisibility(View.VISIBLE);
                vauvojenkirjabtn.setVisibility(View.VISIBLE);


                nuoretbtn.setVisibility(View.VISIBLE);


                lastpuuhbtn.setVisibility(View.VISIBLE);


                ylakate.setVisibility(View.VISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        });
        pokkarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
                textToSpeech.speak(getString(R.string.pokkaritpuhe), TextToSpeech.QUEUE_FLUSH, null);
                //Poistetaan pääkategotia napit käytöstä.
                kaunobtn.setVisibility(View.INVISIBLE);
                tietogabtn.setVisibility(View.INVISIBLE);
                lapsetbtn.setVisibility(View.INVISIBLE);
                pokkarbtn.setVisibility(View.INVISIBLE);
                sarjabtn.setVisibility(View.INVISIBLE);
                rikosbtn.setVisibility(View.INVISIBLE);
                lehdetbtn.setVisibility(View.INVISIBLE);
                englanbtn.setVisibility(View.INVISIBLE);
                svenFranDeutsbtn.setVisibility(View.INVISIBLE);
                elokuvatbtn.setVisibility(View.INVISIBLE);
                ajankohtaistabtn.setVisibility(View.INVISIBLE);
                paperituotteetbtn.setVisibility(View.INVISIBLE);

                kaunopokkaritbtn.setVisibility(View.VISIBLE);
                rikosjajannityspokkaritbtn.setVisibility(View.VISIBLE);
                scifijafantasiapokkaritbtn.setVisibility(View.VISIBLE);
                tietopokkaritbtn.setVisibility(View.VISIBLE);

                ylakate.setVisibility(View.VISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
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
        rikosbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
                textToSpeech.speak(getString(R.string.rikospuhe), TextToSpeech.QUEUE_FLUSH, null);
                //Poistetaan pääkategotia napit käytöstä.
                kaunobtn.setVisibility(View.INVISIBLE);
                tietogabtn.setVisibility(View.INVISIBLE);
                lapsetbtn.setVisibility(View.INVISIBLE);
                pokkarbtn.setVisibility(View.INVISIBLE);
                sarjabtn.setVisibility(View.INVISIBLE);
                rikosbtn.setVisibility(View.INVISIBLE);
                lehdetbtn.setVisibility(View.INVISIBLE);
                englanbtn.setVisibility(View.INVISIBLE);
                svenFranDeutsbtn.setVisibility(View.INVISIBLE);
                elokuvatbtn.setVisibility(View.INVISIBLE);
                ajankohtaistabtn.setVisibility(View.INVISIBLE);
                paperituotteetbtn.setVisibility(View.INVISIBLE);

                //Avataan alakategorianapit käyttöön.
                crimeNovEngbtn.setVisibility(View.VISIBLE);
                rikosuudetbtn.setVisibility(View.VISIBLE);
                rikospokkabtn.setVisibility(View.VISIBLE);
                rikosromaanibtn.setVisibility(View.VISIBLE);
                truecrimebtn.setVisibility(View.VISIBLE);


                ylakate.setVisibility(View.VISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo1.setImageResource(R.drawable.kuvarobo);
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
                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
                textToSpeech.speak(getString(R.string.englanpuhe), TextToSpeech.QUEUE_FLUSH, null);
                //Poistetaan pääkategotia napit käytöstä.
                kaunobtn.setVisibility(View.INVISIBLE);
                tietogabtn.setVisibility(View.INVISIBLE);
                lapsetbtn.setVisibility(View.INVISIBLE);
                pokkarbtn.setVisibility(View.INVISIBLE);
                sarjabtn.setVisibility(View.INVISIBLE);
                rikosbtn.setVisibility(View.INVISIBLE);
                lehdetbtn.setVisibility(View.INVISIBLE);
                englanbtn.setVisibility(View.INVISIBLE);
                svenFranDeutsbtn.setVisibility(View.INVISIBLE);
                elokuvatbtn.setVisibility(View.INVISIBLE);
                ajankohtaistabtn.setVisibility(View.INVISIBLE);
                paperituotteetbtn.setVisibility(View.INVISIBLE);

                //Avataan alakategorianapit käyttöön.
                crimebtn.setVisibility(View.VISIBLE);
                fictionbnt.setVisibility(View.VISIBLE);
                nonfictionbtn.setVisibility(View.VISIBLE);
                poetrybtn.setVisibility(View.VISIBLE);
                engscifibtn.setVisibility(View.VISIBLE);
                newfictionbtn.setVisibility(View.VISIBLE);
                newnonfictionbtn.setVisibility(View.VISIBLE);

                ylakate.setVisibility(View.VISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        });
        svenFranDeutsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
                textToSpeech.speak(getString(R.string.svenFranDeutspuhe), TextToSpeech.QUEUE_FLUSH, null);
                //Poistetaan pääkategotia napit käytöstä.
                kaunobtn.setVisibility(View.INVISIBLE);
                tietogabtn.setVisibility(View.INVISIBLE);
                lapsetbtn.setVisibility(View.INVISIBLE);
                pokkarbtn.setVisibility(View.INVISIBLE);
                sarjabtn.setVisibility(View.INVISIBLE);
                rikosbtn.setVisibility(View.INVISIBLE);
                lehdetbtn.setVisibility(View.INVISIBLE);
                englanbtn.setVisibility(View.INVISIBLE);
                svenFranDeutsbtn.setVisibility(View.INVISIBLE);
                elokuvatbtn.setVisibility(View.INVISIBLE);
                ajankohtaistabtn.setVisibility(View.INVISIBLE);
                paperituotteetbtn.setVisibility(View.INVISIBLE);

                //Avataan alakategorianapit käyttöön.
                ruotsinkielibtn.setVisibility(View.VISIBLE);
                ranskankielibtn.setVisibility(View.VISIBLE);
                saksankielibtn.setVisibility(View.VISIBLE);

                ylakate.setVisibility(View.VISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        });
        elokuvatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                elokuvat = true;
                vertaa();
            }
        });
        ajankohtaistabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                ajankohtaista = true;
                vertaa();
            }
        });
        paperituotteetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Haetaan lopetus metodi jotta sovellus tietää että sitä käytetään.
                 */
                lopetus();
                /**
                 * Lopetetaan kuunteleminen koska sovelluksen ohjaaminen on toteutettu napeilla.
                 */
                speechRecognizer.stopListening();
                /**
                 * Muutetaan paperituotteet boolean true:ksi jotta voidaan toteuttaa sen toiminta.
                 */
                paperituotteet = true;
                /**
                 * Haetaan vertaa metodi jossa toiminta toteutetaan.
                 */
                vertaa();
            }
        });
        /**
         * Alakategoria nappulat muuttavat kyseisen kategorian booleanin true:ksi.
         * Nappi hakee vertaa metodia jossa toteutetaan toiminto
         *
         * Kaikki alakategoria napit ovat rakeneeltaan samanlaisia muuten kuin id muutoksilta.
         * tarkempi kuvaus kommentoituna löytyy vain "ruotsinkielibtn" napilta.
         *
         * Ruotsi, Ranska ja Saksa napit
         *Ruotsinkieli nappi
         */
        ruotsinkielibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Haetaan lopetus metodi jotta sovellus tietää että sitä käytetään.
                 */
                lopetus();
                /**
                 * Lopetetaan kuunteleminen koska sovelluksen ohjaaminen on toteutettu napeilla.
                 */
                speechRecognizer.stopListening();
                /**
                 * Muutetaan ruotsinkieli boolean true:ksi jotta voidaan toteuttaa sen toiminta.
                 */
                ruotsinkieli = true;
                /**
                 * Haetaan vertaa metodi jossa toiminta toteutetaan.
                 */
                vertaa();
            }
        });
        /**
         * Ranskankieli nappi
         */
        ranskankielibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                ranskankieli = true;
                vertaa();
            }
        });
        /**
         * Saksankieli nappi
         */
        saksankielibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                saksankieli = true;
                vertaa();
            }
        });
        /**
        * Kaunokirjallisuus napit.
         * Baabel nappi
        */
        baabebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                baabel = true;
                vertaa();
            }
        });
        /**
         * Antikvaariset nappi
         */
        antikvaarisetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                antikvaariset = true;
                vertaa();
            }
        });
        /**
         * Esseet nappi (ei käytössä ja siksi kommenti viivojen sisällä
         */
        /*esseebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                essee = true;
                vertaa();
            }
        });*/
        /**
         * Kaunokirjallisuus nappi
         */
        kaunokibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                kaunokirjallisuus = true;
                vertaa();
            }
        });
        /**
         * Keltainen kirjasto nappi
         */
        keltainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                keltainen = true;
                vertaa();
            }
        });
        /**
         * Uudet kaunokirjallisuus nappi
         */
        uudetkaunobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                uudetkaunokirjallisuus = true;
                vertaa();
            }
        });
        /**
         * Klassikot kaunokirjallisuus nappi
         */
        klassikotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                klassikot = true;
                vertaa();
            }
        });
        /**
         * Otavan kirjaston nappi
         */
        otavabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                otava = true;
                vertaa();
            }
        });
        /**
         * Äänikirja nappi
         */
        aanikibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                aanikirja = true;
                vertaa();
            }
        });
        /**
         * Scifi, Fantasia ja Kauhu nappi
         */
        fantasibtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                lopetus();
                speechRecognizer.stopListening();
                fantasia = true;
                vertaa();
            }
        });
        /**
         * Runo nappi
         */
        runobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                runo = true;
                vertaa();
            }
        });
        /**
         * Rikos ja jännitys nappi
         */
        rikojanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                rikojanitus = true;
                vertaa();
            }
        });
        /**
         * uudet rikoskirjat nappi
         */
        uudetrikosbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lopetus();
                speechRecognizer.stopListening();
                RikoskirjaUutuudet = true;
                vertaa();
            }
        });
        /**
        * Tietokirjallisuuden napit
         * Venäjä nappi
        */
        ukravenabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                ukrainajavenaja = true;
                vertaa();
            }
        });
        /**
         * Elokuva ja teatteri nappi
         */
        eloteatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                elokuvajateatteri = true;
                vertaa();
            }
        });
        /**
         * Elämänkerrat nappi
         */
        elamankbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                elamankerta = true;
                vertaa();
            }
        });
        /**
         * Antiikkikirjallisuus nappi
         */
        antiikkikirjallisuusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                antiikkikirjallisuus = true;
                vertaa();
            }
        });
        /**
         * Holokausti nappi
         */
        holokaustibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                holokausti = true;
                vertaa();
            }
        });
        /**
         * Ilmastonmuutos nappi
         */
        ilmastonmuutosbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                ilmastonmuutos = true;
                vertaa();
            }
        });
        /**
         * Filosofia nappi
         */
        filobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                filosofia = true;
                vertaa();
            }
        });
        /**
         * Historia nappi
         */
        histbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                historia = true;
                vertaa();
            }
        });
        /**
         * Musiikki nappi
         */
        musiibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                musiikki = true;
                vertaa();
            }
        });
        /**
         * Politiikka nappi
         */
        politbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                politiikka = true;
                vertaa();
            }
        });
        /**
         * Sanakirja nappi
         */
        sanakbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                sanakirja = true;
                vertaa();
            }
        });
        /**
         * Tietokirja nappi
         */
        tietobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lopetus();
                speechRecognizer.stopListening();
                tiede = true;
                vertaa();


            }
        });
        /**
         * Kielet nappi
         */
        kieletbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                kielet = true;
                vertaa();
            }
        });
        /**
         * Käsityö ja rakentaminen nappi
         */
        kasityojarakentaminenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                kasityojarakentaminen = true;
                vertaa();
            }
        });
        /**
         * Luonnontieteet nappi
         */
        luonnontieteetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                luonnontieteet = true;
                vertaa();
            }
        });
        /**
         * Luonto, kasvit ja eläimet nappi
         */
        luontokasvitelaimetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                luontokasvitelaimet = true;
                vertaa();
            }
        });
        /**
         * Maat ja kulttuurit nappi
         */
        maatjakulttuuribtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                maatjakulttuuri = true;
                vertaa();
            }
        });
        /**
         *
         * Matkailu nappi
         */
        matkailubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                matkailu = true;
                vertaa();
            }
        });
        /**
         * Puutarha nappi
         */
        puutarhabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                puutarha = true;
                vertaa();
            }
        });
        /**
         * Ruoka nappi
         */
        ruokabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                ruoka = true;
                vertaa();
            }
        });
        /**
         * Taide ja design nappi
         */
        taidejadesignbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                taidejadesign = true;
                vertaa();
            }
        });
        /**
         * Terveys nappi
         */
        terveysbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                terveys = true;
                vertaa();
            }
        });
        /**
         * Tieteellinen kirjoittaminen nappi
         */
        tieteellinenkirjoittaminenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                tieteellinenkirjoittaminen = true;
                vertaa();
            }
        });
        /**
         * Urheilu nappi
         */
        urheilubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                urheilu = true;
                vertaa();
            }
        });
        /**
         * Uskonnot nappi
         */
        uskonnotbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                uskonnot = true;
                vertaa();
            }
        });
        /**
         * Lastenkirjat napit
         * Lasten romaanit nappi
         */
        lastenromaanibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                lastenromaani = true;
                vertaa();
            }
        });
        /**
         * Englanninkieliset lastenkirjat nappi
         */
        kidsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                kids = true;
                vertaa();
            }
        });
        /**
         * Lasten elokuvat nappi
         */
        lastelobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                lastenelokuvat = true;
                vertaa();
            }
        });
        /**
         * Lasten kirjat nappi
         */
        lastenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                lastenkirjat = true;
                vertaa();
            }
        });
        /**
         * Lasten tietokirjat nappi
         */
        lasttietbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                lastentietikirjat = true;
                vertaa();
            }
        });
        /**
         * Lastenkuvakirja nappi
         */
        lastenkuvakirjabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                lastenkuvakirja = true;
                vertaa();
            }
        });
        /**
         * Ruotsinkieliset lastenkirjat nappi
         */
        ruotsilastenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                ruotsinkielisetlastenkirjat = true;
                vertaa();
            }
        });
        /**
         * Vauvojen ja taaperoiden kirjat nappi
         */
        vauvojenkirjabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                vauvojenkirja = true;
                vertaa();
            }
        });
        /**
         * Nuorten kirjat nappi
         */
        nuoretbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                nuoret = true;
                vertaa();
            }
        });
        /**
         * Puuhaa lapsille nappi
         */
        lastpuuhbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                puuhaalapsille = true;
                vertaa();
            }
        });
        /**
         * Rikoskirjallisuus napit
         * Englannin kieliset romaanit nappi
         */
        crimeNovEngbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                CrimeNovelsInEnglish = true;
                vertaa();
            }
        });
        /**
         * Rikoskirjallisuus uutuudet nappi
         */
        rikosuudetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                RikoskirjaUutuudet = true;
                vertaa();
            }
        });
        /**
         * Rikos pokkarit nappi
         */
        rikospokkabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                RikosPokkarit = true;
                vertaa();
            }
        });
        /**
         * Rikos romaanit nappi
         */
        rikosromaanibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                Rikosromaanit = true;
                vertaa();
            }
        });
        /**
         * True Crime nappi
         */
        truecrimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                TrueCrime = true;
                vertaa();
            }
        });
        /**
         * Pokkarit napit
         * kaunokirjallisuus pokkarit nappi
         */
        kaunopokkaritbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                kaunopokkarit = true;
                vertaa();
            }
        });
        /**
         * Rikos ja jännitys pokkarit nappi
         */
        rikosjajannityspokkaritbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                RikosPokkarit = true;
                vertaa();
            }
        });
        /**
         * Scifi ja fantasia pokkarit nappi
         */
        scifijafantasiapokkaritbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                scifijafantasiapokkarit = true;
                vertaa();
            }
        });
        /**
         * Tietopokkarit nappi
         */
        tietopokkaritbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                tietopokkarit = true;
                vertaa();
            }
        });
        /**
         * Englanninkieliset kirjat napit
         * Crime nappi
         */
        crimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                crime = true;
                vertaa();
            }
        });
        /**
         * Fiction nappi
         */
        fictionbnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                fiction = true;
                vertaa();
            }
        });
        /**
         * Non-Fiction nappi
         */
        nonfictionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                nonfiction = true;
                vertaa();
            }
        });
        /**
         * Poetry nappi
         */
        poetrybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                poetry = true;
                vertaa();
            }
        });
        /**
         * Englanninkielinen Scifi nappi
         */
        engscifibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                engscifi = true;
                vertaa();
            }
        });
        /**
         * Uudet Fiction nappi
         */
        newfictionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                newfiction = true;
                vertaa();
            }
        });
        /**
         * Uudet Non-fiction nappi
         */
        newnonfictionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lopetus();
                speechRecognizer.stopListening();
                newnonfiction = true;
                vertaa();
            }
        });
        /**
         * ylakate nappi joka palautta yläkategoriat takaisin näkyviin
         */
        ylakate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Haetaan lopetus metodi jotta sovellus tietää että sitä käytetään
                 */
                lopetus();
                /**
                 * Lopetetaan kuunteleminen koska sovellusta käytetään napeilla.
                 */
                speechRecognizer.stopListening();
                /**
                 * Lopetetaan aikaisempi puhe.
                 */
                textToSpeech.stop();
                /**
                 * Haetaan textToSpeech ja siihen string jossa kerrotaan että voidaan valita haluama yläkategoria.
                 */
                textToSpeech.speak(getString(R.string.ylakatepuhe), TextToSpeech.QUEUE_FLUSH, null);
                /**
                 * Avataan kaikki yläkategoria napit näkyville
                 */
                kaunobtn.setVisibility(View.VISIBLE);
                tietogabtn.setVisibility(View.VISIBLE);
                lapsetbtn.setVisibility(View.VISIBLE);
                pokkarbtn.setVisibility(View.VISIBLE);
                sarjabtn.setVisibility(View.VISIBLE);
                rikosbtn.setVisibility(View.VISIBLE);
                lehdetbtn.setVisibility(View.VISIBLE);
                englanbtn.setVisibility(View.VISIBLE);
                svenFranDeutsbtn.setVisibility(View.VISIBLE);
                elokuvatbtn.setVisibility(View.VISIBLE);
                ajankohtaistabtn.setVisibility(View.VISIBLE);
                paperituotteetbtn.setVisibility(View.VISIBLE);
                /**
                 * Piilotetaan kaikki alakategoria napit näkyvistä
                 */
                ruotsinkielibtn.setVisibility(View.INVISIBLE);
                ranskankielibtn.setVisibility(View.INVISIBLE);
                saksankielibtn.setVisibility(View.INVISIBLE);
                fantasibtn.setVisibility(View.INVISIBLE);
                uudetrikosbtn.setVisibility(View.INVISIBLE);
                runobtn.setVisibility(View.INVISIBLE);
                rikojanbtn.setVisibility(View.INVISIBLE);
                baabebtn.setVisibility(View.INVISIBLE);
                antikvaarisetbtn.setVisibility(View.INVISIBLE);
                //esseebtn.setVisibility(View.INVISIBLE);
                kaunokibtn.setVisibility(View.INVISIBLE);
                keltainbtn.setVisibility(View.INVISIBLE);
                uudetkaunobtn.setVisibility(View.INVISIBLE);
                klassikotbtn.setVisibility(View.INVISIBLE);
                otavabtn.setVisibility(View.INVISIBLE);
                aanikibtn.setVisibility(View.INVISIBLE);
                tietobtn.setVisibility(View.INVISIBLE);
                ukravenabtn.setVisibility(View.INVISIBLE);
                eloteatbtn.setVisibility(View.INVISIBLE);
                elamankbtn.setVisibility(View.INVISIBLE);
                antiikkikirjallisuusbtn.setVisibility(View.INVISIBLE);
                holokaustibtn.setVisibility(View.INVISIBLE);
                ilmastonmuutosbtn.setVisibility(View.INVISIBLE);
                filobtn.setVisibility(View.INVISIBLE);
                histbtn.setVisibility(View.INVISIBLE);
                musiibtn.setVisibility(View.INVISIBLE);
                politbtn.setVisibility(View.INVISIBLE);
                sanakbtn.setVisibility(View.INVISIBLE);
                kieletbtn.setVisibility(View.INVISIBLE);
                kasityojarakentaminenbtn.setVisibility(View.INVISIBLE);
                luonnontieteetbtn.setVisibility(View.INVISIBLE);
                luontokasvitelaimetbtn.setVisibility(View.INVISIBLE);
                maatjakulttuuribtn.setVisibility(View.INVISIBLE);
                matkailubtn.setVisibility(View.INVISIBLE);
                puutarhabtn.setVisibility(View.INVISIBLE);
                ruokabtn.setVisibility(View.INVISIBLE);
                taidejadesignbtn.setVisibility(View.INVISIBLE);
                terveysbtn.setVisibility(View.INVISIBLE);
                tieteellinenkirjoittaminenbtn .setVisibility(View.INVISIBLE);
                urheilubtn.setVisibility(View.INVISIBLE);
                uskonnotbtn.setVisibility(View.INVISIBLE);
                lastenromaanibtn.setVisibility(View.INVISIBLE);
                kidsbtn.setVisibility(View.INVISIBLE);
                lastelobtn.setVisibility(View.INVISIBLE);
                lastenbtn.setVisibility(View.INVISIBLE);
                lasttietbtn.setVisibility(View.INVISIBLE);
                lastenkuvakirjabtn.setVisibility(View.INVISIBLE);
                ruotsilastenbtn.setVisibility(View.INVISIBLE);
                vauvojenkirjabtn.setVisibility(View.INVISIBLE);
                nuoretbtn.setVisibility(View.INVISIBLE);
                lastpuuhbtn.setVisibility(View.INVISIBLE);
                crimeNovEngbtn.setVisibility(View.INVISIBLE);
                rikosuudetbtn.setVisibility(View.INVISIBLE);
                rikospokkabtn.setVisibility(View.INVISIBLE);
                rikosromaanibtn.setVisibility(View.INVISIBLE);
                truecrimebtn.setVisibility(View.INVISIBLE);
                kaunopokkaritbtn.setVisibility(View.INVISIBLE);
                rikosjajannityspokkaritbtn.setVisibility(View.INVISIBLE);
                scifijafantasiapokkaritbtn.setVisibility(View.INVISIBLE);
                tietopokkaritbtn.setVisibility(View.INVISIBLE);
                crimebtn.setVisibility(View.INVISIBLE);
                fictionbnt.setVisibility(View.INVISIBLE);
                nonfictionbtn.setVisibility(View.INVISIBLE);
                poetrybtn.setVisibility(View.INVISIBLE);
                engscifibtn.setVisibility(View.INVISIBLE);
                newfictionbtn.setVisibility(View.INVISIBLE);
                newnonfictionbtn.setVisibility(View.INVISIBLE);
                /**
                 * Piilotetaan ylakate nappula koska ollaan jo yläkategoria valikossa.
                 */
                ylakate.setVisibility(View.INVISIBLE);
            }
        });
        /**
         * Luodaan touchListener mainlayoutille joka on koko näkymä
         * Tällä tarkoitus o hakea lopetus metodia joka kerta kun näyttöön koskee ja estää tällöin sovelluksen uudelleen käynnistymisen.
         */
        mylayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                lopetus();
                return false;
            }
        });
        }
    /**
     * lopetus metodi on metodi jossa on timeri joka laskee milloin sovellus käynnistyy uudelleen.
     * Keskeisessä osassa on INT i, joka lisääntyy timerissa ja tietyn arvon saavuttaessa käynnistää sovelluksen uudelleen.
     */
    public void lopetus(){
        /**
         * Sovelluksen ollessa aktivoitu i ei tule olemaan 0 ja tällöin tämä toteutuu.
         * Metodia kutsuttaessa i ei ole nolla muutetaan i arvoon 1 jotta se on mahdollisimman kaukana uudelleen käynnistämisestä.
         * i:tä ei muuteta 0 arvoon koska se käynnistäisi toisen samanlaisen timerin samanaikaisesti.
         */
        if (i != 0) {
            i = 1;
            Log.e(String.valueOf(LOG_TAG), "Lopetus on valittu uudestaan ");
        }
        /**
         * Ensimmäisellä kerralla kun sovellus aktivoidaan kutsutaan tätä metodia ja silloin i on 0 joten tämä kohta tapahtuu.
         * Tässä aloitetaan timeri joka lisää joka kierroksella i:n arvoon yhden arvon lisää.
         */
        if (i == 0) {
            timerlopetus.schedule(new TimerTask() {
                @Override
                public void run() {
                    /**
                     * Timerin sisällu tulee olla "runOnUiThread metodi jotta se voi hakea restart metodin.
                     */
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run () {
                            i++;
                            Log.e(String.valueOf(LOG_TAG), "I on tällähetkellä " + i);
                            /**
                             * Jos i saavuttaa arvon 15 käynnistetään sovellus uudelleen koska kukaan ei ole sitä hetkeen käyttänyt.
                             * Arvoa voi muuttaa jos haluaa nopeuttaa tai lisätä käynnistämisen kuluvaa aikaa.
                             * Tällähetkellä jokainen i yksi arvo tarkoittaa 10 sekunttia.
                             */
                            if (i > 15) {
                                restart();
                            }
                        }
                    });
                }

            }, 0, 10000); /** Tässö määritellään yhden timerin kierroksen kesto millisekuntteina. Tällähetkellä 10 sekunttia. Halutessa voi lisätä tai vähentää joka vakuttaa sovelluksen uudelleen käynnistämisen kaikaan */
        }
    }

    /**
     * takePicture metodi on metodi joka ottaa valokuvia. Tätä haetaan sovelluksen ollessa nukkumis tilassa.
     */
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
                        /**
                         * kuvan saamisen jälkeen kuva tallennetaan bitmappiin ja sen jälkeen sen kokoa pienennetään suuresti.
                         * Int kuva toteuttaa että joka toinen käsitellään joka toisessa if lausekkeessa tasasen kierron saavutettavakasi.
                         */
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
                        /**
                         * Kuvan käsittelyn jälkeen tulostetaan logcattiin niiden ero hakemalla compareEquivalance metodi jossa kuvia verrataan
                         * Seuraava if lause hakee compareEquivalance metodista mikä oli kuvien eroavaisuus ja valitsee sen mukaan toiminnan.
                         * Kuvissa on joka kerta eroa pitää vaan löytää oikea arvo jota ei saa alittaa. Aktivointi herkyyttä voi lisätä vertailuarvoa nostamalla.
                         * text1 ei tällähetkellä tulosta mitään näytölle sillä se on näkymätön. Tätä on käytetty helpottamaan kehitystä ja näkemään näytöltä heti mikäli jokin muuttuu.
                         */
                        Log.e(String.valueOf(LOG_TAG), "eroavaisuus on " + String.valueOf(compareEquivalance()));
                        if (compareEquivalance() < 0.285){
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
    /**
     * aloita metodi on aktivointi metodi jota haetaan jos kamera vertailussa löytyy eroa tai jos painetaa aloita nappia.
     */
    private void aloita() {
        /**
         * haetaan lopetus metodi jotta sovellus tietää että sitä käytetään
         */
            lopetus();
            aloitettu = true;
        /**
         * Pidempi tapa kuin aikasemmin toistaa puhetta haluamasta stringistä.
         * Ensin hetaan haluttu string muuttujaan x joka sitten tuotetaan puheeksi textToSpeech metodilla.
         */
            String x = getString(R.string.terve);
            textToSpeech.speak(x, TextToSpeech.QUEUE_FLUSH, null);
        /**
         * Piilotetaan aloitus nappi näkyvistä käynnistämisen jälkeen
         * Tallennetaan databaseen aikaleima milloin sovellus on aktivoitu.
         */
            converBtn.setVisibility(View.INVISIBLE);
            DatabaseReference myRef = database.getReference("aloitettu");
            myRef.push().setValue(millisInString);
        /**
         * Vaihdetaan hahmo tilalle puhuva animaatio
         */
        Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo);
        /**
         * Luodaan handleri joka totetuttaa toiminnat alku tervehdyksen jälkeen.
         */
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    /**
                     * Luodaan toinen handler joka muuttaa näytöllä olevan figuurin sijainnin.
                     * Tässä ollut ongelmia ettei siirry mutta näin toiminut
                     */
                    handler2.postAtFrontOfQueue(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                hahmo.setVisibility(View.INVISIBLE);
                                hahmo1.setVisibility(View.VISIBLE);
                                }
                            });
                        }
                    });
                    /**
                     * Tutetaan näkyviin yläkategorian napit.
                     * Tuotetaan näkyviin kartta.
                     */
                    voiceBtn.setVisibility(View.VISIBLE);
                    kaunobtn.setVisibility(View.VISIBLE);
                    tietogabtn.setVisibility(View.VISIBLE);
                    lapsetbtn.setVisibility(View.VISIBLE);
                    pokkarbtn.setVisibility(View.VISIBLE);
                    sarjabtn.setVisibility(View.VISIBLE);
                    rikosbtn.setVisibility(View.VISIBLE);
                    lehdetbtn.setVisibility(View.VISIBLE);
                    englanbtn.setVisibility(View.VISIBLE);
                    svenFranDeutsbtn.setVisibility(View.VISIBLE);
                    elokuvatbtn.setVisibility(View.VISIBLE);
                    ajankohtaistabtn.setVisibility(View.VISIBLE);
                    paperituotteetbtn.setVisibility(View.VISIBLE);
                    kartta.setVisibility(View.VISIBLE);
                    //text1.setVisibility(View.VISIBLE);
                    /**
                     * Aloitetaan automaattisesti puheen kuuntelu joka mahdollistaa ettei tablettiin tarvitse koskea koko käytön aikana.
                     */
                    speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 7000); /** Arvo määrittelee handlerin toiminnan ajoituksen. Tällähetkellä 7 sekunttia joka täsmää alkupuheen kanssa. Joten Handlerin sisöllä oleva toiminta toteutetaan heti alku puheen jälkeen. */
        }
    /**
     * Metodi suorittaa kuvien vertailun.
     * Metodi palauttaa float arvon joka on kuvien eroaviasuus.
     */
     public static float compareEquivalance() {
        /**
        * Mikäli ei ole kuvia palautta metodi 0.
        */
        if (bitmap == null || bitmap2 == null || bitmap.getWidth() != bitmap2.getWidth()
                || bitmap.getHeight() != bitmap2.getHeight()) {
            return 0;
        }
         /**
          * heataan molempien kuvien koko ja pixelit.
          */
        ByteBuffer buffer1 = ByteBuffer.allocate(bitmap.getHeight() * bitmap.getRowBytes());
        bitmap.copyPixelsToBuffer(buffer1);

        ByteBuffer buffer2 = ByteBuffer.allocate(bitmap2.getHeight() * bitmap2.getRowBytes());
        bitmap2.copyPixelsToBuffer(buffer2);

        byte[] array1 = buffer1.array();
        byte[] array2 = buffer2.array();

        int len = array1.length; // array1 and array2 ovat saman pituisia.
        int count = 10;
         /**
          * suoritetaan loop jossa käydään kaikki kuvan pixelit läpi ja verrataan toisen kuvan samaan pixeliin.
          * Jos pixelit vastaavat toisiaan lisätään laskuriin count arvo.
          */
         for (int i = 0; i < len; i++) {
            if (array1[i] == array2[i]) {
                count++;
            }
        }
         /**
          * Palautetaan tulos laskemalla laskuriin kerättyt arvot jaettuna kuvan pituudella.
          */
        return ((float) (count)) / len;
    }

    /**
     * Metodissa avataan kamera käyttöön.
     */
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
    /**
     * TLuodaan listener kameran textureViewille.
     * Täältä haetaan openCamera metodi.
     */
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

    /**
    *Verrataan contain() methodilla avain sana stringeihin kuunneltua puhetta.
    *Mikäli avain sana löytyy puheesta muuttuu sitä vastaava boolean true:ksi.
    *True booleanin alta löytyvä toiminta toteutetaan.
     */
    public void tunnistus() {
        /**
         * Käytetään contains() methodia avain sana stringien läpikäymiseksi.
         * Mikäli löydetään vastaavuus muuttuu kyseisen booleanin arvo true:ksi
         *
         * Keskustelu ja kuinkavoin ovat kokeellisia interaktiivisia avainsanoja keskustelusta.
         */
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
        /**
         * Ruotsi, Ranska ja Saksa tunnistus
         * Ruotsi
         */
        ruotsinkieli0 = word.contains(getString(R.string.ruotsinkieli0));
        ruotsinkieli1 = word.contains(getString(R.string.ruotsinkieli1));
        ruotsinkieli2 = word.contains(getString(R.string.ruotsinkieli2));
        ruotsinkieli3 = word.contains(getString(R.string.ruotsinkieli3));
        ruotsinkieli4 = word.contains(getString(R.string.ruotsinkieli4));
        ruotsinkieli5 = word.contains(getString(R.string.ruotsinkieli5));
        /**
         * Ranska
         */
        ranskankieli0 = word.contains(getString(R.string.ranskankieli0));
        ranskankieli1 = word.contains(getString(R.string.ranskankieli1));
        ranskankieli2 = word.contains(getString(R.string.ranskankieli2));
        ranskankieli3 = word.contains(getString(R.string.ranskankieli3));
        ranskankieli4 = word.contains(getString(R.string.ranskankieli4));
        ranskankieli5 = word.contains(getString(R.string.ranskankieli5));
        /**
        * Saksa
        */
        saksankieli0 = word.contains(getString(R.string.saksankieli0));
        saksankieli1 = word.contains(getString(R.string.saksankieli1));
        saksankieli2 = word.contains(getString(R.string.saksankieli2));
        saksankieli3 = word.contains(getString(R.string.saksankieli3));
        saksankieli4 = word.contains(getString(R.string.saksankieli4));
        saksankieli5 = word.contains(getString(R.string.saksankieli5));
        /**
        * Kaunokirjallisuus tunnistus
         * Baabel
        */
        baabel0 = word.contains(getString(R.string.baabe0));
        baabel1 = word.contains(getString(R.string.baabe1));
        baabel2 = word.contains(getString(R.string.baabe2));
        baabel3 = word.contains(getString(R.string.baabe3));
        baabel4 = word.contains(getString(R.string.baabe4));
        baabel5 = word.contains(getString(R.string.baabe5));
        /**
         * Antikvaariset
         */
        antikvaariset0 = word.contains(getString(R.string.antikvaariset0));
        antikvaariset1 = word.contains(getString(R.string.antikvaariset1));
        antikvaariset2 = word.contains(getString(R.string.antikvaariset2));
        antikvaariset3 = word.contains(getString(R.string.antikvaariset3));
        antikvaariset4 = word.contains(getString(R.string.antikvaariset4));
        antikvaariset5 = word.contains(getString(R.string.antikvaariset5));
        /**
         * Essee
         */
        /*essee0 = word.contains(getString(R.string.essee0));
        essee1 = word.contains(getString(R.string.essee1));
        essee2 = word.contains(getString(R.string.essee2));
        essee3 = word.contains(getString(R.string.essee3));
        essee4 = word.contains(getString(R.string.essee4));
        essee5 = word.contains(getString(R.string.essee5));*/
        /**
         * Kaunokirjallisus
         */
        kauno0 = word.contains(getString(R.string.kauno0));
        kauno1 = word.contains(getString(R.string.kauno1));
        kauno2 = word.contains(getString(R.string.kauno2));
        kauno3 = word.contains(getString(R.string.kauno3));
        kauno4 = word.contains(getString(R.string.kauno4));
        kauno5 = word.contains(getString(R.string.kauno5));
        /**
         * Keltainen kirjasto
         */
        kelta0 = word.contains(getString(R.string.kelta0));
        kelta1 = word.contains(getString(R.string.kelta1));
        kelta2 = word.contains(getString(R.string.kelta2));
        kelta3 = word.contains(getString(R.string.kelta3));
        kelta4 = word.contains(getString(R.string.kelta4));
        kelta5 = word.contains(getString(R.string.kelta5));
        /**
         * Uudet kaunokirjallisuus
         */
        uudetkauno0 = word.contains(getString(R.string.uudetkauno0));
        uudetkauno1 = word.contains(getString(R.string.uudetkauno1));
        uudetkauno2 = word.contains(getString(R.string.uudetkauno2));
        uudetkauno3 = word.contains(getString(R.string.uudetkauno3));
        uudetkauno4 = word.contains(getString(R.string.uudetkauno4));
        uudetkauno5 = word.contains(getString(R.string.uudetkauno5));
        /**
         * Klassikot kaunokirjallisuus
         */
        klassikot0 = word.contains(getString(R.string.klassikot0));
        klassikot1 = word.contains(getString(R.string.klassikot1));
        klassikot2 = word.contains(getString(R.string.klassikot2));
        klassikot3 = word.contains(getString(R.string.klassikot3));
        klassikot4 = word.contains(getString(R.string.klassikot4));
        klassikot5 = word.contains(getString(R.string.klassikot5));
        /**
         * Otava kirjasto
         */
        otav0 = word.contains(getString(R.string.otav0));
        otav1 = word.contains(getString(R.string.otav1));
        otav2 = word.contains(getString(R.string.otav2));
        otav3 = word.contains(getString(R.string.otav3));
        otav4 = word.contains(getString(R.string.otav4));
        otav5 = word.contains(getString(R.string.otav5));
        /**
         * Äänikirjat
         */
        aani0 = word.contains(getString(R.string.aani0));
        aani1 = word.contains(getString(R.string.aani1));
        aani2 = word.contains(getString(R.string.aani2));
        aani3 = word.contains(getString(R.string.aani3));
        aani4 = word.contains(getString(R.string.aani4));
        aani5 = word.contains(getString(R.string.aani5));
        /**
         * Fantasia
         */
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
        /**
         * Runot
         */
        runot0 = MainActivity.word.contains(getString(R.string.runo0));
        runot1 = MainActivity.word.contains(getString(R.string.runo1));
        runot2 = MainActivity.word.contains(getString(R.string.runo2));
        runot3 = MainActivity.word.contains(getString(R.string.runo3));
        runot4 = MainActivity.word.contains(getString(R.string.runo4));
        runot5 = MainActivity.word.contains(getString(R.string.runo5));
        /**
         * Rikos ja jännitys
         */
        rikoja0 = MainActivity.word.contains(getString(R.string.rikojan0));
        rikoja1 = MainActivity.word.contains(getString(R.string.rikojan1));
        rikoja2 = MainActivity.word.contains(getString(R.string.rikojan2));
        rikoja3 = MainActivity.word.contains(getString(R.string.rikojan3));
        /**
         * Kiitos takaa löytyy avainsanat kun käyttäjä kiittää sovellusta
         */
        kiitos1 = word.contains(getString(R.string.kiits1));
        kiito = MainActivity.word.contains(getString(R.string.kiits0));
        oli = MainActivity.word.contains(getString(R.string.kiits1));
        /**
         * Sijainti
         */
        sijainti0 = MainActivity.word.contains(getString(R.string.sijan0));
        sijainti1 = MainActivity.word.contains(getString(R.string.sijan1));
        sijainti2 = MainActivity.word.contains(getString(R.string.sijan2));
        sijainti3 = MainActivity.word.contains(getString(R.string.sijan3));
        /**
         * Tietokirjallisuus tunnistus
         * Venajä
         */
        ukrvena0 = word.contains(getString(R.string.ukrvena0));
        ukrvena1 = word.contains(getString(R.string.ukrvena1));
        ukrvena2 = word.contains(getString(R.string.ukrvena2));
        ukrvena3 = word.contains(getString(R.string.ukrvena3));
        ukrvena4 = word.contains(getString(R.string.ukrvena4));
        ukrvena5 = word.contains(getString(R.string.ukrvena5));
        /**
         * Elokuva ja teatteri
         */
        elokteat0 = word.contains(getString(R.string.elokteat0));
        elokteat1 = word.contains(getString(R.string.elokteat1));
        elokteat2 = word.contains(getString(R.string.elokteat2));
        elokteat3 = word.contains(getString(R.string.elokteat3));
        elokteat4 = word.contains(getString(R.string.elokteat4));
        elokteat5 = word.contains(getString(R.string.elokteat5));
        /**
         * Elämänkerta
         */
        elamank0 = word.contains(getString(R.string.elamank0));
        elamank1 = word.contains(getString(R.string.elamank1));
        elamank2 = word.contains(getString(R.string.elamank2));
        elamank3 = word.contains(getString(R.string.elamank3));
        elamank4 = word.contains(getString(R.string.elamank4));
        elamank5 = word.contains(getString(R.string.elamank5));
        /**
         * Antiikkikirjallisuus
         */
        antiikkikirjallisuus0 = word.contains(getString(R.string.antiikkikirjallisuus0));
        antiikkikirjallisuus1 = word.contains(getString(R.string.antiikkikirjallisuus1));
        antiikkikirjallisuus2 = word.contains(getString(R.string.antiikkikirjallisuus2));
        antiikkikirjallisuus3 = word.contains(getString(R.string.antiikkikirjallisuus3));
        antiikkikirjallisuus4 = word.contains(getString(R.string.antiikkikirjallisuus4));
        antiikkikirjallisuus5 = word.contains(getString(R.string.antiikkikirjallisuus5));
        /**
         * Holokausti
         */
        holokausti0 = word.contains(getString(R.string.holokausti0));
        holokausti1 = word.contains(getString(R.string.holokausti1));
        holokausti2 = word.contains(getString(R.string.holokausti2));
        holokausti3 = word.contains(getString(R.string.holokausti3));
        holokausti4 = word.contains(getString(R.string.holokausti4));
        holokausti5 = word.contains(getString(R.string.holokausti5));
        /**
         * Ilmastonmuutos
         */
        ilmastonmuutos0 = word.contains(getString(R.string.ilmastonmuutos0));
        ilmastonmuutos1 = word.contains(getString(R.string.ilmastonmuutos1));
        ilmastonmuutos2 = word.contains(getString(R.string.ilmastonmuutos2));
        ilmastonmuutos3 = word.contains(getString(R.string.ilmastonmuutos3));
        ilmastonmuutos4 = word.contains(getString(R.string.ilmastonmuutos4));
        ilmastonmuutos5 = word.contains(getString(R.string.ilmastonmuutos5));
        /**
         * Filosofia
         */
        filo0 = word.contains(getString(R.string.filo0));
        filo1 = word.contains(getString(R.string.filo1));
        filo2 = word.contains(getString(R.string.filo2));
        filo3 = word.contains(getString(R.string.filo3));
        filo4 = word.contains(getString(R.string.filo4));
        filo5 = word.contains(getString(R.string.filo5));
        /**
         * Historia
         */
        hist0 = word.contains(getString(R.string.hist0));
        hist1 = word.contains(getString(R.string.hist1));
        hist2 = word.contains(getString(R.string.hist2));
        hist3 = word.contains(getString(R.string.hist3));
        hist4 = word.contains(getString(R.string.hist4));
        hist5 = word.contains(getString(R.string.hist5));
        /**
         * Musiikki
         */
        musii0 = word.contains(getString(R.string.musii0));
        musii1 = word.contains(getString(R.string.musii1));
        musii2 = word.contains(getString(R.string.musii2));
        musii3 = word.contains(getString(R.string.musii3));
        musii4 = word.contains(getString(R.string.musii4));
        musii5 = word.contains(getString(R.string.musii5));
        /**
         * Politiikka
         */
        polit0 = word.contains(getString(R.string.polit0));
        polit1 = word.contains(getString(R.string.polit1));
        polit2 = word.contains(getString(R.string.polit2));
        polit3 = word.contains(getString(R.string.polit3));
        polit4 = word.contains(getString(R.string.polit4));
        polit5 = word.contains(getString(R.string.polit5));
        /**
         * Sanakirjat
         */
        sanak0 = word.contains(getString(R.string.sanak0));
        sanak1 = word.contains(getString(R.string.sanak1));
        sanak2 = word.contains(getString(R.string.sanak2));
        sanak3 = word.contains(getString(R.string.sanak3));
        sanak4 = word.contains(getString(R.string.sanak4));
        sanak5 = word.contains(getString(R.string.sanak5));
        /**
         * Tietokirjat
         */
        tiede0 = MainActivity.word.contains(getString(R.string.tiet0));
        tiede1 = MainActivity.word.contains(getString(R.string.tiet1));
        tiede2 = MainActivity.word.contains(getString(R.string.tiet2));
        tiede3 = MainActivity.word.contains(getString(R.string.tiet3));
        tiede4 = MainActivity.word.contains(getString(R.string.tiet4));
        tiede5 = MainActivity.word.contains(getString(R.string.tiet5));
        tiede6 = MainActivity.word.contains(getString(R.string.tiet6));
        tiede7 = MainActivity.word.contains(getString(R.string.tiet7));
        /**
         * Kielet
         */
        kielet0 = word.contains(getString(R.string.kielet0));
        kielet1 = word.contains(getString(R.string.kielet1));
        kielet2 = word.contains(getString(R.string.kielet2));
        kielet3 = word.contains(getString(R.string.kielet3));
        kielet4 = word.contains(getString(R.string.kielet4));
        kielet5 = word.contains(getString(R.string.kielet5));
        /**
         * Käsityö ja rakentaminen
         */
        kasityojarakentaminen0 = word.contains(getString(R.string.kasityojarakentaminen0));
        kasityojarakentaminen1 = word.contains(getString(R.string.kasityojarakentaminen1));
        kasityojarakentaminen2 = word.contains(getString(R.string.kasityojarakentaminen2));
        kasityojarakentaminen3 = word.contains(getString(R.string.kasityojarakentaminen3));
        kasityojarakentaminen4 = word.contains(getString(R.string.kasityojarakentaminen4));
        kasityojarakentaminen5 = word.contains(getString(R.string.kasityojarakentaminen5));
        /**
         * Luonnontieteet
         */
        luonnontieteet0 = word.contains(getString(R.string.luonnontieteet0));
        luonnontieteet1 = word.contains(getString(R.string.luonnontieteet1));
        luonnontieteet2 = word.contains(getString(R.string.luonnontieteet2));
        luonnontieteet3 = word.contains(getString(R.string.luonnontieteet3));
        luonnontieteet4 = word.contains(getString(R.string.luonnontieteet4));
        luonnontieteet5 = word.contains(getString(R.string.luonnontieteet5));
        /**
         * Luonto, Kasvit ja eläimet
         */
        luontokasvitelaimet0 = word.contains(getString(R.string.luontokasvitelaimet0));
        luontokasvitelaimet1 = word.contains(getString(R.string.luontokasvitelaimet1));
        luontokasvitelaimet2 = word.contains(getString(R.string.luontokasvitelaimet2));
        luontokasvitelaimet3 = word.contains(getString(R.string.luontokasvitelaimet3));
        luontokasvitelaimet4 = word.contains(getString(R.string.luontokasvitelaimet4));
        luontokasvitelaimet5 = word.contains(getString(R.string.luontokasvitelaimet5));
        /**
         * Maat ja kulttuuri
         */
        maatjakulttuuri0 = word.contains(getString(R.string.maatjakulttuuri0));
        maatjakulttuuri1 = word.contains(getString(R.string.maatjakulttuuri1));
        maatjakulttuuri2 = word.contains(getString(R.string.maatjakulttuuri2));
        maatjakulttuuri3 = word.contains(getString(R.string.maatjakulttuuri3));
        maatjakulttuuri4 = word.contains(getString(R.string.maatjakulttuuri4));
        maatjakulttuuri5 = word.contains(getString(R.string.maatjakulttuuri5));
        /**
         * Matkailu
         */
        matkailu0 = word.contains(getString(R.string.matkailu0));
        matkailu1 = word.contains(getString(R.string.matkailu1));
        matkailu2 = word.contains(getString(R.string.matkailu2));
        matkailu3 = word.contains(getString(R.string.matkailu3));
        matkailu4 = word.contains(getString(R.string.matkailu4));
        matkailu5 = word.contains(getString(R.string.matkailu5));
        /**
         * Puutarha
         */
        puutarha0 = word.contains(getString(R.string.puutarha0));
        puutarha1 = word.contains(getString(R.string.puutarha1));
        puutarha2 = word.contains(getString(R.string.puutarha2));
        puutarha3 = word.contains(getString(R.string.puutarha3));
        puutarha4 = word.contains(getString(R.string.puutarha4));
        puutarha5 = word.contains(getString(R.string.puutarha5));
        /**
         * Ruoka
         */
        ruoka0 = word.contains(getString(R.string.ruoka0));
        ruoka1 = word.contains(getString(R.string.ruoka1));
        ruoka2 = word.contains(getString(R.string.ruoka2));
        ruoka3 = word.contains(getString(R.string.ruoka3));
        ruoka4 = word.contains(getString(R.string.ruoka4));
        ruoka5 = word.contains(getString(R.string.ruoka5));
        /**
         * Taide ja Design
         */
        taidejadesign0 = word.contains(getString(R.string.taidejadesign0));
        taidejadesign1 = word.contains(getString(R.string.taidejadesign1));
        taidejadesign2 = word.contains(getString(R.string.taidejadesign2));
        taidejadesign3 = word.contains(getString(R.string.taidejadesign3));
        taidejadesign4 = word.contains(getString(R.string.taidejadesign4));
        taidejadesign5 = word.contains(getString(R.string.taidejadesign5));
        /**
         * Terveys
         */
        terveys0 = word.contains(getString(R.string.terveys0));
        terveys1 = word.contains(getString(R.string.terveys1));
        terveys2 = word.contains(getString(R.string.terveys2));
        terveys3 = word.contains(getString(R.string.terveys3));
        terveys4 = word.contains(getString(R.string.terveys4));
        terveys5 = word.contains(getString(R.string.terveys5));
        /**
         * Tieteellinenkirjoittaminen ja kirjallisuus
         */
        tieteellinenkirjoittaminen0 = word.contains(getString(R.string.tieteellinenkirjoittaminen0));
        tieteellinenkirjoittaminen1 = word.contains(getString(R.string.tieteellinenkirjoittaminen1));
        tieteellinenkirjoittaminen2 = word.contains(getString(R.string.tieteellinenkirjoittaminen2));
        tieteellinenkirjoittaminen3 = word.contains(getString(R.string.tieteellinenkirjoittaminen3));
        tieteellinenkirjoittaminen4 = word.contains(getString(R.string.tieteellinenkirjoittaminen4));
        tieteellinenkirjoittaminen5 = word.contains(getString(R.string.tieteellinenkirjoittaminen5));
        /**
         * Urheilu
         */
        urheilu0 = word.contains(getString(R.string.urheilu0));
        urheilu1 = word.contains(getString(R.string.urheilu1));
        urheilu2 = word.contains(getString(R.string.urheilu2));
        urheilu3 = word.contains(getString(R.string.urheilu3));
        urheilu4 = word.contains(getString(R.string.urheilu4));
        urheilu5 = word.contains(getString(R.string.urheilu5));
        /**
         * Uskonnot
         */
        uskonnot0 = word.contains(getString(R.string.uskonnot0));
        uskonnot1 = word.contains(getString(R.string.uskonnot1));
        uskonnot2 = word.contains(getString(R.string.uskonnot2));
        uskonnot3 = word.contains(getString(R.string.uskonnot3));
        uskonnot4 = word.contains(getString(R.string.uskonnot4));
        uskonnot5 = word.contains(getString(R.string.uskonnot5));
        /**
         * Lastenkirjat
         * Lasten romaanit
         */
        lastenromaani0 = word.contains(getString(R.string.lastenromaani0));
        lastenromaani1 = word.contains(getString(R.string.lastenromaani1));
        lastenromaani2 = word.contains(getString(R.string.lastenromaani2));
        lastenromaani3 = word.contains(getString(R.string.lastenromaani3));
        lastenromaani4 = word.contains(getString(R.string.lastenromaani4));
        lastenromaani5 = word.contains(getString(R.string.lastenromaani5));
        /**
         * Englanninkieliset lastenkirjat
         */
        kids0 = word.contains(getString(R.string.kids0));
        kids1 = word.contains(getString(R.string.kids1));
        kids2 = word.contains(getString(R.string.kids2));
        kids3 = word.contains(getString(R.string.kids3));
        kids4 = word.contains(getString(R.string.kids4));
        kids5 = word.contains(getString(R.string.kids5));
        /**
         * Lasten elokuvat
         */
        lastelo0 = word.contains(getString(R.string.lastelo0));
        lastelo1 = word.contains(getString(R.string.lastelo1));
        lastelo2 = word.contains(getString(R.string.lastelo2));
        lastelo3 = word.contains(getString(R.string.lastelo3));
        lastelo4 = word.contains(getString(R.string.lastelo4));
        lastelo5 = word.contains(getString(R.string.lastelo5));
        /**
         * Lastenkirjat
         */
        lasten0 = word.contains(getString(R.string.lasten0));
        lasten1 = word.contains(getString(R.string.lasten1));
        lasten2 = word.contains(getString(R.string.lasten2));
        lasten3 = word.contains(getString(R.string.lasten3));
        lasten4 = word.contains(getString(R.string.lasten4));
        lasten5 = word.contains(getString(R.string.lasten5));
        /**
         * Lasten tietokirjat
         */
        lasttiet0 = word.contains(getString(R.string.lasttiet0));
        lasttiet1 = word.contains(getString(R.string.lasttiet1));
        lasttiet2 = word.contains(getString(R.string.lasttiet2));
        lasttiet3 = word.contains(getString(R.string.lasttiet3));
        lasttiet4 = word.contains(getString(R.string.lasttiet4));
        lasttiet5 = word.contains(getString(R.string.lasttiet5));
        /**
         * Lastenkuvakirjat
         */
        lastenkuvakirja0 = word.contains(getString(R.string.lastenkuvakirja0));
        lastenkuvakirja1 = word.contains(getString(R.string.lastenkuvakirja1));
        lastenkuvakirja2 = word.contains(getString(R.string.lastenkuvakirja2));
        lastenkuvakirja3 = word.contains(getString(R.string.lastenkuvakirja3));
        lastenkuvakirja4 = word.contains(getString(R.string.lastenkuvakirja4));
        lastenkuvakirja5 = word.contains(getString(R.string.lastenkuvakirja5));
        /**
         * Ruotsinkieliset lastenkirjat
         */
        ruotsilasten0 = word.contains(getString(R.string.ruotsilasten0));
        ruotsilasten1 = word.contains(getString(R.string.ruotsilasten1));
        ruotsilasten2 = word.contains(getString(R.string.ruotsilasten2));
        ruotsilasten3 = word.contains(getString(R.string.ruotsilasten3));
        ruotsilasten4 = word.contains(getString(R.string.ruotsilasten4));
        ruotsilasten5 = word.contains(getString(R.string.ruotsilasten5));
        /**
         * Vauvojen ja taaperoiden kirjat
         */
        vauvojenkirja0 = word.contains(getString(R.string.vauvojenkirja0));
        vauvojenkirja1 = word.contains(getString(R.string.vauvojenkirja1));
        vauvojenkirja2 = word.contains(getString(R.string.vauvojenkirja2));
        vauvojenkirja3 = word.contains(getString(R.string.vauvojenkirja3));
        vauvojenkirja4 = word.contains(getString(R.string.vauvojenkirja4));
        vauvojenkirja5 = word.contains(getString(R.string.vauvojenkirja5));
        /**
         * Nuortenkirjat
         */
        nuoret0 = word.contains(getString(R.string.nuoret0));
        nuoret1 = word.contains(getString(R.string.nuoret1));
        nuoret2 = word.contains(getString(R.string.nuoret2));
        nuoret3 = word.contains(getString(R.string.nuoret3));
        nuoret4 = word.contains(getString(R.string.nuoret4));
        nuoret5 = word.contains(getString(R.string.nuoret5));
        /**
         * Puuhaa lapsille
         */
        lastpuuh0 = word.contains(getString(R.string.lastpuuh0));
        lastpuuh1 = word.contains(getString(R.string.lastpuuh1));
        lastpuuh2 = word.contains(getString(R.string.lastpuuh2));
        lastpuuh3 = word.contains(getString(R.string.lastpuuh3));
        lastpuuh4 = word.contains(getString(R.string.lastpuuh4));
        lastpuuh5 = word.contains(getString(R.string.lastpuuh5));
        /**
         * Rikoskirjallisuus
         * Crime novels, in English
         */
        crimeNovEng0 = word.contains(getString(R.string.crimeNovEng0));
        crimeNovEng1 = word.contains(getString(R.string.crimeNovEng1));
        crimeNovEng2 = word.contains(getString(R.string.crimeNovEng2));
        crimeNovEng3 = word.contains(getString(R.string.crimeNovEng3));
        crimeNovEng4 = word.contains(getString(R.string.crimeNovEng4));
        crimeNovEng5 = word.contains(getString(R.string.crimeNovEng5));
        /**
         * Rikos uutuudet
         */
        rikosuudet0 = word.contains(getString(R.string.rikosuudet0));
        rikosuudet1 = word.contains(getString(R.string.rikosuudet1));
        rikosuudet2 = word.contains(getString(R.string.rikosuudet2));
        rikosuudet3 = word.contains(getString(R.string.rikosuudet3));
        rikosuudet4 = word.contains(getString(R.string.rikosuudet4));
        rikosuudet5 = word.contains(getString(R.string.rikosuudet5));
        /**
         * Rikospokkarit
         */
        rikospokka0 = word.contains(getString(R.string.rikospokka0));
        rikospokka1 = word.contains(getString(R.string.rikospokka1));
        rikospokka2 = word.contains(getString(R.string.rikospokka2));
        rikospokka3 = word.contains(getString(R.string.rikospokka3));
        rikospokka4 = word.contains(getString(R.string.rikospokka4));
        rikospokka5 = word.contains(getString(R.string.rikospokka5));
        /**
         * Rikosromaanit
         */
        rikosromaani0 = word.contains(getString(R.string.rikosromaani0));
        rikosromaani1 = word.contains(getString(R.string.rikosromaani1));
        rikosromaani2 = word.contains(getString(R.string.rikosromaani2));
        rikosromaani3 = word.contains(getString(R.string.rikosromaani3));
        rikosromaani4 = word.contains(getString(R.string.rikosromaani4));
        rikosromaani5 = word.contains(getString(R.string.rikosromaani5));
        /**
         * True Crime
         */
        truecrime0 = word.contains(getString(R.string.truecrime0));
        truecrime1 = word.contains(getString(R.string.truecrime1));
        truecrime2 = word.contains(getString(R.string.truecrime2));
        truecrime3 = word.contains(getString(R.string.truecrime3));
        truecrime4 = word.contains(getString(R.string.truecrime4));
        truecrime5 = word.contains(getString(R.string.truecrime5));
        /**
         * Pokkarit
         * Kaunopokkarit
         */
        kaunopokkarit0 = word.contains(getString(R.string.kaunopokkarit0));
        kaunopokkarit1 = word.contains(getString(R.string.kaunopokkarit1));
        kaunopokkarit2 = word.contains(getString(R.string.kaunopokkarit2));
        kaunopokkarit3 = word.contains(getString(R.string.kaunopokkarit3));
        kaunopokkarit4 = word.contains(getString(R.string.kaunopokkarit4));
        kaunopokkarit5 = word.contains(getString(R.string.kaunopokkarit5));
        /**
         * Scifi ja fantasia pokkarit
         */
        scifijafantasiapokkarit0 = word.contains(getString(R.string.scifijafantasiapokkarit0));
        scifijafantasiapokkarit1 = word.contains(getString(R.string.scifijafantasiapokkarit1));
        scifijafantasiapokkarit2 = word.contains(getString(R.string.scifijafantasiapokkarit2));
        scifijafantasiapokkarit3 = word.contains(getString(R.string.scifijafantasiapokkarit3));
        scifijafantasiapokkarit4 = word.contains(getString(R.string.scifijafantasiapokkarit4));
        scifijafantasiapokkarit5 = word.contains(getString(R.string.scifijafantasiapokkarit5));
        /**
         * Tietopokkarit
         */
        tietopokkarit0 = word.contains(getString(R.string.tietopokkarit0));
        tietopokkarit1 = word.contains(getString(R.string.tietopokkarit1));
        tietopokkarit2 = word.contains(getString(R.string.tietopokkarit2));
        tietopokkarit3 = word.contains(getString(R.string.tietopokkarit3));
        tietopokkarit4 = word.contains(getString(R.string.tietopokkarit4));
        tietopokkarit5 = word.contains(getString(R.string.tietopokkarit5));
        /**
         * Englanninkieliset kirjat
         * Crime
         */
        crime0 = word.contains(getString(R.string.crime0));
        crime1 = word.contains(getString(R.string.crime1));
        crime2 = word.contains(getString(R.string.crime2));
        crime3 = word.contains(getString(R.string.crime3));
        crime4 = word.contains(getString(R.string.crime4));
        crime5 = word.contains(getString(R.string.crime5));
        /**
         * Fiction
         */
        fiction0 = word.contains(getString(R.string.fiction0));
        fiction1 = word.contains(getString(R.string.fiction1));
        fiction2 = word.contains(getString(R.string.fiction2));
        fiction3 = word.contains(getString(R.string.fiction3));
        fiction4 = word.contains(getString(R.string.fiction4));
        fiction5 = word.contains(getString(R.string.fiction5));
        /**
         * Non-fiction
         */
        nonfiction0 = word.contains(getString(R.string.nonfiction0));
        nonfiction1 = word.contains(getString(R.string.nonfiction1));
        nonfiction2 = word.contains(getString(R.string.nonfiction2));
        nonfiction3 = word.contains(getString(R.string.nonfiction3));
        nonfiction4 = word.contains(getString(R.string.nonfiction4));
        nonfiction5 = word.contains(getString(R.string.nonfiction5));
        /**
         * Poetry
         */
        poetry0 = word.contains(getString(R.string.poetry0));
        poetry1 = word.contains(getString(R.string.poetry1));
        poetry2 = word.contains(getString(R.string.poetry2));
        poetry3 = word.contains(getString(R.string.poetry3));
        poetry4 = word.contains(getString(R.string.poetry4));
        poetry5 = word.contains(getString(R.string.poetry5));
        /**
         * Englanninkielinen scifi
         */
        engscifi0 = word.contains(getString(R.string.engscifi0));
        engscifi1 = word.contains(getString(R.string.engscifi1));
        engscifi2 = word.contains(getString(R.string.engscifi2));
        engscifi3 = word.contains(getString(R.string.engscifi3));
        engscifi4 = word.contains(getString(R.string.engscifi4));
        engscifi5 = word.contains(getString(R.string.engscifi5));
        /**
         * Uudet fiction
         */
        newfiction0 = word.contains(getString(R.string.newfiction0));
        newfiction1 = word.contains(getString(R.string.newfiction1));
        newfiction2 = word.contains(getString(R.string.newfiction2));
        newfiction3 = word.contains(getString(R.string.newfiction3));
        newfiction4 = word.contains(getString(R.string.newfiction4));
        newfiction5 = word.contains(getString(R.string.newfiction5));
        /**
         * Uudet Non-fiction
         */
        newnonfiction0 = word.contains(getString(R.string.newnonfiction0));
        newnonfiction1 = word.contains(getString(R.string.newnonfiction1));
        newnonfiction2 = word.contains(getString(R.string.newnonfiction2));
        newnonfiction3 = word.contains(getString(R.string.newnonfiction3));
        newnonfiction4 = word.contains(getString(R.string.newnonfiction4));
        newnonfiction5 = word.contains(getString(R.string.newnonfiction5));
        /**
         * Lehdet
         */
        lehd0 = word.contains(getString(R.string.lehd0));
        lehd1 = word.contains(getString(R.string.lehd1));
        lehd2 = word.contains(getString(R.string.lehd2));
        lehd3 = word.contains(getString(R.string.lehd3));
        lehd4 = word.contains(getString(R.string.lehd4));
        lehd5 = word.contains(getString(R.string.lehd5));
        /**
         * Sarjakuvat
         */
        sarja0 = word.contains(getString(R.string.sarja0));
        sarja1 = word.contains(getString(R.string.sarja1));
        sarja2 = word.contains(getString(R.string.sarja2));
        sarja3 = word.contains(getString(R.string.sarja3));
        sarja4 = word.contains(getString(R.string.sarja4));
        sarja5 = word.contains(getString(R.string.sarja5));
        /**
         * Pokkarit
         */
        pokka0 = word.contains(getString(R.string.pokka0));
        pokka1 = word.contains(getString(R.string.pokka1));
        pokka2 = word.contains(getString(R.string.pokka2));
        pokka3 = word.contains(getString(R.string.pokka3));
        pokka4 = word.contains(getString(R.string.pokka4));
        pokka5 = word.contains(getString(R.string.pokka5));
        /**
         * Englanninkieliset
         */
        engl0 = word.contains(getString(R.string.engl0));
        engl1 = word.contains(getString(R.string.engl1));
        engl2 = word.contains(getString(R.string.engl2));
        engl3 = word.contains(getString(R.string.engl3));
        engl4 = word.contains(getString(R.string.engl4));
        engl5 = word.contains(getString(R.string.engl5));
        /**
         * Elokuvat
         */
        elokuvat0 = word.contains(getString(R.string.elokuvat0));
        elokuvat1 = word.contains(getString(R.string.elokuvat1));
        elokuvat2 = word.contains(getString(R.string.elokuvat2));
        elokuvat3 = word.contains(getString(R.string.elokuvat3));
        elokuvat4 = word.contains(getString(R.string.elokuvat4));
        elokuvat5 = word.contains(getString(R.string.elokuvat5));
        /**
         * Ajankoitaiset
         */
        ajankohtaista0 = word.contains(getString(R.string.ajankohtaista0));
        ajankohtaista1 = word.contains(getString(R.string.ajankohtaista1));
        ajankohtaista2 = word.contains(getString(R.string.ajankohtaista2));
        ajankohtaista3 = word.contains(getString(R.string.ajankohtaista3));
        ajankohtaista4 = word.contains(getString(R.string.ajankohtaista4));
        ajankohtaista5 = word.contains(getString(R.string.ajankohtaista5));
        /**
         * Paperituotteet
         */
        paperituotteet0 = word.contains(getString(R.string.paperituotteet0));
        paperituotteet1 = word.contains(getString(R.string.paperituotteet1));
        paperituotteet2 = word.contains(getString(R.string.paperituotteet2));
        paperituotteet3 = word.contains(getString(R.string.paperituotteet3));
        paperituotteet4 = word.contains(getString(R.string.paperituotteet4));
        paperituotteet5 = word.contains(getString(R.string.paperituotteet5));
        /**
         * Kaikki avainsanat läpi käytyä katsotaan onko jokin avainsanan boolean muuttunut true:ksi eli vastannutr kuultua.
         * Muutetaan kyseisen vastaavuuden mukaan kyseisen kategororian boolean true:ksi.
         * laskuri arvo muutetaan 0 joka kerta kun vastaavuus löytyy.
         * lopuksi kun vastaavuus löytyy haetaan vertaa metodi jossa toteutetaan toiminta.
         */
        if (ruotsinkieli0 || ruotsinkieli1 || ruotsinkieli2 || ruotsinkieli3 || ruotsinkieli4 || ruotsinkieli5) {
            ruotsinkieli = true;
            laskuri = 0;
            vertaa();
        } else if (ranskankieli0 || ranskankieli1 || ranskankieli2 || ranskankieli3 || ranskankieli4 || ranskankieli5) {
            ranskankieli = true;
            laskuri = 0;
            vertaa();
        } else if (saksankieli0 || saksankieli1 || saksankieli2 || saksankieli3 || saksankieli4 || saksankieli5) {
            saksankieli = true;
            laskuri = 0;
            vertaa();
        } else if (baabel0 || baabel1 || baabel2 || baabel3 || baabel4 || baabel5) {
            baabel = true;
            laskuri = 0;
            vertaa();
        } else if (antikvaariset0 || antikvaariset1 || antikvaariset2 || antikvaariset3 || antikvaariset4 || antikvaariset5) {
            antikvaariset = true;
            laskuri = 0;
            vertaa();
        } /*else if (essee0 || essee1 || essee2 || essee3 || essee4 || essee5) {
            essee = true;
            laskuri = 0;
            vertaa();
        }*/ else if (kauno0 || kauno1 || kauno2 || kauno3 || kauno4 || kauno5) {
            kaunokirjallisuus = true;
            laskuri = 0;
            vertaa();
        } else if (kelta0 || kelta1 || kelta2 || kelta3 || kelta4 || kelta5) {
            keltainen = true;
            laskuri = 0;
            vertaa();
        } else if (uudetkauno0 || uudetkauno1 || uudetkauno2 || uudetkauno3 || uudetkauno4 || uudetkauno5) {
            uudetkaunokirjallisuus = true;
            laskuri = 0;
            vertaa();
        } else if (klassikot0 || klassikot1 || klassikot2 || klassikot3 || klassikot4 || klassikot5) {
            klassikot = true;
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
        } else if (antiikkikirjallisuus0 || antiikkikirjallisuus1 || antiikkikirjallisuus2 || antiikkikirjallisuus3 || antiikkikirjallisuus4 || antiikkikirjallisuus5) {
            antiikkikirjallisuus = true;
            laskuri = 0;
            vertaa();
        } else if (holokausti0 || holokausti1 || holokausti2 || holokausti3 || holokausti4 || holokausti5) {
            holokausti = true;
            laskuri = 0;
            vertaa();
        } else if (ilmastonmuutos0 || ilmastonmuutos1 || ilmastonmuutos2 || ilmastonmuutos3 || ilmastonmuutos4 || ilmastonmuutos5) {
            ilmastonmuutos = true;
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
        } else if (kasityojarakentaminen0 || kasityojarakentaminen1 || kasityojarakentaminen2 || kasityojarakentaminen3 || kasityojarakentaminen4 || kasityojarakentaminen5) {
            kasityojarakentaminen = true;
            laskuri = 0;
            vertaa();
        } else if (luonnontieteet0 || luonnontieteet1 || luonnontieteet2 || luonnontieteet3 || luonnontieteet4 || luonnontieteet5) {
            luonnontieteet = true;
            laskuri = 0;
            vertaa();
        } else if (luontokasvitelaimet0 || luontokasvitelaimet1 || luontokasvitelaimet2 || luontokasvitelaimet3 || luontokasvitelaimet4 || luontokasvitelaimet5) {
            luontokasvitelaimet = true;
            laskuri = 0;
            vertaa();
        } else if (matkailu0 || matkailu1 || matkailu2 || matkailu3 || matkailu4 || matkailu5) {
            matkailu = true;
            laskuri = 0;
            vertaa();
        } else if (puutarha0 || puutarha1 || puutarha2 || puutarha3 || puutarha4 || puutarha5) {
            puutarha = true;
            laskuri = 0;
            vertaa();
        } else if (ruoka0 || ruoka1 || ruoka2 || ruoka3 || ruoka4 || ruoka5) {
            ruoka = true;
            laskuri = 0;
            vertaa();
        } else if (taidejadesign0 || taidejadesign1 || taidejadesign2 || taidejadesign3 || taidejadesign4 || taidejadesign5) {
            taidejadesign = true;
            laskuri = 0;
            vertaa();
        } else if (terveys0 || terveys1 || terveys2 || terveys3 || terveys4 || terveys5) {
            terveys = true;
            laskuri = 0;
            vertaa();
        } else if (tieteellinenkirjoittaminen0 || tieteellinenkirjoittaminen1 || tieteellinenkirjoittaminen2 || tieteellinenkirjoittaminen3 || tieteellinenkirjoittaminen4 || tieteellinenkirjoittaminen5) {
            tieteellinenkirjoittaminen = true;
            laskuri = 0;
            vertaa();
        } else if (urheilu0 || urheilu1 || urheilu2 || urheilu3 || urheilu4 || urheilu5) {
            urheilu = true;
            laskuri = 0;
            vertaa();
        } else if (uskonnot0 || uskonnot1 || uskonnot2 || uskonnot3 || uskonnot4 || uskonnot5){
            uskonnot = true;
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
        } else if (engl0 || engl1 || engl2 || engl3 || engl4 || engl5) {
            englanti = true;
            laskuri = 0;
            vertaa();
        } else if (lastenromaani0 || lastenromaani1 || lastenromaani2 || lastenromaani3 || lastenromaani4 || lastenromaani5) {
            lastenromaani = true;
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
        } else if (lasttiet0 || lasttiet1 || lasttiet2 || lasttiet3 || lasttiet4 || lasttiet5) {
            lastentietikirjat = true;
            laskuri = 0;
            vertaa();
        } else if (lastenkuvakirja0 || lastenkuvakirja1 || lastenkuvakirja2 || lastenkuvakirja3 || lastenkuvakirja4 || lastenkuvakirja5) {
            lastenkuvakirja = true;
            laskuri = 0;
            vertaa();
        } else if (ruotsilasten0 || ruotsilasten1 || ruotsilasten2 || ruotsilasten3 || ruotsilasten4 || ruotsilasten5) {
            ruotsinkielisetlastenkirjat = true;
            laskuri = 0;
            vertaa();
        } else if (vauvojenkirja0 || vauvojenkirja1 || vauvojenkirja2 || vauvojenkirja3 || vauvojenkirja4 || vauvojenkirja5) {
            vauvojenkirja = true;
            laskuri = 0;
            vertaa();
        } else if (nuoret0 || nuoret1 || nuoret2 || nuoret3 || nuoret4 || nuoret5) {
            nuoret = true;
            laskuri = 0;
            vertaa();
        } else if (lastpuuh0 || lastpuuh1 || lastpuuh2 || lastpuuh3 || lastpuuh4 || lastpuuh5) {
            puuhaalapsille = true;
            laskuri = 0;
            vertaa();
        } else if (crimeNovEng0 || crimeNovEng1 || crimeNovEng2 || crimeNovEng3 || crimeNovEng4 || crimeNovEng5) {
            CrimeNovelsInEnglish = true;
            laskuri = 0;
            vertaa();
        } else if (rikosuudet0 || rikosuudet1 || rikosuudet2 || rikosuudet3 || rikosuudet4 || rikosuudet5) {
            RikoskirjaUutuudet = true;
            laskuri = 0;
            vertaa();
        } else if (rikospokka0 || rikospokka1 || rikospokka2 || rikospokka3 || rikospokka4 || rikospokka5) {
            RikosPokkarit = true;
            laskuri = 0;
            vertaa();
        } else if (rikosromaani0 || rikosromaani1 || rikosromaani2 || rikosromaani3 || rikosromaani4 || rikosromaani5) {
            Rikosromaanit = true;
            laskuri = 0;
            vertaa();
        } else if (truecrime0 || truecrime1 || truecrime2 || truecrime3 || truecrime4 || truecrime5) {
            TrueCrime = true;
            laskuri = 0;
            vertaa();
        } else if (kaunopokkarit0 || kaunopokkarit1 || kaunopokkarit2 || kaunopokkarit3 || kaunopokkarit4 || kaunopokkarit5) {
            kaunopokkarit = true;
            laskuri = 0;
            vertaa();
        } else if (scifijafantasiapokkarit0 || scifijafantasiapokkarit1 || scifijafantasiapokkarit2 || scifijafantasiapokkarit3 || scifijafantasiapokkarit4 || scifijafantasiapokkarit5) {
            scifijafantasiapokkarit = true;
            laskuri = 0;
            vertaa();
        } else if (tietopokkarit0 || tietopokkarit1 || tietopokkarit2 || tietopokkarit3 || tietopokkarit4 || tietopokkarit5) {
            tietopokkarit = true;
            laskuri = 0;
            vertaa();
        } else if (crime0 || crime1 || crime2 || crime3 || crime4 || crime5) {
            crime = true;
            laskuri = 0;
            vertaa();
        } else if (nonfiction0 || nonfiction1 || nonfiction2 || nonfiction3 || nonfiction4 || nonfiction5) {
            nonfiction = true;
            laskuri = 0;
            vertaa();
        } else if (poetry0 || poetry1 || poetry2 || poetry3 || poetry4 || poetry5) {
            poetry = true;
            laskuri = 0;
            vertaa();
        } else if (engscifi0 || engscifi1 || engscifi2 || engscifi3 || engscifi4 || engscifi5) {
            engscifi = true;
            laskuri = 0;
            vertaa();
        } else if (newfiction0 || newfiction1 || newfiction2 || newfiction3 || newfiction4 || newfiction5) {
            newfiction = true;
            laskuri = 0;
            vertaa();
        } else if (newnonfiction0 || newnonfiction1 || newnonfiction2 || newnonfiction3 || newnonfiction4 || newnonfiction5) {
            newnonfiction = true;
            laskuri = 0;
            vertaa();
        } else if (elokuvat0 || elokuvat1 || elokuvat3 || elokuvat4 || elokuvat5) {
            elokuvat = true;
            laskuri = 0;
            vertaa();
        } else if (fantasia0 || fantasia1 || fantasia2 || fantasia3 || fantasia4 || fantasia5 || fantasia6 || fantasia7 || fantasia8 || fantasia9) {
            fantasia = true;
            laskuri = 0;
            vertaa();
        } else if (ajankohtaista0 || ajankohtaista1 || ajankohtaista2 || ajankohtaista3 || ajankohtaista4 || ajankohtaista5) {
            ajankohtaista = true;
            laskuri = 0;
            vertaa();
        } else if (kielet0 || kielet1 || kielet2 || kielet3 || kielet4 || kielet5) {
            kielet = true;
            laskuri = 0;
            vertaa();
        } else if (sijainti0 || sijainti1 || sijainti2 || sijainti3) {
            sijainti = true;
            laskuri = 0;
            vertaa();
        } else if (kiito || oli || kiitos1) {
            kiitoksia = true;
            vertaa();
        } else if (tiede0 || tiede1 || tiede2 || tiede3 || tiede4 || tiede5 || tiede6 || tiede7) {
            tiede = true;
            laskuri = 0;
            vertaa();
        } else if (lasten0 || lasten1 || lasten2 || lasten3 || lasten4 || lasten5) {
            lastenkirjat = true;
            laskuri = 0;
            vertaa();
        } else if (maatjakulttuuri0 || maatjakulttuuri1 || maatjakulttuuri2 || maatjakulttuuri3 || maatjakulttuuri4 || maatjakulttuuri5) {
            maatjakulttuuri = true;
            laskuri = 0;
            vertaa();
        } else if (paperituotteet0 || paperituotteet1 || paperituotteet2 || paperituotteet3 || paperituotteet4 || paperituotteet5){
            paperituotteet = true;
            laskuri = 0;
            vertaa();
        } else if (fiction0 || fiction1 || fiction2 || fiction3 || fiction4 || fiction5) {
            fiction = true;
            laskuri = 0;
            vertaa();
        } else if (keskustelu0 || keskustelu1 || keskustelu2 || keskustelu3 ||
                keskustelu4) {
            /**
             * kokeellisissa interaktiivisissa vastauksissa toiminta on tuotettu tässä metodissa mutta sen voi siirtää tulevaisuudessa myös vertaa metodiin.
             */
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
            /**
             * Mikäli puuheesta ei löydetty avain sanoja lisätään int "laskuri" yksi arvo.
             */
            ++laskuri;
            /**
             * Mikäli int "laskuri" arvo on kaksi pysäytetään kuuntelu kierre ja annetaan uudet ohjeet.
             * Tällä keskeytetään ikuinen loop jossa aina kysytään uudelleen kun ei ymmerretä.
             * laskuri myös nollataan tässä.
             */
            if (laskuri == 2) {
                MainActivity.textToSpeech.speak(getString(R.string.kysyuu), TextToSpeech.QUEUE_FLUSH, null);
                speechRecognizer.stopListening();
                laskuri = 0;
                /**
                 * Mikäli int "laskuri" arvo on alle 2 pyydetään kysymään uudelleen ja aloitetaan uudestaan kuuntelu
                 */
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

    /**
     * Vertaa() methodissa toteutetaan toiminta mikö on valittu joko nappulalla tai avainsanan löytämisellä.
     */
    public void vertaa() {
        /**
         * Metodin alussa piilotetaan kaikki markerit jotta saadaan varmasti näkyviin ainoastaan haetut sijainnit.
         */
        marker1.setVisibility(View.INVISIBLE);
        marker2.setVisibility(View.INVISIBLE);
        marker3.setVisibility(View.INVISIBLE);
        marker4.setVisibility(View.INVISIBLE);
        marker5.setVisibility(View.INVISIBLE);
        marker6.setVisibility(View.INVISIBLE);
        marker7.setVisibility(View.INVISIBLE);
        marker8.setVisibility(View.INVISIBLE);
        marker9.setVisibility(View.INVISIBLE);
        marker10.setVisibility(View.INVISIBLE);
        marker11.setVisibility(View.INVISIBLE);
        marker12.setVisibility(View.INVISIBLE);
        marker13.setVisibility(View.INVISIBLE);
        marker14.setVisibility(View.INVISIBLE);
        marker15.setVisibility(View.INVISIBLE);
        marker16.setVisibility(View.INVISIBLE);
        marker17.setVisibility(View.INVISIBLE);
        marker18.setVisibility(View.INVISIBLE);
        marker19.setVisibility(View.INVISIBLE);
        marker20.setVisibility(View.INVISIBLE);
        marker21.setVisibility(View.INVISIBLE);
        marker22.setVisibility(View.INVISIBLE);
        marker23.setVisibility(View.INVISIBLE);
        marker24.setVisibility(View.INVISIBLE);
        marker25.setVisibility(View.INVISIBLE);
        marker26.setVisibility(View.INVISIBLE);
        marker27.setVisibility(View.INVISIBLE);
        marker28.setVisibility(View.INVISIBLE);
        marker29.setVisibility(View.INVISIBLE);
        marker30.setVisibility(View.INVISIBLE);
        marker31.setVisibility(View.INVISIBLE);
        marker32.setVisibility(View.INVISIBLE);
        marker33.setVisibility(View.INVISIBLE);
        marker34.setVisibility(View.INVISIBLE);
        marker35.setVisibility(View.INVISIBLE);
        marker36.setVisibility(View.INVISIBLE);
        marker37.setVisibility(View.INVISIBLE);
        marker38.setVisibility(View.INVISIBLE);
        marker39.setVisibility(View.INVISIBLE);
        marker40.setVisibility(View.INVISIBLE);
        marker41.setVisibility(View.INVISIBLE);
        marker42.setVisibility(View.INVISIBLE);
        marker43.setVisibility(View.INVISIBLE);
        marker44.setVisibility(View.INVISIBLE);
        marker45.setVisibility(View.INVISIBLE);
        marker46.setVisibility(View.INVISIBLE);
        marker47.setVisibility(View.INVISIBLE);
        marker48.setVisibility(View.INVISIBLE);
        marker49.setVisibility(View.INVISIBLE);
        marker50.setVisibility(View.INVISIBLE);
        marker51.setVisibility(View.INVISIBLE);
        marker52.setVisibility(View.INVISIBLE);
        marker53.setVisibility(View.INVISIBLE);
        marker54.setVisibility(View.INVISIBLE);
        /**
         * if elsen avulla käydään kaikkien kategorioiden booleanit läpi jotta löydetään kategoria jota on haettu.
         * Mikäli kategorian boolean on true toteutetaan sen toiminta.
         *
         * Kaikkien kategorioiden rakenne on sama mutta toiminnat eroavat
         * Tarkempi kuvaus toiminnasta kommentoituna löytyy vain "else if (ruotsinkieli)" alta.
         *
         * Sijainti ei ole käytössä. Sijainti näytti käyttäjälle oman sijainnin kartalla. Sijainti on tällähetkellä piirettynä kartta kuvaan manuaalisesti
         */
        if (MainActivity.sijainti) {

            ylakate.setVisibility(View.VISIBLE);

            /*RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);
            markerRelativeLayout.leftMargin = 1600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);*/

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);

        }
        /**
         * Ruotsi, ranska ja saksa toiminnot
         */
        else if (ruotsinkieli){
            /**
             * tuotetaan kyseisen kategorian sijainti näkyviin
             * Katso lisä materiaali sijainti numeroista ja niiden sijainnista kartalla.
             */
            marker30.setVisibility(View.VISIBLE);
            /**
             * Vaihdetaan hahmo1 tilalle puhuva animaatio
             */
            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            /**
             * Tuotetaan puhe ohjeistus sijainnista.
             */
            //String x = getString(R.string.mispuhe);
            MainActivity.textToSpeech.speak(getString(R.string.ruotsinkielipuhe), TextToSpeech.QUEUE_FLUSH, null);
            /**
             * Muutetaan kategorian boolean = false.
             * Muutetaan kategorian avainsanojen booleanit = false.
             */
            ruotsinkieli = false;
            ruotsinkieli0 = false;
            ruotsinkieli1 = false;
            ruotsinkieli2 = false;
            ruotsinkieli3 = false;
            ruotsinkieli4 = false;
            ruotsinkieli5 = false;
            /**
             * Luodaan handler joka muuttaa hahmo1 figuurin takaisin perus kuvaksi puheen jälkeen.
             */
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);
        }
        else if (ranskankieli){
            marker20.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.mispuhe);
            MainActivity.textToSpeech.speak(getString(R.string.ranskankielipuhe), TextToSpeech.QUEUE_FLUSH, null);
            ranskankieli = false;
            ranskankieli0 = false;
            ranskankieli1 = false;
            ranskankieli2 = false;
            ranskankieli3 = false;
            ranskankieli4 = false;
            ranskankieli5 = false;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);
        }
        else if (saksankieli){
            marker20.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.mispuhe);
            MainActivity.textToSpeech.speak(getString(R.string.saksankielipuhe), TextToSpeech.QUEUE_FLUSH, null);
            saksankieli = false;
            saksankieli0 = false;
            saksankieli1 = false;
            saksankieli2 = false;
            saksankieli3 = false;
            saksankieli4 = false;
            saksankieli5 = false;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);
        }
        /**
         * Kaunokirjallisuus kategorian toiminnot
         */
        else if (baabel) {
            marker10.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);

        }
        else if (antikvaariset) {

            marker10.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.mispuhe);
            MainActivity.textToSpeech.speak(getString(R.string.antikvaarisetpuhe), TextToSpeech.QUEUE_FLUSH, null);
            antikvaariset = false;
            antikvaariset0 = false;
            antikvaariset1 = false;
            antikvaariset2 = false;
            antikvaariset3 = false;
            antikvaariset4 = false;
            antikvaariset5 = false;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);

        }
        /*else if (essee) {

            RelativeLayout.LayoutParams markerRelativeLayout = new RelativeLayout.LayoutParams(60, 60);
            markerRelativeLayout.leftMargin = 1600;
            markerRelativeLayout.topMargin = 500;
            MainActivity.marker.setLayoutParams(markerRelativeLayout);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);

        }*/
        else if (kaunokirjallisuus) {
            marker24.setVisibility(View.VISIBLE);
            marker27.setVisibility(View.VISIBLE);
            marker28.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);

        }
        else if (keltainen) {
            marker10.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);

        }
        else if (uudetkaunokirjallisuus) {

            marker26.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.mispuhe);
            MainActivity.textToSpeech.speak(getString(R.string.uudetkaunopuhe), TextToSpeech.QUEUE_FLUSH, null);
            uudetkaunokirjallisuus = false;
            uudetkauno0 = false;
            uudetkauno1 = false;
            uudetkauno2 = false;
            uudetkauno3 = false;
            uudetkauno4 = false;
            uudetkauno5 = false;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);

        }
        else if (klassikot) {

            marker24.setVisibility(View.VISIBLE);
            marker27.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.mispuhe);
            MainActivity.textToSpeech.speak(getString(R.string.klassikotpuhe), TextToSpeech.QUEUE_FLUSH, null);
            klassikot = false;
            klassikot0 = false;
            klassikot1 = false;
            klassikot2 = false;
            klassikot3 = false;
            klassikot4 = false;
            klassikot5 = false;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                    //speechRecognizer.startListening(speechRecognizerIntent);
                }
            }, 4000);
        }
        else if (otava) {

            marker10.setVisibility(View.VISIBLE);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                        //speechRecognizer.startListening(speechRecognizerIntent);
                    }
                }, 4000);

            }
        else if (aanikirja) {

            marker22.setVisibility(View.VISIBLE);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                        //speechRecognizer.startListening(speechRecognizerIntent);
                    }
                }, 4000);

            }
        else if (MainActivity.fantasia) {

                //String x = getString(R.string.fantapuhe);


                marker28.setVisibility(View.VISIBLE);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        else if (runo) {

                //String x = getString(R.string.fantapuhe);

            marker11.setVisibility(View.VISIBLE);
            marker12.setVisibility(View.VISIBLE);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        else if (rikojanitus) {

                //String x = getString(R.string.fantapuhe);
            marker21.setVisibility(View.VISIBLE);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        /**
         * Tietokirjallisuuden katagorian toiminnot
         */
        else if (ukrainajavenaja){

                marker36.setVisibility(View.VISIBLE);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        else if (elokuvajateatteri){
            marker34.setVisibility(View.VISIBLE);;

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        else if (elamankerta){
            marker32.setVisibility(View.VISIBLE);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        else if (antiikkikirjallisuus){
            marker39.setVisibility(View.VISIBLE);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
                //String x = getString(R.string.tiedpuhe);
                MainActivity.textToSpeech.speak(getString(R.string.antiikkikirjallisuuspuhe), TextToSpeech.QUEUE_FLUSH, null);

            antiikkikirjallisuus = false;
            antiikkikirjallisuus0 = false;
            antiikkikirjallisuus1 = false;
            antiikkikirjallisuus2 = false;
            antiikkikirjallisuus3 = false;
            antiikkikirjallisuus4 = false;
            antiikkikirjallisuus5 = false;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        else if (holokausti){
            marker39.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.holokaustipuhe), TextToSpeech.QUEUE_FLUSH, null);

            holokausti = false;
            holokausti0 = false;
            holokausti1 = false;
            holokausti2 = false;
            holokausti3 = false;
            holokausti4 = false;
            holokausti5 = false;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (ilmastonmuutos){
            marker33.setVisibility(View.VISIBLE);;

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.ilmastonmuutospuhe), TextToSpeech.QUEUE_FLUSH, null);

            ilmastonmuutos = false;
            ilmastonmuutos0 = false;
            ilmastonmuutos1 = false;
            ilmastonmuutos2 = false;
            ilmastonmuutos3 = false;
            ilmastonmuutos4 = false;
            ilmastonmuutos5 = false;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (filosofia){
            marker39.setVisibility(View.VISIBLE);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        else if (historia){
            marker36.setVisibility(View.VISIBLE);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        else if (musiikki){
            marker33.setVisibility(View.VISIBLE);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        else if (politiikka){
            marker35.setVisibility(View.VISIBLE);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        else if (sanakirja){
            marker41.setVisibility(View.VISIBLE);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        else if (MainActivity.tiede) {
            marker9.setVisibility(View.VISIBLE);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        else if (kielet){
            marker41.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.kieletpuhe), TextToSpeech.QUEUE_FLUSH, null);

            kielet = false;
            kielet0 = false;
            kielet1 = false;
            kielet2 = false;
            kielet3 = false;
            kielet4 = false;
            kielet5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (kasityojarakentaminen){
            marker54.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.kasityojarakentaminenpuhe), TextToSpeech.QUEUE_FLUSH, null);

            kasityojarakentaminen = false;
            kasityojarakentaminen0 = false;
            kasityojarakentaminen1 = false;
            kasityojarakentaminen2 = false;
            kasityojarakentaminen3 = false;
            kasityojarakentaminen4 = false;
            kasityojarakentaminen5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (luonnontieteet){
            marker36.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.luonnontieteetpuhe), TextToSpeech.QUEUE_FLUSH, null);

            luonnontieteet = false;
            luonnontieteet0 = false;
            luonnontieteet1 = false;
            luonnontieteet2 = false;
            luonnontieteet3 = false;
            luonnontieteet4 = false;
            luonnontieteet5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (luontokasvitelaimet){
            marker16.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.luontokasvitelaimetpuhe), TextToSpeech.QUEUE_FLUSH, null);

            luontokasvitelaimet = false;
            luontokasvitelaimet0 = false;
            luontokasvitelaimet1 = false;
            luontokasvitelaimet2 = false;
            luontokasvitelaimet3 = false;
            luontokasvitelaimet4 = false;
            luontokasvitelaimet5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (maatjakulttuuri){
            marker39.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.maatjakulttuuripuhe), TextToSpeech.QUEUE_FLUSH, null);

            maatjakulttuuri = false;
            maatjakulttuuri0 = false;
            maatjakulttuuri1 = false;
            maatjakulttuuri2 = false;
            maatjakulttuuri3 = false;
            maatjakulttuuri4 = false;
            maatjakulttuuri5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (matkailu){
            marker41.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.matkailupuhe), TextToSpeech.QUEUE_FLUSH, null);

            matkailu = false;
            matkailu0 = false;
            matkailu1 = false;
            matkailu2 = false;
            matkailu3 = false;
            matkailu4 = false;
            matkailu5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (puutarha){
            marker16.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.puutarhapuhe), TextToSpeech.QUEUE_FLUSH, null);

            puutarha = false;
            puutarha0 = false;
            puutarha1 = false;
            puutarha2 = false;
            puutarha3 = false;
            puutarha4 = false;
            puutarha5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (ruoka){
            marker17.setVisibility(View.VISIBLE);
            marker54.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.ruokapuhe), TextToSpeech.QUEUE_FLUSH, null);

            ruoka = false;
            ruoka0 = false;
            ruoka1 = false;
            ruoka2 = false;
            ruoka3 = false;
            ruoka4 = false;
            ruoka5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (taidejadesign){
            marker51.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.taidejadesignpuhe), TextToSpeech.QUEUE_FLUSH, null);

            taidejadesign = false;
            taidejadesign0 = false;
            taidejadesign1 = false;
            taidejadesign2 = false;
            taidejadesign3 = false;
            taidejadesign4 = false;
            taidejadesign5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (terveys){
            marker40.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.terveyspuhe), TextToSpeech.QUEUE_FLUSH, null);

            terveys = false;
            terveys0 = false;
            terveys1 = false;
            terveys2 = false;
            terveys3 = false;
            terveys4 = false;
            terveys5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (tieteellinenkirjoittaminen){
            marker33.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.tieteellinenkirjoittaminenpuhe), TextToSpeech.QUEUE_FLUSH, null);

            tieteellinenkirjoittaminen = false;
            tieteellinenkirjoittaminen0 = false;
            tieteellinenkirjoittaminen1 = false;
            tieteellinenkirjoittaminen2 = false;
            tieteellinenkirjoittaminen3 = false;
            tieteellinenkirjoittaminen4 = false;
            tieteellinenkirjoittaminen5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (urheilu){
            marker16.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.urheilupuhe), TextToSpeech.QUEUE_FLUSH, null);

            urheilu = false;
            urheilu0 = false;
            urheilu1 = false;
            urheilu2 = false;
            urheilu3 = false;
            urheilu4 = false;
            urheilu5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (uskonnot){
            marker39.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.uskonnotpuhe), TextToSpeech.QUEUE_FLUSH, null);

            uskonnot = false;
            uskonnot0 = false;
            uskonnot1 = false;
            uskonnot2 = false;
            uskonnot3 = false;
            uskonnot4 = false;
            uskonnot5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        /**
         * Ylakategorian yksittäis kategoriat
         */
        else if (lehdet){
            marker52.setVisibility(View.VISIBLE);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
        else if (sarjakuvat){
            marker50.setVisibility(View.VISIBLE);

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);
            }
       /* else if (pokkarit){



            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (englanti){


            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }*/
        else if (MainActivity.kiitoksia) {

                Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
                //String x = getString(R.string.kiitos);
                MainActivity.textToSpeech.speak(getString(R.string.kiitos), TextToSpeech.QUEUE_FLUSH, null);

                MainActivity.kiitoksia = false;
                kiito = false;
                oli = false;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hahmo1.setImageResource(R.drawable.kuvarobo);
                    }
                }, 3600);


                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                restart();
            }
        else if (elokuvat){
            marker31.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.elokuvatpuhe), TextToSpeech.QUEUE_FLUSH, null);
            elokuvat = false;
            elokuvat0 = false;
            elokuvat1 = false;
            elokuvat2 = false;
            elokuvat3 = false;
            elokuvat4 = false;
            elokuvat5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (ajankohtaista){
            marker1.setVisibility(View.VISIBLE);
            marker3.setVisibility(View.VISIBLE);
            marker6.setVisibility(View.VISIBLE);
            marker7.setVisibility(View.VISIBLE);
            marker8.setVisibility(View.VISIBLE);
            marker52.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.ajankohtaistapuhe), TextToSpeech.QUEUE_FLUSH, null);
            ajankohtaista = false;
            ajankohtaista0 = false;
            ajankohtaista1 = false;
            ajankohtaista2 = false;
            ajankohtaista3 = false;
            ajankohtaista4 = false;
            ajankohtaista5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (paperituotteet){
            marker2.setVisibility(View.VISIBLE);
            marker4.setVisibility(View.VISIBLE);
            marker5.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.paperituotteetpuhe), TextToSpeech.QUEUE_FLUSH, null);
            paperituotteet = false;
            paperituotteet0 = false;
            paperituotteet1 = false;
            paperituotteet2 = false;
            paperituotteet3 = false;
            paperituotteet4 = false;
            paperituotteet5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        /**
         * Lastenkirjat kategorian toiminnot
         */
        else if (lastenromaani){
            marker41.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.lastenromaanipuhe), TextToSpeech.QUEUE_FLUSH, null);
            lastenromaani = false;
            lastenromaani0 = false;
            lastenromaani1 = false;
            lastenromaani2 = false;
            lastenromaani3 = false;
            lastenromaani4 = false;
            lastenromaani5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (kids){
            marker49.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (lastenelokuvat){
            marker53.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (lastenkirjat){
            marker41.setVisibility(View.VISIBLE);


            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (lastentietikirjat){
            marker47.setVisibility(View.VISIBLE);
            marker49.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (lastenkuvakirja){
            marker43.setVisibility(View.VISIBLE);
            marker48.setVisibility(View.VISIBLE);
            marker53.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.lastenkuvakirjapuhe), TextToSpeech.QUEUE_FLUSH, null);
            lastenkuvakirja = false;
            lastenkuvakirja0 = false;
            lastenkuvakirja1 = false;
            lastenkuvakirja2 = false;
            lastenkuvakirja3 = false;
            lastenkuvakirja4 = false;
            lastenkuvakirja5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (ruotsinkielisetlastenkirjat){
            marker30.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.ruotsilastenpuhe), TextToSpeech.QUEUE_FLUSH, null);
            ruotsinkielisetlastenkirjat = false;
            ruotsilasten0 = false;
            ruotsilasten1 = false;
            ruotsilasten2 = false;
            ruotsilasten3 = false;
            ruotsilasten4 = false;
            ruotsilasten5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (vauvojenkirja){
            marker44.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.vauvojenkirjapuhe), TextToSpeech.QUEUE_FLUSH, null);
            vauvojenkirja = false;
            vauvojenkirja0 = false;
            vauvojenkirja1 = false;
            vauvojenkirja2 = false;
            vauvojenkirja3 = false;
            vauvojenkirja4 = false;
            vauvojenkirja5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (nuoret){
            marker40.setVisibility(View.VISIBLE);


            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (puuhaalapsille){
            marker45.setVisibility(View.VISIBLE);
            marker46.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        /**
         * Rikoskirja kategoria toiminnot
         */
        else if (CrimeNovelsInEnglish){
            marker15.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (RikoskirjaUutuudet){
            marker25.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (RikosPokkarit){
            marker23.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (Rikosromaanit){
            marker21.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (TrueCrime){
            marker21.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
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
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        /**
         * Pokkarit
         */
        else if (kaunopokkarit){
            marker22.setVisibility(View.VISIBLE);
            marker23.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.kaunopokkaritpuhe), TextToSpeech.QUEUE_FLUSH, null);
            kaunopokkarit = false;
            kaunopokkarit0 = false;
            kaunopokkarit1 = false;
            kaunopokkarit2 = false;
            kaunopokkarit3 = false;
            kaunopokkarit4 = false;
            kaunopokkarit5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (scifijafantasiapokkarit){
            marker23.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.scifijafantasiapokkaritpuhe), TextToSpeech.QUEUE_FLUSH, null);
            scifijafantasiapokkarit = false;
            scifijafantasiapokkarit0 = false;
            scifijafantasiapokkarit1 = false;
            scifijafantasiapokkarit2 = false;
            scifijafantasiapokkarit3 = false;
            scifijafantasiapokkarit4 = false;
            scifijafantasiapokkarit5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (tietopokkarit){
            marker22.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.tietopokkaritpuhe), TextToSpeech.QUEUE_FLUSH, null);
            tietopokkarit = false;
            tietopokkarit0 = false;
            tietopokkarit1 = false;
            tietopokkarit2 = false;
            tietopokkarit3 = false;
            tietopokkarit4 = false;
            tietopokkarit5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        /**
         * Ennglanninkieliset kirja kategoriat
         */
        else if (crime) {
            marker15.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.crimepuhe), TextToSpeech.QUEUE_FLUSH, null);
            crime = false;
            crime0 = false;
            crime1 = false;
            crime2 = false;
            crime3 = false;
            crime4 = false;
            crime5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (fiction) {
            marker15.setVisibility(View.VISIBLE);
            marker16.setVisibility(View.VISIBLE);
            marker18.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.fictionpuhe), TextToSpeech.QUEUE_FLUSH, null);
            fiction = false;
            fiction0 = false;
            fiction1 = false;
            fiction2 = false;
            fiction3 = false;
            fiction4 = false;
            fiction5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (nonfiction) {
            marker13.setVisibility(View.VISIBLE);
            marker14.setVisibility(View.VISIBLE);
            marker19.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.nonfictionpuhe), TextToSpeech.QUEUE_FLUSH, null);
            nonfiction = false;
            nonfiction0 = false;
            nonfiction1 = false;
            nonfiction2 = false;
            nonfiction3 = false;
            nonfiction4 = false;
            nonfiction5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (poetry) {
            marker12.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.poetrypuhe), TextToSpeech.QUEUE_FLUSH, null);
            poetry = false;
            poetry0 = false;
            poetry1 = false;
            poetry2 = false;
            poetry3 = false;
            poetry4 = false;
            poetry5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (engscifi) {
            marker15.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.engscifipuhe), TextToSpeech.QUEUE_FLUSH, null);
            engscifi = false;
            engscifi0 = false;
            engscifi1 = false;
            engscifi2 = false;
            engscifi3 = false;
            engscifi4 = false;
            engscifi5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (newfiction) {
            marker18.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.newfictionpuhe), TextToSpeech.QUEUE_FLUSH, null);
            newfiction = false;
            newfiction0 = false;
            newfiction1 = false;
            newfiction2 = false;
            newfiction3 = false;
            newfiction4 = false;
            newfiction5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        else if (newnonfiction) {
            marker19.setVisibility(View.VISIBLE);

            Glide.with(MainActivity.mainLayout).load(R.drawable.puhuvarobo).into(MainActivity.hahmo1);
            //String x = getString(R.string.tiedpuhe);
            MainActivity.textToSpeech.speak(getString(R.string.newnonfictionpuhe), TextToSpeech.QUEUE_FLUSH, null);
            newnonfiction = false;
            newnonfiction0 = false;
            newnonfiction1 = false;
            newnonfiction2 = false;
            newnonfiction3 = false;
            newnonfiction4 = false;
            newnonfiction5 = false;


            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    hahmo1.setImageResource(R.drawable.kuvarobo);
                }
            }, 3600);
        }
        }

    /**
     * restart() metodi hakee recreate() metodin aktiviteetin uudelleen käynnistämiseksi.
     * Ennen uudelleen käynnistystä nollataan kuvat tunnistuksesta sekä
     * pysäytetään kaikki timerit ja handlerit jottei niitä lähde tuplamääränä pyörimään uudelleen aloitettua.
     */
    protected void restart(){
        //Intent intent = getIntent()
        //finish();
        //startActivity(getIntent());
                resized = null;
                resized2 = null;
                imageView2.setImageBitmap(null);
                imageView.setImageBitmap(null);
                //imageView.setImageResource(0);
                //imageView2.setImageResource(0);
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

    /**
     * onPostResume() metodin tarpeellisuutta tulee selvittää.
     * Metodi oli käytössä avaamaan kameran taustan sekä kameran itsestään kutsuttaessa.
     */
    @Override
    protected void onPostResume() {
        super.onPostResume();
        startBackgroundThread();
        if(textureView.isAvailable())
            openCamera();
        else
            textureView.setSurfaceTextureListener(textureListener);
    }

    /**
     * onPause() tarpeellisuutta tulee selvittöö.
     * Metodi oli käytössä pysäyttämään tarpeen mukaan kameran tausta sekä puheen tuottamis toimintoja.
     */
    @Override
    protected void onPause(){

        stopBackgroundThread();

        if (textToSpeech != null){
            textToSpeech.stop();

        }
        super.onPause();
    }

    /**
     * checkPermission() metodi tarkistaa onko äänen tallentamiselle annettu lupa sovelluksella.
     */
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},RecordAudioRequestCode);
        }
    }

    /**
     * onRequestPermissionResult() metodi tarkistaa käyttölupaa äänentallentamisesta sekä kameran käytöstä.
     */
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

    /**
     * showLanguageDialog() luo popup dialogin josta voi vaihtaa kieltä.
     */
    private void showLanguageDialog(){
        /**
         * Luodaan lista jossa on kaksi eri stringiä jotka ovat kielivaihtoehdot.
         * Luodaan AlertDialog.Builder joka  tekee popupin. Heataan stringi otsikoksi sekä lisätään ruotsinkielinen teksti seuraavelle riville.
         */
        final String[] listItems = {"English", "Suomi"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle(getString(R.string.kielivalinta) + "\nSvensk språkliga användargränssnittet är i utvecklingsfasen.");

        /**
         * Laitetaan luotu lista popuppiin ja tehdään listan itemeistä klikattavaia.
         * Luodaan molempien itemien listenerien sisälle toiminta mitä tehdään (Haetaan setLocale metodi johon lähetetään haluttu kielivalinta).
         * Haetaan restart metodi kielivalinnan jälkeen.
         */
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0){
                    //english
                    /*speechRecognizer.stopListening();
                    textToSpeech.stop();
                    handler.removeCallbacksAndMessages(null);
                    timer.cancel();*/
                    setLocale("en");
                    //recreate();
                    restart();
                }else if(i == 1){
                    //Suomi
                    /*speechRecognizer.stopListening();
                    textToSpeech.stop();
                    handler.removeCallbacksAndMessages(null);
                    timer.cancel();*/
                    setLocale("fi");
                    //recreate();
                    restart();
                }
                dialogInterface.dismiss();

            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }

    /**
     * Valittu kielivalinta muutetaan käyttöliittymään käyttöön jotta sovellus käyttää oikeaa kieltä.
     */
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

    /**
     * Haetaan valittua kielivalintaa ja palautetaan se "language" stringinä. Tätä metodia haettassa saadaan haettua käytössä oleva kieli.
     * Käytetään esim puheentunnistuksen ja puheen tuottamisen määrittelyssä että oikea kieli on valittu.
     */
    public String loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);
        return language;
    }

    /**
     * stopBackgroundThread() ja startBackGroundThread() metodien tarpeellisuutta pitää selvittää.
     * Metodeita kutsuttiin kameran taustan luomiseen ja lopettamiseen mutta kamera toimii piilossa eikä näytä mitään näytöllä.
     */
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


