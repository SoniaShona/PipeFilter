package sample.code;
public  class Calcul extends Filter {
 
    Pipe _dataINPipe;
    Pipe _dataOUTPipe;
    
    public Calcul(Pipe _dataINPipe, Pipe _dataOUTPipe) {
		super();
		this._dataINPipe = _dataINPipe;
		this._dataOUTPipe = _dataOUTPipe;
	}
    public String getData(){
        return _dataINPipe.dataOUT();
    }
     
    public void sendData( String tempData)
	{
        _dataOUTPipe.dataIN(tempData);
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		execute();
	}
	@Override
	synchronized void execute() {
    	while (true){
			String opt = _dataINPipe.dataOUT();

			int op1 = -1;
			int op2 = -1;
			int  res;
			String sortie ="";
			switch (opt){
				case "+":// addition
					op1 = Integer.parseInt(_dataINPipe.dataOUT());
					op2 = Integer.parseInt(_dataINPipe.dataOUT());
					res = op1 + op2;
					sortie = op1 +" "+opt+" "+op2+"="+res;
					_dataOUTPipe.dataIN(opt);
					_dataOUTPipe.dataIN(String.valueOf(op1));
					_dataOUTPipe.dataIN(String.valueOf(op2));
					_dataOUTPipe.dataIN(String.valueOf(res));
					break;
				case "*":// produit
					op1 = Integer.parseInt(_dataINPipe.dataOUT());
					op2 = Integer.parseInt(_dataINPipe.dataOUT());
					res = op1 * op2;
					sortie = op1 +" "+opt+" "+op2+"="+res;
					_dataOUTPipe.dataIN(opt);
					_dataOUTPipe.dataIN(String.valueOf(op1));
					_dataOUTPipe.dataIN(String.valueOf(op2));
					_dataOUTPipe.dataIN(String.valueOf(res));
					break;
				case "!":// facto
					int f = 1;
					op1 = Integer.parseInt(_dataINPipe.dataOUT());
					for (int i = 1; i <= op1; i++)
						f = f * i;
					res = f;
					sortie = op1 +" "+opt+"="+res;
					_dataOUTPipe.dataIN(opt);
					_dataOUTPipe.dataIN(String.valueOf(op1));
					_dataOUTPipe.dataIN(String.valueOf(res));
					break;
				default:
					res = -1;
					break;
			}

		}
	}
}
 