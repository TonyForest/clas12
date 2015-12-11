/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jlab.clas.tools.utils;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

/**
 *
 * @author gavalian
 */
public class FileUtils {
    
    public static List<String> getFilesInDir(String directory){
        ArrayList<String> fileList = new ArrayList<String>();
        File[] files = new File(directory).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                if(file.getName().startsWith(".")==true||
                        file.getName().endsWith("~")){
                    System.out.println("[FileUtils] ----> skipping file : " + file.getName());
                } else {
                    fileList.add(file.getAbsolutePath());
                }
            }
        }
        return fileList;
    }
    
    public static List<String> getFilesInDir(String directory,String extension){
        ArrayList<String> fileList = new ArrayList<String>();
        File[] files = new File(directory).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                if(file.getName().startsWith(".")==true||
                        file.getName().endsWith("~")){
                    System.out.println("[FileUtils] ----> skipping file : " + file.getName());
                } else {
                    if(file.getName().endsWith(extension)==true){
                        fileList.add(file.getAbsolutePath());
                    }
                }
            }
        }
        return fileList;
    }
    public static List<String> getFilesFromJar(String jarname){
        ArrayList<String> files = new ArrayList<String>();
        //JarFile jarFile = new JarFile(pathToJar);
        //TreeMap<String,String> a = null;
        return files;
    }
    
    public static void main(String[] args){
        List<String> fileList = FileUtils.getFilesInDir("/Users/gavalian/Work/Software/Release-8.0/COATJAVA/coatjava/etc/bankdefs/translation");
        for(String file : fileList){
            System.out.println("file -> " + file);
        }
    }
    
}
