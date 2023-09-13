package testsCopControl_JUnit5;

import pista.Helipuerto;
import pista.PistaSimple;
import pista.PosicionesEntradaVaciaException;
import avion.AvionSimple;
import copControl.Dificultad;
import copControl.Mapa;
import copControl.Nivel;
import copControl.Posicion;

//librerias de Junit 5
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestCopControl{
	
	@BeforeEach
	public void setUp(){
		
		mapaDeJuego = new Mapa();
	}
	
	Mapa mapaDeJuego;
	
	@Test
	public void testAvionVolando() {
		Nivel nvl = new Nivel(mapaDeJuego, new Dificultad(2,3,5));
		Posicion posicionInicial= new Posicion(2,2);
		Posicion posicionFinal= new Posicion(2,2);
		
		AvionSimple avion1 = new AvionSimple(posicionInicial, posicionFinal,mapaDeJuego);
		avion1.avanzar();
		assertTrue(avion1.getEstaVolando());
	}
	
	@Test
	public void testTieneQueHaberAvionesVolando() {
		Nivel nvl = new Nivel(mapaDeJuego, new Dificultad(4, 1, 1));
		Posicion posicionInicial= new Posicion(2,2);
		Posicion posicionFinal= new Posicion(2,2);
		
		AvionSimple avion1 = new AvionSimple(posicionInicial, posicionFinal,mapaDeJuego);
		AvionSimple avion2 = new AvionSimple(new Posicion(1, 1), posicionFinal,mapaDeJuego);
		nvl.colocarAvionEnAire(avion1);
		nvl.colocarAvionEnAire(avion2);
		assertTrue(mapaDeJuego.tieneAvionesVolando());
	}
	
	@Test
	public void testUnAvionChocaConOtroAvion() {
		Nivel nvl = new Nivel(mapaDeJuego, new Dificultad(2,3,5));
		Posicion posicionInicial= new Posicion(2,2);
		Posicion posicionFinal= new Posicion(2,2);
		
		AvionSimple avion1 = new AvionSimple(posicionInicial, posicionFinal,mapaDeJuego);
		AvionSimple avion2 = new AvionSimple(posicionInicial, posicionFinal,mapaDeJuego);
		nvl.colocarAvionEnAire(avion1);
		nvl.colocarAvionEnAire(avion2);
		assertTrue(nvl.huboChoque());
	}
	
	  @Test public void testPosicionEsUnExtremo() {
	        Dificultad dificultad = new Dificultad(3, 5, 2);
	        Nivel nvl = new Nivel(mapaDeJuego, dificultad);

	        Posicion pos= new Posicion(0, 0);
	        assertTrue(mapaDeJuego.esPosicionExtremo(pos));;
	    }
	  
	
	
	@Test
	public void testUnAvionSimplePuedeAterrizarEnUnaPistaSimple() throws PosicionesEntradaVaciaException{
		Posicion posicionInicial= new Posicion(1,1);
		Posicion posicionFinal= new Posicion(5,5);
		PistaSimple pistaSimple = new PistaSimple(posicionFinal);
		
		AvionSimple avion = new AvionSimple(posicionInicial, posicionFinal,mapaDeJuego);
		
		assertTrue(avion.puedeAterrizar(pistaSimple));
	}
	
	@Test
	public void testUnAvionSimpleNoPuedeAterrizarEnUnHelipuerto() throws PosicionesEntradaVaciaException{
		Posicion posicionInicial= new Posicion(1,1);
		Posicion posicionFinal= new Posicion(5,5);
		Helipuerto helipuerto = new Helipuerto(posicionFinal);
		
		AvionSimple avion = new AvionSimple(posicionInicial, posicionFinal,mapaDeJuego);
		
		assertFalse(avion.puedeAterrizar(helipuerto));
	}
	
	@Test
	public void testUnaPosicionDentroDelRadioDelAvionDebeDevolverTrue() {
		Posicion posicionInicial= new Posicion(30,30);
		Posicion posicionFinal= new Posicion(3,9);
		Posicion posicionDePrueba=new Posicion(31,31);
		
		
		AvionSimple avion = new AvionSimple(posicionInicial, posicionFinal,mapaDeJuego);
		assertTrue(avion.esPosicionContenida(posicionDePrueba));
		
	}
}
