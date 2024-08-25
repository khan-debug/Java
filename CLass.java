import java.util.*;

 

class quiz {

    int processId;

    int arrivalTime;

    int burstTime;

    int remainingTime;

    int completionTime;

    int turnaroundTime;

    int waitingTime;

 

    public Process(int processId, int arrivalTime, int burstTime) {

        this.processId = processId;

        this.arrivalTime = arrivalTime;

        this.burstTime = burstTime;

        this.remainingTime = burstTime;

        this.completionTime = 0;

        this.turnaroundTime = 0;

        this.waitingTime = 0;

    }

}

 

public class CLass {

 

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

 

        System.out.print("Enter the number of processes: ");

        int n = scanner.nextInt();

 

        Process[] processes = new Process[n];

 

        for (int i = 0; i < n; i++) {

            System.out.println("\nEnter details for Process " + (i + 1));

            System.out.print("Enter Arrival Time: ");

            int arrivalTime = scanner.nextInt();

            System.out.print("Enter Burst Time: ");

            int burstTime = scanner.nextInt();

 

            processes[i] = new Process(i + 1, arrivalTime, burstTime);

        }

 

        System.out.print("\nEnter the time quantum: ");

        int quantum = scanner.nextInt();

 

        calculateCompletionTime(processes, quantum);

 

        calculateTurnaroundTime(processes);

 

        calculateWaitingTime(processes);

 

        calculateAverageTimes(processes);

 

        scanner.close();

    }

 

    public static void calculateCompletionTime(Process[] processes, int quantum) {

        Queue<Process> queue = new LinkedList<>();

        int currentTime = 0;

 

        int n = processes.length;

 

        int i = 0;

 

        while (i < n || !queue.isEmpty()) {

            while (i < n && processes[i].arrivalTime <= currentTime) {

                queue.add(processes[i]);

                i++;

            }

 

            if (!queue.isEmpty()) {

                Process currentProcess = queue.poll();

                int executionTime = Math.min(quantum, currentProcess.remainingTime);

                currentTime += executionTime;

                currentProcess.remainingTime -= executionTime;

 

                if (currentProcess.remainingTime == 0) {

                    currentProcess.completionTime = currentTime;

                } else {

                    queue.add(currentProcess);

                }

            } else {

                currentTime++;

            }

        }

    }

 

    public static void calculateTurnaroundTime(Process[] processes) {

        for (Process process : processes) {

            process.turnaroundTime = process.completionTime - process.arrivalTime;

        }

    }

 

    public static void calculateWaitingTime(Process[] processes) {

        for (Process process : processes) {

            process.waitingTime = process.turnaroundTime - process.burstTime;

        }

    }

 

    public static void calculateAverageTimes(Process[] processes) {

        int n = processes.length;

        double totalWaitingTime = 0;

        double totalTurnaroundTime = 0;

 

        for (Process process : processes) {

            totalWaitingTime += process.waitingTime;

            totalTurnaroundTime += process.turnaroundTime;

        }

 

        double averageWaitingTime = (totalWaitingTime / n)-1;

        double averageTurnaroundTime = (totalTurnaroundTime / n )-1;

        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);

        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);

    }

}