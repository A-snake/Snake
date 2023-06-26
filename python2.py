import os
import sqlite3
from flask import Flask, request, send_file

app = Flask(__name__)

@app.route('/search', methods=['POST'])
def search():
    data = request.get_json()
    dir = data['dir']
    keyword = data['keyword']
    conn = sqlite3.connect('files.db')
    c = conn.cursor()
    c.execute("SELECT * FROM files WHERE name LIKE ? OR path LIKE ?", ('%' + keyword + '%', '%' + keyword + '%'))
    files = [{'name': row[0], 'path': row[1], 'size': row[2]} for row in c.fetchall()]
    conn.close()
    return json.dumps(files)

@app.route('/save', methods=['POST'])
def save():
    data = request.get_json()
    files = data['files']
    content = ''
    for file in files:
        with open(file, 'r') as f:
            content += f.read()
    return content

if __name__ == '__main__':

    from gevent import pywsgi

    server = pywsgi.WSGIServer(('0.0.0.0',5000),app)
    server.serve_forever()

    conn = sqlite3.connect('files.db')
    c = conn.cursor()
    c.execute("CREATE TABLE IF NOT EXISTS files (name TEXT, path TEXT, size INTEGER)")
    conn.commit()
    conn.close()
    app.run()