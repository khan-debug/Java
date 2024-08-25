import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Optional;

public class RoundRobinSchedulerApp extends Application {

    private int processNumber = 1; // Process number counter

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Process Scheduler - JavaFX");

        Label processLabel = new Label("Enter the number of processes:");
        TextField processTextField = new TextField();
        Button enterButton = new Button("Enter");

        VBox vbox = new VBox(processLabel, processTextField, enterButton);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();

        enterButton.setOnAction(event -> {
            try {
                int n = Integer.parseInt(processTextField.getText());
                QuizProcess[] processes = new QuizProcess[n];

                for (int i = 0; i < n; i++) {
                    System.out.println("\nEnter details for Process " + processNumber);
                    int arrivalTime = getUserInput("Enter Arrival Time for Process " + processNumber + ": ");
                    int burstTime = getUserInput("Enter Burst Time for Process " + processNumber + ": ");

                    processes[i] = new QuizProcess(processNumber++, arrivalTime, burstTime);
                }

                int quantum = getUserInput("\nEnter the time quantum: ");

                calculateCompletionTime(processes, quantum);
                calculateTurnaroundTime(processes);
                calculateWaitingTime(processes);

                TableView<ProcessData> tableView = createTableView(processes);
                HBox resultLayout = createResultLayout(tableView, processes, quantum);

                Stage resultStage = new Stage();
                resultStage.setTitle("Process Scheduler - Results");
                Scene resultScene = new Scene(resultLayout);
                resultStage.setScene(resultScene);
                resultStage.show();

            } catch (NumberFormatException e) {
                showAlert("Invalid input. Please enter a valid number of processes.");
            }
        });
    }

    private int getUserInput(String prompt) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setContentText(prompt);
        Optional<String> result = dialog.showAndWait();
        return result.map(Integer::parseInt).orElse(0);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void calculateCompletionTime(QuizProcess[] processes, int quantum) {
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

    private void calculateTurnaroundTime(QuizProcess[] processes) {
        for (QuizProcess process : processes) {
            process.turnaroundTime = process.completionTime - process.arrivalTime;
        }
    }

    private void calculateWaitingTime(QuizProcess[] processes) {
        for (QuizProcess process : processes) {
            process.waitingTime = process.turnaroundTime - process.burstTime;
        }
    }

    private TableView<ProcessData> createTableView(QuizProcess[] processes) {
        TableView<ProcessData> tableView = new TableView<>();

        TableColumn<ProcessData, Integer> pidColumn = new TableColumn<>("Process ID");
        pidColumn.setCellValueFactory(new PropertyValueFactory<>("pid"));

        TableColumn<ProcessData, Integer> arrivalColumn = new TableColumn<>("Arrival Time");
        arrivalColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));

        TableColumn<ProcessData, Integer> burstColumn = new TableColumn<>("Burst Time");
        burstColumn.setCellValueFactory(new PropertyValueFactory<>("burstTime"));

        TableColumn<ProcessData, Integer> completionColumn = new TableColumn<>("Completion Time");
        completionColumn.setCellValueFactory(new PropertyValueFactory<>("completionTime"));

        TableColumn<ProcessData, Integer> turnaroundColumn = new TableColumn<>("Turnaround Time");
        turnaroundColumn.setCellValueFactory(new PropertyValueFactory<>("turnaroundTime"));

        TableColumn<ProcessData, Integer> waitingColumn = new TableColumn<>("Waiting Time");
        waitingColumn.setCellValueFactory(new PropertyValueFactory<>("waitingTime"));

        tableView.getColumns().addAll(pidColumn, arrivalColumn, burstColumn, completionColumn, turnaroundColumn, waitingColumn);

        ObservableList<ProcessData> data = FXCollections.observableArrayList();
        for (QuizProcess process : processes) {
            data.add(new ProcessData(process.processId, process.arrivalTime, process.burstTime,
                    process.completionTime, process.turnaroundTime, process.waitingTime));
        }

        tableView.setItems(data);

        return tableView;
    }

    private HBox createResultLayout(TableView<ProcessData> tableView, QuizProcess[] processes, int quantum) {
        HBox resultLayout = new HBox();
        resultLayout.setSpacing(10);
        resultLayout.setPadding(new Insets(10));

        Label avgLabel = new Label(String.format("Average Waiting Time: %.2f\nAverage Turnaround Time: %.2f",
                calculateAverageWaitingTime(processes), calculateAverageTurnaroundTime(processes)));
        avgLabel.setStyle("-fx-font-size: 14px;");

        GanttChart ganttChart = new GanttChart(processes);
        HBox ganttChartBox = ganttChart.createGanttChart(quantum);

        resultLayout.getChildren().addAll(tableView, avgLabel, ganttChartBox);

        return resultLayout;
    }

    private double calculateAverageWaitingTime(QuizProcess[] processes) {
        int n = processes.length;
        double totalWaitingTime = 0;

        for (QuizProcess process : processes) {
            totalWaitingTime += process.waitingTime;
        }

        return totalWaitingTime / n;
    }

    private double calculateAverageTurnaroundTime(QuizProcess[] processes) {
        int n = processes.length;
        double totalTurnaroundTime = 0;

        for (QuizProcess process : processes) {
            totalTurnaroundTime += process.turnaroundTime;
        }

        return totalTurnaroundTime / n;
    }

    public class ProcessData {
        private final SimpleIntegerProperty pid;
        private final SimpleIntegerProperty arrivalTime;
        private final SimpleIntegerProperty burstTime;
        private final SimpleIntegerProperty completionTime;
        private final SimpleIntegerProperty turnaroundTime;
        private final SimpleIntegerProperty waitingTime;

        public ProcessData(int pid, int arrivalTime, int burstTime, int completionTime, int turnaroundTime, int waitingTime) {
            this.pid = new SimpleIntegerProperty(pid);
            this.arrivalTime = new SimpleIntegerProperty(arrivalTime);
            this.burstTime = new SimpleIntegerProperty(burstTime);
            this.completionTime = new SimpleIntegerProperty(completionTime);
            this.turnaroundTime = new SimpleIntegerProperty(turnaroundTime);
            this.waitingTime = new SimpleIntegerProperty(waitingTime);
        }

        public int getPid() {
            return pid.get();
        }

        public int getArrivalTime() {
            return arrivalTime.get();
        }

        public int getBurstTime() {
            return burstTime.get();
        }

        public int getCompletionTime() {
            return completionTime.get();
        }

        public int getTurnaroundTime() {
            return turnaroundTime.get();
        }

        public int getWaitingTime() {
            return waitingTime.get();
        }
    }

    public class GanttChart {
        private final int[] pid;
        private final int[] ct;

        public GanttChart(QuizProcess[] processes) {
            int n = processes.length;
            this.pid = new int[n];
            this.ct = new int[n];

            for (int i = 0; i < n; i++) {
                this.pid[i] = processes[i].processId;
                this.ct[i] = processes[i].completionTime;
            }
        }

        public HBox createGanttChart(int quantum) {
            HBox ganttChartBox = new HBox();
            ganttChartBox.setSpacing(10);
            ganttChartBox.setStyle("-fx-border-color: #000000; -fx-padding: 10px;");

            int currentTime = 0;

            for (int i = 0; i < pid.length; i++) {
                Label label = new Label("P" + pid[i]);
                label.setStyle("-fx-background-color: #3498db; -fx-padding: 5px;");
                int executionTime = Math.min(quantum, ct[i] - currentTime);
                label.setMinWidth(executionTime * 20);  // Adjust the multiplier for a better visualization
                currentTime = ct[i];
                ganttChartBox.getChildren().add(label);
            }

            return ganttChartBox;
        }
    }
}
