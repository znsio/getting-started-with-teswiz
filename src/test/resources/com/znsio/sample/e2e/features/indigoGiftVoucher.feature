@indigo
Feature: Personalize Indigo Gift Voucher
  #  CONFIG=./configs/indigo_config.properties PLATFORM=web TAG=indigo ./gradlew run
  @web @invalidPromo
  Scenario: I, as a Guest, Personalize Gift Voucher with invalid promo code.
	Given I personalize the Indigo "1" Gift Voucher of "3000" worth.
	When User applies invalid promocode
	Then User should be able to proceed with orignal amount.