{:name Maintaining_the_Identity_of_Dynamically_Embodied_Agents
 :description ""
 :assumptions
    {A1 (Believes! human t0 (HasTraitType blackAndYellow color))

     A2 (Perceives! human t1 (HasIdentifyingTrait (embodiment a) blackAndYellow) )

     A3 (Perceives! human t2 (HasIdentifyingTrait (embodiment b) blackAndYellow) )

     A4 (Believes! human t3 (if (exists [?agent1 ?agent2 ?trait]
                                    (and
                                        (or
                                            (HasTraitType ?trait color)
                                            (HasTraitType ?trait class)
                                            (HasTraitType ?trait features)
                                            (HasTraitType ?trait markings)
                                        )
                                        (HasIdentifyingTrait ?agent1 ?trait)
                                        (HasIdentifyingTrait ?agent2 ?trait)
                                    )
                                )
                                (= (identityOf ?agent1) (identityOf ?agent2) )))

    }

    A5 (Believes! human t4 (and
                                (IsMoreEffectiveThan features color)
                                (IsMoreEffectiveThan features class)
                                (IsMoreEffectiveThan features markings)
                           ))

 :goal  (Believes! human t4 (= (identityOf (embodiment a)) (identityOf (embodiment b)) ))
}

{:name Generic-Direct-Migration-Test
 :description ""
 :assumptions
    {A1 (Perceives! human t1 (Holds (IdentifyingTrait (embodiment a) TTSVoiceCarol) t2 ))

     A2 (Perceives! human t2 (Holds (IdentifyingTrait (embodiment b) TTSVoiceCarol) t3 ))

     A3 (Believes! human t3 (if (exists [?agent1 ?agent2 ?trait ?time1 ?time2]
                                    (and
                                        (Holds (IdentifyingTrait ?agent1 ?trait) ?time1)
                                        (Holds (IdentifyingTrait ?agent2 ?trait) ?time2)
                                    )
                                )
                                (= (identityOf ?agent1) (identityOf ?agent2) )))

    }

 :goal  (Believes! human t4 (= (identityOf (embodiment a)) (identityOf (embodiment c))))
}

{:name Indirect-Migration-Test
 :description ""
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