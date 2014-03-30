import sys
import io
sys.path.append("/home/ugrads/m/madano/web_project/Python27/Lib/site-packages")
import MySQLdb

conn = MySQLdb.connect(host= "database2.cse.tamu.edu",
                  user="madano",
                  passwd="cars",
                  db="madano")
x = conn.cursor()

try:
   x.execute("""INSERT INTO Words VALUES (%s,%s)""",(188,'words'))
   conn.commit()
except:
   conn.rollback()

conn.close()