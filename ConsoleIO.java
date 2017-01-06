import java.io.*;

public class ConsoleIO {

  private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  public static String readString(){
    while (true) {
      try {
        return reader.readLine();
      } catch (IOException e) {
      }
    }
  }

  public static int readInt() {
		while (true) {
			try {
				String input = reader.readLine();
				return Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.print("Integer value expected. Please try again: ");
			} catch (IOException e) {
				// should never happen
			}
		}
	}

}
