package com.trifulcas.clases;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UtilsFile {
    public static ArrayList<String> folderList(String path){
        File folder=new File(path);
        ArrayList<String> names=new ArrayList<>();
        if (folder.exists() && folder.isDirectory()) {
            // Obtiene una lista de los archivos y directorios contenidos en el directorio
            File[] files = folder.listFiles();
            // Recorre cada archivo o directorio
            for (File file : files) {
                if (file.isFile()) {
                    // Si es un archivo, imprime su nombre
                    System.out.println(file.getName());
                    names.add(file.getPath());
                } else if (file.isDirectory()) {
                    // Si es un directorio, llama recursivamente al método
                    names.addAll(folderList(file.getPath()));
                }
            }
        }
        return names;
    }
}
