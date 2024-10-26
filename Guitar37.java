// Evelyn Salas
// CSE 143 AO with Stuart Reges
// Homework 2
// The Guitar37 class simulates a guitar with 37 strings represented by a keyboard

import java.util.*;

public class Guitar37 implements Guitar {
    // constants
    public static final String KEYBOARD =
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
    private static final int stringNum = 37; // magic number, size of keyboard
    private int count = 0;
    private GuitarString[] strings;

    /*
    * Constructs a container of keyboard characters
    */
    public Guitar37(){
        strings = new GuitarString[stringNum];
        for (int i = 0; i < stringNum; i++) {
            double frequency = 440 * Math.pow(2, (i - 24.0)/12.0);
            strings[i] = new GuitarString(frequency);
        }
    }
    
     /*
    * takes in a pitch and plucks that note
    * @param int pitch is added to a constant to get the note
    */
    public void playNote(int pitch){
        int note = pitch + 24;
        if (note > -1 && note < 37) {
            strings[note].pluck();
        }
    }

     /*
    * Returns true if a string is valid
    * @param char string is a string from the container
    * @return returns true if string is of the right size
    * @return false if string is not the right size
    */ 
    public boolean hasString(char string) {
        if (KEYBOARD.indexOf(string) < 0 || KEYBOARD.indexOf(string) > 36) {
            return false;
        }

        return true;
    }

     /*
    * verifys that the characters corespond to the notes
    * @param char string
    * @throw throws IllegalArgumentException if there is no string
    */
    public void pluck(char string) {
        if (hasString(string)) {
            int index = KEYBOARD.indexOf(string);
            strings[index].pluck();
        } else {
            throw new IllegalArgumentException();
        }

    }

     /*
    * takes a sample from the container
    * @returns the sum of 2 samples
    */
    public double sample() {
        double sum = 0.0;
        for (int i = 0; i < stringNum; i++) {
            sum = sum + strings[i].sample();
        }
        return sum;
    }

     /*
    * Increses the count
    */
    public void tic(){
        for (int i = 0; i < stringNum; i++) {
            strings[i].tic();
        }
        count++;
    }

     /*
    * Returns the count
    * @return count
    *
    */
    public int time(){
        return count;
    }
}