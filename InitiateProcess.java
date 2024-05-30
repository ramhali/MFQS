package Processes;
import MultiQueue.*;

import java.util.Scanner;
import java.util.LinkedList;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class InitiateProcess {
    Scanner scanner = new Scanner(System.in);
    static LinkedList<Process> list = new LinkedList();
    int number;
        
    public void initiateProcess(){
        System.out.println("Enter Number of Processes: ");
        number = scanner.nextInt();
        Process.process = new Process[number];
        GetInput g = new GetInput();
        
        for(int i = 1; i <= number; i++){
            System.out.println("Set Arrival Time for Process " +i +": ");
            int aTime = scanner.nextInt();
            System.out.println("Set Burst Time for Process " +i +": ");
            int bTime = scanner.nextInt();
            System.out.println("Set Priority for Process " +i +": ");
            int priority = scanner.nextInt();
            
            Process.process[i-1] = new Process(i, bTime, aTime, priority);
            
        }
        
        g.getInput();
        
    }
}
