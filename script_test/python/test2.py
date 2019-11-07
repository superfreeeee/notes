from sympy import diff,symbols
import numpy as np

def cal_f(x,y):
#    x, y = symbols('x y')
    return (1-x)**2+100*(y-x**2)**2

def cal_dx(x,y):
    x0, y0 = x, y
    x,y = symbols('x y')
    return diff(cal_f(x,y),x).subs(x, x0).subs(y, y0)

def cal_dy(x,y):
    x0, y0 = x, y
    x,y = symbols('x y')
    return diff(cal_f(x,y),y).subs(x, x0).subs(y, y0)

def train_grad(max_iter = 100000, step = 0.001):
    w = np.zeros((2,), dtype = np.float32)
    loss = 20
    iter_count = 0
    while loss > 0.001 and iter_count < max_iter:
        err = np.zeros((2,), dtype = np.float32)
        err[0] = cal_dx(w[0],w[1])
        err[1] = cal_dy(w[0],w[1])
        
        for j in range(2):
            w[j] -= step * err[j]
            
        loss = cal_f(w[0],w[1])
        
        print("iter_count:",iter_count, "the loss:",loss)
        iter_count += 1
    return iter_count, loss, w

if __name__ == '__main__':
    iter_count, loss, w = train_grad()
    print('iter_count:', iter_count)
    print('loss:', loss)
    print('w:', w)