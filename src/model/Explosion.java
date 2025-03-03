package model;

public class Explosion {
	private long duracion = 2000;
	
	public Explosion () {	
		long inicio = System.currentTimeMillis();
        while (System.currentTimeMillis() - inicio < duracion) {	
        }

    }
	

}
