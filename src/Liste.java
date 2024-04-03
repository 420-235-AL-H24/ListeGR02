public class Liste<Type> {
    public class Noeud {
        public Type valeur;
        public Noeud precedent, prochain;

        public Noeud(Type valeur) {
            this.valeur = valeur;
            this.precedent = null;
            this.prochain = null;
        }

        @Override
        public String toString() {
            return String.valueOf(valeur);
        }
    }


    private Noeud premier, dernier;
    private int nbElements;

    public Liste() {
        premier = dernier = null;
        nbElements = 0;
    }

    public String toString() {
        String str = "[";
        for (Noeud courant = premier; courant != null; courant = courant.prochain)
            str += courant.valeur + ", ";
        return str + "]";
    }

    public int getNbElements() {
        return nbElements;
    }

    public boolean estVide() {
        return nbElements == 0;
    }

    public Type getElementAt(int index) {
        return getNoeudAt(index).valeur;
    }

    private Noeud getNoeudAt(int index) {
        if (index >= 0) {
            for (Noeud courant = premier; courant != null; courant = courant.prochain)
                if (index-- == 0)
                    return courant;
        }
        else {
            for (Noeud courant = dernier; courant != null; courant = courant.precedent)
                if (++index == 0)
                    return courant;
        }
        return null;
    }

    public boolean ajouter(Type valeur) {
        //tableau[nbElements++] = valeur;

        if (estVide()) {
            premier = new Noeud(valeur);
            dernier = premier;
        }
        else {
            Noeud nouveau = new Noeud(valeur);
            dernier.prochain = nouveau;
            nouveau.precedent = dernier;
            dernier = dernier.prochain;
        }
        nbElements++;
        return true;
    }

    public boolean ajouter(Type valeur, int index) {
        if (index < -nbElements || index > nbElements)
            //throw new IndexOutOfBoundsException();
            return false;

        Noeud precedent = getNoeudAt(index - 1);
        Noeud suivant = precedent.prochain;
        Noeud nouveau = new Noeud(valeur);

        if (index == 0) {
            nouveau.prochain = premier;
            nouveau.precedent = null;
            premier = nouveau;
        }
        else if (index == nbElements || index == -1) {
            return ajouter(valeur);
        }
        else {
            nouveau.prochain = precedent.prochain;
            nouveau.precedent = precedent;
            precedent.prochain = nouveau;
            suivant.precedent = nouveau;
        }

        nbElements++;
        return true;
    }

    public void ajouter(Liste<Type> autre) {
        for (int i = 0; i < autre.getNbElements(); i++)
            this.ajouter(autre.getElementAt(i));
    }

    public int trouver(Type valeur) {
        int index = 0;
        for (Noeud courant = premier; courant != null; courant = courant.prochain) {
            if (courant.valeur == valeur)
                return index;
            else
                index++;
        }
        return -1;
    }

    public boolean trouverTout(Liste<Type> autre) {
        for (int i = 0; i < autre.getNbElements(); i++)
            if (this.trouver(autre.getElementAt(i)) == -1)
                return false;
        return true;
    }

    public boolean effacerAt(int index) {
        if (index < -nbElements || index > nbElements)
            return false;

        if (index == 0) {
            premier = premier.prochain;
            premier.precedent = null;
        }
        else if (index == nbElements - 1 || index == -1) {
            dernier = dernier.precedent;
            dernier.prochain = null;
        }
        else {
            Noeud precedent = getNoeudAt(index - 1);
            Noeud suivant = getNoeudAt(index + 1);
            precedent.prochain = suivant;
            suivant.precedent = precedent;
        }

        nbElements--;
        return true;
    }

    public boolean effacerTout(Liste<Type> autre) {
        boolean modifie = false;
        for (int i = 0; i < autre.getNbElements(); i++) {
            Type valeurCherchee = autre.getElementAt(i);
            int indexTrouve = this.trouver(valeurCherchee);
            if (indexTrouve != -1) {
                effacerAt(indexTrouve);
                modifie = true;
            }
        }
        return modifie;
    }

    public void effacerTout() {
        premier = null;
        nbElements = 0;
    }
}
