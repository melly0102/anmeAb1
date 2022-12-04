package ab1.impl.Nachnamen;

import java.util.*;

import ab1.Ab1;
import ab1.Configuration;
import ab1.NFA;

public class Ab1Impl implements Ab1 {

	@Override
	public NFA fromPattern(String pattern) {
		// TODO Auto-generated method stub
		//DFA to NFA method ?
		return new NFAImpl(0, 0, new HashSet<>(), pattern);
	}

	@Override
	public boolean accepts(NFA a, String word) {
		// TODO Auto-generated method stub
		//wenn automat a mit word in einen Endzustand landet dann return true#
		boolean accepts = false;
		Set<Configuration> c = a.step(new Configuration(0, word));

		Configuration lastConfig = (Configuration) c.toArray()[c.size()-1];

		if(lastConfig.getWord() != ""){
			return false;
		}else{
			accepts = a.isAcceptingState(lastConfig.getState());
			return accepts;
		}
	}

	@Override
	public List<Integer> find(NFA a, String text) {
		// TODO Auto-generated method stub
		List<Integer> foundIndex = new LinkedList<>();
		if (text != null && text.length() > 0) {
			for(int i=0; i <= text.length(); i++){
				if(i>0) {
					text = text.replaceFirst(String.valueOf(text.charAt(0)), "");
				}
				System.out.println(text);
				Configuration textConfig = new Configuration(0, text);
				Set<Configuration> configurations = a.step(textConfig);

				for(Configuration c: configurations){
					if(a.isAcceptingState(c.getState())){
						foundIndex.add(i);
						System.out.println(i);
					}
				}
			}

			return foundIndex;
		}

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
