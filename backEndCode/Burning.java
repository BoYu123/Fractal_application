package backEndCode;

public class Burning implements fractals { //implements Fractals
	private int _coreNum = 8;
	private int[][] _results  = new int[2048][2048];
	//_results = new int[2048][2048];
	/*
	 * matrix store for the escape time
	 */
	private double[][] _frac;
	/*
	 * matrix is 2048*2048 stored for the fractal
	 * this is redundancy
	 */
	private double _xPrime; 
	/*
	 *store for the current x value
	 */
	private double _yPrime;	
	/*
	 *store for the current y value
	 */
	private double _temp;
	/*
	 *every time store for the value equals to the _xPrime
	 */
	private double _escapeDistance = 2;
	/*
	 * this is the escape distance
	 */
	private int _escapeTimeUpperBound = 255;
	/*
	 * this is the maximum loop time
	 */
	private int[][] _resultsTemp;
	private double constGapX =0.1;
	private double constGapY = 0.105;
	private double constStartX =-1.8;
	private double constStartY =-0.08;

	private  double gapX = 0.1/2047;
	private  double gapY = 0.105/2047;
	private double startX = -1.8;
	private double startY = -0.08;
	public Burning() {  //constructor of the burning ship fractal
		_frac = new double[2048][2048];
		 _resultsTemp = new int[2048][2048];
	}
	
	
	@Override
	public double TransX(int row) {  //see interface comments
		double div = 0.1/2047;
		return -1.8+div*row;
	}
	
	@Override
	public double TransY(int col) {  //see interface comments
		double div = 0.105/2047;	
		return -0.08+div*col;	
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
		//_coreNum = coreNum;
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
				//m2[r - lower][c] = calcEscape((startX+gapX*(r-lower)), (startY+gapY*c));
	
			}
		}
		return mfrac;
		}
	
	
	@Override
	public double ClcX(double x, double y) {  //see interface comments

		_xPrime =  _xPrime*_xPrime-_yPrime*_yPrime + x;
		return _xPrime  ;
	}
	@Override
	public double ClcY(double x, double y) {  //see interface comments

		_yPrime = Math.abs(2 * _temp * _yPrime)+ y;
		return _yPrime;
		
	}
	public int[][] getMatrix() {	//see interface comments
		constGapX =0.1;
		 constGapY = 0.105;
		constStartX =-1.8;
		 constStartY =-0.08;
		 gapX = 0.1/2047;
		gapY = 0.105/2047;
		 startX = -1.8;
	    startY = -0.08;
		return _results;
	}
	
	public int calcEscape(double x, double y) {
		
		_xPrime = x;   //set the _xPrime to the first x value it should be
		_yPrime = y;   //set the _yPrime to the first y value it should be
		double distance = Math.sqrt(Math.pow(_xPrime, 2)+ Math.pow(_yPrime, 2));    //calc the distance 
		int passes = 0;     // used for calc the current loop time
		while (distance <= _escapeDistance && passes < _escapeTimeUpperBound) { 
			_temp=_xPrime;       //set time to the _xprime
			_xPrime = ClcX(x, y);  //compute the _xprime 
			_yPrime = ClcY(x, y);  //compute the _yprime  
			passes = passes + 1;   //current loop time +1
			distance = Math.sqrt(Math.pow(_xPrime, 2) + Math.pow(_yPrime, 2));  //compute for the new distance
		}
		return passes;  //return for the loop time
		
		
		
	}


	@Override
	public void setEscapeDistance(double newDistance) {

		_escapeDistance = newDistance;
		
	}


	@Override
	public void setEscapeTime(int time) {

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
