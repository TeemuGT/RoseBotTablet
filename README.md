# RoseBotTablet
Tabletti sovellus kehitetty Samsung galaxy tab 8  tabletille.
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

Database:
  Sovelluksessa hyödynnetään Firebase Databasea tiedonkeruuseen. Databaseen tallennetaan aloita() metodin haut sekä kaikki kuunnellut puheet mitä sovellus      kuuntelee.
  Databasessa on kaksi id:tä johon tietoa tallennetaan. text ja aloitettu. text id:n taakse tallennetaan kaikki puhe mitä sovellus kuulee. aloitettu id:n taakse tallennetaan aikaleima joka kerta kun aloita() metodi haetaan.

Jatkokehitys ideoita:
- Alku speaking skippaus nappi.
- Firebase ML kit face recognition.
- Sijainnit tarkemmaksi. Millä puolella hyllyä sijainti on ja usempi sijainti hyllyyn.
- Puheohjauksella haettaessa avautuisi myös haetun kategorian yläkategorian muutkin alakategoria napit.
- Teksti ohjaus "klikkaa minua kysyäksesi puheohjauksella".
- Avainsanojen parantelu sekä kehittäminen.

Avainsanoja joita ei ole ohjelmassa mutta voisi olla.
- **Romaanit** Tällä hetkellä ei voi etsiä romaani avainsanalla.
- **Teologinen** Asiakas etsi tätä mutta pitää selvittää onko kyseisiä kirjoja miten ja järkevää lisätä.
