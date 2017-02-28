package algorithm;

public class Pair<T, U> {
	
    private T t;
    private U u;
    
    
	public Pair(T t, U u) {
		this.setT(t);
		this.setU(u);
	}
	

	/**
	 * @return the t
	 */
	public T getT() {
		return t;
	}


	/**
	 * @param t the t to set
	 */
	public void setT(T t) {
		this.t = t;
	}


	/**
	 * @return the u
	 */
	public U getU() {
		return u;
	}


	/**
	 * @param u the u to set
	 */
	public void setU(U u) {
		this.u = u;
	}
	
	
	public boolean compareTo(Pair<T,U> P){
		boolean bool = false;
		
		if(this.getT() == P.getT() && this.getU() == P.getU()){
			bool = true;
		}
		
		return bool;
	}
	

}
