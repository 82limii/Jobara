
import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import layers
import numpy as np

import pydot
import graphviz
from IPython.display import SVG
from keras.utils.vis_utils import model_to_dot





x_train = np.load("train_data_c.npy")
y_train = np.load("train_label_c.npy")

print(x_train.shape)

x_train = x_train / 255.0


inputs = keras.Input(shape=(224, 224, 3))
x = inputs
x = layers.Conv2D(64, 3, activation='relu', padding="same")(x)
x = layers.Conv2D(64, 3, activation='relu', padding="same")(x)
x = layers.MaxPooling2D(2)(x)
x = layers.Conv2D(128, 3, activation='relu', padding="same")(x)
x = layers.Conv2D(128, 3, activation='relu', padding="same")(x)
x = layers.MaxPooling2D(2)(x)
x = layers.Conv2D(256, 3, activation='relu', padding="same")(x)
x = layers.Conv2D(256, 3, activation='relu', padding="same")(x)
x = layers.MaxPooling2D(2)(x)

x = layers.Flatten()(x)
x = layers.Dense(256)(x)
x = layers.Dense(256)(x)
x = layers.Dense(30, activation='softmax')(x)
outputs = x

model = keras.Model(inputs, outputs)
model.summary()




model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])

model.fit(x_train, y_train,batch_size=16, epochs=2)

SVG(model_to_dot(Mmodel, show_shapes=True).create(prog='dot', format='svg'))

model.save('mycnn.h5')











