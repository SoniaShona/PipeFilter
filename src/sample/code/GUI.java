package sample.code;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public  class GUI extends Filter implements Initializable {
	@FXML public TextField op1_tf;
	@FXML public TextField op2_tf;
	@FXML public Label results_label;

	String o1;
	String o2;
	String opt;


	public void setPipes(Pipe _dataINPipe, Pipe _dataOUTPipe){
		this._dataINPipe = _dataINPipe;
		this._dataOUTPipe = _dataOUTPipe;
	}

	Pipe _dataINPipe;
    Pipe _dataOUTPipe;
     
    public String getData(){
        return _dataINPipe.dataOUT();
    }
     
    public void sendData( String tempData){
        _dataOUTPipe.dataIN(tempData);
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		execute();
	}

	@Override
	synchronized void execute() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	//Permet de forcer l'utilisateur a entrer des chiffres
		op1_tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue,
								String newValue) {
				if (!newValue.matches("\\d*")) {
					op1_tf.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
		op2_tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue,
								String newValue) {
				if (!newValue.matches("\\d*")) {
					op2_tf.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});

	}

	public void quitOnclick(ActionEvent actionEvent) {
		Stage stage = (Stage) op1_tf.getScene().getWindow();
		stage.close();
		System.exit(0);
    }

	public void traceOnclick(ActionEvent actionEvent){
		if(Desktop.getDesktop().isSupported(java.awt.Desktop.Action.OPEN)){
			try {
				java.awt.Desktop.getDesktop().open(new File("trace.txt"));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public void sumOnclick(ActionEvent actionEvent) {
    	if(!op1_tf.getText().isEmpty()||!op2_tf.getText().isEmpty()){
    		o1 = op1_tf.getText();
    		o2 = op2_tf.getText();
    		opt="+";
			_dataOUTPipe.dataIN(opt);
			_dataOUTPipe.dataIN(o1);
			_dataOUTPipe.dataIN(o2);
			results_label.setText(_dataINPipe.dataOUT());
		}
	}

	public void prodOnclick(ActionEvent actionEvent) {
		if(!op1_tf.getText().isEmpty()||!op2_tf.getText().isEmpty()){
			o1 = op1_tf.getText();
			o2 = op2_tf.getText();
			opt="*";
			_dataOUTPipe.dataIN(opt);
			_dataOUTPipe.dataIN(o1);
			_dataOUTPipe.dataIN(o2);

			results_label.setText(_dataINPipe.dataOUT());
		}
	}

	public void factoOnclick(ActionEvent actionEvent) {
		if(!op1_tf.getText().isEmpty()){
			o1 = op1_tf.getText();
			opt="!";
			_dataOUTPipe.dataIN(opt);
			_dataOUTPipe.dataIN(o1);

			results_label.setText(_dataINPipe.dataOUT());
		}
	}
}
 