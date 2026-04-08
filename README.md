Green Code

Pourquoi le Green Code ?

Le numérique représente une part croissante de la consommation énergétique mondiale. Le Green Code vise à réduire l'empreinte environnementale des applications en adoptant des pratiques de développement plus sobres : moins de données transférées, moins de calculs inutiles, moins de ressources consommées côté infrastructure.

Ce qui a déjà été fait

Les Dockerfiles utilisent une image de base eclipse-temurin:21-jre-alpine plutôt qu'une image JDK complète, beaucoup plus légère (~85 MB contre ~450 MB), ce qui réduit le stockage et le temps de téléchargement. Les versions des images Docker sont également épinglées (mysql:8, mongo:6) pour éviter des téléchargements inutiles à chaque redémarrage.



Pistes d'amélioration

Pagination : getAllPatients() utilise findAll() qui charge tous les patients en mémoire. Dans un contexte médical où le nombre de patients peut croître rapidement, remplacer par une pagination réduirait la consommation mémoire.

Cache : L'assessment déclenche deux appels REST à chaque chargement de page. Un cache (@Cacheable) éviterait des calculs et des appels réseau redondants.

Limites Docker : Aucune limite mémoire/CPU n'est définie dans le docker-compose.yml. Ajouter des contraintes éviterait qu'un service monopolise les ressources au détriment des autres.
