package Processes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */

public class PPrioAlgo {
    public void ppAlgo() {
        int cycleCounter = 0;
        ArrayList<Process> processes = new ArrayList<>();
        Collections.addAll(processes, Process.process);
        
        // sort by arrival time
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));

        while (!processes.isEmpty() || !InitiateProcess.list.isEmpty()) {
            // add processes that have arrived
            while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= cycleCounter) {
                addProcess(processes.remove(0));
            }
            
            if (!InitiateProcess.list.isEmpty()) {
                Process currentProcess = InitiateProcess.list.getFirst(); 
                InitiateProcess.list.removeFirst();

                int innerCounter = 1;
                while (innerCounter != 0) {
                    System.out.print("Performing at Cycle Time " + cycleCounter + " : ");
                    System.out.println("Process " + currentProcess.getProcessId());
                    cycleCounter++;

                    // decrement burst time
                    currentProcess.setBurstTime(currentProcess.getBurstTime() - 1);

                    // add processes that arrive while running
                    while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= cycleCounter) {
                        addProcess(processes.remove(0));
                    }

                    // check preemptive condition
                    if (!InitiateProcess.list.isEmpty() &&
                        InitiateProcess.list.getFirst().getPriority() < currentProcess.getPriority()) {
                        
                        // if to preempt
                        addProcess(currentProcess); // return current process
                        currentProcess = InitiateProcess.list.getFirst(); // get process
                        InitiateProcess.list.removeFirst(); // remove process
                        
                        innerCounter = 1; // reset counter for new process
                    } else {
                        
                        innerCounter--; // decrement inner counter for the current process
                    }
                }
                // add the current process back to the list
                if (currentProcess.getBurstTime() > 0) {
                    addProcess(currentProcess);
                }
            } else {
               
                System.out.println("Performing at Cycle Time " + cycleCounter + " : ");
                cycleCounter++;
                
            }
        }
    }

    public void addProcess(Process p) {
        int index = 0;
        while (index < InitiateProcess.list.size() && 
               (InitiateProcess.list.get(index).getPriority() < p.getPriority() || 
               (InitiateProcess.list.get(index).getPriority() == p.getPriority() && 
                InitiateProcess.list.get(index).getArrivalTime() <= p.getArrivalTime()))) {
            index++;
        }
        InitiateProcess.list.add(index, p);
    }
}

