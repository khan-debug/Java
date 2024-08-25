import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Queue;

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

    // Getters and setters for JavaFX TableView
    public int getProcessId() {
        return processId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }
}

public class JavaFXQuizApp extends Application {

    private final ObservableList<QuizProcess> processList = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Round Robin Scheduler");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Label processesLabel = new Label("Number of Processes:");
        TextField processesField = new TextField();

        Label quantumLabel = new Label("Time Quantum:");
        TextField quantumField = new TextField();

        Button submitButton = new Button("Submit");

        grid.add(processesLabel, 0, 0);
        grid.add(processesField, 1, 0);
        grid.add(quantumLabel, 0, 1);
        grid.add(quantumField, 1, 1);
        grid.add(submitButton, 1, 2);

        submitButton.setOnAction(e -> {
            int numProcesses = Integer.parseInt(processesField.getText());
            int quantum = Integer.parseInt(quantumField.getText());

            processList.clear();

            for (int i = 0; i < numProcesses; i++) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Process Details");
                dialog.setHeaderText("Enter details for Process " + (i + 1));
                dialog.setContentText("Enter Arrival Time:");
                int arrivalTime = Integer.parseInt(dialog.showAndWait().get());

                dialog.setContentText("Enter Burst Time:");
                int burstTime = Integer.parseInt(dialog.showAndWait().get());

                processList.add(new QuizProcess(i + 1, arrivalTime, burstTime));
            }

            calculateCompletionTime(processList.toArray(new QuizProcess[0]), quantum);
            calculateTurnaroundTime(processList.toArray(new QuizProcess[0]));
            calculateWaitingTime(processList.toArray(new QuizProcess[0]));

            displayResults(processList);
        });

        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void displayResults(ObservableList<QuizProcess> processes) {
        Stage resultStage = new Stage();
        resultStage.setTitle("Scheduling Results");

        VBox vbox = new VBox();
        vbox.setSpacing(10);

        TableView<QuizProcess> table = createTable(processes);
        LineChart<Number, Number> chart = createChart(processes);

        vbox.getChildren().addAll(table, chart);

        Scene scene = new Scene(vbox, 800, 600);
        resultStage.setScene(scene);

        resultStage.show();
    }

    private TableView<QuizProcess> createTable(ObservableList<QuizProcess> processes) {
        TableView<QuizProcess> table = new TableView<>();
        table.setEditable(false);

        TableColumn<QuizProcess, Integer> processIdCol = new TableColumn<>("Process");
        processIdCol.setCellValueFactory(new PropertyValueFactory<>("processId"));

        TableColumn<QuizProcess, Integer> arrivalTimeCol = new TableColumn<>("Arrival Time");
        arrivalTimeCol.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));

        TableColumn<QuizProcess, Integer> burstTimeCol = new TableColumn<>("Burst Time");
        burstTimeCol.setCellValueFactory(new PropertyValueFactory<>("burstTime"));

        TableColumn<QuizProcess, Integer> completionTimeCol = new TableColumn<>("Completion Time");
        completionTimeCol.setCellValueFactory(new PropertyValueFactory<>("completionTime"));

        TableColumn<QuizProcess, Integer> turnaroundTimeCol = new TableColumn<>("Turnaround Time");
        turnaroundTimeCol.setCellValueFactory(new PropertyValueFactory<>("turnaroundTime"));

        TableColumn<QuizProcess, Integer> waitingTimeCol = new TableColumn<>("Waiting Time");
        waitingTimeCol.setCellValueFactory(new PropertyValueFactory<>("waitingTime"));

        table.getColumns().addAll(processIdCol, arrivalTimeCol, burstTimeCol, completionTimeCol, turnaroundTimeCol, waitingTimeCol);
        table.setItems(processes);

        return table;
    }

    private LineChart<Number, Number> createChart(ObservableList<QuizProcess> processes) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Process");
        yAxis.setLabel("Completion Time");

        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle("Process Completion Times");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Completion Time");

        for (QuizProcess process : processes) {
            series.getData().add(new XYChart.Data<>(process.processId, process.completionTime));
        }

        chart.getData().add(series);

        return chart;
    }
}
