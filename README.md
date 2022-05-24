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
- Lopetus()
- takePicture()
- aloita()
- compareEquivalance
- openCamera()
- tunnistus()
- vertaa()
- restart()
- onPostResume()
- onPause()
- checkPermission()
- onRequestPermissionsResult()
- showLanguageDialog()
- setLocale()
- stopBackroundTread()
- startBackgroundThread()
