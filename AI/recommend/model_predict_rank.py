import cx_Oracle
import os
import numpy as np
import tensorflow as tf
from jobara.make_function import make_dict
import auth_prop

LOCATION = r"D:\instantclient_21_3"
os.environ["PATH"] = LOCATION + ";" + os.environ["PATH"]

oracle_acc = auth_prop.oracle
connection = cx_Oracle.connect(oracle_acc['user'], oracle_acc['pw'], oracle_acc['ip'])
cursor = connection.cursor()

sql1 = """SELECT A.SUR1, A.SUR2, A.SUR3, A.SUR4, A.SUR5
                , A.SUR6, A.SUR7, A.SUR8, A.SUR9, A.SUR10
                , A.SUR11, A.SUR12, A.SUR13, A.SUR14, A.SUR15
                , A.SUR16, A.SUR17, A.SUR18, A.SUR19, A.SUR20
                , A.PMEM_ID, A.SUR_DATE
        FROM   SURVEY A
        WHERE  A.SUR_DATE = (
                    SELECT MAX(B.SUR_DATE)
                    FROM   SURVEY B
                    WHERE  B.PMEM_ID = A.PMEM_ID
                )"""

model = tf.keras.models.load_model('model.h5')


cursor.execute(sql1)

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
    print(tmp)
    
    input_r = np.reshape(tmp,(1,20))
    predictions = model.predict(input_r)
    
    emem_id = []    # insert쿼리에 넣을 emem_id 배열
    rate = []   # insert쿼리에 넣을 rate 배열
    
    dict_emem = make_dict()
    
    for idx in range(10):
        pred_cnt = np.argmax(predictions[0])
        # print(pred_cnt)
        for key, value in dict_emem.items():
            if value == pred_cnt:
                # print(key)  # 기업 아이디
                emem_id.append(key)
                
        rate.append(predictions[0][pred_cnt]*100)
        # print(str(idx)+"  "+str(predictions[0][pred_cnt]*100))
        # print("pmem_id",i[20])
        predictions[0][pred_cnt] = 0
        
    pmem_id = i[20] # insert 쿼리에 넣을 pmem_id
    sur_date = i[21]    #insert 쿼리에 넣을 sur_date
    
    cs = connection.cursor()
    sql2 = """INSERT INTO recommend (
                pmem_id, 
                emem_id_1, emem_id_2, emem_id_3, emem_id_4, emem_id_5,
                emem_id_6, emem_id_7, emem_id_8, emem_id_9, emem_id_10,
                rate_1, rate_2, rate_3, rate_4, rate_5,
                rate_6, rate_7, rate_8, rate_9, rate_10,
                sur_date
            ) VALUES (
                :1
                , :2 , :3 , :4 , :5, :6 
                , :7 , :8 , :9 , :10, :11 
                , :12 , :13 , :14 , :15 , :16
                , :17 , :18 , :19 , :20 , :21
                , :22
            )
    """
    cs.execute(sql2,(pmem_id, emem_id[0], emem_id[1], emem_id[2], emem_id[3]
                     , emem_id[4], emem_id[5], emem_id[6], emem_id[7], emem_id[8]
                     , emem_id[9], rate[0], rate[1], rate[2], rate[3], rate[4]
                     , rate[5], rate[6], rate[7], rate[8], rate[9], sur_date))
    cs.close()
    connection.commit()
    
connection.close()