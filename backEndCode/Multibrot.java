package backEndCode;

public class Multibrot implements fractals { 
	//see instance variable in Burning for details 
	private int _coreNum = 3;
	private int[][] _results  = new int[2048][2048];
	private double[][] _frac;
	private double _xPrime; 
	private double _yPrime;
	private double _temp;
	private double _escapeDistance = 2;
	private int _escapeTimeUpperBound = 255;
	private int[][] _resultsTemp;
	private double constGapX = 2.0;
	private double constGapY = 2.6;
	private double constStartX =-1.0;
	private double constStartY =-1.3;
	private  double gapX =2.0/2047;
	private  double gapY = 2.6/2047;
	private double startX =-1.0;
	private double startY = -1.3;
	public Multibrot(){     //constructor of the Multibrot fractal
		_frac = new double[2048][2048];
		 _resultsTemp = new int[2048][2048];
	}
	
	@Override
	public double TransX(int row) { //see interface comments
		double div= 2.0/2047;
		return (-1.0)+(div*row);
	}
	
	@Override
	public double TransY(int col) { //see interface comments
		double div= 2.6/2047;
		return -1.3+div*col;
		
	}
	public double TransX1(int row) { //see interface comments
		double div = constGapX/2047;
		return constStartX+div*row;
	}

	
	public double TransY1(int col) {  //see interface comments
		double div = constGapY/2047;
		return constStartY+div*col;
	}
	public void update() {  //see interface comments
		for (int i=0; i<_coreNum;i++)
		update(i);
	}
	@Override
	public int[][] update( int num ) {  
		//calc for the exact coordinate for the x and y
				int lower = (int)Math.round(num * 2048/(double)_coreNum);		//the lower bound or starting index of the matrix
				int upper = (int)Math.round((double)(num+1)*2048/(double)_coreNum);		//the upper bound of ending index of the matrix
				int k = upper - lower; 					//find the difference between the bounds to find the appropriate size of the matrix
				int[][] mfrac = new int[k][2048];		//initialize the matrix fraction
				for (int r = lower; r < upper ; r++) {
					for (int c = 0; c < 2048 ; c++) {
						// translate the scale of the lower to upper bound to the scale of the range for this object
						mfrac[r - lower][c] = calcEscape((startX+gapX*(r-lower)+lower*gapX), (startY+gapY*c));   //calculate escape time and store in the matrix fraction
					}
				}
				return mfrac;
	}
	
	public int[][] getMatrix() {   //see interface comments
		constGapX = 2.0;
	     constGapY = 2.6;
		 constStartX =-1.0;
		 constStartY =-1.3;
		 gapX =2.0/2047;
		gapY = 2.6/2047;
		 startX =-1.0;
		startY = -1.3;
		return _results;
	}
	
	@Override
	public double ClcX(double x, double y) {  //see interface comments
		_xPrime = _xPrime*_xPrime*_xPrime - (3 * _xPrime*_yPrime*_yPrime) + x;
		return _xPrime;
	}
	@Override
	public double ClcY(double x, double y) { //see interface comments
		_yPrime = (3 * _yPrime * _temp*_temp) - _yPrime*_yPrime*_yPrime+ y;
		return _yPrime;
	}
	public int calcEscape(double x, double y) {//see interface comments and for comment in Burning for details
		_xPrime = x;
		_yPrime = y;
		double distance = Math.sqrt((Math.pow(_xPrime, 2) + Math.pow(_yPrime, 2)));
		int passes = 0;
		while (distance <= _escapeDistance && passes < _escapeTimeUpperBound) { //distance not confirmed
			_temp=_xPrime;
			_xPrime = ClcX(x, y);
			_yPrime = ClcY(x, y);
			passes = passes + 1;
			distance = Math.sqrt((_xPrime*_xPrime) + (_yPrime*_yPrime));
		}
		return passes;
	}

	@Override
	public void setEscapeDistance(double newDistance) {
		// TODO Auto-generated method stub
		_escapeDistance = newDistance;
	}

	@Override
	public void setEscapeTime(int time) {
		// TODO Auto-generated method stub
		_escapeTimeUpperBound = time;
	}

	@Override
	public int[][] getMatrixZoom(int x,int y, int width, int height) {  //see interface comments
		 gapX = (TransX1(x+width) - TransX1(x))/2047;
		    gapY = (TransY1(y+height) - TransY1(y))/2047;
			startX = TransX1(x);
		    startY = TransY1(y);
			constGapX = gapX*2047;
			constGapY = gapY*2047;
			constStartX = startX;
			constStartY = startY;
			
			
			return _resultsTemp;
	}

	@Override
	public void setCoreNum(int coreNum) {
		// TODO Auto-generated method stub
		_coreNum = coreNum;
	}
}
