Feature: Ordering drinks

  Background:
    Given Seb who wants to create an Order
    When Jean-Michel is declared as recipient

    @ordering
  Scenario: Creating an empty order
  #  Given Romeo who wants to create an Order
  #  When Juliet is declared as recipient
    Then the order does not contain any drinks

 # @Ordering
 #  Scenario: Adding a drink to an order
 #   Given Tom who wants to create an Order
 #   When Jerry is declared as recipient
 #   And a "PepsiCola Zero" is added to the order
 #   Then the order contains 1 drink

  @ordering
  Scenario: Checking the contents of an order
  #  Given Seb who wants to create an Order
  #  When Jean-Michel is declared as recipient
    And a "PepsiCoke Zero" is added to the order
    And a "DietCola Max" is added to the order
    And another "PepsiCoke Zero" is added to the order
    Then the order contains 3 drinks
    And the order contains 4 "PepsiCoke Zero"
    And the order contains 1 "DietCola Max"

  @payment
  Scenario: Paying the price
   # Given Celine who wants to create an Order
    And the price of a "PepsiCoke Zero" being 2.75 dollars
    And the price of a "DietCola Max" being 2.55 dollars
    And taxes in Quebec being 0%
   # When Rene is declared as recipient
    And a "PepsiCoke Zero" is added to the order
    And a "DietCola Max" is added to the order
    Then the price without taxes is 5.30 dollars
    And the price including taxes is 5.30 dollars
