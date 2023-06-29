import os
import PyPDF2
import docx
import tkinter as tk
from tkinter import filedialog
import pymysql


def save_sql(file_name,keyword,location,text):
    global id
    id += 1

    # 插入数据
    sql = "INSERT INTO document_list (id, keyword,file_name,location,text) VALUES (%s, %s,%s,%s,%s)"
    values = [(id, keyword,file_name,location,text)]

    cursor.executemany(sql, values)

    conn.commit()


def start_output(root, file):
    fileaddress = str(os.path.join(root, file))
    output.insert(tk.END, fileaddress + "\n")
    output.insert(tk.END, "----------------------------------------------\n")

def docx_output(file_address,keyword):
    doc=docx.Document(file_address)
    paragraph_list = []
    temp="\033[31m{}\033[0m".format(keyword)
    for paragraph in doc.paragraphs:
        paragraph_list.append(paragraph.text.split("\n"))
    for i,paragraph in enumerate(paragraph_list):
        if keyword in str(paragraph):
            result=str(paragraph).replace(keyword,temp)
            output.insert(tk.END, "Paragraph {}: {}\n".format(i+1,result))
            location = "Paragraph {}".format(i+1)
            save_sql(file_address, keyword, location,paragraph)
    output.insert(tk.END, "\n")

def pdf_output(file_address, keyword):
    temp = "\033[31m{}\033[0m".format(keyword)
    pdf_file = open(file_address, 'rb')
    pdf_reader = PyPDF2.PdfReader(pdf_file)
    num_pages = len(pdf_reader.pages)
    for page in range(num_pages):
        pdf_page = pdf_reader.pages[page]
        page_text = pdf_page.extract_text()
        lines = page_text.split('\n')
        for i, line in enumerate(lines):
            if keyword in line:
                result = line.replace(keyword, temp)
                output.insert(tk.END, f"Page {page + 1}, Line {i}: {result}\n")
                location="Page {},Line {}".format(page+1,i)
                save_sql(file_address,keyword,location,line)
        output.insert(tk.END, "\n")
    pdf_file.close()

def search_files(keyword, path):
    for root, dirs, files in os.walk(path):
        for file in files:
            if file.endswith('.docx'):
                start_output(root, file)
                docx_output(os.path.join(root, file), keyword)
            elif file.endswith('.pdf'):
                start_output(root, file)
                pdf_output(os.path.join(root, file), keyword)

def get_input1():
    input_text = entry_keyword.get()
    output.delete(1.0, tk.END)  # 清空输出框内容
    folder_path = filedialog.askdirectory()  # 弹出文件夹选择对话框
    if folder_path:
        search_files(input_text, folder_path)

def get_input2():
    host = entry_keyword.get()
    user = entry_keyword.get()
    password = entry_keyword.get()
    label = tk.Label(window, text="数据库创建成功!", font=("Arial", 12),foreground="red")
    label.place(x=380,y=170)

if __name__ == "__main__":
    id = 0
    # 连接数据库
    conn = pymysql.connect(host='localhost', user='root', password='123456')
    # 创建数据库
    cursor = conn.cursor()
    cursor.execute("DROP DATABASE IF EXISTS document")
    cursor.execute("CREATE DATABASE IF NOT EXISTS document")
    cursor.execute("USE document")
    # 创建表
    cursor.execute("CREATE TABLE IF NOT EXISTS document_list (id INT AUTO_INCREMENT PRIMARY KEY,"
                   "keyword VARCHAR(255), "
                   "file_name VARCHAR(255),"
                   "location VARCHAR(255),"
                   "text VARCHAR(255))")
    # 提交事务
    conn.commit()

    window = tk.Tk()
    window.title("文件检索系统")
    window.geometry("600x600+700+100")
    window.maxsize(600, 600)
    window.minsize(200, 300)

    entry_keyword = tk.Entry(window)
    entry_keyword.place(x=10,y=10)
    entry_keyword.insert(0, "keyword")  # 在输入框的开头插入文本

    entry_host = tk.Entry(window)
    entry_host.place(x=380, y=10)
    entry_host.insert(0, "localhost")

    entry_user = tk.Entry(window)
    entry_user.place(x=380, y=50)
    entry_user.insert(0, "root")

    entry_password = tk.Entry(window)
    entry_password.place(x=380, y=90)
    entry_password.insert(0, "123456")

    button1 = tk.Button(window, text="搜索", command=get_input1)
    button1.place(x=170,y=8)

    button2 = tk.Button(window, text="创建数据库", command=get_input2)
    button2.place(x=380, y=130)

    output = tk.Text(window, height=40, width=50)
    output.place(x=10,y=50)

    window.mainloop()

    # 关闭连接
    cursor.close()
    conn.close()
