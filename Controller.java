package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	@FXML
	public Label welcomeLabel;
	@FXML
	public ChoiceBox choice;
	@FXML
	public TextField input;
	@FXML
	public Button convert;

	private static final String C_TO_F = "Celsius to Fahrenheit";
	private static final String F_TO_C = "Fahrenheit to Celsius";

		private boolean isC_TO_F = true;

		@Override
		public void initialize(URL location, ResourceBundle resources) {

			choice.getItems().add(C_TO_F);
			choice.getItems().add(F_TO_C);

			choice.setValue(C_TO_F);

			choice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->  {

				if (newValue.equals(C_TO_F)) {
					isC_TO_F = true;
				} else {
					isC_TO_F = false;
				}
			});

			convert.setOnAction(event -> {
				convert();
			});
		}

		private void convert() {

			String inputText = input.getText();

			float enteredTemperature = 0.0f;
			try {
				enteredTemperature = Float.parseFloat(inputText);
			} catch (Exception exception) {
				alert();
				return;
			}

			float newTemp = 0.0f;
			if (isC_TO_F) {
				newTemp = (enteredTemperature * 9 / 5) + 32;
			} else {
				newTemp = (enteredTemperature - 32) * 5 / 9;
			}

			display(newTemp);
		}

		private void alert() {

			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error Occurred");
			alert.setHeaderText("Invalid Temperature Entered");
			alert.setContentText("Please enter a valid temperature");
			alert.show();
		}

		private void display(float newTemperature) {

			String unit = isC_TO_F? " F" : " C";

			System.out.println("The new temperature is: " + newTemperature + unit);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Result");
			alert.setContentText("The new temperature is: " + newTemperature + unit);
			alert.show();
		}
	}


