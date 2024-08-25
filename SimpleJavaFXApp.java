import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class SimpleJavaFXApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Process Scheduler - JavaFX");

        Label processLabel = new Label("Enter the number of processes:");
        TextField processTextField = new TextField();

        VBox vbox = new VBox(processLabel, processTextField);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();

        processTextField.setOnAction(event -> {
            try {
                int n = Integer.parseInt(processTextField.getText());
                int pid[] = new int[n];
                int ar[] = new int[n];
                int bt[] = new int[n];
                int ct[] = new int[n];
                int ta[] = new int[n];
                int wt[] = new int[n];
                int temp;
                float avgwt = 0, avgta = 0;

                for (int i = 0; i < n; i++) {
                    ar[i] = getUserInput("Enter arrival time for process " + (i + 1) + ":");
                    bt[i] = getUserInput("Enter burst time for process " + (i + 1) + ":");
                    pid[i] = i + 1;
                }

                // Sorting according to arrival times
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n - (i + 1); j++) {
                        if (ar[j] > ar[j + 1]) {
                            temp = ar[j];
                            ar[j] = ar[j + 1];
                            ar[j + 1] = temp;
                            temp = bt[j];
                            bt[j] = bt[j + 1];
                            bt[j + 1] = temp;
                            temp = pid[j];
                            pid[j] = pid[j + 1];
                            pid[j + 1] = temp;
                        }
                    }
                }

                // Finding completion times
                for (int i = 0; i < n; i++) {
                    if (i == 0) {
                        ct[i] = ar[i] + bt[i];
                        ta[i] = ct[i] - ar[i];          // turnaround time= completion time- arrival time
                        wt[i] = ta[i] - bt[i];          // waiting time= turnaround time- burst time
                        avgwt += wt[i];               // total waiting time
                        avgta += ta[i];               // total turnaround time
                    } else {
                        if (ar[i] > ct[i - 1]) {
                            ct[i] = ar[i] + bt[i];
                        } else {
                            ct[i] = ct[i - 1] + bt[i];
                        }
                        ta[i] = ct[i] - ar[i];          // turnaround time= completion time- arrival time
                        wt[i] = ta[i] - bt[i];          // waiting time= turnaround time- burst time
                        avgwt += wt[i];               // total waiting time
                        avgta += ta[i];               // total turnaround time
                    }
                }

                avgwt /= n;
                avgta /= n;

                // Display results in a table
                displayResultsInTable(pid, ar, bt, ct, ta, wt, avgwt, avgta);

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

    private void displayResultsInTable(int[] pid, int[] ar, int[] bt, int[] ct, int[] ta, int[] wt, float avgwt, float avgta) {
        TableView<ProcessData> tableView = new TableView<>();

        TableColumn<ProcessData, Integer> pidColumn = new TableColumn<>("PID");
        pidColumn.setCellValueFactory(new PropertyValueFactory<>("pid"));

        TableColumn<ProcessData, Integer> arColumn = new TableColumn<>("Arrival Time");
        arColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));

        TableColumn<ProcessData, Integer> btColumn = new TableColumn<>("Burst Time");
        btColumn.setCellValueFactory(new PropertyValueFactory<>("burstTime"));

        TableColumn<ProcessData, Integer> ctColumn = new TableColumn<>("Completion Time");
        ctColumn.setCellValueFactory(new PropertyValueFactory<>("completionTime"));

        TableColumn<ProcessData, Integer> taColumn = new TableColumn<>("Turnaround Time");
        taColumn.setCellValueFactory(new PropertyValueFactory<>("turnaroundTime"));

        TableColumn<ProcessData, Integer> wtColumn = new TableColumn<>("Waiting Time");
        wtColumn.setCellValueFactory(new PropertyValueFactory<>("waitingTime"));

        tableView.getColumns().addAll(pidColumn, arColumn, btColumn, ctColumn, taColumn, wtColumn);

        ObservableList<ProcessData> data = FXCollections.observableArrayList();
        for (int i = 0; i < pid.length; i++) {
            data.add(new ProcessData(pid[i], ar[i], bt[i], ct[i], ta[i], wt[i]));
        }

        tableView.setItems(data);

        Label avgLabel = new Label(String.format("Average Waiting Time: %.2f\nAverage Turnaround Time: %.2f", avgwt, avgta));

        VBox vbox = new VBox(tableView, avgLabel);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        Stage stage = new Stage();
        stage.setTitle("Process Scheduler - Results");
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class ProcessData {
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
}