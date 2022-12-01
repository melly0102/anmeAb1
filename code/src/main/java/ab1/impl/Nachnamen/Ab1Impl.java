package ab1.impl.Nachnamen;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ab1.Ab1;
import ab1.NFA;

public class Ab1Impl implements Ab1 {

	@Override
	public NFA fromPattern(String pattern) {
		// TODO Auto-generated method stub
		//DFA to NFA method ?
		return new NFAImpl(0, 0, new HashSet<>(), pattern);
	}

/**
 * Überprüft, ob der NFA ein gegebenes Wort akzeptiert.
 *
 * @param a der NFA
 * @param word das Input-Wort
 * @return true, wenn der NFA akzeptiert, sonst false
 * */

	@Override
	public boolean accepts(NFA a, String word) {
		// TODO Auto-generated method stub
		//wenn automat a mit word in einen Endzustand landet dann return true
		return false;
	}

	@Override
	public List<Integer> find(NFA a, String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pattern1() {
		// TODO Auto-generated method stub

		return "^.*(abc|cba).*$";
	}

	@Override
	public String pattern2() {
		// TODO Auto-generated method stub
		return "^(.{2})*(aab).*$"; // "^.*{2}(aab).*$";
	}

	@Override
	public String pattern3() {
		// TODO Auto-generated method stub
		return "^(1|01| )*0?$";//"^1?((01|1)*)0?$";//"|01)*0?$";
	}

	@Override
	public String pattern4() {
		// TODO Auto-generated method stub
		return "^(th)[^s ]*s$";
	}

	@Override
	public String pattern5() {
		// TODO Auto-generated method stub
		return "([a-zA-Z0-9!#$%&'*+-/=?^_`.{|}~]{1,64})((@(aau|edu.aau).at){1})";
	}

}
