from django.shortcuts import render


# Create your views here.

def home(request):
    return render(request, 'fdd/home.html')


def xinhua(request):
    return render(request, 'fdd/新华.html')

def haikang(request):
    return render(request, 'fdd/海康.html')

def keda(request):
    return render(request, 'fdd/科大.html')

def jinshan(request):
    return render(request, 'fdd/金山.html')

def chenguang(request):
    return render(request, 'fdd/晨光.html')

def dollar(request):
    return render(request, 'fdd/美元指数.html')

def pound(request):
    return render(request, 'fdd/英镑.html')

def gold(request):
    return render(request, 'fdd/黄金.html')

def silver(request):
    return render(request, 'fdd/白银.html')

def naturalgas(request):
    return render(request, 'fdd/天然气.html')

def soybean(request):
    return render(request, 'fdd/大豆.html')

def aboutus(request):
    return render(request, 'fdd/aboutus.html')