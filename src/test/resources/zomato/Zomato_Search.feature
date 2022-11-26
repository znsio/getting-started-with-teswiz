@zomato @web
Feature: Search functionality
      #CONFIG=./configs/zomato_config.properties TAG="@zomato and @web" PLATFORM=web  ./gradlew run

  Scenario: Online Ordering not supported on zomato through desktop as logged in user
    Given I, as logged in user search for "Flavour" resturant in "Meerut" city
    Then On online order, I see info banner


  Scenario: Search Resturants in Meerut city having 4+ rating as a guest user
    Given I, a "guest user" from "Meerut" city select "4+ Ratings"
    Then I see resturants having 4+ ratings


  Scenario: Search Resturants in current location having Outdoor seating as a guest user
    Given I, a "guest user" using current location selects "Outdoor Seating" in "Dinning out"
    Then I see resturants near me


  Scenario: Search Burger Cusine for Delivery in Meerut city as guest user
    Given I, as logged in user from "Meerut" city select "Burger" cusine
    Then I see resturants to deliver burger