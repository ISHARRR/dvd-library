/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.dvdcollection.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

/**
 *
 * @author ishar
 */
public class UserIOConsoleImpl implements UserIO {

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        String message = null;
        boolean flag = true;

        while (flag) {
            message = sc.nextLine();
            if ("".equals(message)) {
                System.out.println("Invalid Entry");
            } else {
                flag = false;
            }
        }

        char[] charArray = message.toCharArray();
        boolean foundSpace = true;

        for (int i = 0; i < charArray.length; i++) {
            if (Character.isLetter(charArray[i])) {
                if (foundSpace) {
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    foundSpace = false;
                }
            } else {
                foundSpace = true;
            }
        }
        message = String.valueOf(charArray);
        String stripTrailingSpace = message.strip();
        return stripTrailingSpace;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        int input = 0;
        while (flag) {
            try {
                input = sc.nextInt();
            } catch (java.util.InputMismatchException e) {
                sc.nextLine();
            }
            
            if (input >= min && input <= max) {
                flag = false;
            } else {
                System.out.println("Error out of range or Invalid: " + prompt);
                input = sc.nextInt();
            }

        }

        return input;
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        return sc.nextDouble();
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        Double input = sc.nextDouble();
        while (flag) {
            if (input >= min && input <= max) {
                flag = false;
            }
            System.out.println(prompt);
            input = sc.nextDouble();
        }

        return input;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        return sc.nextFloat();
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        float input = sc.nextFloat();
        while (flag) {
            if (input >= min && input <= max) {
                flag = false;
            }
            System.out.println(prompt);
            input = sc.nextFloat();
        }

        return input;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        return sc.nextLong();
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        long input = sc.nextLong();
        while (flag) {
            if (input >= min && input <= max) {
                flag = false;
            }
            System.out.println(prompt);
            input = sc.nextLong();
        }

        return input;
    }

    @Override
    public String readDate(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt + " Example 'DD-MM-YYYY'");
        String date = null;

        boolean flag = true;

        while (flag) {
            date = sc.nextLine();
            try {
                LocalDate.parse(date,
                        DateTimeFormatter.ofPattern("dd-MM-uuuu")
                                .withResolverStyle(ResolverStyle.STRICT)
                );
                flag = false;

            } catch (DateTimeParseException e) {
                System.out.println("Incorect format - Match Example");
            }
        }
        return date;
    }

    @Override
    public String readMpaa(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        String rating = null;
        boolean flag = true;
        while (flag) {
            int ratingNo = sc.nextInt();
            switch (ratingNo) {
                case 1:
                    rating = "U";
                    break;
                case 2:
                    rating = "PG";
                    break;
                case 3:
                    rating = "12";
                    break;
                case 4:
                    rating = "15";
                    break;
                case 5:
                    rating = "18";
                    break;
                case 6:
                    rating = "R18";
                    break;
                default:
                    System.out.println("Invalid Entery - Choose Again");
                    break;
            }
            if (ratingNo >= 1 && ratingNo <= 6) {
                flag = false;
            }
        }
        return rating;
    }

    @Override
    public String readId(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        String id = null;
        boolean flag = true;

        while (flag) {
            id = sc.nextLine();
            if (isNumeric(id)) {
                if (id.length() >= 5) {
                    String cutName = id.substring(0, 5);
                    int i = Integer.parseInt(cutName);
                    String leadingZeros = String.format("%05d", i);
                    id = leadingZeros;
                    flag = false;
                } else {
                    System.out.println("Invalid entery - try again");
                }
            } else {
                System.out.println("Invalid entery - try again");
            }
        }
        return id;
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\d+)?");
    }

    public static boolean lettersOnly(String str) {
        return str.matches("^[a-zA-Z]*$");
    }

    @Override
    public String readPersonName(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        String message = null;
        boolean flag = true;

        while (flag) {
            message = sc.nextLine();
            if (!lettersOnly(message) || "".equals(message)) {
                System.out.println("Invalid Entry - No Numbers or Empty String");
                flag = true;
            } else {
                flag = false;
            }
        }

        char[] charArray = message.toCharArray();
        boolean foundSpace = true;

        for (int i = 0; i < charArray.length; i++) {
            if (Character.isLetter(charArray[i])) {
                if (foundSpace) {
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    foundSpace = false;
                }
            } else {
                foundSpace = true;
            }
        }
        message = String.valueOf(charArray);
        String stripTrailingSpace = message.strip();
        return stripTrailingSpace;
    }

}
