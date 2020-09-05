from pyecharts import options as opts
from pyecharts.charts import Kline
import os
import pandas as pd

name = 'yingbang'
path = os.getcwd() + '/'+name+'.csv'
df = pd.read_csv(path)
data = []
datetime = []
for i in reversed(range(23)):
    data.append([df.iloc[i,1],df.iloc[i,2],df.iloc[i,4],df.iloc[i,3]])
    datetime.append(df.iloc[i,0])

c = (
    Kline()
        .add_xaxis(datetime)
        .add_yaxis("kline",data)
        .set_global_opts(
        xaxis_opts=opts.AxisOpts(is_scale=True),
        yaxis_opts=opts.AxisOpts(
            is_scale=True,
            splitarea_opts=opts.SplitAreaOpts(
                is_show=True, areastyle_opts=opts.AreaStyleOpts(opacity=1)
            ),
        ),
        datazoom_opts=[opts.DataZoomOpts(type_="inside")],
        title_opts=opts.TitleOpts(title="云计算第一组人民币对英镑7月k线图"),
    )
        .render("英镑.html")
)
