# RoseBotTablet
Tabletti sovellus kehitetty Samsung Galaxy Tab 8 Ultra 5G tabletille.
Sovelluksen tehtävä on auttaa kirjakaupassa asioivia henkilöitä löytämään haluamansa kirja kategoria helposti.
Sovellus rakentuu robotti figuurista sekä kartasta. Kategoriat on jaettu pää kategorioihin ja niiden alle alakategoriat.
Sovellusta voi käyttää joko puheohjauksella sekä fyysisesti nappeja painamalla.
Sovellus on autonominen ja toimii täysin itsestään sammumatta.

Sovelluksen käyttö.
- Sovellukselle tullessa sovellus on nukkumis tilassa ja seuraa kameran avulla muutoksia edessään. Sovelluksen voi myös aktivoida napilla mikäli sovellus ei tunnista muutosta.
- Sovellus aktivoituu ja alkaa puhumaan. Puhumisen jälkeen näkyviin tulee päänäkymä jossa näkyy kartta sekä yläkategoriat.
  Puhumisen jälkeen myös sovellus alkaa kuuntelemaan puhe komentoja.
- Sovellus vastaanottaa komentoja joko puheella tai napeilla valitsemalla.
- Puhe ohjais ohjaa suoraan valittuu alakategoriaan asetettujen avainsanojen avulla. Nappi ohjauksella tulee ensin valita pääkategoria ja sen jälkeen haluttu alagategoria.
- Valittu kategoria näytetään kartalla nuolen avulla.
- Sovelluksessa on ajastin joka käynnistää sovelluksen automaattisesti uudelleen nukkumis tilaan tietyn ajan kuluttua.
- Sovelluksen voi myös itse käynnistää restart napista uudelleen.
- Sovellus toimii kahdella kielellä, Suomi ja Englanti.
- Sovellus tunnistaa kuvassa muutoksen kameran avulla jos joku tulee tabletin eteen. Jos muutos huomataan käynnistyy ohjelma.

Sovelluksessa on yksi Class: MainActivity

Sovelluksessa on luotuja metodeita:
- OnCreate()
  - Pää mettodi joka pyörittää ohjelmaa. Sisältää puheen tunnistuksen, puheen tuottaminen, timer kameralle ja kuvan ottamisella sekä niiden                             vertaamiselle, kategorioden napit sekä hakee ulkoasun xml tiedostosta.
- lopetus()
  - lopetus() metodi pyörittää timeria joka laskee aikaa jolloin sovellus uudelleen käynnistyy. Metodia haetaan aina kun sovelluksessa tehdään jotakin ja tämä           nollaa timerin jotta se lähtee alusta. Tällä saadaan sovellus käynnistymään uudelleen käyttämättömänä tietyn ajan kuluttua.
- takePicture()
  - takePicture() Metodilla otetaan kuvia. Kuva sijoitetaan kahteen eri Bitmapiin vuorotellen. tämän jälkeen haetaan compareEquivalance() metodia joka vertaa kuvia     toisiinsa ja palauttaa float arvon joka kertoo kuvien eroavaisuuden. Kun eroavaisuus on tarpeeksi suuri muuttuu "ihminen" booelan true:ksi.
- aloita()
  - aloita() metodi on sovelluksen aktivointi metodi. Se haetaan joko sillä että kuvissa tunnistetaan muutos tai sitten aloitus nappia painamalla. Metodissa             tervehditään käyttäjää ja hadlerin avulla muutetaan näkymä käyttönäkymäksi tervehdyksen jälkeen.
- compareEquivalance()
  - Metodissa verrataan kahta kuvaa keskenään pixeli pixeliltä ja palautetaan float arvo kuvien eroavaisuudesta.
- openCamera()
  - openCamera() avaa kameran käyttöä varten. Kamera avautuu mutta ei tule näkyviin näytölle.
- tunnistus()
  - tunnistus() metodi käy läpi kaikki avainsanat jotka se hakee string.xml tiedostosta. Avainsanoja verrataan "word" stringiin johon kuultu puhe on tallennettu.      Jos jokin avain sana vastaa kuuluta muuttuu tässä vertailussa oleva bolean true:ksi. Sen jälkeen metodi käy läpi boleanit mikä on muuttunut true:ksi. Jos jokin      on muuttunut true:ksi toteutetaan sen alla oleva toiminto. Jos ei niin lopussa on ilmoitus että sovellus ei ymmärtänyt. Viimeisenä on vielä laskuri joka laskee      montako kertaa sovellus ei ymmärrä ja lopettaa kuuntelun jos tarpeeksi monta kertaa tulee puhetta jota sovellus ei ymmärrä.
- vertaa()
  - vertaa() metodi toteuttaa toiminnan. Metodissa on (else if) lausekkeita boolean arvoille. Mikäli aikasemmin on jokin boolean muuttunut true:ksi, (napilla           suoraan muutettu, puheen tunnistuksella avain sana vastannut kuultua puhetta). Toiminta löytyy jokaiselle eri ala-kategorialle jossa määritellää mitkä sijainnit     näytetään kartalla ja mikä puhe haetaan string.xml tiedostosta.
- restart()
  - restart() metodin avulla toteutetaan uudelleen käynnistys sovellukselle. Metodi haetaan napilla, tai sitten jos sovellus on ollut tarpeeksi kauan käyttämättä,       tai sitten jos sovellus on nukkumis tilassa ottanut tarpeeksi monta kuvaa. Metodissa käytetää valmista recreate() metodia joka sulkee aktiviteetin ja käynnistää     sen automaattisesti uudestaan. 
       Kokeilussa oli myös (" Intent intent = getIntent()
                              finish();
                              startActivity(getIntent()); ")
       Tämä tyyli ei kuitenkaan suostunut toimimaan jos sovellus oli lukittu tabletin näytölle!
   Uudelleen aloituksen lisäksi ennen lopetusta metodissa nollataan arvoja sekä pysäytetään kaikki timer:rit ja handler:it.
   Tämä sen vuoksi etteivät ne jää pyörimään ja uudelleen käynnistyksen jälkeen aloita uusia jolloin olisi tupla timer pyörimässä samaan aikaan.
- onPostResume()
- onPause()
- checkPermission()
  - Metodi tarkistaa onko sovellukselle annettu oikeudet äänen tallennukseen.
- onRequestPermissionsResult()
- showLanguageDialog()
  - Metodissa luodaan popup dialog joka haetaan kielen vaihto napilla. Popup luodaan AlertDialog metodilla ja siihen laitetaan kahden stingin lista (englanti ja         suomi). Listasta luodaan yksittäin valittavia objekteja ja lisätään clikcListener. Halutusta vaihtoehdosta vaihtaa setLocale arvon haluttuun kieleen.
- setLocale()
  - Popup dialogista saatu arvo muutetaan käytettävään muotoon.
- loadLocale()
  - loadLocale metodia käytetään hakemaan valittua kieltä. Metodi käsittelee mikä kieli on viimeksi valittu ja palauttaa sen kutsuttaessa. Metodia haetaan puheen       tunnistuksessa sekä puheen tuottamisessa jotta molemmat toimii valitulla kielellä.
- stopBackroundTread()
- startBackgroundThread()

**Database:**

Sovelluksessa hyödynnetään Firebase Databasea tiedonkeruuseen. Databaseen tallennetaan aloita() metodin haut sekä kaikki kuunnellut puheet mitä sovellus      kuuntelee.
Databasessa on kaksi id:tä johon tietoa tallennetaan. text ja aloitettu. text id:n taakse tallennetaan kaikki puhe mitä sovellus kuulee. aloitettu id:n taakse tallennetaan aikaleima joka kerta kun aloita() metodi haetaan.
Uuden Firebase databasen saa lisättyä Android studion avulla helposti. Valitse yläpalkista "tools" ja sen valikosta "Firebase". Tämä avaa Firebase consolin. Valitse sieltä "realtime database", koska tämä on käytössä. Sen jälkeen valitse "get started wiht realtime database", josta saat Android studion omat ohjeet databasen käyttöön. Mitään koodia ei tarvitse vaihtaa mikäli uudessa databasessa on myös id:t "aloitettu" aktivoinneille sekä "text" puheentunnistukselle. Consolista Databasen yhdistäminen tekee automaattisesti kaiken tarvittavat muutokset ohjelmaan jotta database tulee oikein yhdistettyä. 

**Avainsanojen vertaaminen:**

Vertaamisessa hyödynnetään "contains()" metodia jolla pystytään tarkistamaan löytyykö verrattavasta stringistä yhtäläisyys metodille annettuun stringiin.
Avainsanat on luotu xml.tiedostoon josta niitä haetaan. Kaikki avainsanat käydään puheentunnistuksen jälkeen läpi.
Jokaiselle vertailulle on osoitettu oma boolean arvo joka muutetaan vastaavuuden löydyttyä true:ksi.
Kun kaikki avainsanat on käyty läpi käydään boolean arvot läpi if lauseella ja katsotaan onko joku true.
Useampi boolean voi olla true mutta boolean vertailussa toteutuu vain ensimmäinen lause joka on true. Tämän vuoksi vertailussa pitää katsoa järjestystä niin että monimutkasimmat avainsanan booleanit tulee ensin jotta jos käyttäjä etsii niitä niin ne toteutuu. Yksinkertasimmat avainsana booleanit voi laittaa vertailussa viimeisiksi jotta jos ennenn niitä ei löydy vastausta niin ne toteutuu. Esim. "maa" merkkijono esiintyy monessa eri sanassa ja tämän vuoksi sen pitää olla vertailun viimesiten joukossa. Ei ole hyvä että käyttäjän etsiessä "riskosromaanit" avainsanalla ja ohjelma ohjaisi "maa" avainsanan kategoriaan.

**Animaatiot**

Animaatiot on luotu sovellukseen Gif tiedostoina. Sovelluksessa on luotu kaksi "pl.droidsonroids.gif.GifImageView" sijaintia robotti figuurille. Nukkumistilalle sekä aktivoitu tilalle omansa. Näihin vaihdetaan tilanteen mukaan aina sopiva kuva tai gif animaatio. Esim. jos sovellus tuottaa puhetta vaihdetaan puhuvarobo gif animaation näkyville puheen ajaksi.

**Sovelluksen palautus nukkumistilaan automaattisesti:**

Sovellus on käytön alussa nukkumistilassa ja se palaa automaattisesti takaisin siihen käyttämättömänä. 
Tämä on oteutettu kahdella eri metodilla. Sovelluksessa on luotu restart() metodi joka käynnistää sovelluksen haettaessa uudelleen.
Sovellukseen on luotu lopetus() metodi jolla saadaan sovellus käynnistymään itse käyttämättömänä uudelleen.
lopetus() metodi haetaan enimmäisen kerran kun sovellus aktivoituu joko tunnistuksen tai napin painamisen yhteydessä. Olemassa oleva int I on tässä vaiheessa = 0.
Kun lopetus() metodi haetaan ja I on 0, on metodissa lauseke jolla saadaan käynnistettyä timer joka lisää asetetun ajan välein int I:lle yhden arvon lisää. Timeriin on asetettu lauseke jossa kun int I saavuttaa tietyn arvon hakee se restart() metodin jotta sovellus käynnistyy uudelleen. Tämä mahdollistaa että aktivoinnin jälkeen sovellus käynnistyy itse tietyn ajan kuluttua uudelleen. 
lopetus() metodi haetaan joka kerta kun käyttäjä koskee laitteeseen tai laite kuuntelee käyttäjää. lopetus() metodiin on tehty myös lauseke jos int I on jokin muu kuin 0, muuttaa se int I arvon = 1. Tällä toiminnolla saadaan aikaiseksi että kun metodi haetaan kun käyttäjä käyttää sovellusta ja timer on jo käynnissä muutetaan int I arvo mahdollisimman pieneksi jotta uudelleen käynnistykseen kuluva aika nollaantuu. Int I:tä ei voida muuttaa = 0, koska muuten timer voisi käynnistyä useamman kerran ja niitä olisi enemmän kuin yksi päällekkäin päällä. Timer on käynnissä koko sovelluksen aktiivisen käytön ajan kunnes sovellus käynnistyy uudelleen ja timer suljetaan.

**Automaattinen aktivointi kameran avulla:**

Tässä vaiheessa kameran tunnistus ominaisuus on luotu kahta kuvaa vertailemalla. Tähän on olemassa muitakin tapoja tehdä esim. Firebase ML kit face recognition palvelun avulla.
Tässä versiossa on tunnistukseen on käytetty laitteen kameraan ja se avataan näkymättömästi openCamera() metodilla. 
onCreate() metodissa sovellusken käynnistyttä heti alussa luodaan timer joka on käynnissä niin kauan ennen kuin sovellus aktivoidaan aloita() metodilla. Timerissa on asetettu aikaväli jonka välein se hakee takePicture() metodin jossa otetaan valokuva. Valokuvan ottamisen jälkeen takePicture() metodissa valokuva tallenetaan vuoron perään kahteen eri bitmap:iin. Kuvaa tallentamisen jälkeen pienennetään suuresti vertailun helpoittamiseksi. Kuvan käsittelyn jälkeen takePicture() metodissa on if lauseke jossa haetaan compareEquivalance() metodi joka palauttaa kahden otetun kuvan vertailussa havaitun eron float arvona. Jos ero on tarpeeksi suuri toteutuu if lauseke ja siellä muutetaan boolean ihminen = true.
compareEquivalance() metodissa käytetään for looppia jossa verrataan kuvien pikseleitä toisiinsa yksi kerrallaan. Jokaisesta yhteneväisyydestä lisätään laskuriin int count yksi arvo. Lopuksi int count jaetaan kuvan koolla jolloin saadaan eroavaisuudelle arvo joka palautetaan. 
Kun timeri palaa uudelle kierokselle on siellä kaksi lauseketta. Boolean ihminen on joko true tai false. Jos se on false hakee timer takePicture() metodin uudelleen. Jos se on true hakee timer aloita metodin jotta sovellus aktivoituu ja timer suljetaan. 
Timeriin on myös asetettu laskuri joka laskee kuinka monta kuvaa on otettu. Jos laskuri saa tietyn arvon hakee se restart metodin ja sovellus käynnistyy uudelleen. Tällä pyritään ettei sovellus ole liian pitkiä aikoja käynnissä ilman uudelleen käynnistymistä.
Valkouvia tallennetaan aina vuorotellen kahteen eri bitmappiin. Jos bitmabissa on jo kuva se korvataan uudella. Valokuvat eivät tallennu pysyvästi missään vaihessa ja ne katoavat sovelluksen uudelleen käynnistyksessä tai sitten ku se korvataan uudella kuvalla.

**Jatkokehitys ideoita:**
- Alku speaking skippaus nappi.
- Firebase ML kit face recognition.
- Tietokannan kehitys
- Sijainnit tarkemmaksi. Millä puolella hyllyä sijainti on ja usempi sijainti hyllyyn.
- Puheohjauksella haettaessa avautuisi myös haetun kategorian yläkategorian muutkin alakategoria napit.
- Puheohjauksella aktivoida kuuntelu
- Teksti ohjaus "klikkaa minua kysyäksesi puheohjauksella".
- Avainsanojen parantelu sekä kehittäminen.
- Nimi ja kirja haku
- Ylläpitokäyttöliittymä
- Ehdotuksia käyttäjälle
- Yleisesti ohjelmakoodin rakenteen tarkastelu

Avainsanoja joita ei ole ohjelmassa mutta voisi olla.
- **Romaanit** Tällä hetkellä ei voi etsiä romaani avainsanalla.
- **Teologinen** Asiakas etsi tätä mutta pitää selvittää onko kyseisiä kirjoja miten ja järkevää lisätä.


**Sovelluksen käyttöönotto uudesa ympäristössä**

Mikäli kyseisen sovelluksen haluaa ottaa käyttöön uudessa liikkeessä tai ymäristössä tulee varautua suureen määrään ohjelmakoodin sisällön luontia ja muokkaamista.
Soovellukseen tulee vaihtaa uutta tilannetta vaativa pohjapiirrustus kartta. Kartan saa vaihdettua activity_main.xml tiedostosta jossa kartta imageView luodaan.
Samassa tiedostossa voi vahtaa uuden logon sovelluksen vasempaan yläreunaan.
Työläin osuus on luoda sovellukselle tarvittavat yläkategoriat ja alakategoriat.
Ilman yllapitokäyttöliittymää jota ei vielä ole, pitää kaikki kategoriat ja niiden avainsanat sekä sijainnit lisätä ohjelmakoodiin.

Yläkategorian luonti
- Luo kategorialle nappi. activity_main.xml
- Tuo nappi näkyviin main java tiedostossa ja uo napille listeneri.
- Luo string.xml tiedostoissa napin nimi sekä puhe joka tuotetaan nappia painessa.(Kaikille kielille oma)

Alakategorian luonti
- Luo kategorialle nappi. Sijoita myös nappi oikeaan kohtaan. activity_main.xml
- Tuo nappi näkyviin main java tiedostossa ja uo napille listeneri.
- Luo string.xml tiedostoissa napin nimi sekä puhe joka tuotetaan kategorian toiminnassa.(Kaikille kielille oma)
- Luo kategorialle oma markkeri ja sijoita se kartalla oikeaan kohtaan. Markeri on oletuksena INVISIBLE. activity_main.xml
- Luo kategorialle pää boolean.
- Luo kategorialle avainsanojen verran avainsana booleaneja.
- Luo avainsana booleanien verran avainsanoja kategorialle. string.xml(Joka kielelle omat)
- Tunnistus() metodissa luodaan jokaiseelle avainsana booleanille contains lauseke jossa haetaan yksittäin avainsanoja string.xml tiedostosta ja verrataan niitä        kuultuun puheeseen. Jos avainsanoja on luotu esim 6 kpl niin lauseke luodaan 6 kertaan jokaisella oma boolean jokaista eri avainsanaa kohti.
runot0 = word.contains(getString(R.string.runo0)); runot0 on luotu boolean (oletuksena false). word on puheen tunnistuksena kuultu puhe. runo0 on id string.xml tiedostossa olevalle avainsanalle. käytetään contains metodia vertaamaan haettua avainsanaa kuultuun puheeseen. Mikäli vertailussa on yhteneväisyys muuttuu runot0 boolean true:ksi.
- Luo Tunnistus() metodissa vertailu kategorian avainsanoille että onko joku niistä true. Jos on muuta kategorian pää boolean true:ksi. 

else if (runot0){ 
  
  runo = true;        //Runo kategorian pää boolean true.
  
  laskuri = 0;        //Kuuntelu laskuri nollataan kun löydetään vastaus kysymykseen.
  
  vertaa() }          //Haetaan seuraava metodi.

- Vertaa() metodiin luodaan kategorialle oma else if() joka toteutuu jos kategorian pää boolean on true. Lausekkeen sisällä tuotetaan kategorian puhe ja animaatiot. Sen lisäksi otetaan kyseisen kategorian sijainti näkyviin.
