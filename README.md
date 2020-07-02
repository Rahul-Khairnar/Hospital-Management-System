## Hospital Management System: Project Overview

* I made a desktop application for Hospital Managment System.
* This desktop will help the hospital to maintain a proper database of all the patients who are visiting the hospital for treatment.
* The application uses authentication at the start of the application to avoid illegal access to the database.

## Resources Used

* JAVA Version: JDK 14
* Sublime Text 3
* Modules used: Swing, AWT, Hibernate, time.format, awt.image.buffered, ImageIo, Sarxos.webcam, Io.File
* API For webcam - https://github.com/sarxos/webcam-capture
* Database: Oracle 11g

## About the application

The application for Hospital Management System has got windows for all CRUD operations. Windows are:
* New Patient- For adding new patients to the database.
* Patient Image- For clicking the image of the patient and storing it in the database.
* Illness Page- For adding the problems the patient has to the database. The illnesses are categorized into 5 different categories from 1-5 and stored accordingly.
* Expense Window- This window shows all the details of a particular patient and also shows the expenses incurred by the total number of days.
* Explore Window- This has the functionality to see the list of all the patients admitteed to the hospital. It also has a chart feature which plots charts for the total number of patients in all the five categories.
* Update window- This window allows you to update the details of the patient.
* Delete window-  This window allows you to delete the patient details from the database.
    
![alt text](https://github.com/Rahul-Khairnar/Hospital-Management-System/blob/master/Screenshots/New%20Patient.PNG "New Patient")

![alt text](https://github.com/Rahul-Khairnar/Hospital-Management-System/blob/master/Screenshots/Patient%20Image.PNG "Patient Image")

![alt text](https://github.com/Rahul-Khairnar/Hospital-Management-System/blob/master/Screenshots/Patient%20Details.PNG "Patient Details")

![alt text](https://github.com/Rahul-Khairnar/Hospital-Management-System/blob/master/Screenshots/Main%20Window.PNG "Main Window")

![alt text](https://github.com/Rahul-Khairnar/Hospital-Management-System/blob/master/Screenshots/Login.PNG "login Page")


    
## Validations
The application has got proper validations set for the entry of all the details those are being entered about a patient. The validations include:
* Patient id should be positive and should be an Integer.
* The name should be more than one charecter long ad should not have any special characters.
* The aadhar number should be 8 digit long.
* The phone number should be 10 digit long.
* The city should be minimum 4 charecters long.
* The pin code should be 6 digit long.
* There should be atleast one illness selected.

## Getter Setters
Getters and setters are written for all parameters that are stored and retreived from the database. Along with the details collected from the patient, time of that particular day is also
inserted into the table.

## Table
* Table name is: Patient Actual
* Columns in the table:
    * Patient Id(Primary key)
    * Patient First Name
    * Patient Last Name
    * Aadhar Number
    * Telephone code
    * Contact Number
    * Address
    * Pin Code
    * Email Id
    
## Hibernate 
Hibernate is used in place of JDBC because we dont have to write separate queries for saving and retreiving the data from the database. All the database connectivity related details are stored in
the **hibernate.cfg.xml** and all the column mapping is stored in the mapping file name **hibernate.hbm.xml**. All the saving and retreiving the details are done using get and set methods.

## Data Visualisation
All illness related information is stored in the database in the form of categories from 1-5. Hence the counts of total patients in each category is calculated and is used to make charts.

![alt text](https://github.com/Rahul-Khairnar/Hospital-Management-System/blob/master/Screenshots/Charts.PNG "login Page")
