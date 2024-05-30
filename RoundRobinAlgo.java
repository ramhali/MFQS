package Processes;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class RoundRobinAlgo {
    public void rrAlgo(int timeQuantum) {
        Queue<Process> queue = new ArrayDeque<>();
        Process[] processes = Process.process.clone(); // Create a copy to preserve original order
        Arrays.sort(processes, Comparator.comparingInt(Process::getArrivalTime)); // Sort by arrival time

        for (Process p : processes) {
            queue.add(p);
        }

        int cycleCounter = 0;

        while (!queue.isEmpty()) {
            Process currentProcess = queue.poll();
            System.out.println("Program Executing: Process " + currentProcess.getProcessId() +
                    " with " + currentProcess.getBurstTime() + " Burst Time");

            int remainingBurstTime = currentProcess.getBurstTime();
            
            if (remainingBurstTime > timeQuantum) {
                remainingBurstTime -= timeQuantum;
                cycleCounter += timeQuantum;
                currentProcess.setBurstTime(remainingBurstTime);
                queue.add(currentProcess); // Add back to the end of the queue for future execution
            } else {
                cycleCounter += remainingBurstTime;
                currentProcess.setBurstTime(0); // Process is completed
                System.out.println("Process Done!");
            }

            // Print cycle time
            if(currentProcess.getBurstTime() == 0) {
                System.out.println("PROCESS DONE!");
            } else {
                System.out.println("Performing at Cycle Time " + cycleCounter + " : ");
            }            
        }
    }


    public void setQuantum() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Time Quantum: ");
        int tQuantum = scanner.nextInt();

        rrAlgo(tQuantum);

        scanner.close();
    }
}
