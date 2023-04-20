package com.day1;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) throws Exception{

        FileManagement fileManagement = new FileManagement();
        System.out.println("==== Word program ===");

        do {
            System.out.println("1. Count word in file");
            System.out.println("2. Find file by word");
            System.out.println("3. Exit");
            Scanner input = new Scanner(System.in);
            System.out.print("Enter your choice: ");
            String sChoice = input.nextLine();

            int choice = 0;
            try {
                choice = checkChoice(sChoice);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            switch (choice){
                case 1: {
                    System.out.println("--- Count word in a file ---");
                    fileManagement.countWord();
                    break;
                }
                case 2:{
                    System.out.println("--- Find file by word ---");
                    fileManagement.findFile();
                    break;
                }
                case 3:{
                    System.out.println(">> Program ended!");
                    return;
                }
            }

        }while (true);
    }

    public static int checkChoice(String sChoice) throws Exception{
        if(sChoice.equals("")){
            throw new Exception(">> Err: Choose can not be empty!");
        }
        int choice;
        try{
            choice = Integer.parseInt(sChoice);
        }
        catch (Exception e){
            throw new Exception(">> Err: Please enter a number");
        }
        if (choice > 0 && choice < 3){
            return choice;
        }else {
            throw new Exception(">> Err: Please enter a number form 1 to 3");
        }
    }
}
