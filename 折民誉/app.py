import os
import PyPDF2
from docx2pdf import convert
import sqlite3
from flask import Flask, request, render_template, jsonify
from flask_cors import CORS

app = Flask(__name__)
CORS(app)



def get_output_result():
    # 从数据库中获取输出结果
    conn = sqlite3.connect('document.sqlite')
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM document")
    results = cursor.fetchall()

    # 构建输出字符串
    output = []
    for result in results:
        output.append((result[1], result[2]))

    conn.close()

    return output


@app.route('/select-folder', methods=['POST'])
def select_folder():
    folder_path = request.json['folderPath']
    return jsonify(folderPath=folder_path)


@app.route('/')
def index():
    return render_template('index.html')


@app.route('/search', methods=['POST'])
def search():
    keyword = request.json['keyword']
    folder_path = request.json['folderPath']
    conn = sqlite3.connect('document.sqlite')
    cursor = conn.cursor()
    cursor.execute("CREATE TABLE IF NOT EXISTS document (id INTEGER PRIMARY KEY AUTOINCREMENT, file TEXT NOT NULL, output TEXT)")
    cursor.execute("DELETE FROM document")  # 清空表格内容
    conn.commit()
    search_files(keyword, folder_path)
    cursor.execute("SELECT file, output FROM document")
    results = cursor.fetchall()
    conn.close()
    return jsonify(files=results)


def save_sqlite(file, output):
    global id
    id += 1
    document = (id, file, output)
    cursor.execute(
        "INSERT INTO document (id, file, output) VALUES (?, ?, ?)",
        document
    )
    conn.commit()


def output1(root, file):
    fileaddress = str(os.path.join(root, file))
    save_sqlite(fileaddress, '')
    save_sqlite("-----------------------------------", '')


def output2(file_address, keyword):
    pdf_file = open(file_address, 'rb')
    pdf_reader = PyPDF2.PdfReader(pdf_file)
    num_pages = len(pdf_reader.pages)
    for page in range(num_pages):
        pdf_page = pdf_reader.pages[page]
        page_text = pdf_page.extract_text()
        lines = page_text.split('\n')
        for i, line in enumerate(lines):
            if keyword in line:
                save_sqlite(f"Page {page + 1}, Line {i}: {line}", line)
        save_sqlite("", '')


def search_files(keyword, path):
    for root, dirs, files in os.walk(path):
        for file in files:
            if file.endswith('.docx'):
                file_address = os.path.join(root, "output.pdf")
                output1(root, file)
                convert(os.path.join(root, file), file_address)
                output2(os.path.join(root, "output.pdf"), keyword)
                os.remove(file_address)
            elif file.endswith('.pdf'):
                output1(root, file)
                output2(os.path.join(root, file), keyword)


if __name__ == "__main__":
    id = 0
    if os.path.exists('document.sqlite'):
        os.remove('document.sqlite')
    conn = sqlite3.connect('document.sqlite')
    cursor = conn.cursor()
    cursor.execute("CREATE TABLE document (id INTEGER PRIMARY KEY AUTOINCREMENT, file TEXT NOT NULL, output TEXT)")
    conn.commit()

    app.run(host='0.0.0.0', port=5000)
