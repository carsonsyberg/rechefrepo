f= open("guru99.txt","w+")
print("test")
for i in range(10):
     f.write("This is line %d\r\n" % (i+1))
f.close()