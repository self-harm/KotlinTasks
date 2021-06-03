package taxipark

import java.lang.NullPointerException

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> = this.allDrivers
    .filter { driver ->
        driver !in trips
            .map { trip ->  trip.driver }
    }
    .toSet()

/*

 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> = this.allPassengers
    .filter {
        this.trips
            .filter { trip -> it in trip.passengers }
            .count() >= minTrips
    }
    .toSet()

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> = this.allPassengers
        .filter {
            this.trips
                .filter { trip -> it in trip.passengers && trip.driver == driver }
                .count() > 1
        }
        .toSet()


/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> = allPassengers
    .filter {
        this.trips.filter { trip -> it in trip.passengers && trip.discount != null }.count() >
        this.trips.filter { trip -> it in trip.passengers }.count() / 2
    }
    .toSet()


/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    if (this.trips.isEmpty()) return null

    val map = mutableMapOf<Int, Int>()
    this.trips
        .forEach {
            var key: Int = (it.duration!!.div(10))
            if (map.containsKey(key)) {
                var value = map.getValue(key)
                map.set(key, ++value)
            } else map.put(key, 1)
        }


    val maxValue = map.values.maxOrNull()
    var lastKey = 0

    for ((key, value) in map) {
        if (maxValue == value) {
            lastKey = key
            break
        }
    }

    return 10 * lastKey..10 * lastKey + 9
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    TODO()
}
