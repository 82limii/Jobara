import os
import numpy as np
import cx_Oracle
import auth_prop


class MyUtil:

    def make_dict(self):
        LOCATION = r"D:\instantclient_21_3"
        os.environ["PATH"] = LOCATION + ";" + os.environ["PATH"]
        
        oracle_acc = auth_prop.oracle
        connection = cx_Oracle.connect(oracle_acc['user'], oracle_acc['pw'], oracle_acc['ip'])
        cursor = connection.cursor()
        
        emember_id = []
    
        cursor.execute("""
        SELECT SUR1, SUR2, SUR3, SUR4, SUR5
                , SUR6, SUR7, SUR8, SUR9, SUR10
                , SUR11, SUR12, SUR13, SUR14, SUR15
                , SUR16, SUR17, SUR18, SUR19, SUR20
                , EMEM_ID
            FROM REC_START
        """)
        
        for i in cursor:
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
            emember_id.append(i[20])
            
        emember_n = np.array(emember_id)
        
        emem = np.unique(emember_n)
        dict_emem = {}
        
        for idx, emem_id in enumerate(emem):
            dict_emem[emem_id] = idx
            
        return dict_emem
    
    
if __name__ == '__main__':
    mu = MyUtil()
    myd = mu.make_dict()
    print(myd)