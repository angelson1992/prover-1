package com.naveensundarg.shadow.prover.sandboxes;

import com.naveensundarg.shadow.prover.core.*;
import com.naveensundarg.shadow.prover.core.ccprovers.CognitiveCalculusProver;
import com.naveensundarg.shadow.prover.core.ccprovers.SecondOrderProver;
import com.naveensundarg.shadow.prover.core.proof.Justification;
import com.naveensundarg.shadow.prover.representations.formula.Formula;
import com.naveensundarg.shadow.prover.representations.value.Value;
import com.naveensundarg.shadow.prover.representations.value.Variable;
import com.naveensundarg.shadow.prover.utils.*;
import com.naveensundarg.shadow.prover.utils.Reader;
import org.apache.commons.lang3.tuple.Pair;

import java.io.*;
import java.util.*;

import static us.bpsm.edn.Keyword.newKeyword;

/**
 *
 */
public class TippaeSandbox {

    private static SnarkWrapper snarkProver;

    private static boolean isCognitiveProof = false; //Set this variable to determine if using Cognitive prover or only Snark
    private static int specificTestNumber = 10; //Set this int to -1 to run all test, set from 0 to NumberOfTest-1 to indicate which test to run
    private static boolean redirectConsole = false;
    private static String logLocation = "consoleCout.log";
    private static boolean readLogMode = false;

    private static int RefutationCount = 0;
    private static int RowCount = 0;
    private static int ProofSymbolCount = 0;

    public static void main(String[] args) throws Exception {

        if(readLogMode) {
            String rawConsole = fileToString(logLocation);
            parseSExpr(rawConsole.substring(0, rawConsole.lastIndexOf(")")));
            System.out.println(ProofSymbolCount-RowCount);
        }

        if(redirectConsole) {
            File coutCapture = new File(logLocation);
            PrintStream logger = new PrintStream(coutCapture);
            System.setOut(logger);
        }

        snarkProver = SnarkWrapper.getInstance();

        List<Problem> tests = ProblemReader.readFrom(TippaeSandbox.class.getResourceAsStream("../teleportation_axioms.clj"));

        for (int i = 0; i < tests.size(); i++) {

            if ( (specificTestNumber > -1 && specificTestNumber == i) || specificTestNumber == -1 ) {

                Problem p = (tests.get(i));

                CognitiveCalculusProver cognitiveCalculusProver = new CognitiveCalculusProver();

                Prover prover;

                if (isCognitiveProof) {
                    prover = cognitiveCalculusProver;
                } else {
                    prover = snarkProver;
                }

                if (p.getAnswerVariables().isPresent()) {

                    Long startTime = System.currentTimeMillis();

                    Optional<Pair<Justification, Set<Map<Variable, Value>>>> answer = prover.proveAndGetMultipleBindings(p.getAssumptions(), p.getGoal(), p.getAnswerVariables().get());

                    Long endTime = System.currentTimeMillis();

                    System.out.println(p.getName());

                    if (answer.isPresent()) {

                        System.out.println("Proved and the answer is [" + answer.get().getRight() + "]");
                        System.out.println("Run time was " + (endTime - startTime) + " milliseconds.");

                    } else {

                        System.out.println("No!");

                    }

                } else {

                    Long startTime = System.currentTimeMillis();

                    Optional<Justification> justificationOptional = (prover.prove(p.getAssumptions(), p.getGoal()));

                    Long endTime = System.currentTimeMillis();

                    System.out.println(p.getName());

                    if (justificationOptional.isPresent()) {

                        System.out.println("Proved!");
                        System.out.println("Run time was " + (endTime - startTime) + " milliseconds.");

                    } else {

                        System.out.println("No!");

                    }

                }

            }

        }

    }

    private static String fileToString(String input){

        String fileAsString = "";
        try (BufferedReader br = new BufferedReader(new FileReader(logLocation))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileAsString = fileAsString + line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileAsString;
    }

    private static String parseSExpr(String input){

        input = input.trim();
        //System.out.println(input);

        if(input.isEmpty()){
            return input;
        }
        else if(input.startsWith("(")){
            return parseSExpr(input.substring(1));
        }else if(input.startsWith(")")){
            return input;
        }else{
            int skipNumber = 0;
            int nextSpaceIndex = input.indexOf(" ");
            int nextOpenParenIndex = input.indexOf("(");
            if(nextOpenParenIndex < nextSpaceIndex && nextOpenParenIndex != -1){
                skipNumber = nextOpenParenIndex;
            }else{
                skipNumber = nextSpaceIndex;
            }
            if(skipNumber <= 0){
                skipNumber = input.length();
            }

            if(input.startsWith("Refutation")){
                RefutationCount++;
            }
            if(input.startsWith("Row")){
                RowCount++;
            }

            if (!(input.startsWith("Refutation") || input.startsWith("Row"))){
                System.out.println(input.substring(0, skipNumber));
                ProofSymbolCount++;
            }

            return parseSExpr(input.substring(skipNumber));
        }

    }

    private static Value getDefinition(Problem prob, Variable inputVar, Value input) throws Reader.ParsingException {

        List<Variable> vars = prob.getAnswerVariables().get();
        Variable var = vars.get(0);

        Formula defGoal = Reader.readFormulaFromString( "(Define" +
                                                        "   " + input.toString() +
                                                        "   " + var.toString() +
                                                        ")");

        System.out.println(defGoal);

//        Optional<Pair<Justification, Set<Map<Variable, Value>>>> ans = snarkProver.proveAndGetMultipleBindings(prob.getAssumptions(), defGoal, vars);

        return null;
    }

    //A function to convert a simplest form S-formatted math question into
    private static Formula questionToFormula(String question){

        if(question.startsWith("(")){question = question.substring(1);}
        if(question.endsWith(")")){question = question.substring(0, question.length()-1);}
        String[] splitString = question.split(" ");
        for (int i = 0; i < splitString.length; i++) {
            System.out.print(splitString[i] + ",");
        }

        //TODO as I've just realized that this is non-critical

        return null;
    }

    private static String numToSuccessor(int num){

        if (num == 0){
            return "0";
        }else if (num > 0){
            String answer = "(S 0)";
            for (int i = 1; i < num; i++){
                answer = "(S" + answer + ")";
            }
            return answer;
        }else{
            System.err.print("numToSuccessor recieved a negative number");
            return "";
        }

    }

    private static String successorToNum(String suc){

        int answer = 0;
        while (suc.contains("(S")){
            answer++;
            suc = suc.substring(2);
        }
        return Integer.toString(answer);

    }

    private static Formula convertNumToSuc(Formula formula) throws Reader.ParsingException {

        String toBeConverted = formula.toString();
        String answer = "";
        String temp = "";

        for (int i = 0; i < toBeConverted.length(); i++){

            String currentChar = toBeConverted.substring(i, i+1);

            if(currentChar.matches("\\d") && ((i > 0) && !toBeConverted.substring(i-1,i).matches("[A-Za-z]"))){
                temp = temp + currentChar;
                //   Not the last character             and char to the right is a digit                       not the first element and there is not a letter to the left
                if (!(i < toBeConverted.length()-1 && toBeConverted.substring(i+1, i+2).matches("\\d") )) {
                    answer = answer + numToSuccessor(Integer.parseInt(temp));

                    temp = "";
                }

            }else{
                answer = answer + currentChar;
            }

        }

        return Reader.readFormulaFromString(answer);

    }

    private static Set<Formula> convertNumToSuc(Set<Formula> formulas) throws Reader.ParsingException {
        System.out.flush();
        Set<Formula> answer = CollectionUtils.newEmptySet();

        Iterator<Formula> iterator = formulas.iterator();
        while(iterator.hasNext()){
            Formula setElements = iterator.next();
            answer.add(convertNumToSuc(setElements));

        }

        return answer;
    }

    public static void main1(String[] args) throws Exception {


        SecondOrderProver secondOrderProver = new SecondOrderProver();

        //Formula formula1 = Reader.readFormulaFromString("(forall x (= (+ x 0) x))");
        //Formula formula2 = Reader.readFormulaFromString("(forall P (if (and (P 0) (forall x (if (P x) (P (s x))))) (forall x (P x))))");
        //Formula formula3univ = Reader.readFormulaFromString("(forall Y (exists Z (forall x (iff (Z x) (Y  x)))))");

        //Formula goal = Reader.readFormulaFromString("(exists Z (forall x (Z x)))");

        Formula form1 = Reader.readFormulaFromString("(forall (?x) (not (= 0 (S ?x))))");
        Formula form2 = Reader.readFormulaFromString("(forall (?x ?y) (if (= (S ?x) (S ?y)) (= ?x ?y) ))");
        Formula form3 = Reader.readFormulaFromString("(forall (?x) (= (add ?x 0) ?x))");
        Formula form4 = Reader.readFormulaFromString("(forall (?x ?y) (= (add ?x (S ?y)) (add (S ?x) ?y)))");
        Formula form5 = Reader.readFormulaFromString("(forall (?x) (= (mult ?x 0) 0))");
        Formula form6 = Reader.readFormulaFromString("(forall (?x ?y) (= (mult ?x (S ?y)) (add (mult ?x ?y) ?x)))");

        //Formula goal = Reader.readFormulaFromString("(forall x (not (= (S x) 0)))");
        //Formula goalTest1 = Reader.readFormulaFromString("(not (= 0 (S 76)))");
        //Formula goalTest2 = Reader.readFormulaFromString("(not (= 0 (S 76)))");
        //Formula goalTest3n4 = Reader.readFormulaFromString("(= (add (S(S 0)) (S 0) ) (S(S(S 0)))) )");
        //Formula goalTest5n6 = Reader.readFormulaFromString("(= (mult (S(S 0)) (S(S(S 0))) ) (S(S(S(S(S(S 0)))))) )");


        Formula TwobyOneDigMult = Reader.readFormulaFromString("(forall (?MultiplicandTensDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit) " +
                                                                        "(= (TwoByOneMult ?MultiplicandTensDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)" +
                                                                                    "(+ " +
                                                                                        "(MultiplicandOnesDigitTimesMultiplierOnesDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit) " +
                                                                                        "(MultiplicandTensDigitTimesMultiplierOnesDigit ?MultiplicandTensDigit ?MultiplierOnesDigit)" +
                                                                                    ")" +
                                                                        ")" +
                                                               ")");

        Formula MultiplicandOnesDigitTimesMultiplierOnesDigit = Reader.readFormulaFromString("(forall (?MultiplicandOnesDigit ?MultiplierOnesDigit) " +
                                                                                                "(= " +
                                                                                                    "(MultiplicandOnesDigitTimesMultiplierOnesDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)" +
                                                                                                    "(* ?MultiplicandOnesDigit ?MultiplierOnesDigit)" +
                                                                                                ") " +
                                                                                             ")");

        Formula MultiplicandTensDigitTimesMultiplierOnesDigit = Reader.readFormulaFromString("(forall (?MultiplicandTensDigit ?MultiplierOnesDigit) " +
                                                                                                "(= " +
                                                                                                    "(MultiplicandTensDigitTimesMultiplierOnesDigit ?MultiplicandTensDigit ?MultiplierOnesDigit)" +
                                                                                                    "(* (* ?MultiplicandTensDigit 10) ?MultiplierOnesDigit)" +
                                                                                                 ") " +
                                                                                            ")");

        Formula validStep1 = Reader.readFormulaFromString("(forall (?MultiplicandOnesDigit ?MultiplierOnesDigit)" +
                                                              "(Valid (MultiplicandOnesDigitTimesMultiplierOnesDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit) )" +
                                                          ")");

        Formula validStep2 = Reader.readFormulaFromString("(forall (?MultiplicandTensDigit ?MultiplierOnesDigit)" +
                                                              "(Valid (MultiplicandTensDigitTimesMultiplierOnesDigit ?MultiplicandTensDigit ?MultiplierOnesDigit) )" +
                                                          ")");

//        Formula validStep3 = Reader.readFormulaFromString("(forall (?MultiplicandOnesDigit ?MultiplierOnesDigit)" +
//                                                              "(Valid (MultiplicandOnesDigitTimesMultiplierOnesDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit) )" +
//                                                          ")");

        Formula invalidStep1 = Reader.readFormulaFromString("(forall (?MultiplicandTensDigit ?MultiplicandOnesDigit)" +
                                                              "(Invalid (MultiplicandTensDigitTimesMultiplicandOnesDigit ?MultiplicandTensDigit ?MultiplicandOnesDigit) )" +
                                                          ")");

        Formula validityCleaning = Reader.readFormulaFromString("(forall ?validStep" +
                                                                    "(if (Valid ?validStep) ?validStep )" +
                                                                ")");


        Formula goalTestMult1 = Reader.readFormulaFromString("(=" +
                                                                    "(TwoByOneMult 1 5 5)" +
                                                                    "(+" +
                                                                            "(MultiplicandOnesDigitTimesMultiplierOnesDigit 5 5)" +
                                                                            "(MultiplicandTensDigitTimesMultiplierOnesDigit 1 5)" +
                                                                    ")" +
                                                             ")");

        Formula goalTestMult2 = Reader.readFormulaFromString("(=" +
                                                                    "(TwoByOneMult 1 5 5)" +
                                                                    "(75)" +
                                                            ")");

        Formula goalTestingAdd = Reader.readFormulaFromString("(=" +
                                                                    "(+" +
                                                                        "(5)" +
                                                                        "(5)" +
                                                                    ")" +
                                                                    "(10))" +
                                                              ")");

        //Formula temp = (Logic.getFalseFormula());


        //Logic.transformSecondOrderToFirstOrderDeep(formula1);


        Sets.fromArray(new Formula[]{form1, form2, form3, form4, form5, form6, TwobyOneDigMult, validStep1, validStep2, invalidStep1, validityCleaning, MultiplicandOnesDigitTimesMultiplierOnesDigit, MultiplicandTensDigitTimesMultiplierOnesDigit, goalTestMult1}).stream().map(Logic::transformSecondOrderToFirstOrderDeep).forEach(System.out::println);

        long start = System.currentTimeMillis();

        Problem problem = new Problem("Format Test", "This is a test of the format", Sets.fromArray(new Formula[]{form1, form2, form3, form4, form5, form6, TwobyOneDigMult, validStep1, validStep2, invalidStep1, validityCleaning, MultiplicandOnesDigitTimesMultiplierOnesDigit, MultiplicandTensDigitTimesMultiplierOnesDigit}), goalTestMult1);

        Optional<Justification> answer = secondOrderProver.prove(Sets.fromArray(new Formula[]{form1, form2, form3, form4, form5, form6, TwobyOneDigMult, validStep1, validStep2, invalidStep1, validityCleaning, MultiplicandOnesDigitTimesMultiplierOnesDigit, MultiplicandTensDigitTimesMultiplierOnesDigit}), goalTestMult1);


        System.out.println(answer);
        System.out.println(answer.isPresent());


        long end = System.currentTimeMillis();

        System.out.println(end-start);

    }

    public static void mainl(String[] args) throws Exception {



         SnarkWrapper.getInstance();
    }

}
