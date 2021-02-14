# Yövuororankaisija

Yritetään tutkia onko vuorolistan yövuorot sijoitettu huonosti ja työntekijän toivomukset otettu huomioon.

### Säännöt

##### Yövuorosäännöt
- Yövuoroja voi olla peräkkäin 1 – 5. 
- Jokaisen yövuoron jälkeen on tai ei ole nk. nukkumapäivä, jolloin siis ei ole töitä vaan se on varattu nukkumiseen. 
- Nukkumapäivän jälkeen on joko kiinteä määrä vapaapäiviä (1 – 2) tai yövuorojen määrästä riippuva määrä vapaapäiviä. 
- Yövuoroja ennen voi olla mitä tahansa vuoroja tai vapaapäiviä.
##### Toivomussäännöt
- Työntekijä voi toivoa työvuoroja tai vapaapäiviä priorisoituna tai normaalina
- Priorisoidut pitäisi ehdottomasti toteuttaa
- Vuorotyyppejä voi olla 3-n, kuitenkin maksimissaan 30
- Työryhmiä voi olla 3-6

### Sääntöesimerkkejä:
##### Yövuorosääntöesimerkkejä
- yövuorojen jälkeen oltava nukkumapäivä + vähintään 2 vapaapäivää
- yövuorojen jälkeen oltava nukkumapäivä + vähintään 1 vapaapäivä
- yövuorojen jälkeen oltava vähintään 2 vapaapäivää
- yövuorojen jälkeen oltava vähintään 1 vapaapäivä
- n:n yövuoron jälkeen oltava vähintään n vapaapäivää
- n:n yövuoron jälkeen oltava vähintään n-1 vapaapäivää
- n:n yövuoron jälkeen oltava vähintään n-2 vapaapäivää
##### Toivomussääntöesimerkkejä
- priorisoitu vapaapäivätoive eli toive pitää ehdottomasti toteuttaa
- normaali vapaapäivätoive eli olisi kiva, jos toive toteutuisi
- priorisoitu työvuorotoive eli toive pitää ehdottomasti toteuttaa
- normaali työvuorotoive eli olisi kiva, jos toive toteutuisi

### Esimerkkituloste:

#####Käytetään Finnairin 1979-vuoden tyovuoromallia:

```
kuinkaHuonoLista =[m, m, m, m, m, v, v, y, y, y, y, y, v, m, m, m, m, m, v, v, m, m, m, m, m, v, v]
Tutkitaan ( 1. ) Yo-putki:
! n:n yövuoron jälkeen oltava vähintään n vapaapäivää = 4 sakkoa
! yövuoron jälkeen oltava vähintään n-1 vapaapäivää = 4 sakkoa
! n:n yövuoron jälkeen oltava vähintään n-2 vapaapäivää = 4 sakkoa
! yövuorojen jälkeen oltava vähintään 2 vapaapäivää = 5 sakkoa
! yövuorojen jälkeen oltava nukkumapäivä + vähintään 2 vapaapäivää = 5 sakkoa
Putkessa Yovuorot: 5 Nukkumapaivat: 0 Vapaat: 1
fi.nukkujat.YokasittelynTulos= putki: [y, y, y, y, y, v] putkenSakot: 22

Yövuoroputkia löydetty = 1 kpl
>>> Sakkoa yhteensä = 22 <<<<
```

#####Tarkastellessa yhä Vuoro ja fi.nukkujat.Toive -listaa:
Esimerkki tulkinnasta: "A5p+" = A vuoro, 5 ryhmä, prioriteetti, toive toteutettu
```
! vuoropyyntöön ei vastatu = 1 sakkoa (q != v)
! vuoropyyntöön ei vastatu = 1 sakkoa (a != v)
Toivesakot: 2
Prioriteetteja rikottu: 0
Vuorot =[A4  , P2  , Q3  , I4  , Y3  , Y4  , N   , V   , V   , A   ]
Toiveet=[A4 +, P  +, V   , I  +, Y3 +, Y  +, N  +, V  +, V  +, V   ]
--Yösakot(harjoitus1): 0 
--Toivesakot(harjoitus2): 2
```
### Ohje:
Laskentaa ja painoarvoja voi säätää muuttamalla fi.nukkujat.Cont -luokasta löytyviä muuttujia.

- Tee sekä työvuoro, että toivomuslista csv muodossa.
- Lisää ne resource kansioon ja korvaa siellä olevat vanhat tiedostot.
- Ohjelma palauttaa lukuarvoina tiedon kuinka hyvin työvoron yövuorot ja työntekijän toivomukset on sijoiteltu.

#####FAQ:
Is a CSV file a text file?

"A comma-separated values (CSV) file is a delimited text file that uses a comma to separate values." -rfc4180

addedjenkinstest
