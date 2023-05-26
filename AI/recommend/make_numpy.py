import cx_Oracle
import os
import numpy as np
import auth_prop

LOCATION = r"D:\instantclient_21_3"
os.environ["PATH"] = LOCATION + ";" + os.environ["PATH"]

oracle_acc = auth_prop.oracle
connection = cx_Oracle.connect(oracle_acc['user'], oracle_acc['pw'], oracle_acc['ip'])
cursor = connection.cursor()

survey_data=[]
emember_id=[]

#SQL
cursor.execute("""
    SELECT SUR1, SUR2, SUR3, SUR4, SUR5
        , SUR6, SUR7, SUR8, SUR9, SUR10
        , SUR11, SUR12, SUR13, SUR14, SUR15
        , SUR16, SUR17, SUR18, SUR19, SUR20
        , EMEM_ID
    FROM REC_START
""")


for i in cursor:
    tmp = []
    tmp.append(int(i[0]))
    tmp.append(int(i[1]))
    tmp.append(int(i[2]))
    tmp.append(int(i[3]))
    tmp.append(int(i[4]))
    tmp.append(int(i[5]))
    tmp.append(int(i[6]))
    tmp.append(int(i[7]))
    tmp.append(int(i[8]))
    tmp.append(int(i[9]))
    tmp.append(int(i[10]))
    tmp.append(int(i[11]))
    tmp.append(int(i[12]))
    tmp.append(int(i[13]))
    tmp.append(int(i[14]))
    tmp.append(int(i[15]))
    tmp.append(int(i[16]))
    tmp.append(int(i[17]))
    tmp.append(int(i[18]))
    tmp.append(int(i[19]))
    tmp_n = np.array(tmp)
    # print(tmp_n)
    # print(i[20])
    survey_data.append(tmp_n)
    emember_id.append(i[20])
    
cursor.execute("""
    SELECT SUR1, SUR2, SUR3, SUR4, SUR5
        , SUR6, SUR7, SUR8, SUR9, SUR10
        , SUR11, SUR12, SUR13, SUR14, SUR15
        , SUR16, SUR17, SUR18, SUR19, SUR20
        , B.EMEM_ID, A.PMEM_ID, EMP_STATE_CD
    FROM SURVEY A INNER JOIN EMP_INFO B ON (A.PMEM_ID = B.PMEM_ID)
""")


for i in cursor:
    tmp = []
    tmp.append(int(i[0]))
    tmp.append(int(i[1]))
    tmp.append(int(i[2]))
    tmp.append(int(i[3]))
    tmp.append(int(i[4]))
    tmp.append(int(i[5]))
    tmp.append(int(i[6]))
    tmp.append(int(i[7]))
    tmp.append(int(i[8]))
    tmp.append(int(i[9]))
    tmp.append(int(i[10]))
    tmp.append(int(i[11]))
    tmp.append(int(i[12]))
    tmp.append(int(i[13]))
    tmp.append(int(i[14]))
    tmp.append(int(i[15]))
    tmp.append(int(i[16]))
    tmp.append(int(i[17]))
    tmp.append(int(i[18]))
    tmp.append(int(i[19]))
    tmp_n = np.array(tmp)
    # print(tmp_n)
    # print(i[20])
    survey_data.append(tmp_n)
    emember_id.append(i[20])

data_n = np.array(survey_data)
emember_n = np.array(emember_id)

emem = np.unique(emember_n)

mu = MyUtil()
myd = mu.make_dict()
dict_emem = {}

for idx, emem_id in enumerate(emem):
    dict_emem[emem_id] = idx
    
print(dict_emem)
    
label = []

for i in emember_n:
    for key, value in dict_emem.items() :
        if key == i :
            label.append(int(value))

label_n = np.array(label)

# print(data_n.shape)
# print(label_n.shape)

np.save("data_numpy", data_n)
np.save("label_numpy", label_n)