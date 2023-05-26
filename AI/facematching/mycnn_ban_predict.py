import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import layers
import numpy as np
import cv2
from facematching.daopmember import DaoPMember
arr_name = [
    "samelec","kakao","anrap","naver","carhyun",
    "lgelec","mobihyun","giup","kakaopay","google",
    "sktel","devslys","nhn","line","coopang",
    "minz","craft","ency","zikb","bibe",
    "cafe24","ebeykor","nice","tmax","samsds",
    "hanmds","ringnet","comtex","secui","sinsege"
]


dpm = DaoPMember()
cnt_del = dpm.mydelete()
print("cnt_del",cnt_del)


mylist = dpm.getData()
print(mylist)

model = tf.keras.models.load_model('mycnn.h5')

for f in mylist:
    img = cv2.imread("D:/saveFiles/"+f['pmem_pic'])
    dst = cv2.resize(img, dsize=(224, 224))
    dst = cv2.cvtColor(dst, cv2.COLOR_BGR2RGB)
    
    dst = dst / 255.0
    
    x_train =  np.reshape(dst,(1,224,224,3))
    
    arr_pred = model.predict(x_train)
    pred_idx = np.argmax(arr_pred[0])
    
    print("pred_idx",pred_idx)
    print("arr_pred",arr_pred[0]*100)
    
    arr_companys = []
    for idx,i in enumerate(arr_pred[0]):
        arr_companys.append({'company':arr_name[idx],'percent':i})
    
    
    for i in range(30):
        for j in range(30):
            if arr_companys[i]['percent'] > arr_companys[j]['percent']:
                i_p = arr_companys[i]['percent']
                i_c = arr_companys[i]['company']
                j_p = arr_companys[j]['percent']
                j_c = arr_companys[j]['company']
                arr_companys[i]['percent'] = j_p
                arr_companys[i]['company'] = j_c
                arr_companys[j]['percent'] = i_p
                arr_companys[j]['company'] = i_c
                
    for i in range(30):
        arr_companys[i]['percent'] = int(100*arr_companys[i]['percent'])          
                
                
    cnt_ins = dpm.myinsert(arr_companys[0]['company'], arr_companys[0]['percent'], arr_companys[1]['company'], arr_companys[1]['percent'], arr_companys[2]['company'], arr_companys[2]['percent'], arr_companys[3]['company'], arr_companys[3]['percent'], arr_companys[4]['company'], arr_companys[4]['percent'], f['pmem_id'])
    print("cnt_ins",cnt_ins)
            
        













