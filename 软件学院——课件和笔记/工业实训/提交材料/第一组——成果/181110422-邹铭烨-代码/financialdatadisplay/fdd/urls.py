from django.urls import path
from . import views
urlpatterns = [
    path('', views.home),
    path('xinhua', views.xinhua),
    path('haikang', views.haikang),
    path('keda', views.keda),
    path('jinshan', views.jinshan),
    path('chenguang', views.chenguang),
    path('dollar', views.dollar),
    path('pound', views.pound),
    path('gold', views.gold),
    path('silver', views.silver),
    path('naturalgas', views.naturalgas),
    path('soybean', views.soybean),
    path('aboutus', views.aboutus)
]
