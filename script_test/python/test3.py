#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Mon Oct  7 23:22:54 2019

@author: superfree
"""

import numpy as np

NA = 0

def cal_f(x, y):
    return 

def cal_dx(x, y):
    return NA

def cal_dy(x, y):
    return NA

def train_grad(max_iter=100000, step=0.001):
    w = np.zeros((2,), dtype=np.float32)
    loss = 20
    iter_count = 0
    while loss > 0.001 and iter_count < max_iter:
        err = np.zeros((2,), dtype=np.float32)
        err[0] = cal_dx(w[0], w[1])
        err[1] = NA
        for j in range(2):
            w[j] -= step * err[j]
        loss = cal_f(w[0], w[1])
        print("iter_count: ", iter_count, "the loss: ", NA)
        iter_count += 1
    return w

if __name__ == '__main__':
    w = NA
    NA
    