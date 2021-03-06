# Dag 1
Gewerkt aan het opzetten van de Readme en het uitdenken van het concept en de MVC, met daarbij mogelijke uitbreidingen indien
de tijd dit toelaat.

# Dag 2
Project aangemaakt en de volledige Git opgezet. Verder gewerkt aan het Design Document en dus nagedacht over de classes en 
hoe de database eruit moet zien. Verder ook een opzet gemaakt voor het inloggen en aanmelden en FireBase geïmplementeerd.

# Dag 3
Design Document volledig gemaakt en veel basis classes aangemaakt, zodat er een duidelijk overzicht was. Verder het inloggen
en aanmelden voorlopig afgemaakt met daarbij het creëeren van een profiel en daarbij via de Google API de locatie in te laden.
Hiermee werkt het standaard log in en aanmeld systeem, waarbij fouten nog niet worden afgevangen in bijvoorbeeld API requests.

# Dag 4
Gewerkt aan het vinden van een andere user, waarbij er gekeken wordt naar de criteria op locatie na. Lang een probleem gehad met
de asycnhrone FireBase calls en het verwerken van de resultaten, op het moment dat ze gevonden zijn. Uiteindelijk middels een
Runnable de oplossing weten te vinden, waardoor je nu een andere user kan vinden. Verder ook het bottom menu standaard gemaakt 
waardoor je gemakkelijk de tabs onderin kan inladen bij elke activity, wat veel tijd kan schelen in de toekomst.

# Dag 5

Probleem gehad met een asynchrone call binnen nog een asynchrone call, uiteindelijk opgelost door één van de calls te verwerken in de andere zodat er maar één call is en je geen problemen krijgt met het resultaat verwerken. De voorwaarde voor het zoeken worden nu goed behandeld. Verder opzet gemaakt voor de chats en users kunnen elkaar nu toevoegen en chats beginnen. Chat zelf nog niet geïmplementeerd.

# Dag 6

Werkende realtime app geïmplementeerd tussen twee users. De userIds nog niet geconvert naar de namen die daar bij horen. Verder het profiel aanmaken uitgebreid met leeftijd, geslacht en een beschrijving zodat er een uitgebreider profiel geschetst wordt. Daarnaast geïmplementeerd dat er meer users gevonden worden met de search again knop, door een lijst te creëeren tijdens de eerste search en die lijst af te lopen. Als de lijst leeg is, ga je weer terug naar het zoek scherm. Nog geen restricties gezet op de lengte van de lijst of het aantal users dat na gelopen wordt.

# Dag 7

Geimplementeerd dat over waar een user id staat nu de naam van de user zichtbaar wordt. Hierbij ervoor gekozen om bij de listview een hidden text field mee te geven met daarin het Id zodat deze gepassed worden naar de volgende activiteit en ik dus niet de hele structuur hoef om te gooien. Dit gemaakt met custom list adapters zodat de list item meerdere elementen kan bevatten. Al het hiervoor genoemde ook toegepast op de chat zodat deze duidelijk is en gepersonaliseert voor elke user.

# Dag 8

Parameters toegevoegd aan de zoekfunctie, waarbij je nu dus kan zoeken op gelijk geslacht en een radius kan aangeven voor de leeftijd van de user die hij zoekt. Edit Profile knop toegevoegd en uitgewerkt zodat je het profiel kan aanpassen. Hierbij eerst gekeken naar een dailog, maar uiteindelijk gekozen voor een nieuwe activity, aangezien er veel waardes aangepast kunnen worden. Ervoor gekozen om edit text te gebruiken te vullen met de huidige informatie en na het editten dus alles opnieuw in firebase te zetten.

# Dag 9

Begonnen met de opzet van het creëeren en zoeken van schemes. Activiteiten aangemaakt hiervoor en het basis scherm van de schemes toegevoegd. Verder het aanmaken van schemes gemaakt en het zoeken geïmplementeerd. Het probleem hierbij was dat Firebase geen SQL LIKE queries toestaat, waardoor een echte zoek functie niet mogelijk is. Als oplossing hiervoor ervoor gekozen om keywords toe te voegen en op basis van het kiezen van keywords de schemes terug kan vinden. Zoekfunctie werkt op deze manier en je kan redelijk specifiek filteren op het soort scheme wat je wilt vinden.

# Dag 10

Het editten van schemes toegevoegd waarbij ik voor dezelfde flow als het edit profile gekozen heb, dus via een nieuwe activity opnieuw de data in de firebase zetten. Verder bij elke scheme toegevoegd dat je kan raten indien het scheme niet van jou is. Dit gebeurt via een star rating, waarbij in firebase wordt bijgehouden wat de rating is, en bij elke nieuwe rating het opnieuw berekend wordt.
