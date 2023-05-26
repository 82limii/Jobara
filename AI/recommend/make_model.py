import tensorflow as tf
import numpy as np

data_n = np.load("data_numpy.npy")
label_n = np.load("label_numpy.npy")
emem = np.unique(label_n)
emem_cnt = emem.size
print(data_n)
print("-----------")
print(label_n)
model = tf.keras.models.Sequential([
    tf.keras.layers.Flatten(input_shape=(20, 1)),
    tf.keras.layers.Dense(512, activation=tf.nn.relu),
    tf.keras.layers.Dense(emem_cnt, activation=tf.nn.softmax)  #기업수
])

model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])

model.fit(data_n, label_n, epochs=100)

model.save('model.h5')