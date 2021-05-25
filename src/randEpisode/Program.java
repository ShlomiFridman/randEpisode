package randEpisode;

import java.io.File;

public class Program {

	private static String getPath(File f) {
		String str = f.getAbsolutePath();
		return str.substring(0,str.lastIndexOf(f.getName()));
	}
	
	public static void main(String[] args) {
		File file = new File("a");
		Window w = new Window(getPath(file));
	}

}
