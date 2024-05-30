package Processes;

import MultiQueue.SchedulerQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SJRFAlgo {
    int demotedId;
    int cycleCounter = 0;
    int processCounter = 0;

    public void sjrfAlgo(int current, ArrayList<SchedulerQueue> queue) {
        AlgoPicker a = new AlgoPicker();
        ArrayList<Process> processes = new ArrayList<>();
        Collections.addAll(processes, Process.process);

        // Clear the Process.process array
        Process.process = new Process[50];

        // Sort by arrival time
        processes.sort(Comparator.comparingInt(Process::getArrivalTime));

        while (!processes.isEmpty() || !InitiateProcess.list.isEmpty()) {
            // Add processes that have arrived
            while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= cycleCounter) {
                addProcess(processes.remove(0));
            }

            if (!InitiateProcess.list.isEmpty()) {
                Process currentProcess = InitiateProcess.list.getFirst();
                InitiateProcess.list.removeFirst();

                while (currentProcess.getBurstTime() > 0) {
                    System.out.print("Performing at Cycle Time " + cycleCounter + " : ");
                    System.out.println("Process " + currentProcess.getProcessId() + " at Queue: " + queue.get(current).getId());
                    cycleCounter++;
                    currentProcess.setBurstTime(currentProcess.getBurstTime() - 1); // Decrement burst time

                    // Add processes that arrive during this cycle
                    while (!processes.isEmpty() && processes.get(0).getArrivalTime() <= cycleCounter) {
                        addProcess(processes.remove(0));
                    }

                    // Check preemptive condition
                    if (!InitiateProcess.list.isEmpty() &&
                        InitiateProcess.list.getFirst().getBurstTime() < currentProcess.getBurstTime()) {

                        demotedId = currentProcess.getProcessId();
                        demoteProcess(currentProcess); // Use the demoteProcess method to handle demotion
                        currentProcess = InitiateProcess.list.getFirst();
                        InitiateProcess.list.removeFirst(); // Set the new current process

                    }
                }
                // Current process has finished execution and should not be re-added to the list
            } else {
                // If no process is ready, just increment the cycle counter
                System.out.println("Performing at Cycle Time " + cycleCounter + " : ");
                cycleCounter++;
            }
        }
        System.out.println("Queue Done!");
        int next = queue.get(0).getAlgorithm();
        System.out.println(next);
        a.algoPicker(next, 0, queue);
    }

    public void addProcess(Process p) {
        int index = 0;
        while (index < InitiateProcess.list.size() &&
               InitiateProcess.list.get(index).getBurstTime() <= p.getBurstTime()) {
            index++;
        }
        InitiateProcess.list.add(index, p);
    }

    public void demoteProcess(Process p) {
        // Check for the first null element in the array to place the demoted process
        int index = 0;
        while (index < Process.process.length && Process.process[index] != null) {
            index++;
        }
        // If the array is full, expand it
        if (index >= Process.process.length) {
            Process[] newArray = new Process[Process.process.length + 10];
            System.arraycopy(Process.process, 0, newArray, 0, Process.process.length);
            Process.process = newArray;
        }
        Process.process[index] = new Process(demotedId, p.getBurstTime(), 0, p.getPriority());

        System.out.println("Process Demoted!");
        System.out.println("Id, BTime, ATime, Prio: " + Process.process[index].getProcessId() + " " + Process.process[index].getBurstTime() + " " + Process.process[index].getArrivalTime() + " " + Process.process[index].getPriority());
    }
}
