import urllib.request
import re
headers  ={
'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36'
}

req = urllib.request.Request(url='http://push2.eastmoney.com/api/qt/stock/trends2/get?cb=jQuery112405638669758788302_1596013292468&secid=103.ZS21H&ut=fa5fd1943c7b386f172d6893dbfba10b&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5%2Cf6%2Cf7%2Cf8%2Cf9%2Cf10%2Cf11%2Cf12%2Cf13&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58&iscr=0&ndays=1&_=1596013292897',headers=headers,method='GET')
response = urllib.request.urlopen(req)
html = response.read().decode('utf-8')

print(html)

#result = re.findall('.$?[(.*?)]',html)
strlist = html.split('["')[1][:-6].split('\",\"')
print(strlist)

import csv

with open('大豆2103.csv','a',newline='') as f:
    write = csv.writer(f)
    for i in range(len(strlist)):
        row = [strlist[i][0:10],strlist[i][11:16],strlist[i][-7:]]
        #row =[(strlist[len(strlist)-1][0:10]),(strlist[0][-8:]),(strlist[2])]
        write.writerow(row)
    f.close()
    print('文件写入成功')
