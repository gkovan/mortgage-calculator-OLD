Feature: Calculate mortgage monthly payment
  The user should be able to get monthly payment

 Scenario: Calculate monthly mortgage paymemnt
    Given principal of "100000" and interest rate of "5.5" and years of "30"
    When api is invoked to calculate monthly mortgage payment
    Then Api returns with a http status code of 200 and the monthly payment
