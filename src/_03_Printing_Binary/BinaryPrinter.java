package _03_Printing_Binary;

public class BinaryPrinter {
	/*
	 * Complete the methods below so they print the passed in parameter in binary.
	 * Do not use the Integer.toBinaryString method!
	 * Use the main method to test your methods.
	 */

	
	public void printByteBinary(byte b) {
		// We first want to print the bit in the one's place
		// Shift b seven bits to the right
		// Use the & operator to "mask" the bit in the one's place
		// This can be done by "anding" (&) it with the value of 1
		
		// Print the result using System.out.print (NOT System.out.println)
		
		//Use this method to print the remaining 7 bits of b
		

		String b1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
		for (int i = 0; i < b1.length(); i++) {
			System.out.print(b1.charAt(i));
		}
		System.out.println();
	}
	
	public void printShortBinary(short s) {
		// Create 2 byte variables
		byte one;
		byte two;
		// Use bit shifting and masking (&) to save the first
		// 8 bits of s in one byte, and the second 8 bits of
		// s in the other byte
		one = (byte) (s>>8);
		two = (byte) ((s)^(one<<8));

		printByteBinary(two);
		printByteBinary(one);
		// Call printByteBinary twice using the two bytes
		// Make sure they are in the correct order
	}
	
	public void printIntBinary(int i) {
		// Create 2 short variables
		short one;
		short two;
		// Use bit shifting and masking (&) to save the first
		// 16 bits of i in one short, and the second 16 bits of
		// i in the other short
		one =  (short) (i>>16);
		two = (short) ((i)^(one<<16));

		printShortBinary(two);
		printShortBinary(one);
		// Call printShortBinary twice using the two short variables
		// Make sure they are in the correct order
	}
	
	public void printLongBinary(long l) {
		// Use the same method as before to complete this method

		int one;
		int two;
		one =  (int) (l>>31);
		two = (int) ((l)|(one<<31));

		printIntBinary(two);
		printIntBinary(one);
	}
	
	public static void main(String[] args) {
		// Test your methods here
		System.out.println("Binary: ");
		BinaryPrinter printer = new BinaryPrinter();
		printer.printByteBinary((byte) 3);
		System.out.println();
		short x = (short) ((Math.pow(2, 15)-1));
		System.out.println("Short: ");
		printer.printShortBinary(x);
		System.out.println();
		x = (short) ((Math.pow(2, 14)-1));
		printer.printShortBinary(x);
		System.out.println("Int: ");
		printer.printIntBinary((int) (Integer.MAX_VALUE));
		System.out.println("Int: ");
		printer.printLongBinary((long) (Long.MAX_VALUE));
		System.out.println();
		printer.printLongBinary((long) (Long.MAX_VALUE/Integer.MAX_VALUE));
		System.out.println(Long.MAX_VALUE/Integer.MAX_VALUE);
	}
}
