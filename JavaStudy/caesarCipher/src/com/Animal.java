package com;

import java.util.Arrays;
import java.util.Scanner;

public class Animal {
    public static final double FAVNUMBER = 1.6180;

    private String name;
    private int weight;
    private boolean hasOwner = false;
    private byte age;
    private long uniqueID;
    private char favouriteChar;
    private double speed;
    private float height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isHasOwner() {
        return hasOwner;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public long getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(long uniqueID) {
        this.uniqueID = uniqueID;
    }

    public void setUniqueID() {
        long maxID = 10000;
        long minID = 1;
        this.uniqueID = minID + (long)(Math.random()*(maxID - minID));

        String stringNumber = Long.toString(maxID);
        int numberString = Integer.parseInt(stringNumber);
    }

    public char getFavouriteChar() {
        return favouriteChar;
    }

    public void setFavouriteChar(char favouriteChar) {
        this.favouriteChar = favouriteChar;
    }

    public void setFavouriteChar() {
        int randomNumber = (int)(Math.random()*126+1);
        this.favouriteChar = (char)randomNumber;

        if (randomNumber == 32){
            System.out.println("favourite character set to space");
        }else if(randomNumber == 10){
            System.out.println("favourite character set to newLine");
        }else {
            System.out.println("favourite character set to " + this.favouriteChar);
        }

        if ((randomNumber > 97)&&(randomNumber<122)){
            System.out.println("favourite character is lowercase letter");
        }

        if ((randomNumber > 97)&&(randomNumber<122) || (randomNumber > 64)&&(randomNumber<91)){
            System.out.println("favourite character set to is letter ");
        }
        int whichIsBigger = (50>randomNumber)?50:randomNumber;

        switch (randomNumber){
            case 8:
                System.out.println("favourite character set to backspace");
                break;
            case 11:
                System.out.println("favourite character set to ...");
                break;
            default:
                System.out.println();
                break;

        }
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    protected static int numberOfAnimals = 0;
    static Scanner userInput = new Scanner(System.in);

    public Animal() {
        numberOfAnimals++;
        int sumOfAnimals = 5+1;
        System.out.println("5+1=" + sumOfAnimals);
        System.out.print("enter the name: ");
        if (userInput.hasNextLine()){
            this.setName(userInput.nextLine());
        }
        this.getFavouriteChar();
        this.getUniqueID();
    }

    protected static void countTo(int startingNumber){
        for (int i = startingNumber; i <= 100; i++) {
            if (i == 90) continue;
            System.out.println(i);
        }
    }

    protected static String printNumbers(int maxNumbers){
        int i = 1;
        while (i<(maxNumbers/2)){
            System.out.println(i);
            i++;

            if (i==(maxNumbers/2)) break;
        }
        Animal.countTo(maxNumbers/2);
        return "end of printNumbers";
    }

    protected static void getMyNumber(){
        int number;
        do {
            System.out.println("guess number up to 100");
            while (!(userInput.hasNextInt())){
                String numberEntered = userInput.next();
                System.out.printf("%s is not a number\n" , numberEntered);
            }

            number = userInput.nextInt();
        }while (number!=50);
    }

    public String makeSound(){
        return "Gerrr";
    }

    public static void speakAnimal(Animal randAnimal){
        System.out.println("Animal says" + randAnimal.makeSound());
    }

    public static void main(String[] args) {
        Animal theAnimal = new Animal();

        String[] stringArrays = {"aa", "bb", "cc"};
        for (String word : stringArrays){
            System.out.println(word);
        }
        String[] cloneOfArray = Arrays.copyOf(stringArrays, 5);
        for (int i = 0; i < cloneOfArray.length; i++) {
            System.out.println(cloneOfArray[i]);
        }
        for (String word : cloneOfArray){
            System.out.println(word);
        }
    }
}
