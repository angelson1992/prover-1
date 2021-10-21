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
                    (ArtifactExists product ?RectLength ?RectWidth)
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
                    (ArtifactExists difference ?LineLengthTotal ?LineLengthPart1out2)
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
                    (ArtifactExists sum ?AreaOfPartRect1 ?AreaOfPartRect2)
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

 :answer-variables [?x ?a ?b ?c]

 :answers-expected ( )

 :goal (and
            (IsAreaOf ?x compRect1)
            (= ?x 22)
            (ArtifactExists ?a ?b ?c)
       )
}