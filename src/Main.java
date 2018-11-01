

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
	// write your code here
   /*     loopIT("Oppbave 0");
        ObligSBinTre<String> tre = new ObligSBinTre<>(Comparator.naturalOrder());
        System.out.println();
        System.out.println(tre.antall());

        loopIT("Oppgave 1");
        System.out.println();
        Integer[] a = {4,7,2,9,5,10,8,1,3,6};
        ObligSBinTre<Integer> tre1 = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a) tre1.leggInn(verdi);
        System.out.println(tre1.antall()); // Utskrift: 10

        loopIT("Oppgave2");
        System.out.println();

        Integer[] a1 = {4,7,2,9,4,10,8,7,4,6};
        ObligSBinTre<Integer> tre3 = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a1) tre3.leggInn(verdi);
        System.out.println(tre3.antall()); // Utskrift: 10
        System.out.println(tre3.antall(5)); // Utskrift: 0
        System.out.println(tre3.antall(4)); // Utskrift: 3
        System.out.println(tre3.antall(7)); // Utskrift: 2
        System.out.println(tre3.antall(10)); // Utskrift: 1

        loopIT("Oppgave 3");

        System.out.println();
        int[] a2 = {4,7,2,9,4,10,8,7,4,6,1};
        ObligSBinTre<Integer> tre4 = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a2) tre4.leggInn(verdi);
        System.out.println(tre4); // [1, 2, 4, 4, 4, 6, 7, 7, 8, 9, 10]
        System.out.println(tre4.omvendtString());


        loopIT("OPpgave 5");
        System.out.println();

        int[] a3 = {4,7,2,9,4,10,8,7,4,6,1};
        ObligSBinTre<Integer> tre6 = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a3) tre6.leggInn(verdi);
        System.out.println(tre6);

        System.out.println(tre6.fjernAlle(4)); // 3
                           tre6.fjernAlle(7);
                           tre6.fjern(8);

        System.out.println(tre6.antall()); // 5
        System.out.println(tre6 + " " + tre6.omvendtString());
*/
        loopIT("Oppgave 6");
        System.out.println();
        ObligSBinTre<String> tre5 = new ObligSBinTre<>(Comparator.naturalOrder());
        char[] verdier = "IATBHJCRSOFELKGDMPQN".toCharArray();
        tre5.leggInn("F");
        tre5.leggInn("B");
        tre5.leggInn("AAAAAAAAAAAAAAAAAAAA");
        String[] verdierr = "HDGOCEKPJMIL".split("");

        for (String c : verdierr) tre5.leggInn(c);
        System.out.println(tre5.hoyreGren() + " " + tre5.lengstGren());
        // Utskrift: [I, T, J, R, S] [I, A, B, H, C, F, E, D]


        loopIT("Oppgave 8 a og b Example 1");
        System.out.println();
        System.out.println(tre5.bladnodeverdier());
        System.out.println(tre5.postString());


        loopIT("OPPGAVE 8 B");
        System.out.println();
        int[] a5 = {4,7,2,9,4,10,8,7,4,6};
        ObligSBinTre<Integer> tre7 = new ObligSBinTre<>(Comparator.naturalOrder());
        for (int verdi : a5) tre7.leggInn(verdi);
        System.out.println(tre7.postString()); // [2, 6, 4, 4, 7, 8, 10, 9, 7, 4]

        loopIT("Oppgave 7");
        System.out.println();

        String[] s = tre5.grener();
        for (String gren1 : s) System.out.println(gren1);
        // Utskrift:
        // [I, A, B, H, C, F, E, D]
        // [I, A, B, H, C, F, G]
        // [I, T, J, R, O, L, K]
        // [I, T, J, R, O, L, M, N]
        // [I, T, J, R, O, P,
    }


    public static void loopIT (String label){
        System.out.println(label);
        for (int i = 0; i < label.length(); i++) {
            System.out.print("*");
        }
    }
}
