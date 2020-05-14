#Semestrální práce z PPJ 2020
Aplikace shromažduje data z Weather api a umožnuje uživateli tyto data prohlížet, editovat 
a dále s nimi pracovat přes REST API.

Aby bylo možné město přidat do sledování, tak je potřeba, aby byla založena země do které bude patřit.
Pokud je země založena, poté je koumikováno na Weather api, ze kterého je získána informace,
jestli toto město existuje. Pokud neexstistuje, Weather api vrací 404 a aplikace
to prezentuje jako 'ServiceException()'.

##Komunikace na REST api
Pro jednoduší testování rest api jsem vytvořil projekt v Restlet clientovi, který je možný si naimportovat. 
JSON pro import je uložený v složce doc/ 

##Read-only mode
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
    
    
