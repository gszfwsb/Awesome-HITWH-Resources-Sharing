import urllib.request
import re
headers  ={
'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36'
}

req = urllib.request.Request(url='http://push2.eastmoney.com/api/qt/stock/trends2/get?cb=jQuery112407344591752870742_1596014744871&secid=101.SI00Y&ut=fa5fd1943c7b386f172d6893dbfba10b&fields1=f1%2Cf2%2Cf3%2Cf4%2Cf5%2Cf6%2Cf7%2Cf8%2Cf9%2Cf10%2Cf11%2Cf12%2Cf13&fields2=f51%2Cf52%2Cf53%2Cf54%2Cf55%2Cf56%2Cf57%2Cf58&iscr=0&ndays=1&_=1596014744952',headers=headers,method='GET')
response = urllib.request.urlopen(req)
html = response.read().decode('utf-8')

print(html)
#result = re.findall('.$?[(.*?)]',html)
strlist = html.split('["')[1][:-6].split('\",\"')
print(strlist)

import csv

with open('COMEX白银.csv','a',newline='') as f:
    write = csv.writer(f)
    for i in range(len(strlist)):
        row = [strlist[i][0:10],strlist[i][11:16],strlist[i][-6:]]
        #row =[(strlist[len(strlist)-1][0:10]),(strlist[0][-8:]),(strlist[2])]
        write.writerow(row)
    f.close()
    print('文件写入成功')
'''    
["2020-07-29 06:00,24.580,24.580,24.580,24.580,17,0.000,24.5800",
 "2020-07-29 06:01,24.580,24.615,24.615,24.560,105,0.000,24.5843",
 "2020-07-29 06:02,24.615,24.565,24.615,24.560,90,0.000,24.5929",
 "2020-07-29 06:03,24.565,24.595,24.595,24.565,21,0.000,24.5920",
 "2020-07-29 06:04,24.590,24.605,24.605,24.585,32,0.000,24.5924",
 "2020-07-29 06:24,24.610,24.585,24.610,24.560,75,0.000,24.6005",
 "2020-07-29 06:25,24.590,24.585,24.590,24.585,2,0.000,24.6004",
 "2020-07-29 06:26,24.595,24.595,24.600,24.595,10,0.000,24.6004",
 "2020-07-29 06:27,24.590,24.545,24.590,24.545,34,0.000,24.5986",
 "2020-07-29 17:20,24.510,24.510,24.510,24.510,2,0.000,24.3783"]
'''