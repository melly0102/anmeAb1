package ab1;

import java.util.Set;

/**
 * Schnittstelle eines NFA. Der Automat numeriert seine Zustände von 0 bis
 * numStates-1 (0-indiziert).
 */
public interface NFA {
	/**
	 * @return Menge der Endzustände
	 */
	public Set<Integer> getAcceptingStates();

	/**
	 * @return Startzustand
	 */
	public int getInitialState();

	/**
	 * @param s zu testender Zustand
	 * @throws IllegalArgumentException Wenn es den Zustand nicht gibt
	 * @return true, wenn der Zustand s ein Endzustand ist. Ansonsten false.
	 */
	public boolean isAcceptingState(int s) throws IllegalStateException;

	/**
	 * Liefert die Anzahl der Zustände des Automaten
	 *
	 * @return die Anzahl der Zustände des Automaten
	 */
	public int getNumStates();
	
	/**
	 * Erzeugt einen Automaten, der die Vereinigung des Automaten mit dem übergebenen Automaten darstellt.
	 *
	 * @param a der zu vereinigende Automat
	 * @return neuer Automat
	 */
	public NFA union(NFA a);

	/**
	 * Hängt den Automaten a an den Automaten an
	 *
	 * @param a der anzuhängende Automat
	 * @return neuer Automat
	 */
	public NFA concat(NFA a);

	/**
	 * Bildet den Kleene-Stern des Automaten
	 * @return neuer Automat
	 */
	public NFA kleeneStar();

	/**
	 * Berechnet alle Nachfolgekonfigurationen einer gegebenen
	 * Konfiguration, also einen Berechnungsschritt.
	 *
	 * @param configuration Die Ausgangskonfiguration
	 * @return alle Nachfolgekonfigurationen gemäß Ableitungsrelation des NFAs
	 */
	public Set<Configuration> step(Configuration configuration) throws IllegalStateException;
}
