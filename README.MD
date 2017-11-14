
0. To run the project, use Maven commands:
- go into project directory
- run command "mvn test"

1.Project structure :

This project is the backend layer of TrainScheduler application.
There are several layers in this application :
  - Several objects which will allow us to store/consult different chunks of train information ("org.annainc.trainscheduler.domain" package)
  - Repository class (TrainDetailStore.java) which allows us to communicate with database
  - Controller class (TrainScheduler.java) which will allow us (for example) to have a link between Repository and front layer, it is not obvious right now but if we add booking ticket feature, our Controller will manage TrainTicket instances to inscrease/descrease number of seats.

Train schedule objects :
TrainDetail.java has information about train, train stations and train schedule.
TrainSchedule.java has information about train schedule : the days the trains works, exceptional days.
Station.java has a description of train station: time of arrival, time of departure, station of arrival, station of departure.
TrainResponse.java has a simplified format data for train schedule details ( can be useful for example to use for some front end components).

2. Project tests :
Project test are written in TrainSchedulerTest.java class.
There are 3 tests right now :
- check of adding of a new train schedule (and consult train schedule details)
- to check if we manage well train schedule exception
- check if we can add train schedule detail to existing one

3. The things to do next in project :

A.
To schedule other things than days of weeks, TrainSchedule class can be an interface,
and one implementation can be weeklySchedule or customSchedule, or any other need.
Then we are looking for a train, we will call the method on the schedule object : “doesMatch(theDateWeAreLookingFor)”
as it is done in train detail store, and it should continue to work.

B.
To book a ticket:
- we will look for the matching train detail and departure time
- We will look if there is a train instance matching this detail and departure time
    - If there is an instance, we will increase the number of seats occupied for the paths the user has
    requested (ex: SF -> LA will book a seat for SF -> San Jose and San Jose -> LA, but not in LA -> San diego)
	- If there is no instances created yet, we will initialize a new instance and increase the number of seats as described above.

An instance is a train at a specific date and time. It has a list of itineraries, and for each itinerary a number of occupied seats.

