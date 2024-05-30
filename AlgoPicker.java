package Processes;
import MultiQueue.*;
import java.util.ArrayList;

import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class AlgoPicker {
    Scanner scanner = new Scanner(System.in);
    FCFSAlgo fcfs = new FCFSAlgo();
    SJFAlgo sjf = new SJFAlgo();
    SJRFAlgo sjrf = new SJRFAlgo();
    NPPrioAlgo npp = new NPPrioAlgo();
    PPrioAlgo pp = new PPrioAlgo();
    RoundRobinAlgo rr = new RoundRobinAlgo();
    
    
    public void algoPicker(int algo, int current, ArrayList<SchedulerQueue> queue){
        
    /*    System.out.println("Choose Scheduling Algorithm: ");
        System.out.println("1 - FCFS");
        System.out.println("2 - SJF");
        System.out.println("3 - SJRF");
        System.out.println("4 - Nonpreemptive Priority Scheduling");
        System.out.println("5 - Preemptive Priority Scheduling");
        System.out.println("6 - Round Robin");
    */    
        switch(algo){
            case 1 -> fcfs.fcfsAlgo(current, queue);
            case 2 -> sjf.sjfAlgo(current, queue);
            case 3 -> sjrf.sjrfAlgo(current, queue);
            case 4 -> npp.nppAlgo();
            case 5 -> pp.ppAlgo();
            case 6 -> rr.setQuantum();
            default -> System.out.println("Invalid Input!");
        }
    }
}
