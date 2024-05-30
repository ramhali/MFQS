package Processes;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class Process {
    static Process[] process;
    
    private final int processId;
    private final int arrivalTime;
    
    private int burstTime;
    private int priority;
    
    Process (int processId, int burstTime, int arrivalTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
    }
    
    public void setBurstTime(int bTime){
        this.burstTime = bTime;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public int getProcessId(){
        return processId;
    }
    public int getBurstTime(){
        return burstTime;
    }
    public int getArrivalTime(){
        return arrivalTime;
    }
    public int getPriority(){
        return priority;
    }
    
}
