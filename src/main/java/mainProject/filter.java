package mainProject;
import java.util.Vector;

public abstract class filter {
	String par1;
	String par2;
	String par3;
	String par4;
	public filter(String par1, String par2, String par3, String par4) {
		super();
		this.par1 = par1;
		this.par2 = par2;
		this.par3 = par3;
		this.par4 = par4;
	}
	abstract Vector<sameScanWifi> filt(Vector<sameScanWifi> database1, Vector<sameScanWifi> Database2);
}
