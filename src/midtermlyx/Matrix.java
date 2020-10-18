package midtermlyx;

import java.io.*;
import java.util.ArrayList;

public class Matrix {
	double[][] mtx = null;
	
	public Matrix(int m, int n) {
		this.mtx = new double[m][n];
		
	}
	
	public int numRows() {
		return mtx.length;
	}
	
	public double[] getRow(int i) {
		return mtx[i];
	}
	
	public double getElement(int i, int j) {
		return mtx[i][j];
	}
	
	public void setElement(int i, int j, double val) {
		mtx[i][j] = val;
	}
	
	public int numColumns() {
		return mtx[0].length;
	}
	
	public void save(String filename) {
		try {
			PrintWriter out = null;
			out = new PrintWriter(new FileWriter(filename));
			out.println(numRows() + "  " + numColumns());
			for (int i = 0; i < numRows(); i++) {
				for (int j = 0; j < numColumns(); j++) {
						out.print(String.valueOf(mtx[i][j]) + "  ");
				}
				out.flush();
				out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}


	}


	
	public static Matrix read(String filename) throws ExceptionWrongMatrixValues, ExceptionWrongMatrixDimension {
		ArrayList<String> readList = new ArrayList<>();
		try {
			FileReader f = new FileReader(filename);
			BufferedReader in = new BufferedReader(f);
			String ln = in.readLine();
			int row = Integer.parseInt(ln.split(" ")[0]);
			int column = Integer.parseInt(ln.split(" ")[1]);
			int columnCnt = 0;
			System.out.println(ln);
			while ( (ln = in.readLine()) != null) {
				System.out.println(ln);
				if (ln.split(" ").length != column && columnCnt > 0) {
					throw new ExceptionWrongMatrixDimension();
				}

				columnCnt++;

			}
			if (columnCnt != row) {
				throw new ExceptionWrongMatrixDimension();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.err.println("Caught NumberFormatException: " + e.getMessage());
			throw new ExceptionWrongMatrixValues();
		}


		return null;
	}
	
	public Matrix sum(Matrix m) {
		return null;
	}
	
	// this will come in handy for the multiplication part
	public double[] getColumn(int j) {
		if (j >= this.numColumns()) {
			return null;
		}
		double[] col = new double[this.numRows()];
		for (int i=0; i<mtx.length; i++) {
			col[i] = mtx[i][j];
		}
		return col;
	}
	
	public Double dotproduct(double[] v1, double[] v2) {
		Double result = null;
		if (v1.length == v2.length) {
			result = 0.0;
			for (int i = 0; i < v1.length; i++) {
				result += v1[i] * v2[i];
			}
		}
		return result;
	}
	
	public Matrix product(Matrix m) {
		int m1Row = numRows();
		int m1Column = numColumns();
		int m2Row = m.numRows();
		int m2Column = m.numColumns();
		if (m1Column == m2Row) {
			Matrix productResult = new Matrix(m1Row, m2Column);
			for (int i = 0; i < m1Row; i++) {
				for (int j = 0; j < m2Column; j++) {
					productResult.setElement(i, j, dotproduct(getRow(i), m.getColumn(j)));
				}
			}
			return productResult;
		}
		return null;
	}
	
	public static void main(String[] args) {
		Matrix m1 = new Matrix(2,3);
		m1.setElement(0, 0, 1.0);
		m1.setElement(0, 1, 1.0);
		m1.setElement(0, 2, 1.0);
		m1.setElement(1, 0, 1.0);
		m1.setElement(1, 1, 1.0);
		m1.setElement(1, 2, 1.0);
		
		Matrix m2 = new Matrix(3,2);
		m2.setElement(0, 0, 2.0);
		m2.setElement(0, 1, 2.0);
		m2.setElement(1, 0, 2.0);
		m2.setElement(1, 1, 3.0);
		m2.setElement(2, 0, 3.0);
		m2.setElement(2, 1, 3.0);
		
		Matrix result = m1.product(m2);
		m1.save("m1");
		m2.save("m2");
		result.save("result");
		
		try {
			m1 = Matrix.read("src\\midtermlyx\\dependency\\m1");
			m2 = Matrix.read("src\\midtermlyx\\dependency\\m2");
			Matrix m3 = Matrix.read("src\\midtermlyx\\dependency\\result");
		} catch (Exception e) {
			System.err.println("error: " + e);
		}
		System.out.println("read && save complete");
		try {
			Matrix badMatrix = Matrix.read("src\\midtermlyx\\dependency\\wrongcolumns");
		} catch (ExceptionWrongMatrixDimension e1) {
			System.err.println("wrong dimensions: " + e1);
		} catch (ExceptionWrongMatrixValues e2) {
			System.err.println("wrong matrix values: " + e2);
		}


		try {
			Matrix badMatrix2 = Matrix.read("src\\midtermlyx\\dependency\\wrongrows");
		} catch (ExceptionWrongMatrixValues e) {
			System.err.println("wrong dimensions: " + e);
		} catch (ExceptionWrongMatrixDimension e) {
			System.err.println("wrong matrix values: " + e);
		}
		
		return;
		
		
	}
	
}
