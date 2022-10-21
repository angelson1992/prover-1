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

import java.lang.reflect.Array;
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

    public Optional<Pair<Justification, Set<Map<Variable, Value>>>> runSimulation(int beginningStep, int endingStep, Formula stepGoal, Optional<List<Variable>> stepGoalVariables, String startingEmbodiment, String endingEmbodiment) throws Reader.ParsingException {

        Optional<Pair<Justification, Set<Map<Variable, Value>>>> stepProof = Optional.empty();
        boolean foundResponse = false;

        for (int i = beginningStep; i < endingStep; i++){

            System.out.println("Simulating timeStep " + i);

            stepProof = checkSimulationStep(i, stepGoal, stepGoalVariables);

            if(stepProof.isPresent()){
                System.out.println(stepProof);

                Variable probX = stepGoalVariables.get().get(0);
                Variable probY = stepGoalVariables.get().get(1);
                for(Map<Variable, Value> foundAnswer: stepProof.get().getRight()){
                    String currentStartingEmbodiment = foundAnswer.get(probX).toSnarkString();
                    String currentEndingEmbodiemnt = foundAnswer.get(probY).toSnarkString();
                    if(currentStartingEmbodiment.compareToIgnoreCase(startingEmbodiment)==0 && currentEndingEmbodiemnt.compareToIgnoreCase(endingEmbodiment)==0){
                        return stepProof;
                    }
                }

            }

        }

        return stepProof;

    }

    public Pair<List<String>, Optional<Pair<Justification, Set<Map<Variable, Value>>>>> runPlanningSimulations(String scenarioInput, String startingEmbodiment, String endingEmbodiment, int beginningTime, int endingTime, Formula stepgoal, Optional<List<Variable>> stepgoalvars) throws Reader.ParsingException {
        Map<String, Set<Map<String, Set<Pair<String, String>>>>> environmentEmbodiments = CollectionUtils.newMap();
        //System.out.println(scenarioInput);
        String[] scenarioLines = scenarioInput.split("\\|");
        for (String line: scenarioLines){

            String[] lineParts = line.split(",");
            String embodiment = lineParts[0].trim();
            String action = lineParts[1].trim();
            String consequent = lineParts[2].trim();
            String consequentType = lineParts[3].trim();

            Set<Map<String, Set<Pair<String,String>>>> currentEmbodiment;
            if (environmentEmbodiments.containsKey(embodiment)){            //Does the data structure already have the embodiment contained
                currentEmbodiment = environmentEmbodiments.get(embodiment);
            }else{
                currentEmbodiment = CollectionUtils.newEmptySet();
                environmentEmbodiments.put(embodiment, currentEmbodiment);
            }

            Map<String, Set<Pair<String, String>>> currentAction = CollectionUtils.newMap();
            boolean foundAction = false;
            for (Map<String, Set<Pair<String, String>>> actionConsequent: currentEmbodiment){ //Does the data structure already have this action associated with the embodiment
                if (actionConsequent.containsKey(action)){
                    currentAction = actionConsequent;
                    foundAction = true;
                }
            }
            if(!foundAction) {
                currentEmbodiment.add(currentAction);
                currentAction.put(action, CollectionUtils.newEmptySet());
            }

            currentAction.get(action).add(Pair.of(consequent, consequentType));

        }

        Set<Map<String, Set<Pair<String, String>>>> potentialBeginnings = environmentEmbodiments.get(startingEmbodiment);

        Set<Map<String, Set<Pair<String, String>>>> potentialEndings = environmentEmbodiments.get(endingEmbodiment);

        Set<Map<String, Set<Pair<String, String>>>> potentialMiddles = CollectionUtils.newEmptySet();
        for(String key: environmentEmbodiments.keySet()){
            if(!key.equals(startingEmbodiment) && !key.equals(endingEmbodiment)){
                potentialMiddles.addAll(environmentEmbodiments.get(key));
            }
        }

        Set<List<Map<String, Set<Pair<String, String>>>>> scenarioListSet = CollectionUtils.newEmptySet();

        for(Map<String, Set<Pair<String, String>>> beginning: potentialBeginnings){

            Set<Map<String, Set<Pair<String, String>>>> temp = CollectionUtils.newEmptySet();
            for(Map<String, Set<Pair<String, String>>> tempBeginning: potentialBeginnings){
                if(!tempBeginning.equals(beginning)){
                    temp.add(tempBeginning);
                }
            }
            potentialMiddles.addAll(temp);

            for(Map<String, Set<Pair<String, String>>> ending: potentialEndings){

                Set<Map<String, Set<Pair<String, String>>>> temp2 = CollectionUtils.newEmptySet();
                for(Map<String, Set<Pair<String, String>>> tempEnding: potentialEndings){
                    if(!tempEnding.equals(ending)){
                        temp2.add(tempEnding);
                    }
                }
                potentialMiddles.addAll(temp2);

                //TODO Find all combinations of potentialMiddleElements (probs recursion)
                Set<List<Map<String, Set<Pair<String, String>>>>> combinationOfPotentialMiddles = findAllCombinations(potentialMiddles);

                for(List<Map<String, Set<Pair<String, String>>>> potentialMid: combinationOfPotentialMiddles){
                    List<Map<String, Set<Pair<String, String>>>> tempAnswerVar = CollectionUtils.newEmptyList();
                    tempAnswerVar.add(beginning);
                    tempAnswerVar.addAll(potentialMid);
                    tempAnswerVar.add(ending);
                    scenarioListSet.add(tempAnswerVar);
                }

                potentialMiddles.removeAll(temp2);
            }

            potentialMiddles.removeAll(temp);
        }

        System.out.println(scenarioListSet);

        Set<String> uniqueIdentityCues = CollectionUtils.newEmptySet();
        Set<String> migrationCues = CollectionUtils.newEmptySet();
        Set<Pair<String, String>> cueTypings = CollectionUtils.newEmptySet();
        Optional<Pair<Justification, Set<Map<Variable, Value>>>> successfulSimulation = Optional.empty();
        List<String> correctPlan = CollectionUtils.newEmptyList();

        for(Set<Map<String, Set<Pair<String, String>>>> embodimentActionConsequentSet: environmentEmbodiments.values()){
            for(Map<String, Set<Pair<String, String>>> embodimentActionConsequent: embodimentActionConsequentSet){
                for(Set<Pair<String, String>> consequentPairSet: embodimentActionConsequent.values()){
                    for(Pair<String, String> consequentPair: consequentPairSet){

                        String identityCue = consequentPair.getLeft().split(" ")[2].trim();
                        String migrationCue = consequentPair.getLeft().split(" ")[2].trim();

                        if(consequentPair.getLeft().split(" ")[0].trim().compareTo("(PresentingUniqueIdentityCue") == 0){
                            identityCue = identityCue.substring(0, identityCue.length()-1);
                            uniqueIdentityCues.add(identityCue);
                        }
                        if(consequentPair.getLeft().split(" ")[0].trim().compareTo("(PresentingMigrationCue") == 0){
                            migrationCue = migrationCue.substring(0, migrationCue.length()-1);
                            migrationCues.add(migrationCue);
                        }

                        String cueTypeString = consequentPair.getRight();
                        Pair<String, String> cueWithTyping = Pair.of(identityCue, cueTypeString);
                        cueTypings.add(cueWithTyping);
                    }
                }
            }
        }

        for(List<Map<String, Set<Pair<String, String>>>> scenarioList: scenarioListSet) {
            System.out.println(scenarioList);


            for(int i = 0; i < scenarioList.size(); i++) {

                List<String> baseFormula = CollectionUtils.newEmptyList();
                String workableString = scenarioList.toString();
                String[] scenarioSteps = workableString.split(", ");
                for (int l = 0; l < scenarioSteps.length; l++) {
                    String workingStep = scenarioSteps[l];
                    String[] workingStepParts = workingStep.split("[\\(\\[\\{\\)\\]\\}=,]+");
                    String actionName = workingStepParts[1];
                    String consequent = workingStepParts[2];
                    String ConTyping = workingStepParts[3];

                    baseFormula.add(consequent);
                }

                int timeCount = 0;
                for(String form:baseFormula){
                    timeCount++;
                    Formula inputForm = Reader.readFormulaFromString("(HoldsAt (" + form + ") t" + timeCount + ")");
                    boardStateAssumptions.add(inputForm);
                }

                for(String uniqueIdentityCue: uniqueIdentityCues){
                    Formula isUIDForm = Reader.readFormulaFromString("(IsUniqueIdentityCue " + uniqueIdentityCue + ")");
                    boardStateAssumptions.add(isUIDForm);
                }

                for(String migrationCue: migrationCues){
                    Formula isMigCueForm = Reader.readFormulaFromString("(IsMigrationCue " + migrationCue + ")");
                    boardStateAssumptions.add(isMigCueForm);
                }

                for(Pair<String, String> typingPair: cueTypings){
                    Formula cueTypeForm = Reader.readFormulaFromString("(HasCueTyping " + typingPair.getLeft() + " " + typingPair.getRight() + ")");
                    boardStateAssumptions.add(cueTypeForm);
                }

                //stepgoal = Reader.readFormulaFromString("(MaintainingIdentityWithCue " + startingEmbodiment + " " + endingEmbodiment + " ?x)");
                //Variable var = new Variable("?x");
                //List varList = CollectionUtils.newEmptyList();
                //varList.add(var);
                //stepgoalvars = Optional.of(varList);

                Optional<Pair<Justification, Set<Map<Variable, Value>>>> results = runSimulation(beginningTime, endingTime, stepgoal, stepgoalvars, startingEmbodiment, endingEmbodiment);
                if(results.isPresent()){
                    successfulSimulation = results;
                    correctPlan = baseFormula;
                    return Pair.of(correctPlan, successfulSimulation);
                }

            }

        }

        return Pair.of(correctPlan, successfulSimulation);

    }

    public Set<List<Map<String, Set<Pair<String, String>>>>> findAllCombinations(Set<Map<String, Set<Pair<String, String>>>> input){
        if(input.size() == 1){
            List inputList = CollectionUtils.newEmptyList();
            inputList.add(input);
            Set inputListsSet = CollectionUtils.newEmptySet();
            inputListsSet.add(inputList);
            return inputListsSet;
        }else{
            Set<Map<String, Set<Pair<String, String>>>> temp = CollectionUtils.newEmptySet();
            temp.addAll(input);

            Set<List<Map<String, Set<Pair<String, String>>>>> answerSet = CollectionUtils.newEmptySet();

            for(Map<String, Set<Pair<String, String>>> obj: temp){

                Set<Map<String, Set<Pair<String, String>>>> innerTemp = CollectionUtils.newEmptySet();
                innerTemp.addAll(input);

                List tempAnswerList = CollectionUtils.newEmptyList();

                innerTemp.remove(obj);
                Set<List<Map<String, Set<Pair<String, String>>>>> innerRecurseResponse = findAllCombinations(innerTemp);
                for(List<Map<String, Set<Pair<String, String>>>> responseList: innerRecurseResponse){
                    Set shellSet = CollectionUtils.newEmptySet();
                    shellSet.add(obj);
                    tempAnswerList.add(shellSet);
                    for(int i = 0; i < responseList.size(); i++) {
                        tempAnswerList.add(responseList.get(i));
                    }
                    answerSet.add(tempAnswerList);
                }

            }
            return answerSet;

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
        int problemNumber = 1;

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

        Problem q = tests.get(0);
        //System.out.println(p.getGoal());
        //System.out.println(p.getAnswerVariables());
        //System.out.println(testTheory1.checkSimulationStep(1, stepGoal, sGV));
        //testTheory1.extractEventsFromAssumptions();
        //System.out.println(testTheory1.simulationEvents);
        //System.out.println(testTheory1.boardStateAssumptions);
        String finalAnswerString = "";
        for(int i = 0; i < 9; i++){
            testTheory1 = new SimulationSandbox(i, fileName);
            Problem p = tests.get(i);
            Long startTime = System.currentTimeMillis();
            Optional<Pair<Justification, Set<Map<Variable, Value>>>> proof = testTheory1.runSimulation(1, 4, p.getGoal(), p.getAnswerVariables(), "?x", "?y");
            Long endTime = System.currentTimeMillis();
            if(proof.isPresent()){
                String responseString = "Proved and Run time for " + p.getName() + " was " + (endTime - startTime) + " milliseconds.\n";
                System.out.println(responseString);
                finalAnswerString = finalAnswerString + responseString;
            }else{
                String responseString = "Not Proven and Run time for " + p.getName() + " was " + (endTime - startTime) + " milliseconds.\n";
                System.out.println(responseString);
                finalAnswerString = finalAnswerString + responseString;
            }
        }
        System.out.println(finalAnswerString);

        //testTheory1.runSimulation(0, 6, p.getGoal(), p.getAnswerVariables(), "Projector", "ToyRobot");

        String scenario = "Phone, displayAvatar, (PresentingUniqueIdentityCue Phone robotHead2), visualFeatures | " +
                          "Phone, speakWithAvatarVoice, (PresentingUniqueIdentityCue Phone steveTTS001), auditoryVoiceCue | " +
                          "Projector, displayAvatar, (PresentingUniqueIdentityCue Projector robotHead2), visualFeatures | " +
                          "ToyRobot, speakWithAvatarVoice, (PresentingUniqueIdentityCue ToyRobot steveTTS001), auditoryVoiceCue";

        //Pair<List<String>, Optional<Pair<Justification, Set<Map<Variable, Value>>>>> finalResult = testTheory1.runPlanningSimulations(scenario, "Projector", "ToyRobot", 1, 5, p.getGoal(), p.getAnswerVariables());
        //System.out.println(finalResult.getLeft());
    }

}
