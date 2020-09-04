from impala.dbapi import connect
import pandas as pd

list1 = []
list2 = []
list3 = []
list4 = []
list5 = []

conn = connect(host='192.168.58.128', port=10000, auth_mechanism="PLAIN")
cur = conn.cursor()

cur.execute('use zmy_db')
cur.execute('show tables')
cur.execute('select * from nycsv limit 100')
for line in cur.fetchall():
    line = list(line)
    list1.append(line[0])
    #list2.append(line[1])
    list3.append(line[2])
    list4.append(line[3])
    list5.append(line[4])




pf = pd.DataFrame(list(zip(list1, list3, list4, list5)), columns = ['id', 'data', 'date', 'time'])
print(pf)
pf.to_csv('NewYork.csv', encoding='UTF-8')

conn.close()
