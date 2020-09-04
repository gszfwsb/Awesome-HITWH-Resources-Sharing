import urllib.request
import csv
f = open('demo.csv','a',newline='')
writer = csv.writer(f)

headers = {
'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36 Edg/84.0.522.44'
}

req = urllib.request.Request('https://hq.sinajs.cn/?_=1595908078020/&list=hf_GC',headers=headers,method='GET')
response = urllib.request.urlopen(req)
list = response.read().decode('GBK').split(',')

print(list)
print(list[0][-15:-10]+'   '+list[len(list)-2]+'   '+list[0][-8:-1]+'  '+list[len(list)-3]+' '+list[6])
row = [list[0][-15:-10],list[len(list)-2],list[0][-8:-1],list[len(list)-3],list[6]]
writer.writerow(row)
print(list[0][-8:-1]+'写入成功')

f.close()

'''
import urllib.request
import csv
f = open('demo2.csv','a',newline='')
writer = csv.writer(f)

headers = {
'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36 Edg/84.0.522.44'
}

req = urllib.request.Request('https://hq.sinajs.cn/rn=1596081869675list=fx_sgbpcny',headers=headers,method='GET')
response = urllib.request.urlopen(req)
list = response.read().decode('GBK').split(',')

str = list[0][4:-10]+' '+list[-9]+' '+list[1]+' '+list[-1][:-3]+' '+list[0][-8:]
row = [list[0][4:-10],list[-9],list[1],list[-1][:-3],list[0][-8:]]
writer.writerow(row)
print(str+'写入成功')
f.close()'''


