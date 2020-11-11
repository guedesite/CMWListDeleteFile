import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class main {

	public static List<String> out;
	
	public static void main(String[] args) {
		File baseDir = new File("base/");
		out = new ArrayList<String>();
		loopDir(baseDir);
		PrintWriter writer;
		try {
			writer = new PrintWriter("output.txt", "UTF-8");
			for(String s:out) {
				writer.println(s);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void loopDir(File f2) {
		List<String> temp = new ArrayList<String>();
		int nb = 0;
		for(File f:f2.listFiles()) {
			if(!f.isDirectory()) {
				nb++;
			}
		}
		int i = 0;
		for(File f:f2.listFiles()) {
			if(f.isDirectory()) {
				loopDir(f);
			}
			else {
				if(!new File(f.getAbsolutePath().replace("\\CMWListDeleteFile\\base\\", "\\CMWListDeleteFile\\change\\")).exists()) {
					i++;
					temp.add("unlink('"+f.getAbsolutePath()+"');");
					System.out.println("find file "+f.getAbsolutePath());
				}
			}
		}
		if(nb == i && nb != 0) {
			out.add("undir('"+f2.getAbsolutePath()+"');");
			System.out.println("find dir "+f2.getAbsolutePath());
		} else {
			out.addAll(temp);
		}
	}

}
