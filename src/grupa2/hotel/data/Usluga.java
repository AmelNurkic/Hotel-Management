package grupa2.hotel.data;

/**
 * 
 * @author Ensar
 * @since 8.3.2016
 */
public class Usluga {

	private String imeUsluge;
	//znam da su danas cijene 2.99 , 0.85  al' jebes ga
	private int cijenaUsluge;
	
	
	public Usluga() { }

	public Usluga(String imeUsluge, int cijenaUsluge) {
		this.imeUsluge = imeUsluge;
		this.cijenaUsluge = cijenaUsluge;
	}
	
	public String getImeUsluge() {
		return imeUsluge;
	}
	public void setImeUsluge(String imeUsluge) {
		this.imeUsluge = imeUsluge;
	}
	public int getCijenaUsluge() {
		return cijenaUsluge;
	}
	public void setCijenaUsluge(int cijenaUsluge) {
		this.cijenaUsluge = cijenaUsluge;
	}
	
	
	
}
