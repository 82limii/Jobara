import sys
import cv2
import numpy as np
import pytesseract
from _pytest.python_api import approx
import re

def ocr_namecard(src):
    
    if src is None:
        print('image load failed')
        sys.exit()
        
    src_gray = cv2.cvtColor(src, cv2.COLOR_BGR2GRAY)    # 흑백으로 변환
    
    # th, src_bin = cv2.threshold(src_gray, 0, 255, cv2.THRESH_BINARY | cv2.THRESH_TRIANGLE)  # 이진화
    th, src_bin = cv2.threshold(src_gray, 0, 255, cv2.THRESH_BINARY | cv2.THRESH_OTSU)
    print(th)
    
    contours,_ = cv2.findContours(src_bin, cv2.RETR_TREE, cv2.CHAIN_APPROX_NONE)    # 윤곽선 추출
    # contours,_ = cv2.findContours(src_bin, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_NONE)
    print(len(contours))
    
    for pts in contours:
    
        if cv2.contourArea(pts) < 10000: # 윤곽선의 넓이로 1차 필터링
            continue
    
        approx = cv2.approxPolyDP(pts, cv2.arcLength(pts, True)*0.02, True) # 점 4개의 좌표 잡기
        
        if len(approx) != 4:
            continue
        
        cv2.polylines(src, pts, True, (0, 0, 255), 3)  # 명함부분 윤곽선 표시
        w, h = 900, 500 # 명함 가로세로 비율
        
        # 4개의 점 위치 찾기
        numarr = []
        arr00 = None
        arrw0 = None
        arr0h = None
        arrwh = None
        arrw00h = []
        for i in range(0,4):
            print(i)
            num1 = approx[i, 0, :][0]
            num2 = approx[i, 0, :][1]
            sum = num1 + num2
            print('num1', num1)
            print('num2', num2)
            print('num1+num2', num1 + num2)
            numarr.append(sum)
        
        max_tmp = max(numarr)
        max_index = numarr.index(max_tmp)
        min_tmp = min(numarr)
        min_index = numarr.index(min_tmp)
        
        arr00 = np.array([approx[min_index, 0, :]])
        arrwh = np.array([approx[max_index, 0, :]])
        
        for j in range(0,4):
            if j!=max_index and j!=min_tmp:
                arrw00h.append(j)
        
        for k in arrw00h:
            if approx[k, 0, :][0] < approx[k, 0, :][1]:
                arr0h = np.array([approx[k, 0, :]])
            else:
                arrw0 = np.array([approx[k, 0, :]])
        
        result = np.array([arr00, arrw0, arr0h, arrwh])
        print('result', result)
            
        # srcQuad = np.array([[approx[0, 0, :]], [approx[1, 0, :]], [approx[2, 0, :]], [approx[3, 0, :]] ]).astype(np.float32)
        srcQuad = result.astype(np.float32)
        dstQuad = np.array([[0, 0], [w, 0], [0, h], [w, h]]).astype(np.float32)
        pers = cv2.getPerspectiveTransform(srcQuad, dstQuad)
        dst = cv2.warpPerspective(src, pers, (w, h), flags=cv2.INTER_CUBIC)
        
        cv2.imwrite("image.jpg",dst)
        img = cv2.imread("image.jpg")
        
        x1, y1, xw1, yh1 = 40, 40, 500, 80
        buyern_img = img[y1:y1+yh1, x1:x1+xw1]
        
        x2, y2, xw2, yh2 = 40, 200, 500, 60
        pern_img = img[y2:y2+yh2, x2:x2+xw2]
        
        x3, y3, xw3, yh3 = 40, 260, 500, 60
        dept_img = img[y3:y3+yh3, x3:x3+xw3]
        
        x4, y4, xw4, yh4 = 40, 320, 800, 60
        buyera_img = img[y4:y4+yh4, x4:x4+xw4]
        
        x5, y5, xw5, yh5 = 40, 380, 400, 47
        tel_img = img[y5:y5+yh5, x5:x5+xw5]
    
        x6, y6, xw6, yh6 = 440, 380, 700, 47
        phone_img = img[y6:y6+yh6, x6:x6+xw6]
    
        x7, y7, xw7, yh7 = 40, 420, 400, 47
        page_img = img[y7:y7+yh7, x7:x7+xw7]
     
        x8, y8, xw8, yh8 = 440, 420, 700, 47
        mail_img = img[y8:y8+yh8, x8:x8+xw8]
    
        buyern_str = pytesseract.image_to_string(buyern_img, lang='kor+eng')
        pern_str = pytesseract.image_to_string(pern_img, lang='kor')
        dept_str = pytesseract.image_to_string(dept_img, lang='kor')
        buyera_str = pytesseract.image_to_string(buyera_img, lang='kor+eng')
        tel_str = pytesseract.image_to_string(tel_img, lang='eng')
        phone_str = pytesseract.image_to_string(phone_img, lang='eng')
        page_str = pytesseract.image_to_string(page_img, lang='eng')
        mail_str = pytesseract.image_to_string(mail_img, lang='eng')
        
        pattern = re.compile('[a-zA-Z0-9가-힣]')
        
        if re.search(pattern,buyern_str) is not None:
            buyern_str = buyern_str.replace("\n","")
            buyern_str = buyern_str.replace(" ","")
        if re.search(pattern,pern_str) is not None:
            pern_str = pern_str.replace("\n","")
            pern_str = pern_str.replace(" ","")
        if re.search(pattern,dept_str) is not None:
            dept_str = dept_str.replace("\n","")
            dept_str = dept_str.replace(" ","")
            
            split_str = dept_str.split("/")
            dept_str_ori = split_str[0]
            posi_str = split_str[1]
        if re.search(pattern,buyera_str) is not None:
            buyera_str = buyera_str.replace("\n","")
            buyera_str = buyera_str.replace(" ","")
        if re.search(pattern,tel_str) is not None:
            tel_str = tel_str.replace("\n","")
            tel_str = tel_str.replace(" ","")
            tel_str = tel_str.replace("T","")
        if re.search(pattern,phone_str) is not None:
            phone_str = phone_str.replace("\n","")
            phone_str = phone_str.replace(" ","")
            phone_str = phone_str.replace("P","")
        if re.search(pattern,page_str) is not None:
            page_str = page_str.replace("\n","")
            page_str = page_str.replace(" ","")
            page_str = page_str.replace("H","")
        if re.search(pattern,mail_str) is not None:
            mail_str = mail_str.replace("\n","")
            mail_str = mail_str.replace(" ","")
            mail_str = mail_str.replace("E","")
        print("========")

    if buyern_str is None:
        buyern_str = ""
    if pern_str is None:
        pern_str = ""
    if dept_str_ori is None:
        dept_str_ori = ""
    if posi_str is None:
        posi_str = ""
    if buyera_str is None:
        buyera_str = ""
    if tel_str is None:
        tel_str = ""
    if phone_str is None:
        phone_str = ""
    if page_str is None:
        page_str = ""
    if mail_str is None:
        mail_str = ""
    print(buyern_str, pern_str, dept_str_ori, posi_str, buyera_str, tel_str, phone_str, page_str, mail_str)
    
    return buyern_str, pern_str, dept_str_ori, posi_str, buyera_str, tel_str, phone_str, page_str, mail_str