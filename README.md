NetflixTranscribe
=================

##Purpose & Description: 
Quickly obtain the script of a movie through the use of crowdsourcing (Amazon Mechanical Turk's API).
The movie is broken down into 10 second time segments. Each of these segments is pushed to AMT for workers to create a script of that time segment by watching the specified section on Netflix. This includes the character's name and what is being said or a description of the scene if no talking takes place. These results are gathered and compared with an actual script on http://www.transcryptors.com/
  
##Executing the program
Swap in your Amazon MTurk credentials in the python scripts prior to execution.
