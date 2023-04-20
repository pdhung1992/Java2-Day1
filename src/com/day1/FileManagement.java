package com.day1;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManagement {
        public void countWord(){
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("Enter file path: ");
                String filePath = input.nextLine();
                if(filePath.equals("")){
                    throw new Exception("File path can not be empty!");
                }

                System.out.print("Enter word to find: ");
                String word = input.nextLine();
                if(word.equals("")){
                    throw new Exception("Word input can not be empty!");
                }

                File fileSource = new File(filePath);

                int count = 0;

                count = count(fileSource, word);

                if( count > 0){
                    System.out.println("Word '" + word +"' found " + count + " times");
                }else{
                    System.out.println("Word '" + word + "' not found!");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        public void findFile(){
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("Enter Path: ");
                String path = input.nextLine();
                if (path.equals("")){
                    throw new Exception("Path can not be empty!");
                }
                System.out.print("Enter word to find: ");
                String word = input.nextLine();
                if (word.equals("")){
                    throw new Exception("Word input can not be empty!");
                }

                File directory = new File(path);

                System.out.println("--- File name ---");
                getFile(directory, word);
                for(String item : list){
                    System.out.println(item);
                }
                System.out.println(">> Total file include word '" + word + "': "+ list.size());
            } catch(Exception e) {
                System.out.println(e.getMessage());;
            }
        }

    private String readFile(File fileSource) throws Exception {
        try {
            FileInputStream fis = new FileInputStream(fileSource);
            int available = fis.available(); //length of file
            byte content[] = new byte[available];

            fis.read(content);

            //Close file
            fis.close();
            return ( new String(content) );
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public int count(File fileSource, String word) {
        int count = 0;
        try {
            String content = this.readFile(fileSource);
            //Count
            Pattern pattern = Pattern.compile(word);
            Matcher match = pattern.matcher(content);

            while( match.find() ) {
                count++;
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    List<String> list;

    public FileManagement(){
        list = new ArrayList<String>();
    }

    public void getFile(File fileSource, String word) {
        //File can be File || Directory
        //List of Files and Directories
        File files[] = fileSource.listFiles();
        for(File item : files) {
            if(item.isDirectory()) {
                getFile(item, word);
            } else {
                if(item.getName().endsWith("txt")) {
                    try {
                        int count = this.count(item, word); //class
                        if(count > 0) {
//                            System.out.println(item.getName());//
                            list.add(item.getName());
                        }else{
                            System.out.println("File not found!");
                        }
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}
