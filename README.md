# TorontoParking

## Overview
TorontoParking is a Smart Android Application that aims to simplify the "Toronto Parking Experience". This application uses Toronto open data and Google Maps API to provide users with a seamless interface. More specifically, the application simulates a public parking reservation system (assuming that the City of Toronto can provide real time data on available parking spaces).

## How it works

1. We collect data on Toronto parking locations using their [open data](https://open.toronto.ca/dataset/parking-lot-facilities/).
2. Then we create information activities that contains the parking lot information which users can view.
3. Utilizing Google Maps API, we plot all the parking lot facilities using markers and use clustering for efficiency and design purposes.
4. To open a specific parking lot's information activity, users can utilize the search feature to search by name or the maps feature to search by location.
5. To use more features, users can easily create and log into an account using their email and password (with Firebase auth and real time database).
6. To reserve, users must register their vehicle and enter a valid date/time.
7. Finally, the reservation is complete.

## Future Plans

- Implement a database that contains all reservations and handles parking availability.
- Get the current number of physically available spaces using Toronto monitoring systems.
- Link a payment system to the application when reserving parkings.
- Save user-defined vehicles for future use.
- Create user-defined recently viewed, favorited, and past reservations activities.
- Design different color themes for application.
- Change the app from the simulation stage, to a working, production stage.
- Hopefully, release the project on the Google Play Store.

## Challenges

- I wanted to send a reservation confirmation email/text to the user using Twilio API, however, there were many problems setting this up. 
- Trying to get the number of currently available parking spaces.

### Known Bugs

- Some buttons are still not functional.
