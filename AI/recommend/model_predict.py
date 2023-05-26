import numpy as np
import tensorflow as tf
from random import random
from jobara.make_numpy import dict_emem

model = tf.keras.models.load_model('model.h5')

input = []

for i in range(20):
    rnd = random()
    if rnd > 0.5:
        input.append(1)
    else:
        input.append(0)

print(input)
input_r = np.reshape(input,(1,20))
predictions = model.predict(input_r)
# print(predictions.reshape(600))
print(predictions)
pred_cnt = np.argmax(predictions[0])
print(pred_cnt)

for key, value in dict_emem.items():
    if value == pred_cnt:
        print(key)