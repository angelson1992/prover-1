{:name        "Simulation 1"
 :description "Simulation of a child observing an agent"
 :assumptions {
               A1 (Perceives! human t1 (Believes! (embodiment a) t1 (Holds (Prop watch stopped) t1)))
               A2 (Perceives! human t2 (Believes! (embodiment b) t2 (Holds (Prop watch stopped) t2)))

               A3 (Believes! human t3 (PersonalObject watch))
               A4 (Believes! human t4 (if
                                        (exists [?agent1 ?agent2 ?u ?prop ?time1 ?time2]
                                            (and
                                                (PersonalObject ?u)
                                                (Believes! ?agent1 ?time1 (Holds (Prop ?u ?prop) ?time1))
                                                (Believes! ?agent2 ?time2 (Holds (Prop ?u ?prop) ?time2))
                                            )
                                        )
                                        (= (identityOf ?agent1) (identityOf ?agent2))
                                      )
                  )

              }

 :goal        (Believes! human t5 (= (identityOf (embodiment a)) (identityOf (embodiment b))))}

{:name        "Simulation 2"
 :description "Simulation of multiple children observing an agent"
 :assumptions {
               A1 (Perceives! human1 t1 (Believes! (embodiment a) t1 (Holds (Prop game (Winner human1)) t1)))
               A5 (Perceives! human2 t1 (Believes! (embodiment a) t1 (Holds (Prop game (Winner human1)) t1)))

               A2 (Perceives! human1 t2 (Believes! (embodiment b) t2 (Holds (Prop game (Winner human1)) t2)))
               A6 (Perceives! human2 t2 (Believes! (embodiment b) t2 (Holds (Prop game (Winner human1)) t2)))

               A3 (Believes! human1 t3 (Experience game))
               A8 (Believes! human2 t3 (Experience game))

               A4 (Believes! human1 t4 (if
                                        (exists [?agent1 ?agent2 ?u ?prop ?time1 ?time2]
                                            (and
                                                (Experience ?u)
                                                (Believes! ?agent1 ?time1 (Holds (Prop ?u ?prop) ?time1))
                                                (Believes! ?agent2 ?time2 (Holds (Prop ?u ?prop) ?time2))
                                            )
                                        )
                                        (= (identityOf ?agent1) (identityOf ?agent2))
                                      )
                  )

               A7 (Believes! human2 t4 (if
                                        (exists [?agent1 ?agent2 ?u ?prop ?time1 ?time2]
                                            (and
                                                (Experience ?u)
                                                (Believes! ?agent1 ?time1 (Holds (Prop ?u ?prop) ?time1))
                                                (Believes! ?agent2 ?time2 (Holds (Prop ?u ?prop) ?time2))
                                            )
                                        )
                                        (= (identityOf ?agent1) (identityOf ?agent2))
                                       )
                 )

              }

 :goal        (and
                (Believes! human1 t5 (= (identityOf (embodiment a)) (identityOf (embodiment b))))
                (Believes! human2 t5 (= (identityOf (embodiment a)) (identityOf (embodiment b))))
              )}
