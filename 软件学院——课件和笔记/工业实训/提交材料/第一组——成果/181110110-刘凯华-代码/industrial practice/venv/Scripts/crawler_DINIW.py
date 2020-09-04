import urllib.request
import re
headers  ={
'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36'
}

req = urllib.request.Request(url='https://hq.sinajs.cn/rn=1595908843394list=DINIW',headers=headers,method='GET')
response = urllib.request.urlopen(req)
html = response.read().decode('gb2312')

#print(html)
strlist = html.split(',')

print(strlist)
print(strlist[len(strlist)-2]+':'+strlist[1]+'  time:'+strlist[len(strlist)-1][0:10]+' '+strlist[0][-8:])

import csv

with open('US dollar index.csv','a',newline='') as f:
    row =[(strlist[len(strlist)-1][0:10]),(strlist[0][-8:]),(strlist[2])]
    write = csv.writer(f)
    write.writerow(row)
    f.close()
    print('文件写入成功')