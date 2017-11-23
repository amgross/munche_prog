import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class genericFunctions {
	/**
	 * from https://stackoverflow.com/questions/453018/number-of-lines-in-a-file-in-java 
	 * Gets path of a file.  
	 * Return the number of lines in it.
	 * 
	 * @param filename
	 * @return 1 or number of lines
	 * @throws IOException
	 */
	public static int countLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {                 // try
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			is.close();
			return (count == 0 && !empty) ? 1 : count;
		} catch(IOException e) {
			is.close();
			return 0;

		}
	}
}
