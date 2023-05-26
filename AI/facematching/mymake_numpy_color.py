import cv2
import numpy as np
import os

arr_name = [
            "samelec","kakao","anrap","naver","carhyun",
            "lgelec","mobihyun","giup","kakaopay","google",
            "sktel","devslys","nhn","line","coopang",
            "minz","craft","ency","zikb","bibe",
            "cafe24","ebeykor","nice","tmax","samsds",
            "hanmds","ringnet","comtex","secui","sinsege"
            ]


arr_label = [
        "00","01","02","03","04",
        "05","06","07","08","09",
        "10","11","12","13","14",
        "15","16","17","18","19",
        "20","21","22","23","24",
        "25","26","27","28","29"
    ]

train_data = []
train_label = []

for idx,l in enumerate(arr_label):
    str_dir = "emp_member/{}".format(l)
    myfiles = os.listdir(str_dir)
    for f in myfiles:
        # img = cv2.imread("{}/{}".format(str_dir,f),cv2.IMREAD_UNCHANGED)
        img = cv2.imread("{}/{}".format(str_dir,f))
        train_data.append(img)
        train_label.append(idx)
        print(str_dir,f)


train_data_n = np.array(train_data)
train_label_n = np.array(train_label)

np.save("train_data_c",train_data_n)
np.save("train_label_c",train_label_n)

print(train_data_n[0])
# print(train_data_n)
print(train_data_n.shape)

# print(train_label_n)
print(train_label_n.shape)