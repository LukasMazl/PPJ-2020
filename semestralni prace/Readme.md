# Semestrální práce z PPJ 2020

Aplikace shromažduje data z Weather api a umožnuje uživateli tyto data prohlížet, editovat 
a dále s nimi pracovat přes REST API.

Aby bylo možné město přidat do sledování, tak je potřeba, aby byla založena země do které bude patřit.
Pokud je země založena, poté je koumikováno na Weather api, ze kterého je získána informace,
jestli toto město existuje. Pokud neexstistuje, Weather api vrací 404 a aplikace
to prezentuje jako 'ServiceException()'.

## Komunikace na REST api
Pro jednoduší testování rest api jsem vytvořil projekt v Restlet clientovi, který je možný si naimportovat. 
JSON pro import je uložený v složce doc/ 

## Popis jobů
Aplikace využívá joby pro průběžnou aktualizaci dat a pro hlídání expirace záznamů v mondodb

### Job OldDataRemover 
Zdůvodů testovatelnosti je interval tohoto jobu nastaven na 1000ms. Job se spustí a smaže staré záznamy. Doba záznamu je konfigurovatlná přes properties

```properties
#Inteval (miliseconds) for deleting old records
cz.mazl.tul.job.intervatDeleteExpiration=3600
#Expiration days
cz.mazl.tul.job.deleteExpiration=14
```

### Job UpdateTemperature
Tento job průběžně aktualizuje a stahuje záznamy o aktuální teplotách. Po stažení jsou záznamy uloženy do mongoDB a u entity města, u kterého probíhal update je aktualizování proměnná lastUpdate. Podle této hodnoty datumu jsou brány ostatní města. Weather REST API má limitovaný počet možných requestů, proto je možné tento job upravovat pomocí properties. 

```properties
#Inteval (miliseconds) for updating of city weathers
cz.mazl.tul.job.updateInterval=10000
#Max number of cities whose weather stats will be updated
cz.mazl.tul.job.updateBatch=60
```
    
## Read-only mode
Aplikaci je možné spustit v read-only modu pomocí aktivování spring profilu ,,read-only"

```cmd
java -Dspring.profiles.active=read-only -jar ppj-2020-1.0-SNAPSHOT.war
```

## Důležité odkazy
Url pro přístup do aplikace

    http://localhost:8080/
   nebo
    
    http://localhost:8080/index
    
Swagger api pro dokumentaci REST api

    http://localhost:8080/swagger-ui.html
    
Odkaz pro export dat do formátu csv

    http://localhost:8080/download/{iso_code}/{nazev_mesta}
    
    
