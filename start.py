__author__ = 'phinehasz'
from Tkinter import *
import tkFont
import subprocess

class Reg (Frame):
    def __init__(self,master):
        frame = Frame(master)
        frame.pack()
        self.lab1 = Label(frame,text = "video/tag")
        self.lab1.grid(row = 0,column = 0,sticky = W)
        self.ent1 = Entry(frame)
        self.ent1.grid(row = 0,column = 1,sticky = W)

        self.lab2 = Label(frame,text = "thread:")
        self.lab2.grid(row = 1,column = 0)
        self.ent2 = Entry(frame)
        self.ent2.grid(row = 1,column = 1,sticky = W)

        self.lab3 = Label(frame,text = "begin:")
        self.lab3.grid(row = 2,column = 0)
        self.ent3 = Entry(frame)
        self.ent3.grid(row = 2,column = 1,sticky = W)

        self.button = Button(frame,text = "   start   ",command = self.Submit)
        self.button.grid(row = 3,column = 1,sticky = W)

        self.lab4 = Label(frame,text = "")
        self.lab4.grid(row = 4,column = 0,sticky = W)
   

    def Submit(self):
        choice = self.ent1.get()
        threadNum = self.ent2.get()
        begin = self.ent3.get()
        if choice == 'video' or choice == 'tag':
            self.lab4["text"] = "prepared to start..."
            #open a jar
            command = "java -jar bilibiliCrawl.jar"
            
            cmd = [command,"start",choice,"-th",threadNum,"begin",begin]
            new_cmd = " ".join(cmd)
            #file_out = subprocess.Popen(new_cmd,stdout=subprocess.PIPE,stderr=subprocess.STDOUT)
            subprocess.Popen(new_cmd)
            # while True:
            #     line = file_out.stdout.readline()
            #     print(line)
            #     if subprocess.Popen.poll(file_out)==0: #check it is end
            #         break   
            root.destroy()
        else:
            self.lab4["text"] = "Error Input!"
       
root = Tk()
root.title("bilibili crawl manager")
root.geometry('240x100') 
#open on mid of screen
root.withdraw()    #hide window
screen_width = root.winfo_screenwidth()
screen_height = root.winfo_screenheight() - 100    #under windows, taskbar may lie under the screen
root.resizable(False,False)

#add some widgets to the root window...

root.update_idletasks()
root.deiconify()    #now window size was calculated
root.withdraw()     #hide window again
root.geometry('%sx%s+%s+%s' % (root.winfo_width() + 10, root.winfo_height() + 10, (screen_width - root.winfo_width())/2, (screen_height - root.winfo_height())/2) )    #center window on desktop
root.deiconify()

#ft = tkFont.Font(family='Fixdsys', size=1, weight=tkFont.BOLD)
#Label(root, text='hello sticky', font=ft).grid()
app = Reg(root)
root.mainloop()
        