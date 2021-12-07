package _01_Bit_Shifting;

public class BitShifter {
	public static void main(String[] args) {
		// 1. Jot down the value of num in binary.
		int num = 4;
		int numShifted = num << 3;
		
		// 2. Print the value of numShifted, and convert that value to binary.
		System.out.println(num);
		System.out.println(Integer.toBinaryString(num));
		System.out.println(numShifted);
		System.out.println(Integer.toBinaryString(numShifted));
		// 3. Compare the two binary values. Can you figure out what the << operator is for?
		//moves the binary values over the value specified in the direction specified and adds a 0 to the end if necessary
		// 4. Try shifting num 3 places.
		
		// FYI: Binary values can be shifted to the right as well using the >> operator.	
	}
}
