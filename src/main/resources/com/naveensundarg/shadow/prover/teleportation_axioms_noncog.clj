{:name Maintaining_the_Identity_of_Dynamically_Embodied_Agents
 :description "This formalism test is for the idea of using color, class, features, and marking as identity cues"
 :assumptions
    {D1 (and
            (IsUniqueIdentityCue blackAndYellow)

            (IsSupportedIdentityCueTyping visualColorScheme)
            (IsSupportedIdentityCueTyping visualDistinguishingMarkings)
            (IsSupportedIdentityCueTyping visualClass)
            (IsSupportedIdentityCueTyping visualFeatures)
            (IsSupportedIdentityCueTyping behavioralDisplayedMood)
            (IsSupportedIdentityCueTyping behavioralInteractionStrategy)
            (IsSupportedIdentityCueTyping auditoryVoiceCue)
            (IsSupportedIdentityCueTyping auditoryDopplerCue)
            (IsSupportedIdentityCueTyping behavioralPersonalityIndicatingAction)
            (IsSupportedIdentityCueTyping behavioralTaskCompletionStrategy)
            (IsSupportedIdentityCueTyping behavioralTaskContinuation)

            (IsSupportedMigrationCueTyping visualMovingBar)
            (IsSupportedMigrationCueTyping visualMovingFace)
            (IsSupportedMigrationCueTyping visualFlashingLights)
            (IsSupportedMigrationCueTyping behavioralExpressiveActivityStateChange)
            (IsSupportedMigrationCueTyping behavioralPromptHuman)

            (ArePairedMigrationSignals visualMovingBar barDraining barFilling)
            (ArePairedMigrationSignals visualMovingFace faceDeparture faceArrival)
            (ArePairedMigrationSignals visualFlashingLights lightAccelerate lightDecelerate)
            (ArePairedMigrationSignals behavioralExpressiveActivityStateChange expressiveDeactivation expressiveActivation)
            (forall [?migrationCue] (if (IsSupportedCue ?migrationCue) (ArePairedMigrationSignals behavioralPromptHuman humanResponse ?migrationCue)))

            (IsSupportedUnfalsifiedIdentityCueTyping behavioralPersonalityIndicatingAction)
            (IsSupportedUnfalsifiedIdentityCueTyping behavioralSpecificQuirks)
            (IsSupportedUnfalsifiedIdentityCueTyping behavioralTaskCompletionStrategy)

        )

     D2 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
            (if
                (and
                    (PresentedUniqueIdentityCue ?embodiment1 ?uniqueIdentityCue)
                    (PresentingUniqueIdentityCue ?embodiment2 ?uniqueIdentityCue)
                    (IsSupportedCue ?uniqueIdentityCue)
                )
                (IdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
            )
        )

     D3 (forall [?uniqueIdentityCue ?identityCueTyping]
            (if
                (and
                    (HasCueTyping ?uniqueIdentityCue ?identityCueTyping)
                    (IsSupportedIdentityCueTyping ?identityCueTyping)
                    (IsUniqueIdentityCue ?uniqueIdentityCue)
                )
                (IsSupportedCue ?uniqueIdentityCue)
            )
        )

     D4 (forall [?embodiment1 ?embodiment2 ?migrationCue]
            (if
                (and
                    (PresentingMigrationCue ?embodiment1 ?departureCue)
                    (PresentingMigrationCue ?embodiment2 ?arrivalCue)
                    (ArePairedMigrationSignals ?migrationCue ?departureCue ?arrivalCue)
                    (IsSupportedCue ?migrationCue)
                )
                (MigrationRealizedWithCue ?embodiment1 ?embodiment2 ?migrationCue)
            )
        )

     D5 (forall [?migrationCue]
            (if
                (and
                    (HasCueTyping ?migrationCue ?migrationCueTyping)
                    (IsSupportedMigrationCueTyping ?migrationCueTyping)
                    (IsMigrationCue ?migrationCue)
                )
                (IsSupportedCue ?migrationCue)
            )
        )

     D6 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
            (if
                (and
                    (PresentedUniqueIdentityCueInContext ?embodiment1 ?uniqueIdentityCue ?context1)
                    (PresentingUniqueIdentityCueInContext ?embodiment2 ?uniqueIdentityCue ?context2)
                    (AreSimilarContext ?context1 ?context2)
                    (IsSupportedCue ?uniqueIdentityCue)
                )
                (IdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
            )
        )

     D7 (forall [?uniqueIdentityCue ?identityCueTyping]
            (if
                (and
                    (HasCueTyping ?uniqueIdentityCue ?identityCueTyping)
                    (IsSupportedUnfalsifiedIdentityCueTyping ?identityCueTyping)
                    (IsUniqueIdentityCue ?uniqueIdentityCue)
                )
                (IsSupportedUnfalsifiedCue ?uniqueIdentityCue)
            )
        )

     D8 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
            (if
                (and
                    (IsMoreEmbodiedThan ?embodiment1 ?embodiment2)
                    (PresentedUniqueIdentityCueInContext ?embodiment1 ?uniqueIdentityCue ?context1)
                    (PresentingUniqueIdentityCueInContext ?embodiment2 ?uniqueIdentityCue ?context2)
                    (IsSupportedUnfalsifiedCue ?uniqueIdentityCue)
                    (AreSimilarContext ?context1 ?context2)
                )
                (UnfalsifiedIdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
            )
        )

     D9 (forall [?embodiment1 ?embodiment2 ?embodiment3]
            (if
                (and
                    (UnfalsifiedIdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?cue1)
                    (UnfalsifiedIdentityMaintainedWithCue ?embodiment2 ?embodiment3 ?cue2)
                )
                (UnfalsifiedIdentityMaintainedWithCue ?embodiment1 ?embodiment3 ?cue2)
            )
        )

     C2 (HasCueTyping blackAndYellow visualColorScheme)

     C4 (and
            (HigherEffectThan visualFeatures visualDistinguishingMarkings)
            (HigherEffectThan visualFeatures visualClass)
        )

     A1 (HoldsAt (PresentingUniqueIdentityCue (embodiment a) blackAndYellow) t1) ;;at t1

     A2 (HoldsAt (PresentingUniqueIdentityCue (embodiment b) blackAndYellow) t2) ;;at t2

    }

 :answer-variables [?x ?y ?z]

 :answers-expected ( )

 :goal (IdentityMaintainedWithCue ?x ?y ?z) ;;at t4
}

{:name Agent_Migration_between_Bodies_and_Platforms
 :description "This formalism test is for the idea of using personality as a pair of agent mood and reaction strategy (e.g. shy, arrogant, friendly) as identity cues"
 :assumptions
    {D1 (and
                 (IsUniqueIdentityCue blackAndYellow)

                 (IsSupportedIdentityCueTyping visualColorScheme)
                 (IsSupportedIdentityCueTyping visualDistinguishingMarkings)
                 (IsSupportedIdentityCueTyping visualClass)
                 (IsSupportedIdentityCueTyping visualFeatures)
                 (IsSupportedIdentityCueTyping behavioralDisplayedMood)
                 (IsSupportedIdentityCueTyping behavioralInteractionStrategy)
                 (IsSupportedIdentityCueTyping auditoryVoiceCue)
                 (IsSupportedIdentityCueTyping auditoryDopplerCue)
                 (IsSupportedIdentityCueTyping behavioralPersonalityIndicatingAction)
                 (IsSupportedIdentityCueTyping behavioralTaskCompletionStrategy)
                 (IsSupportedIdentityCueTyping behavioralTaskContinuation)

                 (IsSupportedMigrationCueTyping visualMovingBar)
                 (IsSupportedMigrationCueTyping visualMovingFace)
                 (IsSupportedMigrationCueTyping visualFlashingLights)
                 (IsSupportedMigrationCueTyping behavioralExpressiveActivityStateChange)
                 (IsSupportedMigrationCueTyping behavioralPromptHuman)

                 (ArePairedMigrationSignals visualMovingBar barDraining barFilling)
                 (ArePairedMigrationSignals visualMovingFace faceDeparture faceArrival)
                 (ArePairedMigrationSignals visualFlashingLights lightAccelerate lightDecelerate)
                 (ArePairedMigrationSignals behavioralExpressiveActivityStateChange expressiveDeactivation expressiveActivation)
                 (forall [?migrationCue] (if (IsSupportedCue ?migrationCue) (ArePairedMigrationSignals behavioralPromptHuman humanResponse ?migrationCue)))

                 (IsSupportedUnfalsifiedIdentityCueTyping behavioralPersonalityIndicatingAction)
                 (IsSupportedUnfalsifiedIdentityCueTyping behavioralSpecificQuirks)
                 (IsSupportedUnfalsifiedIdentityCueTyping behavioralTaskCompletionStrategy)

             )

          D2 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
                 (if
                     (and
                         (PresentedUniqueIdentityCue ?embodiment1 ?uniqueIdentityCue)
                         (PresentingUniqueIdentityCue ?embodiment2 ?uniqueIdentityCue)
                         (IsSupportedCue ?uniqueIdentityCue)
                     )
                     (IdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
                 )
             )

          D3 (forall [?uniqueIdentityCue ?identityCueTyping]
                 (if
                     (and
                         (HasCueTyping ?uniqueIdentityCue ?identityCueTyping)
                         (IsSupportedIdentityCueTyping ?identityCueTyping)
                         (IsUniqueIdentityCue ?uniqueIdentityCue)
                     )
                     (IsSupportedCue ?uniqueIdentityCue)
                 )
             )

          D4 (forall [?embodiment1 ?embodiment2 ?migrationCue]
                 (if
                     (and
                         (PresentingMigrationCue ?embodiment1 ?departureCue)
                         (PresentingMigrationCue ?embodiment2 ?arrivalCue)
                         (ArePairedMigrationSignals ?migrationCue ?departureCue ?arrivalCue)
                         (IsSupportedCue ?migrationCue)
                     )
                     (MigrationRealizedWithCue ?embodiment1 ?embodiment2 ?migrationCue)
                 )
             )

          D5 (forall [?migrationCue]
                 (if
                     (and
                         (HasCueTyping ?migrationCue ?migrationCueTyping)
                         (IsSupportedMigrationCueTyping ?migrationCueTyping)
                         (IsMigrationCue ?migrationCue)
                     )
                     (IsSupportedCue ?migrationCue)
                 )
             )

          D6 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
                 (if
                     (and
                         (PresentedUniqueIdentityCueInContext ?embodiment1 ?uniqueIdentityCue ?context1)
                         (PresentingUniqueIdentityCueInContext ?embodiment2 ?uniqueIdentityCue ?context2)
                         (AreSimilarContext ?context1 ?context2)
                         (IsSupportedCue ?uniqueIdentityCue)
                     )
                     (IdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
                 )
             )

          D7 (forall [?uniqueIdentityCue ?identityCueTyping]
                 (if
                     (and
                         (HasCueTyping ?uniqueIdentityCue ?identityCueTyping)
                         (IsSupportedUnfalsifiedIdentityCueTyping ?identityCueTyping)
                         (IsUniqueIdentityCue ?uniqueIdentityCue)
                     )
                     (IsSupportedUnfalsifiedCue ?uniqueIdentityCue)
                 )
             )

          D8 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
                 (if
                     (and
                         (IsMoreEmbodiedThan ?embodiment1 ?embodiment2)
                         (PresentedUniqueIdentityCueInContext ?embodiment1 ?uniqueIdentityCue ?context1)
                         (PresentingUniqueIdentityCueInContext ?embodiment2 ?uniqueIdentityCue ?context2)
                         (IsSupportedUnfalsifiedCue ?uniqueIdentityCue)
                         (AreSimilarContext ?context1 ?context2)
                     )
                     (UnfalsifiedIdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
                 )
             )

          D9 (forall [?embodiment1 ?embodiment2 ?embodiment3]
                 (if
                     (and
                         (UnfalsifiedIdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?cue1)
                         (UnfalsifiedIdentityMaintainedWithCue ?embodiment2 ?embodiment3 ?cue2)
                     )
                     (UnfalsifiedIdentityMaintainedWithCue ?embodiment1 ?embodiment3 ?cue2)
                 )
             )

          A0 (and (IsUniqueIdentityCue friendly) (HasCueTyping friendly behavioralInteractionStrategy ) )

          A1 (HoldsAt (PresentingUniqueIdentityCue (embodiment a) friendly) t1)

          A2 (HoldsAt (PresentingUniqueIdentityCue (embodiment b) friendly) t2)

    }

 :answer-variables [?x ?y ?z]

 :answers-expected ( )

 :goal (IdentityMaintainedWithCue ?x ?y ?z) ;;at t4
}

{:name A_User_Study_on_Visualization_of_Agent_Migration_between_Two_Companion_Robots
 :description "This formalism is for the idea of teleportation cues, specifically migrating faces. May not be significant."
 :assumptions
    { D1 (and
              (IsUniqueIdentityCue blackAndYellow)
              (IsMigrationCue movingAvatar)
              (HasCueTyping movingAvatar visualMovingFace)

              (IsSupportedIdentityCueTyping visualColorScheme)
              (IsSupportedIdentityCueTyping visualDistinguishingMarkings)
              (IsSupportedIdentityCueTyping visualClass)
              (IsSupportedIdentityCueTyping visualFeatures)
              (IsSupportedIdentityCueTyping behavioralDisplayedMood)
              (IsSupportedIdentityCueTyping behavioralInteractionStrategy)
              (IsSupportedIdentityCueTyping auditoryVoiceCue)
              (IsSupportedIdentityCueTyping auditoryDopplerCue)
              (IsSupportedIdentityCueTyping behavioralPersonalityIndicatingAction)
              (IsSupportedIdentityCueTyping behavioralTaskCompletionStrategy)
              (IsSupportedIdentityCueTyping behavioralTaskContinuation)

              (IsSupportedMigrationCueTyping visualMovingBar)
              (IsSupportedMigrationCueTyping visualMovingFace)
              (IsSupportedMigrationCueTyping visualFlashingLights)
              (IsSupportedMigrationCueTyping behavioralExpressiveActivityStateChange)
              (IsSupportedMigrationCueTyping behavioralPromptHuman)

              (ArePairedMigrationSignals visualMovingBar barDraining barFilling)
              (ArePairedMigrationSignals visualMovingFace faceDeparture faceArrival)
              (ArePairedMigrationSignals visualFlashingLights lightAccelerate lightDecelerate)
              (ArePairedMigrationSignals behavioralExpressiveActivityStateChange expressiveDeactivation expressiveActivation)
              (forall [?migrationCue] (if (IsSupportedCue ?migrationCue) (ArePairedMigrationSignals behavioralPromptHuman humanResponse ?migrationCue)))

              (IsSupportedUnfalsifiedIdentityCueTyping behavioralPersonalityIndicatingAction)
              (IsSupportedUnfalsifiedIdentityCueTyping behavioralSpecificQuirks)
              (IsSupportedUnfalsifiedIdentityCueTyping behavioralTaskCompletionStrategy)

              )

       D2 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
              (if
                  (and
                      (PresentedUniqueIdentityCue ?embodiment1 ?uniqueIdentityCue)
                      (PresentingUniqueIdentityCue ?embodiment2 ?uniqueIdentityCue)
                      (IsSupportedCue ?uniqueIdentityCue)
                  )
                  (IdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
              )
          )

       D3 (forall [?uniqueIdentityCue ?identityCueTyping]
              (if
                  (and
                      (HasCueTyping ?uniqueIdentityCue ?identityCueTyping)
                      (IsSupportedIdentityCueTyping ?identityCueTyping)
                      (IsUniqueIdentityCue ?uniqueIdentityCue)
                  )
                  (IsSupportedCue ?uniqueIdentityCue)
              )
          )

       D4 (forall [?embodiment1 ?embodiment2 ?migrationCue]
              (if
                  (and
                      (PresentingMigrationCue ?embodiment1 ?departureCue)
                      (PresentingMigrationCue ?embodiment2 ?arrivalCue)
                      (ArePairedMigrationSignals ?migrationCueTyping ?departureCue ?arrivalCue)
                      (HasCueTyping ?migrationCue ?migrationCueTyping)
                      (IsSupportedCue ?migrationCue)
                  )
                  (MigrationRealizedWithCue ?embodiment1 ?embodiment2 ?migrationCue)
              )
          )

       D5 (forall [?migrationCue]
              (if
                  (and
                      (HasCueTyping ?migrationCue ?migrationCueTyping)
                      (IsSupportedMigrationCueTyping ?migrationCueTyping)
                      (IsMigrationCue ?migrationCue)
                  )
                  (IsSupportedCue ?migrationCue)
              )
          )

       D6 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
              (if
                  (and
                      (PresentedUniqueIdentityCueInContext ?embodiment1 ?uniqueIdentityCue ?context1)
                      (PresentingUniqueIdentityCueInContext ?embodiment2 ?uniqueIdentityCue ?context2)
                      (AreSimilarContext ?context1 ?context2)
                      (IsSupportedCue ?uniqueIdentityCue)
                  )
                  (IdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
              )
          )

       D7 (forall [?uniqueIdentityCue ?identityCueTyping]
              (if
                  (and
                      (HasCueTyping ?uniqueIdentityCue ?identityCueTyping)
                      (IsSupportedUnfalsifiedIdentityCueTyping ?identityCueTyping)
                      (IsUniqueIdentityCue ?uniqueIdentityCue)
                  )
                  (IsSupportedUnfalsifiedCue ?uniqueIdentityCue)
              )
          )

       D8 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
              (if
                  (and
                      (IsMoreEmbodiedThan ?embodiment1 ?embodiment2)
                      (PresentedUniqueIdentityCueInContext ?embodiment1 ?uniqueIdentityCue ?context1)
                      (PresentingUniqueIdentityCueInContext ?embodiment2 ?uniqueIdentityCue ?context2)
                      (IsSupportedUnfalsifiedCue ?uniqueIdentityCue)
                      (AreSimilarContext ?context1 ?context2)
                  )
                  (UnfalsifiedIdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
              )
          )

       D9 (forall [?embodiment1 ?embodiment2 ?embodiment3]
              (if
                  (and
                      (UnfalsifiedIdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?cue1)
                      (UnfalsifiedIdentityMaintainedWithCue ?embodiment2 ?embodiment3 ?cue2)
                  )
                  (UnfalsifiedIdentityMaintainedWithCue ?embodiment1 ?embodiment3 ?cue2)
              )
          )

       A1 (HoldsAt (PresentingMigrationCue (embodiment a) faceDeparture) t2)

       A2 (HoldsAt (PresentingMigrationCue (embodiment b) faceArrival) t2)
    }

 :answer-variables [?x ?y ?z]

 :answers-expected ( )

 :goal  (MigrationRealizedWithCue ?x ?y ?z)
}

{:name Identity_of_socially_interactive_robotic_twins_initial_results_of_VHRI_study
 :description "This formalism test is for the idea of using personality as a distinguishing identifying cue between several identical looking embodiments"
 :assumptions
    {D1 (and
                   (IsUniqueIdentityCue blackAndYellow)

                   (IsMigrationCue movingAvatar)
                   (HasCueTyping movingAvatar visualMovingFace)

                   (IsUniqueIdentityCue rapidMovements)
                   (HasCueTyping rapidMovements behavioralPersonalityIndicatingAction)
                   (IsUniqueIdentityCue slowMovements)
                   (HasCueTyping slowMovements behavioralPersonalityIndicatingAction)
                   (not (IsUniqueIdentityCue sputnikForm))
                   (HasCueTyping embodimentAppearance visualFeatures)

                   (IsSupportedIdentityCueTyping visualColorScheme)
                   (IsSupportedIdentityCueTyping visualDistinguishingMarkings)
                   (IsSupportedIdentityCueTyping visualClass)
                   (IsSupportedIdentityCueTyping visualFeatures)
                   (IsSupportedIdentityCueTyping behavioralDisplayedMood)
                   (IsSupportedIdentityCueTyping behavioralInteractionStrategy)
                   (IsSupportedIdentityCueTyping auditoryVoiceCue)
                   (IsSupportedIdentityCueTyping auditoryDopplerCue)
                   (IsSupportedIdentityCueTyping behavioralPersonalityIndicatingAction)
                   (IsSupportedIdentityCueTyping behavioralTaskCompletionStrategy)
                   (IsSupportedIdentityCueTyping behavioralTaskContinuation)

                   (IsSupportedMigrationCueTyping visualMovingBar)
                   (IsSupportedMigrationCueTyping visualMovingFace)
                   (IsSupportedMigrationCueTyping visualFlashingLights)
                   (IsSupportedMigrationCueTyping behavioralExpressiveActivityStateChange)
                   (IsSupportedMigrationCueTyping behavioralPromptHuman)

                   (ArePairedMigrationSignals visualMovingBar barDraining barFilling)
                   (ArePairedMigrationSignals visualMovingFace faceDeparture faceArrival)
                   (ArePairedMigrationSignals visualFlashingLights lightAccelerate lightDecelerate)
                   (ArePairedMigrationSignals behavioralExpressiveActivityStateChange expressiveDeactivation expressiveActivation)
                   (forall [?migrationCue] (if (IsSupportedCue ?migrationCue) (ArePairedMigrationSignals behavioralPromptHuman humanResponse ?migrationCue)))

                   (IsSupportedUnfalsifiedIdentityCueTyping behavioralPersonalityIndicatingAction)
                   (IsSupportedUnfalsifiedIdentityCueTyping behavioralSpecificQuirks)
                   (IsSupportedUnfalsifiedIdentityCueTyping behavioralTaskCompletionStrategy)

                   )

    D2 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
           (if
               (and
                   (PresentedUniqueIdentityCue ?embodiment1 ?uniqueIdentityCue)
                   (PresentingUniqueIdentityCue ?embodiment2 ?uniqueIdentityCue)
                   (IsSupportedCue ?uniqueIdentityCue)
               )
               (IdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
           )
       )

    D3 (forall [?uniqueIdentityCue ?identityCueTyping]
           (if
               (and
                   (HasCueTyping ?uniqueIdentityCue ?identityCueTyping)
                   (IsSupportedIdentityCueTyping ?identityCueTyping)
                   (IsUniqueIdentityCue ?uniqueIdentityCue)
               )
               (IsSupportedCue ?uniqueIdentityCue)
           )
       )

    D4 (forall [?embodiment1 ?embodiment2 ?migrationCue]
           (if
               (and
                   (PresentingMigrationCue ?embodiment1 ?departureCue)
                   (PresentingMigrationCue ?embodiment2 ?arrivalCue)
                   (ArePairedMigrationSignals ?migrationCueTyping ?departureCue ?arrivalCue)
                   (HasCueTyping ?migrationCue ?migrationCueTyping)
                   (IsSupportedCue ?migrationCue)
               )
               (MigrationRealizedWithCue ?embodiment1 ?embodiment2 ?migrationCue)
           )
       )

    D5 (forall [?migrationCue]
           (if
               (and
                   (HasCueTyping ?migrationCue ?migrationCueTyping)
                   (IsSupportedMigrationCueTyping ?migrationCueTyping)
                   (IsMigrationCue ?migrationCue)
               )
               (IsSupportedCue ?migrationCue)
           )
       )

    D6 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
           (if
               (and
                   (PresentedUniqueIdentityCueInContext ?embodiment1 ?uniqueIdentityCue ?context1)
                   (PresentingUniqueIdentityCueInContext ?embodiment2 ?uniqueIdentityCue ?context2)
                   (AreSimilarContext ?context1 ?context2)
                   (IsSupportedCue ?uniqueIdentityCue)
               )
               (IdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
           )
       )

    D7 (forall [?uniqueIdentityCue ?identityCueTyping]
           (if
               (and
                   (HasCueTyping ?uniqueIdentityCue ?identityCueTyping)
                   (IsSupportedUnfalsifiedIdentityCueTyping ?identityCueTyping)
                   (IsUniqueIdentityCue ?uniqueIdentityCue)
               )
               (IsSupportedUnfalsifiedCue ?uniqueIdentityCue)
           )
       )

    D8 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
           (if
               (and
                   (IsMoreEmbodiedThan ?embodiment1 ?embodiment2)
                   (PresentedUniqueIdentityCueInContext ?embodiment1 ?uniqueIdentityCue ?context1)
                   (PresentingUniqueIdentityCueInContext ?embodiment2 ?uniqueIdentityCue ?context2)
                   (IsSupportedUnfalsifiedCue ?uniqueIdentityCue)
                   (AreSimilarContext ?context1 ?context2)
               )
               (UnfalsifiedIdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
           )
       )

    D9 (forall [?embodiment1 ?embodiment2 ?embodiment3]
           (if
               (and
                   (UnfalsifiedIdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?cue1)
                   (UnfalsifiedIdentityMaintainedWithCue ?embodiment2 ?embodiment3 ?cue2)
               )
               (UnfalsifiedIdentityMaintainedWithCue ?embodiment1 ?embodiment3 ?cue2)
           )
       )


     A1 (HoldsAt (PresentingUniqueIdentityCue (embodiment a) sputnikForm)  t1) ;;at t1
     A2 (HoldsAt  (PresentingUniqueIdentityCue (embodiment a) rapidMovements) t1) ;;at t1

     A3 (HoldsAt (PresentingUniqueIdentityCue (embodiment b) sputnikForm) t2) ;;at t2
     A4 (HoldsAt (PresentingUniqueIdentityCue (embodiment b) rapidMovements) t2) ;;at t2

     A5 (HoldsAt (PresentingUniqueIdentityCue (embodiment c) sputnikForm) t3) ;;at t3
     A6 (HoldsAt (PresentingUniqueIdentityCue (embodiment c) slowMovements) t3) ;;at t3

    }

 :answer-variables [?x ?y ?z]

 :answers-expected ( )

 :goal (IdentityMaintainedWithCue ?x ?y ?z) ;;at t4

}

{:name Identity_of_a_companion_migrating_between_robots_significantly_different_in_terms_of_expressive_capabilities_initial_results_of_VHRI_study
 :description "This formalism test is for the idea of using personality as a distinguishing identifying cue between environments of decreasing levels of embodiment"
 :assumptions
    {D1 (and
                   (IsUniqueIdentityCue blackAndYellow)

                   (IsMigrationCue movingAvatar)
                   (HasCueTyping movingAvatar visualMovingFace)

                   (IsUniqueIdentityCue fearfulMovements)
                   (HasCueTyping fearfulMovements behavioralPersonalityIndicatingAction)
                   (IsUniqueIdentityCue slowMovements)
                   (HasCueTyping slowMovements behavioralPersonalityIndicatingAction)
                   (IsUniqueIdentityCue sputnikForm)
                   (HasCueTyping embodimentAppearance visualFeatures)

                   (IsSupportedIdentityCueTyping visualColorScheme)
                   (IsSupportedIdentityCueTyping visualDistinguishingMarkings)
                   (IsSupportedIdentityCueTyping visualClass)
                   (IsSupportedIdentityCueTyping visualFeatures)
                   (IsSupportedIdentityCueTyping behavioralDisplayedMood)
                   (IsSupportedIdentityCueTyping behavioralInteractionStrategy)
                   (IsSupportedIdentityCueTyping auditoryVoiceCue)
                   (IsSupportedIdentityCueTyping auditoryDopplerCue)
                   (IsSupportedIdentityCueTyping behavioralPersonalityIndicatingAction)
                   (IsSupportedIdentityCueTyping behavioralTaskCompletionStrategy)
                   (IsSupportedIdentityCueTyping behavioralTaskContinuation)

                   (IsSupportedMigrationCueTyping visualMovingBar)
                   (IsSupportedMigrationCueTyping visualMovingFace)
                   (IsSupportedMigrationCueTyping visualFlashingLights)
                   (IsSupportedMigrationCueTyping behavioralExpressiveActivityStateChange)
                   (IsSupportedMigrationCueTyping behavioralPromptHuman)

                   (ArePairedMigrationSignals visualMovingBar barDraining barFilling)
                   (ArePairedMigrationSignals visualMovingFace faceDeparture faceArrival)
                   (ArePairedMigrationSignals visualFlashingLights lightAccelerate lightDecelerate)
                   (ArePairedMigrationSignals behavioralExpressiveActivityStateChange expressiveDeactivation expressiveActivation)
                   (forall [?migrationCue] (if (IsSupportedCue ?migrationCue) (ArePairedMigrationSignals behavioralPromptHuman humanResponse ?migrationCue)))

                   (IsSupportedUnfalsifiedIdentityCueTyping behavioralPersonalityIndicatingAction)
                   (IsSupportedUnfalsifiedIdentityCueTyping behavioralSpecificQuirks)
                   (IsSupportedUnfalsifiedIdentityCueTyping behavioralTaskCompletionStrategy)

                   )

    D2 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
           (if
               (and
                   (PresentedUniqueIdentityCue ?embodiment1 ?uniqueIdentityCue)
                   (PresentingUniqueIdentityCue ?embodiment2 ?uniqueIdentityCue)
                   (IsSupportedCue ?uniqueIdentityCue)
               )
               (IdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
           )
       )

    D3 (forall [?uniqueIdentityCue ?identityCueTyping]
           (if
               (and
                   (HasCueTyping ?uniqueIdentityCue ?identityCueTyping)
                   (IsSupportedIdentityCueTyping ?identityCueTyping)
                   (IsUniqueIdentityCue ?uniqueIdentityCue)
               )
               (IsSupportedCue ?uniqueIdentityCue)
           )
       )

    D4 (forall [?embodiment1 ?embodiment2 ?migrationCue]
           (if
               (and
                   (PresentingMigrationCue ?embodiment1 ?departureCue)
                   (PresentingMigrationCue ?embodiment2 ?arrivalCue)
                   (ArePairedMigrationSignals ?migrationCueTyping ?departureCue ?arrivalCue)
                   (HasCueTyping ?migrationCue ?migrationCueTyping)
                   (IsSupportedCue ?migrationCue)
               )
               (MigrationRealizedWithCue ?embodiment1 ?embodiment2 ?migrationCue)
           )
       )

    D5 (forall [?migrationCue]
           (if
               (and
                   (HasCueTyping ?migrationCue ?migrationCueTyping)
                   (IsSupportedMigrationCueTyping ?migrationCueTyping)
                   (IsMigrationCue ?migrationCue)
               )
               (IsSupportedCue ?migrationCue)
           )
       )

    D6 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
           (if
               (and
                   (PresentedUniqueIdentityCueInContext ?embodiment1 ?uniqueIdentityCue ?context1)
                   (PresentingUniqueIdentityCueInContext ?embodiment2 ?uniqueIdentityCue ?context2)
                   (AreSimilarContext ?context1 ?context2)
                   (IsSupportedCue ?uniqueIdentityCue)
               )
               (IdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
           )
       )

    D7 (forall [?uniqueIdentityCue ?identityCueTyping]
           (if
               (and
                   (HasCueTyping ?uniqueIdentityCue ?identityCueTyping)
                   (IsSupportedUnfalsifiedIdentityCueTyping ?identityCueTyping)
                   (IsUniqueIdentityCue ?uniqueIdentityCue)
               )
               (IsSupportedUnfalsifiedCue ?uniqueIdentityCue)
           )
       )

    D8 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
           (if
               (and
                   (IsMoreEmbodiedThan ?embodiment1 ?embodiment2)
                   (PresentedUniqueIdentityCueInContext ?embodiment1 ?uniqueIdentityCue ?context1)
                   (PresentingUniqueIdentityCueInContext ?embodiment2 ?uniqueIdentityCue ?context2)
                   (IsSupportedUnfalsifiedCue ?uniqueIdentityCue)
                   (AreSimilarContext ?context1 ?context2)
               )
               (UnfalsifiedIdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
           )
       )

    D9 (forall [?embodiment1 ?embodiment2 ?embodiment3]
           (if
               (and
                   (UnfalsifiedIdentityMaintainedWithCue ?embodiment1 ?embodiment2 ?cue1)
                   (UnfalsifiedIdentityMaintainedWithCue ?embodiment2 ?embodiment3 ?cue2)
               )
               (UnfalsifiedIdentityMaintainedWithCue ?embodiment1 ?embodiment3 ?cue2)
           )
       )


     A1 (HoldsAt (PresentingUniqueIdentityCue (embodiment a) sputnikForm) t1) ;;at t1
     A2 (HoldsAt (PresentingUniqueIdentityCueInContext (embodiment a) fearfulMovements enteringUnknownRoom) t1) ;;at t1

     A3 (HoldsAt (PresentingUniqueIdentityCue (embodiment b) pioneerForm) t2) ;;at t2
     A4 (HoldsAt (PresentingUniqueIdentityCueInContext (embodiment b) fearfulMovements enteringUnseenRoom) t2) ;;at t2
     A5 (HoldsAt (AreSimilarContext enteringUnknownRoom enteringUnseenRoom) t2)
     A6 (HoldsAt (IsMoreEmbodiedThan (embodiment a) (embodiment b)) t2)


    }

 :answer-variables [?x ?y ?z]

 :answers-expected ( )

 :goal (UnfalsifiedIdentityMaintainedWithCue ?x ?y ?z) ;;at t4

}

{:name Identity_of_a_companion_migrating_between_robots_without_common_communication_modalities_initial_results_of_VHRI_study
 :description ""
 :assumptions
    {D1  (and
                   (IsUniqueIdentityCue blackAndYellow)

                   (IsMigrationCue movingAvatar)
                   (HasCueTyping movingAvatar visualMovingFace)

                   (IsUniqueIdentityCue penetratingGaze)
                   (HasCueTyping penetratingGaze behavioralPersonalityIndicatingAction)
                   (IsUniqueIdentityCue slowMovements)
                   (HasCueTyping slowMovements behavioralPersonalityIndicatingAction)
                   (IsUniqueIdentityCue wideArcingRoutes)
                   (HasCueTyping wideArcingRoutes behavioralTaskCompletionStrategy)
                   (IsUniqueIdentityCue sputnikForm)
                   (HasCueTyping embodimentAppearance visualFeatures)
                   (IsUniqueIdentityCue TTSVoiceCarol)
                   (HasCueTyping TTSVoiceCarol auditoryVoiceCue)

                   (IsSupportedIdentityCueTyping visualColorScheme)
                   (IsSupportedIdentityCueTyping visualDistinguishingMarkings)
                   (IsSupportedIdentityCueTyping visualClass)
                   (IsSupportedIdentityCueTyping visualFeatures)
                   (IsSupportedIdentityCueTyping behavioralDisplayedMood)
                   (IsSupportedIdentityCueTyping behavioralInteractionStrategy)
                   (IsSupportedIdentityCueTyping auditoryVoiceCue)
                   (IsSupportedIdentityCueTyping auditoryDopplerCue)
                   (IsSupportedIdentityCueTyping behavioralPersonalityIndicatingAction)
                   (IsSupportedIdentityCueTyping behavioralTaskCompletionStrategy)
                   (IsSupportedIdentityCueTyping behavioralTaskContinuation)

                   (IsSupportedMigrationCueTyping visualMovingBar)
                   (IsSupportedMigrationCueTyping visualMovingFace)
                   (IsSupportedMigrationCueTyping visualFlashingLights)
                   (IsSupportedMigrationCueTyping behavioralExpressiveActivityStateChange)
                   (IsSupportedMigrationCueTyping behavioralPromptHuman)

                   (ArePairedMigrationSignals visualMovingBar barDraining barFilling)
                   (ArePairedMigrationSignals visualMovingFace faceDeparture faceArrival)
                   (ArePairedMigrationSignals visualFlashingLights lightAccelerate lightDecelerate)
                   (ArePairedMigrationSignals behavioralExpressiveActivityStateChange expressiveDeactivation expressiveActivation)
                   (forall [?migrationCue] (if (IsSupportedCue ?migrationCue) (ArePairedMigrationSignals behavioralPromptHuman humanResponse ?migrationCue)))

                   (IsSupportedUnfalsifiedIdentityCueTyping behavioralPersonalityIndicatingAction)
                   (IsSupportedUnfalsifiedIdentityCueTyping behavioralSpecificQuirks)
                   (IsSupportedUnfalsifiedIdentityCueTyping behavioralTaskCompletionStrategy)

                   )

    D2 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
           (if
               (and
                   (PresentedUniqueIdentityCue ?embodiment1 ?uniqueIdentityCue)
                   (PresentingUniqueIdentityCue ?embodiment2 ?uniqueIdentityCue)
                   (IsSupportedCue ?uniqueIdentityCue)
               )
               (MaintainingIdentityWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
           )
       )

    D3 (forall [?uniqueIdentityCue ?identityCueTyping]
           (if
               (and
                   (HasCueTyping ?uniqueIdentityCue ?identityCueTyping)
                   (IsSupportedIdentityCueTyping ?identityCueTyping)
                   (IsUniqueIdentityCue ?uniqueIdentityCue)
               )
               (IsSupportedCue ?uniqueIdentityCue)
           )
       )

    D4 (forall [?embodiment1 ?embodiment2 ?migrationCue]
           (if
               (and
                   (PresentingMigrationCue ?embodiment1 ?departureCue)
                   (PresentingMigrationCue ?embodiment2 ?arrivalCue)
                   (ArePairedMigrationSignals ?migrationCueTyping ?departureCue ?arrivalCue)
                   (HasCueTyping ?migrationCue ?migrationCueTyping)
                   (IsSupportedCue ?migrationCue)
               )
               (SignalingMigrationWithCue ?embodiment1 ?embodiment2 ?migrationCue)
           )
       )

    D5 (forall [?migrationCue]
           (if
               (and
                   (HasCueTyping ?migrationCue ?migrationCueTyping)
                   (IsSupportedMigrationCueTyping ?migrationCueTyping)
                   (IsMigrationCue ?migrationCue)
               )
               (IsSupportedCue ?migrationCue)
           )
       )

    D6 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
           (if
               (and
                   (PresentedUniqueIdentityCueInContext ?embodiment1 ?uniqueIdentityCue ?context1)
                   (PresentingUniqueIdentityCueInContext ?embodiment2 ?uniqueIdentityCue ?context2)
                   (AreSimilarContext ?context1 ?context2)
                   (IsSupportedCue ?uniqueIdentityCue)
               )
               (MaintainingIdentityWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
           )
       )

    D7 (forall [?uniqueIdentityCue ?identityCueTyping]
           (if
               (and
                   (HasCueTyping ?uniqueIdentityCue ?identityCueTyping)
                   (IsSupportedUnfalsifiedIdentityCueTyping ?identityCueTyping)
                   (IsUniqueIdentityCue ?uniqueIdentityCue)
               )
               (IsSupportedUnfalsifiedCue ?uniqueIdentityCue)
           )
       )

    D8 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
           (if
               (and
                   (IsMoreEmbodiedThan ?embodiment1 ?embodiment2)
                   (PresentedUniqueIdentityCueInContext ?embodiment1 ?uniqueIdentityCue ?context1)
                   (PresentingUniqueIdentityCueInContext ?embodiment2 ?uniqueIdentityCue ?context2)
                   (IsSupportedUnfalsifiedCue ?uniqueIdentityCue)
                   (AreSimilarContext ?context1 ?context2)
               )
               (MaintainingUnfalsifiedIdentityWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
           )
       )

    D9 (forall [?embodiment1 ?embodiment2 ?embodiment3]
           (if
               (and
                   (MaintainedUnfalsifiedIdentityWithCue ?embodiment1 ?embodiment2 ?cue1)
                   (MaintainingUnfalsifiedIdentityWithCue ?embodiment2 ?embodiment3 ?cue2)
               )
               (MaintainingUnfalsifiedIdentityWithCue ?embodiment1 ?embodiment3 ?cue2)
           )
       )

    A1 (HoldsAt (PresentingUniqueIdentityCueInContext (embodiment emysHead) penetratingGaze genericConversation) t1) ;;at t1
    A2 (HoldsAt (PresentingUniqueIdentityCue (embodiment emysHead) TTSVoiceCarol) t1) ;;at t1

    A3 (HoldsAt (PresentingUniqueIdentityCueInContext (embodiment pioneerRobot) wideArcingRoutes deliverBirthdayFlowers) t2) ;;at t2
    A4 (HoldsAt (IsMoreEmbodiedThan (embodiment emysHead) (embodiment pioneerRobot) ) t2) ;;at t2

    A5 (HoldsAt (PresentingUniqueIdentityCueInContext (embodiment emysHead) penetratingGaze genericConversation) t3) ;;at t3
    A6 (HoldsAt (PresentingUniqueIdentityCue (embodiment emysHead) TTSVoiceCarol) t3) ;;at t3
    A7 (HoldsAt (IsMoreEmbodiedThan (embodiment emysHead) (embodiment pioneerRobot) ) t3) ;;at t3

    A8 (HoldsAt (PresentingUniqueIdentityCueInContext (embodiment sputnikRobot) wideArcingRoutes deliverBirthdayCards) t4) ;;at t4
    A9 (HoldsAt (PresentingUniqueIdentityCueInContext (embodiment sputnikRobot) penetratingGaze deliverBirthdayCards) t4) ;;at t4
    A10 (HoldsAt (AreSimilarContext deliverBirthdayFlowers deliverBirthdayCards) t4) ;;at t4
    A11 (HoldsAt (AreSimilarContext genericConversation deliverBirthdayCards) t4) ;;at t4
    A12 (HoldsAt (IsMoreEmbodiedThan (embodiment emysHead) (embodiment pioneerRobot) ) t4) ;;at t4
    A13 (HoldsAt (IsMoreEmbodiedThan (embodiment sputnikRobot) (embodiment pioneerRobot) ) t4) ;;at t4
    A14 (HoldsAt (IsMoreEmbodiedThan (embodiment emysHead) (embodiment sputnikRobot) ) t4) ;;at t4

    A15 (HoldsAt (PresentingUniqueIdentityCueInContext (embodiment pioneerRobot) wideArcingRoutes deliverBirthdayFlowers) t5) ;;at t5
    A16 (HoldsAt (AreSimilarContext deliverBirthdayCards deliverBirthdayFlowers) t5) ;;at t5
    A17 (HoldsAt (AreSimilarContext genericConversation deliverBirthdayCards) t5) ;;at t5
    A18 (HoldsAt (IsMoreEmbodiedThan (embodiment emysHead) (embodiment pioneerRobot) ) t5) ;;at t5
    A19 (HoldsAt (IsMoreEmbodiedThan (embodiment sputnikRobot) (embodiment pioneerRobot) ) t5) ;;at t5
    A20 (HoldsAt (IsMoreEmbodiedThan (embodiment emysHead) (embodiment sputnikRobot) ) t5) ;;at t5


    }

 :answer-variables [?x ?y ?z]

 :answers-expected ( )

 :goal  (MaintainingUnfalsifiedIdentityWithCue ?x ?y ?z) ;;at t5
}

{:name Prototyping_Realistic_Long-term_Human-Robot_Interaction_for_the_Study_of_Agent_Migration
 :description "This formalism takes what is presented in this paper and interprets it as a migration cue"
 :assumptions
    { D1  (and
                         (IsUniqueIdentityCue blackAndYellow)

                         (IsMigrationCue promptingProcess)
                         (HasCueTyping promptingProcess behavioralPromptHuman)

                         (IsMigrationCue signalingProcess)
                         (HasCueTyping signalingProcess behavioralExpressiveActivityStateChange)

                         (IsUniqueIdentityCue penetratingGaze)
                         (HasCueTyping penetratingGaze behavioralPersonalityIndicatingAction)
                         (IsUniqueIdentityCue slowMovements)
                         (HasCueTyping slowMovements behavioralPersonalityIndicatingAction)
                         (IsUniqueIdentityCue wideArcingRoutes)
                         (HasCueTyping wideArcingRoutes behavioralTaskCompletionStrategy)
                         (IsUniqueIdentityCue sputnikForm)
                         (HasCueTyping embodimentAppearance visualFeatures)
                         (IsUniqueIdentityCue TTSVoiceCarol)
                         (HasCueTyping TTSVoiceCarol auditoryVoiceCue)

                         (IsSupportedIdentityCueTyping visualColorScheme)
                         (IsSupportedIdentityCueTyping visualDistinguishingMarkings)
                         (IsSupportedIdentityCueTyping visualClass)
                         (IsSupportedIdentityCueTyping visualFeatures)
                         (IsSupportedIdentityCueTyping behavioralDisplayedMood)
                         (IsSupportedIdentityCueTyping behavioralInteractionStrategy)
                         (IsSupportedIdentityCueTyping auditoryVoiceCue)
                         (IsSupportedIdentityCueTyping auditoryDopplerCue)
                         (IsSupportedIdentityCueTyping behavioralPersonalityIndicatingAction)
                         (IsSupportedIdentityCueTyping behavioralTaskCompletionStrategy)
                         (IsSupportedIdentityCueTyping behavioralTaskContinuation)

                         (IsSupportedMigrationCueTyping visualMovingBar)
                         (IsSupportedMigrationCueTyping visualMovingFace)
                         (IsSupportedMigrationCueTyping visualFlashingLights)
                         (IsSupportedMigrationCueTyping behavioralExpressiveActivityStateChange)
                         (IsSupportedMigrationCueTyping behavioralPromptHuman)

                         (ArePairedMigrationSignals visualMovingBar barDraining barFilling)
                         (ArePairedMigrationSignals visualMovingFace faceDeparture faceArrival)
                         (ArePairedMigrationSignals visualFlashingLights lightAccelerate lightDecelerate)
                         (ArePairedMigrationSignals behavioralExpressiveActivityStateChange expressiveDeactivation expressiveActivation)
                         (forall [?migrationCue] (if (IsSupportedCue ?migrationCue) (ArePairedMigrationSignals behavioralPromptHuman humanResponse ?migrationCue) ) )

                         (IsSupportedUnfalsifiedIdentityCueTyping behavioralPersonalityIndicatingAction)
                         (IsSupportedUnfalsifiedIdentityCueTyping behavioralSpecificQuirks)
                         (IsSupportedUnfalsifiedIdentityCueTyping behavioralTaskCompletionStrategy)

                         )

          D2 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
                 (if
                     (and
                         (PresentedUniqueIdentityCue ?embodiment1 ?uniqueIdentityCue)
                         (PresentingUniqueIdentityCue ?embodiment2 ?uniqueIdentityCue)
                         (IsSupportedCue ?uniqueIdentityCue)
                     )
                     (MaintainingIdentityWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
                 )
             )

          D3 (forall [?uniqueIdentityCue ?identityCueTyping]
                 (if
                     (and
                         (HasCueTyping ?uniqueIdentityCue ?identityCueTyping)
                         (IsSupportedIdentityCueTyping ?identityCueTyping)
                         (IsUniqueIdentityCue ?uniqueIdentityCue)
                     )
                     (IsSupportedCue ?uniqueIdentityCue)
                 )
             )

          D4 (forall [?embodiment1 ?embodiment2 ?migrationCue]
                 (if
                     (and
                         (PresentingMigrationCue ?embodiment1 ?departureCue)
                         (PresentingMigrationCue ?embodiment2 ?arrivalCue)
                         (ArePairedMigrationSignals ?migrationCueTyping ?departureCue ?arrivalCue)
                         (HasCueTyping ?migrationCue ?migrationCueTyping)
                         (IsSupportedCue ?migrationCue)
                     )
                     (SignalingMigrationWithCue ?embodiment1 ?embodiment2 ?migrationCue)
                 )
             )

          D5 (forall [?migrationCue]
                 (if
                     (and
                         (HasCueTyping ?migrationCue ?migrationCueTyping)
                         (IsSupportedMigrationCueTyping ?migrationCueTyping)
                         (IsMigrationCue ?migrationCue)
                     )
                     (IsSupportedCue ?migrationCue)
                 )
             )

          D6 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
                 (if
                     (and
                         (PresentedUniqueIdentityCueInContext ?embodiment1 ?uniqueIdentityCue ?context1)
                         (PresentingUniqueIdentityCueInContext ?embodiment2 ?uniqueIdentityCue ?context2)
                         (AreSimilarContext ?context1 ?context2)
                         (IsSupportedCue ?uniqueIdentityCue)
                     )
                     (MaintainingIdentityWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
                 )
             )

          D7 (forall [?uniqueIdentityCue ?identityCueTyping]
                 (if
                     (and
                         (HasCueTyping ?uniqueIdentityCue ?identityCueTyping)
                         (IsSupportedUnfalsifiedIdentityCueTyping ?identityCueTyping)
                         (IsUniqueIdentityCue ?uniqueIdentityCue)
                     )
                     (IsSupportedUnfalsifiedCue ?uniqueIdentityCue)
                 )
             )

          D8 (forall [?embodiment1 ?embodiment2 ?uniqueIdentityCue]
                 (if
                     (and
                         (IsMoreEmbodiedThan ?embodiment1 ?embodiment2)
                         (PresentedUniqueIdentityCueInContext ?embodiment1 ?uniqueIdentityCue ?context1)
                         (PresentingUniqueIdentityCueInContext ?embodiment2 ?uniqueIdentityCue ?context2)
                         (IsSupportedUnfalsifiedCue ?uniqueIdentityCue)
                         (AreSimilarContext ?context1 ?context2)
                     )
                     (MaintainingUnfalsifiedIdentityWithCue ?embodiment1 ?embodiment2 ?uniqueIdentityCue)
                 )
             )

          D9 (forall [?embodiment1 ?embodiment2 ?embodiment3]
                 (if
                     (and
                         (MaintainedUnfalsifiedIdentityWithCue ?embodiment1 ?embodiment2 ?cue1)
                         (MaintainingUnfalsifiedIdentityWithCue ?embodiment2 ?embodiment3 ?cue2)
                     )
                     (MaintainingUnfalsifiedIdentityWithCue ?embodiment1 ?embodiment3 ?cue2)
                 )
             )

          A1 (HoldsAt (PresentingMigrationCue (embodiment a) humanResponse) t3)

          A2 (HoldsAt (PresentingMigrationCue (embodiment b) signalingProcess) t3)

          A3 (HoldsAt (PresentingMigrationCue (embodiment a) expressiveDeactivation) t3)

          A4 (HoldsAt (PresentingMigrationCue (embodiment b) expressiveActivation) t3)
    }

 :answer-variables [?x ?y ?z]

 :answers-expected ( )

 :goal  (SignalingMigrationWithCue ?x ?y ?z) ;;at t4
}

{:name The_Unified_Theory_Of_Teleportation
 :description ""
 :assumptions
    {B1 (HasTrait (embodiment a) wagglesEyebrows) ;;at t1
     A1 (HasTrait (embodiment a) TTSVoiceCarol) ;;at t1
     A2 (HasTrait (embodiment b) TTSVoiceCarol) ;;at t2
     B2 (HasTrait (embodiment b) AngularRoutes) ;;at t4
     B3 (HasTrait (embodiment c) AngularRoutes) ;;at t3

     A3 (IsUniqueIdentifyingTrait TTSVoiceCarol)
     B4 (IsUniqueIdentifyingTrait AngularRoutes)

     A4 (and
          (IsTeleportationCue movingFace)
          (IsTeleCueInit movingFaceInit)
          (IsTeleCueFinish movingFaceFinish)
          (TeleportationCueHalves movingFace movingFaceInit movingFaceFinish)
          ;;(IsTeleportationCue movingBar)

          (IsTraitType color)
          (IsTraitType class)
          (IsTraitType features)
          (IsTraitType markings)
          (IsTraitType voice)
          (IsTraitType personality)

          (IsTrait friendly)
          (IsMood pleasant)
          (IsInteractionStrategy motivating)
          (PersonalityComponentsAre friendly pleasant motivating)
        )

     A5 (IsPresentingTeleCue (embodiment a) movingFaceInit)
     A6 (IsPresentingTeleCue (embodiment b) movingFaceFinish)

     C1 (forall [?embodiment1 ?embodiment2 ?trait]
            (if
                (and
                    (IsUniqueIdentifyingTrait ?trait)
                    (HasTrait ?embodiment1 ?trait)
                    (HasTrait ?embodiment2 ?trait)
                )

                (IdentityRetention ?embodiment1 ?embodiment2 )

            )
        )

    C2 (forall [?embodiment1 ?embodiment2 ?teleportationCue ?teleCueInit ?teleCueFinish]
          (if
              (and
                  (IsTeleportationCue ?teleportationCue)
                  (TeleportationCueHalves ?teleportationCue ?teleCueInit ?teleCueFinish)
                  (IsPresentingTeleCue ?embodiment1 ?teleCueInit)
                  (IsPresentingTeleCue ?embodiment2 ?teleCueFinish)
              )

              (TeleportationRealization ?embodiment1 ?embodiment2)
          )
       )

    C3  (forall [?embodiment1 ?embodiment2 ?embodiment3]
             (if
                 (and
                     (IdentityRetention ?embodiment1 ?embodiment2 )
                     (IdentityRetention ?embodiment2 ?embodiment3 )
                 )

                 (IdentityRetention ?embodiment1 ?embodiment3 )
             )
        )

    }

 :answer-variables [?x ?y]

 :answers-expected ( )

 :goal  (IdentityRetention ?x ?y) ;;at t4
}

{:name The_Unified_Theory_Of_Identity_Teleportation_SEC_Version
 :description ""
 :assumptions
    {SC1 (forall [?f ?t]
                 (implies (and (InitiallyP ?f) (not (Clipped t0 ?f ?t)))
                          (HoldsAt ?f ?t)))

     SC2 (forall [?t1 ?t2 ?e ?f]
                 (implies (and (Happens ?e ?t1)
                               (Initiates ?e ?f ?t1)
                               (Prior ?t1 ?t2)
                               (not (Clipped ?t1 ?f ?t2)))
                          (HoldsAt ?f ?t2)))

     SC3 (forall [?t1 ?f ?t2]
                 (iff (Clipped ?t1 ?f ?t2)
                      (exists [?e ?t]
                              (and (Happens ?e ?t)
                                   (Prior ?t1 ?t)
                                   (Prior ?t ?t2)
                                   (Terminates ?e ?f ?t)))))

     SC4 (and
            (Prior t0 t1)
            (Prior t1 t2)
            (Prior t2 t3)
            (Prior t3 t4)
            (Prior t4 t5)
            (Prior t5 tLast)
            (forall [?time1 ?time2 ?time3]
                (implies
                    (and
                        (Prior ?time1 ?time2)
                        (Prior ?time2 ?time3)
                    )
                    (Prior ?time1 ?time3)
                )
            )
         )

     SC5 (forall [?f ?t1 ?t2]
            (implies
                (and
                    (HoldsAt ?f ?t1)
                    (Prior ?t1 ?t2)
                    (not (Clipped ?t1 ?f ?t2))
                )
                (HoldsAt ?f ?t2)
            )
         )

     SC6 (forall [?t0 ?t1 ?f ?t2]
            (implies
                (and
                    (not (Clipped ?t0 ?f ?t2))
                    (Prior ?t0 ?t1)
                    (Prior ?t1 ?t2)
                )
                (and
                    (not (Clipped ?t0 ?f ?t1))
                    (not (Clipped ?t1 ?f ?t2))
                )
            )
         )

     SC7 (forall [?t1 ?f ?t2]
            (implies
                (HoldsAt ?f ?t1 ?t2)
                (and
                    (HoldsAt ?f ?t1)
                    (not (Clipped ?t1 ?f ?t2))
                )
            )
         )

     B1 (HoldsAt (HasTrait (embodiment a) wagglesEyebrows) t1) ;;at t1
     A1 (HoldsAt (HasTrait (embodiment a) TTSVoiceCarol) t1) ;;at t1
     A2 (HoldsAt (HasTrait (embodiment b) TTSVoiceCarol) t2) ;;at t2
     B2 (HoldsAt (HasTrait (embodiment b) AngularRoutes) t2) ;;at t2
     B3 (HoldsAt (HasTrait (embodiment c) AngularRoutes) t3) ;;at t3

     A3 (HoldsAt (IsUniqueIdentifyingTrait TTSVoiceCarol) t0 t3)
     ;;AC3 (not (Clipped t0 (IsUniqueIdentifyingTrait TTSVoiceCarol) t3))
     B4 (HoldsAt (IsUniqueIdentifyingTrait AngularRoutes) t0 t3)
     ;;AC4 (not (Clipped t0 (IsUniqueIdentifyingTrait AngularRoutes) t3))
     T1 (not (Clipped t3 (IdentityRetention (embodiment a) (embodiment c)) t5) )
     T2 (not (Clipped t3 (IdentityRetention (embodiment a) (embodiment c)) t4) )

     A4 (and
          (HoldsAt (IsTeleportationCue movingFace) t1 tLast)
          (HoldsAt (IsTeleCueInit movingFaceInit) t1  tLast)
          (HoldsAt (IsTeleCueFinish movingFaceFinish) t1  tLast)
          (HoldsAt (TeleportationCueHalves movingFace movingFaceInit movingFaceFinish) t1  tLast)
          ;;(HoldsAt (IsTeleportationCue movingBar) t1)

          (HoldsAt (IsTraitType color) t1)
          (HoldsAt (IsTraitType class) t1)
          (HoldsAt (IsTraitType features) t1)
          (HoldsAt (IsTraitType markings) t1)
          (HoldsAt (IsTraitType voice) t1)
          (HoldsAt (IsTraitType personality) t1)

          (HoldsAt (IsTrait friendly) t1)
          (HoldsAt (IsMood pleasant) t1)
          (HoldsAt (IsInteractionStrategy motivating) t1)
          (HoldsAt (PersonalityComponentsAre friendly pleasant motivating) t1)
        )


     A5 (HoldsAt (IsPresentingTeleCue (embodiment a) movingFaceInit) t1)
     A6 (HoldsAt (IsPresentingTeleCue (embodiment b) movingFaceFinish) t1)

     C1 (forall [?embodiment1 ?embodiment2 ?trait ?time1 ?time2]
            (if
                (and
                    (HoldsAt (IsUniqueIdentifyingTrait ?trait) ?time1)
                    (HoldsAt (IsUniqueIdentifyingTrait ?trait) ?time2)
                    (HoldsAt (HasTrait ?embodiment1 ?trait) ?time1)
                    (HoldsAt (HasTrait ?embodiment2 ?trait) ?time2)
                    (Prior ?time1 ?time2)
                )

                (HoldsAt (IdentityRetention ?embodiment1 ?embodiment2) ?time2)

            )
        )

    C2 (forall [?embodiment1 ?embodiment2 ?teleportationCue ?teleCueInit ?teleCueFinish ?time]
          (if
              (and
                  (HoldsAt (IsTeleportationCue ?teleportationCue) ?time)
                  (HoldsAt (TeleportationCueHalves ?teleportationCue ?teleCueInit ?teleCueFinish) ?time)
                  (HoldsAt (IsPresentingTeleCue ?embodiment1 ?teleCueInit) ?time)
                  (HoldsAt (IsPresentingTeleCue ?embodiment2 ?teleCueFinish) ?time)
              )

              (HoldsAt (TeleportationRealization ?embodiment1 ?embodiment2) ?time)
          )
       )

    C3  (forall [?embodiment1 ?embodiment2 ?embodiment3 ?time1 ?time2]
             (if
                 (and
                     (HoldsAt (IdentityRetention ?embodiment1 ?embodiment2 ) ?time1)
                     (HoldsAt (IdentityRetention ?embodiment2 ?embodiment3 ) ?time2)
                     (Prior ?time1 ?time2)
                 )

                 (HoldsAt (IdentityRetention ?embodiment1 ?embodiment3 ) ?time2)
             )
        )

    }

 :answer-variables [?x ?y]

 :answers-expected ( )

 :goal  (HoldsAt (IdentityRetention ?x ?y) t3) ;;at t
}