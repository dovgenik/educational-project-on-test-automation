package org.example;

import static java.lang.Math.round;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        int data[] = { 3, 6, 7, 10, 34, 56, 60, 62, 65, 67, 71, 75 };
        int numberToFind = 6;

        //
        int left=0;
        int right= data.length-1;
        int numberOfFindElement = -1;


        if (numberToFind >= data[left] && numberToFind <= data[right]) {

        while (left <= right) {
            // Безпечний розрахунок середнього індексу (захищає від переповнення int)
            int mid = left + (right - left) / 2;
            System.out.println("mid: " + mid + " L: "+left + " R: "+right);
            if (data[mid] == numberToFind) {
                numberOfFindElement = mid;
                break; // Знайшли! Виходимо
            } else if (data[mid] < numberToFind) {
                left = mid + 1; // Шукаємо у правій половині
            } else {
                right = mid - 1; // Шукаємо у лівій половині
            }
        }
    }

    System.out.println(numberOfFindElement);



        }







    static void matrics() {
        Byte[] filler = {1, 5, 7, 9, 13, 17, 19, 21, 25};
        int countFiller =0;
        String outElement= "";
        int maxLenOutElement=2;
        //PUT YOUR CODE HERE
        for (int i = 1; i <= 25; i++) {
            outElement= "";
            if (((i-1) % 5) == 0) {
                System.out.println("");
                maxLenOutElement=2;
            }
            else {
                maxLenOutElement=3;
            }
            if(i != filler[countFiller]) {
                outElement += i;
            } else {
                outElement += "*";
                countFiller += 1;
            }

            while (outElement.length() < maxLenOutElement) {
                outElement = " " + outElement;
            }

            System.out.print(outElement);

        }
    }

    static void bulbashka() {
        int[] array = {19, 7, 1, 5, 22, 30, 2, 10, 20, 4, 23, 8, 3, 21, 6, 0};
        int length = array.length;
        boolean finishSort = false;
        int a, b;
        int cycleCount=0;
while (!finishSort) {

    finishSort=true;
        for (int i = 0; i <= (length-2); i++) {
            a = array[i];
            b = array[i + 1];
            if (b < a) {
                array[i] = b;
                array[i + 1] = a;
                finishSort = false;
            }
            cycleCount++;
        }

   for (int ii = (length-2); ii > 0; ii--) {
        a = array[ii-1];
        b = array[ii];
        if (b < a) {
            array[ii] = a;
            array[ii - 1] = b;
            finishSort = false;
        }
        cycleCount++;
    }
        }

        System.out.println("Проходів - "+cycleCount);
        for (int i = 0; i < length; i++) {
            System.out.print(array[i] + " ");
        }
    }
    }
