\echo "Requêtes de l'exercice 1 :"

\echo '\n'
\echo "Nombre de formations gérés par ParcourSup :"
-- (a)
SELECT COUNT(DISTINCT n110) FROM import;

\echo '\n'
\echo "Nombre d'établissement géré par ParcourSup :"
-- (b)
SELECT COUNT(DISTINCT n4) FROM import;

\echo '\n'
\echo "Nombre de formations pour l'Université de Lille :"
-- (c)
SELECT COUNT(n110) FROM import WHERE n4 = 'Université de Lille';

\echo '\n'
\echo "Nombre de fomations dans notre IUT :"
-- (d)
SELECT COUNT(n110) FROM import WHERE n4 = 'Institut universitaire de technologie de Lille - Université de Lille';

\echo '\n'
\echo "Code du BUT Informatique de l'Université de Lille :"
-- (e)
SELECT n110 FROM import WHERE n4 = 'Institut universitaire de technologie de Lille - Université de Lille' AND n10 = 'BUT - Informatique';

\echo '\n'
\echo "Fin des requêtes de l'exercice 1 et début de celles de l'exercice 3"


\echo "Requêtes de l'exercice 3 :"

--question 1:
\echo '\n'
\echo "question 1 : \n"
\echo "Ici on affiche l'effectif des neo bacheliers avec la colonne de droite qui est calculée, pour pouvoir vérifier si les valeurs sont correctes."
Select n56, (n57+n58+n59) AS verif
FROM import
ORDER BY n56 DESC;

--question 2:
\echo '\n'
\echo "question 2 : \n"
\echo "Pour vérifier que c'est exact ici, on verifie que la colonne n56 soit bien égale à la somme des colonnes n57,n58,n56, si tout est correcte, l'affichage doit correspondres à des 't'."
Select n56=(n57+n58+n59) AS verif
FROM import
WHERE n56=(n57+n58+n59)
ORDER BY n56 DESC;

--questions 3:
\echo '\n'
\echo "question 3 : \n"
\echo "Ici on affiche le pourcentage d'admis ayant reçu leurs propositions d’admission à l'ouverture de la procédure principale."
SELECT n74, CASE WHEN n47 = 0 THEN 0 ELSE round((n51/n47)*100,1) END AS verif
FROM import;

--questions 4:
\echo '\n'
\echo "question 4 : \n"
\echo "Pour vérifier que les valeurs sont exactes, on ajoute une vérification sur n74."
SELECT n74, CASE WHEN n47 = 0 THEN 0 ELSE round((n51/n47)*100,1) END AS verif
FROM import
WHERE n74 != CASE WHEN n47 = 0 THEN 0 ELSE round((n51/n47)*100,1) END;

-- questions 5:
\echo '\n'
\echo "question 5 : \n"
\echo "Affiche le pourcentage d’admis ayant reçu leurs propositions d’admission avant la fin de la procédure principale et le calcul qui le vérifie. Cela est exact à partir de la première décimal."
SELECT n76, round((n53/n47)*100)
FROM import
WHERE n47 > 0;

-- questions 6:
\echo '\n'
\echo "question 6 : \n"
\echo "On refait la même chose que pour la question 5 mais avec nos tables ventilées."
SELECT Pourc_Admis_proposition_av_fin_proc_prin,round((Admis_ayant_prop_av_fin_proc_prin/Eff_Tot_Admis)*100)
FROM association
WHERE Eff_Tot_Admis > 0;

-- questions 7:
\echo '\n'
\echo "question 7 : \n"
\echo "On affiche le pourcentage d'admis néo bacheliers boursiers avec le calcul qui le vérifie. Cela est exact à partir de la première décimal."
SELECT n81,CASE WHEN n56 = 0 THEN 0 ELSE round(CAST(n55 AS NUMERIC(10,4))/(n56)*100,0)END
FROM import;


-- questions 8:
\echo '\n'
\echo "question 8 : \n"
\echo "Même chose que la question 7 mais avec nos tables ventilées."
SELECT Pourc_Admis_NB_boursiers, round(CAST(Eff_Admis_NB_boursiers AS NUMERIC(10,4))/(Admis_NB)*100,0)
FROM association WHERE Admis_NB > 0;



SELECT association.Pourc_Admis_NB_boursiers 
FROM association INNER JOIN formation ON association.Code_Formation = formation.Code_Formation
WHERE lower(Filiere) LIKE ANY (array['%but%', '%bts%', '%licence%']) AND Region = 'Normandie' OR Region = 'Pays de la Loire';