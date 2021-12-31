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
    {C10    (Common! now (IsTrait slow_movements) )
     C11   (Common! now (IsTrait rapid_movements))
     ;;C12   (Common! now (IsTrait penetrating_look))
     ;;C13   (Common! now (IsTrait restless_glance))
     ;;C14   (Common! now (IsTrait active_neck))
     C15   (Common! now (IsTrait sputnik_form))
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
                                 (HasTraitType ?personalityTrait1 personality)
                                 (HasTraitType ?appearanceTrait appearance)
                                 (HasIdentifyingTrait ?embodiment1 ?personalityTrait1)
                                 (HasIdentifyingTrait ?embodiment2 ?personalityTrait1)
                                 (HasIdentifyingTrait ?embodiment1 ?appearanceTrait)
                                 (HasIdentifyingTrait ?embodiment2 ?appearanceTrait)
                                 (HasIdentifyingTrait ?embodiment3 ?appearanceTrait)
                             )

                                (TeleportedInto (identityOf ?embodiment1) (identityOf ?embodiment2) ?personalityTrait1)

                         )
                     )
       )

     A1 (Perceives! human t1 (and (HasIdentifyingTrait (embodiment a) sputnik_form) (HasIdentifyingTrait (embodiment a) rapid_movements)) )

     A2 (Perceives! human t2 (and (HasIdentifyingTrait (embodiment b) sputnik_form) (HasIdentifyingTrait (embodiment b) rapid_movements)) )

     A3 (Perceives! human t3 (and (HasIdentifyingTrait (embodiment c) sputnik_form) (HasIdentifyingTrait (embodiment c) slow_movements)) )

    }

 :goal (Believes! human t4 (TeleportedInto (identityOf (embodiment a)) (identityOf (embodiment b)) rapid_movements))

}

{:name Generic-Direct-Migration-Test
 :description ""
 :assumptions
    {A1 (Perceives! human t1 (IdentifyingTrait (embodiment a) TTSVoiceCarol))

     A2 (Perceives! human t2 (IdentifyingTrait (embodiment b) TTSVoiceCarol))

     A3 (Common! now (forall [?agent1 ?agent2 ?trait ?time1 ?time2]
                                (if
                                    (and
                                        (IdentifyingTrait ?agent1 ?trait)
                                        (IdentifyingTrait ?agent2 ?trait)
                                    )

                                    (TeleportedInto (identityOf ?agent1) (identityOf ?agent2) )

                                )
                     )
        )

    }

 :goal  (Believes! human t4 (TeleportedInto (identityOf (embodiment a)) (identityOf (embodiment b))))
}

{:name Identity_of_a_companion_migrating_between_robots_without_common_communication_modalities_initial_results_of_VHRI_study
 :description ""
 :assumptions
    {A1 (Perceives! human t1 (IdentifyingTrait (embodiment a) wagglesEyebrows))
     A2 (Perceives! human t2 (IdentifyingTrait (embodiment a) TTSVoiceCarol))

     A3 (Perceives! human t3 (IdentifyingTrait (embodiment b) TTSVoiceCarol))
     A4 (Perceives! human t4 (IdentifyingTrait (embodiment b) AngularRoutes))

     A5 (Perceives! human t5 (IdentifyingTrait (embodiment c) AngularRoutes))

     A6 (Common! now (forall [?agent1 ?agent2 ?trait]
                                (if
                                    (and
                                        (IdentifyingTrait ?agent1 ?trait)
                                        (IdentifyingTrait ?agent2 ?trait)
                                    )

                                    (TeleportedInto (identityOf ?agent1) (identityOf ?agent2) )
                                )
                     )
        )

     A7 (Common! now (forall [?agent1 ?agent2 ?agent3]
                                 (if
                                     (and
                                         (TeleportedInto (identityOf ?agent1) (identityOf ?agent2) )
                                         (TeleportedInto (identityOf ?agent2) (identityOf ?agent3) )
                                     )

                                     (TeleportedInto (identityOf ?agent1) (identityOf ?agent3) )
                                 )
                      )
         )

    }

 :goal  (Believes! human t7 (TeleportedInto (identityOf (embodiment a)) (identityOf (embodiment c))))
}

{:name Prototyping_Realistic_Long-term_Human-Robot_Interaction_for_the_Study_of_Agent_Migration
 :description "This formalism takes what is presented in this paper and interprets it as a migration cue"
 :assumptions
    { C1 (Common! now (and
                         (IsTeleportationCue commandTeleport)
                         (IsTeleportationCue requestTeleport)
                         (IsTeleCueInit commandTeleInit)
                         (IsTeleCueInit requestTeleInit)
                         (IsTeleCueFinish commandTeleFinish)
                         (IsTeleCueFinish requestTeleFinish)
                         (forall ?teleportationCue (exists [?teleCueInit ?teleCueFinish] (TeleportationCueHalves ?teleportationCue ?teleCueInit ?teleCueFinish)))
                      )
         )

      C2 (Common! now (and
                           (TeleportationCueHalves commandTeleport commandTeleInit commandTeleFinish)
                           (TeleportationCueHalves requestTeleport requestTeleInit requestTeleFinish)
                      )
         )

      C3 (Common! now (forall [?embodiment1 ?embodiment2 ?teleportationCue ?teleCueInit ?teleCueFinish ?time]
                          (if
                              (and
                                  (IsTeleportationCue ?teleportationCue)
                                  (TeleportationCueHalves ?teleportationCue ?teleCueInit ?teleCueFinish)
                                  (IsPresentingTeleCue ?embodiment1 ?teleCueInit)
                                  (IsPresentingTeleCue ?embodiment2 ?teleCueFinish)
                              )

                              (TeleportedInto (identityOf ?embodiment1) (identityOf ?embodiment2) )
                          )
                      )
         )

      A1 (Perceives! human t2 (and
                                (IsPresentingTeleCue (embodiment a) requestTeleInit)
                                (IsPresentingTeleCue (embodiment b) requestTeleFinish)
                              )
         )



    }

 :goal  (Believes! human t4 (TeleportedInto (identityOf (embodiment a)) (identityOf (embodiment b)) ))
}