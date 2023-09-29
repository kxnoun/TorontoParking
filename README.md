# TorontoParking 🚗🅿️

---

## 🌟 Overview

**TorontoParking** is a Smart Android Application that aims to simplify the "Toronto Parking Experience". This application uses Toronto open data and Google Maps API to provide users with a seamless interface. More specifically, the application simulates a public parking reservation system (assuming that the City of Toronto can provide real time data on available parking spaces).

---

## 🚀 How It Works

1. **Data Collection**: We collect data on Toronto parking locations using their [open data](https://open.toronto.ca/dataset/parking-lot-facilities/).
2. **Information Activities**: Then we create information activities that contains the parking lot information which users can view.

<p align="center">
  <img src="https://github.com/kxnoun/TorontoParking/blob/main/README_images/information_page.jpg" alt="Information Activity" width="300"/>
</p>

3. **Google Maps Integration**: Utilizing Google Maps API, we plot all the parking lot facilities using markers and use clustering for efficiency and design purposes.

<p align="center">
  <img src="https://github.com/kxnoun/TorontoParking/blob/main/README_images/main_page.jpg" alt="Clustering" width="300"/>
</p>

4. **Search & Access**: To open a specific parking lot's information activity, users can utilize the search feature to search by name or the maps feature to search by location.

<p align="left">
  <img src="https://github.com/kxnoun/TorontoParking/blob/main/README_images/clicked_marker_page.jpg" alt="Clicked Marker" width="290"/>
  <img src="https://github.com/kxnoun/TorontoParking/blob/main/README_images/search_page.jpg" alt="Search Feature" width="290"/>
  <img src="https://github.com/kxnoun/TorontoParking/blob/main/README_images/search_button_page.jpg" alt="Search Click Feature" width="290"/>
</p>

5. **User Accounts**: To use more features, users can easily create and log into an account using their email and password (with Firebase auth and real time database).

<p align="center">
  <img src="https://github.com/kxnoun/TorontoParking/blob/main/README_images/register_page.jpg" alt="Register Page" width="290"/>
  <img src="https://github.com/kxnoun/TorontoParking/blob/main/README_images/login_page.jpg" alt="Login Page" width="290"/>
</p>

6. **Reservations**: Users can register their vehicle and specify a date/time for parking.

<p align="center"">
  <img src="https://github.com/kxnoun/TorontoParking/blob/main/README_images/vehicle_register_page.jpg" alt="Vehicle Registration" width="290"/>
  <img src="https://github.com/kxnoun/TorontoParking/blob/main/README_images/date_register_page.jpg" alt="Date and Time" width="290"/>
</p>

7. **Confirmation**: Finally, the reservation is complete.

<p align="center">
  <img src="https://github.com/kxnoun/TorontoParking/blob/main/README_images/confirmation_page.jpg" alt="Confirmation Page" width="300"/>
</p>

---

## 🌱 Future Plans

- Implement a database that contains all reservations and handles parking availability.
- Get the current number of physically available spaces using Toronto monitoring systems.
- Link a payment system to the application when reserving parkings.
- Save user-defined vehicles for future use.
- Create user-defined recently viewed, favorited, and past reservations activities.
- Design different color themes for application.
- Change the app from the simulation stage, to a working, production stage.
- Hopefully, release the project on the Google Play Store.

---

## 🚧 Challenges

- I wanted to send a reservation confirmation email/text to the user using Twilio API, however, there were many problems setting this up. 
- Trying to get the number of currently available parking spaces.

---

## ⚠️ Known Bugs

- Some buttons are still not functional.
- Forgot password is still not functional. 
