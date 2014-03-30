import sys
import io
sys.path.append("/home/ugrads/m/madano/web_project/Python27/Lib/site-packages")
from boto.mturk.connection import MTurkConnection

#REFEREBCES: http://www.toforge.com/2011/05/boto-mturk-tutorial-fetch-results-and-pay-workers/

ACCESS_ID = ''
SECRET_KEY = ''
HOST = 'mechanicalturk.amazonaws.com'


def get_all_reviewable_hits(mtc):
    page_size = 50
    hits = mtc.get_reviewable_hits(page_size=page_size)
    #print "Total results to fetch %s " % hits.TotalNumResults
    #print "Request hits page %i" % 1
    
    total_pages = float(hits.TotalNumResults)/page_size
    int_total= int(total_pages)
    if(total_pages-int_total>0):
        total_pages = int_total+1
    else:
        total_pages = int_total
    pn = 1
    while pn < total_pages:
        pn = pn + 1
        #print "Request hits page %i" % pn
        temp_hits = mtc.get_reviewable_hits(page_size=page_size,page_number=pn)
        hits.extend(temp_hits)
    return hits
 
mtc = MTurkConnection(aws_access_key_id=ACCESS_ID,
                      aws_secret_access_key=SECRET_KEY,
                      host=HOST)

hits = get_all_reviewable_hits(mtc)

for hit in hits:
    assignments = mtc.get_assignments(hit.HITId)
    for assignment in assignments:
        #print "Answers of the worker %s" % assignment.WorkerId
        for question_form_answer in assignment.answers[0]:
            for value in question_form_answer.fields:
				print "\nResult: " + value
				#print "%s" % value
				mtc.approve_assignment(assignment.AssignmentId)
				mtc.disable_hit(hit.HITId)
				