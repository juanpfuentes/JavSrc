package com.trifulcas.utilStrings;

import java.text.Normalizer;

public class UtilsString {
    public static String invertir(String cadena) {
        StringBuilder res = new StringBuilder(cadena);
        return res.reverse().toString();
    }

    public static int contarChar(String cadena,char car){
        int res=0;
        for(int i=0;i<cadena.length();i++){
            if (cadena.charAt(i)==car){
                res++;
            }
        }
        return res;
    }
    public static long contarCharStream(String cadena,char car){
        long res=cadena.chars().filter(x->x==car).count();
        return res;
    }

    public static String sinEspacios(String cadena){
        cadena=cadena.trim();
        while(cadena.indexOf("  ")>=0){
            cadena=cadena.replace("  "," ");
        }
        return cadena;
    }
    public static boolean palindromo(String cadena){
        cadena=quitarAcentos(cadena).replaceAll("[^A-Za-z]+", "").toUpperCase();
        return cadena.equals(invertir(cadena));
    }

    public static String quitarAcentos(String text) {
        return text == null ? null :
                Normalizer.normalize(text, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public static int vocales(String cadena){
        cadena=quitarAcentos(cadena).toLowerCase();
        String voc="aeiou";
        int res=0;
        for(char c:voc.toCharArray()){
            res+=contarChar(cadena,c);
        }
        return res;
    }
}
