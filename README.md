# Mutants detector

![appscreenshot](https://github.com/alvizeta/mlmutants/blob/master/appscreenshot.png)

This application was developed in order to achieve some goals described in the exam to enter to MercadoLibre which is the largest eshop in Argentina.

I keep it here because is a good example of an entire application with a simple Angular UI that consumes a simple API built with Java and Springboot using a h2 database.

The details about the goals of this exam are described in /MercadoLibre Exam.txt and the time provided was 72hs so it was a fun challenge to deal with :)

# Instructions to run the app

  Java project
  in order to run the java project go to the /target and run the command:

  java -jar target/mutants-0.0.1-SNAPSHOT.jar 

  this will start an instance of the application (and the integrated H2 database) in your direction localhost:300

  therefore the services of the api will be running in these addreses:

  localhost:3000/stats

  localhost:3000/mutant

  Javascript/Angular project
  make sure you have installed NPM Node.js Grunt Bower in your machine
  then go to mutants-ui/src/main/webapp and in there run the command:

  grunt serve

  that will initialize your angular app in the url localhost:9000/

  go there and start verifying mutants!
