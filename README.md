# Yövuororankaisija

Yritetään tutkia onko vuorolistan yövuorot sijoitettu huonosti

### Säännöt

- Yövuoroja voi olla peräkkäin 1 – 5. 
- Jokaisen yövuoron jälkeen on tai ei ole nk. nukkumapäivä, jolloin siis ei ole töitä vaan se on varattu nukkumiseen. 
- Nukkumapäivän jälkeen on joko kiinteä määrä vapaapäiviä (1 – 2) tai yövuorojen määrästä riippuva määrä vapaapäiviä. 
- Yövuoroja ennen voi olla mitä tahansa vuoroja tai vapaapäiviä.


### Sääntöesimerkkejä:

- yövuorojen jälkeen oltava nukkumapäivä + vähintään 2 vapaapäivää
- yövuorojen jälkeen oltava nukkumapäivä + vähintään 1 vapaapäivä
- yövuorojen jälkeen oltava vähintään 2 vapaapäivää
- yövuorojen jälkeen oltava vähintään 1 vapaapäivä
- n:n yövuoron jälkeen oltava vähintään n vapaapäivää
- n:n yövuoron jälkeen oltava vähintään n-1 vapaapäivää
- n:n yövuoron jälkeen oltava vähintään n-2 vapaapäivää


### Esimerkkituloste:

Käytetään Finnairin 1979-vuoden tyovuoromallia

```
kuinkaHuonoLista =[m, m, m, m, m, v, v, y, y, y, y, y, v, m, m, m, m, m, v, v, m, m, m, m, m, v, v]
Tutkitaan ( 1. ) Yo-putki:
! n:n yövuoron jälkeen oltava vähintään n vapaapäivää = 4 sakkoa
! yövuoron jälkeen oltava vähintään n-1 vapaapäivää = 4 sakkoa
! n:n yövuoron jälkeen oltava vähintään n-2 vapaapäivää = 4 sakkoa
! yövuorojen jälkeen oltava vähintään 2 vapaapäivää = 5 sakkoa
! yövuorojen jälkeen oltava nukkumapäivä + vähintään 2 vapaapäivää = 5 sakkoa
Putkessa Yovuorot: 5 Nukkumapaivat: 0 Vapaat: 1
YokasittelynTulos= putki: [y, y, y, y, y, v] putkenSakot: 22

Yövuoroputkia löydetty = 1 kpl
>>> Sakkoa yhteensä = 22 <<<<
```

Laskentaa ja painoarvoja voi säätää muuttamalla fi.nukkujat.Cont -luokasta löytyviä muuttujia.