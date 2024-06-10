import extensions.File;
import extensions.CSVFile;

class Jeu extends Program{
    boolean quitter = false;
    int progression = 0;
    String pseudo = "";
    String logo = fileAsString("./image/logoJeu.txt");
    String manoir = fileAsString("./image/manoir.txt");
    String fantomeAngry = fileAsString("./image/fantomeAngry.txt");
    String fantomeGentil = fileAsString("./image/fantomeGentil.txt");

    void algorithm(){ 
        clearScreen();
        startGame();
    }
    
    void startGame(){
        menu();
        if(progression == 0){
            debut();   
        }
        while(progression <= 4){
                sauvegardePartieEnCours("./save/sauv.csv");
                salleActuel();
        }
        quitter();
    }

    void menu(){
        println(logo);
        boolean choixConfirmer = false;
        while(!choixConfirmer){
            println(logo);
            String choix = readString();
            if(equals(choix, "1")){
                clearScreen();
                genereSauvegarde("./save/sauv.csv");
                choixConfirmer = true;
            }else if(equals(choix, "2")){
                sauvegarde("./save/sauv.csv");
                choixConfirmer = true;
            }else if(equals(choix, "3")){
                progression = 5;
                quitter = true;
                choixConfirmer = true;
            }else if(equals(choix, "666")){
                clearScreen();
                println(fileAsString("./image/tetedemort.txt"));
                delay(666);
            }else{
                println("Veuillez entrer une valeur valide !");
            }
        }
    }
    
    void quitter(){
        println("");
    }

    void sauvegarde(String cheminFichier){
        CSVFile saves = loadCSV(cheminFichier);
        int nombreLignes = rowCount(saves);
        int nombreColonnes = columnCount(saves);
        String[][] contenu = new String[nombreLignes + 1][nombreColonnes];
        for(int idxLigne = 0; idxLigne <= nombreLignes; idxLigne += 1){
            if(idxLigne == nombreLignes){
                    println("Quel est votre pseudo ?");
                    pseudo = readString();
                    contenu[idxLigne][0] = pseudo;
                    String salle = "";
                    if(progression == 0){
                        salle = "Hall";
                    }else if(progression == 1){
                        salle = "Anglais";
                    }else if(progression == 2){
                        salle = "Physique";
                    }else if(progression == 3){
                        salle = "Histoire";
                    }else if(progression == 5){
                        salle = "End";
                    }
                    contenu[idxLigne][1] = salle;
            }else{
                for(int idxColonne = 0; idxColonne < nombreColonnes; idxColonne += 1){
                    contenu[idxLigne][idxColonne] = getCell(saves, idxLigne, idxColonne);
                }
            }
        }
        saveCSV(contenu, cheminFichier); 
        println("Sauvegarde faites.");
    }

    void sauvegardePartieEnCours(String cheminFichier){
        CSVFile saves = loadCSV(cheminFichier);
        int nombreLignes = rowCount(saves);
        int nombreColonnes = columnCount(saves);
        String[][] contenu = new String[nombreLignes][nombreColonnes];
        for(int idxLigne = 0; idxLigne < nombreLignes; idxLigne += 1){
            for(int idxColonne = 0; idxColonne < nombreColonnes; idxColonne += 1){
                contenu[idxLigne][idxColonne] = getCell(saves, idxLigne, idxColonne);
            }
        }
        for(int idxLigne = 0; idxLigne < nombreLignes; idxLigne += 1){
            if(equals(contenu[idxLigne][0], pseudo)){
                    String salle = "";
                    if(progression == 0){
                        salle = "Hall";
                    }else if(progression == 1){
                        salle = "Anglais";
                    }else if(progression == 2){
                        salle = "Physique";
                    }else if(progression == 3){
                        salle = "Histoire";
                    }else if(progression == 4){
                        salle = "End";
                    }
                    contenu[idxLigne][1] = salle;
            }
        }
        saveCSV(contenu, cheminFichier); 
        println("Sauvegarde faites.");
    }

    void genereSauvegarde(String cheminFichier){
        CSVFile saves = loadCSV(cheminFichier);
        int nombreLignes = rowCount(saves);
        int nombreColonnes = columnCount(saves);
        String[][] contenu = new String[nombreLignes][nombreColonnes];
        for(int idxLigne = 0; idxLigne < nombreLignes; idxLigne += 1){
            for(int idxColonne = 0; idxColonne < nombreColonnes; idxColonne += 1){
                contenu[idxLigne][idxColonne] = getCell(saves, idxLigne, idxColonne);
            }
        }
        println("Quel est votre pseudo ?");
        pseudo = readString();
        for(int idxLigne = 0; idxLigne < nombreLignes; idxLigne += 1){
            if(equals(contenu[idxLigne][0], pseudo)){
                String progress = contenu[idxLigne][1];
                if(equals(progress, "Hall")){
                    progression = 0;
                    println(progress);
                }else if(equals(progress, "Anglais")){
                    progression = 1;
                }else if(equals(progress, "Physique")){
                    progression = 2;
                }else if(equals(progress, "Histoire")){
                    progression = 3;
                }else if(equals(progress, "End")){
                    progression = 4;
                }
            }
        }
    }

    void debut(){
        println(manoir);
        println(fileAsString("./image/contexte.txt"));
        println("Suivant");
        if(!equals(readString(), null)){
            clearScreen();
            println(fantomeAngry);
            println("bouuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuh");
            println("Suivant");
            if(!equals(readString(), null)){
                clearScreen();
                println(fantomeGentil);
                println("et non je suis gentil en faite ahahah");
                println("Suivant");
                if(!equals(readString(), null)){
                    clearScreen();
                }
            }
        } 
    }

    void salleActuel(){
        if(progression == 0){
            hall();
        }else if(progression == 1){
            sauvegardePartieEnCours("./save/sauv.csv");
            anglais();
        }else if(progression == 2){
            sauvegardePartieEnCours("./save/sauv.csv");
            sciences();
        }else if(progression == 3){
            sauvegardePartieEnCours("./save/sauv.csv");
            histoire();
        }else if(progression == 4){
            sauvegardePartieEnCours("./save/sauv.csv");
            theEnd();
        }
    }

    void hall(){
        // Faire le texte d'entre dans le manoir (passage qui se referme);
        println(fantomeGentil);
        println("Vous êtes maintenant enfermé dans ce manoir et votre seul moyen d'en sortir est de réussir toute les épreuves");
        println("qui s'y trouvent, chaque salle sera sur le thème d'une matière scolaire ! Je sens que vous êtes plutôt doué donc vous");
        println("devrier y arriver assez facilement non ?");
        println("Suivant");
        if(!equals(readString(), null)){
            println("Après avoir écouté ce mystérieux fantôme vous expliquer comment faire pour s'échapper de cet endroit,");
            println("vous et vous amis décidaient de vous diriger vers la seul porte disponnible.");
            println("Vous entrez dans une sombre pièce, et au bout de quelques secondes, les lumières s'allument et");
            println("une personne s'y tiens en son centre.");
            println("Suivant");
            if(!equals(readString(), null)){
                progression += 1;
                clearScreen();
            }
        }
    }

    void anglais(){
        println(fileAsString("./image/monsieurGentil.txt"));
        println("Bienvenue dans la première salle de ce magnifique manoir !");
        println("Comme tu le sais déjà, chaque salle représente une matière, et celle-ci représente l'anglais !");
        println("Et c'est moi qui superviserai cette première épreuve. Commençons directement si tu es prêt");
        println("Suivant");
        int points = 0;
        if(!equals(readString(), null)){
            String[][] contenu = creationTableQuestion("./question/anglais.csv");
            String[][] questions = questionsAnglais(contenu);
            String question = "";
            String reponse = "";
            clearScreen();
            println("Vous devez traduire les mots donnés en Anglais !");
            for(int i = 0; i < length(questions); i += 1){
                question = questions[i][0];
                reponse = questions[i][1];
                println(fileAsString("./image/monsieurGentil.txt"));
                println("Traduisez : " + question + " : ");
                String reponseJoueur = readString();
                if(!equals(reponse, reponseJoueur)){
                    if(checkText(reponse, reponseJoueur)){
                        println("Bonne réponse, +1 point !");
                        points += 1;
                    }else{
                        println("Aie... Mauvaise réponse, la réponse était : " + reponse);
                    }
                }else{
                    println("Bonne réponse, +1 point !");
                    points += 1;
                }
                println("Question Suivante");
                if(!equals(readString(), null)){
                    clearScreen();
                }
            }
        }
        println("");
        if(points >= 10){
            println("Félicitations, vous avez réussi avec un total de " + points + " points !");
        }else{
            println("Tu as finis cette épreuve avec un total de.... " + points + " sur 20 ! Avoir la moyenne est si difficile que ça ?");
            println("Bon j'avoue, certains mots sont peu être à un assez haut niveau mais quand même..");
            println("je vais te laisser accéder à la salle suivante parce que je suis quelqu'un de bien, bon courage pour la suite !");
        }
        println("Suivant");
        if(!equals(readString(), null)){
            progression += 1;
        }
    }
    
    boolean checkText(String txt1, String txt2){
        boolean success = true;
        char caractTxt1;
        char caractTxt2;
        int rapport = (int)'a' - (int)'A';
        if(length(txt1) == length(txt2)){
            for(int i = 0; i < length(txt1); i ++){
                caractTxt1 = charAt(txt1, i);
                caractTxt2 = charAt(txt2, i);
                if(success){
                    if((int)caractTxt1 + rapport == (int)caractTxt2 || (int)caractTxt1 - rapport == (int)caractTxt2 || (int)caractTxt1 == (int)caractTxt2){
                        print("");
                    }else{
                        success = false;
                    }
                }
            }
        }else{
            success = false;
        }
        return success;
    }
    String[][] creationTableQuestion(String cheminFichier){
        CSVFile table = loadCSV(cheminFichier);
        int nombreLigne = rowCount(table);
        int nombreColonne = columnCount(table);
        String[][] contenuTable = new String[nombreLigne][nombreColonne];
        for(int idxLigne = 0; idxLigne < nombreLigne; idxLigne += 1){
            for(int idxColonne = 0; idxColonne < nombreColonne; idxColonne += 1){
                contenuTable[idxLigne][idxColonne] = getCell(table, idxLigne, idxColonne);
            }
        }
        return contenuTable;
    }

    String[][] questionsAnglais(String[][] contenueTable){
        String[][] questions = new String[20][2];
        for(int i = 0; i < 20; i += 1){
            int random = (int) (random() * 200);
            questions[i][0] = contenueTable[random][0];
            questions[i][1] = contenueTable[random][1];
        }
        return questions;
    }

    void sciences(){
        boolean reussi = false;
        int points = 0;
        String[] elements = new String[]{"Hydrogène", "Lithium", "Sodium", "Magnésium", "Potassium", "Radium", "Titane", "Chrome", "Fer", "Cobalt", "Nickel", "Cuivre", "Zinc", 
                                        "Palladium", "Argent", "Or", "Bore", "Carbone", "Azote", "Oxygène", "Fluor", "Hélium", "Néon", "Aluminium", "Silicium", "Phospore", "Souffre",
                                        "Chlore", "Argon", "Gallium", "Krypton", "Xénon", "Iode", "Etain", "Radon", "Uranium", "Francium"};
        int jokers = 2;
        println(fileAsString("./image/profPhysique.txt"));
        println("Bienvenue dans cette nouvelle salle ! Le thème de celle-ci est la Physique-Chimie !");
        println("On va jouer à un petit jeu assez cool ! Le PENDU !");
        println("Vu que j'adore la table des éléments, tu vas devoir essayer de les trouvers ! ");
        println("Suivant");
        if(!equals(readString(), null)){
            for(int i = 1; i <= 5; i += 1){
                int tentatives = 5;
                int random = (int) (random() * 20);
                String motInitiale = elements[random];
                String mot = transfoMot(motInitiale);
                boolean trouver = false;
                while(tentatives > 0 && trouver != true){
                    println(fileAsString("./image/profPhysique.txt"));
                    println("tour actuel : tour " + i);
                    if(equals(mot, motInitiale)){
                            trouver = true;
                    }else{
                        println("Il vous reste actuellement " + tentatives + " tentatives.");
                        println("Mot actuel : " + mot);
                        // choix lettre et verif si lettre in mot initiale :
                        println("Choississez un caractère : ");
                        char caract = readChar();
                        boolean verif = lettreDansMot(motInitiale, caract);
                            if((caract >= 'A' && caract <= 'Z')||(caract >= 'a' && caract <= 'z')){
                                if(verif == true){
                                    mot = rajoutCaract(motInitiale, mot, caract);
                                }else{
                                    tentatives -= 1;
                                }
                            }else{
                                if(jokers > 0){
                                    println("ATTENTION ! Vous avez entré un caractère incorrecte.. Un joker vient d'être consommé");
                                    jokers -= 1;
                                    println("Il vous en reste : " + jokers);
                                }else{
                                    println("Vous n'avez plus de jokers, vous perdez donc une tentative...");
                                    tentatives -= 1;
                                }

                            }
                    }
                }
                if(trouver = true){
                    points += 1;
                    println("Bravo vous avez réussi à trouver le mot qui était : " + motInitiale);
                }else{
                    println("Dommage.. Le mot cherché était : " + motInitiale);
                }
                
                println("Question Suivant");
                if(!equals(readString(), null)){
                    clearScreen();
                    println(fileAsString("./image/profPhysique.txt"));
                    println("Question suivante");
                }
            }
        }   
        if(points > 3){
            println("Félicitations vous avez réussi cette épreuve avec " + points + " points !");
        }else{
            println("... Vous n'avez pas réussi... je vous laisse quand même accéder à la salle suivante parce que vous avez fait l'effort de jouer mais bon...");
            println("Essayer de ne pas faire la même chose dans les salles suivantes.");
            delay(60000);
        }
        println("Suivant");
        if(!equals(readString(), null)){
            progression += 1;
        }
    }

    String transfoMot(String mot){
        String motEtoile = "";
        for(int i = 0; i < length(mot); i += 1){
            motEtoile += "*";
        }
        return motEtoile;
    }

    boolean lettreDansMot(String motInitiale, char car){
        boolean lettreIn = false;
        for(int i = 0; i < length(motInitiale); i += 1){
            char lettre = charAt(motInitiale, i);
            if(lettre == car){
                lettreIn = true;
            }else if(car == 'e' && (lettre == 'é' || lettre == 'è')){
                lettreIn = true;
            }
        }
        if(lettreIn == false){
            if(charAt(motInitiale, 0) == car - 32){
                lettreIn = true;
            }
        }
        return lettreIn;
    }

    String rajoutCaract(String motInitiale, String motEtoile, char caract){
        String motAvecCaract = "";
        for(int i = 0; i < length(motInitiale); i += 1){
            char lettreInitiale = charAt(motInitiale, i);
            char lettreEtoile = charAt(motEtoile, i);
            if(i == 0 && caract - 32 == lettreInitiale){
                motAvecCaract += lettreInitiale;
            }else{
                if(lettreInitiale == caract){
                motAvecCaract += caract;
                }else{
                    if(caract == 'e' && ( lettreInitiale == 'é' || lettreInitiale == 'è')){
                        motAvecCaract += lettreInitiale;
                    }else{
                        motAvecCaract += lettreEtoile;
                    }
                }
            }
        }
        return motAvecCaract;
    }

    void histoire(){
        clearScreen();
        println("Vous arrivé dans une autre salle de classe :");
        println(fileAsString("./image/profHistoire.txt"));
        println("Bienvenue dans ma salle d'histoire, aujourd'hui on va vérifier tes connaissances sur l'histoire du monde !");
        println("Par contre je te préviens, j'ai un peu d'Alzheimer donc il est possible qu'il soit possible que je me répète de temps en temps");
        int points = 0;
        println("Suivant");
        if(!equals(readString(), null)){
            String[][] contenu = creationTableQuestion("./question/histoire.csv");
            String[][]questions = questionHistoire(contenu);
            String reponse;
            String possiblites;
            String question;
            for(int i = 0; i < length(questions); i += 1){
                reponse = questions[i][1];
                question = questions[i][0];
                String reponseA;
                String reponseB;
                String reponseC;
                int random = (int) (random() * 3);
                if(random == 0){
                    reponseA = questions[i][1];
                    reponseB = questions[i][2];
                    reponseC = questions[i][3];
                }else if(random == 2){
                    reponseA = questions[i][3];
                    reponseB = questions[i][1];
                    reponseC = questions[i][2];
                }else{
                    reponseA = questions[i][2];
                    reponseB = questions[i][3];
                    reponseC = questions[i][1];
                }
                println(question);
                println("A : " + reponseA + " | B :" + reponseB + " | C :" + reponseC);
                String reponseJoueur = readString();
                println(fileAsString("./image/profHistoire.txt"));
                if(equals(reponseJoueur, "A") || equals(reponseJoueur, "B") || equals(reponseJoueur, "C") || equals(reponseJoueur, "a") || equals(reponseJoueur, "b") || equals(reponseJoueur, "c")){
                    if(equals(reponseJoueur, "A") || equals(reponseJoueur, "a")){
                        if(equals(reponseA, reponse)){
                            println("Bien joué !");
                            points += 1;
                        }else{
                            println("Mauvaise réponse !");
                            println("La bonne réponse était : " + reponse);
                        }
                    }else if(equals(reponseJoueur, "B") || equals(reponseJoueur, "b")){
                        if(equals(reponseB, reponse)){
                            println("Bien joué");
                            points += 1;
                        }else{
                            println("Mauvaise réponse");
                            println("La bonne réponse était : " + reponse);
                        }
                    }else if(equals(reponseJoueur, "C") || equals(reponseJoueur, "c")){
                        if(equals(reponseC, reponse)){
                            println("Bien joué");
                            points += 1;
                        }else{
                            println("Mauvaise réponse");
                            println("La bonne réponse était : " + reponse);
                        }
                    }
                }
                
            }
        }
        if(points >= 10){
            println("Félicitations, vous avez réussi avec un total de " + points + " points !");
            println("j'avais fait ces questions exprès pour que vous perdiez ! Veuillez prendre la porte sur votre droite pour sortir !");
        }else{
            println("Vous n'avez même pas réussi à avoir la moyenne.. Vous avez eu un total de " + points + " sur 20 ! C'est catastrophique");
            println("Je ne veux même plus vous voir, veuillez prendre la porte sur votre droite pour sortir !");
        }
        
        progression += 1;
    }

    String[][] questionHistoire(String[][] contenueTable){
        String[][] questions = new String[20][4];
        for(int i = 0; i < 20; i += 1){
            int ligne = (int) (random() * 65);
            questions[i][0] = contenueTable[ligne][0];
            questions[i][1] = contenueTable[ligne][1];
            questions[i][2] = contenueTable[ligne][2];
            questions[i][3] = contenueTable[ligne][3];
        }
        return questions;
    }

    void theEnd(){
        println("En sortant rapidement de la salle d'histoire, vous découvrez que la porte menait sur l'extérieur !");
        println("Vous avez enfin réussi toutes les épreuves et êtes parvenue à sortir du manoir.");
        println("hésitez-vous à y retourner ? o/n");
        String reponse = readString();
        if(equals(reponse,"o") || equals(reponse,"O")){
            progression = 1;
            startGame();
        }else{
            progression += 1;
        }
    } 

}