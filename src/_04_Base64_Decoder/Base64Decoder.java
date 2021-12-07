package _04_Base64_Decoder;

import java.io.BufferedReader;
import java.io.FileReader;

public class Base64Decoder {
	/*
	 * Base 64 is a way of encoding binary data using text.
	 * Each number 0-63 is mapped to a character. 
	 * NOTE: THIS IS NOT THE SAME AS ASCII OR UNICODE ENCODING!
	 * Since the numbers 0 through 63 can be represented using 6 bits, 
	 * every four (4) characters will represent twenty four (24) bits of data.
	 * 4 * 6 = 24
	 * 
	 * For this exercise, we won't worry about what happens if the total bits being converted
	 * do not divide evenly by 24.
	 * 
	 * If one char is 8 bits, is this an efficient way of storing binary data?
	 * (hint: no)
	 * 
	 * It is, however, useful for things such as storing media data inside an HTML file (for web development),
	 * so that way a web site does not have to look for an image, sound, library, or whatever in a separate location.
	 * 
	 * View this link for a full description of Base64 encoding
	 * https://en.wikipedia.org/wiki/Base64
	 */
	
	
	final static char[] base64Chars = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
	};
	
	//1. Complete this method so that it returns the the element in
	//   the base64Chars array that corresponds to the passed in char.
	public static byte convertBase64Char(char c){
		for (int i = 0; i < base64Chars.length; i++) {
			if(base64Chars[i]==c) {
				return (byte)i;
			}
		}
		return 0;
	}
	
	//2. Complete this method so that it will take in a string that is 4 
	//   characters long and return an array of 3 bytes (24 bits). The byte 
	//   array should be the binary value of the encoded characters.
	public static byte[] convert4CharsTo24Bits(String s){
		int[] indexs = {0, 0, 0, 0};
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < base64Chars.length; j++) {
				if(base64Chars[j]==s.charAt(i)) {
					indexs[i] = j;
				}
			}
		}
		
		int[] positions = new int[24];
		for (int i = 0; i < indexs.length; i++) {
			int[] returnVals = intToBytes(indexs[i]);
			for (int j = 0; j < 6; j++) {
				positions[(6*i)+j] = (byte) returnVals[j];
			}
		}
		System.out.println("");

		byte[] output = encodeToByteArray(positions);
		for (int i = 0; i < output.length; i++) {
			System.out.println(output[i]);
		}
		System.out.println("");
		for (int i = 0; i < positions.length; i++) {
			System.out.print(positions[i]);
		}
		System.out.println("");
		
		return output;
	}
	
	public static int[] intToBytes(int i) {
//		System.out.println(i);
		String b1 = String.format("%8s", Integer.toBinaryString(i & 0xFF)).replace(' ', '0');
		int[] combine = new int[8];
		for (int k = 0; k < b1.length(); k++) {
			combine[k]=Integer.parseInt(String.valueOf(b1.charAt(k)));
		}
		System.out.println("");
		for (int j = 0; j < combine.length; j++) {
			System.out.print(combine[j]);
		}
		System.out.println("");
		return removeExtraneous(combine);
	}
	
	public static int[] removeExtraneous(int[] arr) {
		int[] ret = new int[arr.length-2];
		for (int i = 2; i < ret.length+2; i++) {
			ret[i-2] = arr[i];
		}
		return ret;
	}
	
	private static byte[] encodeToByteArray(int[] bits) {
        byte[] results = new byte[(bits.length + 7) / 8];
        int byteValue = 0;
        int index;
        for (index = 0; index < bits.length; index++) {

            byteValue = (byteValue << 1) | bits[index];

            if (index %8 == 7) {
                results[index / 8] = (byte) byteValue;
            }
        }

        if (index % 8 != 0) {
            results[index / 8] = (byte) (byteValue << (8 - (index % 8)));
        }

        return results;
    }
	
	//3. Complete this method so that it takes in a string of any length
	//   and returns the full byte array of the decoded base64 characters.
	public static byte[] base64StringToByteArray(String file) {
		byte[] combined = new byte[(file.length()/4)*3];
		for (int i = 0; i < (int)((file.length()+3)/4); i++) {
			byte[] converted = convert4CharsTo24Bits(file.substring(i*4,i*4+4));
			for (int j = 0; j < converted.length; j++) {
				combined[(i*3)+j] = converted[j];
			}
		}
		return combined;
	}
}
