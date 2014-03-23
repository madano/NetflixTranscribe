import sys
sys.path.append("/home/ugrads/m/madano/web_project/Python27/Lib/site-packages")
from boto.mturk.connection import *
from boto.mturk.question import QuestionContent,Question,QuestionForm,Overview,AnswerSpecification,SelectionAnswer,FormattedContent,FreeTextAnswer
from boto.mturk.qualification import *
from boto.mturk import *

ACCESS_ID = 'YOUR_ACCESS_ID'
SECRET_KEY = 'YOUR_SECRET_KEY'
HOST = 'mechanicalturk.sandbox.amazonaws.com'

mtc = MTurkConnection(aws_access_key_id=ACCESS_ID,
                      aws_secret_access_key=SECRET_KEY,
                      host=HOST)
title = 'Transcribe a movie from Netflix'
description = ('Watch a movie on Netflix and '
               'copy down what the characters say')
keywords = 'netflix, movie, transcription'

#---------------  BUILD OVERVIEW -------------------

s1 = """<![CDATA[<h3>Listen to a short video and transcribe what is said.</h3>


<ul>
	<li>Listen to a short section of <u>Ferris Bueller&#39;s Day Off</u> on Netflix.</li>
	<li>Do not include &quot;hmm&quot; and &quot;errs&quot; in the transcription.</li>
	<li>Do not correct for grammar mistakes but transcribe as spoken.</li>
	<li>Use punctuation where appropriate.</li>
	<li>Indicate different speakers with "[Character_name]", or if name unkown, description such as "[security guard]:" or "[Boy in gas station]".</li>
	<li>To help identify characters, a table with names and pictures has been given below as reference.</li>
	<li>If no words are spoken, please simply describe the scene, or state what is currently being shown.</li>
	<li>for example: "Ferris lays in bed, looking up towards the ceiling, while smiling"</li>
</ul>

<table>
	<tbody>
		<tr>
			<td>
			<h6>Cameron</h6>
			<img alt="Cameron Frye" height="100" src="http://i.imgur.com/utI0L5P.jpg" width="100" /></td>
			<td></td>
			<td>
			<h6>Ed</h6>
			<img alt="Ed Rooney" height="100" src="http://i.imgur.com/xKPwBg0.png" width="100" /></td>
			<td></td>
			<td>
			<h6>Ferris</h6>
			<img alt="Ferris Bueller" height="100" src="http://i.imgur.com/71pZpAn.jpg" width="100" /></td>
		</tr>
		<tr>
			<td>
			<h6>Jeanie</h6>
			<img alt="Jeanie Bueller" height="100" src="http://i.imgur.com/MxHTfe0.jpg" width="100" /></td>
			<td></td>
			<td>
			<h6>Katie</h6>
			<img alt="Katie Bueller" height="100" src="http://i.imgur.com/Gssa4WC.jpg" width="100" /></td>
			<td></td>
			<td>
			<h6>Sloane</h6>
			<img alt="Sloane Peterson" height="100" src="http://i.imgur.com/EOnjujD.jpg" width="100" /></td>
		</tr>
		<tr>
			<td>
			<h6>Tom</h6>
			<img alt="Tom Bueller" height="100" src="http://i.imgur.com/JTqvbIX.jpg" width="100" /></td>
		</tr>
	</tbody>
</table>
<h3>IMPORTANT: Netflix shows the time remaining in the movie!</h3>
<h3>The time given is the time left in the movie. Please just</h3>
<h3>scroll in the movie until the time given to you is displayed as the time remaining!</h3>
<p>If character is still speaking when time expires, please end the line with "..."</p>
<p>For example: ferris: Those are some nice plants. I really ...</p>
<p>If a character was in mid-sentence at the start of your time, please start the line with "..."</p>
<p>For example: ferris: ... like nice plants</p>

				 ]]>"""


overview = Overview()
overview.append_field('Title', 'Give your opinion on this website')

overview.append_field('FormattedContent',s1)


								 
								
#---------------  BUILD QUESTION 1 -------------------
 

#---------------  BUILD QUESTION 2 -------------------
time_start = 6140;
time_end = 240;
time_current = 6140;

while time_current > time_end:
	hours_from = int(time_current/3600)
	minutes_from = int((time_current % 3600) / 60)
	seconds_from = time_current % 60
	
	time_current = time_current - 10;
	
	hours_to = int(time_current/3600)
	minutes_to = int((time_current % 3600) / 60)
	seconds_to = time_current % 60
	
	if minutes_from < 10 and hours_from >= 1:
		minutes_from = "0" + str(minutes_from)
	else:
		minutes_from = str(minutes_from)
		
	if minutes_to < 10  and hours_to >= 1:
		minutes_to = "0" + str(minutes_to)
	else:
		minutes_to = str(minutes_to)
		
		
	if seconds_from < 10:
		seconds_from = "0" + str(seconds_from)
	else:
		seconds_from = str(seconds_from)
	if seconds_to < 10:
		seconds_to = "0" + str(seconds_to)
	else:
		seconds_to = str(seconds_to)
		
	
	if hours_from >= 1 and hours_to >= 1:
		watch = "Watch From: " + str(hours_from) + ":" + minutes_from + ":" + seconds_from + " To: " + str(hours_to) + ":" + minutes_to + ":" + seconds_to
	elif hours_from >= 1 and hours_to < 1:
		watch = "Watch From: " + str(hours_from) + ":" + minutes_from + ":" + seconds_from + " To: " + minutes_to + ":" + seconds_to
	else:
		watch = "Watch From: " + minutes_from + ":" + seconds_from + " To: " + minutes_to + ":" + seconds_to
		
	print watch
	qc2 = QuestionContent()
	qc2.append_field('Title',watch)
	 
	fta2 = FreeTextAnswer()
	 
	q2 = Question(identifier="comments",
				  content=qc2,
				  answer_spec=AnswerSpecification(fta2))
	 
	#--------------- BUILD THE QUESTION FORM -------------------
	 
	question_form = QuestionForm()
	question_form.append(overview)
	question_form.append(q2)
	 
	#--------------- CREATE THE HIT -------------------
	 
	
	mtc.create_hit(questions=question_form,
				   max_assignments=1,
				   title=title,
				   description=description,
				   keywords=keywords,
				   duration = 60*5,
				   reward=0.01)
print "worked" 
