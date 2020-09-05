import tushare as ts    
for i in range(1,30):
    if (i<10):
        chenguang = ts.get_tick_data('603899',date = '2020-07-0'+str(i),src = 'tt')
        if (chenguang is not None):
            chenguang = chenguang[['time','price','change']]
            chenguang.to_csv('晨光-07-0'+str(i)+'.csv')
            print('文件写入成功,'+str(i))
            
    else:
        chenguang = ts.get_tick_data('603899',date = '2020-07-'+str(i),src = 'tt')
        if (chenguang is not None):
            chenguang = chenguang[['time','price','change']]
            chenguang.to_csv('晨光-07-'+str(i)+'.csv')
            print('文件写入成功,'+str(i))
            '''
    jinshan = ts.get_tick_data('688111',date = '2020-07-29',src = 'tt')
    keda = ts.get_tick_data('002230',date = '2020-07-29',src = 'tt')
    haikang = ts.get_tick_data('002415',date = '2020-07-29',src = 'tt')
    xinhua = ts.get_tick_data('603888',date = '2020-07-29',src = 'tt')
    chenguang = ts.get_tick_data('603899',date = '2020-07-29',src = 'tt')

'''