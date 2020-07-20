# Birthday Application

## Usage
This is a project using maven and jdk 11.

Download as a zip or git clone https://github.com/andreypereverzin/birthday

Run the following command to build the application:

`mvn clean install`

To start the application run this command:

`java -jar target/birthday.jar <path-to-citizens-file> <path-to-opted-out-emails-file>`

## Properties
Properties are defined in `app.properties` file which contains the following:

`age=100`\
`weekdaysInAdvance=5`\
`weekdaysALotInAdvance=10`\
`aLotThreashold=20`

where 
<ul>
<li>age is the age in years when the King sends the email</li>
<li>weekdaysInAdvance defines how many weekdays in advance when the King should be notified about sending the email</li>
<li>weekdaysALotInAdvance defines how many weekdays in advance when the King should be notified about sending the email if there are a lot of emails to be sent</li>
<li>aLotThreashold defines what 'a lot' does mean, in this case more than 20 emails means 'a lot'</li>
</ul>

## Assumptions
As the King never works weekends and it is not clearly defined in the task description what should the output of the program include in case if it is executed in a weekend an assumption has been made that it should return empty output.

Also if a birthday is falling on Saturday or Sunday it will be included into the output at the same day as birthdays falling on Monday but number of emails for Saturday, Sunday and Monday are counted separately. For example if there are 10 birthdays on Saturday, 10 birthdays on Sunday and 10 birthdays on Monday (i.e. 30 in total, exceeding 'a lot' threshold), this situation will not be considered as exceeding this threshold.

This logic is implemented in `DateProvider` class.

There is validation for names and email addresses.

If any validation for a line fails - this line is silently skipped.
