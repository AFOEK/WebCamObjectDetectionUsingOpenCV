import numpy as np
import cv2

def mouseRGB(event,x,y,flags,param):
	if event == cv2.EVENT_LBUTTONDOWN:
		colorsB=frame[y,x,0]
		colorsG=frame[y,x,1]
		colorsR=frame[y,x,2]
		colors=frame[y,x]
		print("Red: ",colorsR)
		print("Green: ",colorsG)
		print("Blue: ",colorsB)
		print("BRG Format: ",colors)
		print("Coordinates of pixel: x: ",x,"y: ",y)


cv2.setMouseCallback('mouseRGB',mouseRGB)
cap=cv2.VideoCapture(0)

while(True):
	ret,frame=cap.read()
	#gray=cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
	#cv2.imshow('frame',frame)
	#cv2.imshow('frame',gray)
	cv2.imshow('mouseRGB',frame)
	if cv2.waitKey(1) & 0xFF==ord('q'):
		break
cap.release()
cv2.destroyAllWindows()
