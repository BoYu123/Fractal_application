package backEndCode;

public interface fractals {
      public double [][] _frac = new double[2048][2048];
      public int [][] _results = new int [2048][2048];
      public double TransX(int x);//see TransY
      public double TransY(int y);
      /*@param TransX and TransY take in a row and a column of
       *_results respectively.
       *@return the value of the x or y coordinate accounting for 
       *the range and scope of the specific fractal and accounting 
       *for the size and length of the matrix.
      */
      public int[][] getMatrixZoom(int x,int y, int width, int height) ;
      /*@param (x,y) are the coordinates of the top left hand of the
       * drawn rectangle and width and height are the width and height
       * of the rectangle
       * @return a new matrix used to recalculate the fractal
       */
      public  int[][] update(int num);
		// TODO Auto-generated method stub
		
	
      /*
       * update computes a matrix which store for the escape time.
       */
      public void setCoreNum(int coreNum);
      public void setEscapeDistance(double newDistance);
      /*@param newDistance is the new intended escape distance.
       * setEscapeDistance changes the escapeDistance of the 
       * fractal object to newDistance
       */
      public void setEscapeTime(int newTime);
      /*@param newTime is the new intended maximum escape time.
       * setEscapeTime changes the escapeTimeUpperBound of the
       * fractal object to newTime
       */
      
      public double ClcX(double x,double y);//see ClcY
      public double ClcY(double x,double y);
      /*@param ClcX and ClcY take in a set of coordinate points and
       *@return a new X and Y coordinate respectively using the
       *escape time algorithm unique to each fractal. The new X and 
       *Y coordinates are assigned to instance variables xPrime and 
       *yPrime respectively.
       *See calcEscape for the uses of xPrime and yPrime.
       */
      public int[][] getMatrix(); //returns the _results matrix
      public int calcEscape(double a, double b);
      /*@param calcEscape takes in a set of coordinate points and
       *@returns the escape time. The parameters a and b, which are
       *the x and y coordinates respectively, are passed to ClcX
       *and ClcY and looped until the distance of the point with 
       *coordinates xPrime and yPrime (returned from ClcX and ClcY)
       *from the origin exceeds the set value of the escape distance.
       */
}
