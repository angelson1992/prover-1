
{
:name Test-ValidityInheritance-with-2LayerChoiceList-WIP
:assumptions
  {

;; A1 and its subsections explicitly state the definition of the various types of TwoByOneMult steps
;; along with stating that it is fine to directly replace those steps with their broken down versions.

  A1   (forall (?MultiplicandTensDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
          (=  (TwoByOneMult ?MultiplicandTensDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
              (Addition
                  (MultiplicandOnesDigitTimesMultiplierOnesDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
                  (MultiplicandTensDigitTimesMultiplierOnesDigit ?MultiplicandTensDigit ?MultiplierOnesDigit)
              )
          )
       )

  A1-2 (forall (?MultiplicandTensDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
          (= (TwoByOneMultErr1 ?MultiplicandTensDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
              (Addition
                  (MultiplicandTensDigitTimesMultiplicandOnesDigit ?MultiplicandTensDigit ?MultiplicandOnesDigit)
                  (MultiplicandTensDigitTimesMultiplierOnesDigit ?MultiplicandTensDigit ?MultiplierOnesDigit)
              )
          )
       )

  A1-3 (forall (?MultiplicandTensDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
          (= (TwoByOneMultErr2 ?MultiplicandTensDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
              (Addition
                  (MultiplicandOnesDigitTimesMultiplierOnesDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
                  (MultiplicandTensDigitTimesMultiplicandOnesDigit ?MultiplicandTensDigit ?MultiplicandOnesDigit)
              )
          )
       )

;; This is not currently being used.

  A2  (forall (?MultiplicandOnesDigit ?MultiplierOnesDigit)
          (Define
              (MultiplicandOnesDigitTimesMultiplierOnesDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
              (Optionally (OneByOneMultAttempt ?MultiplicandOnesDigit ?MultiplierOnesDigit) ?Choice)
          )
      )

  A3  (forall (?MultiplicandTensDigit ?MultiplierOnesDigit)
          (Define
              (MultiplicandTensDigitTimesMultiplierOnesDigit ?MultiplicandTensDigit ?MultiplierOnesDigit)
              (* (* ?MultiplicandTensDigit 10) ?MultiplierOnesDigit)
          )
      )

  A4  (forall (?MultiplicandTensDigit ?MultiplicandOnesDigit)
          (Define
              (MultiplicandTensDigitTimesMultiplicandOnesDigit ?MultiplicandTensDigit ?MultiplicandOnesDigit)
              (* ?MultiplicandTensDigit ?MultiplicandOnesDigit)
          )
      )

;; A5-A8 are representing that validity of various possible steps one could take in the process of multiplying
;; a two digit number by a one digit number. They are used to both state that a specific step is valid or
;; invalid as an assertion and to state that it is fine to substitute to substitute the raw step with
;; the explicit validity statement during reasoning.

  A5  (forall (?MultiplicandOnesDigit ?MultiplierOnesDigit)
          (and
              (Validity
                  (MultiplicandOnesDigitTimesMultiplierOnesDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
                  Valid
              )
              (=
                  (MultiplicandOnesDigitTimesMultiplierOnesDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
                  (Validity
                      (MultiplicandOnesDigitTimesMultiplierOnesDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
                      Valid
                  )
              )
          )
      )

  A6  (forall (?MultiplicandTensDigit ?MultiplierOnesDigit)
          (and
              (Validity
                  (MultiplicandTensDigitTimesMultiplierOnesDigit ?MultiplicandTensDigit ?MultiplierOnesDigit)
                  Valid
              )
              (=
                  (MultiplicandTensDigitTimesMultiplierOnesDigit ?MultiplicandTensDigit ?MultiplierOnesDigit)
                  (Validity
                      (MultiplicandTensDigitTimesMultiplierOnesDigit ?MultiplicandTensDigit ?MultiplierOnesDigit)
                      Valid
                  )
              )
          )
      )

  A7  (forall (?MultiplicandOnesDigit ?MultiplierOnesDigit)
          (and
              (Validity
                  (MultiplicandOnesDigitTimesMultiplierOnesDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
                  Valid
              )
              (=
                  (MultiplicandOnesDigitTimesMultiplierOnesDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
                  (Validity
                      (MultiplicandOnesDigitTimesMultiplierOnesDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
                      Valid
                  )
              )
          )
      )

  A8  (forall (?MultiplicandTensDigit ?MultiplicandOnesDigit)
          (and
              (Validity
                  (MultiplicandTensDigitTimesMultiplicandOnesDigit ?MultiplicandTensDigit ?MultiplicandOnesDigit)
                  Invalid
              )
              (=
                  (MultiplicandTensDigitTimesMultiplicandOnesDigit ?MultiplicandTensDigit ?MultiplicandOnesDigit)
                  (Validity
                      (MultiplicandTensDigitTimesMultiplicandOnesDigit ?MultiplicandTensDigit ?MultiplicandOnesDigit)
                      Invalid
                  )
              )
          )
      )

;; A9 and it's subsections are a representation of how the validity of two part addition steps
;; is built and maintained from it's parts.

  A9  (forall (?AddedStep1 ?AddedStep2)
          (Validity
              (Addition (Validity ?AddedStep1 Valid) (Validity ?AddedStep2 Valid) )
              Valid
          )
      )

  A9-1(forall (?AddedStep1 ?AddedStep2)
          (Validity
              (Addition (Validity ?AddedStep1 Invalid) (Validity ?AddedStep2 Valid) )
              Invalid
          )
      )

  A9-2(forall (?AddedStep1 ?AddedStep2)
          (Validity
              (Addition (Validity ?AddedStep1 Valid) (Validity ?AddedStep2 Invalid) )
              Invalid
          )
      )

  A9-3(forall (?AddedStep1 ?AddedStep2)
          (Validity
              (Addition (Validity ?AddedStep1 Invalid) (Validity ?AddedStep2 Invalid) )
              Invalid
          )
      )

;; A10 and it's subsections are a representation of how the validity of two part multiplication steps
;; is built and maintained from it's parts.

  A10 (forall (?AddedStep1 ?AddedStep2)
          (Validity
              (Multiplication (Validity ?AddedStep1 Valid) (Validity ?AddedStep2 Valid) )
              Valid
          )
      )

  A10-1(forall (?AddedStep1 ?AddedStep2)
          (Validity
              (Multiplication (Validity ?AddedStep1 Invalid) (Validity ?AddedStep2 Valid) )
              Invalid
          )
      )

  A10-2(forall (?AddedStep1 ?AddedStep2)
          (Validity
              (Multiplication (Validity ?AddedStep1 Valid) (Validity ?AddedStep2 Invalid) )
              Invalid
          )
      )

  A10-3(forall (?AddedStep1 ?AddedStep2)
          (Validity
              (Multiplication (Validity ?AddedStep1 Invalid) (Validity ?AddedStep2 Invalid) )
              Invalid
          )
      )

;; A11 through A13 are representations of various attempts at multiplying two digit numbers by one digit
;; numbers. We'll call this idea an option tree.

  A11   (forall (?MultiplicandTensDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
          (Optionally
              (TwoByOneMultAttempt ?MultiplicandTensDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
              (TwoByOneMult ?MultiplicandTensDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
          )
       )

  A12   (forall (?MultiplicandTensDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
          (Optionally
              (TwoByOneMultAttempt ?MultiplicandTensDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
              (TwoByOneMultErr1 ?MultiplicandTensDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
          )
       )

  A13   (forall (?MultiplicandTensDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
          (Optionally
              (TwoByOneMultAttempt ?MultiplicandTensDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
              (TwoByOneMultErr2 ?MultiplicandTensDigit ?MultiplicandOnesDigit ?MultiplierOnesDigit)
          )
       )

;; A14 through A17 are representations of various attempts at multiplying one digit numbers by one digit
;; numbers. We'll call this idea an option tree.

  A14   (forall (?MultiplierDigit ?MultiplicandDigit)
          (Optionally
              (OneByOneMultAttempt ?MultiplierDigit ?MultiplicandDigit)
              (OneByOneMult ?MultiplierDigit ?MultiplicandDigit)
          )
       )

  A15   (forall (?MultiplierDigit ?MultiplicandDigit)
          (Optionally
              (OneByOneMultAttempt ?MultiplierDigit ?MultiplicandDigit)
              (OneByOneMultErr1 ?MultiplierDigit ?MultiplicandDigit)
          )
       )

  A16   (forall (?MultiplierDigit ?MultiplicandDigit)
          (Optionally
              (OneByOneMultAttempt ?MultiplierDigit ?MultiplicandDigit)
              (OneByOneMultErr2 ?MultiplierDigit ?MultiplicandDigit)
          )
       )

  A17   (forall (?MultiplierDigit ?MultiplicandDigit)
          (Optionally
              (OneByOneMultAttempt ?MultiplierDigit ?MultiplicandDigit)
              (OneByOneMultErr3 ?MultiplierDigit ?MultiplicandDigit)
          )
       )

  A18  (forall (?Attempt ?Choice)
          (=
              (Answered (Optionally ?Attempt ?Choice) )
              ?Choice
          )
       )


  }

 :answer-variables [?x, ?valid]

 :answers-expected ( )

;; ABANDON: The notion of doing math in the snark. This is because we wouldn't have the lowest levels be unique.
;; ABANDON: The notion of using the justification in further logic. It's difficult to access in addition to not holding the direct math.
;; NOTE: The output will most likely be the lowest level of representation when I write their validity.
;; QUESTION: Can we replace the intermediate validity ratings and how can I write the rules for those.
;; NOTE: Keep in mind that all parts of the branching logic need to be unique identified to work.
;; CONSIDER: Embracing the limitations we're running into with multilevel branching.
;;      It's possible that creating a system that explicitly calls the lower levels gives up access to specifying their validity or simply assuming they are correct.
;;      Current attempt is to build a similar problem based on the results and then run it on the results.

 :goal
        (and
            (Validity ?x ?valid)
            (Optionally (TwoByOneMultAttempt 1 5 5) ?x) ;;This represents the questions being analysed and is not optional
        )

}

{:name Teleportation-test
 :assumptions
    {A1 (Perceives! human t1 (Believes! (embodies a) t1 (Holds (Prop watch stopped) t1 )))
     A2 (Perceives! human t2 (Believes! (embodies b) t2 (Holds (Prop watch stopped) t2 )))

     A3 (Believes! human t3 (PersonalObject watch))
     A4 (Believes! human t4 (if (exists [?agent1 ?agent2 ?u ?prop ?time1 ?time2]
                                    (and
                                        (PersonalObject ?u)
                                        (Believes! ?agent1 ?time1 (Holds (Prop ?u ?prop) ?time1))
                                        (Believes! ?agent2 ?time2 (Holds (Prop ?u ?prop) ?time2))
                                    )
                                )
                                (= (identityOf ?agent1) (identityOf ?agent2) )))
    }

 :goal  (Believes! human t5 (= (identityOf (embodiment a)) (identityOf (embodiment b))))
}

{:name Indirect-Migration-Test
 :assumptions
    {A1 (Perceives! human t1 (Holds (IdentifyingTrait (embodiment a) wagglesEyebrows) t1 ))
     A2 (Perceives! human t2 (Holds (IdentifyingTrait (embodiment a) TTSVoiceCarol) t2 ))

     A3 (Perceives! human t3 (Holds (IdentifyingTrait (embodiment b) TTSVoiceCarol) t3 ))
     A4 (Perceives! human t4 (Holds (IdentifyingTrait (embodiment b) AngularRoutes) t4 ))

     A5 (Perceives! human t5 (Holds (IdentifyingTrait (embodiment c) AngularRoutes) t5 ))

     A6 (Believes! human t4 (if (exists [?agent1 ?agent2 ?trait ?time1 ?time2]
                                    (and
                                        (Holds (IdentifyingTrait ?agent1 ?trait) ?time1)
                                        (Holds (IdentifyingTrait ?agent2 ?trait) ?time2)
                                    )
                                )
                                (= (identityOf ?agent1) (identityOf ?agent2) )))

    }

 :goal  (Believes! human t5 (= (identityOf (embodiment a)) (identityOf (embodiment c))))
}

{:name DecompTest
 :assumptions
    {A1 (forall (?Rectangle ?RectLength ?RectWidth)
            (if
                (and
                    (IsRectangle ?Rectangle)
                    (IsRectLengthOf ?RectLength ?Rectangle)
                    (IsRectWidthOf ?RectWidth ?Rectangle)
                )
                (IsAreaOf (&&product ?RectLength ?RectWidth) ?Rectangle)
            )
        )

     A2 (IsRectangle rect)

     A3 (IsRectLengthOf 5 rect)

     A4 (IsRectWidthOf 4 rect)

     A5 (forall (?Line ?LineLengthTotal ?LineLengthPart1out2)
            (if
                (and
                    (IsLine ?Line)
                    (IsLineLengthTotalOf ?LineLengthTotal ?Line)
                    (IsLineLengthPart1out2Of ?LineLengthPart1out2 ?Line)
                )
                (IsLineLengthPart2out2Of ($$difference ?LineLengthTotal ?LineLengthPart1out2) ?Line)
            )
        )

     A6 (IsLine line)

     A7 (IsLineLengthTotalOf 6 line)

     A8 (IsLineLengthPart1out2Of 5 line)

     A9 (forall (?CompositeRectangle ?PartRect1 ?PartRect2 ?AreaOfPartRect1 ?AreaOfPartRect2)
            (if
                (and
                    (IsCompositeRectangle ?CompositeRectangle)
                    (IsPartRect1Of ?PartRect1 ?CompositeRectangle)
                    (IsPartRect2Of ?PartRect2 ?CompositeRectangle)
                    (IsAreaOf ?AreaOfPartRect1 ?PartRect1)
                    (IsAreaOf ?AreaOfPartRect2 ?PartRect2)
                )
                (IsAreaOf ($$sum ?AreaOfPartRect1 ?AreaOfPartRect2) ?CompositeRectangle)
            )
        )

    A10 (IsRectangle rect2)

    A11 (IsRectLengthOf 1 rect2)

    A12 (IsRectWidthOf 2 rect2)

    A13 (IsCompositeRectangle compRect1)

    A14 (IsPartRect1Of rect compRect1)

    A15 (IsPartRect2Of rect2 compRect1)

    }

 :answer-variables [?x]

 :answers-expected ( )

 :goal (IsAreaOf ?x compRect1)
}

{:name CognitiveSnarkMathTest
 :assumptions
    {A1 (Perceives! human t1 (Believes! (embodies a) t1 (Holds (Prop watch stopped) t1 )))
     A2 (Perceives! human t2 (Believes! (embodies b) t2 (Holds (Prop watch stopped) t2 )))

     A3 (Believes! human t3 (PersonalObject watch))
     A4 (Believes! human t4 (if (exists [?agent1 ?agent2 ?u ?prop ?time1 ?time2]
                                    (and
                                        (PersonalObject ?u)
                                        (Believes! ?agent1 ?time1 (Holds (Prop ?u ?prop) ?time1))
                                        (Believes! ?agent2 ?time2 (Holds (Prop ?u ?prop) ?time2))
                                    )
                                )
                                (= (identityOf ?agent1) (identityOf ?agent2) )))
    }

 :goal  (Believes! human t5 (= ($$product 5 5) (25) ))
}

{:name CognitiveDecompTestRectangleLogic
 :assumptions
    {;;Reasoning over areas of rectangles
    A1  (Believes! human t1
            (forall (?Rectangle ?RectLength ?RectWidth)
                (if
                    (and
                        (IsRectangle ?Rectangle)
                        (IsRectLengthOf ?RectLength ?Rectangle)
                        (IsRectWidthOf ?RectWidth ?Rectangle)
                    )
                    (IsAreaOf ($$product ?RectLength ?RectWidth) ?Rectangle)
                )
            )
        )

     A2 (Perceives! human t2 (IsRectangle rect))

     A3 (Perceives! human t3 (IsRectLengthOf 5 rect))

     A4 (Perceives! human t4 (IsRectWidthOf 4 rect))

    }

 :goal (Believes! human t5 (IsAreaOf (20) rect) )
}

{:name CognitiveCompositeRectangleLogic5By4And1By2
 :assumptions
    {;;Reasoning over areas of rectangles
    A1  (Believes! human t1
            (forall (?Rectangle ?RectLength ?RectWidth)
                (if
                    (and
                        (IsRectangle ?Rectangle)
                        (IsRectLengthOf ?RectLength ?Rectangle)
                        (IsRectWidthOf ?RectWidth ?Rectangle)
                    )
                    (IsAreaOf ($$product ?RectLength ?RectWidth) ?Rectangle)
                )
            )
        )

    A2 (Believes! human t1
            (forall (?CompositeRectangle ?PartRect1 ?PartRect2 ?AreaOfPartRect1 ?AreaOfPartRect2)
                (if
                    (and
                        (IsCompositeRectangle ?CompositeRectangle)
                        (IsPartRect1Of ?PartRect1 ?CompositeRectangle)
                        (IsPartRect2Of ?PartRect2 ?CompositeRectangle)
                        (IsAreaOf ?AreaOfPartRect1 ?PartRect1)
                        (IsAreaOf ?AreaOfPartRect2 ?PartRect2)
                    )
                    (IsAreaOf ($$sum ?AreaOfPartRect1 ?AreaOfPartRect2) ?CompositeRectangle)
                )
            )
        )

    A3 (Perceives! human t2 (IsRectangle rect))

    A4 (Perceives! human t2 (IsRectLengthOf 5 rect))

    A5 (Perceives! human t2 (IsRectWidthOf 4 rect))

    A6 (Perceives! human t3 (IsRectangle rect2))

    A7 (Perceives! human t3 (IsRectLengthOf 1 rect2))

    A8 (Perceives! human t3 (IsRectWidthOf 2 rect2))

    A9 (Perceives! human t4 (IsCompositeRectangle compRect1))

    A10 (Perceives! human t4 (IsPartRect1Of rect compRect1))

    A11 (Perceives! human t4 (IsPartRect2Of rect2 compRect1))

    }

 :goal (Believes! human t5 (IsAreaOf 22 compRect1) )
}

{:name CognitiveCompositeRectangleLogic5By2And6By2
 :assumptions
    {;;Reasoning over areas of rectangles
    A1  (Believes! human t1
            (forall (?Rectangle ?RectLength ?RectWidth)
                (if
                    (and
                        (IsRectangle ?Rectangle)
                        (IsRectLengthOf ?RectLength ?Rectangle)
                        (IsRectWidthOf ?RectWidth ?Rectangle)
                    )
                    (IsAreaOf ($$product ?RectLength ?RectWidth) ?Rectangle)
                )
            )
        )

    A2 (Believes! human t1
            (forall (?CompositeRectangle ?PartRect1 ?PartRect2 ?AreaOfPartRect1 ?AreaOfPartRect2)
                (if
                    (and
                        (IsCompositeRectangle ?CompositeRectangle)
                        (IsPartRect1Of ?PartRect1 ?CompositeRectangle)
                        (IsPartRect2Of ?PartRect2 ?CompositeRectangle)
                        (IsAreaOf ?AreaOfPartRect1 ?PartRect1)
                        (IsAreaOf ?AreaOfPartRect2 ?PartRect2)
                    )
                    (IsAreaOf ($$sum ?AreaOfPartRect1 ?AreaOfPartRect2) ?CompositeRectangle)
                )
            )
        )

    A3 (Perceives! human t2 (IsRectangle rect))

    A4 (Perceives! human t2 (IsRectLengthOf 5 rect))

    A5 (Perceives! human t2 (IsRectWidthOf 2 rect))

    A6 (Perceives! human t3 (IsRectangle rect2))

    A7 (Perceives! human t3 (IsRectLengthOf 6 rect2))

    A8 (Perceives! human t3 (IsRectWidthOf 2 rect2))

    A9 (Perceives! human t4 (IsCompositeRectangle compRect1))

    A10 (Perceives! human t4 (IsPartRect1Of rect compRect1))

    A11 (Perceives! human t4 (IsPartRect2Of rect2 compRect1))

    }

 :goal (Believes! human t5 (IsAreaOf 22 compRect1) )
}