import urllib.request
import csv
import time

while True:

	f = open('demo.csv','a',newline='')
	writer = csv.writer(f)
	headers = {
	'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3775.400 QQBrowser/10.6.4198.400'
	}

	req = urllib.request.Request('https://hq.sinajs.cn/rn=1596336759010list=DINIW',headers=headers,method='GET')
	response = urllib.request.urlopen(req)
	list = response.read().decode('GBK').split(',')

	str = list[0][4:-10]+' '+list[-9]+' '+list[1]+' '+list[-1][:-3]+' '+list[0][-8:]
	row = [list[0][4:-10],list[-9],list[1],list[-1][:-3],list[0][-8:]]
	writer.writerow(row)
	print(str+'写入成功')
	f.close()
	time.sleep(15)

