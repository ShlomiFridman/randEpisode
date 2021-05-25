package randEpisode;

import java.io.File;

public class randEp{

	private String add;
	private String name;
	
	public randEp() {}
	
	public randEp(String add,String name) {
		this.add=add;
		this.name=name;
	}
	
	
	public File getFile(String str,File f) {
		if (f.listFiles().length==0)
			return null;
		for (File tmp:f.listFiles())
			if (tmp.getName().toLowerCase().indexOf(str.toLowerCase())!=-1)
				return tmp;
		return null;
	}
	

	public File getFile() {
		File f = new File(this.add);
		if (!f.exists() || !f.isDirectory())
			return null;
		if (this.name.isEmpty())
			return f;
		for (File tmp:f.listFiles())
			if (tmp.getName().toLowerCase().indexOf(this.name.toLowerCase())!=-1) {
				return tmp;
			}
		return null;
	}
	
	public int randInt(File f) {
		return (int) (Math.floor(Math.random()*f.listFiles().length));
	}
	
	/* The non recursive function
	public File getRand(){
		File f;
		int x,y;
		if (!this.name.isEmpty()) {	// check if the request name exists in the address
			f = new File(this.add);
			f = getFile(this.name,f);
			if (f==null) {
				return null;
			}
		}
		else {	// if true then do the first search (season)
			f = new File(this.add);
			x = randInt(f);
			f = f.listFiles()[x];
		}
		if (!f.isDirectory())	// if its not a directory then return the file
			return f;
		x = randInt(f);
		if (!f.listFiles()[x].isDirectory()){
			return f.listFiles()[x];
		}
		f = f.listFiles()[x];	// goes into the 'season' and get an 'episode'
		y = randInt(f);
		if (y>0) {				// check if the folder isn't empty, if it is then the length is zero and throws error
			return f.listFiles()[y];
		}
		return null;
	}
	 */


	public File getRand(){
		File res = getFile();
		if (res==null)
			return null;
		return getRand(res);
	}

	private File getRand(File f){
		File tmp;
		do {
			tmp = f.listFiles()[randInt(f)];
		} while (dirSize(tmp)<20*Math.pow(10, 6));
		if (tmp.isDirectory())
			return getRand(tmp);
		return tmp;
	}

	private long dirSize(File f) {
		long res = f.length();
		if (!f.isDirectory())
			return res;
		for (File tmp:f.listFiles())
			if (tmp.isFile())
				res+=tmp.length();
			else
				res+=dirSize(tmp);
		return res;
	}
	
	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
