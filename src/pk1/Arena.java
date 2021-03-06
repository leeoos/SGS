package pk1;

import java.util.Random;

public class Arena {
	
    public Cell[][] matrix;
    private int i = 0;
    private int j = 0;
    
    public Arena() {
        matrix = new Cell[Board.space][Board.space+10];
        i = j = matrix.length;
        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[i].length; j++){
                matrix[i][j] = new Null();
            }
        }
    }
    
    public synchronized Cell get(int i, int j) {
		return matrix[i][j];
    }
    
    public synchronized void addi(Cell cell) {
    	matrix[i][j] = cell;
    	j++;
    	if (j==Board.space+10) {
    		j=0; i++;
    		if (i > Board.space) {
        		System.err.println("\nSpaceOverflow\n");
        		Game.stop = true;
        		Game.Killer.interrupt();
    		}
    	}
    	shuffle(this.matrix);
    }
    
	static void shuffle(Cell[][] a) {
	    Random random = new Random();

	    for (int i = a.length - 1; i > 0; i--) {
	        for (int j = a[i].length - 1; j > 0; j--) {
	            int m = random.nextInt(i + 1);
	            int n = random.nextInt(j + 1);

	            Cell temp = a[i][j];
	            a[i][j] = a[m][n];
	            a[m][n] = temp;
	        }
	    }
	}
    
    public synchronized int size() {
		return Board.space*Board.space;
    }
    
    public synchronized int hight() {
		return matrix.length;
    }
    
    public synchronized int length() {
		return matrix[1].length;
    }
    
    public synchronized void add(Cell cell) {
    	
    	boolean ctrl = true;
    	while(ctrl) {
    		int i = new Random().nextInt(this.i);
    		int j = new Random().nextInt(this.j);
    		if (matrix[i][j].name == null) {
        		matrix[i][j] = cell;
        		ctrl = false;
    		}
    	}
    	
    	shuffle(this.matrix);
    }

}
