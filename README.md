# Support Wheel of Fate

* Background

All engineers in Company X take it in turns to support the business for half a day at a time. Currently,
generating a schedule that shows who’s turn is it to support the business is being done manually
and we need to automate that!

* Task

Your task is to design and build an online “Support Wheel of Fate”. This should repeat selecting two
engineers at random to both complete a half day of support (shift) each to ultimately generate a
schedule that shows whose turn is it to support the business.

* Assumptions

1. You can assume that Company X has 10 engineers.
2. You can assume that the schedule will span two weeks and start on the first working day of the
upcoming week.

* Rules

1. An engineer can do at most one half day shift in a day.
2. An engineer cannot have half day shifts on consecutive days.
3. Each engineer should have completed one whole day of support in any 2 week period.

***Important note:

These rules are liable to change in the future, so make sure your design is flexible enough to be able
to add new rules.


## Main technology stack

- Java 1.8
- Spring Boot 2.0.4
- lombok 1.18.2
- jackson-databind 2.9.6
- Gradle

### Usage

Import to eclipse and run :)