{:name Maintaining_the_Identity_of_Dynamically_Embodied_Agents
 :description "This formalism test is for the idea of using color, class, features, and marking as identity cues"
 :assumptions
    {C1 (Common! now (and
                        (IsTrait blackAndYellow)
                        (IsTraitType color)
                        (IsTraitType class)
                        (IsTraitType features)
                        (IsTraitType markings)
                        (forall ?trait (exists ?traitType (HasTraitType ?trait ?traitType)))
                     )
        )

     C2 (Common! now (HasTraitType blackAndYellow color))

     C3 (Common! now (forall [?embodiment1 ?embodiment2 ?trait]
                         (if
                             (and
                                 (IsTrait ?trait)
                                 (HasIdentifyingTrait ?embodiment1 ?trait)
                                 (HasIdentifyingTrait ?embodiment2 ?trait)
                             )

                             (= (identityOf ?embodiment1) (identityOf ?embodiment2) )
                         )
                     )
        )

     C4 (Common! now (and
                            (IsMoreEffectiveThan features color)
                            (IsMoreEffectiveThan features class)
                            (IsMoreEffectiveThan features markings)
                        ))

     A1 (Perceives! human t1 (HasIdentifyingTrait (embodiment a) blackAndYellow) )

     A2 (Perceives! human t2 (HasIdentifyingTrait (embodiment b) blackAndYellow) )

    }

 :goal (Believes! human t4 (= (identityOf (embodiment a)) (identityOf (embodiment b)) ))
}

{:name A_User_Study_on_Visualization_of_Agent_Migration_between_Two_Companion_Robots
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