import os
import PyPDF2
from docx2pdf import convert

def output1(root,file):
    print(os.path.join(root, file))
    print("-----------------------------------")

def output2(file_address,keyword):
    pdf_file = open(file_address, 'rb')
    pdf_reader = PyPDF2.PdfReader(pdf_file)     #解析.pdf文件
    num_pages = len(pdf_reader.pages)    #获取页数
    for page in range(num_pages):
        pdf_page = pdf_reader.pages[page]
        page_text = pdf_page.extract_text()
        lines = page_text.split('\n')     #按行存储在列表中
        for i, line in enumerate(lines):
            if keyword in line:
                print(i+1, line)
        print("")
    pdf_file.close()

def search_files(keyword, path):     #根据关键字和文件夹路径检索文件
    for root, dirs, files in os.walk(path):
        for file in files:
            if file.endswith('.docx'):
                output1(root, file)
                file_address = "./test_documents/temp.pdf"     #临时.pdf文件
                convert(os.path.join(root, file), file_address)     #将.docx文件转换成.pdf文件，方便逐行检索
                output2(file_address,keyword)
                os.remove(file_address)     #删除临时.pdf文件
            elif file.endswith('.pdf'):
                output1(root,file)
                output2(os.path.join(root, file), keyword)


if __name__=="__main__":
    '''从前段获取关键字和文件夹地址后替换下方代码'''
    #keyword=
    #file_address=
    search_files('Python', './test_documents')   #测试案例
