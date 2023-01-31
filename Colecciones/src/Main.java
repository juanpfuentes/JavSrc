import com.trifulcas.clases.Genericas;
import com.trifulcas.clases.Stack;
import com.trifulcas.clases.UtilsFile;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ArrayList<String> archivos= UtilsFile.folderList("c:/Curb");
        System.out.println(archivos);
        Stack<String> cola=new Stack<>();


        cola.push("hola");
        cola.push("adios");
        cola.push("muy");
        cola.push("bien");

        System.out.println(cola.pop());
        System.out.println(cola.pop());
        System.out.println(cola.pop());
        Genericas g=new Genericas();
        g.setNum(5);
        g.nombres.add("aaa");
        try {
            Genericas h = (Genericas) g.clone();
            System.out.println(h.nombres);
        } catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
            System.out.println(ex.getMessage());
        }

        List<Integer> numeros=new ArrayList<>();
        for(int i=1;i<=8;i++){
            numeros.add(i);
        }
        System.out.println(numeros);
        numeros.remove((Integer)1);
        System.out.println(numeros);

        for(int i=numeros.size();i>0;i--){
            if (i%2==0){
                numeros.remove(i-1);
            }
        }
        System.out.println(numeros);
        System.out.println(numeros.indexOf(3));
        ArrayList<String> alumnos=new ArrayList<>();
        alumnos.add("Águeda");
        alumnos.add("Juan");
        alumnos.add("Ana");
        alumnos.add("Íñigo");
        System.out.println(alumnos);
        Collections.sort(alumnos);
        System.out.println(alumnos);

        Comparator<String> accentIgnorantComparator = (o1, o2) -> {
            o1 = Normalizer.normalize(o1, Normalizer.Form.NFD);
            o2 = Normalizer.normalize(o2, Normalizer.Form.NFD);
            return o1.compareTo(o2);
        };
        Collections.sort(alumnos,accentIgnorantComparator);
        System.out.println(alumnos);
        System.out.println(Normalizer.normalize("áéíóúñüÁÉÍÓÚÜÑöäàèìòù", Normalizer.Form.NFD));
    }
}