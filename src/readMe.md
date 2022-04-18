Les données :
- J'ai modifié certaines lignes qui n'étaient pas dans le même format que les autres : il 
manquait un guillemet que j'ai donc rajouté pour permettre la récupération automatique des 
données.
- Certaines chansons sont en double (même chanson mais pas même ID), j'ai décidé de les considérer
comme des chansons différentes, car étant un cas très rare (je n'ai trouvé qu'un seul cas), je
ne voulais faire d'opérations superflues qui pourraient ralentir le programme.

Le projet:

Etape 1:
- J'ai utilisé le thread Timer pour implémenter le compte à rebours, pour une manipulation
plus simple par rapport à un thread que j'aurais créé moi même de toute pièce.

Etape 2 :
- Si la liste filtrée ne comprends qu'une seule chanson, alors celle-ci se jouera en boucle.

Etape 3:
- Lorsqu'on appuie sur le bouton play, la chanson se lance, et pourra être en pause en appuyant 
sur le bouton pause.
- Si le bouton suivant est appuyé pendant que la chanson est en cours de lecture, alors la 
chanson suivante se lancera automatiquement. Pareil pour le bouton précédent.
- Si le bouton suivant est appuyé alors que la chanson est sur pause ou n'a pas été lancée,
alors la chanson suivante ne se lancera pas automatiquement, il faudra appuyer sur play pour 
la jouer. Pareil pour le bouton précédent.
- Le bouton suivant est désactivé tant que le thread n'a pas trouvé la prochaine chanson.



Général:
- Si la liste filtrée comprends au maximum 5 chansons, les chansons vont être jouée 
aléatoirement sans prendre en compte celles qui ont déjà été écoutées. Cependant, on ne peut pas
avoir deux fois de suite la même chansons. J'ai préféré garder une lecture aléatoire plutôt que 
de prioriser la musique qui a été écoutée le moins récemment.
- Si la liste filtrée comprends plus de 5 chansons, une chanson déjà jouée sera accessible 
seulement si 5 autres chansons ont été jouées ensuite. 

