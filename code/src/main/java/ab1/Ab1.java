package ab1;

import java.util.List;

/**
 * Schnittstelle zur Erzeugung und Ausführung von endlichen Automaten.
 */
public interface Ab1 {

	/**
	 * Erzeugt einen NFA, der Wörter über dem Alphabet A-Z, a-z, 0-9 und
	 * dem Leerzeichen erkennt, die dem übergebenen Muster entsprechen. Das
	 * Muster ist ein vereinfachter regulärer Ausdruck, in dem nur einzelne
	 * Zeichen, deren Verkettung (durch Hintereinanderschreiben), sowie die
	 * Sonderzeichen "." und "*" vorkommen, wobei das Sonderzeichen "." für
	 * ein beliebiges Zeichen aus dem Alphabet steht, und das Sonderzeichen
	 * "*" bedeutet, dass das vorangegangene Zeichen beliebig oft
	 * wiederholt werden darf (Hinweis: auch die Kombination ".*" ist
	 * erlaubt). Mehrere solcher vereinfachten regulären Ausdrücke können
	 * durch das zusätzliche Sonderzeichen "|" aneinandergereiht werden,
	 * wodurch ausgedrückt wird, dass Wörter des ersten regulären Ausdrucks
	 * ODER des zweiten regulären Ausdrucks ODER des dritten usw. erkannt
	 * werden sollen. Der leere String entspricht dem Leerwort. Kein String
	 * ("null") entspricht der leeren Menge.
	 *
	 * @param pattern das Textmuster, das vom NFA erkannt werden soll
	 * @return den entsprechenden NFA für das Textmuster
	 */
	public NFA fromPattern(String pattern);

	/**
	 * Überprüft, ob der NFA ein gegebenes Wort akzeptiert.
	 *
	 * @param a der NFA
	 * @param word das Input-Wort
	 * @return true, wenn der NFA akzeptiert, sonst false
	 */
	public boolean accepts(NFA a, String word);

	/**
	 * Durchsucht einen gegebenen Text nach einem Such-Muster (= ein NFA,
	 * der das Such-Muster repräsentiert, siehe oben) und liefert alle
	 * Indizes, an denen das Such-Muster gefunden wurde.  Der NFA "findet"
	 * das Such-Muster im Text, wenn er, ausgehend von einem bestimmten
	 * Index im zu durchsuchenden Text, zu einem Endzustand gelangt (auch
	 * wenn dabei nicht der gesamte restliche Text vom Automaten akzeptiert
	 * wird).
	 *
	 * @param a der NFA, der das Such-Muster repräsentiert, nachdem gesucht
	 *          werden soll
	 * @param text der Text, der durchsucht werden soll
	 * @return eine Liste mit Start-Indizes, an denen das Such-Muster im
	 *         Text gefunden wurde
	 */
	public List<Integer> find(NFA a, String text);

	/**
	 * Erstellen Sie einen vereinfachten (siehe oben) regulären Ausdruck,
	 * der auf alle Zeichenketten passt, die die Zeichenkette "abc" oder
	 * "cba" enthalten.
	 */
	public String pattern1();

	/**
	 * Erstellen Sie einen regulären Ausdruck in egrep-Syntax, dessen
	 * Sprache über dem Alphabet {a, b} alle Wörter beinhaltet, die das
	 * Wort "aab" als Infix haben, und dieser Infix an einer geraden
	 * Position beginnt.
	 */
	public String pattern2();

	/**
	 * Erstellen Sie einen regulären Ausdruck in egrep-Syntax, dessen
	 * Sprache über dem Alphabet {1, 0} genau jene Wörter sind, die das
	 * Wort "00" nicht beinhalten.
	 */
	public String pattern3();


	/**
	 * Erstellen Sie einen regulären Ausdruck in egrep-Syntax, dessen
	 * Sprache all jene Wörter umfasst, die englische Wörter sind, die mit
	 * "th" beginnen, und mit "s" enden, sonst aber kein "s" beinhalten.
	 */
	public String pattern4();

	/**
	 * Erstellen Sie einen regulären Ausdruck in egrep-Syntax, dessen
	 * Sprache genau alle gültigen AAU-E-Mail-Adressen umfasst. Dies sind
	 * also jene mit der Domäne "aau.at" oder "edu.aau.at". Für den lokalen
	 * Teil der E-Mail-Adresse recherchieren Sie selbstständig im
	 * entsprechenden IETF RFC #3696, Abschnitt 3 (siehe
	 * https://www.rfc-editor.org/rfc/rfc3696#page-5).
	 */
	public String pattern5();
}
