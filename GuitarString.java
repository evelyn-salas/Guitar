// Evelyn Salas
// CSE 143 AO with Stuart Reges
// Homework 2
// The GuitarString class simulates the strumming of a guitar by keeping track of track of a
// collection of frequencies.

import java.util.*;

public class GuitarString {
    // Constants
    private Queue<Double> buffer; 
    public double DECAY = 0.996;

    /*
    * Creates a collection of GuitarString frequencies
    * @throws IllegalArgumentException if the frequency is negative or if container is too small
    * @param frequency a single frequency to be added to the collection
    */
    public GuitarString(double frequency) {
        int N = 0;
        N = (int) Math.round(StdAudio.SAMPLE_RATE/frequency);
        buffer = new LinkedList<Double>();
        if( N < 2 || frequency <= 0) {
            throw new IllegalArgumentException("buffer < 2 or frequency <= 0");
        }
        for(int i = 0; i < N; i++) {
            buffer.add(0.0);
        }
    }

    /*
    * Initializes the GuitarString container
    * @throws IllegalArgumentException if the container is too small
    * @param double[] init contains a collection of values to initialze the container
    */
    public GuitarString(double[] init) {
        buffer = new LinkedList<Double>();
        if (init.length < 2) {
            throw new IllegalArgumentException("buffer is less than 2 elements long");
        }
        for(int i = 0; i < init.length; i++) {
            buffer.add(init[i]);
        }
    }

    //replaces N elements with random values
    /*
    * Generates random values and adds them to the container
    * @throws IllegalArgumentException if randomly generated value is not between -0.5 and 0.5
    */
    public void pluck() {
        double randomVal = 0.0;
        for (int i = 0; i < buffer.size(); i++) {
            randomVal = Math.random() - 0.5;
            if (randomVal > 0.5 || randomVal < - 0.5) {
                throw new IllegalArgumentException();
            }
            buffer.remove();
            buffer.add(randomVal);
        }
    }

    /*
    * Applies the Karplus-Strong update to the frequencies in the container
    */
    public void tic() {
        double sample1 = buffer.peek();
        buffer.remove();
        double sample2 = buffer.peek();

        double average = (sample1 + sample2) * 0.5 * DECAY;
        buffer.add(average);
    }

    /*
    * Returns the current sample
    * @returns the current frequency
    */
    public double sample() {
        double sample = buffer.peek();
        return sample;
    }
}