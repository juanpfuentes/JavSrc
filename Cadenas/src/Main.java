
import com.trifulcas.utilStrings.UtilsString;

public class Main {
    public static void main(String[] args) {

        System.out.println(invertir2("Hello world!"));
        System.out.println(invertir("Hello world!"));
        System.out.println(UtilsString.invertir("Hello world!"));
        System.out.println(UtilsString.contarChar("Hola que tal",'a'));
        System.out.println(UtilsString.sinEspacios("   Hola      que    tal "));
        System.out.println(UtilsString.palindromo(("qwqw")));
        System.out.println(UtilsString.palindromo(("ana")));
        System.out.println(UtilsString.palindromo(("Isaac no ronca así")));
        System.out.println(UtilsString.quitarAcentos(("ÁéíüñòâÜ")));
        System.out.println(UtilsString.vocales("Alegría"));

    }


    public static String invertir(String cadena) {
        String res = "";
        for (int i = cadena.length() - 1; i >= 0; i--) {
            res += cadena.charAt(i);
        }
        return res;
    }

    public static String invertir2(String cadena) {
        StringBuilder res = new StringBuilder(cadena);
        return res.reverse().toString();
    }
}