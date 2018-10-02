package oving_04.oppg1;

public class main {


    public static void main(String[] args){
        main c = new main();
        int[] tall ={7,7};
        int[] tall2 ={4,4};

        //Ordner så tabellene er like lange for enklere utførelse senere
        if( tall.length > tall2.length){
            tall2 = c.tabellAdd(tall2,tall.length);
        } else if( tall2.length > tall.length){
            tall = c.tabellAdd(tall,tall2.length);
        }
        Node alpha = new Node(tall[tall.length-1],null,null);
        Node beta  = new Node(tall2[tall.length-1],null,null);

        //Fyller node alpha og beta med verdiene de skal holde
        for(int i = tall.length-2; i >= 0; i--){
            alpha.addNode(new Node(tall[i],null,alpha));
            beta.addNode(new Node(tall2[i],null,beta));
        }

        Node ny = c.minus(alpha,beta);

        System.out.println(ny.getElement());

    }

    //Hjelpe metode
    private Node fjernEkstraNull(Node d){
        Node temp = d;
        if(d.finnForrige() == null){ return new Node(0,null,null);}
        if(d.finnForrige().finnElement() == 0 & d.finnElement() == 0){
            temp = fjernEkstraNull(d.finnForrige());
        } else if(d.finnForrige().finnElement() > 0 & d.finnElement() == 0){
            temp = d.fjernNeste();
        }
        return temp;
    }

    public int[] tabellAdd(int[] arr, int length){
        int[] temp = new int[length];
        int diff = length-arr.length;
        for(int i = diff; i <= temp.length-1; i++){
            temp[i] = arr[i-diff];
        }

        return temp;
    }

    public boolean lengsteTall(Node big, Node small){

        String length_a = "";
        String length_b = "";

        char[] d = big.getElement().toCharArray();
        char[] f = small.getElement().toCharArray();

        for(int i = 0; i < d.length; i++ ){
            length_a += d[i];
            length_b += f[i];
            if(Integer.parseInt(length_a) > Integer.parseInt(length_b) ){
                return true;
            } else if(Integer.parseInt(length_b) > Integer.parseInt(length_a)) {
                return false;
            }
        }
        return false;
    }

    public Node pluss(Node alpha, Node beta){

        int iMente = 0;
        alpha = alpha.finnSiste();
        beta = beta.finnSiste();

        int a = alpha.finnElement();
        int b = beta.finnElement();

        if(a + b + iMente > 9) {a = ((a+b) == 0) ? (a+b) : (a+b+iMente-10); iMente = 1;}
        else{a = a+b+iMente; iMente = 0;}

        Node ny = new Node(a,null,null);

        alpha = alpha.finnNeste();
        beta = beta.finnNeste();
        while(beta != null && alpha != null){
            a = alpha.finnElement();
            b = beta.finnElement();
            if(a + b + iMente > 9) {a = ((a+b) == 0) ? (a+b) : (a+b+iMente-10); iMente = 1;}
            else{a = a+b+iMente; iMente = 0;}
            ny.addNode(new Node(a,null,ny));
            beta = beta.finnNeste();
            alpha = alpha.finnNeste();

        }
        //Legger til en siste Node om tallet som blir laget er større en tabell lengden tidligere
        if(iMente != 0) {
            ny.addNode(new Node(iMente,null,ny));
        }


        //Går fram til første hode
        ny = ny.finnFørste();

        return ny;
    }

    public Node minus(Node alpha, Node beta){
        boolean neg = false;

        //Går til hode i noden
        alpha = alpha.finnFørste();
        beta = beta.finnFørste();

        //Sjekker om første elemente i beta er større enn alpha, visst så må jeg flippe de om så jeg kjører største - minste og forandrer til neg senere

        if(!lengsteTall(alpha,beta)){
            neg = true;
            Node temp = alpha;
            alpha = beta;
            beta = temp;
        }

        int length = alpha.getListLength();
        alpha = alpha.finnSiste();
        beta = beta.finnSiste();

        int iMente = 0;
        int a = alpha.finnElement();
        int b = beta.finnElement();
        //Kjører igjennom if setningen for å finne første a verdi for en start Node
        if(a - b + iMente < 0) {a = ((a-b) == 0) ? (a-b) : (a-b+iMente)+10; iMente = -1;}
        else{a = a-b+iMente; iMente = 0;}

        //Initierer en ny Node som start punkt
        Node ny = new Node(a,null,null);
        beta = beta.finnNeste();
        alpha = alpha.finnNeste();

        //Selve omregnings koden
        while(beta != null && alpha != null){

            //Om både beta og alpha er null så er de ikke vits at koden kjører hehe eksd
            a = alpha.finnElement();
            b = beta.finnElement();
            //Bruker iMente for å vite om forrige tall fikk +10 eller ikke
            if(a - b + iMente < 0 ) {a = (((a-b+iMente) == 0)) ? 0 : (a-b+iMente)+10; iMente = -1;}
            else{a = a-b+iMente; iMente = 0;}
            ny.addNode(new Node(a,null,ny));

            beta = beta.finnNeste();
            alpha = alpha.finnNeste();
        }
        //Finner første node
        ny = ny.finnFørste();
        //Rydder opp i nodene så nodene med null framfor tallet blir borte
        ny = fjernEkstraNull(ny);
        //Setter negativ verdi for første node om beta > alpha tidligere
        if(neg){
            ny.setElement(ny.finnElement()*-1);
        }
        return ny;
    }
}



class Node {
    private int element;
    private Node neste;
    private Node forrige;

    public Node(int e,Node n,Node f) {
        element = e;
        neste = n;
        forrige = f;
    }

    public int finnElement() {
        return element;
    }

    public void setElement(int e) {
        element = e;
    }

    public Node finnSiste() {
        if (forrige != null) {
            return forrige.finnSiste();
        } else {
            return this;
        }
    }


    public Node fjernNeste() {
        Node temp = forrige;
        forrige.setNeste(null);
        return temp;
    }

    public void setNeste(Node node) {
        neste = node;
    }

    public Node finnNeste() {
        return neste;
    }

    public Node finnFørste(){
        if(neste != null){
            return neste.finnFørste();
        } else {
            return this;
        }
    }

    public Node fjernForrige() {
        Node temp = neste;
        neste.setForrige(null);
        return temp;
    }


    public Node finnForrige() {
        return forrige;
    }

    public String getElement() {
        if (forrige != null) {
            return "" + element + forrige.getElement();
        } else return "" + element;
    }

    public int getListLength() {
        if (forrige != null) {
            return 1 + forrige.getListLength();
        } else {
            return 1;
        }

    }

    public void addNode(Node n) {
        if (neste != null) {
            neste.addNode(n);
        } else {
            neste = n;
            n.setForrige(this);
        }
    }

    public void setForrige(Node n) {
        forrige = n;
    }
}
