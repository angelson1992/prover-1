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

{:name CognitiveCompositeRectangleLogic5By4And1By2WithSecondOrder
 :assumptions
    {

    A1  (Believes! TIPPAE t0
            (Believes! human t0
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
         )

    A2  (Believes! TIPPAE t0
            (Believes! human t0
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
        )

    ;;Reasoning over areas of rectangles

    A5 (Perceives! TIPPAE t2 (Perceives! human t2 (IsRectangle rect)))

    A6 (Perceives! TIPPAE t2 (Perceives! human t2 (IsRectLengthOf 5 rect)))

    A7 (Perceives! TIPPAE t2 (Perceives! human t2 (IsRectWidthOf 4 rect)))

    A8 (Perceives! TIPPAE t3 (Perceives! human t3 (IsRectangle rect2)))

    A9 (Perceives! TIPPAE t3 (Perceives! human t3 (IsRectLengthOf 1 rect2)))

    A10 (Perceives! TIPPAE t3 (Perceives! human t3 (IsRectWidthOf 2 rect2)))

    A11 (Perceives! TIPPAE t4 (Perceives! human t4 (IsCompositeRectangle compRect1)))

    A12 (Perceives! TIPPAE t4 (Perceives! human t4 (IsPartRect1Of rect compRect1)))

    A13 (Perceives! TIPPAE t4 (Perceives! human t4 (IsPartRect2Of rect2 compRect1)))

    }

 :goal (Believes! TIPPAE t5 (Believes! human t5 (IsAreaOf 22 compRect1) ))

}