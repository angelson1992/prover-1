package com.naveensundarg.shadow.prover.sandboxes;

import com.naveensundarg.shadow.prover.core.SnarkWrapper;
import com.naveensundarg.shadow.prover.core.ccprovers.CognitiveCalculusProver;
import com.naveensundarg.shadow.prover.core.proof.Justification;
import com.naveensundarg.shadow.prover.representations.formula.Formula;
import com.naveensundarg.shadow.prover.representations.value.Value;
import com.naveensundarg.shadow.prover.representations.value.Variable;
import com.naveensundarg.shadow.prover.utils.CollectionUtils;
import com.naveensundarg.shadow.prover.utils.Problem;
import com.naveensundarg.shadow.prover.utils.ProblemReader;
import com.naveensundarg.shadow.prover.utils.Reader;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

class SimulatedUser {

    public ArrayList<String> getAgentList() {
        return agentList;
    }

    public void setAgentList(ArrayList<String> agentList) {
        this.agentList = agentList;
    }

    public ArrayList<String> getEmbodimentList() {
        return embodimentList;
    }

    public void setEmbodimentList(ArrayList<String> embodimentList) {
        this.embodimentList = embodimentList;
    }

    public ArrayList<String> getIdentityCueList() {
        return identityCueList;
    }

    public void setIdentityCueList(ArrayList<String> identityCueList) {
        this.identityCueList = identityCueList;
    }

    public Map<String, String> getEmbodyingRelationship() {
        return embodyingRelationship;
    }

    public void setEmbodyingRelationship(Map<String, String> embodyingRelationship) {
        this.embodyingRelationship = embodyingRelationship;
    }

    public Map<String, String> getAgentPersonality() {
        return agentPersonality;
    }

    public void setAgentPersonality(Map<String, String> agentPersonality) {
        this.agentPersonality = agentPersonality;
    }

    private ArrayList<String> agentList;
    private ArrayList<String> embodimentList;
    private ArrayList<String> identityCueList;
    private Map<String, String> embodyingRelationship;
    private Map<String, String> agentPersonality;

    public SimulatedUser() {
    }
}

public class SimulationSandbox {

    private int timeStep;
    private Set<Formula> boardStateAssumptions;
    private Formula boardStateGoal;
    private static SnarkWrapper snarkProver = SnarkWrapper.getInstance();
    private static CognitiveCalculusProver ccProver = new CognitiveCalculusProver();
    private Map<Integer, Set<Formula>> simulationEvents;

    private static boolean isCognitive = false;

    public Map<Integer, Set<Formula>> getSimulationEvents() {
        return simulationEvents;
    }

    public SimulationSandbox(int problemNumber, String fileName) throws Exception{
        timeStep = 0;
        simulationEvents = CollectionUtils.newMap();
        boardStateAssumptions = getTheoryAssumptions(problemNumber, fileName);
    }

    public Set<Formula> getTheoryAssumptions(int problemNumber, String fileName) throws Exception{
        List<Problem> tests = ProblemReader.readFrom(SimulationSandbox.class.getResourceAsStream(fileName));
        Problem p = tests.get(problemNumber);
        Set<Formula> answer = CollectionUtils.newEmptySet();
        for(Formula assumption: p.getAssumptions()){
            if(assumption.toString().startsWith("(HoldsAt")){
                String eventString = assumption.toString().substring(8).split("\\st\\d+\\)")[0];
                Formula eventForm = Reader.readFormulaFromString(eventString);
                int timeSlot = Integer.valueOf(assumption.toString().substring(8+eventString.length()+2, assumption.toString().length()-1));
                if(simulationEvents.containsKey(timeSlot)) {
                    simulationEvents.get(timeSlot).add(eventForm);
                }else{
                    Set<Formula> temp = CollectionUtils.newEmptySet();
                    temp.add(eventForm);
                    simulationEvents.put(timeSlot, temp);
                }

            }else{
                answer.add(assumption);
            }
        }
        return answer;
    }

    public Optional<Pair<Justification, Set<Map<Variable, Value>>>> checkSimulationStep(int checkedTimeStep, Formula stepGoal, Optional<List<Variable>> stepGoalVariables) throws Reader.ParsingException {
        Set<Formula> temp = CollectionUtils.newEmptySet();

        extractEventsFromAssumptions();



        if(simulationEvents.containsKey(checkedTimeStep)){  //If there are events in this time step
            Set<Formula> newEvents = simulationEvents.get(checkedTimeStep); //Gather those events in variable newEvents
            for(Formula event : newEvents){ //for each event in newEvents
                boardStateAssumptions.add(event); //add that event to the boardStateAssumptions
                temp.add(event);
            }
        }

        Optional<Pair<Justification, Set<Map<Variable, Value>>>> proof = snarkProver.proveAndGetMultipleBindings(boardStateAssumptions, stepGoal, stepGoalVariables.get()); //Find what variables prove the stepGoal

        if(proof.isPresent()){
            if(stepGoal.toString().length() > 28 && stepGoal.toString().substring(1, 28).compareToIgnoreCase("PresentingUniqueIdentityCue")==0){
                for(Map<Variable, Value> foundAnswer: proof.get().getRight()) {
                    Formula pastTenseVersion = Reader.readFormulaFromString("(PresentedUniqueIdentityCue" + stepGoal.toString().substring(28));
                    Formula answeredPastTenseVersion = pastTenseVersion.apply(foundAnswer);
                    boardStateAssumptions.add(answeredPastTenseVersion);
                }
            }
            if(stepGoal.toString().length() > 37 && stepGoal.toString().substring(1, 37).compareToIgnoreCase("PresentingUniqueIdentityCueInContext")==0){
                for(Map<Variable, Value> foundAnswer: proof.get().getRight()) {
                    Formula pastTenseVersion = Reader.readFormulaFromString("(PresentedUniqueIdentityCueInContext" + stepGoal.toString().substring(37));
                    Formula answeredPastTenseVersion = pastTenseVersion.apply(foundAnswer);
                    boardStateAssumptions.add(answeredPastTenseVersion);
                }
            }
            if(stepGoal.toString().length() > 27 && stepGoal.toString().substring(1, 27).compareToIgnoreCase("MaintainingIdentityWithCue")==0){
                for(Map<Variable, Value> foundAnswer: proof.get().getRight()) {
                    Formula pastTenseVersion = Reader.readFormulaFromString("(MaintainedIdentityWithCue" + stepGoal.toString().substring(27));
                    Formula answeredPastTenseVersion = pastTenseVersion.apply(foundAnswer);
                    boardStateAssumptions.add(answeredPastTenseVersion);
                }
            }
            if(stepGoal.toString().length() > 26 && stepGoal.toString().substring(1, 26).compareToIgnoreCase("SignalingMigrationWithCue")==0){
                for(Map<Variable, Value> foundAnswer: proof.get().getRight()) {
                    Formula pastTenseVersion = Reader.readFormulaFromString("(SignaledMigrationWithCue" + stepGoal.toString().substring(26));
                    Formula answeredPastTenseVersion = pastTenseVersion.apply(foundAnswer);
                    boardStateAssumptions.add(answeredPastTenseVersion);
                }
            }
            if(stepGoal.toString().length() > 38 && stepGoal.toString().substring(1, 38).compareToIgnoreCase("MaintainingUnfalsifiedIdentityWithCue")==0){
                for(Map<Variable, Value> foundAnswer: proof.get().getRight()) {
                    Formula pastTenseVersion = Reader.readFormulaFromString("(MaintainedUnfalsifiedIdentityWithCue" + stepGoal.toString().substring(38));
                    Formula answeredPastTenseVersion = pastTenseVersion.apply(foundAnswer);
                    boardStateAssumptions.add(answeredPastTenseVersion);
                }
            }
        }

        for(Formula endedEvent: temp){ //This for loop handles ended events in the simulation

            if(endedEvent.toString().substring(1, 28).compareToIgnoreCase("PresentingUniqueIdentityCue")==0){
                Formula pastTenseVersion = Reader.readFormulaFromString("(PresentedUniqueIdentityCue" + endedEvent.toString().substring(28));
                boardStateAssumptions.add(pastTenseVersion);
            }
            if(endedEvent.toString().substring(1, 37).compareToIgnoreCase("PresentingUniqueIdentityCueInContext")==0){
                Formula pastTenseVersion = Reader.readFormulaFromString("(PresentedUniqueIdentityCueInContext" + endedEvent.toString().substring(37));
                boardStateAssumptions.add(pastTenseVersion);
            }
            if(endedEvent.toString().substring(1, 27).compareToIgnoreCase("MaintainingIdentityWithCue")==0){
                Formula pastTenseVersion = Reader.readFormulaFromString("(MaintainedIdentityWithCue" + endedEvent.toString().substring(27));
                boardStateAssumptions.add(pastTenseVersion);
            }
            if(endedEvent.toString().substring(1, 26).compareToIgnoreCase("SignalingMigrationWithCue")==0){
                Formula pastTenseVersion = Reader.readFormulaFromString("(SignaledMigrationWithCue" + endedEvent.toString().substring(26));
                boardStateAssumptions.add(pastTenseVersion);
            }
            if(endedEvent.toString().substring(1, 38).compareToIgnoreCase("MaintainingUnfalsifiedIdentityWithCue")==0){
                Formula pastTenseVersion = Reader.readFormulaFromString("(MaintainedUnfalsifiedIdentityWithCue" + endedEvent.toString().substring(38));
                boardStateAssumptions.add(pastTenseVersion);
            }
            //TODO Also handle the other Presenting Something Something Predicates

            if(boardStateAssumptions.contains(endedEvent)){
                boardStateAssumptions.remove(endedEvent);
            }
        }

        return proof;
    }

    public void runSimulation(int beginningStep, int endingStep, Formula stepGoal, Optional<List<Variable>> stepGoalVariables) throws Reader.ParsingException {

        for (int i = beginningStep; i < endingStep; i++){

            System.out.println("Simulating timeStep " + String.valueOf(i));

            Optional<Pair<Justification, Set<Map<Variable, Value>>>> stepProof = checkSimulationStep(i, stepGoal, stepGoalVariables);
            System.out.println(stepProof);

        }

    }

    public void extractEventsFromAssumptions() throws Reader.ParsingException {

        Formula eventSearchGoal = Reader.readFormulaFromString("(HoldsAt ?e ?t)");
        Variable e = new Variable("?e");
        Variable t = new Variable("?t");
        List<Variable> goalVars = CollectionUtils.newEmptyList();
        goalVars.add(e); goalVars.add(t);

        Optional<Pair<Justification, Set<Map<Variable, Value>>>> proof = snarkProver.proveAndGetMultipleBindings(boardStateAssumptions, eventSearchGoal, goalVars);
        //System.out.println(proof);
        if(proof.isPresent()){
            for(Map<Variable, Value> event: proof.get().getRight()){
                int timeSlot = Integer.valueOf(event.get(t).toSnarkString().substring(1));
                Formula eventForm = Reader.readFormulaFromString(event.get(e).toString());
                if(simulationEvents.containsKey(timeSlot)) {
                    simulationEvents.get(timeSlot).add(eventForm);
                }else{
                    Set<Formula> temp = CollectionUtils.newEmptySet();
                    temp.add(eventForm);
                    simulationEvents.put(timeSlot, temp);
                }

            }
        }

    }

    public void extractEventsFromReasoning(String goalName, Formula goal, List<Variable> goalVars){

        Optional<Pair<Justification, Set<Map<Variable, Value>>>> proof = snarkProver.proveAndGetMultipleBindings(boardStateAssumptions, goal, goalVars);
        if(proof.isPresent()){
            for(Map<Variable, Value> foundAnswer: proof.get().getRight()){
                String buffer = "(" + goalName + " ";
                proof.get().getLeft();
            }
        }

    }

    public static void main(String[] args) throws Exception{

        String fileName = "../teleportation_axioms_noncog.clj";
        int problemNumber = 6;

        SimulationSandbox testTheory1 = new SimulationSandbox(problemNumber, fileName);

//        Formula eventT1 = Reader.readFormulaFromString("(HasIdentifyingTrait (embodiment a) blackAndYellow)");
//        Set<Formula> eventsT1Set = CollectionUtils.newEmptySet();
//        eventsT1Set.add(eventT1);
//        Formula eventT2 = Reader.readFormulaFromString("(HasIdentifyingTrait (embodiment b) blackAndYellow)");
//        Set<Formula> eventsT2Set = CollectionUtils.newEmptySet();
//        eventsT2Set.add(eventT2);
//        testTheory1.getSimulationEvents().put(1, eventsT1Set);
//        testTheory1.getSimulationEvents().put(2, eventsT2Set);

//        Formula stepGoal = Reader.readFormulaFromString("(TeleportedInto ?x ?y)");
//        Variable x = new Variable("?x"); Variable y = new Variable("?y"); //Variable z = new Variable("?z");
//        List<Variable> stepGoalVariables = CollectionUtils.newEmptyList();
//        stepGoalVariables.add(x); stepGoalVariables.add(y); //stepGoalVariables.add(z);
//        Optional<List<Variable>> sGV = Optional.of(stepGoalVariables);

        List<Problem> tests = ProblemReader.readFrom(SimulationSandbox.class.getResourceAsStream(fileName));
        Problem p = tests.get(problemNumber);
        //System.out.println(p.getGoal());
        //System.out.println(p.getAnswerVariables());
        //System.out.println(testTheory1.checkSimulationStep(1, stepGoal, sGV));
        //testTheory1.extractEventsFromAssumptions();
        System.out.println(testTheory1.simulationEvents);
        System.out.println(testTheory1.boardStateAssumptions);
        testTheory1.runSimulation(0, 6, p.getGoal(), p.getAnswerVariables());

    }

}
