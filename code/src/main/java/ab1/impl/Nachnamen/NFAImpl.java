package ab1.impl.Nachnamen;

import ab1.Configuration;
import ab1.NFA;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NFAImpl implements NFA {

    final static String EPSILON = "\u03B5";
    private int currentStat;
    private int numStates;
    private Set<Character> alphabet;
    private Set<Integer> acceptStates;
    private Set<Character>[][] transitions;

    String pattern;
    private int initState;
    Integer state;
    Set<Integer> states;

    public NFAImpl(int initState, int state, Set<Integer> states, String pattern) {
        this.initState = initState;
        this.state = state;
        this.states = states;

        if(checkPattern(pattern)){
            this.pattern = pattern;
        }
    }

    private boolean checkPattern(String pattern) {
        Pattern p = Pattern.compile("[a-zA-Z0-9*.|\s]*");
        p.equals(pattern);
        if(pattern == null){
            return false;
        }
        return false;
    }

    @Override
    public Set<Integer> getAcceptingStates() {
        return acceptStates;
    }


    @Override
    public int getInitialState() {
        return this.initState;
    }

    @Override
    public boolean isAcceptingState(int s) throws IllegalStateException {
       if(s < 0 || s >= numStates){
           throw new IllegalStateException();
       }else if(acceptStates.contains(s)){
           return true;
       }
        return false;
    }

    @Override
    public int getNumStates() {
        //count die Anzahl der Steps durch unser Alphabet
        //return die num = anzahl der Steps von initstate -> end
        return numStates;
    }

    @Override
    public NFA union(NFA a) {
        //neuer Startzustand
        int newInitState = a.getNumStates() + this.getNumStates()+1;

        Set<Character> union = new HashSet<>();
       // union.addAll(a)
        /*
        int newNumStat = this.states + a.getNumStates() +1;
        Set<Character> newAlphabet = alphabet;
        newAlphabet.addAll(a.getA)
        */
        return null;
    }

    @Override
    public NFA concat(NFA a) {
        int newNumState = a.getNumStates() + this.numStates+1;
        Set<Character> concat = new HashSet<>();
        concat.addAll(this.alphabet);
        Set<Integer> newAcceptingStates = a.getAcceptingStates();
        int newInitStates = this.getInitialState();

        return a;
    }

    @Override
    public NFA kleeneStar() {
        Set<Integer> union = new HashSet<>();
        union.addAll(acceptStates);
        union.add(initState);

        NFA retNFA = new NFAImpl(numStates + 1, state, union, pattern);
        //   retNFA.step();

        for (int i : acceptStates) {
            //  retNFA.setTransition(i, null, initState);
        }

        return retNFA;
    }

        /*
        int initState = numStates;

        Set<Integer> unionSet= new HashSet<>();
        unionSet.addAll(acceptingStates);
        unionSet.add(initState);

        NFA ret = new NFAImpl(numStates+1,alphabet, unionSet, initState);

        ret.setTransition(initState,null, initialState);

        for(int i : acceptingStates){
            ret.setTransition(i, null, initState);
        }
        return ret;
        */


    @Override
    public Set<Configuration> step(Configuration configuration) throws IllegalStateException {
        return null;
    }
}
