

////////////////// ObligSBinTre /////////////////////////////////
import java.util.*;

public class ObligSBinTre<T> implements Beholder<T>
{
    private static final class Node<T>{// en indre nodeklasse

        private T verdi; // nodens verdi
        private Node<T> venstre, hoyre; // venstre og hoyre barn
        private Node<T> forelder; // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder)
        {
            this.verdi = verdi;
            venstre = v; hoyre = h;
            this.forelder = forelder;
        }
        private Node(T verdi, Node<T> forelder) // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        private Node(T verdi){
            this(verdi, null, null, null);
        }
        @Override
        public String toString(){ return "" + verdi;}
    } // class Node

    private Node<T> rot; // peker til rotnoden
    private int antall; // antall noder
    private int endringer; // antall endringer
    private final Comparator<? super T> comp; // komparator

    public ObligSBinTre(Comparator<? super T> c) // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    /**
     *
     * @param verdi
     * @return
     */
    @Override
    public boolean leggInn(T verdi)
    {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.hoyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);                   // oppretter en ny node

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.hoyre = p;                        // hoyre barn til q

        if( q != null){
            p.forelder = q;
        }else {
            p.forelder = null;
        }

        antall++;                                // én verdi mer i treet
        return true;                             // vellykket innlegging              //
    }
    @Override
    public boolean inneholder(T verdi)
    {
        if (verdi == null) return false;
        Node<T> p = rot;
        while (p != null)
        {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.hoyre;
            else return true;
        }
        return false;
    }

    /**
     * Oppgave 5
     * @param verdi
     * @return
     */
    @Override
    public boolean fjern(T verdi) {

        if (tom()) return false;
        if(verdi == null) return false;
        Node<T> p = rot, q = null;

        while (p != null){ // finner verdi der etter samenliger
            int cmp = comp.compare(verdi, p.verdi);
            if(cmp < 0){
                q = p;
                p = p.venstre;
            }else if (cmp > 0){
                q = p;
                p = p.hoyre;
            }else {
                break;
            }
        }if (p == null) return false;
        if(!(p.venstre != null && p.hoyre != null)){  // tilfelle 1) 0g 2)
            Node<T> hjelpNode;
            if(p.venstre != null){
                hjelpNode = p.venstre;
            }else {
                hjelpNode = p.hoyre;
            }
            if (p == rot){
                rot = hjelpNode;
                if(hjelpNode != null)hjelpNode.forelder = null;
            }else if ( p == q.venstre){
                q.venstre = hjelpNode;
                if (hjelpNode != null) hjelpNode.forelder = q;
            }else {
                q.hoyre = hjelpNode;
                if(hjelpNode != null) hjelpNode.forelder = q;
            }
        }else {  //Tilfelle 3
            Node<T> s = p;
            Node<T> r = p.hoyre;

            while (r.venstre != null){
                s = r;
                r = r.venstre;
            }

            p.verdi = r.verdi; // kopierer verien i r til p

            if(s != p) {
                s.venstre = r.hoyre;
                if(r.hoyre != null) r.hoyre.forelder = s;
            }else {
                s.hoyre = r.hoyre;
                if(r.hoyre != null) r.hoyre.forelder = s;
            }
        }
        antall--;
        return true;
    }

    /**
     * Oppgave 5
     * @param verdi
     * @return
     */
    public int fjernAlle(T verdi) {
        int fjernt = 0;
        while (fjern(verdi)) fjernt++;
        return fjernt;
    }
//        int antallfjernet = 0;
//        for (int i = 0; i <= antall(verdi); i++) {
//            fjern(verdi);
//            antallfjernet++;
//        }return antallfjernet;
//    }

    /**
     * Oppgave 2
     * @return
     */
    @Override
    public int antall()
    {
        return antall;
    }

    /**
     * Oppgave 2
     * @param verdi
     * @return
     */
    public int antall(T verdi) {

        if(verdi == null) return 0;

        Node<T> p = rot;
        int antallForekomme = 0;
        while (p != null){
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if(cmp > 0) p = p.hoyre;
            else {
                antallForekomme++;
                p = p.hoyre;
            }
        }return antallForekomme;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean tom()
    {
        return antall == 0;
    }

    /**
     *
     */
    @Override
    public void nullstill() {

        if(!tom())
            nullstill(rot);
        rot = null;
        antall = 0;
    }

    /**
     *
     * @param p
     */
    private void nullstill(Node<T> p){
        if(p.venstre != null){
            nullstill(p.venstre);
            p.venstre = null;
        }
        if(p.hoyre != null){
            nullstill(p.hoyre);
           p.hoyre = null;
        }
        p.verdi = null;
    }



    /**
     * Oppgave 3 private method
     * @param p
     * @param <T>
     * @return
     */
    private static <T> Node<T> nesteInorden(Node<T> p)
    {

        // Vi finner den første i inorden i det treet som har p som rot
        if(p.hoyre != null) { // p har hoyre barn
            p = p.hoyre;
            while (p.venstre != null){
                p = p.venstre;
            }
            return p;
        }
        while (p.forelder != null && p.forelder.hoyre == p){  // here gå oppover i treet
            p = p.forelder;
        }
       return p.forelder;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        if(tom()) return "[]";
       StringJoiner txt = new StringJoiner(", " , "[", "]");

       Node<T> p = rot;
       while (p.venstre != null)
           p = p.venstre; // første noden p i inorden

        while (p != null){
            txt.add(p.verdi.toString());
            p = nesteInorden(p);
        }
        return txt.toString();
    }

    /**
     *OPPGAVE $
     * @return
     */
    public String omvendtString() {
        Stakk<T> stakk = new TabellStakk<>();
        if (rot == null) {
            return stakk.toString();
        }

        Node<T> p = rot;
        while (p.venstre != null)
            p = p.venstre;

        while (p != null) {
            stakk.leggInn(p.verdi);
            p = nesteInorden(p);
        }
        return stakk.toString();
    }

    /**
     *
     * @return
     */
    public String hoyreGren() {
        StringBuilder txt = new StringBuilder();
        txt.append('[');
        if (rot != null) {
            Node<T> p = rot;
            txt.append(p.verdi);
            while (true) {
                if (p.hoyre == null && p.venstre == null) break;
                if (p.hoyre != null) p = p.hoyre;
                else p = p.venstre;
                txt.append(",").append(" ").append(p.verdi);
            }
        }
            txt.append(']');
        return txt.toString();
    }

    /**
     * OPPGAVE 6
     * @return
     */
    /**
     * @return
     */
    public String lengstGren() {

		if (tom()) return "[]";
		
		Queue<Node<T>> que = new LinkedList<>();
		
		que.add(rot);
		
		Node<T> p = null;

        while (!que.isEmpty()){
            p = que.poll();
            
            if(p.hoyre != null) que.add(p.hoyre);
            if(p.venstre != null) que.add(p.venstre);

        }

       
        return gren(p.verdi);

    }

    /**
     * HELPEMETODE
     * tegnstreng av grenen til en bladnode
     * @param bladnodeverdi
     * @return
     */
    private String gren(T bladnodeverdi){
        StringJoiner txt = new StringJoiner(", ", "[", "]");
        Node<T> p = rot;

        while (p != null){
            txt.add(p.verdi.toString());
            p = comp.compare(bladnodeverdi, p.verdi) < 0 ? p.venstre : p.hoyre;
        }return txt.toString();
    }

    /**
     * Fikk Hjelp på internet og
     * Ulf notater fra 2014
     * @return
     */
    public String[] grener() {
        List<String> list = new ArrayList<>();
        ArrayDeque<T> gren1 = new ArrayDeque<>();
        if(!tom()) grener(rot, list, gren1);
        return list.toArray(new String[0]);
    }

    /**
     *Hjelp Metode med Java sin
     * @param p
     * @param list
     * @param gren1
     * @param <T>
     */
    public static <T> void grener(Node<T> p, List<String> list, ArrayDeque<T> gren1){
        gren1.addLast(p.verdi);
        if(p.venstre != null) grener(p.venstre, list, gren1);
        if(p.hoyre != null) grener(p.hoyre, list, gren1);
        if(p.hoyre == null && p.venstre == null) list.add(gren1.toString());
        gren1.removeLast();
    }

    /**
     *OPPGAVE 8 A
     * @return
     */
    public String bladnodeverdier() {
        StringJoiner txt = new StringJoiner(", ", "[", "]");
        if(tom()) return "[]";
         bladnoderverdier(rot, txt);
         return txt.toString();
    }

    /**
     * HjelpeMetode rekursiv
     * @param p
     * @param txt
     */
    private void bladnoderverdier(Node<T> p, StringJoiner txt){

        if(p.venstre != null)
            bladnoderverdier(p.venstre, txt);
        if (p.hoyre != null)
            bladnoderverdier(p.hoyre, txt);
          if(p.venstre == null && p.hoyre != null)
            txt.add(p.verdi.toString());

    }

    /**
     *OPPGVAE 8B
     * @return
     */
    public String postString(){
        StringJoiner txt = new StringJoiner(", ", "[", "]");
        if(!tom()) postString(rot, txt);
        return txt.toString();
    }

    /**
     * HelpeMetode
     * @param p
     * @param txt
     * @param <T>
     */
    private static <T> void postString(Node<T> p , StringJoiner txt){

        if(p.venstre != null) postString(p.venstre, txt);
        if(p.hoyre != null) postString(p.hoyre, txt);
        txt.add(p.verdi.toString());
    }
    @Override
    public Iterator<T> iterator()
    {
        return new BladnodeIterator();
    }
    private class BladnodeIterator implements Iterator<T>
    {
        private Node<T> p = rot, q = null;
        private boolean removeOK = false;
        private int iteratorendringer = endringer;
        private BladnodeIterator() {// konstruktør

//            if(tom()) return;
//            Node<T> p = rot;
//            while (true){
//                if (p.venstre != null) p = p.venstre;
//                else if (p.hoyre != null) p = p.hoyre;
//                //else
//            }
        }
        @Override
        public boolean hasNext()
        {
            return p != null; // Denne skal ikke endres!
        }
        @Override
        public T next() {
            if(iteratorendringer != endringer){
                throw new ConcurrentModificationException();
            }
            if(!hasNext()) throw new NoSuchElementException("Treet er endret");

            T verdi = p.verdi;
            q = p;    // q oppdatrees før p flyttes

            while (true){
                if(nesteInorden(p) == null){
                    p = nesteInorden(p);
                    break;
                }
                p = nesteInorden(p);
                if(p.venstre != null && p.hoyre == null);
                break;
            }
            removeOK = true;
            return verdi;
        }

        /**
         * skal fjerne verdien som sist ble returnert av next()
         * kan kun kalles en gang for hvert kall på next()
         * hvis remove() kalles i en iterator, skal alle andre iteratore blokkeres
         */
        @Override
        public void remove() {
          if(iteratorendringer != endringer) throw new ConcurrentModificationException();

          iteratorendringer++;
          endringer++;
          antall--;
        }
    } // BladnodeIterator
} // ObligSBinTre