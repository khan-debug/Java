import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class QuizProcess {

    int processId;
    int arrivalTime;
    int burstTime;
    int remainingTime;
    int completionTime;
    int turnaroundTime;
    int waitingTime;

    public QuizProcess(int processId, int arrivalTime, int burstTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.completionTime = 0;
        this.turnaroundTime = 0;
        this.waitingTime = 0;
    }
}

public class QuizJavaFX
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        QuizProcess[] processes = new QuizProcess[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Process " + (i + 1));
            System.out.print("Enter Arrival Time: ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Enter Burst Time: ");
            int burstTime = scanner.nextInt();

            processes[i] = new QuizProcess(i + 1, arrivalTime, burstTime);
        }

        System.out.print("\nEnter the time quantum: ");
        int quantum = scanner.nextInt();

        calculateCompletionTime(processes, quantum);
        calculateTurnaroundTime(processes);
        calculateWaitingTime(processes);

        printTable(processes);

        calculateAverageTimes(processes);

        scanner.close();
    }

    public static void calculateCompletionTime(QuizProcess[] processes, int quantum) {
        Queue<QuizProcess> queue = new LinkedList<>();
        int currentTime = 0;

        int n = processes.length;
        int i = 0;

        while (i < n || !queue.isEmpty()) {
            while (i < n && processes[i].arrivalTime <= currentTime) {
                queue.add(processes[i]);
                i++;
            }

            if (!queue.isEmpty()) {
                QuizProcess currentProcess = queue.poll();
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

    public static void calculateTurnaroundTime(QuizProcess[] processes) {
        for (QuizProcess process : processes) {
            process.turnaroundTime = process.completionTime - process.arrivalTime;
        }
    }

    public static void calculateWaitingTime(QuizProcess[] processes) {
        for (QuizProcess process : processes) {
            process.waitingTime = process.turnaroundTime - process.burstTime;
        }
    }

    public static void calculateAverageTimes(QuizProcess[] processes) {
        int n = processes.length;
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;

        for (QuizProcess process : processes) {
            totalWaitingTime += process.waitingTime;
            totalTurnaroundTime += process.turnaroundTime;
        }

        double averageWaitingTime = totalWaitingTime / n;
        double averageTurnaroundTime = totalTurnaroundTime / n;

        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
    }

    public static void printTable(QuizProcess[] processes) {
        System.out.println("\nProcess\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        for (QuizProcess process : processes) {
            System.out.printf("%d\t%d\t\t%d\t\t%d\t\t\t%d\t\t\t%d\n",
                    process.processId, process.arrivalTime, process.burstTime,
                    process.completionTime, process.turnaroundTime, process.waitingTime);
        }
    }
}
