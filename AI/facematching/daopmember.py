import cx_Oracle
import auth_prop

class DaoPMember:
    def __init__(self):
        oracle_acc = auth_prop.oracle
        self.conn = cx_Oracle.connect(oracle_acc['user'], oracle_acc['pw'], oracle_acc['ip'])
        self.cs = self.conn.cursor()
        
    def getData(self):
        sql = """
            select  PMEM_ID,PMEM_PIC from P_MEMBER
            where
                PMEM_PIC != 'no-image.jpg' 
                and (PMEM_ID ='java')
        """
        rs = self.cs.execute(sql)
    
        list = []
    
        for record in rs:
            list.append({'pmem_id':record[0],'pmem_pic':record[1]})
        
        return list
    
    def myinsert(self,face_enter1,face_per1, face_enter2,face_per2, face_enter3,face_per3, face_enter4,face_per4, face_enter5,face_per5,pmem_id):
        sql = f"""
            insert into FACAEMATCHING 
                (face_enter1,face_per1, face_enter2,face_per2, face_enter3,face_per3, face_enter4,face_per4, face_enter5,face_per5,pmem_id) 
            values 
                ('{face_enter1}',{face_per1}, '{face_enter2}',{face_per2}, '{face_enter3}',{face_per3}, '{face_enter4}',{face_per4}, '{face_enter5}',{face_per5}, '{pmem_id}' )
        """
        print(sql)
        self.cs.execute(sql)
        cnt = self.cs.rowcount
        self.conn.commit() 
        return 1
        
    
    def mydelete(self):
        sql = "delete from FACAEMATCHING"
        self.cs.execute(sql)
        cnt = self.cs.rowcount
        self.conn.commit()
        return cnt  
    
        
    def __del__(self):
        self.cs.close()
        self.conn.close()
        
        
if __name__ == '__main__':
    dpm = DaoPMember()
    mylist = dpm.getData()
    print("mylist",mylist)
    
    # cnt = dpm.myinsert('1','1','1','1','1', '1','1','1','1','1', '1')
    # print("cnt",cnt)