from flask import Flask
from flask import request
from flask_cors import CORS
import cv2
from jobara.recog_card import ocr_namecard
import ssl

app = Flask(__name__)
CORS(app, resources={r'*': {'origins': '*'}})

@app.route('/recog_name', methods=['GET','POST'])
def recog_card():
    if request.method == 'POST' :
        nameCard = request.files['nameCard']
        print("성공")
        print(nameCard)
        nameCard.save('static/name.jpg')
        src = cv2.imread('static/name.jpg')
        
        buyern_str, pern_str, dept_str_ori, posi_str, buyera_str, tel_str, phone_str, page_str, mail_str = ocr_namecard(src)

        myhtml = f"""
        <html>
            <head>
                <script>
                    location.href='https://192.168.141.15:443/contact/com/contactInsert.do?buyern_str={buyern_str}&pern_str={pern_str}&dept_str_ori={dept_str_ori}&posi_str={posi_str}&buyera_str={buyera_str}&tel_str={tel_str}&phone_str={phone_str}&page_str={page_str}&mail_str={mail_str}';
                </script>
            </head>
            <body>
            </body>
        </html>
        """
        
        return myhtml

if __name__ == '__main__':
    ssl_context = ssl.SSLContext(ssl.PROTOCOL_TLS)
    ssl_context.load_cert_chain(certfile='../localhost.crt', keyfile='../localhost_private.key', password='java')
    app.run(debug=True, host='0.0.0.0', port=5443, ssl_context=ssl_context)
# if __name__ == '__main__':
#     app.run(host='0.0.0.0', port='8088', debug=True)