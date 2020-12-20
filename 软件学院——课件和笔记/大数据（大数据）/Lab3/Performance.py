
# coding: utf-8

# The new topics on i). performnace evaluation of clasifiers and ii). multiclass calssification
# are presented in this notebook by following the codes of "Binary_classifier.ipynb", which you have just finished.
# 
# So, you may quickly run the first part of codes and then star with new topics.
# 
# 

# # MNIST

# In[ ]:


from sklearn.datasets import fetch_mldata
mnist = fetch_mldata('MNIST original')
mnist


# In[ ]:


X, y = mnist["data"], mnist["target"]
X.shape


# In[ ]:


y.shape


# In[ ]:


28*28


# In[ ]:


get_ipython().run_line_magic('matplotlib', 'inline')
import matplotlib
import matplotlib.pyplot as plt

some_digit = X[36000]
some_digit_image = some_digit.reshape(28, 28)
plt.imshow(some_digit_image, cmap = matplotlib.cm.binary,
           interpolation="nearest")
plt.axis("off")

# save_fig("some_digit_plot")
plt.show()


# In[ ]:


def plot_digit(data):
    image = data.reshape(28, 28)
    plt.imshow(image, cmap = matplotlib.cm.binary,
               interpolation="nearest")
    plt.axis("off")


# In[ ]:


# EXTRA
def plot_digits(instances, images_per_row=10, **options):
    size = 28
    images_per_row = min(len(instances), images_per_row)
    images = [instance.reshape(size,size) for instance in instances]
    n_rows = (len(instances) - 1) // images_per_row + 1
    row_images = []
    n_empty = n_rows * images_per_row - len(instances)
    images.append(np.zeros((size, size * n_empty)))
    for row in range(n_rows):
        rimages = images[row * images_per_row : (row + 1) * images_per_row]
        row_images.append(np.concatenate(rimages, axis=1))
    image = np.concatenate(row_images, axis=0)
    plt.imshow(image, cmap = matplotlib.cm.binary, **options)
    plt.axis("off")


# In[ ]:


import numpy as np
plt.figure(figsize=(9,9))
example_images = np.r_[X[:12000:600], X[13000:30600:600], X[30600:60000:590]]
plot_digits(example_images, images_per_row=10)
#save_fig("more_digits_plot")
plt.show()


# In[ ]:


y[36000]


# In[ ]:


X_train, X_test, y_train, y_test = X[:60000], X[60000:], y[:60000], y[60000:]


# In[ ]:


import numpy as np

shuffle_index = np.random.permutation(60000)
X_train, y_train = X_train[shuffle_index], y_train[shuffle_index]


# # Binary classifier

# In[ ]:


y_train_5 = (y_train == 5)
y_test_5 = (y_test == 5)


# In[ ]:


from sklearn.linear_model import SGDClassifier

sgd_clf = SGDClassifier(random_state=42)
sgd_clf.fit(X_train, y_train_5)


# In[ ]:


sgd_clf.predict([some_digit])


# In my lecture, I introduced two methods for performance evaualtions of a binary classifer,
# 
# -"Confusion matrix"
# 
# -"ROC"
# 
# Actually, there also other methods.

# # Performance Measures

# A good way to evaluate a model is to use cross-validation (I will talk about it in later lecture).  Here, we simply accept it and use it.
# 
# Let’s use the cross_val_score() function to evaluate your SGDClassifier model (This model will be introduced in next lecture).
# using K-fold cross-validation, with three folds. 
# 
# 
# Remember that K-fold cross-validation means splitting the training set into K-folds (in this case, three), then making predictions and evaluating them on each fold using a model trained on the remaining folds.
# 
# 

# In[ ]:


from sklearn.model_selection import cross_val_score
cross_val_score(sgd_clf, X_train, y_train_5, cv=3, scoring="accuracy")

# the output display some "warning", just simply ignore it!


# Wow! Above 94% accuracy (ratio of correct predictions) on all cross-validation folds?
# 
# This looks amazing, doesn’t it? Well, before you get too excited, let’s look at a very
# dumb classifier that just classifies every single image in the “not-5” class:
#     
#     
#     
#     
#     

# In[ ]:


from sklearn.base import BaseEstimator
class Never5Classifier(BaseEstimator):
    def fit(self, X, y=None):
        pass
    def predict(self, X):
        return np.zeros((len(X), 1), dtype=bool)


# In[ ]:


never_5_clf = Never5Classifier()
cross_val_score(never_5_clf, X_train, y_train_5, cv=3, scoring="accuracy")


# That’s right, it has over 90% accuracy! This is simply because only about 10% of the
# images are 5s, so if you always guess that an image is not a 5, you will be right about
# 90% of the time. Beats Nostradamus.
# 
# This demonstrates why accuracy is generally not the preferred performance measure
# for classifiers, especially when you are dealing with skewed datasets (i.e., when some
# classes are much more frequent than others).
# 
# 

# # Confusion Matrix

# A much better way to evaluate the performance of a classifier is to look at the confu‐
# sion matrix. 
# 
# The general idea is to count the number of times instances of class A are
# classified as class B, as explained in the lecture.
# 
# To compute the confusion matrix, you first need to have a set of predictions, so they
# can be compared to the actual targets. 
# 
# You could make predictions on the test set, but let’s keep it untouched for now (remember that you want to use the test set only at the very end of your project, once you have a classifier that you are ready to launch).
# 
# 
# Instead, you can use the cross_val_predict() function:

# In[ ]:


from sklearn.model_selection import cross_val_predict

y_train_pred = cross_val_predict(sgd_clf, X_train, y_train_5, cv=3)

Just like the cross_val_score() function, cross_val_predict() performs K-fold
cross-validation, but instead of returning the evaluation scores, it returns the predic‐
tions made on each test fold. 

This means that you get a clean prediction for each instance in the training set (“clean” meaning that the prediction is made by a model
that never saw the data during training.
# Now you are ready to get the confusion matrix using the confusion_matrix() func‐
# tion. Just pass it the target classes ( y_train_5 ) and the predicted classes
# ( y_train_pred ):

# In[ ]:


from sklearn.metrics import confusion_matrix

confusion_matrix(y_train_5, y_train_pred)

# the confusion matrix is produced for this case:


# In[ ]:


y_train_perfect_predictions = y_train_5


# In[ ]:


confusion_matrix(y_train_5, y_train_perfect_predictions)

Each row of this array in a confusion matrix represents an actual class, while each column represents a predicted class. 

The first row of this matrix considers non-5 images (the negative class): 53,373 of them were correctly classified as non-5s (they are called true negatives), while the remaining 1,206 were wrongly classified as 5s (false positives).

The second row considers the images of 5s (the positive class): 1,073 were wrongly
classified as non-5s (false negatives), while the remaining 3,668 were correctly classi‐
fied as 5s (true positives). 

A perfect classifier would have only true positives and true
negatives, so its confusion matrix would have nonzero values only on its main diago‐
nal (top left to bottom right).

The confusion matrix gives you a lot of information, but sometimes you may prefer a
more concise metric.

# In[ ]:


# Scikit-Learn provides several functions to compute classifier metrics, 
# including precision and recall:
    


from sklearn.metrics import precision_score, recall_score

precision_score(y_train_5, y_train_pred)

 # with confusion matrix, precision =  TP/(TP+FP), see the lecture-slide,


# In[ ]:


recall_score(y_train_5, y_train_pred)   #  recall =  TP/(TP+FN)


# Now your 5-detector does not look as shiny as it did when you looked at its accuracy.
# When it claims an image represents a 5, it is correct only 75% of the time. Moreover,
# it only detects 68% of the 5s.

# Review the slide for Confusion matrix, you may try to calcualate other perfromance measures, 
# indicated in the matrix difination.
# 
# 

# # ROC curves

# The receiver operating characteristic (ROC) curve is another common tool used with
# binary classifiers. 
# 
# It is very similar to the precision/recall curve, but instead of plotting precision versus recall, the ROC curve plots the true positive rate (another name
# for recall) against the false positive rate. 
# 
# The FPR is the ratio of negative instances that are incorrectly classified as positive. It is equal to one minus the true negative rate, which is the ratio of negative instances that are correctly classified as negative. The TNR is also called specificity. Hence the ROC curve plots sensitivity (recall) versus 1 – specificity.
# 
# To plot the ROC curve, you first need to compute the TPR and FPR for various thres‐
# hold values, using the roc_curve() function:

# In[ ]:


import numpy as np

shuffle_index = np.random.permutation(60000)
X_train, y_train = X_train[shuffle_index], y_train[shuffle_index]


# In[ ]:


from sklearn.metrics import roc_curve

fpr, tpr, thresholds = roc_curve(y_train_5, y_scores)


# In[ ]:


def plot_roc_curve(fpr, tpr, label=None):
    plt.plot(fpr, tpr, linewidth=2, label=label)
    plt.plot([0, 1], [0, 1], 'k--')
    plt.axis([0, 1, 0, 1])
    plt.xlabel('False Positive Rate', fontsize=16)
    plt.ylabel('True Positive Rate', fontsize=16)

plt.figure(figsize=(8, 6))
plot_roc_curve(fpr, tpr)
#save_fig("roc_curve_plot")
plt.show()


# In[ ]:


from sklearn.metrics import roc_auc_score

roc_auc_score(y_train_5, y_scores)


# In[ ]:


from sklearn.ensemble import RandomForestClassifier
forest_clf = RandomForestClassifier(random_state=42)
y_probas_forest = cross_val_predict(forest_clf, X_train, y_train_5, cv=3,
                                    method="predict_proba")


# In[ ]:


y_scores_forest = y_probas_forest[:, 1] # score = proba of positive class
fpr_forest, tpr_forest, thresholds_forest = roc_curve(y_train_5,y_scores_forest)


# In[ ]:


plt.figure(figsize=(8, 6))
plt.plot(fpr, tpr, "b:", linewidth=2, label="SGD")
plot_roc_curve(fpr_forest, tpr_forest, "Random Forest")
plt.legend(loc="lower right", fontsize=16)
save_fig("roc_curve_comparison_plot")
plt.show()


# In[ ]:


roc_auc_score(y_train_5, y_scores_forest)


# In[ ]:


y_train_pred_forest = cross_val_predict(forest_clf, X_train, y_train_5, cv=3)
precision_score(y_train_5, y_train_pred_forest)


# In[ ]:


recall_score(y_train_5, y_train_pred_forest)


# # Multiclass classification

# In[ ]:


sgd_clf.fit(X_train, y_train)
sgd_clf.predict([some_digit])


# In[ ]:


some_digit_scores = sgd_clf.decision_function([some_digit])
some_digit_scores


# In[ ]:


np.argmax(some_digit_scores)


# In[ ]:


sgd_clf.classes_


# In[ ]:


sgd_clf.classes_[1]


# In[ ]:


from sklearn.multiclass import OneVsOneClassifier
ovo_clf = OneVsOneClassifier(SGDClassifier(random_state=42))
ovo_clf.fit(X_train, y_train)
ovo_clf.predict([some_digit])


# In[ ]:


len(ovo_clf.estimators_)


# In[ ]:


forest_clf.fit(X_train, y_train)
forest_clf.predict([some_digit])


# In[ ]:


forest_clf.predict_proba([some_digit])


# In[ ]:


cross_val_score(sgd_clf, X_train, y_train, cv=3, scoring="accuracy")


# In[ ]:


from sklearn.preprocessing import StandardScaler
scaler = StandardScaler()
X_train_scaled = scaler.fit_transform(X_train.astype(np.float64))
cross_val_score(sgd_clf, X_train_scaled, y_train, cv=3, scoring="accuracy")


# In[ ]:


y_train_pred = cross_val_predict(sgd_clf, X_train_scaled, y_train, cv=3)
conf_mx = confusion_matrix(y_train, y_train_pred)
conf_mx


# In[ ]:


def plot_confusion_matrix(matrix):
    """If you prefer color and a colorbar"""
    fig = plt.figure(figsize=(8,8))
    ax = fig.add_subplot(111)
    cax = ax.matshow(conf_mx)
    fig.colorbar(cax)


# In[ ]:


plt.matshow(conf_mx, cmap=plt.cm.gray)
# save_fig("confusion_matrix_plot", tight_layout=False)
plt.show()


# In[ ]:


row_sums = conf_mx.sum(axis=1, keepdims=True)
norm_conf_mx = conf_mx / row_sums


# In[ ]:


np.fill_diagonal(norm_conf_mx, 0)
plt.matshow(norm_conf_mx, cmap=plt.cm.gray)
#save_fig("confusion_matrix_errors_plot", tight_layout=False)
plt.show()


# In[ ]:


cl_a, cl_b = 3, 5
X_aa = X_train[(y_train == cl_a) & (y_train_pred == cl_a)]
X_ab = X_train[(y_train == cl_a) & (y_train_pred == cl_b)]
X_ba = X_train[(y_train == cl_b) & (y_train_pred == cl_a)]
X_bb = X_train[(y_train == cl_b) & (y_train_pred == cl_b)]

plt.figure(figsize=(8,8))
plt.subplot(221); plot_digits(X_aa[:25], images_per_row=5)
plt.subplot(222); plot_digits(X_ab[:25], images_per_row=5)
plt.subplot(223); plot_digits(X_ba[:25], images_per_row=5)
plt.subplot(224); plot_digits(X_bb[:25], images_per_row=5)
#save_fig("error_analysis_digits_plot")
plt.show()







from sklearn.metrics import confusion_matrix

confusion_matrix(y_train_5, y_train_pred)


# In[ ]:


y_train_perfect_predictions = y_train_5


# In[ ]:


confusion_matrix(y_train_5, y_train_perfect_predictions)


# In[ ]:


from sklearn.metrics import precision_score, recall_score

precision_score(y_train_5, y_train_pred)


# In[ ]:


4344 / (4344 + 1307)


# In[ ]:


recall_score(y_train_5, y_train_pred)


# In[ ]:


4344 / (4344 + 1077)


# In[ ]:


from sklearn.metrics import f1_score
f1_score(y_train_5, y_train_pred)


# In[ ]:


4344 / (4344 + (1077 + 1307)/2)


# In[ ]:


y_scores = sgd_clf.decision_function([some_digit])
y_scores


# In[ ]:


threshold = 0
y_some_digit_pred = (y_scores > threshold)


# In[ ]:


y_some_digit_pred


# In[ ]:


threshold = 200000
y_some_digit_pred = (y_scores > threshold)
y_some_digit_pred


# In[ ]:


y_scores = cross_val_predict(sgd_clf, X_train, y_train_5, cv=3,
                             method="decision_function")


# In[ ]:


from sklearn.metrics import precision_recall_curve

precisions, recalls, thresholds = precision_recall_curve(y_train_5, y_scores)


# In[ ]:


def plot_precision_recall_vs_threshold(precisions, recalls, thresholds):
    plt.plot(thresholds, precisions[:-1], "b--", label="Precision", linewidth=2)
    plt.plot(thresholds, recalls[:-1], "g-", label="Recall", linewidth=2)
    plt.xlabel("Threshold", fontsize=16)
    plt.legend(loc="upper left", fontsize=16)
    plt.ylim([0, 1])

plt.figure(figsize=(8, 4))
plot_precision_recall_vs_threshold(precisions, recalls, thresholds)
plt.xlim([-700000, 700000])
save_fig("precision_recall_vs_threshold_plot")
plt.show()


# In[ ]:


(y_train_pred == (y_scores > 0)).all()


# In[ ]:


y_train_pred_90 = (y_scores > 70000)


# In[ ]:


precision_score(y_train_5, y_train_pred_90)


# In[ ]:


recall_score(y_train_5, y_train_pred_90)


# In[ ]:


def plot_precision_vs_recall(precisions, recalls):
    plt.plot(recalls, precisions, "b-", linewidth=2)
    plt.xlabel("Recall", fontsize=16)
    plt.ylabel("Precision", fontsize=16)
    plt.axis([0, 1, 0, 1])

plt.figure(figsize=(8, 6))
plot_precision_vs_recall(precisions, recalls)
save_fig("precision_vs_recall_plot")
plt.show()


# # ROC curves

# In[ ]:


from sklearn.metrics import roc_curve

fpr, tpr, thresholds = roc_curve(y_train_5, y_scores)


# In[ ]:


def plot_roc_curve(fpr, tpr, label=None):
    plt.plot(fpr, tpr, linewidth=2, label=label)
    plt.plot([0, 1], [0, 1], 'k--')
    plt.axis([0, 1, 0, 1])
    plt.xlabel('False Positive Rate', fontsize=16)
    plt.ylabel('True Positive Rate', fontsize=16)

plt.figure(figsize=(8, 6))
plot_roc_curve(fpr, tpr)
save_fig("roc_curve_plot")
plt.show()


# In[ ]:


from sklearn.metrics import roc_auc_score

roc_auc_score(y_train_5, y_scores)


# In[ ]:


from sklearn.ensemble import RandomForestClassifier
forest_clf = RandomForestClassifier(random_state=42)
y_probas_forest = cross_val_predict(forest_clf, X_train, y_train_5, cv=3,
                                    method="predict_proba")


# In[ ]:


y_scores_forest = y_probas_forest[:, 1] # score = proba of positive class
fpr_forest, tpr_forest, thresholds_forest = roc_curve(y_train_5,y_scores_forest)


# In[ ]:


plt.figure(figsize=(8, 6))
plt.plot(fpr, tpr, "b:", linewidth=2, label="SGD")
plot_roc_curve(fpr_forest, tpr_forest, "Random Forest")
plt.legend(loc="lower right", fontsize=16)
save_fig("roc_curve_comparison_plot")
plt.show()


# In[ ]:


roc_auc_score(y_train_5, y_scores_forest)


# In[ ]:


y_train_pred_forest = cross_val_predict(forest_clf, X_train, y_train_5, cv=3)
precision_score(y_train_5, y_train_pred_forest)


# In[ ]:


recall_score(y_train_5, y_train_pred_forest)


# # Multiclass classification

# In[ ]:


sgd_clf.fit(X_train, y_train)
sgd_clf.predict([some_digit])


# In[ ]:


some_digit_scores = sgd_clf.decision_function([some_digit])
some_digit_scores


# In[ ]:


np.argmax(some_digit_scores)


# In[ ]:


sgd_clf.classes_


# In[ ]:


sgd_clf.classes_[5]


# In[ ]:


from sklearn.multiclass import OneVsOneClassifier
ovo_clf = OneVsOneClassifier(SGDClassifier(random_state=42))
ovo_clf.fit(X_train, y_train)
ovo_clf.predict([some_digit])


# In[ ]:


len(ovo_clf.estimators_)


# In[ ]:


forest_clf.fit(X_train, y_train)
forest_clf.predict([some_digit])


# In[ ]:


forest_clf.predict_proba([some_digit])


# In[ ]:


cross_val_score(sgd_clf, X_train, y_train, cv=3, scoring="accuracy")


# In[ ]:


from sklearn.preprocessing import StandardScaler
scaler = StandardScaler()
X_train_scaled = scaler.fit_transform(X_train.astype(np.float64))
cross_val_score(sgd_clf, X_train_scaled, y_train, cv=3, scoring="accuracy")


# In[ ]:


y_train_pred = cross_val_predict(sgd_clf, X_train_scaled, y_train, cv=3)
conf_mx = confusion_matrix(y_train, y_train_pred)
conf_mx


# In[ ]:


def plot_confusion_matrix(matrix):
    """If you prefer color and a colorbar"""
    fig = plt.figure(figsize=(8,8))
    ax = fig.add_subplot(111)
    cax = ax.matshow(conf_mx)
    fig.colorbar(cax)


# In[ ]:


plt.matshow(conf_mx, cmap=plt.cm.gray)
save_fig("confusion_matrix_plot", tight_layout=False)
plt.show()


# In[ ]:


row_sums = conf_mx.sum(axis=1, keepdims=True)
norm_conf_mx = conf_mx / row_sums


# In[ ]:


np.fill_diagonal(norm_conf_mx, 0)
plt.matshow(norm_conf_mx, cmap=plt.cm.gray)
save_fig("confusion_matrix_errors_plot", tight_layout=False)
plt.show()


# In[ ]:


cl_a, cl_b = 3, 5
X_aa = X_train[(y_train == cl_a) & (y_train_pred == cl_a)]
X_ab = X_train[(y_train == cl_a) & (y_train_pred == cl_b)]
X_ba = X_train[(y_train == cl_b) & (y_train_pred == cl_a)]
X_bb = X_train[(y_train == cl_b) & (y_train_pred == cl_b)]

plt.figure(figsize=(8,8))
plt.subplot(221); plot_digits(X_aa[:25], images_per_row=5)
plt.subplot(222); plot_digits(X_ab[:25], images_per_row=5)
plt.subplot(223); plot_digits(X_ba[:25], images_per_row=5)
plt.subplot(224); plot_digits(X_bb[:25], images_per_row=5)
save_fig("error_analysis_digits_plot")
plt.show()


# # Multilabel classification

# In[ ]:


from sklearn.neighbors import KNeighborsClassifier

y_train_large = (y_train >= 7)
y_train_odd = (y_train % 2 == 1)
y_multilabel = np.c_[y_train_large, y_train_odd]

knn_clf = KNeighborsClassifier()
knn_clf.fit(X_train, y_multilabel)


# In[ ]:


knn_clf.predict([some_digit])


# In[ ]:


y_train_knn_pred = cross_val_predict(knn_clf, X_train, y_train, cv=3)
f1_score(y_train, y_train_knn_pred, average="macro")


# # Multioutput classification

# In[ ]:


noise = np.random.randint(0, 100, (len(X_train), 784))
X_train_mod = X_train + noise
noise = np.random.randint(0, 100, (len(X_test), 784))
X_test_mod = X_test + noise
y_train_mod = X_train
y_test_mod = X_test


# In[ ]:


some_index = 5500
plt.subplot(121); plot_digit(X_test_mod[some_index])
plt.subplot(122); plot_digit(y_test_mod[some_index])
save_fig("noisy_digit_example_plot")
plt.show()


# In[ ]:


knn_clf.fit(X_train_mod, y_train_mod)
clean_digit = knn_clf.predict([X_test_mod[some_index]])
plot_digit(clean_digit)
save_fig("cleaned_digit_example_plot")


# # Extra material

# ## Dummy (ie. random) classifier

# In[ ]:


from sklearn.dummy import DummyClassifier
dmy_clf = DummyClassifier()
y_probas_dmy = cross_val_predict(dmy_clf, X_train, y_train_5, cv=3, method="predict_proba")
y_scores_dmy = y_probas_dmy[:, 1]


# In[ ]:


fprr, tprr, thresholdsr = roc_curve(y_train_5, y_scores_dmy)
plot_roc_curve(fprr, tprr)


# ## KNN classifier

# In[ ]:


from sklearn.neighbors import KNeighborsClassifier
knn_clf = KNeighborsClassifier(n_jobs=-1, weights='distance', n_neighbors=4)
knn_clf.fit(X_train, y_train)


# In[ ]:


y_knn_pred = knn_clf.predict(X_test)


# In[ ]:


from sklearn.metrics import accuracy_score
accuracy_score(y_test, y_knn_pred)


# In[ ]:


from scipy.ndimage.interpolation import shift
def shift_digit(digit_array, dx, dy, new=0):
    return shift(digit_array.reshape(28, 28), [dy, dx], cval=new).reshape(784)

plot_digit(shift_digit(some_digit, 5, 1, new=100))


# In[ ]:


X_train_expanded = [X_train]
y_train_expanded = [y_train]
for dx, dy in ((1, 0), (-1, 0), (0, 1), (0, -1)):
    shifted_images = np.apply_along_axis(shift_digit, axis=1, arr=X_train, dx=dx, dy=dy)
    X_train_expanded.append(shifted_images)
    y_train_expanded.append(y_train)

X_train_expanded = np.concatenate(X_train_expanded)
y_train_expanded = np.concatenate(y_train_expanded)
X_train_expanded.shape, y_train_expanded.shape


# In[ ]:


knn_clf.fit(X_train_expanded, y_train_expanded)


# In[ ]:


y_knn_expanded_pred = knn_clf.predict(X_test)


# In[ ]:


accuracy_score(y_test, y_knn_expanded_pred)


# In[ ]:


ambiguous_digit = X_test[2589]
knn_clf.predict_proba([ambiguous_digit])


# In[ ]:


plot_digit(ambiguous_digit)


# # Exercise solutions

# **Coming soon**















# Stop here.
