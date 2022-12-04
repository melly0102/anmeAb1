package ab1.impl.Nachnamen;

import ab1.Configuration;
import ab1.NFA;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NFAImpl implements NFA {
    final static char EPSILON = '!';
    final static char EMPTY = '?';
    final static String ALPHABET = "[a-zA-Z0-9*.| ]*";

    private int currentState;
    private int numStates;
    private Set<Integer> acceptStates;
    private Set<Character>[][] transitions;

    String pattern;
    int initState;
    Integer state;
    Set<Integer> states;

    public NFAImpl(int initState, int state, Set<Integer> states, String pattern) {
        this.initState = initState;
        this.state = state;
        this.states = states;
        this.acceptStates = new HashSet<>();
        this.numStates = 0;

        if(pattern!= null){
            if(checkPattern(pattern)){
                this.pattern = pattern;
                createTransitions();
            }
        }else{
            transitions = null;
        }


        System.out.println("pattern: " + pattern + ", check: " + checkPattern(pattern) + ", " + numStates);
        displayNFA();
        System.out.println(acceptStates);
    }

    private boolean checkPattern(String pattern) {
        numStates = 0;
        if(pattern != null) {
            if(pattern.matches(ALPHABET) && pattern.length() > 0){
                return extendedCheck(pattern, 0);
            }else if(pattern.length() == 0){
                return true;
            }
        }
        return false;
    }

    public void prepareTransitions(){
        for(int i=0; i<=numStates; i++){
            for(int j=0; j<=numStates; j++){
                transitions[i][j] = new HashSet<>();
            }
        }
    }

    private boolean extendedCheck(String pattern, int position){
        if(pattern.charAt(position) == '|'){
            if(position == pattern.length()-1 || pattern.charAt(position+1) == '|'){
                return false;
            }
            numStates--;
        }else if(pattern.charAt(position) == '*'){
            if(position == 0 || pattern.charAt(position-1) == '|' || pattern.charAt(position-1) == '*'){
                return false;
            }
        }else{
            numStates++;
        }

        if(position == pattern.length()-1) {
            return true;
        }

        return extendedCheck(pattern, position+1);
    }

    public void createTransitions(){
        int curState = 0;
        int prevState = 0;

        if(pattern.length() == 0){
            //start is final state
            acceptStates.add(0);

            //only one transition
            //first state is end state with epsilon transition
            transitions = new Set[1][1];
            prepareTransitions();
            transitions[0][0] = new LinkedHashSet<>();
            transitions[0][0].add(EMPTY);
        } else {
            transitions = new Set[numStates+1][numStates+1];
            prepareTransitions();
            //building nfa and detecting final states
            for(int i=0; i<pattern.length(); i++){
                if (pattern.charAt(i) == '|') {
                    //curState is final state
                    //check if pattern at i == *
                    if(pattern.charAt(i-1) == '*'){
                        //connect state to last state
                        addTransition(prevState, curState-1, pattern.charAt(i-2));
                    }else {
                        addTransition(prevState, curState, pattern.charAt(i-1));
                        acceptStates.add(curState);
                    }
                    //current position gets set to 0
                    curState = 0;
                }else if(pattern.charAt(i) == '*'){
                    //state points at itself
                    //get character -1
                    addTransition(curState-1, curState-1, pattern.charAt(i-1));

                    //if i == pattern.length-1 -> final state
                    if(i == pattern.length()-1){
                        addAcceptState(curState-1);
                    }else{
                        //if there is a character of the alphabet after the *
                        if(pattern.charAt(i+1) != '|'){
                            addTransition(curState-1, curState, pattern.charAt(i+1));
                            curState--;
                        }
                    }
                }else{
                    if(i < pattern.length() - 1){
                        //length-1 == i -> final state
                        //connect state to other states if next char in pattern is not * or |
                         if(pattern.charAt(i+1) != '*' && pattern.charAt(i+1) != '|'){
                             //connect state to next state
                             addTransition(curState, curState + 1, pattern.charAt(i));
                        }else{
                            prevState = curState;
                        }
                    }else{
                        //end of pattern -> final state
                        //addTransition(prevState, curState, pattern.charAt(i));
                        addTransition(curState, curState+1, pattern.charAt(i));
                        acceptStates.add(curState + 1);
                    }

                    curState++;
                }
            }
        }
    }

    public void addTransition(int i, int j, char c){
        if(transitions[i][j] == null){
            transitions[i][j] = new HashSet<>();
        }

        if(!transitions[i][j].contains(c)) {
            transitions[i][j].add(c);
        }
    }

    public void displayNFA(){
        if(transitions != null) {
            for (int i = 0; i < transitions.length; i++) {
                for (int j = 0; j < transitions[i].length; j++) {
                    if (transitions[i][j] != null) {
                        System.out.print("From state" + i + " to state" + j + ": ");
                        for (char c : transitions[i][j]) {
                            System.out.print(c + ", ");
                        }
                        System.out.println();
                    } else {
                        System.out.println("From state" + i + " to state" + j + ": EMPTY");
                    }
                }
            }
        }
    }

    public void addAcceptState(int state){
        if(!acceptStates.contains(state)){
            acceptStates.add(state);
        }
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
       if(s < 0 || s > numStates){
           throw new IllegalStateException();
       }else if(acceptStates.contains(s)){
           return true;
       }
        return false;
    }

    @Override
    public int getNumStates() {
        return numStates;
    }

    @Override
    public NFA union(NFA a) {
        return null;
    }

    @Override
    public NFA concat(NFA a) {
        return a;
    }

    @Override
    public NFA kleeneStar() {
        return null;
    }

    @Override
    public Set<Configuration> step(Configuration configuration) throws IllegalStateException {
        Set<Configuration> configurations = new LinkedHashSet<>();

        configurations.add(configuration);

        return recursiveTransitionIteration(configurations, configuration.getWord(), 0);
    }

    public Set<Configuration> recursiveTransitionIteration(Set<Configuration> config, String word, int configStep){
        if(transitions != null && word != ""){
            char first = word.charAt(0);
            for(int i = 0; i <= numStates; i++){
               //check if theres a next state for the character
               if(transitions[configStep][i].contains(first)){
                   Configuration newConfig = new Configuration(i, word.replaceFirst(String.valueOf(first), ""));
                   config.add(newConfig);

                   return recursiveTransitionIteration(config, newConfig.getWord(), newConfig.getState());
               }

               //then check if theres transition to the state itself
               if(i == numStates && first != '.'){
                   i = -1;
                   first = '.';
               }
           }
        }

        return config;
    }

    public int getNextState(char c, String word, int configStep){
        for(int i = 0; i <= numStates; i++){
            char first = word.charAt(0);
            //check if theres a next state for the character
            //then check if theres transition to the state itself
            if(transitions[configStep][i].contains(c) || transitions[configStep][i].contains('.')){
                Configuration newConfig = new Configuration(i, word.replaceFirst(String.valueOf(first), ""));
                //config.add(newConfig);

                //return recursiveTransitionIteration(config, newConfig.getWord(), newConfig.getState());
            }
        }

        return configStep;
    }
}
