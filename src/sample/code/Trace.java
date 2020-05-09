package sample.code;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public  class Trace extends Filter {
 
    Pipe _dataINPipe;
    Pipe _dataOUTPipe;
     
    public Trace(Pipe _dataINPipe, Pipe _dataOUTPipe) {
		super();
		this._dataINPipe = _dataINPipe;
		this._dataOUTPipe = _dataOUTPipe;
	}
    
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
		// TODO Auto-generated method stub
		while (true) {
			String opt = _dataINPipe.dataOUT();
			int op1 = -1;
			int op2 = -1;
			int  res = -1;
			String sortie ="";

			switch (opt){
				case "+":// addition
				case "*":// produit
					op1 = Integer.parseInt(_dataINPipe.dataOUT());
					op2 = Integer.parseInt(_dataINPipe.dataOUT());
					res = Integer.parseInt(_dataINPipe.dataOUT());
					sortie = op1 +" "+opt+" "+op2+"="+res;
					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter("trace.txt", true));
						writer.append('\n');
						writer.append(sortie);
						writer.close();
					}  catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case "!":// facto
					int f = 1;
					op1 = Integer.parseInt(_dataINPipe.dataOUT());
					res = Integer.parseInt(_dataINPipe.dataOUT());
					sortie = op1 +" "+opt+"="+res;
					try {
						BufferedWriter writer = new BufferedWriter(new FileWriter("trace.txt", true));
						writer.append('\n');
						writer.append(sortie);
						writer.close();
					}  catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case "trc"://trace
					try {
						Scanner read = new Scanner(new File("trace.txt"));
						while (read.hasNextLine()) {
							sortie = "trace : " + read.nextLine();
						}
						read.close();


					}  catch (IOException e) {
						e.printStackTrace();
					}
					break;
				default:
					res = -1;
					break;
			}



			_dataOUTPipe.dataIN(sortie);
		}
	}
}
 