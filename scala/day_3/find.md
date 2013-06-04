#Day 3 - Scala

##Find Exercises

###For the sizer program, what would happen if you did not create a new actor for each link you wanted to follow?

The requests would queue up for the actor and would end up running sequentially.

###What would happen to the performance of the application?

The performance gains would not be realized, you might as well run it the old-fashioned way.