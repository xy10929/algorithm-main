package test;

public class test {
	public static void main(String[] args) {
		
		//input
		int n = 19;
		int x =  5;
		String cipher = "CoachBeardisthebest";
		
		//fuction
		char[][] t = new char[x][n];
		char[] c = cipher.toCharArray();
		boolean p = true;
		for (int row = 0, col = 0, index = 0; col < n; col++) {
			t[row][col] = c[index++];
			if(row == 0) {
				row++;
				p = true;
			}else if (row == x - 1) {
				row--;
				p = false;
			}else {
				if(p) {
					row++;
				}else {
					row--;
				}
			}
		}
		for(int row = x - 1; row >= 0; row--) {
			for(int col = n - 1; col >= 0 ; col--) {
				if(t[row][col] >= 'A' && t[row][col] <= 'Z' ||
				   t[row][col] >= 'a' && t[row][col] <= 'z') {
					System.out.print(t[row][col] );
				}
			}
		}
		return;
	}
}
