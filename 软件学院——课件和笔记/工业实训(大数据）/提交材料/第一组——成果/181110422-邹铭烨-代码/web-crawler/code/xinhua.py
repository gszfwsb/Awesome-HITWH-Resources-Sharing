from pyecharts import options as opts
from pyecharts.charts import Kline
import os
import pandas as pd

name = 'xinhua'
data = []
datetime = []
for i in range(1, 30):
    if i % 7 == 4 or i % 7 == 5:
        continue
    datetime.append(i)
    date = '07-' + '{:02d}'.format(i)
    path = os.getcwd() + '/stock/' + name + '/新华-07-' + '{:02d}'.format(i) + '.csv'
    df = pd.read_csv(path)
    value = df.iloc[:, 2].values
    open = value[0]
    close = value[-1]
    highest = max(value)
    lowest = min(value)
    data.append([open, close, lowest, highest])

c = (
    Kline()
        .add_xaxis(["2017/07/{}".format(i) for i in datetime])
        .add_yaxis("kline", data)
        .add_yaxis("kline", data)
        .set_global_opts(
        xaxis_opts=opts.AxisOpts(is_scale=True),
        yaxis_opts=opts.AxisOpts(
            is_scale=True,
            splitarea_opts=opts.SplitAreaOpts(
                is_show=True, areastyle_opts=opts.AreaStyleOpts(opacity=1)
            ),
        ),
        datazoom_opts=[opts.DataZoomOpts(type_="inside")],
        title_opts=opts.TitleOpts(title="云计算第一组新华股7月k线图"),
    )
        .render("新华.html")
)
