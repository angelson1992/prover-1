{:name DecompTest_5x4Rect+2x1Rect
 :assumptions
    {;;Reasoning over areas of rectangles
    A1 (forall (?Rectangle ?RectLength ?RectWidth)
            (if
                (and
                    (IsRectangle ?Rectangle)
                    (IsRectLengthOf ?RectLength ?Rectangle)
                    (IsRectWidthOf ?RectWidth ?Rectangle)
                )
                (IsAreaOf ($$product ?RectLength ?RectWidth) ?Rectangle)
            )
        )

     A2 (IsRectangle rect)

     A3 (IsRectLengthOf 5 rect)

     A4 (IsRectWidthOf 4 rect)

     ;;Reasoning over subtracting parts of a line from another line
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

    ;;Attempt to reason over composite shapes made of two rectangles and getting its area
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

 :goal (and
            (IsAreaOf ?x compRect1)
            (= ?x 22)
       )
}

{:name DecompTest_2x5Rect+6x2Rect
 :assumptions
    {;;Reasoning over areas of rectangles
    A1 (forall (?Rectangle ?RectLength ?RectWidth)
            (if
                (and
                    (IsRectangle ?Rectangle)
                    (IsRectLengthOf ?RectLength ?Rectangle)
                    (IsRectWidthOf ?RectWidth ?Rectangle)
                )
                (IsAreaOf ($$product ?RectLength ?RectWidth) ?Rectangle)
            )
        )

     A2 (IsRectangle rect)

     A3 (IsRectLengthOf 6 rect)

     A4 (IsRectWidthOf 2 rect)

     ;;Reasoning over subtracting parts of a line from another line
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

     A7 (IsLineLengthTotalOf 4 line)

     A8 (IsLineLengthPart1out2Of 2 line)

    ;;Attempt to reason over composite shapes made of two rectangles and getting its area
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

    A11 (IsRectLengthOf 2 rect2)

    A12 (IsRectWidthOf 5 rect2)

    A13 (IsCompositeRectangle compRect1)

    A14 (IsPartRect1Of rect compRect1)

    A15 (IsPartRect2Of rect2 compRect1)

    }

 :answer-variables [?x]

 :answers-expected ( )

 :goal (and
            (IsAreaOf ?x compRect1)
            (= ?x 22)
       )
}

{:name DecompTest_2x5Rect+6x2Rect_Artifacts
 :assumptions
    {;;Reasoning over areas of rectangles
    A1 (forall (?Rectangle ?RectLength ?RectWidth)
            (if
                (and
                    (IsRectangle ?Rectangle)
                    (IsRectLengthOf ?RectLength ?Rectangle)
                    (IsRectWidthOf ?RectWidth ?Rectangle)
                )
                (and
                    (IsAreaOf ($$product ?RectLength ?RectWidth) ?Rectangle)
                    (ArtifactExists product ?RectLength ?RectWidth ($$product ?RectLength ?RectWidth))
                )
            )
        )

     A2 (IsRectangle rect)

     A3 (IsRectLengthOf 6 rect)

     A4 (IsRectWidthOf 2 rect)

     ;;Reasoning over subtracting parts of a line from another line
     A5 (forall (?Line ?LineLengthTotal ?LineLengthPart1out2)
            (if
                (and
                    (IsLine ?Line)
                    (IsLineLengthTotalOf ?LineLengthTotal ?Line)
                    (IsLineLengthPart1out2Of ?LineLengthPart1out2 ?Line)
                )
                (and
                    (IsLineLengthPart2out2Of ($$difference ?LineLengthTotal ?LineLengthPart1out2) ?Line)
                    (ArtifactExists difference ?LineLengthTotal ?LineLengthPart1out2 ($$difference ?LineLengthTotal ?LineLengthPart1out2))
                )
            )
        )

     A6 (IsLine line)

     A7 (IsLineLengthTotalOf 4 line)

     A8 (IsLineLengthPart1out2Of 2 line)

    ;;Attempt to reason over composite shapes made of two rectangles and getting its area
     A9 (forall (?CompositeRectangle ?PartRect1 ?PartRect2 ?AreaOfPartRect1 ?AreaOfPartRect2)
            (if
                (and
                    (IsCompositeRectangle ?CompositeRectangle)
                    (IsPartRect1Of ?PartRect1 ?CompositeRectangle)
                    (IsPartRect2Of ?PartRect2 ?CompositeRectangle)
                    (IsAreaOf ?AreaOfPartRect1 ?PartRect1)
                    (IsAreaOf ?AreaOfPartRect2 ?PartRect2)
                )
                (and
                    (IsAreaOf ($$sum ?AreaOfPartRect1 ?AreaOfPartRect2) ?CompositeRectangle)
                    (ArtifactExists sum ?AreaOfPartRect1 ?AreaOfPartRect2 ($$sum ?AreaOfPartRect1 ?AreaOfPartRect2))
                )
            )
        )

    A10 (IsRectangle rect2)

    A11 (IsRectLengthOf 2 rect2)

    A12 (IsRectWidthOf 5 rect2)

    A13 (IsCompositeRectangle compRect1)

    A14 (IsPartRect1Of rect compRect1)

    A15 (IsPartRect2Of rect2 compRect1)

    }

 :answer-variables [?x ?a ?b ?c ?d]

 :answers-expected ( )

 :goal (and
            (IsAreaOf ?x compRect1)
            (ArtifactExists ?a ?b ?c ?d)
       )
}

{:name DecompTest_2x5Rect+6x2Rect_DifficultyCount
 :assumptions
    {;;Reasoning over areas of rectangles
    A1 (forall (?Rectangle ?RectLength ?RectWidth)
            (if
                (and
                    (IsRectangle ?Rectangle)
                    (IsRectLengthOf ?RectLength ?Rectangle)
                    (IsRectWidthOf ?RectWidth ?Rectangle)
                )
                (and
                    (IsAreaOf ($$product ?RectLength ?RectWidth) ?Rectangle)
                    ;;(ArtifactExists product ?RectLength ?RectWidth ($$product ?RectLength ?RectWidth))
                    (ComplexityMeasure aaaa 4 13)
                )
            )
        )

     A2 (IsRectangle rect)

     A3 (IsRectLengthOf 6 rect)

     A4 (IsRectWidthOf 2 rect)

     ;;Reasoning over subtracting parts of a line from another line
     A5 (forall (?Line ?LineLengthTotal ?LineLengthPart1out2)
            (if
                (and
                    (IsLine ?Line)
                    (IsLineLengthTotalOf ?LineLengthTotal ?Line)
                    (IsLineLengthPart1out2Of ?LineLengthPart1out2 ?Line)
                )
                (and
                    (IsLineLengthPart2out2Of ($$difference ?LineLengthTotal ?LineLengthPart1out2) ?Line)
                    ;;(ArtifactExists difference ?LineLengthTotal ?LineLengthPart1out2 ($$difference ?LineLengthTotal ?LineLengthPart1out2))
                    (ComplexityMeasure aaab 4 13)
                )
            )
        )

     A6 (IsLine line)

     A7 (IsLineLengthTotalOf 4 line)

     A8 (IsLineLengthPart1out2Of 2 line)

    ;;Attempt to reason over composite shapes made of two rectangles and getting its area
     A9 (forall (?CompositeRectangle ?PartRect1 ?PartRect2 ?AreaOfPartRect1 ?AreaOfPartRect2)
            (if
                (and
                    (IsCompositeRectangle ?CompositeRectangle)
                    (IsPartRect1Of ?PartRect1 ?CompositeRectangle)
                    (IsPartRect2Of ?PartRect2 ?CompositeRectangle)
                    (IsAreaOf ?AreaOfPartRect1 ?PartRect1)
                    (IsAreaOf ?AreaOfPartRect2 ?PartRect2)
                )
                (and
                    (IsAreaOf ($$sum ?AreaOfPartRect1 ?AreaOfPartRect2) ?CompositeRectangle)
                    ;;(ArtifactExists sum ?AreaOfPartRect1 ?AreaOfPartRect2 ($$sum ?AreaOfPartRect1 ?AreaOfPartRect2))
                    (ComplexityMeasure aaac 6 19)
                )
            )
        )

    ;;Attempt to reason over composite shapes made of two rectangles and getting its area
     A16 (forall (?AreaOfPartRect1 ?AreaOfPartRect2)
            (if
                (and
                    (IsAreaOf ?AreaOfPartRect1 55)
                    (IsAreaOf ?AreaOfPartRect2 99)
                )
                (and
                    (IsAreaOf ($$sum ?AreaOfPartRect1 ?AreaOfPartRect2) theRect)
                    ;;(ArtifactExists sum ?AreaOfPartRect1 ?AreaOfPartRect2 ($$sum ?AreaOfPartRect1 ?AreaOfPartRect2))
                    (ComplexityMeasure aaad 6 19)
                )
            )
        )

    A10 (IsRectangle rect2)

    A11 (IsRectLengthOf 2 rect2)

    A12 (IsRectWidthOf 5 rect2)

    A13 (IsCompositeRectangle compRect1)

    A14 (IsPartRect1Of rect compRect1)

    A15 (IsPartRect2Of rect2 compRect1)

    }

 :answer-variables [?x ?e ?f ?g]

 :answers-expected ( )

 :goal (and
            (IsAreaOf ?x compRect1)
            ;;(ArtifactExists ?a ?b ?c ?d)
            (ComplexityMeasure ?e ?f ?g)
       )
}

{:name BelowBasicQuestion1
 :assumptions
    {
        A0 (and
                (HasSameSize axe1 axe2)
                (HasSameShape axe1 axe2)
           )

        A1 (forall (?figure1 ?figure2)
                    (if
                        (and
                            (HasSameSize ?figure1 ?figure2)
                            (HasSameShape ?figure1 ?figure2)
                        )
                        (and
                            (AreCongruent ?figure1 ?figure2)
                            ;;(ArtifactExists none none none none)
                            (ComplexityMeasure aaae 3 9)
                            (SkillUsed 4 MG 3 3)
                        )
                    )
            )

        A2 (forall (?figure1 ?figure2)
                    (if
                        (HasSameSize ?figure1 ?figure2)
                        (and
                            (ComplexityMeasure aaaf 3 3)
                            (SkillUsed 1 MG 1 1)
                        )
                    )
            )

        A3 (forall (?figure1 ?figure2)
                    (if
                        (HasSameShape ?figure1 ?figure2)
                        (and
                            (ComplexityMeasure aaag 3 3)
                            (SkillUsed 1 MG 2 1)
                        )
                    )
            )

    }

 :answer-variables [?a ?b]

 :answers-expected ( )

 :goal (AreCongruent ?x ?y)
}

{:name BelowBasicQuestion2
 :assumptions
    {
        A0 (and
                (HasFaces pyramid fourTriangleOneSquare)
                (IsComposedOf optionFigure fourTriangleOneSquare)
           )

        A1 (forall (?figure1 ?solid1)
                    (if
                        (and
                            (HasFaces ?solid1 ?configuration)
                            (IsComposedOf ?figure1 ?configuration)
                        )
                        (and
                            (Is2DRepresentationOf ?figure1 ?solid1)
                            ;;(ArtifactExists none none none none)
                            (ComplexityMeasure aaae 3 9)
                            (SkillUsed 4 MG 3 6)
                        )
                    )
            )

        A2
            (if
                (IsComposedOf optionFigure fourTriangleOneSquare)
                (and
                    (ComplexityMeasure aaaf 2 2)
                    (SkillUsed 2 MG 2 2)
                )
            )


        A3
            (if
                (HasFaces pyramid fourTriangleOneSquare)
                (and
                    (ComplexityMeasure aaag 2 2)
                    (SkillUsed 2 MG 2 1)
                )
            )


    }

 :answer-variables [?x ?y]

 :answers-expected ( )

 :goal  (Is2DRepresentationOf ?x ?y)
}

{:name BasicQuestion1
 :assumptions
    {
        A0 (HaveSameAngle line1 line2)

        A1 (forall (?line1 ?line2)
                    (if
                        (not (Intersecting ?line1 ?line2))

                        (and
                            (AreParallel ?line1 ?line2)
                            ;;(ArtifactExists none none none none)
                            (ComplexityMeasure aaaa 3 6)
                            (SkillUsed 4 GM 3 1)
                        )
                    )
            )

        A2
            (forall (?line1 ?line2)
                    (if
                        (HaveSameAngle ?line1 ?line2)
                        (and
                            (not (Intersecting ?line1 ?line2))
                            (ComplexityMeasure aaab 3 6)
                        )
                    )
            )

    }

 :answer-variables [?x ?y]

 :answers-expected ( )

 :goal  (AreParallel ?x ?y)
}

{:name BasicQuestion2
 :assumptions
    {
        A0 (and
               (NumberOfSides optionFigure 4)
               (AllSidesAreEqualLength optionFigure)
               (NumberOfRightAngles optionFigure 4)
           )

        A1 (forall (?figure1)
                    (if
                        (and
                            (NumberOfSides ?figure1 4)
                            (AllSidesAreEqualLength ?figure1)
                            (NumberOfRightAngles ?figure1 4)
                        )

                        (and
                            (IsShape ?figure1 square)
                            ;;(ArtifactExists none none none none)
                            (ComplexityMeasure aaaa 2 11)
                            (SkillUsed 4 GM 3 8)
                        )
                    )
            )

    }

 :answer-variables [?x]

 :answers-expected ( )

 :goal  (IsShape ?x square)
}

{:name ProficientQuestion1
 :assumptions
    {
        A0 (and
               (IsHorizontalSegment questionSegment)
               (LineSegmentEndpoints questionSegment leftPoint1 rightPoint1)
               (PointCoordinatesAre leftPoint1 2 1)
               (PointCoordinatesAre rightPoint1 6 1)
           )

        A1 (forall (?lineSegment)
                    (if
                        (and
                            (IsHorizontalSegment ?lineSegment)
                            (LineSegmentEndpoints ?lineSegment ?leftPoint ?rightPoint)
                            (PointCoordinatesAre ?leftPoint ?x1 ?y1)
                            (PointCoordinatesAre ?rightPoint ?x2 ?y2)
                        )

                        (and
                            (Length ?lineSegment ($$difference ?x2 ?x1))
                            ;;(ArtifactExists none none none none)
                            (ComplexityMeasure aaaa 4 17)
                            (SkillUsed 4 GM 2 2)
                        )
                    )
            )

    }

 :answer-variables [?x]

 :answers-expected ( )

 :goal  (Length questionSegment ?x)
}

{:name ProficientQuestion2
 :assumptions
    {
        A0 (and
               (IsVerticalSegment questionSegment)
               (LineSegmentEndpoints questionSegment topPoint1 bottomPoint1)
               (PointCoordinatesAre topPoint1 3 4)
               (PointCoordinatesAre bottomPoint1 3 -5)
           )

        A1 (forall (?lineSegment)
                    (if
                        (and
                            (IsVerticalSegment ?lineSegment)
                            (LineSegmentEndpoints ?lineSegment ?topPoint ?bottomPoint)
                            (PointCoordinatesAre ?topPoint ?x1 ?y1)
                            (PointCoordinatesAre ?bottomPoint ?x2 ?y2)
                        )

                        (and
                            (Length ?lineSegment ($$difference ?y1 ?y2))
                            ;;(ArtifactExists none none none none)
                            (ComplexityMeasure aaaa 4 17)
                            (SkillUsed 4 GM 2 3)
                        )
                    )
            )

    }

 :answer-variables [?x]

 :answers-expected ( )

 :goal  (Length questionSegment ?x)
}

{:name AdvancedQuestion1
 :assumptions
    {
    A0 (and
            (IsRectangle rect1)
            (IsRectLengthOf 15 rect1)
            (IsRectWidthOf 4 rect1)
            (IsRectangle rect2)
            (IsRectLengthOf 20 rect2)
            (IsRectWidthOf 3 rect2)
       )

    A1 (forall (?Rectangle ?RectLength ?RectWidth)
            (if
                (and
                    (IsRectangle ?Rectangle)
                    (IsRectLengthOf ?RectLength ?Rectangle)
                    (IsRectWidthOf ?RectWidth ?Rectangle)
                )
                (and
                    (IsAreaOf ($$product ?RectLength ?RectWidth) ?Rectangle)
                    ;;(ArtifactExists product ?RectLength ?RectWidth ($$product ?RectLength ?RectWidth))
                    (ComplexityMeasure aaaa 4 13)
                    (SkillUsed 4 GM 1 4)
                )
            )
        )

    ;;A2 (forall (?Rectangle ?RectLength ?RectWidth)
    ;;        (if
    ;;            (and
    ;;                (IsRectangle ?Rectangle)
    ;;                (IsRectLengthOf ?RectLength ?Rectangle)
    ;;                (IsRectWidthOf ?RectWidth ?Rectangle)
    ;;            )
    ;;            (and
    ;;                (IsPerimeterOf ($$sum ($$product ?RectLength 2) ($$product ?RectWidth 2) ) ?Rectangle)
    ;;                ;;(ArtifactExists product ?RectLength ?RectWidth ($$product ?RectLength ?RectWidth))
    ;;                (ComplexityMeasure aaab 4 15)
    ;;                (SkillUsed 4 GM 1 4)
    ;;            )
    ;;        )
    ;;    )

    }

 :answer-variables [?x ?y]

 :answers-expected ( )

 :goal (and
            (IsAreaOf ?x rect1)
            (IsAreaOf ?y rect2)
            (= ?x ?y)
            ;;(ArtifactExists ?a ?b ?c ?d)
       )
}

{:name AdvancedQuestion2
 :assumptions
    {
        A0 (and
               (IsVerticalSegment questionSegment)
               (LineSegmentEndpoints questionSegment topPoint1 bottomPoint1)
               (PointCoordinatesAre topPoint1 5 8)
               (PointCoordinatesAre bottomPoint1 5 1)
           )

        A1 (forall (?lineSegment)
                    (if
                        (and
                            (IsVerticalSegment ?lineSegment)
                            (LineSegmentEndpoints ?lineSegment ?topPoint ?bottomPoint)
                            (PointCoordinatesAre ?topPoint ?x1 ?y1)
                            (PointCoordinatesAre ?bottomPoint ?x2 ?y2)
                        )

                        (and
                            (Length ?lineSegment ($$difference ?y1 ?y2))
                            ;;(ArtifactExists none none none none)
                            (ComplexityMeasure aaaa 4 17)
                            (SkillUsed 4 GM 2 3)
                        )
                    )
            )

    }

 :answer-variables [?x]

 :answers-expected ( )

 :goal  (Length questionSegment ?x)
}

{:name AdvancedQuestion3
 :assumptions
    {
        A0 (and
               (IsCenterPointOf pointO questionCircle)
               (LineSegmentEndpoints lineSegmentAB pointA pointB)
               (IsOnCircle pointA questionCircle)
               (IsOnCircle pointB questionCircle)
               (SegmentPassesThrough lineSegmentAB pointO)
           )

        A1 (forall (?circle ?centerPoint ?lineSegment)
                    (if
                        (and
                            (IsCenterPointOf ?centerPoint ?circle)
                            (LineSegmentEndpoints ?lineSegment ?point1 ?point2)
                            (IsOnCircle ?point1 ?circle)
                            (IsOnCircle ?point2 ?circle)
                            (SegmentPassesThrough ?lineSegment ?centerPoint)
                        )

                        (and
                            (IsDiameterOf ?lineSegment ?circle)
                            ;;(ArtifactExists none none none none)
                            (ComplexityMeasure aaaa 4 19)
                            (SkillUsed 4 GM 3 2)
                        )
                    )
            )

    }

 :answer-variables [?x ?y]

 :answers-expected ( )

 :goal  (IsDiameterOf ?x ?y)
}

{:name AdvancedQuestion4
 :assumptions
    {
        A0 (and
               (NumberOfSides optionFigure 3)
               (AllSidesAreEqualLength optionFigure)
               (NumberOfAngles optionFigure 3)
               (AllAnglesAreAcute optionFigure)
           )

        A1 (forall (?figure1)
                    (if
                        (and
                            (NumberOfSides ?figure1 3)
                            (AllSidesAreEqualLength ?figure1)
                            (NumberOfAngles ?figure1 3)
                            (AllAnglesAreAcute ?figure1)
                        )

                        (and
                            (IsShape ?figure1 equilateralTriangle)
                            ;;(ArtifactExists none none none none)
                            (ComplexityMeasure aaaa 2 13)
                            (SkillUsed 4 GM 3 7)
                        )
                    )
            )

    }

 :answer-variables [?x]

 :answers-expected ( )

 :goal  (IsShape ?x equilateralTriangle)
}