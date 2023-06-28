'''
在上一版本的基础上，将功能在图形界面上显示
'''
import os
import PyPDF2
from docx2pdf import convert
import sqlite3
import tkinter as tk

def save_sqlite(file):
    global id
    id += 1
    document = (id, file)
    cursor.execute(
        "insert into document (id,file) values(?,?)",
        document
    )
    conn.commit()

def output1(root, file):
    fileaddress = str(os.path.join(root, file))
    output.insert(tk.END, fileaddress + "\n")
    output.insert(tk.END, "-----------------------------------\n")

def output2(file_address, keyword):
    pdf_file = open(file_address, 'rb')
    pdf_reader = PyPDF2.PdfReader(pdf_file)     #解析.pdf文件
    num_pages = len(pdf_reader.pages)       #获取页数
    for page in range(num_pages):
        pdf_page = pdf_reader.pages[page]
        page_text = pdf_page.extract_text()
        lines = page_text.split('\n')       #按行存储在列表中
        for i, line in enumerate(lines):
            if keyword in line:
                output.insert(tk.END, f"Page {page + 1}, Line {i}: {line}\n")
        output.insert(tk.END, "\n")
    pdf_file.close()

def search_files(keyword, path):        #根据关键字和文件夹路径检索文件
    for root, dirs, files in os.walk(path):
        for file in files:
            if file.endswith('.docx'):
                file_address = "./test_documents/output.pdf"         #临时.pdf文件
                output1(root, file)
                save_sqlite(os.path.join(root, file))       #将文件地址存入数据库
                convert(os.path.join(root, file), file_address)     #将.docx文件转换成.pdf文件，方便逐行检索
                output2(file_address, keyword)
                os.remove(file_address)     #删除临时.pdf文件
            elif file.endswith('.pdf'):
                output1(root, file)
                save_sqlite(os.path.join(root, file))
                output2(os.path.join(root, file), keyword)

def get_input():
    input_text = entry.get()
    output.delete(1.0, tk.END)  # 清空输出框内容
    search_files(input_text, './test_documents')

if __name__ == "__main__":
    id = 0
    dbPath = 'document.sqlite'      #创建数据库
    if os.path.exists(dbPath):
        os.remove(dbPath)
    conn = sqlite3.connect(dbPath)
    cursor = conn.cursor()
    cursor.execute('''CREATE TABLE document (id INT NOT NULL,file CHAR(50) NOT NULL);''')
    conn.commit()

    window = tk.Tk()
    window.title("文件检索系统")
    window.geometry("500x600+500+100")
    window.maxsize(600, 700)
    window.minsize(200, 300)

    entry = tk.Entry(window)
    entry.pack()

    button = tk.Button(window, text="搜索", command=get_input)
    button.pack()

    output = tk.Text(window, height=10, width=50)
    output.pack()

    window.mainloop()
