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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class SimulationSandbox {

    private int timeStep;
    private Set<Formula> boardStateAssumptions;
    private Formula boardStateGoal;
    private static SnarkWrapper snarkProver = SnarkWrapper.getInstance();
    private static CognitiveCalculusProver ccProver = new CognitiveCalculusProver();
    private Map<Integer, Set<Formula>> simulationEvents;

    public Map<Integer, Set<Formula>> getSimulationEvents() {
        return simulationEvents;
    }

    public SimulationSandbox(int problemNumber, String fileName) throws Exception{
        timeStep = 0;
        boardStateAssumptions = getTheoryAssumptions(problemNumber, fileName);
        simulationEvents = CollectionUtils.newMap();
    }

    public Set<Formula> getTheoryAssumptions(int problemNumber, String fileName) throws Exception{
        List<Problem> tests = ProblemReader.readFrom(SimulationSandbox.class.getResourceAsStream(fileName));
        Problem p = tests.get(problemNumber);
        return p.getAssumptions();
    }

    public Optional<Pair<Justification, Set<Map<Variable, Value>>>> checkSimulationStep(int checkedTimeStep, Formula stepGoal, Optional<List<Variable>> stepGoalVariables){
        if(simulationEvents.containsKey(checkedTimeStep)){  //If there are events in this time step
            Set<Formula> newEvents = simulationEvents.get(checkedTimeStep); //Gather those events in variable newEvents
            for(Formula event : newEvents){ //for each event in newEvents
                boardStateAssumptions.add(event); //add that event to the boardStateAssumptions
            }
        }

        Optional<Pair<Justification, Set<Map<Variable, Value>>>> proof = snarkProver.proveAndGetMultipleBindings(boardStateAssumptions, stepGoal, stepGoalVariables.get()); //Find what variables prove the stepGoal
        return proof;
    }

    public void runSimulation(int beginningStep, int endingStep, Formula stepGoal, Optional<List<Variable>> stepGoalVariables){

        for (int i = beginningStep; i < endingStep; i++){

            System.out.println("Simulating timeStep " + String.valueOf(i));
            System.out.println(checkSimulationStep(i, stepGoal, stepGoalVariables));

        }

    }



    public static void main(String[] args) throws Exception{

        SimulationSandbox testTheory1 = new SimulationSandbox(0, "../teleportation_axioms_noncog.clj");
        System.out.println(testTheory1.boardStateAssumptions);

        Formula eventT1 = Reader.readFormulaFromString("(HasIdentifyingTrait (embodiment a) blackAndYellow)");
        Set<Formula> eventsT1Set = CollectionUtils.newEmptySet();
        eventsT1Set.add(eventT1);
        Formula eventT2 = Reader.readFormulaFromString("(HasIdentifyingTrait (embodiment b) blackAndYellow)");
        Set<Formula> eventsT2Set = CollectionUtils.newEmptySet();
        eventsT2Set.add(eventT2);
        testTheory1.getSimulationEvents().put(1, eventsT1Set);
        testTheory1.getSimulationEvents().put(2, eventsT2Set);

        Formula stepGoal = Reader.readFormulaFromString("(TeleportedInto ?x ?y)");
        Variable x = new Variable("?x"); Variable y = new Variable("?y"); //Variable z = new Variable("?z");
        List<Variable> stepGoalVariables = CollectionUtils.newEmptyList();
        stepGoalVariables.add(x); stepGoalVariables.add(y); //stepGoalVariables.add(z);
        Optional<List<Variable>> sGV = Optional.of(stepGoalVariables);

        //List<Problem> tests = ProblemReader.readFrom(SimulationSandbox.class.getResourceAsStream("../teleportation_axioms_noncog.clj"));
        //Problem p = tests.get(1);
        //System.out.println(p.getGoal());
        //System.out.println(p.getAnswerVariables());
        //System.out.println(testTheory1.checkSimulationStep(1, stepGoal, sGV));
        testTheory1.runSimulation(0, 4, stepGoal, sGV);

    }

}
