import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Grade Calculator");

        // UI elements
        Label subjectCountLabel = new Label("Enter number of subjects:");
        TextField subjectCountField = new TextField();
        Button enterButton = new Button("Enter");

        // GridPane for dynamic input fields
        GridPane marksGrid = new GridPane();
        marksGrid.setHgap(10);
        marksGrid.setVgap(10);
        marksGrid.setPadding(new Insets(10, 10, 10, 10));

        Label totalMarksLabel = new Label("Total Marks:");
        Label averagePercentageLabel = new Label("Average Percentage:");
        Label gradeLabel = new Label("Grade:");
        TextField totalMarksField = new TextField();
        totalMarksField.setEditable(false);
        TextField averagePercentageField = new TextField();
        averagePercentageField.setEditable(false);
        TextField gradeField = new TextField();
        gradeField.setEditable(false);

        enterButton.setOnAction(e -> {
            int numSubjects = Integer.parseInt(subjectCountField.getText());
            marksGrid.getChildren().clear();
            for (int i = 0; i < numSubjects; i++) {
                Label label = new Label("Subject " + (i + 1) + ":");
                TextField textField = new TextField();
                marksGrid.add(label, 0, i);
                marksGrid.add(textField, 1, i);
            }
            Button calculateButton = new Button("Calculate");
            calculateButton.setOnAction(event -> {
                int totalMarks = 0;
                for (int i = 0; i < numSubjects; i++) {
                    TextField textField = (TextField) marksGrid.getChildren().get(2 * i + 1);
                    int marks = Integer.parseInt(textField.getText());
                    totalMarks += marks;
                }
                double averagePercentage = (double) totalMarks / numSubjects;
                char grade;
                if (averagePercentage >= 90) {
                    grade = 'A';
                } else if (averagePercentage >= 80) {
                    grade = 'B';
                } else if (averagePercentage >= 70) {
                    grade = 'C';
                } else if (averagePercentage >= 60) {
                    grade = 'D';
                } else {
                    grade = 'F';
                }
                totalMarksField.setText(String.valueOf(totalMarks));
                averagePercentageField.setText(String.format("%.2f", averagePercentage));
                gradeField.setText(String.valueOf(grade));
            });
            marksGrid.add(calculateButton, 0, numSubjects, 2, 1);
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(subjectCountLabel, subjectCountField, enterButton, marksGrid,
                totalMarksLabel, totalMarksField, averagePercentageLabel, averagePercentageField, gradeLabel, gradeField);

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
