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

                             (TeleportedInto (identityOf ?embodiment1) (identityOf ?embodiment2) )
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

 :goal (Believes! human t4 (TeleportedInto (identityOf (embodiment a)) (identityOf (embodiment b)) ))
}

{:name A_User_Study_on_Visualization_of_Agent_Migration_between_Two_Companion_Robots
 :description "This formalism is for the idea of teleportation cues, specifically migrating faces"
 :assumptions
    { C1 (Common! now (and
                         (IsTeleportationCue movingFace)
                         (IsTeleCueInit movingFaceInit)
                         (IsTeleCueFinish movingFaceFinish)
                         (IsTeleportationCue movingBar)
                         (forall ?teleportationCue (exists [?teleCueInit ?teleCueFinish] (TeleportationCueHalves ?teleportationCue ?teleCueInit ?teleCueFinish)))
                      )
         )

      C2 (Common! now (TeleportationCueHalves movingFace movingFaceInit movingFaceFinish))

      C3 (Common! now (forall [?embodiment1 ?embodiment2 ?teleportationCue ?teleCueInit ?teleCueFinish ?time]
                          (if
                              (and
                                  (IsTeleportationCue ?teleportationCue)
                                  (TeleportationCueHalves ?teleportationCue ?teleCueInit ?teleCueFinish)
                                  (Holds (IsPresentingTeleCue ?embodiment1 ?teleCueInit) ?time)
                                  (Holds (IsPresentingTeleCue ?embodiment2 ?teleCueFinish) ?time)
                              )

                              (TeleportedInto (identityOf ?embodiment1) (identityOf ?embodiment2) )
                          )
                      )
         )

      A1 (Perceives! human t2 (and
                                (Holds (IsPresentingTeleCue (embodiment a) movingFaceInit) t1)
                                (Holds (IsPresentingTeleCue (embodiment b) movingFaceFinish) t1)
                              )
         )



    }

 :goal  (Believes! human t4 (TeleportedInto (identityOf (embodiment a)) (identityOf (embodiment b)) ))
}

{:name Agent_Migration_between_Bodies_and_Platforms
 :description "This formalism test is for the idea of using personality as a pair of agent mood and reaction strategy (e.g. shy, arrogant, friendly) as identity cues"
 :assumptions
    {C1 (Common! now (and
                        (IsTrait friendly)
                        (IsTraitType personality)
                        (IsMood pleasant)
                        (IsInteractionStrategy motivating)
                        (forall ?trait (exists ?traitType (HasTraitType ?trait ?traitType)))
                        (forall ?personalityTrait
                            (exists [?mood ?interactionStrategy]
                                (PersonalityComponentsAre ?personalityTrait ?mood ?interactionStrategy)
                            )
                        )
                     )
        )

     C2 (and
            (Common! now (HasTraitType friendly personality))
            (Common! now (PersonalityComponentsAre friendly pleasant motivating))
        )

     C3 (Common! now (forall [?embodiment1 ?embodiment2 ?trait]
                         (if
                             (and
                                 (IsTrait ?trait)
                                 (HasIdentifyingTrait ?embodiment1 ?trait)
                                 (HasIdentifyingTrait ?embodiment2 ?trait)
                             )

                             (TeleportedInto (identityOf ?embodiment1) (identityOf ?embodiment2) )
                         )
                     )
        )

     A1 (Perceives! human t1 (HasIdentifyingTrait (embodiment a) friendly) )

     A2 (Perceives! human t2 (HasIdentifyingTrait (embodiment b) friendly) )

    }

 :goal (Believes! human t4 (TeleportedInto (identityOf (embodiment a)) (identityOf (embodiment b)) ))
}

{:name Identity_of_socially_interactive_robotic_twins_initial_results_of_VHRI_study
 :description "This formalism test is for the idea of using personality as a distinguishing identifying cue between several identical looking embodiments"
 :assumptions
    {;;C1    (Common! now (IsTrait slow_movements) )
     ;;C11   (Common! now (IsTrait rapid_movements))
     ;;C12   (Common! now (IsTrait penetrating_look))
     ;;C13   (Common! now (IsTrait restless_glance))
     ;;C14   (Common! now (IsTrait active_neck))
     ;;C15   (Common! now (IsTrait sputnik_form))
     ;;C16   (Common! now (IsTraitType personality))
     ;;C17   (Common! now (IsTraitType appearance))
     ;;C18   (Common! now (forall ?trait (exists ?traitType (HasTraitType ?trait ?traitType))))



     C2     (Common! now (HasTraitType slow_movements personality))
     C21    (Common! now (HasTraitType rapid_movements personality))
     ;;C22    (Common! now (HasTraitType penetrating_look personality))
     ;;C23    (Common! now (HasTraitType restless_glance personality))
     ;;C24    (Common! now (HasTraitType active_neck personality))
     C25    (Common! now (HasTraitType sputnik_form appearance))


     C3 (Common! now (forall [?embodiment1 ?embodiment2 ?embodiment3 ?personalityTrait1 ?personalityTrait2 ?appearanceTrait]
                         (if
                             (and
                                 ;;(IsTrait ?personalityTrait1)
                                 ;;(IsTrait ?personalityTrait2)
                                 ;;(IsTrait ?appearanceTrait)
                                 (HasTraitType ?personalityTrait1 personality)
                                 (HasTraitType ?personalityTrait2 personality)
                                 (HasTraitType ?appearanceTrait appearance)
                                 (HasIdentifyingTrait ?embodiment1 ?personalityTrait1)
                                 (HasIdentifyingTrait ?embodiment2 ?personalityTrait1)
                                 (HasIdentifyingTrait ?embodiment3 ?personalityTrait2)
                                 (HasIdentifyingTrait ?embodiment1 ?appearanceTrait)
                                 (HasIdentifyingTrait ?embodiment2 ?appearanceTrait)
                                 (HasIdentifyingTrait ?embodiment3 ?appearanceTrait)
                             )

                             (and
                                (TeleportedInto (identityOf ?embodiment1) (identityOf ?embodiment2) )
                                (not (TeleportedInto (identityOf ?embodiment1) (identityOf ?embodiment3)))
                                (not (TeleportedInto (identityOf ?embodiment2) (identityOf ?embodiment3)))
                             )
                         )
                     )
        )

     A1 (Perceives! human t1 (and (HasIdentifyingTrait (embodiment a) sputnik_form) (HasIdentifyingTrait (embodiment a) rapid_movements)) )

     A2 (Perceives! human t2 (and (HasIdentifyingTrait (embodiment b) sputnik_form) (HasIdentifyingTrait (embodiment b) rapid_movements)) )

     A3 (Perceives! human t3 (and (HasIdentifyingTrait (embodiment c) sputnik_form) (HasIdentifyingTrait (embodiment c) slow_movements)) )

    }

 :goal (Believes! human t4 (and (HasIdentifyingTrait (embodiment a) sputnik_form)
                                (HasIdentifyingTrait (embodiment b) sputnik_form)
                                (HasIdentifyingTrait (embodiment c) sputnik_form)
                            ))
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